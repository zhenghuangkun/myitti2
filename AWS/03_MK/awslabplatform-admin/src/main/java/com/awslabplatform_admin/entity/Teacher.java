package com.awslabplatform_admin.entity;

import java.util.Date;

public class Teacher {
	private	 String id;

	private String realName;

	private String IAM;

	private int mechanismId;

	private String phoneNum;

	private String email;

	private Date birthday;

	private String address;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIAM() {
		return IAM;
	}

	public void setIAM(String IAM) {
		this.IAM = IAM;
	}

	public int getMechanismId() {
		return mechanismId;
	}

	public void setMechanismId(int mechanismId) {
		this.mechanismId = mechanismId;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
