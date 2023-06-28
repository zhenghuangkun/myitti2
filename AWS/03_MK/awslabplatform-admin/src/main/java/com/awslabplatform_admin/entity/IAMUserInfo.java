package com.awslabplatform_admin.entity;

public class IAMUserInfo {

	private String IAMName;
	
	private String password;
	
	private String accessKeyID;
	
	private String accessKey;
	
	private String consoleLoginLink;

	public String getIAMName() {
		return IAMName;
	}

	public void setIAMName(String iAMName) {
		IAMName = iAMName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getConsoleLoginLink() {
		return consoleLoginLink;
	}

	public void setConsoleLoginLink(String consoleLoginLink) {
		this.consoleLoginLink = consoleLoginLink;
	}

	@Override
	public String toString() {
		return "IAMUserInfo [IAMName=" + IAMName + ", password=" + password
				+ ", accessKeyID=" + accessKeyID + ", accessKey=" + accessKey
				+ ", consoleLoginLink=" + consoleLoginLink + "]";
	}

}
