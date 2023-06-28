package com.awslabplatform.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author lijf
 * date 2018年3月18日 上午10:12:02
 */
public class Course implements Serializable{
	private String courseId;
	private String collegeId;
	private String specialtyId;
	private String teacherId;
	private String courseName;
	private String description;
	private Date createTime;
	private String coursePicFileId;
	private boolean commentAllowable;
	private int useCount;
	private float avgScore;
	private int evaluateAmount;
	private int status;
	private String courseType;
	private String courseTypeLevel;
	private int openRange;
	private Date updateTime;
	private boolean courseStartUpType;
	private Date courseStratTime;
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}
	public String getSpecialtyId() {
		return specialtyId;
	}
	public void setSpecialtyId(String specialtyId) {
		this.specialtyId = specialtyId;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
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
	public String getCoursePicFileId() {
		return coursePicFileId;
	}
	public void setCoursePicFileId(String coursePicFileId) {
		this.coursePicFileId = coursePicFileId;
	}
	public boolean isCommentAllowable() {
		return commentAllowable;
	}
	public void setCommentAllowable(boolean commentAllowable) {
		this.commentAllowable = commentAllowable;
	}
	public int getUseCount() {
		return useCount;
	}
	public void setUseCount(int useCount) {
		this.useCount = useCount;
	}
	public float getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(float avgScore) {
		this.avgScore = avgScore;
	}
	public int getEvaluateAmount() {
		return evaluateAmount;
	}
	public void setEvaluateAmount(int evaluateAmount) {
		this.evaluateAmount = evaluateAmount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public String getCourseTypeLevel() {
		return courseTypeLevel;
	}
	public void setCourseTypeLevel(String courseTypeLevel) {
		this.courseTypeLevel = courseTypeLevel;
	}
	public int getOpenRange() {
		return openRange;
	}
	public void setOpenRange(int openRange) {
		this.openRange = openRange;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public boolean isCourseStartUpType() {
		return courseStartUpType;
	}
	public void setCourseStartUpType(boolean courseStartUpType) {
		this.courseStartUpType = courseStartUpType;
	}
	public Date getCourseStratTime() {
		return courseStratTime;
	}
	public void setCourseStratTime(Date courseStratTime) {
		this.courseStratTime = courseStratTime;
	}
}