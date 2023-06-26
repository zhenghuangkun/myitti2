/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/ngap-16.2.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/h`
 */

#ifndef	_NGAP_UE_UP_CIoT_Support_H_
#define	_NGAP_UE_UP_CIoT_Support_H_


#include <asn_application.h>

/* Including external dependencies */
#include <NativeEnumerated.h>

#ifdef __cplusplus
extern "C" {
#endif

/* Dependencies */
typedef enum NGAP_UE_UP_CIoT_Support {
	NGAP_UE_UP_CIoT_Support_supported	= 0
	/*
	 * Enumeration is extensible
	 */
} e_NGAP_UE_UP_CIoT_Support;

/* NGAP_UE-UP-CIoT-Support */
typedef long	 NGAP_UE_UP_CIoT_Support_t;

/* Implementation */
extern asn_TYPE_descriptor_t asn_DEF_NGAP_UE_UP_CIoT_Support;
asn_struct_free_f NGAP_UE_UP_CIoT_Support_free;
asn_struct_print_f NGAP_UE_UP_CIoT_Support_print;
asn_constr_check_f NGAP_UE_UP_CIoT_Support_constraint;
ber_type_decoder_f NGAP_UE_UP_CIoT_Support_decode_ber;
der_type_encoder_f NGAP_UE_UP_CIoT_Support_encode_der;
xer_type_decoder_f NGAP_UE_UP_CIoT_Support_decode_xer;
xer_type_encoder_f NGAP_UE_UP_CIoT_Support_encode_xer;
per_type_decoder_f NGAP_UE_UP_CIoT_Support_decode_uper;
per_type_encoder_f NGAP_UE_UP_CIoT_Support_encode_uper;
per_type_decoder_f NGAP_UE_UP_CIoT_Support_decode_aper;
per_type_encoder_f NGAP_UE_UP_CIoT_Support_encode_aper;

#ifdef __cplusplus
}
#endif

#endif	/* _NGAP_UE_UP_CIoT_Support_H_ */
#include <asn_internal.h>