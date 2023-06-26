/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/ngap-16.2.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/h`
 */

#include "NGAP_WarningAreaCoordinates.h"

int
NGAP_WarningAreaCoordinates_constraint(const asn_TYPE_descriptor_t *td, const void *sptr,
			asn_app_constraint_failed_f *ctfailcb, void *app_key) {
	const OCTET_STRING_t *st = (const OCTET_STRING_t *)sptr;
	size_t size;
	
	if(!sptr) {
		ASN__CTFAIL(app_key, td, sptr,
			"%s: value not given (%s:%d)",
			td->name, __FILE__, __LINE__);
		return -1;
	}
	
	size = st->size;
	
	if((size >= 1 && size <= 1024)) {
		/* Constraint check succeeded */
		return 0;
	} else {
		ASN__CTFAIL(app_key, td, sptr,
			"%s: constraint failed (%s:%d)",
			td->name, __FILE__, __LINE__);
		return -1;
	}
}

/*
 * This type is implemented using OCTET_STRING,
 * so here we adjust the DEF accordingly.
 */
static asn_per_constraints_t asn_PER_type_NGAP_WarningAreaCoordinates_constr_1 CC_NOTUSED = {
	{ APC_UNCONSTRAINED,	-1, -1,  0,  0 },
	{ APC_CONSTRAINED,	 10,  10,  1,  1024 }	/* (SIZE(1..1024)) */,
	0, 0	/* No PER value map */
};
static const ber_tlv_tag_t asn_DEF_NGAP_WarningAreaCoordinates_tags_1[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (4 << 2))
};
asn_TYPE_descriptor_t asn_DEF_NGAP_WarningAreaCoordinates = {
	"WarningAreaCoordinates",
	"WarningAreaCoordinates",
	&asn_OP_OCTET_STRING,
	asn_DEF_NGAP_WarningAreaCoordinates_tags_1,
	sizeof(asn_DEF_NGAP_WarningAreaCoordinates_tags_1)
		/sizeof(asn_DEF_NGAP_WarningAreaCoordinates_tags_1[0]), /* 1 */
	asn_DEF_NGAP_WarningAreaCoordinates_tags_1,	/* Same as above */
	sizeof(asn_DEF_NGAP_WarningAreaCoordinates_tags_1)
		/sizeof(asn_DEF_NGAP_WarningAreaCoordinates_tags_1[0]), /* 1 */
	{ 0, &asn_PER_type_NGAP_WarningAreaCoordinates_constr_1, NGAP_WarningAreaCoordinates_constraint },
	0, 0,	/* No members */
	&asn_SPC_OCTET_STRING_specs	/* Additional specs */
};

