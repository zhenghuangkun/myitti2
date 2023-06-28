package com.awslabplatform.entity;

public class CredentialsAndRegion {
	private String id;/*CH verison 2.0 新增属性 IAMID*/
	private String region="cn-northwest-1";
	private String accessKeyID;
	private String accessKey;
    private String iamName;/*CH version 2.0 新增属性 IAM用户名*/
	private String  accountId;/*CH version 2.0 新增属性 账号池账号的 accountId*/

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getAccessKeyID() {
		return accessKeyID;
	}
	public void setAccessKeyID(String accessKeyID) {
		this.accessKeyID = accessKeyID;
	}
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getIamName() {
		return iamName;
	}

	public void setIamName(String iamName) {
		this.iamName = iamName;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
}
