/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/ngap-16.2.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/h`
 */

#ifndef	_NGAP_LoggedMDTTrigger_H_
#define	_NGAP_LoggedMDTTrigger_H_


#include <asn_application.h>

/* Including external dependencies */
#include <NULL.h>
#include "NGAP_EventTrigger.h"
#include <constr_CHOICE.h>

#ifdef __cplusplus
extern "C" {
#endif

/* Dependencies */
typedef enum NGAP_LoggedMDTTrigger_PR {
	NGAP_LoggedMDTTrigger_PR_NOTHING,	/* No components present */
	NGAP_LoggedMDTTrigger_PR_periodical,
	NGAP_LoggedMDTTrigger_PR_eventTrigger
	/* Extensions may appear below */
	
} NGAP_LoggedMDTTrigger_PR;

/* NGAP_LoggedMDTTrigger */
typedef struct NGAP_LoggedMDTTrigger {
	NGAP_LoggedMDTTrigger_PR present;
	union NGAP_LoggedMDTTrigger_u {
		NULL_t	 periodical;
		NGAP_EventTrigger_t	 eventTrigger;
		/*
		 * This type is extensible,
		 * possible extensions are below.
		 */
	} choice;
	
	/* Context for parsing across buffer boundaries */
	asn_struct_ctx_t _asn_ctx;
} NGAP_LoggedMDTTrigger_t;

/* Implementation */
extern asn_TYPE_descriptor_t asn_DEF_NGAP_LoggedMDTTrigger;
extern asn_CHOICE_specifics_t asn_SPC_NGAP_LoggedMDTTrigger_specs_1;
extern asn_TYPE_member_t asn_MBR_NGAP_LoggedMDTTrigger_1[2];
extern asn_per_constraints_t asn_PER_type_NGAP_LoggedMDTTrigger_constr_1;

#ifdef __cplusplus
}
#endif

#endif	/* _NGAP_LoggedMDTTrigger_H_ */
#include <asn_internal.h>
