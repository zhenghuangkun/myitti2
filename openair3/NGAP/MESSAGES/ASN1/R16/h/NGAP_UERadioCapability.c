/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/ngap-16.2.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/h`
 */

#include "NGAP_UERadioCapability.h"

/*
 * This type is implemented using OCTET_STRING,
 * so here we adjust the DEF accordingly.
 */
static const ber_tlv_tag_t asn_DEF_NGAP_UERadioCapability_tags_1[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (4 << 2))
};
asn_TYPE_descriptor_t asn_DEF_NGAP_UERadioCapability = {
	"UERadioCapability",
	"UERadioCapability",
	&asn_OP_OCTET_STRING,
	asn_DEF_NGAP_UERadioCapability_tags_1,
	sizeof(asn_DEF_NGAP_UERadioCapability_tags_1)
		/sizeof(asn_DEF_NGAP_UERadioCapability_tags_1[0]), /* 1 */
	asn_DEF_NGAP_UERadioCapability_tags_1,	/* Same as above */
	sizeof(asn_DEF_NGAP_UERadioCapability_tags_1)
		/sizeof(asn_DEF_NGAP_UERadioCapability_tags_1[0]), /* 1 */
	{ 0, 0, OCTET_STRING_constraint },
	0, 0,	/* No members */
	&asn_SPC_OCTET_STRING_specs	/* Additional specs */
};

