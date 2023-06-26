/*! \file common/config/config_paramdesc.h
 * \brief configuration module, include file for libconfig implementation 
 * \author Francois TABURET
 * \date 2020
 * \version 0.1
 * \note
 * \warning
 */
 
#include <stdint.h>
#ifndef CONFIG_PARAMDESC_H
#define CONFIG_PARAMDESC_H

#define MAX_OPTNAME_SIZE 64


typedef struct paramdef_s{
  char         optname[MAX_OPTNAME_SIZE]; /* parameter name, can be used as long command line option */
  char         *helpstr;                  /* help string */
  unsigned int paramflags;                /* value is a "ored" combination of above PARAMFLAG_XXXX values */
  union {                                 /* pointer to the parameter value, completed by the config module */
    char        **strptr;
    char        **strlistptr;
    uint8_t     *u8ptr;
    int8_t      *i8ptr;
    uint16_t    *u16ptr;
    int16_t     *i16ptr;
    uint32_t    *uptr;
    int32_t     *iptr;
    uint64_t    *u64ptr;
    int64_t     *i64ptr;
    double      *dblptr;
    void        *voidptr;
  };

  union {                                /* default parameter value, to be used when PARAMFLAG_MANDATORY is not specified */
    char      *defstrval;
    char      **defstrlistval;
    uint32_t  defuintval;
    int       defintval;
    uint64_t  defint64val;
    int       *defintarrayval;
    double    defdblval;
  } ;
  char type;                              /* parameter value type, as listed below as TYPE_XXXX macro */
  int numelt;                             /* number of elements in a list or array parameters or max size of string value */
  int *processedvalue;
}paramdef_t;


#define TYPE_INT        TYPE_INT32
#define TYPE_UINT       TYPE_UINT32
#define TYPE_STRING     1
#define TYPE_INT8       2
#define TYPE_UINT8      3
#define TYPE_INT16      4
#define TYPE_UINT16     5
#define TYPE_INT32      6
#define TYPE_UINT32     7
#define TYPE_INT64      8
#define TYPE_UINT64     9
#define TYPE_MASK       10
#define TYPE_DOUBLE     16
#define TYPE_IPV4ADDR   20
#define TYPE_LASTSCALAR 25


#endif  /* CONFIG_PARAMDESC_H */
