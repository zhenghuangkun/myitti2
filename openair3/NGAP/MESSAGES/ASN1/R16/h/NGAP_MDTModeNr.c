/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/ngap-16.2.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/h`
 */

#include "NGAP_MDTModeNr.h"

asn_per_constraints_t asn_PER_type_NGAP_MDTModeNr_constr_1 CC_NOTUSED = {
	{ APC_CONSTRAINED | APC_EXTENSIBLE,  1,  1,  0,  1 }	/* (0..1,...) */,
	{ APC_UNCONSTRAINED,	-1, -1,  0,  0 },
	0, 0	/* No PER value map */
};
asn_TYPE_member_t asn_MBR_NGAP_MDTModeNr_1[] = {
	{ ATF_NOFLAGS, 0, offsetof(struct NGAP_MDTModeNr, choice.immediateMDTNr),
		(ASN_TAG_CLASS_CONTEXT | (0 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NGAP_ImmediateMDTNr,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"immediateMDTNr"
		},
	{ ATF_NOFLAGS, 0, offsetof(struct NGAP_MDTModeNr, choice.loggedMDTNr),
		(ASN_TAG_CLASS_CONTEXT | (1 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NGAP_LoggedMDTNr,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"loggedMDTNr"
		},
};
static const asn_TYPE_tag2member_t asn_MAP_NGAP_MDTModeNr_tag2el_1[] = {
    { (ASN_TAG_CLASS_CONTEXT | (0 << 2)), 0, 0, 0 }, /* immediateMDTNr */
    { (ASN_TAG_CLASS_CONTEXT | (1 << 2)), 1, 0, 0 } /* loggedMDTNr */
};
asn_CHOICE_specifics_t asn_SPC_NGAP_MDTModeNr_specs_1 = {
	sizeof(struct NGAP_MDTModeNr),
	offsetof(struct NGAP_MDTModeNr, _asn_ctx),
	offsetof(struct NGAP_MDTModeNr, present),
	sizeof(((struct NGAP_MDTModeNr *)0)->present),
	asn_MAP_NGAP_MDTModeNr_tag2el_1,
	2,	/* Count of tags in the map */
	0, 0,
	2	/* Extensions start */
};
asn_TYPE_descriptor_t asn_DEF_NGAP_MDTModeNr = {
	"MDTModeNr",
	"MDTModeNr",
	&asn_OP_CHOICE,
	0,	/* No effective tags (pointer) */
	0,	/* No effective tags (count) */
	0,	/* No tags (pointer) */
	0,	/* No tags (count) */
	{ 0, &asn_PER_type_NGAP_MDTModeNr_constr_1, CHOICE_constraint },
	asn_MBR_NGAP_MDTModeNr_1,
	2,	/* Elements count */
	&asn_SPC_NGAP_MDTModeNr_specs_1	/* Additional specs */
};

