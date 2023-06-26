/*
  Author: zhenghuangkun
*/
#define _GNU_SOURCE
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <errno.h>
#include <stdlib.h>
#include <string.h>
#include <signal.h>
#include <pthread.h>
#include "telnetsrv.h"
#include <string.h>
#include <strings.h>
#include <stdarg.h>
#include <unistd.h>
#include <fcntl.h>
#include <dlfcn.h>
#include <sys/time.h>
#include <sys/resource.h>
#include <pthread.h>
#include "log.h"

uint32_t g_telnet_service = 10;
uint32_t g_telnet_offect = 10;

void telnet_shell_test_mod_cmd(int32_t sock, const char *option);


telnetshell_cmd_info_t g_testcmd[] = {
  {"test", "[start,stop]", telnet_shell_test_mod_cmd},
  {"", "", NULL}
};

telnetshell_var_info_t g_testvar[] = {
  {"service", TELNET_SHELL_VAR_TYPE_UINT32, &g_telnet_service},
  {"offect", TELNET_SHELL_VAR_TYPE_UINT32, &g_telnet_offect},
  {"", TELNET_SHELL_VAR_TYPE_NONO, NULL}
};


void string_trip(char *s1)
{
  char *ps = s1;
  char *pe = s1 + strlen(s1) - 1 - 2; // - /r/n
  size_t  n = strlen(s1);
  int i=0;
  int r=0;
  int l=0;

  for(i=0; i<n && *ps == ' '; i++) {
    ps++;
  }
  l = i;

  for(i=0; i<n && *pe == ' '; i++) {
    pe--;
  }
  r = i;

  if(l + r >= n-2) {
    //printf("all space\n");
    return;
  }
  memcpy(s1, s1+l, n-l-r-2);
  s1[n-l-r-2] = 0;
}

uint8_t string_compare(const char *s1, const char *s2){
  uint8_t ret = 0; // ng
  size_t  n = strlen(s2);

  if(strncasecmp(s1, s2, n) == 0)
    ret = 1;
  
  return ret;
}


void client_print(int32_t sock, const char *sendbuf, int32_t size)
{
  send(sock, sendbuf, size, 0);
  return;
}

void telnet_shell_test_mod_cmd(int32_t sock, const char *option)
{
  

}

void telnet_var_setget(int32_t sock, telnetshell_var_info_t *var, const char *cmd, const char *cmdarg)
{
  int8_t sflag = 0;
  uint32_t len = 0;
  char sbuffer[1024];
  char varname[TELNET_SHELL_VAR_NAME_LENGTH] = {0};
  char optstr[10];

  sscanf(cmdarg, "%20s %s", varname, optstr);

  if(string_compare(cmd, "set"))
    sflag = 1;

  for(int i=0; var[i].voidptr != NULL; i++) {
    if(string_compare(varname, var[i].varname)) {
      if(sflag) {
        switch(var[i].type){
          case TELNET_SHELL_VAR_TYPE_INT8:
            *((int8_t *)var[i].voidptr) = atoi(optstr);
            break;
          case TELNET_SHELL_VAR_TYPE_UINT8:
            *((uint8_t *)var[i].voidptr) = atoi(optstr);
            break;
          case TELNET_SHELL_VAR_TYPE_INT16:
            *((int16_t *)var[i].voidptr) = atoi(optstr);
            break;
          case TELNET_SHELL_VAR_TYPE_UINT16:
            *((uint16_t *)var[i].voidptr) = atoi(optstr);
            break;
          case TELNET_SHELL_VAR_TYPE_INT32:
            *((int32_t *)var[i].voidptr) = atoi(optstr);
            break;
          case TELNET_SHELL_VAR_TYPE_UINT32:
            *((uint32_t *)var[i].voidptr) = atoi(optstr);
            break;
          case TELNET_SHELL_VAR_TYPE_INT64:
            *((long long *)var[i].voidptr) = atoll(optstr);
            break;
          case TELNET_SHELL_VAR_TYPE_UINT64:
            *((unsigned long long *)var[i].voidptr) = atoll(optstr);
            break;
          case TELNET_SHELL_VAR_TYPE_DOUBLE:
          case TELNET_SHELL_VAR_TYPE_UDOUBLE:
            *((double *)var[i].voidptr) = atof(optstr);
            break;
          case TELNET_SHELL_VAR_TYPE_STRING:
            strcpy((char *)var[i].voidptr, optstr);
            break;
          default:
            return;
        }

        len = sprintf(sbuffer, "set %s to %s success\n", varname, optstr);
      }

      else {
        switch(var[i].type){
          case TELNET_SHELL_VAR_TYPE_INT8:
          case TELNET_SHELL_VAR_TYPE_INT16:
          case TELNET_SHELL_VAR_TYPE_INT32:
          case TELNET_SHELL_VAR_TYPE_INT64:
            len = sprintf(sbuffer, "%s is %d\n", varname, *((int32_t *)var[i].voidptr));
            break;
          case TELNET_SHELL_VAR_TYPE_UINT8:
          case TELNET_SHELL_VAR_TYPE_UINT16:
          case TELNET_SHELL_VAR_TYPE_UINT32:
          case TELNET_SHELL_VAR_TYPE_UINT64:
            len = sprintf(sbuffer, "%s is %u\n", varname, *((uint32_t *)var[i].voidptr));
            break;
          default:
            return;
        }
      }

      break;
    }
  }

  client_print(sock, sbuffer, len);

}

