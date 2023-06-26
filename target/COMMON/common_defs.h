/*
  Author: zhenghuangkun
*/

#ifndef COMMON_DEFS_H_
#define COMMON_DEFS_H_

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

extern pthread_cond_t sync_cond;
extern pthread_mutex_t sync_mutex;
extern int sync_var;


static inline void wait_sync(const char *threadName)
{
  printf( "waiting for sync (%s,%d/%p,%p,%p)\n", threadName, sync_var, &sync_var, &sync_cond, &sync_mutex);

  pthread_mutex_lock(&sync_mutex);

  while(sync_var<0)
    pthread_cond_wait(&sync_cond, &sync_mutex);

  
  pthread_mutex_unlock(&sync_mutex);

  printf("got sync (%s)\n", threadName);

  fflush(stdout);
  fflush(stderr);
}

#endif
