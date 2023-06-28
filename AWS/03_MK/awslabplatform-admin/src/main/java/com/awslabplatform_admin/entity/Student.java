package com.awslabplatform_admin.entity;

public class Student {
	private String id;

	private String stuId;// 学号

	private String realName;// 真实名

	private String phoneNum;// 手机号

	private String email;// 电子邮箱

	private String userPwd;// 密码

	private String schoolId;// 学校id

	private String schoolName;// 学校名称

	private String mechanismId;// 所属机构（院系）ID

	private String mechanismName;// 院系名

	private String major;// 专业ID

	private String majorName;// 专业名

	private String grade;// 年级ID

	private String gradeName;// 年级名

	private String classes; // 班级ID

	private String classesName;// 班级名

	private String birthday;// 生日

	private String address;// 地址

	private String runningExperiment;// 正在运行的实验

	private Integer userState;// 用户状态(删除0/正常1)

	private String linkedUserId;// 关联用户ID

	private String name;// 学生名用户名(ztree使用) = realName

	private String IAM;// IAM

	private String picFileId;// 学生头像ID

	private String picFileUrl;// 学生头像URL

	private String copyStuId;// 编辑是否学号存在

	private String copyPhoneNum;// 编辑是否电话号码存在

	private String copyEmail;// 编辑是否邮箱存在
	
	private String experimentName;//当前学生正在做的实验名称

	public String getExperimentName() {
		return experimentName;
	}

	public void setExperimentName(String experimentName) {
		this.experimentName = experimentName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getCopyStuId() {
		return copyStuId;
	}

	public void setCopyStuId(String copyStuId) {
		this.copyStuId = copyStuId;
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

	public String getLinkedUserId() {
		return linkedUserId;
	}

	public void setLinkedUserId(String linkedUserId) {
		this.linkedUserId = linkedUserId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIAM() {
		return IAM;
	}

	public void setIAM(String iAM) {
		IAM = iAM;
	}

	public String getMechanismId() {
		return mechanismId;
	}

	public void setMechanismId(String mechanismId) {
		this.mechanismId = mechanismId;
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

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getClassesName() {
		return classesName;
	}

	public void setClassesName(String classesName) {
		this.classesName = classesName;
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

	public String getPicFileUrl() {
		return picFileUrl;
	}

	public void setPicFileUrl(String picFileUrl) {
		this.picFileUrl = picFileUrl;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getCopyPhoneNum() {
		return copyPhoneNum;
	}

	public void setCopyPhoneNum(String copyPhoneNum) {
		this.copyPhoneNum = copyPhoneNum;
	}

	public String getCopyEmail() {
		return copyEmail;
	}

	public void setCopyEmail(String copyEmail) {
		this.copyEmail = copyEmail;
	}

}
