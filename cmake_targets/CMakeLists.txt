# Author: zhenghuangkun

cmake_minimum_required (VERSION 3.0)

#############################################
# Base directories, compatible with legacy OAI building
################################################
set (OPENAIR_DIR     $ENV{OPENAIR_DIR})
set (OPENAIR1_DIR    ${OPENAIR_DIR}/openair1)
set (OPENAIR2_DIR    ${OPENAIR_DIR}/openair2)
set (OPENAIR3_DIR    ${OPENAIR_DIR}/openair3)
set (UTIL            ${OPENAIR_DIR}/UTIL)
set (ITTI            ${UTIL}/itti)
set (TELNET          ${UTIL}/telnet)
set (OPENAIR_TARGETS ${OPENAIR_DIR}/target)
set (OPENAIR_CMAKE   ${OPENAIR_DIR}/cmake_targets)
set (OPENAIR_BIN_DIR ${CMAKE_CURRENT_BINARY_DIR}${CMAKE_FILES_DIRECTORY})

###########################################
# macros to define options as there is numerous options in oai
################################################
macro(add_option name val helpstr)
  if(DEFINED ${name})
    set(value ${${name}})
  else(DEFINED ${name})
    set(value ${val})
  endif()
  set(${name} ${value} CACHE STRING "${helpstr}")
  add_definitions("-D${name}=${value}")
endmacro(add_option)

macro(add_boolean_option name val helpstr)
  if(DEFINED ${name})
    set(value ${${name}})
  else(DEFINED ${name})
    set(value ${val})
  endif()
  set(${name} ${value} CACHE STRING "${helpstr}")
  set_property(CACHE ${name} PROPERTY TYPE BOOL)
  if (${value})
    add_definitions("-D${name}")
  endif (${value})
endmacro(add_boolean_option)

macro(add_integer_option name val helpstr)
  if(DEFINED ${name})
    set(value ${${name}})
  else(DEFINED ${name})
    set(value ${val})
  endif()
  set(${name} ${value} CACHE STRING "${helpstr}")
  add_definitions("-D${name}=${value}")
endmacro(add_integer_option)

macro(add_list1_option name val helpstr)
  if(DEFINED ${name})
    set(value ${${name}})
  else(DEFINED ${name})
    set(value ${val})
  endif()
  set(${name} ${value} CACHE STRING "${helpstr}")
  set_property(CACHE ${name} PROPERTY STRINGS ${ARGN})
  if(NOT "${value}" STREQUAL "False")
    add_definitions("-D${name}=${value}")
  endif()
endmacro(add_list1_option)

macro(add_list2_option name val helpstr)
  if(DEFINED ${name})
    set(value ${${name}})
  else(DEFINED ${name})
    set(value ${val})
  endif()
  set(${name} ${value} CACHE STRING "${helpstr}")
  set_property(CACHE ${name} PROPERTY STRINGS ${ARGN})
  if(NOT "${value}" STREQUAL "False")
    add_definitions("-D${value}=1")
  endif()
endmacro(add_list2_option)

macro(add_list_string_option name val helpstr)
  if(DEFINED ${name})
    set(value ${${name}})
  else(DEFINED ${name})
    set(value ${val})
  endif()
  set(${name} ${value} CACHE STRING "${helpstr}")
  set_property(CACHE ${name} PROPERTY STRINGS ${ARGN})
  if(NOT "${value}" STREQUAL "False")
    add_definitions("-D${name}=\"${value}\"")
  endif()
endmacro(add_list_string_option)

function(make_version VERSION_VALUE)
  math(EXPR RESULT "0")
  foreach (ARG ${ARGN})
    math(EXPR RESULT "${RESULT} * 16 + ${ARG}")
  endforeach()
  set(${VERSION_VALUE} "${RESULT}" PARENT_SCOPE)
endfunction()
  
project (ThreadDemo)

