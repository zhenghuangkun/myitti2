/*
  Author: zhenghuangkun
*/

#include <stdio.h>
#include <unistd.h>
#include <sys/eventfd.h>
#include <string.h>
#include "intertask_interface.h"
#include "l1_task.h"
#include "log.h"
#include "common_defs.h"

void l1_init(void)
{
  LOG_I(L1, "l1 task Starting....\n");
}

void l1_process_itti_msg(void *notUsed)
{
  MessageDef *msg_p;
  itti_receive_msg(TASK_L1, &msg_p);

  switch(ITTI_MSG_ID(msg_p)) {
    case RRC_MAC_IN_SYNC_IND:
      LOG_I(L1, "Received message %s form task %s\n", ITTI_MSG_NAME(msg_p), ITTI_MSG_ORIGIN_NAME(msg_p));
      //LOG_I(L1, "frame %u\n", RRC_MAC_IN_SYNC_IND(msg_p).frame);
      //LOG_I(L1, "sub_frame %u\n", RRC_MAC_IN_SYNC_IND(msg_p).sub_frame);
      //LOG_I(L1, "enb_index %u\n", RRC_MAC_IN_SYNC_IND(msg_p).enb_index);
      break;
    case SCTP_CLIENT_SETUP_RESP:
      LOG_I(L1, "Received message %s form task %s\n", ITTI_MSG_NAME(msg_p), ITTI_MSG_ORIGIN_NAME(msg_p));
      
      LOG_I(L1, "assoc_id %u\n", SCTP_CLIENT_SETUP_RESP(msg_p).assoc_id);
      sleep(1);
      
      MessageDef *msg = itti_alloc_new_message(TASK_L1, SCTP_DATA_SEND_REQ);
      SCTP_DATA_SEND_REQ(msg).in_stream   = SCTP_CLIENT_SETUP_RESP(msg_p).in_stream;
      SCTP_DATA_SEND_REQ(msg).out_stream  = SCTP_CLIENT_SETUP_RESP(msg_p).out_stream;
      SCTP_DATA_SEND_REQ(msg).assoc_id    = SCTP_CLIENT_SETUP_RESP(msg_p).assoc_id;
      SCTP_DATA_SEND_REQ(msg).buffer      = malloc(sizeof(uint8_t) * 50);
      strcpy((char*)SCTP_DATA_SEND_REQ(msg).buffer, "hello Im client");
      SCTP_DATA_SEND_REQ(msg).length      = 50;
      itti_send_msg_to_task(TASK_SCTP, 0, msg);

      LOG_I(L1, "send a message to SCTP task\n");
    default:
      break;
  }

  itti_free(ITTI_MSG_ORIGIN_ID(msg_p), msg_p);
}

void *l1_task(void *arg)
{
  int i=0;
  l1_init();
  wait_sync("l1_task");
  while(1) {
    l1_process_itti_msg(NULL);
    i++;
  }
  return NULL;
}

