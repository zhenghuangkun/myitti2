/*
  Author: zhenghuangkun
*/

#include <stdint.h>

#include "s1ap_itti_message.h"
#include "intertask_interface.h"
#include "log.h"
#include "common_defs.h"

static uint32_t in_stream;
static uint32_t out_stream;
static uint32_t assoc_id;

void s1ap_itti_set_sctp_connect_info(uint32_t in_stream1, uint32_t out_stream1, uint32_t assoc_id1)
{
  in_stream = in_stream1;
  out_stream = out_stream1;
  assoc_id = assoc_id1;
}

void s1ap_itti_send_sctp_data_req(uint8_t *buffer, uint32_t length)
{

  MessageDef *msg = itti_alloc_new_message(TASK_S1AP, SCTP_DATA_SEND_REQ);
  SCTP_DATA_SEND_REQ(msg).in_stream   = in_stream;
  SCTP_DATA_SEND_REQ(msg).out_stream  = out_stream;
  SCTP_DATA_SEND_REQ(msg).assoc_id    = assoc_id;
  SCTP_DATA_SEND_REQ(msg).buffer      = buffer;
  SCTP_DATA_SEND_REQ(msg).length      = length;
  itti_send_msg_to_task(TASK_SCTP, 0, msg);

}