Message("Architecture is ${CMAKE_SYSTEM_PROCESSOR}")

Message("OPENAIR_BIN_DIR is ${OPENAIR_BIN_DIR}")

set(C_FLAGS_PROCESSOR " ${C_FLAGS_PROCESSOR} ${CFLAGS_PROCESSOR_USER}")

#set(CMAKE_BUILD_TYPE "Debug")
if (CMAKE_BUILD_TYPE STREQUAL "")
   set(CMAKE_BUILD_TYPE "RelWithDebInfo")
endif()
message("CMAKE_BUILD_TYPE is ${CMAKE_BUILD_TYPE}")
add_list_string_option(CMAKE_BUILD_TYPE "RelWithDebInfo" "Choose the type of build, options are: None(CMAKE_CXX_FLAGS or CMAKE_C_FLAGS used) Debug Release RelWithDebInfo MinSizeRel." Debug Release RelWithDebInfo MinSizeRel)


Message("Architecture is ${CMAKE_SYSTEM_PROCESSOR}")
if (CMAKE_SYSTEM_PROCESSOR STREQUAL "armv7l")
  set(C_FLAGS_PROCESSOR "-gdwarf-2 -mfloat-abi=hard -mfpu=neon -lgcc -lrt")
else (CMAKE_SYSTEM_PROCESSOR STREQUAL "armv7l")
  if(EXISTS  "/proc/cpuinfo")
    file(STRINGS "/proc/cpuinfo" CPUINFO REGEX flags LIMIT_COUNT 1)
    if (CPUINFO MATCHES "avx2")
      set(C_FLAGS_PROCESSOR "${C_FLAGS_PROCESSOR} -mavx2")
      set(COMPILATION_AVX2 "True")
    else()
      set(COMPILATION_AVX2 "False")
    endif()
    if (CPUINFO MATCHES "sse4_1")
      set(C_FLAGS_PROCESSOR "${C_FLAGS_PROCESSOR} -msse4.1")
    endif()
    if (CPUINFO MATCHES "ssse3")
      set(C_FLAGS_PROCESSOR "${C_FLAGS_PROCESSOR} -mssse3")
    endif()
  else()
    Message("/proc/cpuinfo does not exit. We will use manual CPU flags")
  endif()
endif()

set(C_FLAGS_PROCESSOR " ${C_FLAGS_PROCESSOR} ${CFLAGS_PROCESSOR_USER}")

Message("C_FLAGS_PROCESSOR is ${C_FLAGS_PROCESSOR}")

add_definitions("-DASN_DISABLE_OER_SUPPORT")

set(CMAKE_C_FLAGS
  "${CMAKE_C_FLAGS} ${C_FLAGS_PROCESSOR} -pipe -std=gnu99 -Wall -Wstrict-prototypes -fno-strict-aliasing -rdynamic -funroll-loops -Wno-packed-bitfield-compat -fPIC"
  )

# add autotools definitions that were maybe used!

set(MKVER "'MAKE_VERSION(a,b,c)=((a)*256+(b)*16+c)'")
set(CMAKE_C_FLAGS
  "${CMAKE_C_FLAGS} -DSTDC_HEADERS=1 -DHAVE_SYS_TYPES_H=1 -DHAVE_SYS_STAT_H=1 -DHAVE_STDLIB_H=1 -DHAVE_STRING_H=1 -DHAVE_MEMORY_H=1 -DHAVE_STRINGS_H=1 -DHAVE_INTTYPES_H=1 -DHAVE_STDINT_H=1 -DHAVE_UNISTD_H=1 -DHAVE_FCNTL_H=1 -DHAVE_ARPA_INET_H=1 -DHAVE_SYS_TIME_H=1 -DHAVE_SYS_SOCKET_H=1 -DHAVE_STRERROR=1 -DHAVE_SOCKET=1 -DHAVE_MEMSET=1 -DHAVE_GETTIMEOFDAY=1 -DHAVE_STDLIB_H=1 -DHAVE_MALLOC=1 -DHAVE_LIBSCTP -D${MKVER}"
)
set(CMAKE_CXX_FLAGS 
  "${CMAKE_CXX_FLAGS} ${C_FLAGS_PROCESSOR} -Wno-packed-bitfield-compat -fPIC -Wall -fno-strict-aliasing -rdynamic -std=c++11 -D${MKVER}"
)

