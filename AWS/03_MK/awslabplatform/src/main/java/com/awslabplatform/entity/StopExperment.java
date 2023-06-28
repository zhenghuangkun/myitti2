package com.awslabplatform.entity;

public class StopExperment {
	private String id;/*CH verison 2.0  IAMID 实验IAMID*/
	private String accessKeyID;/*管理员 accessKeyID*/
	private String accessKey;/*管理员 accessKey*/
	private String iamName;/*CH version 2.0  实验IAMName*/
	private String accountId;/*CH version 2.0  账号池里 accountId*/

	public StopExperment(String id, String accessKeyID, String accessKey, String iamName, String accountId) {
		this.id = id;
		this.accessKeyID = accessKeyID;
		this.accessKey = accessKey;
		this.iamName = iamName;
		this.accountId = accountId;
	}

	public StopExperment() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
