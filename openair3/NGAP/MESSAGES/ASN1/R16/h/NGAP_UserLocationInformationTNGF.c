/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/ngap-16.2.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/h`
 */

#include "NGAP_UserLocationInformationTNGF.h"

#include "NGAP_ProtocolExtensionContainer.h"
static asn_TYPE_member_t asn_MBR_NGAP_UserLocationInformationTNGF_1[] = {
	{ ATF_NOFLAGS, 0, offsetof(struct NGAP_UserLocationInformationTNGF, tNAP_ID),
		(ASN_TAG_CLASS_CONTEXT | (0 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NGAP_TNAP_ID,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"tNAP-ID"
		},
	{ ATF_NOFLAGS, 0, offsetof(struct NGAP_UserLocationInformationTNGF, iPAddress),
		(ASN_TAG_CLASS_CONTEXT | (1 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NGAP_TransportLayerAddress,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"iPAddress"
		},
	{ ATF_POINTER, 2, offsetof(struct NGAP_UserLocationInformationTNGF, portNumber),
		(ASN_TAG_CLASS_CONTEXT | (2 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NGAP_PortNumber,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"portNumber"
		},
	{ ATF_POINTER, 1, offsetof(struct NGAP_UserLocationInformationTNGF, iE_Extensions),
		(ASN_TAG_CLASS_CONTEXT | (3 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NGAP_ProtocolExtensionContainer_9385P273,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"iE-Extensions"
		},
};
static const int asn_MAP_NGAP_UserLocationInformationTNGF_oms_1[] = { 2, 3 };
static const ber_tlv_tag_t asn_DEF_NGAP_UserLocationInformationTNGF_tags_1[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
static const asn_TYPE_tag2member_t asn_MAP_NGAP_UserLocationInformationTNGF_tag2el_1[] = {
    { (ASN_TAG_CLASS_CONTEXT | (0 << 2)), 0, 0, 0 }, /* tNAP-ID */
    { (ASN_TAG_CLASS_CONTEXT | (1 << 2)), 1, 0, 0 }, /* iPAddress */
    { (ASN_TAG_CLASS_CONTEXT | (2 << 2)), 2, 0, 0 }, /* portNumber */
    { (ASN_TAG_CLASS_CONTEXT | (3 << 2)), 3, 0, 0 } /* iE-Extensions */
};
static asn_SEQUENCE_specifics_t asn_SPC_NGAP_UserLocationInformationTNGF_specs_1 = {
	sizeof(struct NGAP_UserLocationInformationTNGF),
	offsetof(struct NGAP_UserLocationInformationTNGF, _asn_ctx),
	asn_MAP_NGAP_UserLocationInformationTNGF_tag2el_1,
	4,	/* Count of tags in the map */
	asn_MAP_NGAP_UserLocationInformationTNGF_oms_1,	/* Optional members */
	2, 0,	/* Root/Additions */
	4,	/* First extension addition */
};
asn_TYPE_descriptor_t asn_DEF_NGAP_UserLocationInformationTNGF = {
	"UserLocationInformationTNGF",
	"UserLocationInformationTNGF",
	&asn_OP_SEQUENCE,
	asn_DEF_NGAP_UserLocationInformationTNGF_tags_1,
	sizeof(asn_DEF_NGAP_UserLocationInformationTNGF_tags_1)
		/sizeof(asn_DEF_NGAP_UserLocationInformationTNGF_tags_1[0]), /* 1 */
	asn_DEF_NGAP_UserLocationInformationTNGF_tags_1,	/* Same as above */
	sizeof(asn_DEF_NGAP_UserLocationInformationTNGF_tags_1)
		/sizeof(asn_DEF_NGAP_UserLocationInformationTNGF_tags_1[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_NGAP_UserLocationInformationTNGF_1,
	4,	/* Elements count */
	&asn_SPC_NGAP_UserLocationInformationTNGF_specs_1	/* Additional specs */
};
