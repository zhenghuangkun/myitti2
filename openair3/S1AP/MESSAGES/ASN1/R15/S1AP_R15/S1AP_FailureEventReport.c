/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "SonTransfer-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/openair3/NGAP/MESSAGES/ASN1/R16/s1ap-15.6.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/build_dir/build/CMakeFiles/NGAP_R16`
 */

#include "S1AP_FailureEventReport.h"

asn_per_constraints_t asn_PER_type_S1AP_FailureEventReport_constr_1 CC_NOTUSED = {
	{ APC_CONSTRAINED | APC_EXTENSIBLE,  0,  0,  0,  0 }	/* (0..0,...) */,
	{ APC_UNCONSTRAINED,	-1, -1,  0,  0 },
	0, 0	/* No PER value map */
};
asn_TYPE_member_t asn_MBR_S1AP_FailureEventReport_1[] = {
	{ ATF_NOFLAGS, 0, offsetof(struct S1AP_FailureEventReport, choice.tooEarlyInterRATHOReportFromEUTRAN),
		(ASN_TAG_CLASS_CONTEXT | (0 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_S1AP_TooEarlyInterRATHOReportReportFromEUTRAN,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"tooEarlyInterRATHOReportFromEUTRAN"
		},
};
static const asn_TYPE_tag2member_t asn_MAP_S1AP_FailureEventReport_tag2el_1[] = {
    { (ASN_TAG_CLASS_CONTEXT | (0 << 2)), 0, 0, 0 } /* tooEarlyInterRATHOReportFromEUTRAN */
};
asn_CHOICE_specifics_t asn_SPC_S1AP_FailureEventReport_specs_1 = {
	sizeof(struct S1AP_FailureEventReport),
	offsetof(struct S1AP_FailureEventReport, _asn_ctx),
	offsetof(struct S1AP_FailureEventReport, present),
	sizeof(((struct S1AP_FailureEventReport *)0)->present),
	asn_MAP_S1AP_FailureEventReport_tag2el_1,
	1,	/* Count of tags in the map */
	0, 0,
	1	/* Extensions start */
};
asn_TYPE_descriptor_t asn_DEF_S1AP_FailureEventReport = {
	"FailureEventReport",
	"FailureEventReport",
	&asn_OP_CHOICE,
	0,	/* No effective tags (pointer) */
	0,	/* No effective tags (count) */
	0,	/* No tags (pointer) */
	0,	/* No tags (count) */
	{ 0, &asn_PER_type_S1AP_FailureEventReport_constr_1, CHOICE_constraint },
	asn_MBR_S1AP_FailureEventReport_1,
	1,	/* Elements count */
	&asn_SPC_S1AP_FailureEventReport_specs_1	/* Additional specs */
};

