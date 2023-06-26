/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/ngap-16.2.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/h`
 */

#include "NGAP_AlternativeQoSParaSetItem.h"

#include "NGAP_PacketErrorRate.h"
#include "NGAP_ProtocolExtensionContainer.h"
asn_TYPE_member_t asn_MBR_NGAP_AlternativeQoSParaSetItem_1[] = {
	{ ATF_NOFLAGS, 0, offsetof(struct NGAP_AlternativeQoSParaSetItem, alternativeQoSParaSetIndex),
		(ASN_TAG_CLASS_CONTEXT | (0 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NGAP_AlternativeQoSParaSetIndex,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"alternativeQoSParaSetIndex"
		},
	{ ATF_POINTER, 5, offsetof(struct NGAP_AlternativeQoSParaSetItem, guaranteedFlowBitRateDL),
		(ASN_TAG_CLASS_CONTEXT | (1 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NGAP_BitRate,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"guaranteedFlowBitRateDL"
		},
	{ ATF_POINTER, 4, offsetof(struct NGAP_AlternativeQoSParaSetItem, guaranteedFlowBitRateUL),
		(ASN_TAG_CLASS_CONTEXT | (2 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NGAP_BitRate,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"guaranteedFlowBitRateUL"
		},
	{ ATF_POINTER, 3, offsetof(struct NGAP_AlternativeQoSParaSetItem, packetDelayBudget),
		(ASN_TAG_CLASS_CONTEXT | (3 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NGAP_PacketDelayBudget,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"packetDelayBudget"
		},
	{ ATF_POINTER, 2, offsetof(struct NGAP_AlternativeQoSParaSetItem, packetErrorRate),
		(ASN_TAG_CLASS_CONTEXT | (4 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NGAP_PacketErrorRate,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"packetErrorRate"
		},
	{ ATF_POINTER, 1, offsetof(struct NGAP_AlternativeQoSParaSetItem, iE_Extensions),
		(ASN_TAG_CLASS_CONTEXT | (5 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NGAP_ProtocolExtensionContainer_9385P4,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"iE-Extensions"
		},
};
static const int asn_MAP_NGAP_AlternativeQoSParaSetItem_oms_1[] = { 1, 2, 3, 4, 5 };
static const ber_tlv_tag_t asn_DEF_NGAP_AlternativeQoSParaSetItem_tags_1[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
static const asn_TYPE_tag2member_t asn_MAP_NGAP_AlternativeQoSParaSetItem_tag2el_1[] = {
    { (ASN_TAG_CLASS_CONTEXT | (0 << 2)), 0, 0, 0 }, /* alternativeQoSParaSetIndex */
    { (ASN_TAG_CLASS_CONTEXT | (1 << 2)), 1, 0, 0 }, /* guaranteedFlowBitRateDL */
    { (ASN_TAG_CLASS_CONTEXT | (2 << 2)), 2, 0, 0 }, /* guaranteedFlowBitRateUL */
    { (ASN_TAG_CLASS_CONTEXT | (3 << 2)), 3, 0, 0 }, /* packetDelayBudget */
    { (ASN_TAG_CLASS_CONTEXT | (4 << 2)), 4, 0, 0 }, /* packetErrorRate */
    { (ASN_TAG_CLASS_CONTEXT | (5 << 2)), 5, 0, 0 } /* iE-Extensions */
};
asn_SEQUENCE_specifics_t asn_SPC_NGAP_AlternativeQoSParaSetItem_specs_1 = {
	sizeof(struct NGAP_AlternativeQoSParaSetItem),
	offsetof(struct NGAP_AlternativeQoSParaSetItem, _asn_ctx),
	asn_MAP_NGAP_AlternativeQoSParaSetItem_tag2el_1,
	6,	/* Count of tags in the map */
	asn_MAP_NGAP_AlternativeQoSParaSetItem_oms_1,	/* Optional members */
	5, 0,	/* Root/Additions */
	6,	/* First extension addition */
};
asn_TYPE_descriptor_t asn_DEF_NGAP_AlternativeQoSParaSetItem = {
	"AlternativeQoSParaSetItem",
	"AlternativeQoSParaSetItem",
	&asn_OP_SEQUENCE,
	asn_DEF_NGAP_AlternativeQoSParaSetItem_tags_1,
	sizeof(asn_DEF_NGAP_AlternativeQoSParaSetItem_tags_1)
		/sizeof(asn_DEF_NGAP_AlternativeQoSParaSetItem_tags_1[0]), /* 1 */
	asn_DEF_NGAP_AlternativeQoSParaSetItem_tags_1,	/* Same as above */
	sizeof(asn_DEF_NGAP_AlternativeQoSParaSetItem_tags_1)
		/sizeof(asn_DEF_NGAP_AlternativeQoSParaSetItem_tags_1[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_NGAP_AlternativeQoSParaSetItem_1,
	6,	/* Elements count */
	&asn_SPC_NGAP_AlternativeQoSParaSetItem_specs_1	/* Additional specs */
};

