package com.awslabplatform.entity;

import java.io.Serializable;
/**
 * javaBean启动实验时所需要 的重要信息 
 * @author lijf
 * @date 2018年3月23日 上午10:55:40
 */
public class ExperimentInfo implements Serializable{
	
	private String templateUrl; //aws S3中的模板URL
	private String keyName;//连接实例密钥
	private String instanceType;
	public String getTemplateUrl() {
		return templateUrl;
	}
	public void setTemplateUrl(String templateUrl) {
		this.templateUrl = templateUrl;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getInstanceType() {
		return instanceType;
	}
	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}
}
