/*
  Author: zhenghuangkun
*/

#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <errno.h>
#include <sys/eventfd.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <string.h>
#include <sys/types.h>          /* See NOTES */
#include <sys/socket.h>
#include <netinet/in.h>  
#include <netinet/sctp.h> 
#include <ifaddrs.h>

#include "intertask_interface.h"
#include "sctp_task.h"
#include "sctp_defs.h"
#include "sctp_common.h"
#include "log.h"
#include "common_defs.h"
#include "sctp_messages_types.h"

static sctp_info_t sctp_lists;

void sctp_get_peeraddresses(int sd, int *nb_remoteAddress, struct sockaddr **remoteAddress);


void sctp_init(void)
{
  memset(&sctp_lists, 0, sizeof(sctp_lists));
  sctp_list_init(&sctp_lists);

#if 0
  sctp_cnx_elm_t *elm = NULL;

  elm = calloc(1, sizeof(sctp_cnx_elm_t));
  elm->assoc_id = 10;
  sctp_elms_insert(&sctp_lists, elm);
  
  elm = calloc(1, sizeof(sctp_cnx_elm_t));
  elm->assoc_id = 8;
  sctp_elms_insert(&sctp_lists, elm);

  elm = calloc(1, sizeof(sctp_cnx_elm_t));
  elm->assoc_id = 3;
  sctp_elms_insert(&sctp_lists, elm);

  elm = calloc(1, sizeof(sctp_cnx_elm_t));
  elm->assoc_id = 9;
  sctp_elms_insert(&sctp_lists, elm);

  elm = calloc(1, sizeof(sctp_cnx_elm_t));
  elm->assoc_id = 2;
  sctp_elms_insert(&sctp_lists, elm);

  elm = calloc(1, sizeof(sctp_cnx_elm_t));
  elm->assoc_id = 1;
  sctp_elms_insert(&sctp_lists, elm);

  elm = calloc(1, sizeof(sctp_cnx_elm_t));
  elm->assoc_id = 5;
  sctp_elms_insert(&sctp_lists, elm);

  elm = calloc(1, sizeof(sctp_cnx_elm_t));
  elm->assoc_id = 4;
  sctp_elms_insert(&sctp_lists, elm);

  elm = calloc(1, sizeof(sctp_cnx_elm_t));
  elm->assoc_id = 7;
  sctp_elms_insert(&sctp_lists, elm);

  elm = calloc(1, sizeof(sctp_cnx_elm_t));
  elm->assoc_id = 6;
  sctp_elms_insert(&sctp_lists, elm);

  elm = calloc(1, sizeof(sctp_cnx_elm_t));
  elm->assoc_id = 15;
  sctp_elms_insert(&sctp_lists, elm);

  //sctp_elms_remove(&sctp_lists, 8);

  sctp_elms_dumps(&sctp_lists);

  sctp_cnx_elm_t *e = sctp_elms_get_by_associd(&sctp_lists, 1);
  LOG_I(SCTP, "e %p, %d\n", e, e->assoc_id);

#endif
  LOG_I(SCTP, "sctp task Starting....\n");
  
}

