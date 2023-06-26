/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "S1AP-PDU-Contents"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/openair3/NGAP/MESSAGES/ASN1/R16/s1ap-15.6.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/build_dir/build/CMakeFiles/NGAP_R16`
 */

#include "S1AP_E-RABToBeSetupItemBearerSUReq.h"

#include "S1AP_ProtocolExtensionContainer.h"
static asn_TYPE_member_t asn_MBR_S1AP_E_RABToBeSetupItemBearerSUReq_1[] = {
	{ ATF_NOFLAGS, 0, offsetof(struct S1AP_E_RABToBeSetupItemBearerSUReq, e_RAB_ID),
		(ASN_TAG_CLASS_CONTEXT | (0 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_S1AP_E_RAB_ID,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"e-RAB-ID"
		},
	{ ATF_NOFLAGS, 0, offsetof(struct S1AP_E_RABToBeSetupItemBearerSUReq, e_RABlevelQoSParameters),
		(ASN_TAG_CLASS_CONTEXT | (1 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_S1AP_E_RABLevelQoSParameters,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"e-RABlevelQoSParameters"
		},
	{ ATF_NOFLAGS, 0, offsetof(struct S1AP_E_RABToBeSetupItemBearerSUReq, transportLayerAddress),
		(ASN_TAG_CLASS_CONTEXT | (2 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_S1AP_TransportLayerAddress,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"transportLayerAddress"
		},
	{ ATF_NOFLAGS, 0, offsetof(struct S1AP_E_RABToBeSetupItemBearerSUReq, gTP_TEID),
		(ASN_TAG_CLASS_CONTEXT | (3 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_S1AP_GTP_TEID,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"gTP-TEID"
		},
	{ ATF_NOFLAGS, 0, offsetof(struct S1AP_E_RABToBeSetupItemBearerSUReq, nAS_PDU),
		(ASN_TAG_CLASS_CONTEXT | (4 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_S1AP_NAS_PDU,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"nAS-PDU"
		},
	{ ATF_POINTER, 1, offsetof(struct S1AP_E_RABToBeSetupItemBearerSUReq, iE_Extensions),
		(ASN_TAG_CLASS_CONTEXT | (5 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_S1AP_ProtocolExtensionContainer_7327P6,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"iE-Extensions"
		},
};
static const int asn_MAP_S1AP_E_RABToBeSetupItemBearerSUReq_oms_1[] = { 5 };
static const ber_tlv_tag_t asn_DEF_S1AP_E_RABToBeSetupItemBearerSUReq_tags_1[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
static const asn_TYPE_tag2member_t asn_MAP_S1AP_E_RABToBeSetupItemBearerSUReq_tag2el_1[] = {
    { (ASN_TAG_CLASS_CONTEXT | (0 << 2)), 0, 0, 0 }, /* e-RAB-ID */
    { (ASN_TAG_CLASS_CONTEXT | (1 << 2)), 1, 0, 0 }, /* e-RABlevelQoSParameters */
    { (ASN_TAG_CLASS_CONTEXT | (2 << 2)), 2, 0, 0 }, /* transportLayerAddress */
    { (ASN_TAG_CLASS_CONTEXT | (3 << 2)), 3, 0, 0 }, /* gTP-TEID */
    { (ASN_TAG_CLASS_CONTEXT | (4 << 2)), 4, 0, 0 }, /* nAS-PDU */
    { (ASN_TAG_CLASS_CONTEXT | (5 << 2)), 5, 0, 0 } /* iE-Extensions */
};
static asn_SEQUENCE_specifics_t asn_SPC_S1AP_E_RABToBeSetupItemBearerSUReq_specs_1 = {
	sizeof(struct S1AP_E_RABToBeSetupItemBearerSUReq),
	offsetof(struct S1AP_E_RABToBeSetupItemBearerSUReq, _asn_ctx),
	asn_MAP_S1AP_E_RABToBeSetupItemBearerSUReq_tag2el_1,
	6,	/* Count of tags in the map */
	asn_MAP_S1AP_E_RABToBeSetupItemBearerSUReq_oms_1,	/* Optional members */
	1, 0,	/* Root/Additions */
	6,	/* First extension addition */
};
asn_TYPE_descriptor_t asn_DEF_S1AP_E_RABToBeSetupItemBearerSUReq = {
	"E-RABToBeSetupItemBearerSUReq",
	"E-RABToBeSetupItemBearerSUReq",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_E_RABToBeSetupItemBearerSUReq_tags_1,
	sizeof(asn_DEF_S1AP_E_RABToBeSetupItemBearerSUReq_tags_1)
		/sizeof(asn_DEF_S1AP_E_RABToBeSetupItemBearerSUReq_tags_1[0]), /* 1 */
	asn_DEF_S1AP_E_RABToBeSetupItemBearerSUReq_tags_1,	/* Same as above */
	sizeof(asn_DEF_S1AP_E_RABToBeSetupItemBearerSUReq_tags_1)
		/sizeof(asn_DEF_S1AP_E_RABToBeSetupItemBearerSUReq_tags_1[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_E_RABToBeSetupItemBearerSUReq_1,
	6,	/* Elements count */
	&asn_SPC_S1AP_E_RABToBeSetupItemBearerSUReq_specs_1	/* Additional specs */
};

