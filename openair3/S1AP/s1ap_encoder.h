/*
  Author: zhenghuangkun
*/





#ifndef S1AP_ENCODER_H
#define S1AP_ENCODER_H

#include "s1ap_common.h"

int s1ap_encode_pdu(S1AP_S1AP_PDU_t *pdu, uint8_t **buffer, uint32_t *len);


#endif