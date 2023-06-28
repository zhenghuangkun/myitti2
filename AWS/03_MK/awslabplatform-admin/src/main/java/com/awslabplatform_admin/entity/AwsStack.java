package com.awslabplatform_admin.entity;

import java.io.Serializable;
import java.util.List;

import com.amazonaws.services.cloudformation.model.Parameter;
import com.amazonaws.services.cloudformation.model.Tag;

/**
 * 
 * @author weixin
 * @version 2018年4月9日
 */
public class AwsStack implements Serializable {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *堆栈ID
	 */
	private String stackId;
	
	/**
	 * 栈名
	 */
	private String stackName;
	
	/**
	 * 模板路径
	 */
	private String tmplUrl;
	
	/**
	 * 区域
	 */
	private String region;
	
	/**
	 * 用户真实姓名
	 */
	private String realName;
	
	/**
	 * IAM用户名称
	 */
	private String IAMName;
	
	/**
	 * 学校
	 */
	private String school;
	
	/**
	 * 院系
	 */
	private String department;
	
	/**
	 * 访问keyID
	 */
	private String accessKeyID;
	
	/**
	 * 访问key
	 */
	private String accessKey;
	
	/**
	 * 标签
	 */
	private List<Tag> Tags;
	
	/**
	 * EC2实例ID
	 */
	private List<String> instanceIds;
	
	/**
	 * 模板参数
	 */
	private List<Parameter> parameters;
	

	public String getStackId() {
		return stackId;
	}

	public void setStackId(String stackId) {
		this.stackId = stackId;
	}

	public String getStackName() {
		return stackName;
	}

	public void setStackName(String stackName) {
		this.stackName = stackName;
	}

	public String getTmplUrl() {
		return tmplUrl;
	}

	public void setTmplUrl(String tmplUrl) {
		this.tmplUrl = tmplUrl;
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

	public List<Tag> getTags() {
		return Tags;
	}

	public void setTags(List<Tag> tags) {
		Tags = tags;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIAMName() {
		return IAMName;
	}

	public void setIAMName(String iAMName) {
		IAMName = iAMName;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<String> getInstanceIds() {
		return instanceIds;
	}

	public void setInstanceIds(List<String> instanceIds) {
		this.instanceIds = instanceIds;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

}
