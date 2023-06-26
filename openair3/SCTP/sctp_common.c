/*
  Author: zhenghuangkun
*/

#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/eventfd.h>
#include <errno.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <string.h>

#include <netinet/in.h>
#include <netinet/sctp.h>

#include "intertask_interface.h"
#include "sctp_task.h"
#include "sctp_defs.h"
#include "sctp_common.h"
#include "log.h"


#define RB_LEFT(node)     node->left
#define RB_RIGHT(node)    node->right

void sctp_elms_right_dumps(sctp_cnx_list_t *node);


int sctp_compare(sctp_cnx_elm_t *s, sctp_cnx_elm_t *c, int flag)
{
  if(!s || !c)
    return -1;

  if(flag) {
    if(s->assoc_id < c->assoc_id)
      return 1;
    else if(s->assoc_id == c->assoc_id)
      return 0;
    else 
      return -1;
  } else {
    if(s->sd < c->sd)
      return 1;
    else if(s->sd == c->sd)
      return 0;
    else
      return -1;
  }

}

void sctp_list_init(sctp_info_t *info)
{
  info->entry.elm = NULL;
  info->entry.left = NULL;
  info->entry.right = NULL;
}


void sctp_elms_insert(sctp_info_t *info, sctp_cnx_elm_t *elms)
{
  sctp_cnx_list_t *lists;
  sctp_cnx_elm_t  *e;
  int ret = 0;

  lists = &info->entry;

  // head
  if(lists->elm == NULL){
    lists->elm = elms;
    lists->left = NULL;
    lists->right = NULL;
    return;
  }


  for(e=lists->elm; e!=NULL; ) {
    ret = sctp_compare(e, elms, 1);

    if(ret == 0) {
      LOG_E(SCTP, "%d insert error. assoc_id exists\n", elms->assoc_id);
      return;
    }

    else if(ret == 1) {
      if(RB_RIGHT(lists) == NULL) {
        RB_RIGHT(lists) = calloc(1, sizeof(sctp_cnx_list_t));
        lists = RB_RIGHT(lists);
        lists->left = NULL;
        lists->right = NULL;
        lists->elm = elms;
        return;
      }

      lists = RB_RIGHT(lists);
    }
    else {
      if(RB_LEFT(lists) == NULL) {
        RB_LEFT(lists) = calloc(1, sizeof(sctp_cnx_list_t));
        lists = RB_LEFT(lists);
        lists->left = NULL;
        lists->right = NULL;
        lists->elm = elms;
        return;
      }

      lists = RB_LEFT(lists);
    }

    e = lists->elm;
  }
  
}


sctp_cnx_elm_t *sctp_elms_get_by_associd(sctp_info_t *info, int32_t assoc_id) 
{
  sctp_cnx_list_t *lists;
  sctp_cnx_elm_t  *e;

  lists = &info->entry;

  for(e=lists->elm; e!=NULL;) {
    if(e->assoc_id == assoc_id) {
      return e;
    }
    else if(e->assoc_id < assoc_id) {
      lists = lists->right;
      if(lists == NULL) 
        break;
    }
    else {
      lists = lists->left;
      if(lists == NULL) 
        break;
    }

    e = lists->elm;
  }

  return NULL;
}

sctp_cnx_elm_t *sctp_elms_get_by_sd(sctp_info_t *info, int32_t sd) 
{
  sctp_cnx_list_t *lists;
  sctp_cnx_elm_t  *e;

  lists = &info->entry;

  for(e=lists->elm; e!=NULL;) {
    if(e->sd == sd) {
      return e;
    }
    else if(e->sd < sd) {
      lists = lists->right;
      if(lists == NULL) 
        break;
    }
    else {
      lists = lists->left;
      if(lists == NULL) 
        break;
    }

    e = lists->elm;
  }

  return NULL;
}


static int left_cnt = 0;
sctp_cnx_list_t * sctp_elms_left_find(sctp_cnx_list_t *node, sctp_cnx_list_t **parent)
{
  
  if(RB_RIGHT(node) == NULL) {
    left_cnt = 0;
    return node;
  }
  
  else {
    if(left_cnt == 0)
      *parent = RB_LEFT((*parent));
    else
      *parent = RB_RIGHT((*parent));
    
    left_cnt = 1;
    return sctp_elms_left_find(RB_RIGHT(node), parent);
  }
  
}

static int right_cnt = 0;
sctp_cnx_list_t * sctp_elms_right_find(sctp_cnx_list_t *node, sctp_cnx_list_t **parent)
{
  
  if(RB_LEFT(node) == NULL) {
    right_cnt = 0;
    return node;
  }
  
  else {
    if(right_cnt == 0)
      *parent = RB_RIGHT((*parent));
    else
      *parent = RB_LEFT((*parent));

    right_cnt = 1;
    return sctp_elms_right_find(RB_LEFT(node), parent);
  }
  
}


