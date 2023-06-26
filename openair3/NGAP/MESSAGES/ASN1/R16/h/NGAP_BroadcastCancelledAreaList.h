/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/ngap-16.2.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/h`
 */

#ifndef	_NGAP_BroadcastCancelledAreaList_H_
#define	_NGAP_BroadcastCancelledAreaList_H_


#include <asn_application.h>

/* Including external dependencies */
#include "NGAP_CellIDCancelledEUTRA.h"
#include "NGAP_TAICancelledEUTRA.h"
#include "NGAP_EmergencyAreaIDCancelledEUTRA.h"
#include "NGAP_CellIDCancelledNR.h"
#include "NGAP_TAICancelledNR.h"
#include "NGAP_EmergencyAreaIDCancelledNR.h"
#include "NGAP_ProtocolIE-SingleContainer.h"
#include <constr_CHOICE.h>

#ifdef __cplusplus
extern "C" {
#endif

/* Dependencies */
typedef enum NGAP_BroadcastCancelledAreaList_PR {
	NGAP_BroadcastCancelledAreaList_PR_NOTHING,	/* No components present */
	NGAP_BroadcastCancelledAreaList_PR_cellIDCancelledEUTRA,
	NGAP_BroadcastCancelledAreaList_PR_tAICancelledEUTRA,
	NGAP_BroadcastCancelledAreaList_PR_emergencyAreaIDCancelledEUTRA,
	NGAP_BroadcastCancelledAreaList_PR_cellIDCancelledNR,
	NGAP_BroadcastCancelledAreaList_PR_tAICancelledNR,
	NGAP_BroadcastCancelledAreaList_PR_emergencyAreaIDCancelledNR,
	NGAP_BroadcastCancelledAreaList_PR_choice_Extensions
} NGAP_BroadcastCancelledAreaList_PR;

/* NGAP_BroadcastCancelledAreaList */
typedef struct NGAP_BroadcastCancelledAreaList {
	NGAP_BroadcastCancelledAreaList_PR present;
	union NGAP_BroadcastCancelledAreaList_u {
		NGAP_CellIDCancelledEUTRA_t	 cellIDCancelledEUTRA;
		NGAP_TAICancelledEUTRA_t	 tAICancelledEUTRA;
		NGAP_EmergencyAreaIDCancelledEUTRA_t	 emergencyAreaIDCancelledEUTRA;
		NGAP_CellIDCancelledNR_t	 cellIDCancelledNR;
		NGAP_TAICancelledNR_t	 tAICancelledNR;
		NGAP_EmergencyAreaIDCancelledNR_t	 emergencyAreaIDCancelledNR;
		NGAP_ProtocolIE_SingleContainer_9337P1_t	 choice_Extensions;
	} choice;
	
	/* Context for parsing across buffer boundaries */
	asn_struct_ctx_t _asn_ctx;
} NGAP_BroadcastCancelledAreaList_t;

/* Implementation */
extern asn_TYPE_descriptor_t asn_DEF_NGAP_BroadcastCancelledAreaList;

#ifdef __cplusplus
}
#endif

#endif	/* _NGAP_BroadcastCancelledAreaList_H_ */
#include <asn_internal.h>