##############################
#set(CMAKE_C_FLAGS_RELWITHDEBINFO "-Wall -g3 -gdwarf-2 -O0")
set(CMAKE_C_FLAGS_DEBUG "${CMAKE_C_FLAGS} -g3 -DMALLOC_CHECK_=3") 
set(CMAKE_C_FLAGS_RELWITHDEBINFO "${CMAKE_C_FLAGS} -g3 -gdwarf-2 -DMALLOC_CHECK_=3 -O0") 
set(CMAKE_C_FLAGS_RELEASE "${CMAKE_C_FLAGS}  -O0")
##############################


# Debug related options 
#########################################
add_boolean_option(ASN_DEBUG           False "ASN1 coder/decoder Debug")
add_boolean_option(EMIT_ASN_DEBUG      False "ASN1 coder/decoder Debug")
add_boolean_option(MSG_PRINT           False "print debug messages")
add_boolean_option(DISABLE_XER_PRINT   False "print XER Format")
add_boolean_option(XER_PRINT           False "print XER Format")

################################################## 
# ASN.1 grammar C code generation & dependencies # 
##################################################
set(asn1_generated_dir ${OPENAIR_BIN_DIR})

set (S1AP_RELEASE R15)
set(S1AP_DIR ${OPENAIR3_DIR}/S1AP)
make_version(S1AP_VERSION 15 6 0)
set(S1AP_ASN_FILES "s1ap-15.6.0.asn1")

add_definitions(-DS1AP_VERSION=${S1AP_VERSION})
set(S1AP_ASN_DIR ${S1AP_DIR}/MESSAGES/ASN1/${S1AP_RELEASE})
set(S1AP_C_DIR ${asn1_generated_dir}/S1AP_${S1AP_RELEASE})

#message("COMMAND ${OPENAIR_CMAKE}/tools/make_asn1c_includes.sh "${NGAP_C_DIR}" "${NGAP_ASN_DIR}/${NGAP_ASN_FILES}" "S1AP_"")
# Warning: if you modify ASN.1 source file to generate new C files, cmake should be re-run instead of make 
execute_process(COMMAND ${OPENAIR_CMAKE}/tools/make_asn1c_includes.sh "${S1AP_C_DIR}" "${S1AP_ASN_DIR}/${S1AP_ASN_FILES}" "S1AP_" -fno-include-deps
				RESULT_VARIABLE ret)

if (NOT ${ret} STREQUAL 0)
  message(FATAL_ERROR "${ret}: error")
