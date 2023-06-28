package com.awslabplatform_admin.entity;

import java.util.Date;

public class Report {
	private String id;

	private String experimentId;

	private String studentId;

	private String experimentName;

	private Integer experimentNo;
	
	private String 	content;

	private String filedId;

	private Date createTime;

	private String comment;

	private int score;

	private Date remarkTime;

	private Integer runtime;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExperimentId() {
		return experimentId;
	}

	public void setExperimentId(String experimentId) {
		this.experimentId = experimentId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getExperimentName() {
		return experimentName;
	}

	public void setExperimentName(String experimentName) {
		this.experimentName = experimentName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFiledId() {
		return filedId;
	}

	public void setFiledId(String filedId) {
		this.filedId = filedId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getRemarkTime() {
		return remarkTime;
	}

	public void setRemarkTime(Date remarkTime) {
		this.remarkTime = remarkTime;
	}

	public Integer getExperimentNo() {
		return experimentNo;
	}

	public void setExperimentNo(Integer experimentNo) {
		this.experimentNo = experimentNo;
	}

	public Integer getRuntime() {
		return runtime;
	}

	public void setRuntime(Integer runtime) {
		this.runtime = runtime;
	}
	
	
}
