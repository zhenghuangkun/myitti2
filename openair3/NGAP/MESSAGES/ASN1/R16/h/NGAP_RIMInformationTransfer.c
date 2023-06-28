/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/ngap-16.2.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/h`
 */

#include "NGAP_RIMInformationTransfer.h"

#include "NGAP_ProtocolExtensionContainer.h"
static asn_TYPE_member_t asn_MBR_NGAP_RIMInformationTransfer_1[] = {
	{ ATF_NOFLAGS, 0, offsetof(struct NGAP_RIMInformationTransfer, targetRANNodeID),
		(ASN_TAG_CLASS_CONTEXT | (0 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NGAP_TargetRANNodeID,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"targetRANNodeID"
		},
	{ ATF_NOFLAGS, 0, offsetof(struct NGAP_RIMInformationTransfer, sourceRANNodeID),
		(ASN_TAG_CLASS_CONTEXT | (1 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NGAP_SourceRANNodeID,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"sourceRANNodeID"
		},
	{ ATF_NOFLAGS, 0, offsetof(struct NGAP_RIMInformationTransfer, rIMInformation),
		(ASN_TAG_CLASS_CONTEXT | (2 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NGAP_RIMInformation,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"rIMInformation"
		},
	{ ATF_POINTER, 1, offsetof(struct NGAP_RIMInformationTransfer, iE_Extensions),
		(ASN_TAG_CLASS_CONTEXT | (3 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NGAP_ProtocolExtensionContainer_9385P216,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"iE-Extensions"
		},
};
static const int asn_MAP_NGAP_RIMInformationTransfer_oms_1[] = { 3 };
static const ber_tlv_tag_t asn_DEF_NGAP_RIMInformationTransfer_tags_1[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
static const asn_TYPE_tag2member_t asn_MAP_NGAP_RIMInformationTransfer_tag2el_1[] = {
    { (ASN_TAG_CLASS_CONTEXT | (0 << 2)), 0, 0, 0 }, /* targetRANNodeID */
    { (ASN_TAG_CLASS_CONTEXT | (1 << 2)), 1, 0, 0 }, /* sourceRANNodeID */
    { (ASN_TAG_CLASS_CONTEXT | (2 << 2)), 2, 0, 0 }, /* rIMInformation */
    { (ASN_TAG_CLASS_CONTEXT | (3 << 2)), 3, 0, 0 } /* iE-Extensions */
};
static asn_SEQUENCE_specifics_t asn_SPC_NGAP_RIMInformationTransfer_specs_1 = {
	sizeof(struct NGAP_RIMInformationTransfer),
	offsetof(struct NGAP_RIMInformationTransfer, _asn_ctx),
	asn_MAP_NGAP_RIMInformationTransfer_tag2el_1,
	4,	/* Count of tags in the map */
	asn_MAP_NGAP_RIMInformationTransfer_oms_1,	/* Optional members */
	1, 0,	/* Root/Additions */
	4,	/* First extension addition */
};
asn_TYPE_descriptor_t asn_DEF_NGAP_RIMInformationTransfer = {
	"RIMInformationTransfer",
	"RIMInformationTransfer",
	&asn_OP_SEQUENCE,
	asn_DEF_NGAP_RIMInformationTransfer_tags_1,
	sizeof(asn_DEF_NGAP_RIMInformationTransfer_tags_1)
		/sizeof(asn_DEF_NGAP_RIMInformationTransfer_tags_1[0]), /* 1 */
	asn_DEF_NGAP_RIMInformationTransfer_tags_1,	/* Same as above */
	sizeof(asn_DEF_NGAP_RIMInformationTransfer_tags_1)
		/sizeof(asn_DEF_NGAP_RIMInformationTransfer_tags_1[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_NGAP_RIMInformationTransfer_1,
	4,	/* Elements count */
	&asn_SPC_NGAP_RIMInformationTransfer_specs_1	/* Additional specs */
};
