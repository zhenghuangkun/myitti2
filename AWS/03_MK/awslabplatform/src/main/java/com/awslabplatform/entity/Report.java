package com.awslabplatform.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 实验报告类
 * @author lijf
 * @date 2018年4月3日 上午8:49:51
 */
public class Report implements Serializable{
	/**
	 * id
	 */
	private String id;
	/**
	 * 实验id
	 */
	private String experimentId;
	/**
	 * 学生id
	 */
	private String studentId;
	/**
	 * 实验名称
	 */
	private String experimentName;
	/**
	 * 报告内容
	 */
	private String content;
	/**
	 * 文件id
	 */
	private String filedId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 评语内容
	 */
	private String comment;
	/**
	 * 分数
	 */
	private Integer score;
	/**
	 * 评论时间
	 */
	private Date remarkTime;
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
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Date getRemarkTime() {
		return remarkTime;
	}
	public void setRemarkTime(Date remarkTime) {
		this.remarkTime = remarkTime;
	}
	@Override
	public String toString() {
		return "Report [id=" + id + ", experimentId=" + experimentId
				+ ", studentId=" + studentId + ", experimentName="
				+ experimentName + ", content=" + content + ", filedId="
				+ filedId + ", createTime=" + createTime + ", comment="
				+ comment + ", score=" + score + ", remarkTime=" + remarkTime
				+ "]";
	}
}
