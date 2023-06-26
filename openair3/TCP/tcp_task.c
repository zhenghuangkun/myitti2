/*
  Author: zhenghuangkun
*/

#include <stdio.h>
#include <unistd.h>
#include <sys/eventfd.h>
#include "intertask_interface.h"
#include "tcp_task.h"
#include "log.h"
#include "common_defs.h"


void tcp_init(void)
{
  LOG_I(TCP, "tcp task Starting....\n");
}

void tcp_process_itti_msg(void *notUsed)
{
  MessageDef *msg_p;
  itti_receive_msg(TASK_TCP, &msg_p);

  switch(ITTI_MSG_ID(msg_p)) {

    default:
      break;
  }

  itti_free(ITTI_MSG_ORIGIN_ID(msg_p), msg_p);
}

void *tcp_task(void *arg)
{
  tcp_init();
  wait_sync("tcp_task");
  while(1) {
    tcp_process_itti_msg(NULL);
  }
  return NULL;
}