void *process_command(void *arg)
{
  int s = 0;
  char rbuffer[256] = {0};
  char sbuffer[256] = {0};
  char moduleName[TELNET_SHELL_MODULE_MAX_LENGTH] = {0};
  char cmd[TELNET_SHELL_CMD_NAME_LENGTH] = {0};
  char cmdarg[TELNET_SHELL_CMD_MAX_MSG_LENGTH] = {0};
  telnetcli_info_t *pCliInfo = (telnetcli_info_t *)arg;

  while(1){
    memset(rbuffer, 0x00, sizeof(rbuffer));
    memset(sbuffer, 0x00, sizeof(sbuffer));
    s = recv(pCliInfo->sock, rbuffer, 256, 0);

    if(s>0){
      LOG_I(TELNET, "recv info:%s\n", rbuffer);

      //rbuffer
      string_trip(rbuffer);

      memset(cmd, 0x00, sizeof(cmd));
      memset(cmdarg, 0x00, sizeof(cmdarg));

      sscanf(rbuffer, "%10s %10s %64[^\t\n]", moduleName, cmd, cmdarg);
      LOG_D(TELNET, "moduleName:%sddd\n", moduleName);
      LOG_D(TELNET, "cmd:%sdd\n", cmd);
      LOG_D(TELNET, "cmdarg:%sdd\n", cmdarg);

      
      if(string_compare(moduleName, "help")) {
        
      }
      else {
        for(int i=0; i<telnetsrv_params.cmdListsNum; i++) {
          if(string_compare(moduleName, telnetsrv_params.CmdParLists[i].moduleName)) {

            if(string_compare(cmd, "set") || string_compare(cmd, "get")) {
              telnet_var_setget(pCliInfo->sock, telnetsrv_params.CmdParLists[i].var, cmd, cmdarg);
              
            }
            else{
              for(int j=0; telnetsrv_params.CmdParLists[i].cmd[j].cmdfunc != NULL;j++) {
                if(string_compare(cmd, telnetsrv_params.CmdParLists[i].cmd[j].cmdname)) {
                  telnetsrv_params.CmdParLists[i].cmd[j].cmdfunc(pCliInfo->sock, cmdarg);
                  break;
                }
                
              }
            }
          }
        }
      }
      
      strcpy(sbuffer, "> ");
      client_print(pCliInfo->sock, sbuffer, strlen(sbuffer));
    }
  }
}

