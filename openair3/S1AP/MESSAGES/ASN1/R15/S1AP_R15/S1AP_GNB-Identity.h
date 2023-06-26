/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "S1AP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/openair3/NGAP/MESSAGES/ASN1/R16/s1ap-15.6.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/build_dir/build/CMakeFiles/NGAP_R16`
 */

#ifndef	_S1AP_GNB_Identity_H_
#define	_S1AP_GNB_Identity_H_


#include <asn_application.h>

/* Including external dependencies */
#include "S1AP_GNB-ID.h"
#include <constr_CHOICE.h>

#ifdef __cplusplus
extern "C" {
#endif

/* Dependencies */
typedef enum S1AP_GNB_Identity_PR {
	S1AP_GNB_Identity_PR_NOTHING,	/* No components present */
	S1AP_GNB_Identity_PR_gNB_ID
	/* Extensions may appear below */
	
} S1AP_GNB_Identity_PR;

/* S1AP_GNB-Identity */
typedef struct S1AP_GNB_Identity {
	S1AP_GNB_Identity_PR present;
	union S1AP_GNB_Identity_u {
		S1AP_GNB_ID_t	 gNB_ID;
		/*
		 * This type is extensible,
		 * possible extensions are below.
		 */
	} choice;
	
	/* Context for parsing across buffer boundaries */
	asn_struct_ctx_t _asn_ctx;
} S1AP_GNB_Identity_t;

/* Implementation */
extern asn_TYPE_descriptor_t asn_DEF_S1AP_GNB_Identity;
extern asn_CHOICE_specifics_t asn_SPC_S1AP_GNB_Identity_specs_1;
extern asn_TYPE_member_t asn_MBR_S1AP_GNB_Identity_1[1];
extern asn_per_constraints_t asn_PER_type_S1AP_GNB_Identity_constr_1;

#ifdef __cplusplus
}
#endif

#endif	/* _S1AP_GNB_Identity_H_ */
#include <asn_internal.h>
