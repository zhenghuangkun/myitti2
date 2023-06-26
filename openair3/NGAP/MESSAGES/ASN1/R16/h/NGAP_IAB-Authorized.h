/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/ngap-16.2.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/h`
 */

#ifndef	_NGAP_IAB_Authorized_H_
#define	_NGAP_IAB_Authorized_H_


#include <asn_application.h>

/* Including external dependencies */
#include <NativeEnumerated.h>

#ifdef __cplusplus
extern "C" {
#endif

/* Dependencies */
typedef enum NGAP_IAB_Authorized {
	NGAP_IAB_Authorized_authorized	= 0,
	NGAP_IAB_Authorized_not_authorized	= 1
	/*
	 * Enumeration is extensible
	 */
} e_NGAP_IAB_Authorized;

/* NGAP_IAB-Authorized */
typedef long	 NGAP_IAB_Authorized_t;

/* Implementation */
extern asn_TYPE_descriptor_t asn_DEF_NGAP_IAB_Authorized;
asn_struct_free_f NGAP_IAB_Authorized_free;
asn_struct_print_f NGAP_IAB_Authorized_print;
asn_constr_check_f NGAP_IAB_Authorized_constraint;
ber_type_decoder_f NGAP_IAB_Authorized_decode_ber;
der_type_encoder_f NGAP_IAB_Authorized_encode_der;
xer_type_decoder_f NGAP_IAB_Authorized_decode_xer;
xer_type_encoder_f NGAP_IAB_Authorized_encode_xer;
per_type_decoder_f NGAP_IAB_Authorized_decode_uper;
per_type_encoder_f NGAP_IAB_Authorized_encode_uper;
per_type_decoder_f NGAP_IAB_Authorized_decode_aper;
per_type_encoder_f NGAP_IAB_Authorized_encode_aper;

#ifdef __cplusplus
}
#endif

#endif	/* _NGAP_IAB_Authorized_H_ */
#include <asn_internal.h>
