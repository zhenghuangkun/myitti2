/*
  Author: zhenghuangkun
*/

#ifndef SCTP_DEFS_H_
#define SCTP_DEFS_H_
#include <intertask_interface.h>

typedef enum{
  SCTP_TYPE_CLIENT,
  SCTP_TYPE_SERVER,
  SCTP_TYPE_MAX
}sctp_connect_types_e;

typedef struct sctp_cnx_elm_s{
  sctp_connect_types_e  connect_type;
  int                   sd;
  uint16_t              local_port;
  uint16_t              in_streams;
  uint16_t              out_streams;
  uint16_t              ppid;
  int32_t               assoc_id;
  task_id_t             task_id;
  struct   sockaddr     *peer_addresses; //target address
  int                   nb_peer_addresses;
}sctp_cnx_elm_t;


typedef struct sctp_cnx_list_t{
  struct sctp_cnx_list_t *left;
  struct sctp_cnx_list_t *right;
  sctp_cnx_elm_t  *elm;
}sctp_cnx_list_t;


typedef struct sctp_info_s{
  sctp_cnx_list_t       entry;

  int32_t               server_sd;
  uint16_t              local_port;
  task_id_t             task_id;
  uint16_t              in_streams;
  uint16_t              out_streams;
  uint16_t              ppid;
  int                   sctp_nb_cnx;
}sctp_info_t;

#endif
