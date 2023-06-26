/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "S1AP-Containers"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/openair3/NGAP/MESSAGES/ASN1/R16/s1ap-15.6.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/build_dir/build/CMakeFiles/NGAP_R16`
 */

#include "S1AP_ProtocolIE-SingleContainer.h"

/*
 * This type is implemented using S1AP_E_RABToBeSetupItemBearerSUReqIEs,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_E_RABSetupItemBearerSUResIEs,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_E_RABToBeModifiedItemBearerModReqIEs,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_E_RABModifyItemBearerModResIEs,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_E_RABReleaseItemBearerRelCompIEs,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_E_RABToBeSetupItemCtxtSUReqIEs,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_E_RABSetupItemCtxtSUResIEs,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_TAIItemIEs,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_UE_associatedLogicalS1_ConnectionItemRes,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_UE_associatedLogicalS1_ConnectionItemResAck,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_E_RABModifyItemBearerModConfIEs,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_Bearers_SubjectToStatusTransfer_ItemIEs,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_E_RABInformationListIEs,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_E_RABItemIEs,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_E_RABUsageReportItemIEs,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_MDTMode_ExtensionIE,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_RecommendedCellItemIEs,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_RecommendedENBItemIEs,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_SecondaryRATDataUsageReportItemIEs,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_SONInformation_ExtensionIE,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_E_RABDataForwardingItemIEs,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_E_RABToBeSetupItemHOReqIEs,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_E_RABAdmittedItemIEs,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_E_RABFailedtoSetupItemHOReqAckIEs,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_E_RABToBeSwitchedDLItemIEs,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_E_RABToBeSwitchedULItemIEs,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_E_RABToBeModifiedItemBearerModIndIEs,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_E_RABNotToBeModifiedItemBearerModIndIEs,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_E_RABFailedToResumeItemResumeReqIEs,
 * so here we adjust the DEF accordingly.
 */
/*
 * This type is implemented using S1AP_E_RABFailedToResumeItemResumeResIEs,
 * so here we adjust the DEF accordingly.
 */
static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P0_tags_1[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P0 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P0_tags_1,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P0_tags_1)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P0_tags_1[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P0_tags_1,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P0_tags_1)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P0_tags_1[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_E_RABToBeSetupItemBearerSUReqIEs_1,
	3,	/* Elements count */
	&asn_SPC_S1AP_E_RABToBeSetupItemBearerSUReqIEs_specs_1	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P1_tags_2[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P1 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P1_tags_2,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P1_tags_2)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P1_tags_2[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P1_tags_2,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P1_tags_2)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P1_tags_2[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_E_RABSetupItemBearerSUResIEs_5,
	3,	/* Elements count */
	&asn_SPC_S1AP_E_RABSetupItemBearerSUResIEs_specs_5	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P2_tags_3[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P2 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P2_tags_3,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P2_tags_3)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P2_tags_3[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P2_tags_3,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P2_tags_3)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P2_tags_3[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_E_RABToBeModifiedItemBearerModReqIEs_9,
	3,	/* Elements count */
	&asn_SPC_S1AP_E_RABToBeModifiedItemBearerModReqIEs_specs_9	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P3_tags_4[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P3 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P3_tags_4,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P3_tags_4)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P3_tags_4[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P3_tags_4,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P3_tags_4)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P3_tags_4[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_E_RABModifyItemBearerModResIEs_13,
	3,	/* Elements count */
	&asn_SPC_S1AP_E_RABModifyItemBearerModResIEs_specs_13	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P4_tags_5[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P4 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P4_tags_5,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P4_tags_5)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P4_tags_5[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P4_tags_5,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P4_tags_5)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P4_tags_5[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_E_RABReleaseItemBearerRelCompIEs_17,
	3,	/* Elements count */
	&asn_SPC_S1AP_E_RABReleaseItemBearerRelCompIEs_specs_17	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P5_tags_6[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P5 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P5_tags_6,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P5_tags_6)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P5_tags_6[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P5_tags_6,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P5_tags_6)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P5_tags_6[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_E_RABToBeSetupItemCtxtSUReqIEs_21,
	3,	/* Elements count */
	&asn_SPC_S1AP_E_RABToBeSetupItemCtxtSUReqIEs_specs_21	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P6_tags_7[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P6 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P6_tags_7,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P6_tags_7)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P6_tags_7[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P6_tags_7,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P6_tags_7)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P6_tags_7[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_E_RABSetupItemCtxtSUResIEs_25,
	3,	/* Elements count */
	&asn_SPC_S1AP_E_RABSetupItemCtxtSUResIEs_specs_25	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P7_tags_8[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P7 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P7_tags_8,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P7_tags_8)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P7_tags_8[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P7_tags_8,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P7_tags_8)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P7_tags_8[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_TAIItemIEs_29,
	3,	/* Elements count */
	&asn_SPC_S1AP_TAIItemIEs_specs_29	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P8_tags_9[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P8 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P8_tags_9,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P8_tags_9)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P8_tags_9[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P8_tags_9,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P8_tags_9)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P8_tags_9[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_UE_associatedLogicalS1_ConnectionItemRes_33,
	3,	/* Elements count */
	&asn_SPC_S1AP_UE_associatedLogicalS1_ConnectionItemRes_specs_33	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P9_tags_10[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P9 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P9_tags_10,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P9_tags_10)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P9_tags_10[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P9_tags_10,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P9_tags_10)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P9_tags_10[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_UE_associatedLogicalS1_ConnectionItemResAck_37,
	3,	/* Elements count */
	&asn_SPC_S1AP_UE_associatedLogicalS1_ConnectionItemResAck_specs_37	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P10_tags_11[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P10 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P10_tags_11,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P10_tags_11)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P10_tags_11[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P10_tags_11,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P10_tags_11)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P10_tags_11[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_E_RABModifyItemBearerModConfIEs_41,
	3,	/* Elements count */
	&asn_SPC_S1AP_E_RABModifyItemBearerModConfIEs_specs_41	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P11_tags_12[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P11 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P11_tags_12,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P11_tags_12)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P11_tags_12[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P11_tags_12,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P11_tags_12)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P11_tags_12[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_Bearers_SubjectToStatusTransfer_ItemIEs_45,
	3,	/* Elements count */
	&asn_SPC_S1AP_Bearers_SubjectToStatusTransfer_ItemIEs_specs_45	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P12_tags_13[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P12 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P12_tags_13,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P12_tags_13)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P12_tags_13[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P12_tags_13,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P12_tags_13)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P12_tags_13[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_E_RABInformationListIEs_49,
	3,	/* Elements count */
	&asn_SPC_S1AP_E_RABInformationListIEs_specs_49	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P13_tags_14[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P13 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P13_tags_14,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P13_tags_14)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P13_tags_14[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P13_tags_14,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P13_tags_14)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P13_tags_14[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_E_RABItemIEs_53,
	3,	/* Elements count */
	&asn_SPC_S1AP_E_RABItemIEs_specs_53	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P14_tags_15[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P14 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P14_tags_15,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P14_tags_15)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P14_tags_15[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P14_tags_15,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P14_tags_15)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P14_tags_15[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_E_RABUsageReportItemIEs_57,
	3,	/* Elements count */
	&asn_SPC_S1AP_E_RABUsageReportItemIEs_specs_57	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P15_tags_16[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P15 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P15_tags_16,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P15_tags_16)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P15_tags_16[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P15_tags_16,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P15_tags_16)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P15_tags_16[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_MDTMode_ExtensionIE_61,
	3,	/* Elements count */
	&asn_SPC_S1AP_MDTMode_ExtensionIE_specs_61	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P16_tags_17[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P16 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P16_tags_17,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P16_tags_17)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P16_tags_17[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P16_tags_17,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P16_tags_17)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P16_tags_17[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_RecommendedCellItemIEs_65,
	3,	/* Elements count */
	&asn_SPC_S1AP_RecommendedCellItemIEs_specs_65	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P17_tags_18[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P17 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P17_tags_18,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P17_tags_18)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P17_tags_18[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P17_tags_18,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P17_tags_18)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P17_tags_18[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_RecommendedENBItemIEs_69,
	3,	/* Elements count */
	&asn_SPC_S1AP_RecommendedENBItemIEs_specs_69	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P18_tags_19[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P18 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P18_tags_19,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P18_tags_19)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P18_tags_19[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P18_tags_19,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P18_tags_19)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P18_tags_19[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_SecondaryRATDataUsageReportItemIEs_73,
	3,	/* Elements count */
	&asn_SPC_S1AP_SecondaryRATDataUsageReportItemIEs_specs_73	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P19_tags_20[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P19 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P19_tags_20,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P19_tags_20)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P19_tags_20[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P19_tags_20,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P19_tags_20)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P19_tags_20[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_SONInformation_ExtensionIE_77,
	3,	/* Elements count */
	&asn_SPC_S1AP_SONInformation_ExtensionIE_specs_77	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P20_tags_21[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P20 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P20_tags_21,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P20_tags_21)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P20_tags_21[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P20_tags_21,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P20_tags_21)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P20_tags_21[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_E_RABDataForwardingItemIEs_449,
	3,	/* Elements count */
	&asn_SPC_S1AP_E_RABDataForwardingItemIEs_specs_449	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P21_tags_22[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P21 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P21_tags_22,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P21_tags_22)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P21_tags_22[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P21_tags_22,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P21_tags_22)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P21_tags_22[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_E_RABToBeSetupItemHOReqIEs_453,
	3,	/* Elements count */
	&asn_SPC_S1AP_E_RABToBeSetupItemHOReqIEs_specs_453	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P22_tags_23[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P22 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P22_tags_23,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P22_tags_23)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P22_tags_23[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P22_tags_23,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P22_tags_23)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P22_tags_23[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_E_RABAdmittedItemIEs_457,
	3,	/* Elements count */
	&asn_SPC_S1AP_E_RABAdmittedItemIEs_specs_457	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P23_tags_24[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P23 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P23_tags_24,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P23_tags_24)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P23_tags_24[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P23_tags_24,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P23_tags_24)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P23_tags_24[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_E_RABFailedtoSetupItemHOReqAckIEs_461,
	3,	/* Elements count */
	&asn_SPC_S1AP_E_RABFailedtoSetupItemHOReqAckIEs_specs_461	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P24_tags_25[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P24 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P24_tags_25,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P24_tags_25)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P24_tags_25[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P24_tags_25,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P24_tags_25)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P24_tags_25[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_E_RABToBeSwitchedDLItemIEs_465,
	3,	/* Elements count */
	&asn_SPC_S1AP_E_RABToBeSwitchedDLItemIEs_specs_465	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P25_tags_26[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P25 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P25_tags_26,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P25_tags_26)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P25_tags_26[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P25_tags_26,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P25_tags_26)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P25_tags_26[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_E_RABToBeSwitchedULItemIEs_469,
	3,	/* Elements count */
	&asn_SPC_S1AP_E_RABToBeSwitchedULItemIEs_specs_469	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P26_tags_27[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P26 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P26_tags_27,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P26_tags_27)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P26_tags_27[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P26_tags_27,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P26_tags_27)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P26_tags_27[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_E_RABToBeModifiedItemBearerModIndIEs_473,
	3,	/* Elements count */
	&asn_SPC_S1AP_E_RABToBeModifiedItemBearerModIndIEs_specs_473	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P27_tags_28[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P27 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P27_tags_28,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P27_tags_28)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P27_tags_28[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P27_tags_28,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P27_tags_28)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P27_tags_28[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_E_RABNotToBeModifiedItemBearerModIndIEs_477,
	3,	/* Elements count */
	&asn_SPC_S1AP_E_RABNotToBeModifiedItemBearerModIndIEs_specs_477	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P28_tags_29[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P28 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P28_tags_29,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P28_tags_29)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P28_tags_29[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P28_tags_29,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P28_tags_29)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P28_tags_29[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_E_RABFailedToResumeItemResumeReqIEs_481,
	3,	/* Elements count */
	&asn_SPC_S1AP_E_RABFailedToResumeItemResumeReqIEs_specs_481	/* Additional specs */
};

static const ber_tlv_tag_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P29_tags_30[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
asn_TYPE_descriptor_t asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P29 = {
	"ProtocolIE-SingleContainer",
	"ProtocolIE-SingleContainer",
	&asn_OP_SEQUENCE,
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P29_tags_30,
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P29_tags_30)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P29_tags_30[0]), /* 1 */
	asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P29_tags_30,	/* Same as above */
	sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P29_tags_30)
		/sizeof(asn_DEF_S1AP_ProtocolIE_SingleContainer_7279P29_tags_30[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_S1AP_E_RABFailedToResumeItemResumeResIEs_485,
	3,	/* Elements count */
	&asn_SPC_S1AP_E_RABFailedToResumeItemResumeResIEs_specs_485	/* Additional specs */
};