int global_sd;
void sctp_server_setup_req(SctpServerSetupReq_t *req, task_id_t            task_id)
{
  int sd = 0;
  int ret = 0;
  struct sockaddr               addr;
  struct sockaddr_in            *ip4_addr;
  struct sockaddr_in6           *ip6_addr;

  struct sctp_event_subscribe   evnts;
  sctp_cnx_elm_t *elm = NULL;


  /* https://www.iteye.com/blog/lobert-1770312 */

  if(!req) {
    LOG_E(SCTP, "req is NULL...\n");
    return;
  }
  
  if(req->ipv4v6_flag == 0) { //ipv4
    ip4_addr = (struct sockaddr_in *)&addr;
    ip4_addr->sin_family = AF_INET;
    ip4_addr->sin_port = htons(req->port);
    //ip4_addr->sin_addr.s_addr = inet_addr(req->ipbuf);

    //允许所有接入
    ip4_addr->sin_addr.s_addr = htonl(INADDR_ANY);
    
    sd = socket(PF_INET, SOCK_SEQPACKET, IPPROTO_SCTP);
  } else { // ipv6

    ip6_addr = (struct sockaddr_in6 *)&addr;
    ip6_addr->sin6_family = AF_INET6;
    ip6_addr->sin6_port = htons(req->port);
    inet_pton(AF_INET6, req->ipbuf, ip6_addr->sin6_addr.s6_addr);
    
    sd = socket(AF_INET6, SOCK_STREAM, IPPROTO_SCTP);
  }

  if(sd < 0) {
    LOG_E(SCTP, "socket create error. %s:%d\n", strerror(errno), errno);
    return;
  }

  // receive all event
  memset((void *)&evnts, 1, sizeof(struct sctp_event_subscribe));
  ret = setsockopt(sd, IPPROTO_SCTP, SCTP_EVENTS, &evnts, sizeof(struct sctp_event_subscribe));
  if(ret < 0) {
    close(sd);
    LOG_E(SCTP, "setsockopt error. %s:%d\n", strerror(errno), errno);
    return;
  }

  ret = sctp_bindx(sd, &addr, 1, SCTP_BINDX_ADD_ADDR);
  if(ret < 0) {
    close(sd);
    LOG_E(SCTP, "sctp_bindx error. %s:%d\n", strerror(errno), errno);
    return;
  }

  sctp_lists.server_sd = sd;
  sctp_lists.in_streams = req->in_streams;
  sctp_lists.out_streams = req->out_streams;
  sctp_lists.task_id     = task_id;

  if (sctp_set_init_opt(sd,
                        req->in_streams,
                        req->out_streams,
                        0,
                        0) < 0) {
    close(sd);
    LOG_E(SCTP, "sctp_set_init_opt error. %s:%d\n", strerror(errno), errno);
    return;
  }

  ret = listen(sd, 6);
  if(ret < 0) {
    close(sd);
    LOG_E(SCTP, "listen error. %s:%d\n", strerror(errno), errno);
    return;
  }

  elm = calloc(1, sizeof(sctp_cnx_elm_t));
  elm->sd               = sd;
  elm->connect_type     = SCTP_TYPE_SERVER;
  elm->ppid             = 27;
  elm->in_streams       = req->in_streams;
  elm->out_streams      = req->out_streams;
  elm->task_id          = task_id;
  elm->local_port       = req->port;
  sctp_elms_insert(&sctp_lists, elm);

  itti_subscribe_event_fd(TASK_SCTP, sd);
  sctp_lists.sctp_nb_cnx ++;

  global_sd = sd;
  LOG_I(SCTP, "create sctp server success %d(%d %s:%u), from task %s\n", sd, sctp_lists.sctp_nb_cnx, req->ipbuf, req->port, itti_get_task_name(task_id));
}

