/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/ngap-16.2.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/h`
 */

#include "NGAP_CauseMisc.h"

/*
 * This type is implemented using NativeEnumerated,
 * so here we adjust the DEF accordingly.
 */
asn_per_constraints_t asn_PER_type_NGAP_CauseMisc_constr_1 CC_NOTUSED = {
	{ APC_CONSTRAINED | APC_EXTENSIBLE,  3,  3,  0,  5 }	/* (0..5,...) */,
	{ APC_UNCONSTRAINED,	-1, -1,  0,  0 },
	0, 0	/* No PER value map */
};
static const asn_INTEGER_enum_map_t asn_MAP_NGAP_CauseMisc_value2enum_1[] = {
	{ 0,	27,	"control-processing-overload" },
	{ 1,	42,	"not-enough-user-plane-processing-resources" },
	{ 2,	16,	"hardware-failure" },
	{ 3,	15,	"om-intervention" },
	{ 4,	12,	"unknown-PLMN" },
	{ 5,	11,	"unspecified" }
	/* This list is extensible */
};
static const unsigned int asn_MAP_NGAP_CauseMisc_enum2value_1[] = {
	0,	/* control-processing-overload(0) */
	2,	/* hardware-failure(2) */
	1,	/* not-enough-user-plane-processing-resources(1) */
	3,	/* om-intervention(3) */
	4,	/* unknown-PLMN(4) */
	5	/* unspecified(5) */
	/* This list is extensible */
};
const asn_INTEGER_specifics_t asn_SPC_NGAP_CauseMisc_specs_1 = {
	asn_MAP_NGAP_CauseMisc_value2enum_1,	/* "tag" => N; sorted by tag */
	asn_MAP_NGAP_CauseMisc_enum2value_1,	/* N => "tag"; sorted by N */
	6,	/* Number of elements in the maps */
	7,	/* Extensions before this member */
	1,	/* Strict enumeration */
	0,	/* Native long size */
	0
};
static const ber_tlv_tag_t asn_DEF_NGAP_CauseMisc_tags_1[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (10 << 2))
};
asn_TYPE_descriptor_t asn_DEF_NGAP_CauseMisc = {
	"CauseMisc",
	"CauseMisc",
	&asn_OP_NativeEnumerated,
	asn_DEF_NGAP_CauseMisc_tags_1,
	sizeof(asn_DEF_NGAP_CauseMisc_tags_1)
		/sizeof(asn_DEF_NGAP_CauseMisc_tags_1[0]), /* 1 */
	asn_DEF_NGAP_CauseMisc_tags_1,	/* Same as above */
	sizeof(asn_DEF_NGAP_CauseMisc_tags_1)
		/sizeof(asn_DEF_NGAP_CauseMisc_tags_1[0]), /* 1 */
	{ 0, &asn_PER_type_NGAP_CauseMisc_constr_1, NativeEnumerated_constraint },
	0, 0,	/* Defined elsewhere */
	&asn_SPC_NGAP_CauseMisc_specs_1	/* Additional specs */
};

