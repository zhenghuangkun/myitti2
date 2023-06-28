package com.awslabplatform_admin.entity;

import java.io.Serializable;


/**
 * AWS实例对象
 * @author weixin
 * @version 2018年4月9日
 */
public class AwsInstance implements Serializable {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *数据ID
	 */
	private String id;
	
	/**
	 * 关联ID
	 */
	private String correlationId;
	
	/**
	 *实例名称
	 */
	private String instanceName;
	
	/**
	 *实例ID
	 */
	private String instanceId;
	
	/**
	 *申请ID
	 */
	private String applyId;
	
	/**
	 *模板ID
	 */
	private String tmplId;
	
	
	/**
	 * 密钥名
	 */
	private String keyName;
	
	/**
	 * 密钥链接
	 */
	private String keyPairUrl;
	
	/**
	 * 共有IP
	 */
	private String publicIpAddress;
	/**
	 * 私有IP
	 */
	private String privateIpAddress;
	
	/**
	 * 实例类型
	 */
	private String instanceType;
	
	/**
	 * 实例状态
	 */
	private String instanceState;
	
	/**
	 * 操作用户
	 */
	private String operator;
	
	/**
	 * 操作时间
	 */
	private String optTime;
	

	/**
	 * 实验结束时间
	 * @return
	 */
	private String useEndTime;
	
	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getPublicIpAddress() {
		return publicIpAddress;
	}

	public void setPublicIpAddress(String publicIpAddress) {
		this.publicIpAddress = publicIpAddress;
	}

	public String getPrivateIpAddress() {
		return privateIpAddress;
	}

	public void setPrivateIpAddress(String privateIpAddress) {
		this.privateIpAddress = privateIpAddress;
	}

	public String getInstanceType() {
		return instanceType;
	}

	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}

	public String getInstanceState() {
		return instanceState;
	}

	public void setInstanceState(String instanceState) {
		this.instanceState = instanceState;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public String getKeyPairUrl() {
		return keyPairUrl;
	}

	public void setKeyPairUrl(String keyPairUrl) {
		this.keyPairUrl = keyPairUrl;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOptTime() {
		return optTime;
	}

	public void setOptTime(String optTime) {
		this.optTime = optTime;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getTmplId() {
		return tmplId;
	}

	public void setTmplId(String tmplId) {
		this.tmplId = tmplId;
	}

	public String getUseEndTime() {
		return useEndTime;
	}

	public void setUseEndTime(String useEndTime) {
		this.useEndTime = useEndTime;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}
	
}
