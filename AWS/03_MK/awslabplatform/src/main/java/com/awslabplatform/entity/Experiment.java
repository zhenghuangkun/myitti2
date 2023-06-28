package com.awslabplatform.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 实验类eneity
 * @author lijf
 * date 2018年3月20日 下午2:05:31
 */
public class Experiment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String experimentId;
	private Integer experimentNo;
	private String templateUrl;
	private String courseId;
	private String experimentName;
	private String description;
	private Date createTime;
	private String guideUrl;
	private int runtime;
	private int exeperimentType;
	private String keyName;
	private String keyUrl;
	private String instanceType;
	private Date startTime;
	private Date endTime;
	private String policyFileUrl;
	private String region;
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getInstanceType() {
		return instanceType;
	}
	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}
	public String getExperimentId() {
		return experimentId;
	}
	public void setExperimentId(String experimentId) {
		this.experimentId = experimentId;
	}
	
	public Integer getExperimentNo() {
		return experimentNo;
	}
	public void setExperimentNo(Integer experimentNo) {
		this.experimentNo = experimentNo;
	}
	public String getTemplateUrl() {
		return templateUrl;
	}
	public void setTemplateUrl(String templateUrl) {
		this.templateUrl = templateUrl;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getExperimentName() {
		return experimentName;
	}
	public void setExperimentName(String experimentName) {
		this.experimentName = experimentName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getGuideUrl() {
		return guideUrl;
	}
	public void setGuideUrl(String guideUrl) {
		this.guideUrl = guideUrl;
	}
	public int getRuntime() {
		return runtime;
	}
	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}
	public int getExeperimentType() {
		return exeperimentType;
	}
	public void setExeperimentType(int exeperimentType) {
		this.exeperimentType = exeperimentType;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getKeyUrl() {
		return keyUrl;
	}
	public void setKeyUrl(String keyUrl) {
		this.keyUrl = keyUrl;
	}
	public String getPolicyFileUrl() {
		return policyFileUrl;
	}
	public void setPolicyFileUrl(String policyFileUrl) {
		this.policyFileUrl = policyFileUrl;
	}
	@Override
	public String toString() {
		return "Experiment [experimentId=" + experimentId + ", experimentNo="
				+ experimentNo + ", templateUrl=" + templateUrl + ", courseId="
				+ courseId + ", experimentName=" + experimentName
				+ ", description=" + description + ", createTime=" + createTime
				+ ", guideUrl=" + guideUrl + ", runtime=" + runtime
				+ ", exeperimentType=" + exeperimentType + ", keyName="
				+ keyName + ", keyUrl=" + keyUrl + ", instanceType="
				+ instanceType + "]";
	}

}
