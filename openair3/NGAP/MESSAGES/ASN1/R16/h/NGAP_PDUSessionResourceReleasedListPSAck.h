/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/ngap-16.2.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/h`
 */

#ifndef	_NGAP_PDUSessionResourceReleasedListPSAck_H_
#define	_NGAP_PDUSessionResourceReleasedListPSAck_H_


#include <asn_application.h>

/* Including external dependencies */
#include <asn_SEQUENCE_OF.h>
#include <constr_SEQUENCE_OF.h>

#ifdef __cplusplus
extern "C" {
#endif

/* Forward declarations */
struct NGAP_PDUSessionResourceReleasedItemPSAck;

/* NGAP_PDUSessionResourceReleasedListPSAck */
typedef struct NGAP_PDUSessionResourceReleasedListPSAck {
	A_SEQUENCE_OF(struct NGAP_PDUSessionResourceReleasedItemPSAck) list;
	
	/* Context for parsing across buffer boundaries */
	asn_struct_ctx_t _asn_ctx;
} NGAP_PDUSessionResourceReleasedListPSAck_t;

/* Implementation */
extern asn_TYPE_descriptor_t asn_DEF_NGAP_PDUSessionResourceReleasedListPSAck;

#ifdef __cplusplus
}
#endif

#endif	/* _NGAP_PDUSessionResourceReleasedListPSAck_H_ */
#include <asn_internal.h>