void sctp_client_setup_req(SctpClientSetupReq_t *req, task_id_t            task_id)
{
  int sd = 0;
  int ret = 0;
  struct sockaddr               addr;
  struct sockaddr_in            *ip4_addr;
  struct sockaddr_in6           *ip6_addr;

  struct ifaddrs               *ifaddr = NULL;
  struct ifaddrs               *ifa    = NULL;

  int                           localbindaddrs_num=0;
  struct sockaddr               localbindaddrs[10];

  struct sctp_event_subscribe   evnts;
  sctp_cnx_elm_t *elm = NULL;
  int32_t assoc_id = 0;
  int32_t ns = -1;

  /* https://www.iteye.com/blog/lobert-1770312 */

  if(!req) {
    LOG_E(SCTP, "req is NULL...\n");
    return;
  }

  // create socket
  sd = socket(PF_INET, SOCK_SEQPACKET, IPPROTO_SCTP);
  if(sd < 0) {
    LOG_E(SCTP, "socket create error. %s:%d\n", strerror(errno), errno);
    return;
  }
  
  // receive all event
  memset((void *)&evnts, 1, sizeof(struct sctp_event_subscribe));
  ret = setsockopt(sd, IPPROTO_SCTP, SCTP_EVENTS, &evnts, sizeof(struct sctp_event_subscribe));
  if(ret < 0) {
    close(sd);
    LOG_E(SCTP, "setsockopt error. %s:%d\n", strerror(errno), errno);
    return;
  }

#if 0
  struct ifaddrs {
      struct ifaddrs  *ifa_next;    /* Next item in list */
      char            *ifa_name;    /* Name of interface */
      unsigned int     ifa_flags;   /* Flags from SIOCGIFFLAGS */
      struct sockaddr *ifa_addr;    /* Address of interface */
      struct sockaddr *ifa_netmask; /* Netmask of interface */
      union {
          struct sockaddr *ifu_broadaddr;
                           /* Broadcast address of interface */
          struct sockaddr *ifu_dstaddr;
                           /* Point-to-point destination address */
      } ifa_ifu;
#define              ifa_broadaddr ifa_ifu.ifu_broadaddr
#define              ifa_dstaddr   ifa_ifu.ifu_dstaddr
      void            *ifa_data;    /* Address-specific data */
  };
#endif

  // get PC all if
  ret = getifaddrs(&ifaddr);
  if(ret < 0) {
    close(sd);
    LOG_E(SCTP, "getifaddrs error. %s:%d\n", strerror(errno), errno);
    return;
  }

  for(ifa = ifaddr; ifa!=NULL; ifa = ifa->ifa_next) {
    LOG_I(SCTP, "name %s\n", ifa->ifa_name);

    struct sockaddr_in  *ip4;
    struct sockaddr_in6 *ip6;
    char addrbuf[32];
    
    memset(addrbuf, 0, sizeof(addrbuf));

    // ipv4
    if(ifa->ifa_addr->sa_family == AF_INET) {
      ip4 = (struct sockaddr_in  *)ifa->ifa_addr;
      inet_ntop(AF_INET, &ip4->sin_addr.s_addr, addrbuf, sizeof(addrbuf));
    }
    // ipv6
    else {
      ip6 = (struct sockaddr_in6  *)ifa->ifa_addr;
      inet_ntop(AF_INET6, ip6->sin6_addr.s6_addr, addrbuf, sizeof(addrbuf));
    }

    LOG_I(SCTP, "name %s\n", ifa->ifa_name);
    LOG_I(SCTP, "INET %s\n", ifa->ifa_addr->sa_family == AF_INET? "ipv4":"ipv6");
    LOG_I(SCTP, "addr %s\n", addrbuf);

    for(int idx = 0; idx < req->nb_locals; idx++) {
      
      // ipv4
      if(req->local_info[idx].ipv4v6_flag == 0) {

        if(ifa->ifa_addr->sa_family == AF_INET) {
          ip4 = (struct sockaddr_in  *)ifa->ifa_addr;
          inet_ntop(AF_INET, &ip4->sin_addr.s_addr, addrbuf, sizeof(addrbuf));

          if(inet_addr(req->local_info[idx].ipbuf) == ip4->sin_addr.s_addr) {
            struct sockaddr_in  *localbind_ip4;
            localbind_ip4 = (struct sockaddr_in  *)&localbindaddrs[localbindaddrs_num];

            localbind_ip4->sin_family       = AF_INET;
            localbind_ip4->sin_port         = htons(req->local_info[idx].port);
            localbind_ip4->sin_addr.s_addr  = ip4->sin_addr.s_addr;

            // bind local address
            ret = sctp_bindx(sd, &localbindaddrs[localbindaddrs_num], 1, SCTP_BINDX_ADD_ADDR);
            if(ret < 0) {
              close(sd);
              LOG_E(SCTP, "sctp_bindx error idx %d %s. %s:%d\n", localbindaddrs_num, req->local_info[idx].ipbuf, strerror(errno), errno);
              return;
            }

            LOG_I(SCTP, "set sctpbindx local ipv4 addr idx %d ip %s\n", localbindaddrs_num, req->local_info[idx].ipbuf);
            localbindaddrs_num ++;

            break;
          }
        }
        else {
          continue;
        }
      }

      // ipv6
      else {
        if(ifa->ifa_addr->sa_family == AF_INET6) {
          ip6 = (struct sockaddr_in6  *)ifa->ifa_addr;
          inet_ntop(AF_INET6, ip6->sin6_addr.s6_addr, addrbuf, sizeof(addrbuf));
        
          if(strcmp(addrbuf, req->local_info[idx].ipbuf) == 0) {
            struct sockaddr_in6  *localbind_ip6;
            localbind_ip6 = (struct sockaddr_in6 *)&localbindaddrs[localbindaddrs_num];
          
            localbind_ip6->sin6_family = AF_INET6;
            localbind_ip6->sin6_port = htons(req->local_info[idx].port);
            inet_pton(AF_INET6, req->local_info[idx].ipbuf, localbind_ip6->sin6_addr.s6_addr);

            // bind local address
            ret = sctp_bindx(sd, &localbindaddrs[localbindaddrs_num], 1, SCTP_BINDX_ADD_ADDR);
            if(ret < 0) {
              close(sd);
              LOG_E(SCTP, "sctp_bindx error idx %d %s. %s:%d\n", localbindaddrs_num, req->local_info[idx].ipbuf, strerror(errno), errno);
              return;
            }

            LOG_I(SCTP, "set sctpbindx local ipv6 addr idx %d ip %s\n", localbindaddrs_num, req->local_info[idx].ipbuf);
            localbindaddrs_num ++;
            break;
          }
        }
        else {
          continue;
        }
      }
    }

    LOG_I(SCTP, "---------------------------\n");
  }


  freeifaddrs(ifaddr);
  
  /* Mark the socket as non-blocking */
  //if (fcntl(sd, F_SETFL, O_NONBLOCK) < 0) {
  //    LOG_E(SCTP, "fcntl F_SETFL O_NONBLOCK failed: %s\n", strerror(errno));
  //    close(sd);
  //    return;
  //}

  // set connect to remote server address
  for(int idx = 0; idx < req->nb_servers; idx++) {
    if(req->servers_info[idx].ipv4v6_flag == 0) { //ipv4
      ip4_addr = (struct sockaddr_in *)&addr;
      ip4_addr->sin_family = AF_INET;
      ip4_addr->sin_port = htons(req->servers_info[idx].port);
      ip4_addr->sin_addr.s_addr = inet_addr(req->servers_info[idx].ipbuf);
      
    } else { // ipv6

      ip6_addr = (struct sockaddr_in6 *)&addr;
      ip6_addr->sin6_family = AF_INET6;
      ip6_addr->sin6_port = htons(req->servers_info[idx].port);
      inet_pton(AF_INET6, req->servers_info[idx].ipbuf, ip6_addr->sin6_addr.s6_addr);
      
    }
  }

  ret = sctp_connectx(sd, (struct sockaddr *)&addr, 1, &assoc_id);
  if(ret < 0){
    LOG_E(SCTP, "sctp_connectx error. %s:%d\n", strerror(errno), errno);
    return;
  }
  
  ns = sctp_peeloff(sd, assoc_id);
  if (ns == -1) {
    LOG_E(SCTP, "sctp_peeloff\n");
    LOG_E(SCTP, "sctp_peeloff: sd=%d assoc_id=%d. %s:%d\n", sd, assoc_id, strerror(errno), errno);
    return;
  }

  elm = calloc(1, sizeof(sctp_cnx_elm_t));
  elm->sd               = ns;
  elm->connect_type     = SCTP_TYPE_CLIENT;
  elm->assoc_id         = assoc_id;
  elm->ppid             = 27;
  elm->in_streams       = req->servers_info[0].in_streams;
  elm->out_streams      = req->servers_info[0].out_streams;
  elm->task_id          = task_id;
  elm->local_port       = req->servers_info[0].port;
  sctp_elms_insert(&sctp_lists, elm);

  
  itti_subscribe_event_fd(TASK_SCTP, ns);
  sctp_lists.sctp_nb_cnx ++;
  LOG_I(SCTP, "connect to server success sd %d %d(%d %s:%u), peer sd %d, from task %s\n", sd, assoc_id, sctp_lists.sctp_nb_cnx, req->servers_info[0].ipbuf, req->servers_info[0].port, ns, itti_get_task_name(task_id));

}

