/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "S1AP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/openair3/NGAP/MESSAGES/ASN1/R16/s1ap-15.6.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/build_dir/build/CMakeFiles/NGAP_R16`
 */

#ifndef	_S1AP_Cell_Size_H_
#define	_S1AP_Cell_Size_H_


#include <asn_application.h>

/* Including external dependencies */
#include <NativeEnumerated.h>

#ifdef __cplusplus
extern "C" {
#endif

/* Dependencies */
typedef enum S1AP_Cell_Size {
	S1AP_Cell_Size_verysmall	= 0,
	S1AP_Cell_Size_small	= 1,
	S1AP_Cell_Size_medium	= 2,
	S1AP_Cell_Size_large	= 3
	/*
	 * Enumeration is extensible
	 */
} e_S1AP_Cell_Size;

/* S1AP_Cell-Size */
typedef long	 S1AP_Cell_Size_t;

/* Implementation */
extern asn_per_constraints_t asn_PER_type_S1AP_Cell_Size_constr_1;
extern asn_TYPE_descriptor_t asn_DEF_S1AP_Cell_Size;
extern const asn_INTEGER_specifics_t asn_SPC_S1AP_Cell_Size_specs_1;
asn_struct_free_f S1AP_Cell_Size_free;
asn_struct_print_f S1AP_Cell_Size_print;
asn_constr_check_f S1AP_Cell_Size_constraint;
ber_type_decoder_f S1AP_Cell_Size_decode_ber;
der_type_encoder_f S1AP_Cell_Size_encode_der;
xer_type_decoder_f S1AP_Cell_Size_decode_xer;
xer_type_encoder_f S1AP_Cell_Size_encode_xer;
per_type_decoder_f S1AP_Cell_Size_decode_uper;
per_type_encoder_f S1AP_Cell_Size_encode_uper;
per_type_decoder_f S1AP_Cell_Size_decode_aper;
per_type_encoder_f S1AP_Cell_Size_encode_aper;

#ifdef __cplusplus
}
#endif

#endif	/* _S1AP_Cell_Size_H_ */
#include <asn_internal.h>
