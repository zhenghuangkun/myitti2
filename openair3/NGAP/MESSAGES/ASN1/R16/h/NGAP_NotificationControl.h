/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/ngap-16.2.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/h`
 */

#ifndef	_NGAP_NotificationControl_H_
#define	_NGAP_NotificationControl_H_


#include <asn_application.h>

/* Including external dependencies */
#include <NativeEnumerated.h>

#ifdef __cplusplus
extern "C" {
#endif

/* Dependencies */
typedef enum NGAP_NotificationControl {
	NGAP_NotificationControl_notification_requested	= 0
	/*
	 * Enumeration is extensible
	 */
} e_NGAP_NotificationControl;

/* NGAP_NotificationControl */
typedef long	 NGAP_NotificationControl_t;

/* Implementation */
extern asn_per_constraints_t asn_PER_type_NGAP_NotificationControl_constr_1;
extern asn_TYPE_descriptor_t asn_DEF_NGAP_NotificationControl;
extern const asn_INTEGER_specifics_t asn_SPC_NGAP_NotificationControl_specs_1;
asn_struct_free_f NGAP_NotificationControl_free;
asn_struct_print_f NGAP_NotificationControl_print;
asn_constr_check_f NGAP_NotificationControl_constraint;
ber_type_decoder_f NGAP_NotificationControl_decode_ber;
der_type_encoder_f NGAP_NotificationControl_encode_der;
xer_type_decoder_f NGAP_NotificationControl_decode_xer;
xer_type_encoder_f NGAP_NotificationControl_encode_xer;
per_type_decoder_f NGAP_NotificationControl_decode_uper;
per_type_encoder_f NGAP_NotificationControl_encode_uper;
per_type_decoder_f NGAP_NotificationControl_decode_aper;
per_type_encoder_f NGAP_NotificationControl_encode_aper;

#ifdef __cplusplus
}
#endif

#endif	/* _NGAP_NotificationControl_H_ */
#include <asn_internal.h>
