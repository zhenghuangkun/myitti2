/*
  Author: zhenghuangkun
*/

#ifndef SCTP_COMMON_H_
#define SCTP_COMMON_H_


void sctp_list_init(sctp_info_t *info);
void sctp_elms_insert(sctp_info_t *info, sctp_cnx_elm_t *elms);
sctp_cnx_elm_t *sctp_elms_get_by_associd(sctp_info_t *info, int32_t assoc_id);
sctp_cnx_elm_t *sctp_elms_get_by_sd(sctp_info_t *info, int32_t sd);
void sctp_elms_remove(sctp_info_t *info, int32_t assoc_id);
void sctp_elms_dumps(sctp_info_t *info);

int sctp_set_init_opt(int sd, uint16_t instreams, uint16_t outstreams,
                      uint16_t max_attempts, uint16_t init_timeout);

#endif
