package com.awslabplatform.entity;

import java.util.Date;

public class StudentExperment {

    private String  id;//IAMID

	private String  iamName;//IAM 用户名

	private String  password;//IAM 密码

	private String  consoleLoginLink;//IAM 登录的URL

	private String  payAccountID;//付款账号

	private String  accountId;//实验IAM accountId;

	private String endTime;//实验结束时间；

	private String experimentId;//实验Id

	private int runningTime;//实验时长

	private String  poolUsed;//判断账号池账号的情况

	public StudentExperment() {

	}


	public StudentExperment(String id, String iamName, String password, String consoleLoginLink, String payAccountID, String endTime, String poolUsed) {
		this.id = id;
		this.iamName = iamName;
		this.password = password;
		this.consoleLoginLink = consoleLoginLink;
		this.payAccountID = payAccountID;
		this.endTime = endTime;
		this.poolUsed = poolUsed;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIamName() {
		return iamName;
	}

	public void setIamName(String iamName) {
		this.iamName = iamName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConsoleLoginLink() {
		return consoleLoginLink;
	}

	public void setConsoleLoginLink(String consoleLoginLink) {
		this.consoleLoginLink = consoleLoginLink;
	}

	public String getPayAccountID() {
		return payAccountID;
	}

	public void setPayAccountID(String payAccountID) {
		this.payAccountID = payAccountID;
	}

	public String getPoolUsed() {
		return poolUsed;
	}

	public void setPoolUsed(String poolUsed) {
		this.poolUsed = poolUsed;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getExperimentId() {
		return experimentId;
	}

	public void setExperimentId(String experimentId) {
		this.experimentId = experimentId;
	}

	public int getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}
}
