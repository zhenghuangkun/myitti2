/*
  Author: Zhenghuangkun
*/
#ifndef INTERTASK_INTERFACE_H_
#define INTERTASK_INTERFACE_H_
#include <stdint.h>
#include <sys/epoll.h>

#include <assertions.h>
#include <common.h>
#include <l1_messages_types.h>
#include <sctp_messages_types.h>

typedef uint32_t MessageHeaderSize;

typedef enum {
  TASK_PRIORITY_MAX       = 100,
  TASK_PRIORITY_MAX_LEAST = 85,
  TASK_PRIORITY_MED_PLUS  = 70,
  TASK_PRIORITY_MED       = 55,
  TASK_PRIORITY_MED_LEAST = 40,
  TASK_PRIORITY_MIN_PLUS  = 25,
  TASK_PRIORITY_MIN       = 10,
}task_priority_t;

typedef struct {
  task_priority_t priority;
  unsigned int queue_size;
  /* Printable name */
  char name[256];
  void *(*func)(void *) ;
  void *(*threadFunc)(void *) ;
} task_info_t;


#define TASK_ENUM(TaskID, pRIO, qUEUEsIZE, FuNc, ThreadFunc) TaskID,

#define FOREACH_TASK(TASK_DEF) \
  TASK_DEF(TASK_UNKNOWN,  TASK_PRIORITY_MED, 50, NULL, NULL) \
  TASK_DEF(TASK_L1,       TASK_PRIORITY_MED, 50, NULL, NULL) \
  TASK_DEF(TASK_L2,       TASK_PRIORITY_MED, 50, NULL, NULL) \
  TASK_DEF(TASK_L3,       TASK_PRIORITY_MED, 50, NULL, NULL) \
  TASK_DEF(TASK_SCTP,     TASK_PRIORITY_MED, 50, NULL, NULL) \
  TASK_DEF(TASK_TCP,      TASK_PRIORITY_MED, 50, NULL, NULL) \
  TASK_DEF(TASK_UDP,      TASK_PRIORITY_MED, 50, NULL, NULL) \
  TASK_DEF(TASK_S1AP,     TASK_PRIORITY_MED, 50, NULL, NULL) \
  TASK_DEF(TASK_NGAP,     TASK_PRIORITY_MED, 50, NULL, NULL) \
  TASK_DEF(TASK_MAX,      TASK_PRIORITY_MED, 50, NULL, NULL) 

#define TASK_DEF(TaskID, pRIO, qUEUEsIZE, FuNc, ThreadFunc)          { pRIO, qUEUEsIZE, #TaskID, FuNc, ThreadFunc },

typedef enum{
  FOREACH_TASK(TASK_ENUM)
}task_id_t;
  

/* Map task id to printable name. */
static const task_info_t tasks_info[] = {
  FOREACH_TASK(TASK_DEF)
};

typedef task_id_t thread_id_t;

typedef enum message_priorities_e {
  MESSAGE_PRIORITY_MAX       = 100,
  MESSAGE_PRIORITY_MAX_LEAST = 85,
  MESSAGE_PRIORITY_MED_PLUS  = 70,
  MESSAGE_PRIORITY_MED       = 55,
  MESSAGE_PRIORITY_MED_LEAST = 40,
  MESSAGE_PRIORITY_MIN_PLUS  = 25,
  MESSAGE_PRIORITY_MIN       = 10,
} message_priorities_t;


//MESSAGE_DEF(RRC_MAC_IN_SYNC_IND,        MESSAGE_PRIORITY_MED_PLUS, RrcMacInSyncInd,             rrc_mac_in_sync_ind)

/* This enum defines messages ids. Each one is unique. */
typedef enum {
#define MESSAGE_DEF(iD, pRIO, sTRUCT, fIELDnAME) iD,
#include <all_msg.h>
#undef MESSAGE_DEF
  MESSAGES_ID_MAX,
} MessagesIds;


typedef struct {
  MessagesIds messageId;          /**< Unique message id as referenced in enum MessagesIds */
  task_id_t  originTaskId;        /**< ID of the sender task */
  task_id_t  destinationTaskId;   /**< ID of the destination task */
  uint16_t instance;            /**< Task instance for virtualization */
  MessageHeaderSize ittiMsgSize;         /**< Message size (not including header size) */
}MessageHeader;

typedef struct message_info_s {
  int id;
  message_priorities_t priority;
  /* Message payload size */
  MessageHeaderSize size;
  /* Printable name */
  const char name[256];
} message_info_t;

/* Map message id to message information */
static const message_info_t messages_info[] = {
#define MESSAGE_DEF(iD, pRIO, sTRUCT, fIELDnAME) { iD, pRIO, sizeof(sTRUCT), #iD },
#include <all_msg.h>
#undef MESSAGE_DEF
};

typedef union msg_s {
#define MESSAGE_DEF(iD, pRIO, sTRUCT, fIELDnAME) sTRUCT fIELDnAME;
#include <all_msg.h>
#undef MESSAGE_DEF
} msg_t;


typedef struct{
  MessageHeader ittiMsgHeader; /**< Message header */
  msg_t         ittiMsg;
}MessageDef;

#define ITTI_MSG_ID(mSGpTR)                 ((mSGpTR)->ittiMsgHeader.messageId)
#define ITTI_MSG_ORIGIN_ID(mSGpTR)          ((mSGpTR)->ittiMsgHeader.originTaskId)
#define ITTI_MSG_DESTINATION_ID(mSGpTR)     ((mSGpTR)->ittiMsgHeader.destinationTaskId)


#define ITTI_MSG_NAME(mSGpTR)               itti_get_message_name(ITTI_MSG_ID(mSGpTR))
#define ITTI_MSG_ORIGIN_NAME(mSGpTR)        itti_get_task_name(ITTI_MSG_ORIGIN_ID(mSGpTR))
#define ITTI_MSG_DESTINATION_NAME(mSGpTR)   itti_get_task_name(ITTI_MSG_DESTINATION_ID(mSGpTR))


#ifdef __cplusplus
extern "C" {
#endif

#define  THREAD_MAX 0 //for compatibility

const char *itti_get_message_name(MessagesIds message_id);

const char *itti_get_task_name(task_id_t task_id);

int itti_send_msg_to_task(task_id_t task_id, uint16_t instance, MessageDef *message);

int itti_get_events_locked(task_id_t task_id, struct epoll_event **events);

void itti_receive_msg(task_id_t task_id, MessageDef **received_msg);

int itti_create_task(task_id_t task_id,
                     void *(*start_routine) (void *),
                     void *args_p);

MessageDef *itti_alloc_new_message(
  task_id_t         origin_task_id,
  MessagesIds       message_id);


int itti_free(task_id_t task_id, void *ptr);

void itti_unsubscribe_event_fd(task_id_t task_id, int fd);

void itti_subscribe_event_fd(task_id_t task_id, int fd);

int itti_init(task_id_t task_max, thread_id_t thread_max, MessagesIds messages_id_max, const task_info_t *tasks_info,
              const message_info_t *messages_info);

void *itti_malloc(task_id_t       origin_task_id, task_id_t destinationTaskId, ssize_t size);

MessageDef *itti_alloc_new_message(task_id_t origin_task_id, MessagesIds            message_id);


#ifdef __cplusplus
}
#endif


#endif /* INTERTASK_INTERFACE_H_ */
