/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "S1AP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/openair3/NGAP/MESSAGES/ASN1/R16/s1ap-15.6.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/build_dir/build/CMakeFiles/NGAP_R16`
 */

#include "S1AP_LoggedMBSFNMDT.h"

#include "S1AP_MBSFN-ResultToLog.h"
#include "S1AP_ProtocolExtensionContainer.h"
static asn_TYPE_member_t asn_MBR_S1AP_LoggedMBSFNMDT_1[] = {
	{ ATF_NOFLAGS, 0, offsetof(struct S1AP_LoggedMBSFNMDT, loggingInterval),
		(ASN_TAG_CLASS_CONTEXT | (0 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_S1AP_LoggingInterval,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"loggingInterval"
		},
	{ ATF_NOFLAGS, 0, offsetof(struct S1AP_LoggedMBSFNMDT, loggingDuration),
		(ASN_TAG_CLASS_CONTEXT | (1 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_S1AP_LoggingDuration,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"loggingDuration"
		},
	{ ATF_POINTER, 2, offsetof(struct S1AP_LoggedMBSFNMDT, mBSFN_ResultToLog),
		(ASN_TAG_CLASS_CONTEXT | (2 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_S1AP_MBSFN_ResultToLog,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"mBSFN-ResultToLog"
		},
	{ ATF_POINTER, 1, offsetof(struct S1AP_LoggedMBSFNMDT, iE_Extensions),
		(ASN_TAG_CLASS_CONTEXT | (3 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_S1AP_ProtocolExtensionContainer_7327P78,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"iE-Extensions"
		},
};
static const int asn_MAP_S1AP_LoggedMBSFNMDT_oms_1[] = { 2, 3 };
static const ber_tlv_tag_t asn_DEF_S1AP_LoggedMBSFNMDT_tags_1[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
static const asn_TYPE_tag2member_t asn_MAP_S1AP_LoggedMBSFNMDT_tag2el_1[] = {
    { (ASN_TAG_CLASS_CONTEXT | (0 << 2)), 0, 0, 0 }, /* loggingInterval */
    { (ASN_TAG_CLASS_CONTEXT | (1 << 2)), 1, 0, 0 }, /* loggingDuration */
    { (ASN_TAG_CLASS_CONTEXT | (2 << 2)), 2, 0, 0 }, /* mBSFN-ResultToLog */
    { (ASN_TAG_CLASS_CONTEXT | (3 << 2)), 3, 0, 0 } /* iE-Extensions */
};
static asn_SEQUENCE_specifics_t asn_SPC_S1AP_LoggedMBSFNMDT_specs_1 = {
	sizeof(struct S1AP_LoggedMBSFNMDT),
	offsetof(struct S1AP_LoggedMBSFNMDT, _asn_ctx),
	asn_MAP_S1AP_LoggedMBSFNMDT_tag2el_1,
	4,	/* Count of tags in the map */
	asn_MAP_S1AP_LoggedMBSFNMDT_oms_1,	/* Optional members */
	2, 0,	/* Root/Additions */
	4,	/* First extension addition */
};
asn_TYPE_descriptor_t asn_DEF_S1AP_LoggedMBSFNMDT = {
	"LoggedMBSFNMDT",
	"LoggedMBSFNMDT",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_LoggedMBSFNMDT_tags_1,
	sizeof(asn_DEF_S1AP_LoggedMBSFNMDT_tags_1)
		/sizeof(asn_DEF_S1AP_LoggedMBSFNMDT_tags_1[0]), /* 1 */
	asn_DEF_S1AP_LoggedMBSFNMDT_tags_1,	/* Same as above */
	sizeof(asn_DEF_S1AP_LoggedMBSFNMDT_tags_1)
		/sizeof(asn_DEF_S1AP_LoggedMBSFNMDT_tags_1[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_LoggedMBSFNMDT_1,
	4,	/* Elements count */
	&asn_SPC_S1AP_LoggedMBSFNMDT_specs_1	/* Additional specs */
};

