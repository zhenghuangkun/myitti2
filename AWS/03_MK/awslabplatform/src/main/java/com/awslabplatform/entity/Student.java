package com.awslabplatform.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Student implements Serializable{
	private static final long serialVersionUID = 1L;


	/**
	 * 用户ID
	 */
	private String id;


	/**
	 * 学号

	 */
	private String stuId;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 联系电话
	 */
	private String phoneNum;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 密码
	 */
	private String userPwd;

	/**
	 * 学校ID
	 */
	private String schoolId;

	private String schoolName;

	/**
	 * 机构ID （学院ID）
	 */
	private String mechanismId;

	/**
	 * 机构名称
	 */
	private String mechanismName;

	/**
	 * 专业
	 */
	private String major;

	private String majorName;
	/**
	 * 年级
	 */
	private String grade;

	private String gradeName;
	/**
	 * 班级
	 */
	private String classes;

	/**
	 * 班级名称
	 */
	private String className;


	/**
	 * 出生日期
	 */
	private String birthday;

	/**
	 * 住址
	 */
	private String address;


	/**
	 * 创建人
	 */
	private String createBy;
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 正在跑的实验
	 */
	private String runningExperiment;

	/**
	 * 用户状态(删除0/正常1)
	 */
	private Integer userState;

	/**
	 * 用户头像
	 */
	private String picFileId;

	private String picFileUrl ;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getMechanismId() {
		return mechanismId;
	}

	public void setMechanismId(String mechanismId) {
		this.mechanismId = mechanismId;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getMechanismName() {
		return mechanismName;
	}

	public void setMechanismName(String mechanismName) {
		this.mechanismName = mechanismName;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRunningExperiment() {
		return runningExperiment;
	}

	public void setRunningExperiment(String runningExperiment) {
		this.runningExperiment = runningExperiment;
	}

	public Integer getUserState() {
		return userState;
	}

	public void setUserState(Integer userState) {
		this.userState = userState;
	}

	public String getPicFileId() {
		return picFileId;
	}

	public void setPicFileId(String picFileId) {
		this.picFileId = picFileId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getPicFileUrl() {
		return picFileUrl;
	}

	public void setPicFileUrl(String picFileUrl) {
		this.picFileUrl = picFileUrl;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", stuId=" + stuId + ", realName="
				+ realName + ", phoneNum=" + phoneNum + ", email=" + email
				+ ", userPwd=" + userPwd + ", mechanismName=" + mechanismName
				+ ", major=" + major + ", grade=" + grade + ", className="
				+ className + ", birthday=" + birthday + ", address=" + address
				+ ", createBy=" + createBy + ", createTime=" + createTime
				+ ", runningExperiment=" + runningExperiment + ", userState="
				+ userState + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Student student = (Student) o;
		System.out.println(schoolId+","+student.schoolId);
		return Objects.equals(schoolId, student.schoolId) &&
				Objects.equals(mechanismName, student.mechanismName) &&
				Objects.equals(major, student.major) &&
				Objects.equals(grade, student.grade) &&
				Objects.equals(className, student.className) &&
				Objects.equals(birthday, student.birthday) &&
				Objects.equals(address, student.address);
	}

	@Override
	public int hashCode() {

		return Objects.hash(stuId, realName, phoneNum, email, schoolId, mechanismName, major, grade, className, birthday, address);
	}

}
