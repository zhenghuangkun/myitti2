/*
 * sctp_messages_def.h
 *
 *  Created on: Dec 31, 2019
 *      Author: zhenghuangkun
 */

//-------------------------------------------------------------------------------------------//
// Messages between L1 and L3 layers
MESSAGE_DEF(SCTP_SERVER_SETUP_REQ,        MESSAGE_PRIORITY_MED_PLUS, SctpServerSetupReq_t,             sctp_server_setup_req)
MESSAGE_DEF(SCTP_CLIENT_SETUP_REQ,        MESSAGE_PRIORITY_MED_PLUS, SctpClientSetupReq_t,             sctp_client_setup_req)
MESSAGE_DEF(SCTP_CLIENT_SETUP_RESP,       MESSAGE_PRIORITY_MED_PLUS, SctpClientSetupResp_t,            sctpClientSetupResp)
MESSAGE_DEF(SCTP_DATA_SEND_REQ,           MESSAGE_PRIORITY_MED_PLUS, SctpDataSendReq_t,                sctpDataSendReq)
MESSAGE_DEF(SCTP_DATA_IND_REQ,            MESSAGE_PRIORITY_MED_PLUS, SctpDataIndReq_t,                 sctpDataIndReq)

