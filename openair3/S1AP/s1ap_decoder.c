/*
  Author: zhenghuangkun
*/

#include <stdint.h>

#include "s1ap_decoder.h"
#include "log.h"


int s1ap_decode_pdu(S1AP_S1AP_PDU_t *pdu, uint8_t *buffer, uint32_t len)
{
  asn_dec_rval_t dec_ret;

  printf("len = %d\n", len);
  for(int i = 1; i<=len; i++)
  {
    printf("%02x ", buffer[i-1]);
    if(i%8 == 0) printf("\n");
  }
  printf("\n");

  dec_ret = aper_decode(NULL, 
    &asn_DEF_S1AP_S1AP_PDU, 
    (void **) &pdu, 
    buffer, 
    len, 
    0, 
    0);

  
  if (dec_ret.code != RC_OK) {
    LOG_E(S1AP, "Failed to decode pdu\n");
    return -1;
  }

  xer_fprint(stdout, &asn_DEF_S1AP_S1AP_PDU, (void *)pdu);

  return 0;
}