void sctp_send_data(SctpDataSendReq_t *req, task_id_t            task_id)
{
  int ret;
  sctp_cnx_elm_t *elm = sctp_elms_get_by_associd(&sctp_lists, req->assoc_id);

  LOG_I(SCTP, "sctp_send_data assoc_id %u, length %u, in_stream %u, out_stream %u\n", req->assoc_id, req->length, req->in_stream, req->out_stream);
  if(elm == NULL)
  {
    LOG_E(SCTP, "sctp_send_data assoc_id %u not fount.\n", req->assoc_id);
    return;
  }
  
  ret = sctp_sendmsg(elm->sd, req->buffer,
                     req->length, NULL, 0,
                     htonl(elm->ppid), 0, 1, 0, 0);
  
  if(ret < 0) {
    LOG_E(SCTP, "sctp_sendmsg error. %s:%d\n", strerror(errno), errno);
    return;
  }
}


void sctp_get_peeraddresses(int sd, int *nb_remoteAddress, struct sockaddr **remoteAddress)
{
  int ns = -1;
  char addrbuf[32];
  struct sockaddr *temp_addr_p;

  ns = sctp_getpaddrs(sd, -1, (void*)&temp_addr_p);

  if(ns < 0) {
    LOG_E(SCTP, "sctp_getpaddrs error. %s:%d\n", strerror(errno), errno);
    return;
  }
  
  LOG_I(SCTP, "-------------------\n");
  LOG_I(SCTP, "Peer Address:\n");
  for(int i=0; i < ns; i++) {
    if (temp_addr_p[i].sa_family == AF_INET) {
      struct sockaddr_in *addr;
      addr = (struct sockaddr_in *)&temp_addr_p[i];
      memset(addrbuf, 0, sizeof(addrbuf));
      inet_ntop(AF_INET, &addr->sin_addr, addrbuf, sizeof(addrbuf));

      LOG_I(SCTP, "    -[%s]:%u\n", addrbuf, ntohs(addr->sin_port));
    }
    else {
      struct sockaddr_in6 *ip6_addr;
      ip6_addr = (struct sockaddr_in6 *)&temp_addr_p[i];
      memset(addrbuf, 0, sizeof(addrbuf));
      inet_ntop(AF_INET6, &ip6_addr->sin6_addr.s6_addr, addrbuf, sizeof(addrbuf));

      LOG_I(SCTP, "    -[%s]:%d\n", addrbuf, ntohs(ip6_addr->sin6_port));

    }
  }
  LOG_I(SCTP, "-------------------\n");

  // free perr address
  if(nb_remoteAddress != NULL && remoteAddress != NULL) {
    *nb_remoteAddress = ns;
    *remoteAddress = temp_addr_p;
  }else {
    sctp_freepaddrs(temp_addr_p);
  }

}

