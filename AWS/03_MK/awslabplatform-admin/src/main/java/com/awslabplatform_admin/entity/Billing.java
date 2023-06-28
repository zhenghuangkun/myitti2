package com.awslabplatform_admin.entity;

import java.util.Date;

/**
 * 账单类
 * @author wy
 *
 */
public class Billing{
	
	private String invoiceID;

	private String payerAccountId;
	
	private String linkedAccountId;
	
	private String recordType;
	
	private String recordID;
	
	private Date billingPeriodStartDate;
	
	private Date  billingPeriodEndDate;
	
	private Date  invoiceDate;
	
	private String payerAccountName;
	
	private String linkedAccountName;
	
	private String taxationAddress;
	
	private String payerPONumber;
	
	private String productCode;
	
	private String productName;
	
	private String sellerOfRecord;
	
	private String usageType;
	
	private String operation;
	
	private String rateId;
	
	private String itemDescription;
	
	private Date  usageStartDate;
	
	private Date usageEndDate;
	
	private String usageQuantity;
	
	private String blendedRate;
	
	private String currencyCode;
	
	private String costBeforeTax;
	
	private String credits;
	
	private String taxAmount;
	
	private String taxType;
	
	private String totalCost;
	
	private String totalAmount;//总额度

	private String invoiceAmount;//已开发票金额

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}

	public String getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}

	public String getPayerAccountId() {
		return payerAccountId;
	}

	public void setPayerAccountId(String payerAccountId) {
		this.payerAccountId = payerAccountId;
	}

	public String getLinkedAccountId() {
		return linkedAccountId;
	}

	public void setLinkedAccountId(String linkedAccountId) {
		this.linkedAccountId = linkedAccountId;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getRecordID() {
		return recordID;
	}

	public void setRecordID(String recordID) {
		this.recordID = recordID;
	}

	public Date getBillingPeriodStartDate() {
		return billingPeriodStartDate;
	}

	public void setBillingPeriodStartDate(Date billingPeriodStartDate) {
		this.billingPeriodStartDate = billingPeriodStartDate;
	}

	public Date getBillingPeriodEndDate() {
		return billingPeriodEndDate;
	}

	public void setBillingPeriodEndDate(Date billingPeriodEndDate) {
		this.billingPeriodEndDate = billingPeriodEndDate;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getPayerAccountName() {
		return payerAccountName;
	}

	public void setPayerAccountName(String payerAccountName) {
		this.payerAccountName = payerAccountName;
	}

	public String getLinkedAccountName() {
		return linkedAccountName;
	}

	public void setLinkedAccountName(String linkedAccountName) {
		this.linkedAccountName = linkedAccountName;
	}

	public String getTaxationAddress() {
		return taxationAddress;
	}

	public void setTaxationAddress(String taxationAddress) {
		this.taxationAddress = taxationAddress;
	}

	public String getPayerPONumber() {
		return payerPONumber;
	}

	public void setPayerPONumber(String payerPONumber) {
		this.payerPONumber = payerPONumber;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSellerOfRecord() {
		return sellerOfRecord;
	}

	public void setSellerOfRecord(String sellerOfRecord) {
		this.sellerOfRecord = sellerOfRecord;
	}

	public String getUsageType() {
		return usageType;
	}

	public void setUsageType(String usageType) {
		this.usageType = usageType;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getRateId() {
		return rateId;
	}

	public void setRateId(String rateId) {
		this.rateId = rateId;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public Date getUsageStartDate() {
		return usageStartDate;
	}

	public void setUsageStartDate(Date usageStartDate) {
		this.usageStartDate = usageStartDate;
	}

	public Date getUsageEndDate() {
		return usageEndDate;
	}

	public void setUsageEndDate(Date usageEndDate) {
		this.usageEndDate = usageEndDate;
	}

	public String getUsageQuantity() {
		return usageQuantity;
	}

	public void setUsageQuantity(String usageQuantity) {
		this.usageQuantity = usageQuantity;
	}

	public String getBlendedRate() {
		return blendedRate;
	}

	public void setBlendedRate(String blendedRate) {
		this.blendedRate = blendedRate;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCostBeforeTax() {
		return costBeforeTax;
	}

	public void setCostBeforeTax(String costBeforeTax) {
		this.costBeforeTax = costBeforeTax;
	}

	public String getCredits() {
		return credits;
	}

	public void setCredits(String credits) {
		this.credits = credits;
	}

	public String getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}
	




}
