/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/ngap-16.2.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/h`
 */

#ifndef	_NGAP_DAPSRequestInfo_H_
#define	_NGAP_DAPSRequestInfo_H_


#include <asn_application.h>

/* Including external dependencies */
#include <NativeEnumerated.h>
#include <constr_SEQUENCE.h>

#ifdef __cplusplus
extern "C" {
#endif

/* Dependencies */
typedef enum NGAP_DAPSRequestInfo__dAPSIndicator {
	NGAP_DAPSRequestInfo__dAPSIndicator_daps_ho_required	= 0
	/*
	 * Enumeration is extensible
	 */
} e_NGAP_DAPSRequestInfo__dAPSIndicator;

/* Forward declarations */
struct NGAP_ProtocolExtensionContainer;

/* NGAP_DAPSRequestInfo */
typedef struct NGAP_DAPSRequestInfo {
	long	 dAPSIndicator;
	struct NGAP_ProtocolExtensionContainer	*iE_Extensions;	/* OPTIONAL */
	/*
	 * This type is extensible,
	 * possible extensions are below.
	 */
	
	/* Context for parsing across buffer boundaries */
	asn_struct_ctx_t _asn_ctx;
} NGAP_DAPSRequestInfo_t;

/* Implementation */
/* extern asn_TYPE_descriptor_t asn_DEF_NGAP_dAPSIndicator_2;	// (Use -fall-defs-global to expose) */
extern asn_TYPE_descriptor_t asn_DEF_NGAP_DAPSRequestInfo;

#ifdef __cplusplus
}
#endif

#endif	/* _NGAP_DAPSRequestInfo_H_ */
#include <asn_internal.h>
