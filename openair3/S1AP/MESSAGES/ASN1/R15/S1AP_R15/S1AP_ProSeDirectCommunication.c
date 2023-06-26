/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "S1AP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/openair3/NGAP/MESSAGES/ASN1/R16/s1ap-15.6.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/build_dir/build/CMakeFiles/NGAP_R16`
 */

#include "S1AP_ProSeDirectCommunication.h"

/*
 * This type is implemented using NativeEnumerated,
 * so here we adjust the DEF accordingly.
 */
asn_per_constraints_t asn_PER_type_S1AP_ProSeDirectCommunication_constr_1 CC_NOTUSED = {
	{ APC_CONSTRAINED | APC_EXTENSIBLE,  1,  1,  0,  1 }	/* (0..1,...) */,
	{ APC_UNCONSTRAINED,	-1, -1,  0,  0 },
	0, 0	/* No PER value map */
};
static const asn_INTEGER_enum_map_t asn_MAP_S1AP_ProSeDirectCommunication_value2enum_1[] = {
	{ 0,	10,	"authorized" },
	{ 1,	14,	"not-authorized" }
	/* This list is extensible */
};
static const unsigned int asn_MAP_S1AP_ProSeDirectCommunication_enum2value_1[] = {
	0,	/* authorized(0) */
	1	/* not-authorized(1) */
	/* This list is extensible */
};
const asn_INTEGER_specifics_t asn_SPC_S1AP_ProSeDirectCommunication_specs_1 = {
	asn_MAP_S1AP_ProSeDirectCommunication_value2enum_1,	/* "tag" => N; sorted by tag */
	asn_MAP_S1AP_ProSeDirectCommunication_enum2value_1,	/* N => "tag"; sorted by N */
	2,	/* Number of elements in the maps */
	3,	/* Extensions before this member */
	1,	/* Strict enumeration */
	0,	/* Native long size */
	0
};
static const ber_tlv_tag_t asn_DEF_S1AP_ProSeDirectCommunication_tags_1[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (10 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProSeDirectCommunication = {
	"ProSeDirectCommunication",
	"ProSeDirectCommunication",
	&asn_OP_NativeEnumerated,
	asn_DEF_S1AP_ProSeDirectCommunication_tags_1,
	sizeof(asn_DEF_S1AP_ProSeDirectCommunication_tags_1)
		/sizeof(asn_DEF_S1AP_ProSeDirectCommunication_tags_1[0]), /* 1 */
	asn_DEF_S1AP_ProSeDirectCommunication_tags_1,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProSeDirectCommunication_tags_1)
		/sizeof(asn_DEF_S1AP_ProSeDirectCommunication_tags_1[0]), /* 1 */
	{ 0, &asn_PER_type_S1AP_ProSeDirectCommunication_constr_1, NativeEnumerated_constraint },
	0, 0,	/* Defined elsewhere */
	&asn_SPC_S1AP_ProSeDirectCommunication_specs_1	/* Additional specs */
};