void sctp_client_read_from_socket(sctp_cnx_elm_t *elm)
{
  int                           ret = -1;
  uint32_t                      from_len = 0;
  int                           msg_flags = 0;
  uint8_t                       buffer[8192];
  struct sockaddr_in            addr;
  struct sctp_sndrcvinfo        sinfo;


  memset((void *)&addr, 0, sizeof(struct sockaddr_in));
  from_len = (socklen_t)sizeof(struct sockaddr_in);
  memset((void *)&sinfo, 0, sizeof(struct sctp_sndrcvinfo));

  //int sctp_recvmsg(int s, void *msg, size_t len, struct sockaddr *from,
  //       socklen_t *fromlen, struct sctp_sndrcvinfo *sinfo,
  //       int *msg_flags);

  ret = sctp_recvmsg(elm->sd, (void*)buffer, 8192, (struct sockaddr *)&addr, &from_len, &sinfo, &msg_flags);

  if(ret < 0) {
    if (errno == ENOTCONN) {
        LOG_E(SCTP, "[%s:%d]Received not connected for sd %d\n", __FUNCTION__, __LINE__, elm->sd);
        close(elm->sd);
    } else {
        LOG_E(SCTP, "An error occured during read\n");
        LOG_E(SCTP, "[%s:%d]sctp_recvmsg (fd %d, len %d ): %s:%d\n", __FUNCTION__, __LINE__, elm->sd, ret, strerror(errno), errno);
    }
    return;
  }

#if 0
  union sctp_notification{          // notification event
      struct sctp_tlv    sn_header;
      struct sctp_assoc_change    sn_assoc_change;
      struct sctp_paddr_change    sn_paddr_change;
      struct sctp_remote_error    sn_remote_error;
      struct sctp_send_failed     sn_send_failed;
      struct sctp_shutdown_event  sn_shutdown_event;
      struct sctp_adaption_event  sn_adaption_event;
      struct sctp_pdapi_event     sn_pdapi_event;
  };
  struct sctp_tlv{
      u_int16_t sn_type;
      u_int16_t sn_flags;
      u_int32_t sn_length;
  };

  //* SCTP_ASSOC_CHANGE
  //本通知告知应用进程关联本身发生变动：或者已开始一个新的关联，或者已结束一个现有的关联。它提供的信息定义如下。
  struct sctp_assoc_change{
    u_int16_t    sac_type;
    u_int16_t    sac_flags;
    u_int32_t    sac_length;
    u_int16_t    sac_state;
    u_int16_t    sac_error;
    u_int16_t    sac_outbound_streams;
    u_int16_t    sac_inbound_streams;
    sctp_assoc_t sac_asoc_id;
    u_int8_t     sac_info[];
};

#endif

#if 0
enum sctp_sn_type {
        SCTP_SN_TYPE_BASE     = (1<<15),
        SCTP_ASSOC_CHANGE,    // 32769
#define SCTP_ASSOC_CHANGE SCTP_ASSOC_CHANGE
        SCTP_PEER_ADDR_CHANGE,
#define SCTP_PEER_ADDR_CHANGE SCTP_PEER_ADDR_CHANGE
        SCTP_SEND_FAILED,
#define SCTP_SEND_FAILED SCTP_SEND_FAILED
        SCTP_REMOTE_ERROR,
#define SCTP_REMOTE_ERROR SCTP_REMOTE_ERROR
        SCTP_SHUTDOWN_EVENT,
#define SCTP_SHUTDOWN_EVENT SCTP_SHUTDOWN_EVENT
        SCTP_PARTIAL_DELIVERY_EVENT,
#define SCTP_PARTIAL_DELIVERY_EVENT SCTP_PARTIAL_DELIVERY_EVENT
        SCTP_ADAPTATION_INDICATION,
#define SCTP_ADAPTATION_INDICATION SCTP_ADAPTATION_INDICATION
        SCTP_AUTHENTICATION_INDICATION,
#define SCTP_AUTHENTICATION_INDICATION SCTP_AUTHENTICATION_INDICATION
        SCTP_SENDER_DRY_EVENT, //32777
#define SCTP_SENDER_DRY_EVENT SCTP_SENDER_DRY_EVENT
};
#endif

  if(msg_flags & MSG_NOTIFICATION) {
    union sctp_notification *snp;
    snp = (union sctp_notification *)buffer;

    LOG_I(SCTP, "[%s:%d]Received notification for sd %d, type %u\n", __FUNCTION__, __LINE__, elm->sd, snp->sn_header.sn_type);

    /* Client deconnection */
    if (SCTP_SHUTDOWN_EVENT == snp->sn_header.sn_type) {
      LOG_I(SCTP, "[%s:%d]Client %d is deconnection. close this sd\n", __FUNCTION__, __LINE__, elm->sd);
      
      itti_unsubscribe_event_fd(TASK_SCTP, elm->sd);
      sctp_elms_remove(&sctp_lists, elm->assoc_id);
      sctp_elms_dumps(&sctp_lists);
      // send notify to task
    }
          
    /* Association has changed. */
    else if (SCTP_ASSOC_CHANGE == snp->sn_header.sn_type) {
      struct sctp_assoc_change *sctp_assoc_changed;
      sctp_assoc_changed = &snp->sn_assoc_change;

      LOG_I(SCTP, "[%s:%d]Client association changed: %d\n", __FUNCTION__, __LINE__, sctp_assoc_changed->sac_state);

      /* New physical association requested by a peer */
      switch (sctp_assoc_changed->sac_state) {
        case SCTP_COMM_UP: {

            LOG_I(SCTP, "[%s:%d]Comm up notified for sd %d, assigned assoc_id %d\n",
                       __FUNCTION__, __LINE__, elm->sd, sctp_assoc_changed->sac_assoc_id);

#if 0
            sctp_cnx_elm_t *new_elm = calloc(1, sizeof(sctp_cnx_elm_t));
            
            int ns = sctp_peeloff(elm->sd, sctp_assoc_changed->sac_assoc_id);
            new_elm->sd               = ns;
            new_elm->connect_type     = SCTP_TYPE_CLIENT;
            new_elm->ppid             = elm->ppid;
            new_elm->assoc_id         = sctp_assoc_changed->sac_assoc_id;
            new_elm->in_streams       = elm->in_streams;
            new_elm->out_streams      = elm->out_streams;
            new_elm->task_id          = elm->task_id;
            new_elm->local_port       = elm->local_port;
        
            if (sctp_get_sockinfo(ns, &new_elm->in_streams, &new_elm->out_streams,
                                  &new_elm->assoc_id) != 0) {
                LOG_I(SCTP, "sctp_get_sockinfo failed\n");
                close(ns);
                free(new_elm);
                return;
            }
     
            sctp_elms_insert(&sctp_lists, new_elm);

            sctp_elms_dumps(&sctp_lists);
            itti_subscribe_event_fd(TASK_SCTP, ns);
#endif            
            //get peeraddr
            sctp_get_peeraddresses(elm->sd, NULL, NULL);

            // send notify to task
            MessageDef *msg = itti_alloc_new_message(TASK_SCTP, SCTP_CLIENT_SETUP_RESP);
            SCTP_CLIENT_SETUP_RESP(msg).in_stream = elm->in_streams;
            SCTP_CLIENT_SETUP_RESP(msg).out_stream = elm->out_streams;
            SCTP_CLIENT_SETUP_RESP(msg).assoc_id = elm->assoc_id;
            SCTP_CLIENT_SETUP_RESP(msg).sctp_state = 1;

            itti_send_msg_to_task(elm->task_id, 0, msg);
        }
        break;

        case SCTP_SHUTDOWN_COMP: {
          LOG_I(SCTP, "[%s:%d]Client %d is SHUTDOWN. close this sd\n", __FUNCTION__, __LINE__, elm->sd);
          
          itti_unsubscribe_event_fd(TASK_SCTP, elm->sd);
          sctp_elms_remove(&sctp_lists, elm->assoc_id);
          sctp_elms_dumps(&sctp_lists);
          // send notify to task
          
        }
        break;

        default:
          LOG_E(SCTP, "[%s:%d]unhandled: SCTP_ASSOC_CHANGE to %d\n", __FUNCTION__, __LINE__, sctp_assoc_changed->sac_state);
          break;
      }
   }

  }
  //new message;
  else {
    LOG_I(SCTP, "Receive a message length %d ppid %d\n", ret);
    for(int i=1; i<=ret; i++){
      printf("%02x ", buffer[i-1]);
      if(i%8 == 0) printf("\n");
    }
    printf("\n");
    //LOG_I(SCTP, "%s\n", buffer); 
    LOG_I(SCTP, "[%d][%d] Msg of length %d received from port %u, on stream %d, PPID %d\n",
                   sinfo.sinfo_assoc_id, elm->sd, ret, ntohs(addr.sin_port),
                   sinfo.sinfo_stream, ntohl(sinfo.sinfo_ppid));


    MessageDef *msg = itti_alloc_new_message(TASK_S1AP, SCTP_DATA_IND_REQ);
    SCTP_DATA_IND_REQ(msg).in_stream   = elm->in_streams;
    SCTP_DATA_IND_REQ(msg).out_stream  = elm->out_streams;
    SCTP_DATA_IND_REQ(msg).assoc_id    = elm->assoc_id;
    SCTP_DATA_IND_REQ(msg).buffer      = calloc(ret, sizeof(uint8_t));
    memcpy(SCTP_DATA_IND_REQ(msg).buffer, buffer, ret);
    SCTP_DATA_IND_REQ(msg).length      = ret;
    itti_send_msg_to_task(elm->task_id, 0, msg);
  }
}

