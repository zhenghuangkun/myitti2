package com.awslabplatform_admin.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * AWSAccount表entity
 * 
 * @author czy
 *
 */
public class AwsAccounts implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 122121212123231L;

	private String id;// AWSAccount ID

	private String accountId;// 付款账户ID

	private String accountName;// 付款账户名称

	private String org;// 所属机构
	
	private String departmentId;// 所属院系

	private String email;// 关联邮箱

	private Integer isUPT;// 是否是UPT所有
	
	private String UPTName;// 是否
	
	private String IAM;//IAM账号
	
	private String AK;//access key ID
	
	private String SK;//access key
	
	private String operate;// 创建方式（join\ADD）

	private Date createTime;// 加入或者创建时间

	private String copyAccountId;// 备份AccountId

	private String copyAccountName;// 备份AccountName

	private String orgName;// 机构名称显示在列表上
	
	private String departmentName;// 院系名称显示在列表上

	private Integer accountStause;// 删除状态，0表示可用数据，1表示删除状态
	
	private Integer isActive;//激活/失效   0：激活  1：失效
	
	private Integer isPayingAccount;//是否为主账号
	
	private String payingAccountId;// 主账户ID

	private String payingAccountName;// 主账户名称

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

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}
	
	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIsUPT() {
		return isUPT;
	}

	public void setIsUPT(Integer isUPT) {
		this.isUPT = isUPT;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCopyAccountId() {
		return copyAccountId;
	}

	public void setCopyAccountId(String copyAccountId) {
		this.copyAccountId = copyAccountId;
	}

	public String getCopyAccountName() {
		return copyAccountName;
	}

	public void setCopyAccountName(String copyAccountName) {
		this.copyAccountName = copyAccountName;
	}

	public Integer getAccountStause() {
		return accountStause;
	}

	public void setAccountStause(Integer accountStause) {
		this.accountStause = accountStause;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIAM() {
		return IAM;
	}

	public void setIAM(String iAM) {
		IAM = iAM;
	}

	public String getAK() {
		return AK;
	}

	public void setAK(String aK) {
		AK = aK;
	}

	public String getSK() {
		return SK;
	}

	public void setSK(String sK) {
		SK = sK;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public String getUPTName() {
		return UPTName;
	}

	public void setUPTName(String uPTName) {
		UPTName = uPTName;
	}

	public Integer getIsPayingAccount() {
		return isPayingAccount;
	}

	public void setIsPayingAccount(Integer isPayingAccount) {
		this.isPayingAccount = isPayingAccount;
	}

	public String getPayingAccountId() {
		return payingAccountId;
	}

	public void setPayingAccountId(String payingAccountId) {
		this.payingAccountId = payingAccountId;
	}

	public String getPayingAccountName() {
		return payingAccountName;
	}

	public void setPayingAccountName(String payingAccountName) {
		this.payingAccountName = payingAccountName;
	}

}
