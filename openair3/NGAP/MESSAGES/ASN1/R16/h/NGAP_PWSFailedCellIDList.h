/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/ngap-16.2.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/h`
 */

#ifndef	_NGAP_PWSFailedCellIDList_H_
#define	_NGAP_PWSFailedCellIDList_H_


#include <asn_application.h>

/* Including external dependencies */
#include "NGAP_EUTRA-CGIList.h"
#include "NGAP_NR-CGIList.h"
#include "NGAP_ProtocolIE-SingleContainer.h"
#include <constr_CHOICE.h>

#ifdef __cplusplus
extern "C" {
#endif

/* Dependencies */
typedef enum NGAP_PWSFailedCellIDList_PR {
	NGAP_PWSFailedCellIDList_PR_NOTHING,	/* No components present */
	NGAP_PWSFailedCellIDList_PR_eUTRA_CGI_PWSFailedList,
	NGAP_PWSFailedCellIDList_PR_nR_CGI_PWSFailedList,
	NGAP_PWSFailedCellIDList_PR_choice_Extensions
} NGAP_PWSFailedCellIDList_PR;

/* NGAP_PWSFailedCellIDList */
typedef struct NGAP_PWSFailedCellIDList {
	NGAP_PWSFailedCellIDList_PR present;
	union NGAP_PWSFailedCellIDList_u {
		NGAP_EUTRA_CGIList_t	 eUTRA_CGI_PWSFailedList;
		NGAP_NR_CGIList_t	 nR_CGI_PWSFailedList;
		NGAP_ProtocolIE_SingleContainer_9337P26_t	 choice_Extensions;
	} choice;
	
	/* Context for parsing across buffer boundaries */
	asn_struct_ctx_t _asn_ctx;
} NGAP_PWSFailedCellIDList_t;

/* Implementation */
extern asn_TYPE_descriptor_t asn_DEF_NGAP_PWSFailedCellIDList;

#ifdef __cplusplus
}
#endif

#endif	/* _NGAP_PWSFailedCellIDList_H_ */
#include <asn_internal.h>
