/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/ngap-16.2.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/h`
 */

#ifndef	_NGAP_TAIListForInactive_H_
#define	_NGAP_TAIListForInactive_H_


#include <asn_application.h>

/* Including external dependencies */
#include <asn_SEQUENCE_OF.h>
#include <constr_SEQUENCE_OF.h>

#ifdef __cplusplus
extern "C" {
#endif

/* Forward declarations */
struct NGAP_TAIListForInactiveItem;

/* NGAP_TAIListForInactive */
typedef struct NGAP_TAIListForInactive {
	A_SEQUENCE_OF(struct NGAP_TAIListForInactiveItem) list;
	
	/* Context for parsing across buffer boundaries */
	asn_struct_ctx_t _asn_ctx;
} NGAP_TAIListForInactive_t;

/* Implementation */
extern asn_TYPE_descriptor_t asn_DEF_NGAP_TAIListForInactive;
extern asn_SET_OF_specifics_t asn_SPC_NGAP_TAIListForInactive_specs_1;
extern asn_TYPE_member_t asn_MBR_NGAP_TAIListForInactive_1[1];
extern asn_per_constraints_t asn_PER_type_NGAP_TAIListForInactive_constr_1;

#ifdef __cplusplus
}
#endif

#endif	/* _NGAP_TAIListForInactive_H_ */
#include <asn_internal.h>
