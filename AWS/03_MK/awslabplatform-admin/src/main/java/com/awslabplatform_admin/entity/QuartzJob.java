package com.awslabplatform_admin.entity;

import java.io.Serializable;

/**
 * QuartzJob任务实体类
 * @author weixin
 * @version 2018年4月12日
 */
public class QuartzJob implements Serializable{

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 任务ID
	 */
	private String jobId;
	
	/**
	 * 任务名称
	 */
	private String jobName;
	/**
	 * 任务组名称
	 */
	private String jobGroupName;
	/**
	 * 触发器名称
	 */
	private String triggerName;
	/**
	 * 触发器组名称
	 */
	private String triggerGroupName;
	/**
	 * 任务类型（执行类）
	 */
	private String jobClass;
	
	/**
	 * 任务启动时间
	 */
	private String jobStartTime;
	
	/**
	 * 任务结束时间
	 */
	private String jobEndTime;
	
	/**
	 * 状态
	 */
	private Integer jobState;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroupName() {
		return jobGroupName;
	}

	public void setJobGroupName(String jobGroupName) {
		this.jobGroupName = jobGroupName;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getTriggerGroupName() {
		return triggerGroupName;
	}

	public void setTriggerGroupName(String triggerGroupName) {
		this.triggerGroupName = triggerGroupName;
	}

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	public String getJobStartTime() {
		return jobStartTime;
	}

	public void setJobStartTime(String jobStartTime) {
		this.jobStartTime = jobStartTime;
	}

	public String getJobEndTime() {
		return jobEndTime;
	}

	public void setJobEndTime(String jobEndTime) {
		this.jobEndTime = jobEndTime;
	}

	public Integer getJobState() {
		return jobState;
	}

	public void setJobState(Integer jobState) {
		this.jobState = jobState;
	}
	
}
