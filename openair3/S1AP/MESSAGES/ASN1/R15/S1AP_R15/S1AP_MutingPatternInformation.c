/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "S1AP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/openair3/NGAP/MESSAGES/ASN1/R16/s1ap-15.6.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/build_dir/build/CMakeFiles/NGAP_R16`
 */

#include "S1AP_MutingPatternInformation.h"

#include "S1AP_ProtocolExtensionContainer.h"
/*
 * This type is implemented using NativeEnumerated,
 * so here we adjust the DEF accordingly.
 */
static int
memb_S1AP_muting_pattern_offset_constraint_1(const asn_TYPE_descriptor_t *td, const void *sptr,
			asn_app_constraint_failed_f *ctfailcb, void *app_key) {
	long value;
	
	if(!sptr) {
		ASN__CTFAIL(app_key, td, sptr,
			"%s: value not given (%s:%d)",
			td->name, __FILE__, __LINE__);
		return -1;
	}
	
	value = *(const long *)sptr;
	
	if((value >= 0 && value <= 10239)) {
		/* Constraint check succeeded */
		return 0;
	} else {
		ASN__CTFAIL(app_key, td, sptr,
			"%s: constraint failed (%s:%d)",
			td->name, __FILE__, __LINE__);
		return -1;
	}
}

static asn_per_constraints_t asn_PER_type_S1AP_muting_pattern_period_constr_2 CC_NOTUSED = {
	{ APC_CONSTRAINED | APC_EXTENSIBLE,  3,  3,  0,  4 }	/* (0..4,...) */,
	{ APC_UNCONSTRAINED,	-1, -1,  0,  0 },
	0, 0	/* No PER value map */
};
static asn_per_constraints_t asn_PER_memb_S1AP_muting_pattern_offset_constr_9 CC_NOTUSED = {
	{ APC_CONSTRAINED | APC_EXTENSIBLE,  14,  14,  0,  10239 }	/* (0..10239,...) */,
	{ APC_UNCONSTRAINED,	-1, -1,  0,  0 },
	0, 0	/* No PER value map */
};
static const asn_INTEGER_enum_map_t asn_MAP_S1AP_muting_pattern_period_value2enum_2[] = {
	{ 0,	3,	"ms0" },
	{ 1,	6,	"ms1280" },
	{ 2,	6,	"ms2560" },
	{ 3,	6,	"ms5120" },
	{ 4,	7,	"ms10240" }
	/* This list is extensible */
};
static const unsigned int asn_MAP_S1AP_muting_pattern_period_enum2value_2[] = {
	0,	/* ms0(0) */
	4,	/* ms10240(4) */
	1,	/* ms1280(1) */
	2,	/* ms2560(2) */
	3	/* ms5120(3) */
	/* This list is extensible */
};
static const asn_INTEGER_specifics_t asn_SPC_S1AP_muting_pattern_period_specs_2 = {
	asn_MAP_S1AP_muting_pattern_period_value2enum_2,	/* "tag" => N; sorted by tag */
	asn_MAP_S1AP_muting_pattern_period_enum2value_2,	/* N => "tag"; sorted by N */
	5,	/* Number of elements in the maps */
	6,	/* Extensions before this member */
	1,	/* Strict enumeration */
	0,	/* Native long size */
	0
};
static const ber_tlv_tag_t asn_DEF_S1AP_muting_pattern_period_tags_2[] = {
	(ASN_TAG_CLASS_CONTEXT | (0 << 2)),
	(ASN_TAG_CLASS_UNIVERSAL | (10 << 2))
};
static /* Use -fall-defs-global to expose */
asn_TYPE_descriptor_t asn_DEF_S1AP_muting_pattern_period_2 = {
	"muting-pattern-period",
	"muting-pattern-period",
	&asn_OP_NativeEnumerated,
	asn_DEF_S1AP_muting_pattern_period_tags_2,
	sizeof(asn_DEF_S1AP_muting_pattern_period_tags_2)
		/sizeof(asn_DEF_S1AP_muting_pattern_period_tags_2[0]) - 1, /* 1 */
	asn_DEF_S1AP_muting_pattern_period_tags_2,	/* Same as above */
	sizeof(asn_DEF_S1AP_muting_pattern_period_tags_2)
		/sizeof(asn_DEF_S1AP_muting_pattern_period_tags_2[0]), /* 2 */
	{ 0, &asn_PER_type_S1AP_muting_pattern_period_constr_2, NativeEnumerated_constraint },
	0, 0,	/* Defined elsewhere */
	&asn_SPC_S1AP_muting_pattern_period_specs_2	/* Additional specs */
};

