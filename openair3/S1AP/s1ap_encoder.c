/*
  Author: zhenghuangkun
*/

#include <stdint.h>

#include "s1ap_encoder.h"


int s1ap_encode_pdu(S1AP_S1AP_PDU_t *pdu, uint8_t **buffer, uint32_t *len)
{
  asn_encode_to_new_buffer_result_t res = { NULL, {0, NULL, NULL} };
  //ssize_t encoded;
  
  xer_fprint(stdout, &asn_DEF_S1AP_S1AP_PDU, (void *)pdu);
  
  memset(&res, 0, sizeof(res));
  res = asn_encode_to_new_buffer(NULL, ATS_ALIGNED_CANONICAL_PER, &asn_DEF_S1AP_S1AP_PDU, pdu);
  //encoded = aper_encode_to_new_buffer(&asn_DEF_S1AP_S1AP_PDU, NULL, pdu, (void **)buffer);
  if(res.result.encoded < 0)
  {
    return -1;
  }

  *buffer = res.buffer;
  *len    = res.result.encoded;

  //*len = encoded;
   
  ASN_STRUCT_FREE_CONTENTS_ONLY(asn_DEF_S1AP_S1AP_PDU, pdu);

  return 0;
}



