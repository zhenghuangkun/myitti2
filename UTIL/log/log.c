/*
  Author: zhenghuangkun
*/
#include "log.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdarg.h>


char *log_color_output_start[] = {"", "", "", "", LOG_ORANGE, LOG_RED};
char log_output_level[] = {'N', 'T', 'D', 'I', 'W', 'E'};

void register_component(E_COMPONENT_T component, const char *component_name)
{
  if(component >= COMPONENT_MAX){
    printf("register_component error. component %d >= %d\n", component, COMPONENT_MAX);
  }
  
  strcpy(g_log->log_component[component].component_name, component_name);
  g_log->log_component[component].level = E_LOG_LEVEL_NONE;
}

void log_init(const char *filename)
{
  g_log = calloc(1, sizeof(log_t));

  if(!g_log)
    exit(0);


  register_component(L1, "L1");
  register_component(L2, "L2");
  register_component(L3, "L3");
  register_component(SCTP, "SCTP");
  register_component(TCP, "TCP");
  register_component(UDP, "UDP");
  register_component(NGAP, "NGAP");
  register_component(S1AP, "S1AP");
  register_component(TELNET, "TELNET");

  g_log->stream = stdout;
  g_log->fprint = fprintf;
  g_log->flag   = 0;
  
  if(filename && strlen(filename) > 0){
    memset(g_log->file_path, 0, sizeof(g_log->file_path));
    memcpy(g_log->file_path, filename, strlen(filename));
    g_log->stream = fopen(g_log->file_path, "w+");
    g_log->flag = 1;
  }
  

}

void log_output(const char *filename, const char *function, int line, E_COMPONENT_T component, E_LOG_LEVEL_T level, const char *format, ...)
{
  char params[1000];
  char log_buffer[1500];
  va_list args;
  
  va_start(args, format);

  vsnprintf(params, 1000, format, args);

  snprintf(log_buffer, 1500, "%s[%s][%c] %s%s",
    g_log->flag == 0?log_color_output_start[level]:"",
    g_log->log_component[component].component_name,
    log_output_level[level],
    params,
    g_log->flag == 0?LOG_END:""
    );

  g_log->fprint(g_log->stream, log_buffer);
  fflush(g_log->stream);
  va_end(args);
  
}



