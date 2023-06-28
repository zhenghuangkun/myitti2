package com.awslabplatform.entity;

import java.io.Serializable;
import java.util.Date;

public class CourseComment implements Serializable{
	private String commentId;
	private String userId;
	private String courseId;
	private String content;
	private Date commentTime;
	private int score;
	private Student student;
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "CourseComment [commentId=" + commentId + ", userId=" + userId
				+ ", courseId=" + courseId + ", content=" + content
				+ ", commentTime=" + commentTime + ", score=" + score + "]";
	}
}
