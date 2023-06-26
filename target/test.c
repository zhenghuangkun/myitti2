
#include <stdio.h>
#include <unistd.h>
#include <getopt.h>
#include <stdlib.h>
#include <string.h>
#include <sys/eventfd.h>
#include <intertask_interface.h>
#include <create_tasks.h>
#include "config_paramdesc.h"
#include "telnetsrv.h"
#include "log.h"


int exit_flag = 0;

char *log_filename = NULL;


uint32_t sctp_client_flag = 0;
uint32_t sctp_server_port = 0;
char *sctp_server_addr   = NULL;

pthread_cond_t sync_cond;
pthread_mutex_t sync_mutex;
int sync_var=-1;


void usage(paramdef_t *optios)
{
  int n = 0;
  char str[256] = {0};
  for(n=0; optios[n].type >= TYPE_STRING && optios[n].type <= TYPE_LASTSCALAR; n++) ;

  printf("Usage: test [options]\n");
  printf("Options:\n");

  for(int i=0; i<n; i++) {
    memset(str, 0, sizeof(str));
    sprintf(str, "  -%c, --%-25s %s\n", optios[i].optname[0], optios[i].optname, optios[i].helpstr);
    printf("%s", str);
  }
}

struct option * parse_options(paramdef_t *optios)
{
  struct option *opt;
  int n = 0;
  for(n=0; optios[n].type >= TYPE_STRING && optios[n].type <= TYPE_LASTSCALAR; n++) ;

  opt = malloc(sizeof(struct option) * n);
  
  for(int i=0; i<n; i++) {
    opt[i].name = optios[i].optname;
    opt[i].has_arg = optios[i].paramflags == 1?no_argument:required_argument;

    switch(optios[i].type) {
      case TYPE_INT8:
        *optios[i].i8ptr = optios[i].defintval;
        break;
      case TYPE_UINT8:
        *optios[i].u8ptr = optios[i].defuintval;
        break;
      case TYPE_INT16:
        *optios[i].i16ptr = optios[i].defintval;
        break;
      case TYPE_UINT16:
        *optios[i].u16ptr = optios[i].defuintval;
        break;
      case TYPE_INT:
        *optios[i].iptr = optios[i].defintval;
        break;
      case TYPE_UINT:
        *optios[i].uptr = optios[i].defuintval;
        break;
      case TYPE_DOUBLE:
        *optios[i].dblptr = optios[i].defdblval;
        break;
      case TYPE_STRING:
        *optios[i].strptr = strdup(optios[i].defstrval);
        break;
      default:
        printf("not parsed type for default value %s\n", optios[i].optname);
        exit(1);
    }
  }

  return opt;
}

void parse_command_args(int argc, char * argv[])
{
  int help = 0;
  int c = 0;
  int index = 0;
  paramdef_t optios[] = {
    {"telnetsrv", "Enable Telnetsrv ON/OFF",  0, i8ptr:&telnetsrv_params.flag,   defuintval:1,          TYPE_INT, 0},
    {"clinums",   "Telnetsrv client numbers", 0, uptr:&telnetsrv_params.clinums, defintval:5,           TYPE_INT, 0},
    {"port",      "Telnetsrv bind port",      0, uptr:&telnetsrv_params.port,    defuintval:9090,       TYPE_INT, 0},
    {"scf",       "sctp client flag",         1, uptr:&sctp_client_flag,         defuintval:0,          TYPE_INT, 0},
    {"ssp",       "sctp server port",         0, uptr:&sctp_server_port,         defuintval:5000,       TYPE_INT, 0},
    {"ssa",       "sctp server addr",         0, strptr:&sctp_server_addr,       defstrval:"127.0.0.1", TYPE_STRING, 0},
    {"log-mem",   "Output to file",           0, strptr:&log_filename,           defstrval:"",          TYPE_STRING, 0},
    {"help",      NULL,                       1, iptr:&help,                     defintval:0,           TYPE_INT, 0}
  };

  struct option *long_options = parse_options(optios);

  while((c = getopt_long_only(argc, argv, "", long_options, &index)) != -1) {
    if(optios[index].voidptr != NULL) {
      if(long_options[index].has_arg == no_argument) {
        *optios[index].iptr = 1;
      }
      else {
        switch(optios[index].type) {
          case TYPE_INT8:
            *optios[index].i8ptr = atoi(optarg);
            break;
          case TYPE_UINT8:
            *optios[index].u8ptr = atoi(optarg);
            break;
          case TYPE_INT16:
            *optios[index].i16ptr = atoi(optarg);
            break;
          case TYPE_UINT16:
            *optios[index].u16ptr = atoi(optarg);
            break;
          case TYPE_INT:
            *optios[index].iptr = atoi(optarg);
            break;
          case TYPE_UINT:
            *optios[index].uptr = atoi(optarg);
            break;
          case TYPE_DOUBLE:
            *optios[index].dblptr = atof(optarg);
            break;
          case TYPE_STRING:
            if(*optios[index].strptr) free(*optios[index].strptr);
            *optios[index].strptr = strdup(optarg);
            break;
        }

      }
       
    }      
  }


  if(help == 1){
    usage(optios);
    exit(0);
  }

  return;
}


