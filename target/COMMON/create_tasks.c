/*
  Author: zhenghuangkun
*/

#include <stdio.h>
#include <unistd.h>
#include <sys/eventfd.h>
#include "intertask_interface.h"
#include "create_tasks.h"
#include "l1_task.h"
#include "l2_task.h"
#include "l3_task.h"
#include "sctp_task.h"
#include "tcp_task.h"
#include "udp_task.h"
#include "s1ap_task.h"
#include "ngap_task.h"





int create_tasks(uint32_t instance)
{
  
  itti_create_task(TASK_L1, l1_task, NULL);
  itti_create_task(TASK_L2, l2_task, NULL);
  itti_create_task(TASK_L3, l3_task, NULL);
  itti_create_task(TASK_SCTP, sctp_task, NULL);
  itti_create_task(TASK_TCP, tcp_task, NULL);
  itti_create_task(TASK_UDP, udp_task, NULL);
  itti_create_task(TASK_S1AP, s1ap_task, NULL);
  itti_create_task(TASK_NGAP, ngap_task, NULL);

  return 0;
}