int sctp_get_sockinfo(int sock, uint16_t *instream, uint16_t *outstream,
                      int32_t *assoc_id)
{
  socklen_t i;
  struct sctp_status status;

  memset(&status, 0, sizeof(struct sctp_status));
  i = sizeof(struct sctp_status);

  /* if sock refers to a multi SCTP endpoint, *assoc_id gives us
   * the association ID that we want
   */
  if (assoc_id != NULL)
    status.sstat_assoc_id = *assoc_id;

  if (getsockopt(sock, IPPROTO_SCTP, SCTP_STATUS, &status, &i) < 0) {
    LOG_E(SCTP, "Getsockopt SCTP_STATUS failed: %s\n", strerror(errno));
    return -1;
  }

  LOG_I(SCTP, "----------------------\n");
  LOG_I(SCTP, "SCTP Status:\n");
  LOG_I(SCTP, "assoc id .....: %u\n", status.sstat_assoc_id);
  LOG_I(SCTP, "state ........: %d\n", status.sstat_state);
  LOG_I(SCTP, "instrms ......: %u\n", status.sstat_instrms);
  LOG_I(SCTP, "outstrms .....: %u\n", status.sstat_outstrms);
  LOG_I(SCTP, "fragmentation : %u\n", status.sstat_fragmentation_point);
  LOG_I(SCTP, "pending data .: %u\n", status.sstat_penddata);
  LOG_I(SCTP, "unack data ...: %u\n", status.sstat_unackdata);
  LOG_I(SCTP, "rwnd .........: %u\n", status.sstat_rwnd);
  LOG_I(SCTP, "peer info     :\n");
  LOG_I(SCTP, "    state ....: %u\n", status.sstat_primary.spinfo_state);
  LOG_I(SCTP, "    cwnd .....: %u\n", status.sstat_primary.spinfo_cwnd);
  LOG_I(SCTP, "    srtt .....: %u\n" , status.sstat_primary.spinfo_srtt);
  LOG_I(SCTP, "    rto ......: %u\n" , status.sstat_primary.spinfo_rto);
  LOG_I(SCTP, "    mtu ......: %u\n" , status.sstat_primary.spinfo_mtu);
  LOG_I(SCTP, "----------------------\n");

  if (instream != NULL) {
    *instream = status.sstat_instrms;
  }

  if (outstream != NULL) {
    *outstream = status.sstat_outstrms;
  }

  if (assoc_id != NULL) {
    *assoc_id = status.sstat_assoc_id;
  }

  return 0;
}

