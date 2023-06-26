/*
 * Generated by asn1c-0.9.29 (http://lionet.info/asn1c)
 * From ASN.1 module "NGAP-IEs"
 * 	found in "/home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/ngap-16.2.0.asn1"
 * 	`asn1c -pdu=all -fcompound-names -gen-PER -no-gen-OER -no-gen-example -fno-include-deps -D /home/ubuntu/smbshare/work_jftt/zhenghk/test/THREAD-new/cmake_targets/../openair3/NGAP/MESSAGE/ASN1/R16/h`
 */

#include "NGAP_NR-PCI.h"

int
NGAP_NR_PCI_constraint(const asn_TYPE_descriptor_t *td, const void *sptr,
			asn_app_constraint_failed_f *ctfailcb, void *app_key) {
	long value;
	
	if(!sptr) {
		ASN__CTFAIL(app_key, td, sptr,
			"%s: value not given (%s:%d)",
			td->name, __FILE__, __LINE__);
		return -1;
	}
	
	value = *(const long *)sptr;
	
	if((value >= 0 && value <= 1007)) {
		/* Constraint check succeeded */
		return 0;
	} else {
		ASN__CTFAIL(app_key, td, sptr,
			"%s: constraint failed (%s:%d)",
			td->name, __FILE__, __LINE__);
		return -1;
	}
}

/*
 * This type is implemented using NativeInteger,
 * so here we adjust the DEF accordingly.
 */
asn_per_constraints_t asn_PER_type_NGAP_NR_PCI_constr_1 CC_NOTUSED = {
	{ APC_CONSTRAINED | APC_EXTENSIBLE,  10,  10,  0,  1007 }	/* (0..1007,...) */,
	{ APC_UNCONSTRAINED,	-1, -1,  0,  0 },
	0, 0	/* No PER value map */
};
static const ber_tlv_tag_t asn_DEF_NGAP_NR_PCI_tags_1[] = {
	(ASN_TAG_CLASS_UNIVERSAL | (2 << 2))
};
asn_TYPE_descriptor_t asn_DEF_NGAP_NR_PCI = {
	"NR-PCI",
	"NR-PCI",
	&asn_OP_NativeInteger,
	asn_DEF_NGAP_NR_PCI_tags_1,
	sizeof(asn_DEF_NGAP_NR_PCI_tags_1)
		/sizeof(asn_DEF_NGAP_NR_PCI_tags_1[0]), /* 1 */
	asn_DEF_NGAP_NR_PCI_tags_1,	/* Same as above */
	sizeof(asn_DEF_NGAP_NR_PCI_tags_1)
		/sizeof(asn_DEF_NGAP_NR_PCI_tags_1[0]), /* 1 */
	{ 0, &asn_PER_type_NGAP_NR_PCI_constr_1, NGAP_NR_PCI_constraint },
	0, 0,	/* No members */
	0	/* No specifics */
};

