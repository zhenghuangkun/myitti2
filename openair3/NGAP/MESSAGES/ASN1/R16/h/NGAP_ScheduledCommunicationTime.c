/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/ngap-16.2.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/h`
 */

#include "NGAP_ScheduledCommunicationTime.h"

#include "NGAP_ProtocolExtensionContainer.h"
static int
memb_NGAP_dayofWeek_constraint_1(const asn_TYPE_descriptor_t *td, const void *sptr,
			asn_app_constraint_failed_f *ctfailcb, void *app_key) {
	const BIT_STRING_t *st = (const BIT_STRING_t *)sptr;
	size_t size;
	
	if(!sptr) {
		ASN__CTFAIL(app_key, td, sptr,
			"%s: value not given (%s:%d)",
			td->name, __FILE__, __LINE__);
		return -1;
	}
	
	if(st->size > 0) {
		/* Size in bits */
		size = 8 * st->size - (st->bits_unused & 0x07);
	} else {
		size = 0;
	}
	
	if((size == 7)) {
		/* Constraint check succeeded */
		return 0;
	} else {
		ASN__CTFAIL(app_key, td, sptr,
			"%s: constraint failed (%s:%d)",
			td->name, __FILE__, __LINE__);
		return -1;
	}
}

static int
memb_NGAP_timeofDayStart_constraint_1(const asn_TYPE_descriptor_t *td, const void *sptr,
			asn_app_constraint_failed_f *ctfailcb, void *app_key) {
	long value;
	
	if(!sptr) {
		ASN__CTFAIL(app_key, td, sptr,
			"%s: value not given (%s:%d)",
			td->name, __FILE__, __LINE__);
		return -1;
	}
	
	value = *(const long *)sptr;
	
	if((value >= 0 && value <= 86399)) {
		/* Constraint check succeeded */
		return 0;
	} else {
		ASN__CTFAIL(app_key, td, sptr,
			"%s: constraint failed (%s:%d)",
			td->name, __FILE__, __LINE__);
		return -1;
	}
}

static int
memb_NGAP_timeofDayEnd_constraint_1(const asn_TYPE_descriptor_t *td, const void *sptr,
			asn_app_constraint_failed_f *ctfailcb, void *app_key) {
	long value;
	
	if(!sptr) {
		ASN__CTFAIL(app_key, td, sptr,
			"%s: value not given (%s:%d)",
			td->name, __FILE__, __LINE__);
		return -1;
	}
	
	value = *(const long *)sptr;
	
	if((value >= 0 && value <= 86399)) {
		/* Constraint check succeeded */
		return 0;
	} else {
		ASN__CTFAIL(app_key, td, sptr,
			"%s: constraint failed (%s:%d)",
			td->name, __FILE__, __LINE__);
		return -1;
	}
}

static asn_per_constraints_t asn_PER_memb_NGAP_dayofWeek_constr_2 CC_NOTUSED = {
	{ APC_UNCONSTRAINED,	-1, -1,  0,  0 },
	{ APC_CONSTRAINED,	 0,  0,  7,  7 }	/* (SIZE(7..7)) */,
	0, 0	/* No PER value map */
};
static asn_per_constraints_t asn_PER_memb_NGAP_timeofDayStart_constr_3 CC_NOTUSED = {
	{ APC_CONSTRAINED | APC_EXTENSIBLE,  17, -1,  0,  86399 }	/* (0..86399,...) */,
	{ APC_UNCONSTRAINED,	-1, -1,  0,  0 },
	0, 0	/* No PER value map */
};
static asn_per_constraints_t asn_PER_memb_NGAP_timeofDayEnd_constr_4 CC_NOTUSED = {
	{ APC_CONSTRAINED | APC_EXTENSIBLE,  17, -1,  0,  86399 }	/* (0..86399,...) */,
	{ APC_UNCONSTRAINED,	-1, -1,  0,  0 },
	0, 0	/* No PER value map */
};
asn_TYPE_member_t asn_MBR_NGAP_ScheduledCommunicationTime_1[] = {
	{ ATF_POINTER, 4, offsetof(struct NGAP_ScheduledCommunicationTime, dayofWeek),
		(ASN_TAG_CLASS_CONTEXT | (0 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_BIT_STRING,
		0,
		{ 0, &asn_PER_memb_NGAP_dayofWeek_constr_2,  memb_NGAP_dayofWeek_constraint_1 },
		0, 0, /* No default value */
		"dayofWeek"
		},
	{ ATF_POINTER, 3, offsetof(struct NGAP_ScheduledCommunicationTime, timeofDayStart),
		(ASN_TAG_CLASS_CONTEXT | (1 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NativeInteger,
		0,
		{ 0, &asn_PER_memb_NGAP_timeofDayStart_constr_3,  memb_NGAP_timeofDayStart_constraint_1 },
		0, 0, /* No default value */
		"timeofDayStart"
		},
	{ ATF_POINTER, 2, offsetof(struct NGAP_ScheduledCommunicationTime, timeofDayEnd),
		(ASN_TAG_CLASS_CONTEXT | (2 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NativeInteger,
		0,
		{ 0, &asn_PER_memb_NGAP_timeofDayEnd_constr_4,  memb_NGAP_timeofDayEnd_constraint_1 },
		0, 0, /* No default value */
		"timeofDayEnd"
		},
	{ ATF_POINTER, 1, offsetof(struct NGAP_ScheduledCommunicationTime, iE_Extensions),
		(ASN_TAG_CLASS_CONTEXT | (3 << 2)),
		-1,	/* IMPLICIT tag at current level */
		&asn_DEF_NGAP_ProtocolExtensionContainer_9385P218,
		0,
		{ 0, 0, 0 },
		0, 0, /* No default value */
		"iE-Extensions"
		},
};
static const int asn_MAP_NGAP_ScheduledCommunicationTime_oms_1[] = { 0, 1, 2, 3 };
static const ber_tlv_tag_t asn_DEF_NGAP_ScheduledCommunicationTime_tags_1[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (16 << 2))
};
static const asn_TYPE_tag2member_t asn_MAP_NGAP_ScheduledCommunicationTime_tag2el_1[] = {
    { (ASN_TAG_CLASS_CONTEXT | (0 << 2)), 0, 0, 0 }, /* dayofWeek */
    { (ASN_TAG_CLASS_CONTEXT | (1 << 2)), 1, 0, 0 }, /* timeofDayStart */
    { (ASN_TAG_CLASS_CONTEXT | (2 << 2)), 2, 0, 0 }, /* timeofDayEnd */
    { (ASN_TAG_CLASS_CONTEXT | (3 << 2)), 3, 0, 0 } /* iE-Extensions */
};
asn_SEQUENCE_specifics_t asn_SPC_NGAP_ScheduledCommunicationTime_specs_1 = {
	sizeof(struct NGAP_ScheduledCommunicationTime),
	offsetof(struct NGAP_ScheduledCommunicationTime, _asn_ctx),
	asn_MAP_NGAP_ScheduledCommunicationTime_tag2el_1,
	4,	/* Count of tags in the map */
	asn_MAP_NGAP_ScheduledCommunicationTime_oms_1,	/* Optional members */
	4, 0,	/* Root/Additions */
	4,	/* First extension addition */
};
asn_TYPE_descriptor_t asn_DEF_NGAP_ScheduledCommunicationTime = {
	"ScheduledCommunicationTime",
	"ScheduledCommunicationTime",
	&asn_OP_SEQUENCE,
	asn_DEF_NGAP_ScheduledCommunicationTime_tags_1,
	sizeof(asn_DEF_NGAP_ScheduledCommunicationTime_tags_1)
		/sizeof(asn_DEF_NGAP_ScheduledCommunicationTime_tags_1[0]), /* 1 */
	asn_DEF_NGAP_ScheduledCommunicationTime_tags_1,	/* Same as above */
	sizeof(asn_DEF_NGAP_ScheduledCommunicationTime_tags_1)
		/sizeof(asn_DEF_NGAP_ScheduledCommunicationTime_tags_1[0]), /* 1 */
	{ 0, 0, SEQUENCE_constraint },
	asn_MBR_NGAP_ScheduledCommunicationTime_1,
	4,	/* Elements count */
	&asn_SPC_NGAP_ScheduledCommunicationTime_specs_1	/* Additional specs */
};

