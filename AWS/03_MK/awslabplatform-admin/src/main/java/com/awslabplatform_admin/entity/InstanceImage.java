package com.awslabplatform_admin.entity;

import java.io.Serializable;


/**
 * 实例镜像
 * @author weixin
 * @version 2018年4月15日
 */
public class InstanceImage implements Serializable {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	private String id;
	
	/**
	 *镜像ID
	 */
	private String imageId;
	
	/**
	 * 实例ID
	 */
	private String instanceId;
	
	/**
	 * 实例类型
	 */
	private String instanceType;
	
	/**
	 *镜像名称
	 */
	private String imageName;
	
	/**
	 * 凭证ID
	 */
	private String accessKeyID;
	
	/**
	 * 凭证
	 */
	private String accessKey;
	
	/**
	 * 地区
	 */
	private String region;
	/**
	 * 描述
	 */
	private String imageDescribe;
	
	/**
	 * 镜像状态
	 */
	private Integer imageState;
	
	/**
	 * 操作时间
	 */
	private String optTime;
	/**
	 * 操作人
	 */
	private String operator;
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Integer getImageState() {
		return imageState;
	}
	public void setImageState(Integer imageState) {
		this.imageState = imageState;
	}
	public String getOptTime() {
		return optTime;
	}
	public void setOptTime(String optTime) {
		this.optTime = optTime;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImageDescribe() {
		return imageDescribe;
	}
	public void setImageDescribe(String imageDescribe) {
		this.imageDescribe = imageDescribe;
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
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getInstanceType() {
		return instanceType;
	}
	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}
	
	
	
}
