/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-PDU-Contents"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/ngap-16.2.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/h`
 */

#ifndef	_NGAP_PDUSessionResourceModifyConfirm_H_
#define	_NGAP_PDUSessionResourceModifyConfirm_H_


#include <asn_application.h>

/* Including external dependencies */
#include "NGAP_ProtocolIE-Container.h"
#include <constr_SEQUENCE.h>

#ifdef __cplusplus
extern "C" {
#endif

/* NGAP_PDUSessionResourceModifyConfirm */
typedef struct NGAP_PDUSessionResourceModifyConfirm {
	NGAP_ProtocolIE_Container_9334P8_t	 protocolIEs;
	/*
	 * This type is extensible,
	 * possible extensions are below.
	 */
	
	/* Context for parsing across buffer boundaries */
	asn_struct_ctx_t _asn_ctx;
} NGAP_PDUSessionResourceModifyConfirm_t;

/* Implementation */
extern asn_TYPE_descriptor_t asn_DEF_NGAP_PDUSessionResourceModifyConfirm;
extern asn_SEQUENCE_specifics_t asn_SPC_NGAP_PDUSessionResourceModifyConfirm_specs_1;
extern asn_TYPE_member_t asn_MBR_NGAP_PDUSessionResourceModifyConfirm_1[1];

#ifdef __cplusplus
}
#endif

#endif	/* _NGAP_PDUSessionResourceModifyConfirm_H_ */
#include <asn_internal.h>
