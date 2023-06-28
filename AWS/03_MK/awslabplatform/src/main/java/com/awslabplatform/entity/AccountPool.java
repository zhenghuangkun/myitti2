package com.awslabplatform.entity;

public class AccountPool {

	private String id;//账号池ID

	private String  isUsed;//账号池账号的使用状态

	private String  account;//账号池账号的使用次数

	private String  accountId;//账号池账号

	private String  payAccountId;//付费账号






	public AccountPool() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getPayAccountId() {
		return payAccountId;
	}

	public void setPayAccountId(String payAccountId) {
		this.payAccountId = payAccountId;
	}
}
