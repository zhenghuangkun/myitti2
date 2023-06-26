/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/ngap-16.2.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/h`
 */

#include "NGAP_UPTransportLayerInformation.h"

asn_per_constraints_t asn_PER_type_NGAP_UPTransportLayerInformation_constr_1 CC_NOTUSED = {
	{ APC_CONSTRAINED,	 1,  1,  0,  1 }	/* (0..1) */,
	{ APC_UNCONSTRAINED,	-1, -1,  0,  0 },
	0, 0	/* No PER value map */
};
asn_TYPE_member_t asn_MBR_NGAP_UPTransportLayerInformation_1[] = {
	{ ATF_NOFLAGS, 0, offsetof(struct NGAP_UPTransportLayerInformation, choice.gTPTunnel),
		(ASN_TAG_CLASS_CONTEXT | (0 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NGAP_GTPTunnel,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"gTPTunnel"
		},
	{ ATF_NOFLAGS, 0, offsetof(struct NGAP_UPTransportLayerInformation, choice.choice_Extensions),
		(ASN_TAG_CLASS_CONTEXT | (1 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NGAP_ProtocolIE_SingleContainer_9337P39,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"choice-Extensions"
		},
};
static const asn_TYPE_tag2member_t asn_MAP_NGAP_UPTransportLayerInformation_tag2el_1[] = {
    { (ASN_TAG_CLASS_CONTEXT | (0 << 2)), 0, 0, 0 }, /* gTPTunnel */
    { (ASN_TAG_CLASS_CONTEXT | (1 << 2)), 1, 0, 0 } /* choice-Extensions */
};
asn_CHOICE_specifics_t asn_SPC_NGAP_UPTransportLayerInformation_specs_1 = {
	sizeof(struct NGAP_UPTransportLayerInformation),
	offsetof(struct NGAP_UPTransportLayerInformation, _asn_ctx),
	offsetof(struct NGAP_UPTransportLayerInformation, present),
	sizeof(((struct NGAP_UPTransportLayerInformation *)0)->present),
	asn_MAP_NGAP_UPTransportLayerInformation_tag2el_1,
	2,	/* Count of tags in the map */
	0, 0,
	-1	/* Extensions start */
};
asn_TYPE_descriptor_t asn_DEF_NGAP_UPTransportLayerInformation = {
	"UPTransportLayerInformation",
	"UPTransportLayerInformation",
	&asn_OP_CHOICE,
	0,	/* No effective tags (pointer) */
	0,	/* No effective tags (count) */
	0,	/* No tags (pointer) */
	0,	/* No tags (count) */
	{ 0, &asn_PER_type_NGAP_UPTransportLayerInformation_constr_1, CHOICE_constraint },
	asn_MBR_NGAP_UPTransportLayerInformation_1,
	2,	/* Elements count */
	&asn_SPC_NGAP_UPTransportLayerInformation_specs_1	/* Additional specs */
};

