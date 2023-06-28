package com.awslabplatform_admin.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * AWSIAMPool表entity
 * 
 * @author czy
 *
 */
public class AwsIamPool implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1234231215432L;

	private String id;// AWSIAMPool ID

	private String accountID;// 院系accountID(非付款账户)

	private String payAccountID;// payAccountID
	
	private String payAccountName;// 付款账号名称

	private Integer useType;// 运用类型  0 登录控制台  1 不登陆控制台

	private Integer isUsed;// 是否被使用 0：未被使用   1：被使用

	private String currentUserID;// 当前用户ID（学生实验时候将用户ID填写上）
	
	private String currentUserName;//当前试用者名称（启动时候时候将用户名称填上）
	
	private Date createTime;// 创建时间
	
	private Date updateTime;//修改时间
	
	private Integer isActive;//激活/失效（1：激活 0：失效）

	private Integer isDelete;// 删除标志 0：未删除  1：删除

	private Integer account;
	
	private String useTypeName;
	
	private String IAMId;// IAMID
	
	private String IAMName;// 院系Account

	private String password;//默认密码
	
	private String accessKeyID;// PK

	private String accessKey;// SK
	
	private Integer IAMKind;//0: 表示管理人员使用    1：表示实验者使用
	
	private String copyAccountID;
	
	private String copyIamName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getPayAccountID() {
		return payAccountID;
	}

	public void setPayAccountID(String payAccountID) {
		this.payAccountID = payAccountID;
	}

	public String getPayAccountName() {
		return payAccountName;
	}

	public void setPayAccountName(String payAccountName) {
		this.payAccountName = payAccountName;
	}

	public Integer getUseType() {
		return useType;
	}

	public void setUseType(Integer useType) {
		this.useType = useType;
	}

	public Integer getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}

	public String getCurrentUserID() {
		return currentUserID;
	}

	public void setCurrentUserID(String currentUserID) {
		this.currentUserID = currentUserID;
	}

	public String getCurrentUserName() {
		return currentUserName;
	}

	public void setCurrentUserName(String currentUserName) {
		this.currentUserName = currentUserName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	public String getUseTypeName() {
		return useTypeName;
	}

	public void setUseTypeName(String useTypeName) {
		this.useTypeName = useTypeName;
	}

	public String getIAMId() {
		return IAMId;
	}

	public void setIAMId(String iAMId) {
		IAMId = iAMId;
	}

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

	public Integer getIAMKind() {
		return IAMKind;
	}

	public void setIAMKind(Integer iAMKind) {
		IAMKind = iAMKind;
	}

	public String getCopyAccountID() {
		return copyAccountID;
	}

	public void setCopyAccountID(String copyAccountID) {
		this.copyAccountID = copyAccountID;
	}

	public String getCopyIamName() {
		return copyIamName;
	}

	public void setCopyIamName(String copyIamName) {
		this.copyIamName = copyIamName;
	}
	
}
