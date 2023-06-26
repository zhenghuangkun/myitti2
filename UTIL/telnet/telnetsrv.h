/*! \file common/telnet/telnetsrv.h
 * \brief configuration module, include file for libconfig implementation 
 * \author Francois TABURET
 * \date 2020
 * \version 0.1
 * \note
 * \warning
 */
 
#ifndef TELNETSRV_H
#define TELNETSRV_H
#include <pthread.h>

#define TLENET_CLIENT_NUM_MAX               256
#define TELNET_SHELL_CMD_PARS_LIST_MAX      10
#define TELNET_SHELL_MODULE_MAX_LENGTH      10
#define TELNET_SHELL_CMD_NAME_LENGTH        20
#define TELNET_SHELL_CMD_HELP_LENGTH        80
#define TELNET_SHELL_CMD_MAX_MSG_LENGTH     64
#define TELNET_SHELL_VAR_NAME_LENGTH        20
#define TELNET_SHELL_VAR_HELP_LENGTH        80

typedef enum TELNET_SHELL_VAR_TYPE_e{
  TELNET_SHELL_VAR_TYPE_NONO = 0,
  TELNET_SHELL_VAR_TYPE_INT8 = 1,
  TELNET_SHELL_VAR_TYPE_UINT8,
  TELNET_SHELL_VAR_TYPE_INT16,
  TELNET_SHELL_VAR_TYPE_UINT16,
  TELNET_SHELL_VAR_TYPE_INT32,
  TELNET_SHELL_VAR_TYPE_UINT32,
  TELNET_SHELL_VAR_TYPE_INT64,
  TELNET_SHELL_VAR_TYPE_UINT64,
  TELNET_SHELL_VAR_TYPE_DOUBLE,
  TELNET_SHELL_VAR_TYPE_UDOUBLE,
  TELNET_SHELL_VAR_TYPE_STRING
}TELNET_SHELL_VAR_TYPE_t;


typedef void *(*thread_callback)(void *arg);

typedef struct telnetcli_info_s{
  int32_t sock;
  uint32_t addr;
  uint32_t port;
  pthread_t threadid;
  thread_callback func;
}telnetcli_info_t;

typedef struct telnetshell_cmd_info_s{
  char cmdname[TELNET_SHELL_CMD_NAME_LENGTH];
  char helpstr[TELNET_SHELL_CMD_HELP_LENGTH];
  void (*cmdfunc)(int32_t sock, const char *option);
}telnetshell_cmd_info_t;

typedef struct telnetshell_var_info_s{
  char varname[TELNET_SHELL_VAR_NAME_LENGTH];
  TELNET_SHELL_VAR_TYPE_t type;
  void *voidptr;
}telnetshell_var_info_t;


typedef struct telnetshell_cmd_par_set_s{
  char moduleName[TELNET_SHELL_MODULE_MAX_LENGTH];
  telnetshell_cmd_info_t *cmd;
  telnetshell_var_info_t *var;
}telnetshell_cmd_par_set_t;


typedef struct telnetsrv_params_s{
  int8_t                      flag;        /** telnetsrv on/off */
  int32_t                     sock;
  uint32_t                    port;
  pthread_t                   sthreadid;
  uint32_t                    clinums;
  telnetcli_info_t            cli_info[TLENET_CLIENT_NUM_MAX];
  uint32_t                    cmdListsNum;
  telnetshell_cmd_par_set_t   CmdParLists[TELNET_SHELL_CMD_PARS_LIST_MAX];
  
}telnetsrv_params_t;

telnetsrv_params_t telnetsrv_params;


void run_telnetsrv(void);
void telnetsrv_init(void);

#endif  /* CONFIG_PARAMDESC_H */
