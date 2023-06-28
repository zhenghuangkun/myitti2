/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/ngap-16.2.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/h`
 */

#include "NGAP_AreaScopeOfMDT-EUTRA.h"

asn_per_constraints_t asn_PER_type_NGAP_AreaScopeOfMDT_EUTRA_constr_1 CC_NOTUSED = {
	{ APC_CONSTRAINED | APC_EXTENSIBLE,  2,  2,  0,  3 }	/* (0..3,...) */,
	{ APC_UNCONSTRAINED,	-1, -1,  0,  0 },
	0, 0	/* No PER value map */
};
asn_TYPE_member_t asn_MBR_NGAP_AreaScopeOfMDT_EUTRA_1[] = {
	{ ATF_NOFLAGS, 0, offsetof(struct NGAP_AreaScopeOfMDT_EUTRA, choice.cellBased),
		(ASN_TAG_CLASS_CONTEXT | (0 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NGAP_CellBasedMDT_EUTRA,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"cellBased"
		},
	{ ATF_NOFLAGS, 0, offsetof(struct NGAP_AreaScopeOfMDT_EUTRA, choice.tABased),
		(ASN_TAG_CLASS_CONTEXT | (1 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NGAP_TABasedMDT,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"tABased"
		},
	{ ATF_NOFLAGS, 0, offsetof(struct NGAP_AreaScopeOfMDT_EUTRA, choice.pLMNWide),
		(ASN_TAG_CLASS_CONTEXT | (2 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NULL,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"pLMNWide"
		},
	{ ATF_NOFLAGS, 0, offsetof(struct NGAP_AreaScopeOfMDT_EUTRA, choice.tAIBased),
		(ASN_TAG_CLASS_CONTEXT | (3 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NGAP_TAIBasedMDT,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"tAIBased"
		},
};
static const asn_TYPE_tag2member_t asn_MAP_NGAP_AreaScopeOfMDT_EUTRA_tag2el_1[] = {
    { (ASN_TAG_CLASS_CONTEXT | (0 << 2)), 0, 0, 0 }, /* cellBased */
    { (ASN_TAG_CLASS_CONTEXT | (1 << 2)), 1, 0, 0 }, /* tABased */
    { (ASN_TAG_CLASS_CONTEXT | (2 << 2)), 2, 0, 0 }, /* pLMNWide */
    { (ASN_TAG_CLASS_CONTEXT | (3 << 2)), 3, 0, 0 } /* tAIBased */
};
asn_CHOICE_specifics_t asn_SPC_NGAP_AreaScopeOfMDT_EUTRA_specs_1 = {
	sizeof(struct NGAP_AreaScopeOfMDT_EUTRA),
	offsetof(struct NGAP_AreaScopeOfMDT_EUTRA, _asn_ctx),
	offsetof(struct NGAP_AreaScopeOfMDT_EUTRA, present),
	sizeof(((struct NGAP_AreaScopeOfMDT_EUTRA *)0)->present),
	asn_MAP_NGAP_AreaScopeOfMDT_EUTRA_tag2el_1,
	4,	/* Count of tags in the map */
	0, 0,
	4	/* Extensions start */
};
asn_TYPE_descriptor_t asn_DEF_NGAP_AreaScopeOfMDT_EUTRA = {
	"AreaScopeOfMDT-EUTRA",
	"AreaScopeOfMDT-EUTRA",
	&asn_OP_CHOICE,
	0,	/* No effective tags (pointer) */
	0,	/* No effective tags (count) */
	0,	/* No tags (pointer) */
	0,	/* No tags (count) */
	{ 0, &asn_PER_type_NGAP_AreaScopeOfMDT_EUTRA_constr_1, CHOICE_constraint },
	asn_MBR_NGAP_AreaScopeOfMDT_EUTRA_1,
	4,	/* Elements count */
	&asn_SPC_NGAP_AreaScopeOfMDT_EUTRA_specs_1	/* Additional specs */
};