void run_telnetsrv(void) 
{
  int sock;
  struct sockaddr_in name;
  sock = socket(AF_INET, SOCK_STREAM, 0);

  if(sock == -1) {
    LOG_E(TELNET, "%s:%s:%d socket create error.%s\n", __FILE__, __FUNCTION__, __LINE__, strerror(errno));
    exit(0);
  }

  name.sin_family = AF_INET;
  name.sin_addr.s_addr = INADDR_ANY;
  name.sin_port = htons(telnetsrv_params.port);

  if(bind(sock, (struct sockaddr *)&name, sizeof(name))) {
    LOG_E(TELNET, "%s:%s:%d bind error.%s\n", __FILE__, __FUNCTION__, __LINE__, strerror(errno));
    exit(0);
  }

  if(listen(sock, telnetsrv_params.clinums)) {
    LOG_E(TELNET, "%s:%s:%d listen error.%s\n", __FILE__, __FUNCTION__, __LINE__, strerror(errno));
    exit(0);
  }

  int cli_sock;
  uint32_t cli_len = sizeof(struct sockaddr_in);
  int idx = 0;
  struct sockaddr_in cli_addr;
  
  LOG_I(TELNET, "\nInitializing telnet server...\n");
  for(idx=0; idx< telnetsrv_params.clinums; idx++) {
    telnetsrv_params.cli_info[idx].sock = -1;
    telnetsrv_params.cli_info[idx].func = process_command;
  }

  while((cli_sock = accept(sock, &cli_addr, &cli_len)) != -1){
    LOG_I(TELNET, "Telnet client connected....\n");

    for(idx=0; idx< telnetsrv_params.clinums; idx++) {
      if(telnetsrv_params.cli_info[idx].sock == -1)
        break;
    }

    if(idx >= telnetsrv_params.clinums) continue;

    int ret;
    telnetsrv_params.cli_info[idx].sock = cli_sock;
    telnetsrv_params.cli_info[idx].addr = cli_addr.sin_addr.s_addr;
    telnetsrv_params.cli_info[idx].port = cli_addr.sin_port;

    LOG_I(TELNET, "Creating telnet thread %d\n", idx);
    ret = pthread_create(&telnetsrv_params.cli_info[idx].threadid, NULL, telnetsrv_params.cli_info[idx].func, (void *)&telnetsrv_params.cli_info[idx]);
    
    if(ret != 0) {
      LOG_E(TELNET, "%s:%s:%d pthread_create error.%s\n", __FILE__, __FUNCTION__, __LINE__, strerror(errno));
      continue;
    }

    
  }
  
}

void add_telnet_cmd(const char *moduleName, telnetshell_cmd_info_t *cmd, telnetshell_var_info_t *var)
{
  if(telnetsrv_params.cmdListsNum >= TELNET_SHELL_CMD_PARS_LIST_MAX){
    LOG_E(TELNET, "cmdListsNum error. add cmd fail\n");
    return;
  }

  if(!moduleName) {
    LOG_E(TELNET, "moduleName is NULL. add cmd fail\n");
    return;
  }

  if(!cmd) {
    LOG_E(TELNET, "cmd is NULL. add cmd fail\n");
    return;
  }

  if(!var) {
    LOG_E(TELNET, "var is NULL. add cmd fail\n");
    return;
  }

  strcpy(telnetsrv_params.CmdParLists[telnetsrv_params.cmdListsNum].moduleName, moduleName);
  telnetsrv_params.CmdParLists[telnetsrv_params.cmdListsNum].cmd = cmd;
  telnetsrv_params.CmdParLists[telnetsrv_params.cmdListsNum].var = var;
  telnetsrv_params.cmdListsNum++;
}

void telnetsrv_init(void)
{
  
  pthread_create(&telnetsrv_params.sthreadid, NULL, (void *(*)(void *))run_telnetsrv, NULL);

  telnetsrv_params.cmdListsNum=0;
  add_telnet_cmd("telnet", g_testcmd, g_testvar);
  return;
}
