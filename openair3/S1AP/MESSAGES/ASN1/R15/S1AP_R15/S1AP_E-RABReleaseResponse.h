/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "S1AP-PDU-Contents"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/openair3/NGAP/MESSAGES/ASN1/R16/s1ap-15.6.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/build_dir/build/CMakeFiles/NGAP_R16`
 */

#ifndef	_S1AP_E_RABReleaseResponse_H_
#define	_S1AP_E_RABReleaseResponse_H_


#include <asn_application.h>

/* Including external dependencies */
#include "S1AP_ProtocolIE-Container.h"
#include <constr_SEQUENCE.h>

#ifdef __cplusplus
extern "C" {
#endif

/* S1AP_E-RABReleaseResponse */
typedef struct S1AP_E_RABReleaseResponse {
	S1AP_ProtocolIE_Container_7276P17_t	 protocolIEs;
	/*
	 * This type is extensible,
	 * possible extensions are below.
	 */
	
	/* Context for parsing across buffer boundaries */
	asn_struct_ctx_t _asn_ctx;
} S1AP_E_RABReleaseResponse_t;

/* Implementation */
extern asn_TYPE_descriptor_t asn_DEF_S1AP_E_RABReleaseResponse;
extern asn_SEQUENCE_specifics_t asn_SPC_S1AP_E_RABReleaseResponse_specs_1;
extern asn_TYPE_member_t asn_MBR_S1AP_E_RABReleaseResponse_1[1];

#ifdef __cplusplus
}
#endif

#endif	/* _S1AP_E_RABReleaseResponse_H_ */
#include <asn_internal.h>
