package com.awslabplatform_admin.entity;

import java.util.Date;

public class QuotaAllocation {
	
	private String id;// QuotaAllocation ID
	
	private String accountId;//account Id
	
	private String totalAmount;//总额度

	private String invoiceAmount;//已开发票金额
	
	private String totalMonth;//修改月份


	public String getTotalMonth() {
		return totalMonth;
	}

	public void setTotalMonth(String totalMonth) {
		this.totalMonth = totalMonth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

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

	

}
