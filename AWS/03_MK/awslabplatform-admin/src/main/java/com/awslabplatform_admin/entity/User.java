package com.awslabplatform_admin.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户类
 * 
 * @author weix
 *
 */
public class User implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 380154045822708243L;

	private String userId;// 用户ID

	private String phoneNum;// 联系电话

	private String email;// 邮箱

	private String realName;// 真实姓名

	private String userPwd;// 密码

	private Integer roleType;// 角色类型

	private String awsAccount;// ACCOUNT 账号
	
	private Integer isAwsIam;//是否绑定aws iam 0是绑定 1不绑定

	private String IAM;// IAM 账号

	private String schoolId;// 学校Id

	private String departmentId; // 学院Id

	private String birthday;// 出生年月

	private String address;// 地址

	private String name;// 用户名(ztree使用)

	private String createBy;// 创建人

	private Date createTime;// 创建时间

	private Integer userState;// 用户状态(删除0/正常1)
	
	private String roleId;//用户类型

	private String accountName;// account账户名称

	private String iamName;// IAM账户名称

	private String mechanismName;// 机构学校名称
	
	private String departmentName;//院系名称
	
	private String copyPhoneNum;//编辑电话号码是否存在
	
	private String copyEmail;//编辑邮箱是否存在
	
	private String copyIamName;//编辑IAM是否修改了
	
	private String currentUserId;//当前用户登录ID
	
	private String currentCreateBy;//当前用户登录用户名
	
	private String accountId;//account id

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUserState() {
		return userState;
	}

	public void setUserState(Integer userState) {
		this.userState = userState;
	}

	public String getAccountName() {
		return accountName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getIamName() {
		return iamName;
	}

	public void setIamName(String iamName) {
		this.iamName = iamName;
	}

	public String getMechanismName() {
		return mechanismName;
	}

	public void setMechanismName(String mechanismName) {
		this.mechanismName = mechanismName;
	}
	
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getCopyIamName() {
		return copyIamName;
	}

	public void setCopyIamName(String copyIamName) {
		this.copyIamName = copyIamName;
	}

	public String getCopyPhoneNum() {
		return copyPhoneNum;
	}

	public void setCopyPhoneNum(String copyPhoneNum) {
		this.copyPhoneNum = copyPhoneNum;
	}

	public String getCopyEmail() {
		return copyEmail;
	}

	public void setCopyEmail(String copyEmail) {
		this.copyEmail = copyEmail;
	}

	public String getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(String currentUserId) {
		this.currentUserId = currentUserId;
	}

	public String getCurrentCreateBy() {
		return currentCreateBy;
	}

	public void setCurrentCreateBy(String currentCreateBy) {
		this.currentCreateBy = currentCreateBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* 静态区 */
	/**
	 * 超级管理员
	 */
	public static int ADMINISTRATION_USER = 0;
	/**
	 * 平台管理员
	 */
	public static int PLATFORM_MANAGE_USER = 1;
	/**
	 * 院系管理员
	 */
	public static int DEPARTMENT_MANAGE_USER = 2;
	/**
	 * 教师
	 */
	public static int TEARCH_USER = 3;
	/**
	 * 助教
	 */
	public static int ASSISTANT_USER = 4;
	/**
	 * 学生
	 */
	public static int STUDENT_USER = 5;

}