void sctp_elms_remove(sctp_info_t *info, int32_t assoc_id)
{
  sctp_cnx_list_t *lists, *head, *parent;
  sctp_cnx_list_t *replaceNode, *replaceParent;
  sctp_cnx_elm_t  *e;

  head = &info->entry;
  lists = &info->entry;
  parent = &info->entry;
  
  for(e=lists->elm; e!=NULL;) {
    if(e->assoc_id == assoc_id) {
      //head
      if(lists == head) {
        head->left = NULL;
        head->right = NULL;
        free(head->elm);
        return;
      }

      if(lists->left == NULL && lists->right == NULL) {
        if(parent->left == lists) {
          parent->left = NULL;
          printf("delete left node. assoc_id %d. if left is NULL, right is NULL. parent left\n", e->assoc_id);
          free(e);
          free(lists);
          return;
        }
        else {
          parent->right = NULL;
          printf("delete left node. assoc_id %d. if left is NULL, right is NULL. parent right\n", e->assoc_id);
          free(e);
          free(lists);
          return;
        }
      }

      else if(lists->left != NULL && lists->right == NULL) {
        if(parent->left == lists) {
          parent->left = lists->left;
          printf("delete left node. assoc_id %d. if left not NULL, right is NULL. parent left\n", e->assoc_id);
          free(e);
          free(lists);
          return;
        }
        else {
          parent->right = lists->left;
          printf("delete left node. assoc_id %d. if left not NULL, right is NULL. parent right\n", e->assoc_id);
          free(e);
          free(lists);
          return;
        }

      }
      else if(lists->left == NULL && lists->right != NULL) {
        if(parent->left == lists) {
          parent->left = lists->right;
          printf("delete left node. assoc_id %d. if left is NULL, right not NULL. parent left\n", e->assoc_id);
          free(e);
          free(lists);
          return;
        }
        else {
          parent->right = lists->right;
          printf("delete left node. assoc_id %d. if left is NULL, right not NULL. parent right\n", e->assoc_id);
          free(e);
          free(lists);
          return;
        }

      }

      else {
        replaceParent = lists;
        replaceNode = sctp_elms_left_find(lists->left, &replaceParent);

        if(lists == replaceParent) {
          free(lists->elm);
          lists->elm = replaceNode->elm;
          
          if(replaceNode->left != NULL) {
            lists->left = replaceNode->left;
            free(replaceNode);
          } else {
            free(lists->left);
            lists->left = NULL;
          }
        }

        else {
          free(lists->elm);
          
          lists->elm = replaceNode->elm;

          if(replaceNode->left != NULL) {
            replaceParent->right = replaceNode->left;
            free(replaceNode);
          } else {
            free(replaceNode);
          }
        }
        return;
      }

      break;
    }
    else if(e->assoc_id < assoc_id) {
      parent = lists;
      lists = lists->right;
      if(lists == NULL) 
        break;
    }
    else {
      parent = lists;
      lists = lists->left;
      if(lists == NULL) 
        break;
    }

    e = lists->elm;
  }
}

static int deep = 0;

void sctp_elms_left_dumps(sctp_cnx_list_t *node)
{
  if(node) {

    deep++;
    
    if(node->elm) {
      for(int i=0; i<deep; i++)
        printf("%s", "|───");
      printf("%d\n", node->elm->assoc_id);
    }

    if(node->left) {
      sctp_elms_left_dumps(node->left);
      deep --;
    }

    if(node->right) {
      sctp_elms_right_dumps(node->right);
      deep --;
    }
  }
}

void sctp_elms_right_dumps(sctp_cnx_list_t *node)
{
  if(node) {
    deep ++;
    
    if(node->elm) {
      for(int i=0; i<deep-1; i++)
        printf("%s", "|───");
      printf("└───%d\n", node->elm->assoc_id);
    }

    if(node->left) {
      sctp_elms_left_dumps(node->left);
      deep --;
    }

    if(node->right) {
      sctp_elms_right_dumps(node->right);
      deep --;
    }

  }

}

void sctp_elms_dumps(sctp_info_t *info)
{
  sctp_cnx_list_t *list;

  list = &info->entry;

  deep = 0;

  printf("%d\n", list->elm->assoc_id);

  sctp_elms_left_dumps(list->left);
  deep = 0;
  sctp_elms_right_dumps(list->right);
}




/* sctp set commons */
int sctp_set_init_opt(int sd, uint16_t instreams, uint16_t outstreams,
                      uint16_t max_attempts, uint16_t init_timeout)
{
  int on = 1;
  struct sctp_initmsg init;
  memset((void *)&init, 0, sizeof(struct sctp_initmsg));

  init.sinit_num_ostreams   = outstreams;
  init.sinit_max_instreams  = instreams;
  init.sinit_max_attempts   = max_attempts;
  init.sinit_max_init_timeo = init_timeout;

  if(setsockopt(sd, IPPROTO_SCTP, SCTP_INITMSG, &init, sizeof(init)) < 0){
    return -1;
  }

  /* Allow socket reuse */
  if (setsockopt(sd, SOL_SOCKET, SO_REUSEADDR, &on, sizeof(on)) < 0) {
    return -1;
  }

  return 0;
}
