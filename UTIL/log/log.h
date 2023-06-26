/*! \file common/log/log.h
 * \brief configuration module, include file for libconfig implementation 
 * \author Francois TABURET
 * \date 2020
 * \version 0.1
 * \note
 * \warning
 */
 
#ifndef LOG_H
#define LOG_H
#include <stdio.h>

#define LOG_RED      "\033[0;31m"
#define LOG_L_RED    "\033[1;31m"

#define LOG_BLACK    "\033[30m"
#define LOG_L_BLACK  "\033[1;30m"

#define LOG_ORANGE   "\033[93m"
#define LOG_L_ORANGE "\033[1;93m"

#define LOG_GREEN    "\033[32m"
#define LOG_L_GREEN  "\033[1;32m"

#define LOG_BLUE     "\033[34m"
#define LOG_L_BLUE   "\033[1;34m"

#define LOG_END      "\033[0m"

typedef enum E_LOG_LEVEL_S{
  E_LOG_LEVEL_NONE = 0,
  E_LOG_LEVEL_TRACE,
  E_LOG_LEVEL_DEBUG,
  E_LOG_LEVEL_INFO,
  E_LOG_LEVEL_WARNING,
  E_LOG_LEVEL_ERROR
}E_LOG_LEVEL_T;

typedef enum E_COMPONENT_S{
  COMPONENT_MIN = 0,
  L1 = COMPONENT_MIN,
  L2,
  L3,
  SCTP,
  TCP,
  UDP,
  S1AP,
  NGAP,
  TELNET,
  COMPONENT_MAX
}E_COMPONENT_T;

typedef struct log_component_s{
  char            component_name[10];
  E_LOG_LEVEL_T   level;
  
}log_component_t;

typedef int (*f_log_output_func_t)(FILE *stream, const char *format, ...);

typedef struct log_s{
  log_component_t       log_component[COMPONENT_MAX];
  E_LOG_LEVEL_T         glevel;
  char                  file_path[256];
  unsigned char         flag;
  FILE                  *stream;
  f_log_output_func_t   fprint;
}log_t;


log_t *g_log;


void log_init(const char *filename);
void log_output(const char *filename, const char *function, int line, E_COMPONENT_T component, E_LOG_LEVEL_T level, const char *format, ...);

#define LOG_T(c, x...) do{ if(g_log->log_component[c].level <= E_LOG_LEVEL_TRACE) log_output(__FILE__, __FUNCTION__, __LINE__, c, E_LOG_LEVEL_TRACE, x);}while(0)
#define LOG_D(c, x...) do{ if(g_log->log_component[c].level <= E_LOG_LEVEL_DEBUG) log_output(__FILE__, __FUNCTION__, __LINE__, c, E_LOG_LEVEL_DEBUG, x);}while(0)
#define LOG_I(c, x...) do{ if(g_log->log_component[c].level <= E_LOG_LEVEL_INFO) log_output(__FILE__, __FUNCTION__, __LINE__, c, E_LOG_LEVEL_INFO, x);}while(0)
#define LOG_W(c, x...) do{ if(g_log->log_component[c].level <= E_LOG_LEVEL_WARNING) log_output(__FILE__, __FUNCTION__, __LINE__, c, E_LOG_LEVEL_WARNING, x);}while(0)
#define LOG_E(c, x...) do{ if(g_log->log_component[c].level <= E_LOG_LEVEL_ERROR) log_output(__FILE__, __FUNCTION__, __LINE__, c, E_LOG_LEVEL_ERROR, x);}while(0)






#endif  /* CONFIG_PARAMDESC_H */
