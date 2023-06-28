package com.awslabplatform.entity;

import java.io.Serializable;

public class MyCourse implements Serializable{
	private String id;
	private String studentId;
	private String courseId;
	private String courseName;
	private String coursePic;
	private Integer experimentCount;
	private String lastExperimentId;
	private String lastExperimentName;
	private Integer lastExperimentNo;
	private String finishedExperimentIds;
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
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCoursePic() {
		return coursePic;
	}
	public void setCoursePic(String coursePic) {
		this.coursePic = coursePic;
	}
	public Integer getExperimentCount() {
		return experimentCount;
	}
	public void setExperimentCount(Integer experimentCount) {
		this.experimentCount = experimentCount;
	}
	public String getLastExperimentId() {
		return lastExperimentId;
	}
	public void setLastExperimentId(String lastExperimentId) {
		this.lastExperimentId = lastExperimentId;
	}
	public String getLastExperimentName() {
		return lastExperimentName;
	}
	public void setLastExperimentName(String lastExperimentName) {
		this.lastExperimentName = lastExperimentName;
	}
	public Integer getLastExperimentNo() {
		return lastExperimentNo;
	}
	public void setLastExperimentNo(Integer lastExperimentNo) {
		this.lastExperimentNo = lastExperimentNo;
	}
	public String getFinishedExperimentIds() {
		return finishedExperimentIds;
	}
	public void setFinishedExperimentIds(String finishedExperimentIds) {
		this.finishedExperimentIds = finishedExperimentIds;
	}
	@Override
	public String toString() {
		return "MyCourse [id=" + id + ", studentId=" + studentId
				+ ", courseId=" + courseId + ", courseName=" + courseName
				+ ", coursePic=" + coursePic + ", experimentCount="
				+ experimentCount + ", lastExperimentId=" + lastExperimentId
				+ ", lastExperimentName=" + lastExperimentName
				+ ", lastExperimentNo=" + lastExperimentNo
				+ ", finishedExperimentIds=" + finishedExperimentIds + "]";
	}
	
}
