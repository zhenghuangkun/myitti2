/*
  Author: zhenghuangkun
*/





#ifndef S1AP_ITTI_MESSAGE_H
#define S1AP_ITTI_MESSAGE_H

void s1ap_itti_set_sctp_connect_info(uint32_t in_stream1, uint32_t out_stream1, uint32_t assoc_id1);
void s1ap_itti_send_sctp_data_req(uint8_t *buffer, uint32_t length);


#endif