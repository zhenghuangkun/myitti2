/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/ngap-16.2.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/h`
 */

#ifndef	_NGAP_DAPSResponseInfoList_H_
#define	_NGAP_DAPSResponseInfoList_H_


#include <asn_application.h>

/* Including external dependencies */
#include <asn_SEQUENCE_OF.h>
#include <constr_SEQUENCE_OF.h>

#ifdef __cplusplus
extern "C" {
#endif

/* Forward declarations */
struct NGAP_DAPSResponseInfoItem;

/* NGAP_DAPSResponseInfoList */
typedef struct NGAP_DAPSResponseInfoList {
	A_SEQUENCE_OF(struct NGAP_DAPSResponseInfoItem) list;
	
	/* Context for parsing across buffer boundaries */
	asn_struct_ctx_t _asn_ctx;
} NGAP_DAPSResponseInfoList_t;

/* Implementation */
extern asn_TYPE_descriptor_t asn_DEF_NGAP_DAPSResponseInfoList;

#ifdef __cplusplus
}
#endif

#endif	/* _NGAP_DAPSResponseInfoList_H_ */
#include <asn_internal.h>