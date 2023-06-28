package com.awslabplatform.entity;

import java.io.Serializable;
import java.util.Date;

public class ExperimentRecord implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private String id;
	private String studentId;
	private String experimentId;
	private String stackName;
	private Date startTime;
	private Date endTime;
	private String stopTime;
	private String keyUrl;
	private String keyName;
	private String awsUrl;
	private String windowsPassword;
	private Integer status;
	private String  reason;/*CH version 用来判断启动实验错误类型*/
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getExperimentId() {
		return experimentId;
	}
	public void setExperimentId(String experimentId) {
		this.experimentId = experimentId;
	}
	public String getStackName() {
		return stackName;
	}
	public void setStackName(String stackName) {
		this.stackName = stackName;
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
	public String getStopTime() {
		return stopTime;
	}
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}
	public String getKeyUrl() {
		return keyUrl;
	}
	public void setKeyUrl(String keyUrl) {
		this.keyUrl = keyUrl;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getAwsUrl() {
		return awsUrl;
	}
	public void setAwsUrl(String awsUrl) {
		this.awsUrl = awsUrl;
	}
	public String getWindowsPassword() {
		return windowsPassword;
	}
	public void setWindowsPassword(String windowsPassword) {
		this.windowsPassword = windowsPassword;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
