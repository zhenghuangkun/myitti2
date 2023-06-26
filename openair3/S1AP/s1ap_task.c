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
#include "s1ap_task.h"
#include "s1ap_itti_message.h"
#include "s1ap_generate_message.h"
#include "s1ap_decoder.h"
#include "S1AP_S1AP-PDU.h"


void s1ap_process_itti_msg(void *notUsed)
{
  MessageDef *msg_p;
  itti_receive_msg(TASK_S1AP, &msg_p);

  switch(ITTI_MSG_ID(msg_p)) {

    case SCTP_CLIENT_SETUP_RESP:
      LOG_I(S1AP, "Received message %s form task %s\n", ITTI_MSG_NAME(msg_p), ITTI_MSG_ORIGIN_NAME(msg_p));
      
      LOG_I(S1AP, "assoc_id %u\n", SCTP_CLIENT_SETUP_RESP(msg_p).assoc_id);
      sleep(1);
      
      s1ap_itti_set_sctp_connect_info(SCTP_CLIENT_SETUP_RESP(msg_p).in_stream, SCTP_CLIENT_SETUP_RESP(msg_p).out_stream, SCTP_CLIENT_SETUP_RESP(msg_p).assoc_id);

      s1ap_generate_S1SetupRequest_message();
      LOG_I(S1AP, "send a message to SCTP task\n");
    break;

    case SCTP_DATA_IND_REQ:
    {
      S1AP_S1AP_PDU_t pdu;
      memset(&pdu, 0, sizeof(pdu));
      s1ap_decode_pdu(&pdu, SCTP_DATA_IND_REQ(msg_p).buffer, SCTP_DATA_IND_REQ(msg_p).length);
      if(SCTP_DATA_IND_REQ(msg_p).buffer)
      {
        free(SCTP_DATA_IND_REQ(msg_p).buffer);
      }

      //if(pdu)
      //{ 
      //  ASN_STRUCT_FREE_CONTENTS_ONLY(asn_DEF_S1AP_S1AP_PDU, pdu);
      //}
    }
    break;
    default:
      LOG_I(S1AP, "Received unknow message %s form task %s\n", ITTI_MSG_NAME(msg_p), ITTI_MSG_ORIGIN_NAME(msg_p));
    break;
 }

  itti_free(ITTI_MSG_ORIGIN_ID(msg_p), msg_p);
}

void *s1ap_task(void *arg)
{
  wait_sync("s1ap_task");
  while(1) {
    s1ap_process_itti_msg(NULL);
  }
  return NULL;
}



