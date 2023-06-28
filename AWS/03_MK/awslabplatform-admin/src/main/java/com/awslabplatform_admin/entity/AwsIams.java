package com.awslabplatform_admin.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * AWSIAM表entity
 * 
 * @author czy
 *
 */
public class AwsIams implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1234231215432L;

	private String id;// AWSIAM ID

	private String iAMName;// IAM 用户名

	private String awsAccount;// 关联Account ID

	private String accessKeyID;// 访问keyID

	private String accessKey;// 访问key
	
	private String password;//默认密码
	
	private Integer IAMKind;//0: 表示管理人员使用    1：表示实验者使用
	
	private String consoleLoginLink;//登录控制台链接
	
	private Integer iAMStatus;// IAM 状态（0 可用 1 不可用）

	private Date createTime;// 创建时间
	
	private String iAMtype;// IAM 类型数据库字典查询
	
	private String tb_accountid;// Account账户ID
	
	private String region;//区域
	
	private Integer isUsed;// 是否被使用
	
	private String operator;

	private String copyIamName;
	
	private String itemValue;
	

	public String getTb_accountid() {
		return tb_accountid;
	}

	public void setTb_accountid(String tb_accountid) {
		this.tb_accountid = tb_accountid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getiAMName() {
		return iAMName;
	}

	public void setiAMName(String iAMName) {
		this.iAMName = iAMName;
	}

	public String getAwsAccount() {
		return awsAccount;
	}

	public void setAwsAccount(String awsAccount) {
		this.awsAccount = awsAccount;
	}

	public String getiAMtype() {
		return iAMtype;
	}

	public void setiAMtype(String iAMtype) {
		this.iAMtype = iAMtype;
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
	
	public Integer getIAMKind() {
		return IAMKind;
	}

	public void setIAMKind(Integer iAMKind) {
		IAMKind = iAMKind;
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

	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Integer getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}

	public Integer getiAMStatus() {
		return iAMStatus;
	}

	public void setiAMStatus(Integer iAMStatus) {
		this.iAMStatus = iAMStatus;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCopyIamName() {
		return copyIamName;
	}

	public void setCopyIamName(String copyIamName) {
		this.copyIamName = copyIamName;
	}
	
	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
