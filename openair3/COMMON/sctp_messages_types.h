
/*
 * sctp_messages_types.h
 *
 *  Created on: Dec 31, 2019
 *      Author: zhenghuangkun
 */

#ifndef SCTP_MESSAGES_TYPES_H_
#define SCTP_MESSAGES_TYPES_H_

//-------------------------------------------------------------------------------------------//
// Defines to access message fields.
#define SCTP_SERVER_SETUP_REQ(mSGpTR)             (mSGpTR)->ittiMsg.sctp_server_setup_req
#define SCTP_CLIENT_SETUP_REQ(mSGpTR)             (mSGpTR)->ittiMsg.sctp_client_setup_req
#define SCTP_CLIENT_SETUP_RESP(mSGpTR)            (mSGpTR)->ittiMsg.sctpClientSetupResp
#define SCTP_DATA_SEND_REQ(mSGpTR)                (mSGpTR)->ittiMsg.sctpDataSendReq
#define SCTP_DATA_IND_REQ(mSGpTR)                 (mSGpTR)->ittiMsg.sctpDataIndReq

//-------------------------------------------------------------------------------------------//
typedef struct SctpServerSetupReq_s {
  uint32_t        port;
  uint8_t         ipv4v6_flag;  // 0 ipv4 1 ipv6
  char            ipbuf[32];
  uint32_t        in_streams;
  uint32_t        out_streams;
} SctpServerSetupReq_t;

typedef struct SctpClientSetupReq_info_s {
  uint32_t        port;
  uint8_t         ipv4v6_flag;  // 0 ipv4 1 ipv6
  char            ipbuf[32];
  uint32_t        in_streams;
  uint32_t        out_streams;
} SctpClientSetupReq_info_t;

//-------------------------------------------------------------------------------------------//
typedef struct SctpClientSetupReq_s {
  uint32_t        nb_locals;
  SctpClientSetupReq_info_t local_info[32];

  uint32_t        nb_servers;
  SctpClientSetupReq_info_t servers_info[32];
} SctpClientSetupReq_t;

typedef struct SctpClientSetupResp_s {
  uint32_t  in_stream;
  uint32_t  out_stream;
  uint32_t  assoc_id;
  uint32_t  sctp_state;
}SctpClientSetupResp_t;


typedef struct SctpDataSendReq_s {
  uint32_t  in_stream;
  uint32_t  out_stream;
  uint32_t  assoc_id;
  uint8_t   *buffer;
  uint32_t  length;
}SctpDataSendReq_t;

typedef struct SctpDataIndReq_s {
  uint32_t  in_stream;
  uint32_t  out_stream;
  uint32_t  assoc_id;
  uint8_t   *buffer;
  uint32_t  length;
}SctpDataIndReq_t;


#endif /* L1_MESSAGES_TYPES_H_ */
