/*
  Author: zhenghuangkun
*/

#include <stdio.h>
#include <unistd.h>
#include <sys/eventfd.h>
#include "intertask_interface.h"
#include "l3_task.h"
#include "log.h"
#include "common_defs.h"


void l3_init(void)
{
  LOG_I(L3, "l3 task Starting....\n");
}

void l3_process_itti_msg(void *notUsed)
{
  MessageDef *msg_p;
  itti_receive_msg(TASK_L3, &msg_p);

  switch(ITTI_MSG_ID(msg_p)) {
    case RRC_MAC_IN_SYNC_IND:
      LOG_I(L3, "Received message %s form task %s\n", ITTI_MSG_NAME(msg_p), ITTI_MSG_ORIGIN_NAME(msg_p));
      //LOG_I(L3, "frame %u\n", RRC_MAC_IN_SYNC_IND(msg_p).frame);
      //LOG_I(L3, "sub_frame %u\n", RRC_MAC_IN_SYNC_IND(msg_p).sub_frame);
      //LOG_I(L3, "enb_index %u\n", RRC_MAC_IN_SYNC_IND(msg_p).enb_index);
      break;
    default:
      break;
  }

  itti_free(ITTI_MSG_ORIGIN_ID(msg_p), msg_p);
}

void *l3_task(void *arg)
{
  l3_init();
  wait_sync("l3_task");
  while(1) {
    l3_process_itti_msg(NULL);
  }
  return NULL;
}