void sctp_server_accept_new_associations_req(sctp_cnx_elm_t *elm)
{
  int                           ret = -1;
  uint32_t                      from_len = 0;
  int                           msg_flags = 0;
  uint8_t                       buffer[8192];
  struct sockaddr_in            addr;
  struct sctp_sndrcvinfo        sinfo;


  memset((void *)&addr, 0, sizeof(struct sockaddr_in));
  from_len = (socklen_t)sizeof(struct sockaddr_in);
  memset((void *)&sinfo, 0, sizeof(struct sctp_sndrcvinfo));

  //int sctp_recvmsg(int s, void *msg, size_t len, struct sockaddr *from,
  //       socklen_t *fromlen, struct sctp_sndrcvinfo *sinfo,
  //       int *msg_flags);

  ret = sctp_recvmsg(elm->sd, (void*)buffer, 8192, (struct sockaddr *)&addr, &from_len, &sinfo, &msg_flags);

  if(ret < 0) {
    if (errno == ENOTCONN) {
        LOG_E(SCTP, "[%s:%d]Received not connected for sd %d\n", __FUNCTION__, __LINE__, elm->sd);
        close(elm->sd);
    } else {
        LOG_E(SCTP, "An error occured during read\n");
        LOG_E(SCTP, "[%s:%d]sctp_recvmsg (fd %d, len %d ): %s:%d\n", __FUNCTION__, __LINE__, elm->sd, ret, strerror(errno), errno);
    }
    return;
  }

#if 0
  union sctp_notification{          // notification event
      struct sctp_tlv    sn_header;
      struct sctp_assoc_change    sn_assoc_change;
      struct sctp_paddr_change    sn_paddr_change;
      struct sctp_remote_error    sn_remote_error;
      struct sctp_send_failed     sn_send_failed;
      struct sctp_shutdown_event  sn_shutdown_event;
      struct sctp_adaption_event  sn_adaption_event;
      struct sctp_pdapi_event     sn_pdapi_event;
  };
  struct sctp_tlv{
      u_int16_t sn_type;
      u_int16_t sn_flags;
      u_int32_t sn_length;
  };

  //* SCTP_ASSOC_CHANGE
  //本通知告知应用进程关联本身发生变动：或者已开始一个新的关联，或者已结束一个现有的关联。它提供的信息定义如下。
  struct sctp_assoc_change{
    u_int16_t    sac_type;
    u_int16_t    sac_flags;
    u_int32_t    sac_length;
    u_int16_t    sac_state;
    u_int16_t    sac_error;
    u_int16_t    sac_outbound_streams;
    u_int16_t    sac_inbound_streams;
    sctp_assoc_t sac_asoc_id;
    u_int8_t     sac_info[];
};

#endif

  if(msg_flags & MSG_NOTIFICATION) {
    union sctp_notification *snp;
    snp = (union sctp_notification *)buffer;

    LOG_I(SCTP, "[%s:%d]Received notification for sd %d, type %u\n", __FUNCTION__, __LINE__, elm->sd, snp->sn_header.sn_type);
    
    /* Association has changed. */
    if (SCTP_ASSOC_CHANGE == snp->sn_header.sn_type) {
      struct sctp_assoc_change *sctp_assoc_changed;
      sctp_assoc_changed = &snp->sn_assoc_change;

      LOG_I(SCTP, "[%s:%d]Client association changed: %d\n", __FUNCTION__, __LINE__, sctp_assoc_changed->sac_state);

      /* New physical association requested by a peer */
      switch (sctp_assoc_changed->sac_state) {
        case SCTP_COMM_UP: {
            LOG_I(SCTP, "[%s:%d]Comm up notified for sd %d, assigned assoc_id %d\n",
                       __FUNCTION__, __LINE__, elm->sd, sctp_assoc_changed->sac_assoc_id);
           
            sctp_cnx_elm_t *new_elm = calloc(1, sizeof(sctp_cnx_elm_t));
            
            int ns = sctp_peeloff(elm->sd, sctp_assoc_changed->sac_assoc_id);
            new_elm->sd               = ns;
            new_elm->connect_type     = SCTP_TYPE_CLIENT;
            new_elm->ppid             = elm->ppid;
            new_elm->assoc_id         = sctp_assoc_changed->sac_assoc_id;
            new_elm->in_streams       = elm->in_streams;
            new_elm->out_streams      = elm->out_streams;
            new_elm->task_id          = elm->task_id;
            new_elm->local_port       = elm->local_port;

            struct sockaddr_in            *cliaddr;
            int nb_cliaddrs;
            sctp_get_peeraddresses(ns, &nb_cliaddrs, (struct sockaddr **)&cliaddr);
/*
            if (sctp_set_init_opt(ns,
                        new_elm->in_streams,
                        new_elm->out_streams,
                        0,
                        0) < 0) {
              close(ns);
              LOG_E(SCTP, "[%s:%d]sctp_set_init_opt error. %s:%d\n", __FUNCTION__, __LINE__, strerror(errno), errno);
              return;
            }
*/ 
            if (sctp_get_sockinfo(ns, &elm->in_streams, &elm->out_streams,
                                  &new_elm->assoc_id) != 0) {
                LOG_I(SCTP, "sctp_get_sockinfo failed\n");
                close(ns);
                free(elm);
                return;
            }

            LOG_I(SCTP, "[%s:%d]Client sd %d assoc_id %d is connected\n", __FUNCTION__, __LINE__, ns, sctp_assoc_changed->sac_assoc_id);         
            sctp_elms_insert(&sctp_lists, new_elm);

            sctp_elms_dumps(&sctp_lists);
            itti_subscribe_event_fd(TASK_SCTP, ns);
            sctp_lists.sctp_nb_cnx ++;
        }
      }
   }

  }

}