int main(int argc, char *argv[])
{
  int count = 0;

  parse_command_args(argc, argv);

  log_init(log_filename);
  
  itti_init(TASK_MAX, THREAD_MAX, MESSAGES_ID_MAX, tasks_info, messages_info);

  create_tasks(1);

  if(telnetsrv_params.flag){
    telnetsrv_init();
  }

  // sync all thread
  pthread_mutex_lock(&sync_mutex);
  sync_var = 0;
  pthread_cond_broadcast(&sync_cond);
  pthread_mutex_unlock(&sync_mutex);
  
  MessageDef *msg = itti_alloc_new_message(TASK_L1, RRC_MAC_IN_SYNC_IND);

  RRC_MAC_IN_SYNC_IND(msg).frame = 0;
  RRC_MAC_IN_SYNC_IND(msg).sub_frame = 1;
  RRC_MAC_IN_SYNC_IND(msg).enb_index = 2;

  itti_send_msg_to_task(TASK_L2, 0, msg);

#if 0
  if(sctp_client_flag == 0) {
    msg = itti_alloc_new_message(TASK_L1, SCTP_SERVER_SETUP_REQ);
  
    SCTP_SERVER_SETUP_REQ(msg).port = 5000;
    SCTP_SERVER_SETUP_REQ(msg).ipv4v6_flag = 0;  // ipv4
    strcpy(SCTP_SERVER_SETUP_REQ(msg).ipbuf, "127.0.0.1");	// ipv4
    SCTP_SERVER_SETUP_REQ(msg).in_streams = 32;
    SCTP_SERVER_SETUP_REQ(msg).out_streams = 32;
  
    itti_send_msg_to_task(TASK_SCTP, 0, msg);
  } else {
    msg = itti_alloc_new_message(TASK_L1, SCTP_CLIENT_SETUP_REQ);
    int idx = 0;
    SCTP_CLIENT_SETUP_REQ(msg).servers_info[idx].port = sctp_server_port;
    SCTP_CLIENT_SETUP_REQ(msg).servers_info[idx].ipv4v6_flag = 0;  // ipv4
    strcpy(SCTP_CLIENT_SETUP_REQ(msg).servers_info[idx].ipbuf, sctp_server_addr);  // ipv4
    SCTP_CLIENT_SETUP_REQ(msg).servers_info[idx].in_streams = 32;
    SCTP_CLIENT_SETUP_REQ(msg).servers_info[idx].out_streams = 32;
    SCTP_CLIENT_SETUP_REQ(msg).nb_servers = 1;

    itti_send_msg_to_task(TASK_SCTP, 0, msg);
  }
#endif  
  
  if(sctp_client_flag == 0) {
    msg = itti_alloc_new_message(TASK_S1AP, SCTP_SERVER_SETUP_REQ);
    SCTP_SERVER_SETUP_REQ(msg).port = 5000;
    SCTP_SERVER_SETUP_REQ(msg).ipv4v6_flag = 0;  // ipv4
    strcpy(SCTP_SERVER_SETUP_REQ(msg).ipbuf, "127.0.0.1");  // ipv4
    SCTP_SERVER_SETUP_REQ(msg).in_streams = 32;
    SCTP_SERVER_SETUP_REQ(msg).out_streams = 32;

    itti_send_msg_to_task(TASK_SCTP, 0, msg);
    sleep(2);
  }

  if(sctp_client_flag == 1) {
    msg = itti_alloc_new_message(TASK_S1AP, SCTP_CLIENT_SETUP_REQ);
    int idx = 0;
    SCTP_CLIENT_SETUP_REQ(msg).servers_info[idx].port = sctp_server_port;
    SCTP_CLIENT_SETUP_REQ(msg).servers_info[idx].ipv4v6_flag = 0;  // ipv4
    strcpy(SCTP_CLIENT_SETUP_REQ(msg).servers_info[idx].ipbuf, sctp_server_addr);  // ipv4
    SCTP_CLIENT_SETUP_REQ(msg).servers_info[idx].in_streams = 32;
    SCTP_CLIENT_SETUP_REQ(msg).servers_info[idx].out_streams = 32;
    SCTP_CLIENT_SETUP_REQ(msg).nb_servers = 1;

    idx = 0;
    SCTP_CLIENT_SETUP_REQ(msg).local_info[idx].port = 5222;
    SCTP_CLIENT_SETUP_REQ(msg).local_info[idx].ipv4v6_flag = 0;   // ipv4
    strcpy(SCTP_CLIENT_SETUP_REQ(msg).local_info[idx].ipbuf, "127.0.0.1");
    SCTP_CLIENT_SETUP_REQ(msg).local_info[idx].in_streams = 32;
    SCTP_CLIENT_SETUP_REQ(msg).local_info[idx].out_streams = 32;

    idx++;
    SCTP_CLIENT_SETUP_REQ(msg).local_info[idx].port = 5223;
    SCTP_CLIENT_SETUP_REQ(msg).local_info[idx].ipv4v6_flag = 0;   // ipv4
    strcpy(SCTP_CLIENT_SETUP_REQ(msg).local_info[idx].ipbuf, "10.38.2.152");
    SCTP_CLIENT_SETUP_REQ(msg).local_info[idx].in_streams = 32;
    SCTP_CLIENT_SETUP_REQ(msg).local_info[idx].out_streams = 32;
    
    idx++;
    SCTP_CLIENT_SETUP_REQ(msg).local_info[idx].port = 5224;
    SCTP_CLIENT_SETUP_REQ(msg).local_info[idx].ipv4v6_flag = 0;   // ipv4
    strcpy(SCTP_CLIENT_SETUP_REQ(msg).local_info[idx].ipbuf, "10.38.161.21");
    SCTP_CLIENT_SETUP_REQ(msg).local_info[idx].in_streams = 32;
    SCTP_CLIENT_SETUP_REQ(msg).local_info[idx].out_streams = 32;

    idx++;
    SCTP_CLIENT_SETUP_REQ(msg).local_info[idx].port = 5225;
    SCTP_CLIENT_SETUP_REQ(msg).local_info[idx].ipv4v6_flag = 0;   // ipv4
    strcpy(SCTP_CLIENT_SETUP_REQ(msg).local_info[idx].ipbuf, "192.168.20.21");
    SCTP_CLIENT_SETUP_REQ(msg).local_info[idx].in_streams = 32;
    SCTP_CLIENT_SETUP_REQ(msg).local_info[idx].out_streams = 32;

    idx++;
    SCTP_CLIENT_SETUP_REQ(msg).local_info[idx].port = 5226;
    SCTP_CLIENT_SETUP_REQ(msg).local_info[idx].ipv4v6_flag = 0;   // ipv4
    strcpy(SCTP_CLIENT_SETUP_REQ(msg).local_info[idx].ipbuf, "192.168.10.101");
    SCTP_CLIENT_SETUP_REQ(msg).local_info[idx].in_streams = 32;
    SCTP_CLIENT_SETUP_REQ(msg).local_info[idx].out_streams = 32;

    SCTP_CLIENT_SETUP_REQ(msg).nb_locals = idx + 1;
    itti_send_msg_to_task(TASK_SCTP, 0, msg);

  }

  while(!exit_flag) {
    sleep(1);
    count ++;
#if 0
    MessageDef *msg = itti_alloc_new_message(TASK_L2, RRC_MAC_IN_SYNC_IND);

    RRC_MAC_IN_SYNC_IND(msg).frame = count & 0xFFFF;
    RRC_MAC_IN_SYNC_IND(msg).sub_frame = 1;
    RRC_MAC_IN_SYNC_IND(msg).enb_index = 2;

    itti_send_msg_to_task(TASK_L1, 0, msg);
    
    MessageDef *msg1 = itti_alloc_new_message(TASK_L1, RRC_MAC_IN_SYNC_IND);

    RRC_MAC_IN_SYNC_IND(msg1).frame = count & 0xFFFF;
    RRC_MAC_IN_SYNC_IND(msg1).sub_frame = 1;
    RRC_MAC_IN_SYNC_IND(msg1).enb_index = 2;
    itti_send_msg_to_task(TASK_L2, 0, msg1);
#endif
  }

  return 0;
}
