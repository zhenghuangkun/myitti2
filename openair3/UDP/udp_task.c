/*
  Author: zhenghuangkun
*/

#include <stdio.h>
#include <unistd.h>
#include <sys/eventfd.h>
#include "intertask_interface.h"
#include "udp_task.h"
#include "log.h"
#include "common_defs.h"


void udp_init(void)
{
  LOG_I(UDP, "UDP task Starting....\n");
}

void udp_process_itti_msg(void *notUsed)
{
  MessageDef *msg_p;
  itti_receive_msg(TASK_UDP, &msg_p);

  switch(ITTI_MSG_ID(msg_p)) {

    default:
      break;
  }

  itti_free(ITTI_MSG_ORIGIN_ID(msg_p), msg_p);
}

void *udp_task(void *arg)
{
  udp_init();
  wait_sync("udp_task");
  while(1) {
    udp_process_itti_msg(NULL);
  }
  return NULL;
}