endif (NOT ${ret} STREQUAL 0)
file(GLOB S1AP_source ${S1AP_C_DIR}/*.c)

add_custom_target (
  s1ap_flag ALL
  ${OPENAIR_CMAKE}/tools/make_asn1c_includes.sh "${S1AP_C_DIR}" "${S1AP_ASN_DIR}/${S1AP_ASN_FILES}" "S1AP_" -fno-include-deps
  DEPENDS  "${S1AP_ASN_DIR}/${S1AP_ASN_FILES}"
)

set(UTIL_SRC 
  ${OPENAIR_DIR}/UTIL/log/log.c
)

include_directories("${OPENAIR_DIR}")
include_directories("${OPENAIR_DIR}/COMMON")
include_directories("${OPENAIR2_DIR}/COMMON")
include_directories("${OPENAIR3_DIR}/COMMON")
include_directories("${OPENAIR_TARGETS}/COMMON")

add_library(UTIL_LIB ${UTIL_SRC})

add_library(ITTI_LIB
  ${ITTI}/intertask_interface.cpp
)

add_library(TELNET_LIB
  ${TELNET}/telnetsrv.c
)

include_directories("${ITTI}")
include_directories("${UTIL}/assert")
include_directories("${UTIL}/log")
include_directories("${UTIL}/config")
include_directories("${UTIL}/telnet")

include_directories("${OPENAIR1_DIR}/L1")

set(L1_SRC
  ${OPENAIR1_DIR}/L1/l1_task.c
)
add_library(L1_LIB ${L1_SRC})

include_directories("${OPENAIR2_DIR}/L2")

set(L2_SRC
  ${OPENAIR2_DIR}/L2/l2_task.c
)
add_library(L2_LIB ${L2_SRC})


include_directories("${OPENAIR3_DIR}/L3")
set(L3_SRC
  ${OPENAIR3_DIR}/L3/l3_task.c
)
add_library(L3_LIB ${L3_SRC})

include_directories("${OPENAIR3_DIR}/SCTP")
set(SCTP_SRC
  ${OPENAIR3_DIR}/SCTP/sctp_common.c
  ${OPENAIR3_DIR}/SCTP/sctp_task.c
)
add_library(SCTP_CLIENT ${SCTP_SRC})

include_directories("${OPENAIR3_DIR}/TCP")
set(TCP_SRC
  ${OPENAIR3_DIR}/TCP/tcp_task.c
)
add_library(TCP_CLIENT ${TCP_SRC})

include_directories("${OPENAIR3_DIR}/UDP")
set(UDP_SRC
  ${OPENAIR3_DIR}/UDP/udp_task.c
)
add_library(UDP_CLIENT ${UDP_SRC})


# S1AP
##############################

include_directories("${S1AP_C_DIR}")
include_directories("${S1AP_DIR}")

add_library(S1AP_LIB
  ${S1AP_source}
  ${S1AP_DIR}/s1ap_common.c)
  
add_library(S1AP_APL
  ${S1AP_DIR}/s1ap_decoder.c
  ${S1AP_DIR}/s1ap_encoder.c
  ${S1AP_DIR}/s1ap_generate_message.c
  ${S1AP_DIR}/s1ap_itti_message.c
  ${S1AP_DIR}/s1ap_task.c)

add_dependencies(S1AP_LIB s1ap_flag)
  
# NGAP
##############################
set (NGAP_RELEASE R16)
set(NGAP_DIR ${OPENAIR3_DIR}/NGAP)
make_version(NGAP_VERSION 16 2 0)
set(NGAP_ASN_FILES "ngap-16.2.0.asn1")

add_definitions(-DNGAP_VERSION=${NGAP_VERSION})
set(NGAP_ASN_DIR ${NGAP_DIR}/MESSAGES/ASN1/${NGAP_RELEASE})
set(NGAP_C_DIR ${asn1_generated_dir}/NGAP_${NGAP_RELEASE})

#include_directories("${NGAP_C_DIR}")
include_directories("${NGAP_DIR}")

add_library(NGAP_APL
  ${NGAP_DIR}/ngap_decoder.c
  ${NGAP_DIR}/ngap_encoder.c
  ${NGAP_DIR}/ngap_generate_message.c
  ${NGAP_DIR}/ngap_task.c)
  
add_executable(threadDemo
  ${OPENAIR_TARGETS}/COMMON/create_tasks.c
  ${OPENAIR_TARGETS}/test.c
  )


target_link_libraries (threadDemo
  -Wl,--start-group
  pthread m sctp
  UTIL_LIB ITTI_LIB L1_LIB L2_LIB L3_LIB SCTP_CLIENT S1AP_LIB S1AP_APL NGAP_APL TCP_CLIENT UDP_CLIENT TELNET_LIB
  -Wl,--end-group z dl)
