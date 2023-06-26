/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/ngap-16.2.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/h`
 */

#include "NGAP_PLMNSupportItem.h"

#include "NGAP_ProtocolExtensionContainer.h"
asn_TYPE_member_t asn_MBR_NGAP_PLMNSupportItem_1[] = {
	{ ATF_NOFLAGS, 0, offsetof(struct NGAP_PLMNSupportItem, pLMNIdentity),
		(ASN_TAG_CLASS_CONTEXT | (0 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NGAP_PLMNIdentity,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"pLMNIdentity"
		},
	{ ATF_NOFLAGS, 0, offsetof(struct NGAP_PLMNSupportItem, sliceSupportList),
		(ASN_TAG_CLASS_CONTEXT | (1 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NGAP_SliceSupportList,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"sliceSupportList"
		},
	{ ATF_POINTER, 1, offsetof(struct NGAP_PLMNSupportItem, iE_Extensions),
		(ASN_TAG_CLASS_CONTEXT | (2 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NGAP_ProtocolExtensionContainer_9385P193,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"iE-Extensions"
		},
};
static const int asn_MAP_NGAP_PLMNSupportItem_oms_1[] = { 2 };
static const ber_tlv_tag_t asn_DEF_NGAP_PLMNSupportItem_tags_1[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
static const asn_TYPE_tag2member_t asn_MAP_NGAP_PLMNSupportItem_tag2el_1[] = {
    { (ASN_TAG_CLASS_CONTEXT | (0 << 2)), 0, 0, 0 }, /* pLMNIdentity */
    { (ASN_TAG_CLASS_CONTEXT | (1 << 2)), 1, 0, 0 }, /* sliceSupportList */
    { (ASN_TAG_CLASS_CONTEXT | (2 << 2)), 2, 0, 0 } /* iE-Extensions */
};
asn_SEQUENCE_specifics_t asn_SPC_NGAP_PLMNSupportItem_specs_1 = {
	sizeof(struct NGAP_PLMNSupportItem),
	offsetof(struct NGAP_PLMNSupportItem, _asn_ctx),
	asn_MAP_NGAP_PLMNSupportItem_tag2el_1,
	3,	/* Count of tags in the map */
	asn_MAP_NGAP_PLMNSupportItem_oms_1,	/* Optional members */
	1, 0,	/* Root/Additions */
	3,	/* First extension addition */
};
asn_TYPE_descriptor_t asn_DEF_NGAP_PLMNSupportItem = {
	"PLMNSupportItem",
	"PLMNSupportItem",
	&asn_OP_SEQUENCE,
	asn_DEF_NGAP_PLMNSupportItem_tags_1,
	sizeof(asn_DEF_NGAP_PLMNSupportItem_tags_1)
		/sizeof(asn_DEF_NGAP_PLMNSupportItem_tags_1[0]), /* 1 */
	asn_DEF_NGAP_PLMNSupportItem_tags_1,	/* Same as above */
	sizeof(asn_DEF_NGAP_PLMNSupportItem_tags_1)
		/sizeof(asn_DEF_NGAP_PLMNSupportItem_tags_1[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_NGAP_PLMNSupportItem_1,
	3,	/* Elements count */
	&asn_SPC_NGAP_PLMNSupportItem_specs_1	/* Additional specs */
};