void sctp_events_process(int nb_events, struct epoll_event *events)
{
  int i=0;

  for(i=0; i< nb_events; i++) {
    int32_t sd = events[i].data.fd;
    sctp_cnx_elm_t *elm = sctp_elms_get_by_sd(&sctp_lists, sd);
  
    if(elm != NULL) {
      if(elm->connect_type == SCTP_TYPE_CLIENT) {
        sctp_client_read_from_socket(elm);

      }
      else if(elm->connect_type == SCTP_TYPE_SERVER) {
        sctp_server_accept_new_associations_req(elm);
      }
    }
    
  }
}

void sctp_process_itti_msg(void *notUsed)
{
  MessageDef *msg_p;
  int nb_events = 0;
  struct epoll_event *events;
    
  itti_receive_msg(TASK_SCTP, &msg_p);

  if(msg_p != NULL) {
    switch(ITTI_MSG_ID(msg_p)) {
      case SCTP_SERVER_SETUP_REQ:
        sctp_server_setup_req(&SCTP_SERVER_SETUP_REQ(msg_p), ITTI_MSG_ORIGIN_ID(msg_p));
        break;
      case SCTP_CLIENT_SETUP_REQ:
        sctp_client_setup_req(&SCTP_CLIENT_SETUP_REQ(msg_p), ITTI_MSG_ORIGIN_ID(msg_p));
        break;
      case SCTP_DATA_SEND_REQ:
        sctp_send_data(&SCTP_DATA_SEND_REQ(msg_p), ITTI_MSG_ORIGIN_ID(msg_p));

        if(SCTP_DATA_SEND_REQ(msg_p).buffer) {
          free(SCTP_DATA_SEND_REQ(msg_p).buffer);
        }
        
      default:
        break;
    }

    itti_free(ITTI_MSG_ORIGIN_ID(msg_p), msg_p);
    msg_p = NULL;
  }

  nb_events = itti_get_events_locked(TASK_SCTP, &events);
  sctp_events_process(nb_events, events);
}

void *sctp_task(void *arg)
{
  sctp_init();
  wait_sync("sctp_task");
  while(1) {
    sctp_process_itti_msg(NULL);
  }
  return NULL;
}



