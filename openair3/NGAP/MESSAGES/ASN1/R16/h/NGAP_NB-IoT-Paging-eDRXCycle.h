/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/ngap-16.2.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/h`
 */

#ifndef	_NGAP_NB_IoT_Paging_eDRXCycle_H_
#define	_NGAP_NB_IoT_Paging_eDRXCycle_H_


#include <asn_application.h>

/* Including external dependencies */
#include <NativeEnumerated.h>

#ifdef __cplusplus
extern "C" {
#endif

/* Dependencies */
typedef enum NGAP_NB_IoT_Paging_eDRXCycle {
	NGAP_NB_IoT_Paging_eDRXCycle_hf2	= 0,
	NGAP_NB_IoT_Paging_eDRXCycle_hf4	= 1,
	NGAP_NB_IoT_Paging_eDRXCycle_hf6	= 2,
	NGAP_NB_IoT_Paging_eDRXCycle_hf8	= 3,
	NGAP_NB_IoT_Paging_eDRXCycle_hf10	= 4,
	NGAP_NB_IoT_Paging_eDRXCycle_hf12	= 5,
	NGAP_NB_IoT_Paging_eDRXCycle_hf14	= 6,
	NGAP_NB_IoT_Paging_eDRXCycle_hf16	= 7,
	NGAP_NB_IoT_Paging_eDRXCycle_hf32	= 8,
	NGAP_NB_IoT_Paging_eDRXCycle_hf64	= 9,
	NGAP_NB_IoT_Paging_eDRXCycle_hf128	= 10,
	NGAP_NB_IoT_Paging_eDRXCycle_hf256	= 11,
	NGAP_NB_IoT_Paging_eDRXCycle_hf512	= 12,
	NGAP_NB_IoT_Paging_eDRXCycle_hf1024	= 13
	/*
	 * Enumeration is extensible
	 */
} e_NGAP_NB_IoT_Paging_eDRXCycle;

/* NGAP_NB-IoT-Paging-eDRXCycle */
typedef long	 NGAP_NB_IoT_Paging_eDRXCycle_t;

/* Implementation */
extern asn_per_constraints_t asn_PER_type_NGAP_NB_IoT_Paging_eDRXCycle_constr_1;
extern asn_TYPE_descriptor_t asn_DEF_NGAP_NB_IoT_Paging_eDRXCycle;
extern const asn_INTEGER_specifics_t asn_SPC_NGAP_NB_IoT_Paging_eDRXCycle_specs_1;
asn_struct_free_f NGAP_NB_IoT_Paging_eDRXCycle_free;
asn_struct_print_f NGAP_NB_IoT_Paging_eDRXCycle_print;
asn_constr_check_f NGAP_NB_IoT_Paging_eDRXCycle_constraint;
ber_type_decoder_f NGAP_NB_IoT_Paging_eDRXCycle_decode_ber;
der_type_encoder_f NGAP_NB_IoT_Paging_eDRXCycle_encode_der;
xer_type_decoder_f NGAP_NB_IoT_Paging_eDRXCycle_decode_xer;
xer_type_encoder_f NGAP_NB_IoT_Paging_eDRXCycle_encode_xer;
per_type_decoder_f NGAP_NB_IoT_Paging_eDRXCycle_decode_uper;
per_type_encoder_f NGAP_NB_IoT_Paging_eDRXCycle_encode_uper;
per_type_decoder_f NGAP_NB_IoT_Paging_eDRXCycle_decode_aper;
per_type_encoder_f NGAP_NB_IoT_Paging_eDRXCycle_encode_aper;

#ifdef __cplusplus
}
#endif

#endif	/* _NGAP_NB_IoT_Paging_eDRXCycle_H_ */
#include <asn_internal.h>
