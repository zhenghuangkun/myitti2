/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/ngap-16.2.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/h`
 */

#include "NGAP_UPTransportLayerInformationItem.h"

#include "NGAP_ProtocolExtensionContainer.h"
asn_TYPE_member_t asn_MBR_NGAP_UPTransportLayerInformationItem_1[] = {
	{ ATF_NOFLAGS, 0, offsetof(struct NGAP_UPTransportLayerInformationItem, nGU_UP_TNLInformation),
		(ASN_TAG_CLASS_CONTEXT | (0 << 2)),
		+1,	/* EXPLICIT tag at current level */
		&asn_DEF_NGAP_UPTransportLayerInformation,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"nGU-UP-TNLInformation"
		},
	{ ATF_POINTER, 1, offsetof(struct NGAP_UPTransportLayerInformationItem, iE_Extensions),
		(ASN_TAG_CLASS_CONTEXT | (1 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NGAP_ProtocolExtensionContainer_9385P269,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"iE-Extensions"
		},
};
static const int asn_MAP_NGAP_UPTransportLayerInformationItem_oms_1[] = { 1 };
static const ber_tlv_tag_t asn_DEF_NGAP_UPTransportLayerInformationItem_tags_1[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
static const asn_TYPE_tag2member_t asn_MAP_NGAP_UPTransportLayerInformationItem_tag2el_1[] = {
    { (ASN_TAG_CLASS_CONTEXT | (0 << 2)), 0, 0, 0 }, /* nGU-UP-TNLInformation */
    { (ASN_TAG_CLASS_CONTEXT | (1 << 2)), 1, 0, 0 } /* iE-Extensions */
};
asn_SEQUENCE_specifics_t asn_SPC_NGAP_UPTransportLayerInformationItem_specs_1 = {
	sizeof(struct NGAP_UPTransportLayerInformationItem),
	offsetof(struct NGAP_UPTransportLayerInformationItem, _asn_ctx),
	asn_MAP_NGAP_UPTransportLayerInformationItem_tag2el_1,
	2,	/* Count of tags in the map */
	asn_MAP_NGAP_UPTransportLayerInformationItem_oms_1,	/* Optional members */
	1, 0,	/* Root/Additions */
	2,	/* First extension addition */
};
asn_TYPE_descriptor_t asn_DEF_NGAP_UPTransportLayerInformationItem = {
	"UPTransportLayerInformationItem",
	"UPTransportLayerInformationItem",
	&asn_OP_SEQUENCE,
	asn_DEF_NGAP_UPTransportLayerInformationItem_tags_1,
	sizeof(asn_DEF_NGAP_UPTransportLayerInformationItem_tags_1)
		/sizeof(asn_DEF_NGAP_UPTransportLayerInformationItem_tags_1[0]), /* 1 */
	asn_DEF_NGAP_UPTransportLayerInformationItem_tags_1,	/* Same as above */
	sizeof(asn_DEF_NGAP_UPTransportLayerInformationItem_tags_1)
		/sizeof(asn_DEF_NGAP_UPTransportLayerInformationItem_tags_1[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_NGAP_UPTransportLayerInformationItem_1,
	2,	/* Elements count */
	&asn_SPC_NGAP_UPTransportLayerInformationItem_specs_1	/* Additional specs */
};

