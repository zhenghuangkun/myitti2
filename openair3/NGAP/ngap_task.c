/*
  Author: zhenghuangkun
*/

#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <errno.h>
#include <sys/eventfd.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <string.h>
#include <sys/types.h>          /* See NOTES */
#include <sys/socket.h>
#include <netinet/in.h>  
#include <netinet/sctp.h> 
#include <ifaddrs.h>

#include "intertask_interface.h"
#include "log.h"
#include "common_defs.h"
#include "ngap_task.h"

void ngap_process_itti_msg(void *notUsed)
{
	return;
}

void *ngap_task(void *arg)
{
  wait_sync("ngap_task");
  while(1) {
    ngap_process_itti_msg(NULL);
  }
  return NULL;
}



