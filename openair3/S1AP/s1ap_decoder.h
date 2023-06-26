/*
  Author: zhenghuangkun
*/





#ifndef S1AP_DECODER_H
#define S1AP_DECODER_H


#include "s1ap_common.h"


int s1ap_decode_pdu(S1AP_S1AP_PDU_t *pdu, uint8_t *buffer, uint32_t len);
#endif