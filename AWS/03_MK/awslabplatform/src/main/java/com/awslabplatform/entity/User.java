package com.awslabplatform.entity;

import java.io.Serializable;

public class User implements Serializable{
	private String userId;
	private String phoneNum;
	private String email;
	private String realName;
	private String userPwd;
	private Integer roleType;
	private String awsAccount;
	private Integer isAwsIam;
	private String IAM;
	private String schoolId;
	private String departmentId;
	private String birthday;
	private String address;
	private String createBy;
	private String createTime;
	private Integer userState;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public Integer getRoleType() {
		return roleType;
	}
	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}
	public String getAwsAccount() {
		return awsAccount;
	}
	public void setAwsAccount(String awsAccount) {
		this.awsAccount = awsAccount;
	}
	public Integer getIsAwsIam() {
		return isAwsIam;
	}
	public void setIsAwsIam(Integer isAwsIam) {
		this.isAwsIam = isAwsIam;
	}
	public String getIAM() {
		return IAM;
	}
	public void setIAM(String iAM) {
		IAM = iAM;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getUserState() {
		return userState;
	}
	public void setUserState(Integer userState) {
		this.userState = userState;
	}
}