static asn_TYPE_member_t asn_MBR_S1AP_MutingPatternInformation_1[] = {
	{ ATF_NOFLAGS, 0, offsetof(struct S1AP_MutingPatternInformation, muting_pattern_period),
		(ASN_TAG_CLASS_CONTEXT | (0 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_S1AP_muting_pattern_period_2,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"muting-pattern-period"
		},
	{ ATF_POINTER, 2, offsetof(struct S1AP_MutingPatternInformation, muting_pattern_offset),
		(ASN_TAG_CLASS_CONTEXT | (1 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NativeInteger,
		0,
		{ 0, &asn_PER_memb_S1AP_muting_pattern_offset_constr_9,  memb_S1AP_muting_pattern_offset_constraint_1 },
		0, 0, /* No default value */
		"muting-pattern-offset"
		},
	{ ATF_POINTER, 1, offsetof(struct S1AP_MutingPatternInformation, iE_Extensions),
		(ASN_TAG_CLASS_CONTEXT | (2 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_S1AP_ProtocolExtensionContainer_7327P86,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"iE-Extensions"
		},
};
static const int asn_MAP_S1AP_MutingPatternInformation_oms_1[] = { 1, 2 };
static const ber_tlv_tag_t asn_DEF_S1AP_MutingPatternInformation_tags_1[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
static const asn_TYPE_tag2member_t asn_MAP_S1AP_MutingPatternInformation_tag2el_1[] = {
    { (ASN_TAG_CLASS_CONTEXT | (0 << 2)), 0, 0, 0 }, /* muting-pattern-period */
    { (ASN_TAG_CLASS_CONTEXT | (1 << 2)), 1, 0, 0 }, /* muting-pattern-offset */
    { (ASN_TAG_CLASS_CONTEXT | (2 << 2)), 2, 0, 0 } /* iE-Extensions */
};
static asn_SEQUENCE_specifics_t asn_SPC_S1AP_MutingPatternInformation_specs_1 = {
	sizeof(struct S1AP_MutingPatternInformation),
	offsetof(struct S1AP_MutingPatternInformation, _asn_ctx),
	asn_MAP_S1AP_MutingPatternInformation_tag2el_1,
	3,	/* Count of tags in the map */
	asn_MAP_S1AP_MutingPatternInformation_oms_1,	/* Optional members */
	2, 0,	/* Root/Additions */
	3,	/* First extension addition */
};
asn_TYPE_descriptor_t asn_DEF_S1AP_MutingPatternInformation = {
	"MutingPatternInformation",
	"MutingPatternInformation",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_MutingPatternInformation_tags_1,
	sizeof(asn_DEF_S1AP_MutingPatternInformation_tags_1)
		/sizeof(asn_DEF_S1AP_MutingPatternInformation_tags_1[0]), /* 1 */
	asn_DEF_S1AP_MutingPatternInformation_tags_1,	/* Same as above */
	sizeof(asn_DEF_S1AP_MutingPatternInformation_tags_1)
		/sizeof(asn_DEF_S1AP_MutingPatternInformation_tags_1[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_MutingPatternInformation_1,
	3,	/* Elements count */
	&asn_SPC_S1AP_MutingPatternInformation_specs_1	/* Additional specs */
};

