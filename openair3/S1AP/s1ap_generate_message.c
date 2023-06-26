/*
  Author: zhenghuangkun
*/

#include <stdint.h>

#include "s1ap_generate_message.h"
#include "s1ap_itti_message.h"
#include "s1ap_encoder.h"
#include "s1ap_decoder.h"

#include "s1ap_common.h"
#include "S1AP_SupportedTAs-Item.h"



void s1ap_generate_S1SetupRequest_message(void)
{
  S1AP_S1AP_PDU_t           pdu;
  S1AP_S1SetupRequest_t     *out;
  S1AP_S1SetupRequestIEs_t  *ie;
  uint32_t                  len = 0;
  uint8_t                   *buffer;

  memset(&pdu, 0, sizeof(pdu));

  pdu.present = S1AP_S1AP_PDU_PR_initiatingMessage;
  pdu.choice.initiatingMessage.procedureCode = S1AP_ProcedureCode_id_S1Setup;
  pdu.choice.initiatingMessage.criticality   = S1AP_Criticality_reject;
  pdu.choice.initiatingMessage.value.present = S1AP_InitiatingMessage__value_PR_S1SetupRequest;
  out = &pdu.choice.initiatingMessage.value.choice.S1SetupRequest;

  /* mandatory */
  // Global-ENB-ID
  ie = calloc(1, sizeof(S1AP_S1SetupRequestIEs_t));
  ie->id = S1AP_ProtocolIE_ID_id_Global_ENB_ID;
  ie->criticality = S1AP_Criticality_reject;
  ie->value.present = S1AP_S1SetupRequestIEs__value_PR_Global_ENB_ID;
  ie->value.choice.Global_ENB_ID.pLMNidentity.buf = calloc(3, sizeof(uint8_t));
  //ie->value.choice.Global_ENB_ID.pLMNidentity.buf[0] = 208; //mcc
  //ie->value.choice.Global_ENB_ID.pLMNidentity.buf[1] = 1;   //mnc
  //ie->value.choice.Global_ENB_ID.pLMNidentity.buf[2] = 2;   //mnc length
  ie->value.choice.Global_ENB_ID.pLMNidentity.buf[0] = 0; //mcc
  ie->value.choice.Global_ENB_ID.pLMNidentity.buf[1] = 0xF1;   //mnc
  ie->value.choice.Global_ENB_ID.pLMNidentity.buf[2] = 0x10;   //mnc length
  ie->value.choice.Global_ENB_ID.pLMNidentity.size = 3;

  // macroENB_ID length = 20
  ie->value.choice.Global_ENB_ID.eNB_ID.present = S1AP_ENB_ID_PR_macroENB_ID;
  ie->value.choice.Global_ENB_ID.eNB_ID.choice.macroENB_ID.buf = calloc(3, sizeof(uint8_t));
  uint32_t enbid = 0x10ea1;
  ie->value.choice.Global_ENB_ID.eNB_ID.choice.macroENB_ID.buf[0] = enbid >> 12;
  ie->value.choice.Global_ENB_ID.eNB_ID.choice.macroENB_ID.buf[1] = enbid >> 4;
  ie->value.choice.Global_ENB_ID.eNB_ID.choice.macroENB_ID.buf[2] = (enbid & 0x0F) << 4;
  ie->value.choice.Global_ENB_ID.eNB_ID.choice.macroENB_ID.size = 3;
  ie->value.choice.Global_ENB_ID.eNB_ID.choice.macroENB_ID.bits_unused = 4;

  ASN_SEQUENCE_ADD(&out->protocolIEs.list, ie);

  /* optional */
  // ENBname
  ie = calloc(1, sizeof(S1AP_S1SetupRequestIEs_t));
  ie->id = S1AP_ProtocolIE_ID_id_eNBname;
  ie->criticality = S1AP_Criticality_ignore;
  ie->value.present = S1AP_S1SetupRequestIEs__value_PR_ENBname;
  OCTET_STRING_fromBuf(&ie->value.choice.ENBname, "eNB-Eurecom-LTE", strlen("eNB-Eurecom-LTE"));
  ASN_SEQUENCE_ADD(&out->protocolIEs.list, ie);

  /* mandatory */
  // SupportedTAs
  ie = calloc(1, sizeof(S1AP_S1SetupRequestIEs_t));
  ie->id = S1AP_ProtocolIE_ID_id_SupportedTAs;
  ie->criticality = S1AP_Criticality_reject;
  ie->value.present = S1AP_S1SetupRequestIEs__value_PR_SupportedTAs;
  {
    S1AP_SupportedTAs_Item_t *ta = calloc(1, sizeof(S1AP_SupportedTAs_Item_t));
    // TAC ::= OCTET STRING (SIZE (2))
    // tac = 1
    uint16_t tac = 1;
    ta->tAC.buf = calloc(2, sizeof(uint8_t));
    ta->tAC.buf[0] = tac >> 8;
    ta->tAC.buf[1] = tac & 0xFF;
    ta->tAC.size = 2;

    for(int i=0; i<1; i++)
    {
      S1AP_PLMNidentity_t *plmn = calloc(1, sizeof(S1AP_PLMNidentity_t));
      
      plmn->buf = calloc(3, sizeof(uint8_t));
      plmn->buf[0] = 0; //mcc
      plmn->buf[1] = 0xF1;   //mnc
      plmn->buf[2] = 0x10;   //mnc length
      plmn->size = 3;

      ASN_SEQUENCE_ADD(&ta->broadcastPLMNs.list, plmn);
    }

    ASN_SEQUENCE_ADD(&ie->value.choice.SupportedTAs.list, ta);
  }
  ASN_SEQUENCE_ADD(&out->protocolIEs.list, ie);


  /* mandatory */
  // PagingDRX
  ie = calloc(1, sizeof(S1AP_S1SetupRequestIEs_t));
  ie->id = S1AP_ProtocolIE_ID_id_DefaultPagingDRX;
  ie->criticality = S1AP_Criticality_ignore;
  ie->value.present = S1AP_S1SetupRequestIEs__value_PR_PagingDRX;
  ie->value.choice.PagingDRX = S1AP_PagingDRX_v128;
  ASN_SEQUENCE_ADD(&out->protocolIEs.list, ie);

  
  s1ap_encode_pdu(&pdu, &buffer, &len);

  printf("len = %d\n", len);
  for(int i = 1; i<=len; i++)
  {
    printf("%02x ", buffer[i-1]);
    if(i%8 == 0) printf("\n");
  }
  printf("\n");
  s1ap_itti_send_sctp_data_req(buffer, len);

  S1AP_S1AP_PDU_t           newpdu;
  memset(&newpdu, 0, sizeof(newpdu));
  s1ap_decode_pdu(&newpdu, buffer, len);
}


