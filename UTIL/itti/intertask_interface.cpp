/*
  Author: zhenghuangkun
*/
#include <vector>
#include <map>
#include <sched.h>
#include <sys/eventfd.h>
#include <pthread.h>
#include <unistd.h>
#include <string.h>
#include <errno.h>
#include <intertask_interface.h>
#include <assertions.h>

typedef struct task_list_s {
  task_info_t admin;
  pthread_t thread;
  pthread_mutex_t queue_cond_lock;
  std::vector<MessageDef *> message_queue;
  struct epoll_event  *events;
  int nb_fd_epoll;
  int nb_events;
  int epoll_fd;
  int sem_fd;
} task_list_t;

task_list_t tasks[TASK_MAX];

extern "C" {

  const char *itti_get_message_name(MessagesIds message_id) {
    return messages_info[message_id].name;
  }

  const char *itti_get_task_name(task_id_t task_id) {
    return tasks[task_id].admin.name;
  }
  
  int itti_send_msg_to_task(task_id_t task_id, uint16_t instance, MessageDef *message)
  {
    task_list_t *t = &tasks[task_id];
    
    pthread_mutex_lock(&t->queue_cond_lock);

    if(message) {

      if(t->admin.queue_size <= t->message_queue.size()) {
        printf("task %s size %lu > %u\n", t->admin.name, t->message_queue.size(), t->admin.queue_size);
        pthread_mutex_unlock(&t->queue_cond_lock);
        //return 0;
      }

      message->ittiMsgHeader.destinationTaskId = task_id;
      message->ittiMsgHeader.instance = instance;
      
      // insert message
      t->message_queue.insert(t->message_queue.begin(), message);

      // notify thread fd
      eventfd_t sem_counter = 1;
      AssertFatal(sizeof(sem_counter) == write(t->sem_fd, &sem_counter, sizeof(sem_counter)),
        "write ng, task %s, sem_fd %d. %s\n", t->admin.name, t->sem_fd, strerror(errno));
    }
    
    pthread_mutex_unlock(&t->queue_cond_lock);
    return 0;
  }

  int itti_get_events_locked(task_id_t task_id, struct epoll_event **events)
  {
    task_list_t *t = &tasks[task_id];

    do {
      int epoll_timeout = -1;
      pthread_mutex_unlock(&t->queue_cond_lock);

      //等待事件的产生，类似于select()调用。参数events用来从内核得到事件的集合，
      //maxevents告之内核这个events有多大，这个maxevents的值不能大于创建epoll_create()时的size，
      //参数timeout是超时时间（毫秒，0会立即返回，-1将不确定，也有说法说是永久阻塞）。
      //该函数返回需要处理的事件数目，如返回0表示已超时。
      t->nb_events = epoll_wait(t->epoll_fd, t->events, t->nb_fd_epoll, epoll_timeout);

      if ( t->nb_events  < 0 && (errno == EINTR || errno == EAGAIN ) )
        pthread_mutex_lock(&t->queue_cond_lock);
      
    } while(t->nb_events < 0 && (errno == EINTR || errno == EAGAIN ));


    AssertFatal (t->nb_events >=0,
                "epoll_wait failed for task %s, nb fds %d: %s!\n",
                itti_get_task_name(task_id), t->nb_fd_epoll, strerror(errno));

    fflush(stdout);
    for(int i=0; i<t->nb_events; i++) {
      if((t->events[i].events & EPOLLIN) &&
         (t->events[i].data.fd == t->sem_fd)) {

        // clear event
        eventfd_t   sem_counter;
         /* Read will always return 1 */
        AssertFatal( sizeof(sem_counter) == read (t->sem_fd, &sem_counter, sizeof(sem_counter)), "");
        /* Mark that the event has been processed */
        t->events[i].events &= ~EPOLLIN;
      }
    }

    *events = t->events;
    return t->nb_events;
    
  }

  void itti_receive_msg(task_id_t task_id, MessageDef **received_msg)
  {
    task_list_t *t = &tasks[task_id];
    pthread_mutex_lock(&t->queue_cond_lock);

    if(t->nb_fd_epoll == 1) {
      while(t->message_queue.empty()) {
        itti_get_events_locked(task_id, &t->events);
        pthread_mutex_lock(&t->queue_cond_lock);
      }
    } else {
      if(t->message_queue.empty()) {
        itti_get_events_locked(task_id, &t->events);
        pthread_mutex_lock(&t->queue_cond_lock);
      }
    }

    if(t->message_queue.empty()){
      *received_msg = NULL;
    } else {
      *received_msg = t->message_queue.back();
      t->message_queue.pop_back();
    }

    pthread_mutex_unlock(&t->queue_cond_lock);
  }

  int itti_create_task(task_id_t task_id, void *(*start_routine)(void *), void *args_p) {
      task_list_t *t=&tasks[task_id];
      AssertFatal ( pthread_create (&t->thread, NULL, start_routine, args_p) ==0,
                    "Thread creation for task %d failed!\n", task_id);
      pthread_setname_np( t->thread, itti_get_task_name(task_id) );
      printf("Created Posix thread %s\n",  itti_get_task_name(task_id) );
      
      {
        int policy;
        struct sched_param sparam;
        memset(&sparam, 0, sizeof(sparam));
        sparam.sched_priority = sched_get_priority_max(SCHED_FIFO)-10;
        policy = SCHED_FIFO ;
        if (pthread_setschedparam(t->thread, policy, &sparam) != 0) {
          printf("task %s : Failed to set pthread priority, %s\n",  itti_get_task_name(task_id) , strerror(errno));
        }
      }
      return 0;
    }
  
  void itti_unsubscribe_event_fd(task_id_t task_id, int fd) {
      task_list_t *t=&tasks[task_id];
      t->nb_fd_epoll--;
  
      //EPOLL_CTL_ADD：注册新的fd到epfd中；
      //EPOLL_CTL_MOD：修改已经注册的fd的监听事件；
      //EPOLL_CTL_DEL：从epfd中删除一个fd；
  
      /*
        events可以是以下几个宏的集合：
        EPOLLIN ：     表示对应的文件描述符可以读（包括对端SOCKET正常关闭）；
        EPOLLOUT：    表示对应的文件描述符可以写；
        EPOLLPRI：      表示对应的文件描述符有紧急的数据可读（这里应该表示有带外数据到来）；
        EPOLLERR：     表示对应的文件描述符发生错误；
        EPOLLHUP：     表示对应的文件描述符被挂断；
        EPOLLET：      将EPOLL设为边缘触发(Edge Triggered)模式，这是相对于水平触发(Level Triggered)来说的。
        EPOLLONESHOT： 只监听一次事件，当监听完这次事件之后，如果还需要继续监听这个socket的话，
                      需要再次把这个socket加入到EPOLL队列里
      */
      
      AssertFatal(epoll_ctl(t->epoll_fd, EPOLL_CTL_DEL, fd, NULL) == 0,
                  "epoll_ctl (EPOLL_CTL_DEL) failed for task %s, fd %d: %s!\n",
                  t->admin.name, fd, strerror(errno));
  }

  void itti_subscribe_event_fd(task_id_t task_id, int fd) {
    struct epoll_event event;
    task_list_t *t=&tasks[task_id];
    t->nb_fd_epoll++;
    t->events = (struct epoll_event *)realloc((void *)t->events,
                t->nb_fd_epoll * sizeof(struct epoll_event));

    //EPOLL_CTL_ADD：注册新的fd到epfd中；
    //EPOLL_CTL_MOD：修改已经注册的fd的监听事件；
    //EPOLL_CTL_DEL：从epfd中删除一个fd；

    /*
      events可以是以下几个宏的集合：
      EPOLLIN ：     表示对应的文件描述符可以读（包括对端SOCKET正常关闭）；
      EPOLLOUT：    表示对应的文件描述符可以写；
      EPOLLPRI：      表示对应的文件描述符有紧急的数据可读（这里应该表示有带外数据到来）；
      EPOLLERR：     表示对应的文件描述符发生错误；
      EPOLLHUP：     表示对应的文件描述符被挂断；
      EPOLLET：      将EPOLL设为边缘触发(Edge Triggered)模式，这是相对于水平触发(Level Triggered)来说的。
      EPOLLONESHOT： 只监听一次事件，当监听完这次事件之后，如果还需要继续监听这个socket的话，
                    需要再次把这个socket加入到EPOLL队列里
    */

    event.events  = EPOLLIN | EPOLLERR;
    event.data.u64 = 0;
    event.data.fd  = fd;
    AssertFatal(epoll_ctl(t->epoll_fd, EPOLL_CTL_ADD, fd, &event) == 0,
                "epoll_ctl (EPOLL_CTL_ADD) failed for task %s, fd %d: %s!\n",
                t->admin.name, fd, strerror(errno));
  }

  int itti_init(task_id_t task_max, thread_id_t thread_max, MessagesIds messages_id_max, const task_info_t *tasks_info,
              const message_info_t *messages_info)
  {
    AssertFatal(task_max <= TASK_MAX, "task_max error , task_max %d", task_max);

    for(int i=0; i<task_max; i++) {
      printf("Starting itti queue: %s as task %d\n", tasks_info[i].name, i);
      pthread_mutex_init(&tasks[i].queue_cond_lock, NULL);
      tasks[i].nb_fd_epoll = 0;
      tasks[i].events = NULL;
      tasks[i].epoll_fd = -1;
      tasks[i].sem_fd = -1;
      
      memcpy(&tasks[i].admin, &tasks_info[i], sizeof(task_info_t));

      //
      AssertFatal( ( tasks[i].epoll_fd = epoll_create1(0) ) >=0, "");
      AssertFatal( ( tasks[i].sem_fd = eventfd(0, EFD_SEMAPHORE) ) >=0, "");

      itti_subscribe_event_fd((task_id_t)i, tasks[i].sem_fd);

      if(tasks[i].admin.threadFunc != NULL)
        itti_create_task((task_id_t)i, tasks[i].admin.threadFunc, NULL);
    }
    return 0;
  }

  void *itti_malloc(task_id_t       origin_task_id, task_id_t destinationTaskId, ssize_t size)
  {
    void *ptr = NULL;
    ptr = malloc(size);
    AssertFatal(ptr!=NULL, "Memory allocation of %zu bytes failed (%d -> %d)!\n", size, origin_task_id, destinationTaskId);
    return ptr;
  }

  int itti_free(task_id_t task_id, void *ptr)
  {
    if(ptr != NULL)
      free(ptr);
    return 0;
  }
  
  MessageDef *itti_alloc_new_message(
    task_id_t         origin_task_id,
    MessagesIds       message_id)
  {
    int size = sizeof(MessageHeader) + messages_info[message_id].size;
    MessageDef *tmp = (MessageDef *) itti_malloc(origin_task_id, TASK_UNKNOWN, size);

    tmp->ittiMsgHeader.messageId = message_id;
    tmp->ittiMsgHeader.originTaskId = origin_task_id;
    tmp->ittiMsgHeader.ittiMsgSize = size;

    return tmp;
  }

}
