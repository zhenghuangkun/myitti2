/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "S1AP-PDU-Contents"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/openair3/NGAP/MESSAGES/ASN1/R16/s1ap-15.6.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/build_dir/build/CMakeFiles/NGAP_R16`
 */

#include "S1AP_DownlinkNonUEAssociatedLPPaTransport.h"

asn_TYPE_member_t asn_MBR_S1AP_DownlinkNonUEAssociatedLPPaTransport_1[] = {
	{ ATF_NOFLAGS, 0, offsetof(struct S1AP_DownlinkNonUEAssociatedLPPaTransport, protocolIEs),
		(ASN_TAG_CLASS_CONTEXT | (0 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_S1AP_ProtocolIE_Container_7276P75,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"protocolIEs"
		},
};
static const ber_tlv_tag_t asn_DEF_S1AP_DownlinkNonUEAssociatedLPPaTransport_tags_1[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
static const asn_TYPE_tag2member_t asn_MAP_S1AP_DownlinkNonUEAssociatedLPPaTransport_tag2el_1[] = {
    { (ASN_TAG_CLASS_CONTEXT | (0 << 2)), 0, 0, 0 } /* protocolIEs */
};
asn_SEQUENCE_specifics_t asn_SPC_S1AP_DownlinkNonUEAssociatedLPPaTransport_specs_1 = {
	sizeof(struct S1AP_DownlinkNonUEAssociatedLPPaTransport),
	offsetof(struct S1AP_DownlinkNonUEAssociatedLPPaTransport, _asn_ctx),
	asn_MAP_S1AP_DownlinkNonUEAssociatedLPPaTransport_tag2el_1,
	1,	/* Count of tags in the map */
	0, 0, 0,	/* Optional elements (not needed) */
	1,	/* First extension addition */
};
asn_TYPE_descriptor_t asn_DEF_S1AP_DownlinkNonUEAssociatedLPPaTransport = {
	"DownlinkNonUEAssociatedLPPaTransport",
	"DownlinkNonUEAssociatedLPPaTransport",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_DownlinkNonUEAssociatedLPPaTransport_tags_1,
	sizeof(asn_DEF_S1AP_DownlinkNonUEAssociatedLPPaTransport_tags_1)
		/sizeof(asn_DEF_S1AP_DownlinkNonUEAssociatedLPPaTransport_tags_1[0]), /* 1 */
	asn_DEF_S1AP_DownlinkNonUEAssociatedLPPaTransport_tags_1,	/* Same as above */
	sizeof(asn_DEF_S1AP_DownlinkNonUEAssociatedLPPaTransport_tags_1)
		/sizeof(asn_DEF_S1AP_DownlinkNonUEAssociatedLPPaTransport_tags_1[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_DownlinkNonUEAssociatedLPPaTransport_1,
	1,	/* Elements count */
	&asn_SPC_S1AP_DownlinkNonUEAssociatedLPPaTransport_specs_1	/* Additional specs */
};

