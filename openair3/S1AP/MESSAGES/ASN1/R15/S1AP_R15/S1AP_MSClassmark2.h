/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "S1AP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/openair3/NGAP/MESSAGES/ASN1/R16/s1ap-15.6.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/build_dir/build/CMakeFiles/NGAP_R16`
 */

#ifndef	_S1AP_MSClassmark2_H_
#define	_S1AP_MSClassmark2_H_


#include <asn_application.h>

/* Including external dependencies */
#include <OCTET_STRING.h>

#ifdef __cplusplus
extern "C" {
#endif

/* S1AP_MSClassmark2 */
typedef OCTET_STRING_t	 S1AP_MSClassmark2_t;

/* Implementation */
extern asn_TYPE_descriptor_t asn_DEF_S1AP_MSClassmark2;
asn_struct_free_f S1AP_MSClassmark2_free;
asn_struct_print_f S1AP_MSClassmark2_print;
asn_constr_check_f S1AP_MSClassmark2_constraint;
ber_type_decoder_f S1AP_MSClassmark2_decode_ber;
der_type_encoder_f S1AP_MSClassmark2_encode_der;
xer_type_decoder_f S1AP_MSClassmark2_decode_xer;
xer_type_encoder_f S1AP_MSClassmark2_encode_xer;
per_type_decoder_f S1AP_MSClassmark2_decode_uper;
per_type_encoder_f S1AP_MSClassmark2_encode_uper;
per_type_decoder_f S1AP_MSClassmark2_decode_aper;
per_type_encoder_f S1AP_MSClassmark2_encode_aper;

#ifdef __cplusplus
}
#endif

#endif	/* _S1AP_MSClassmark2_H_ */
#include <asn_internal.h>
