/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/ngap-16.2.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/h`
 */

#include "NGAP_QosFlowSetupRequestList.h"

#include "NGAP_QosFlowSetupRequestItem.h"
static asn_per_constraints_t asn_PER_type_NGAP_QosFlowSetupRequestList_constr_1 CC_NOTUSED = {
	{ APC_UNCONSTRAINED,	-1, -1,  0,  0 },
	{ APC_CONSTRAINED,	 6,  6,  1,  64 }	/* (SIZE(1..64)) */,
	0, 0	/* No PER value map */
};
static asn_TYPE_member_t asn_MBR_NGAP_QosFlowSetupRequestList_1[] = {
	{ ATF_POINTER, 0, 0,
		(ASN_TAG_CLASS_UNIVERSAL | (16 << 2)),
		0,
		&asn_DEF_NGAP_QosFlowSetupRequestItem,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		""
		},
};
static const ber_tlv_tag_t asn_DEF_NGAP_QosFlowSetupRequestList_tags_1[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
static asn_SET_OF_specifics_t asn_SPC_NGAP_QosFlowSetupRequestList_specs_1 = {
	sizeof(struct NGAP_QosFlowSetupRequestList),
	offsetof(struct NGAP_QosFlowSetupRequestList, _asn_ctx),
	0,	/* XER encoding is XMLDelimitedItemList */
};
asn_TYPE_descriptor_t asn_DEF_NGAP_QosFlowSetupRequestList = {
	"QosFlowSetupRequestList",
	"QosFlowSetupRequestList",
	&asn_OP_SEQUENCE_OF,
	asn_DEF_NGAP_QosFlowSetupRequestList_tags_1,
	sizeof(asn_DEF_NGAP_QosFlowSetupRequestList_tags_1)
		/sizeof(asn_DEF_NGAP_QosFlowSetupRequestList_tags_1[0]), /* 1 */
	asn_DEF_NGAP_QosFlowSetupRequestList_tags_1,	/* Same as above */
	sizeof(asn_DEF_NGAP_QosFlowSetupRequestList_tags_1)
		/sizeof(asn_DEF_NGAP_QosFlowSetupRequestList_tags_1[0]), /* 1 */
	{ 0, &asn_PER_type_NGAP_QosFlowSetupRequestList_constr_1, SEQUENCE_OF_constraint },
	asn_MBR_NGAP_QosFlowSetupRequestList_1,
	1,	/* Single element */
	&asn_SPC_NGAP_QosFlowSetupRequestList_specs_1	/* Additional specs */
};

