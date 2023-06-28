package com.awslabplatform_admin.entity;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户类
 * @author zhenghk
 *
 */
public class Course implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 380154045822708243L;
	/**
	 * 课程ID
	 */
	private String courseId;
	/**
	 * 学院Id
	 */
	private String collegeId;

	/**
	 * 课程名
	 */
	private String courseName;

	/**
	 * 课程教师
	 */
	private String teacherId;

	/**
	 * 专业名
	 */
	private String specialtyId;
	/**
	 * 课程描述
	 * */
	private String description;

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 课程图片文件Id
	 */
	private String coursePicFileId;
	/**
	 * 课程图片URL
	 */
	private String coursePicUrl;
	/**
	 * 课程是否允许评价
	 */
	private boolean commentAllowable;

	/**
	 * 已学习人数
	 */
	private Integer useCount;

	/**
	 * 课程平均分
	 */
	private Float avgScore;

	/**
	 * 课程评论人数
	 */
	private Integer evaluateAmount;

	/**
	 * 课程状态(已发布5/已通过6/未通过4/已提交3/未提交2/终止1/已删除0)
	 */
	private Integer status;

	/**
	 * 课程类型
	 */
	private String courseType;
	/**
	 * 课程类型
	 */
	private String courseTypeName;

	/**
	 * 课程类型
	 */
	private String courseTypeLevel;

	/**
	 * 课程公开范围
	 */
	private Integer openRange;

	private Integer courseStartUpType;

	/**
	 * 课程开始时间
	 */
	private String courseStratTime;
	/**
	 * 修改时间
	 */
	private String updateTime;
	
	/**
	 * 概览-老师姓名
	 */
	private String realName;
	
	/**
	 * 概览-老师发布的总课程数量
	 */
	private String courseSum;
	
	/**
	 * 概览-老师发布总课程的总学习人数
	 */
	private String people;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCourseSum() {
		return courseSum;
	}

	public void setCourseSum(String courseSum) {
		this.courseSum = courseSum;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public Course() {
	}

	/*
	 * get/set 方法
	 */


	/*
	 *********************************
	 * 常量区
	 *********************************
	 */
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

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getSpecialtyId() {
		return specialtyId;
	}

	public void setSpecialtyId(String specialtyId) {
		this.specialtyId = specialtyId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
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
	public boolean getCommentAllowable() {
		return this.commentAllowable;
	}

	public Integer getUseCount() {
		return useCount;
	}

	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}

	public Float getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(Float avgScore) {
		this.avgScore = avgScore;
	}

	public Integer getEvaluateAmount() {
		return evaluateAmount;
	}

	public void setEvaluateAmount(Integer evaluateAmount) {
		this.evaluateAmount = evaluateAmount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
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

	public Integer getOpenRange() {
		return openRange;
	}

	public void setOpenRange(Integer openRange) {
		this.openRange = openRange;
	}

	public Integer getCourseStartUpType() {
		return courseStartUpType;
	}

	public void setCourseStartUpType(Integer courseStartUpType) {
		this.courseStartUpType = courseStartUpType;
	}

	public String getCourseStratTime() {
		return courseStratTime;
	}

	public void setCourseStratTime(String courseStratTime) {
		this.courseStratTime = courseStratTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCoursePicUrl() {
		return coursePicUrl;
	}

	public void setCoursePicUrl(String coursePicUrl) {
		this.coursePicUrl = coursePicUrl;
	}

	public String getCourseTypeName() {
		return courseTypeName;
	}

	public void setCourseTypeName(String courseTypeName) {
		this.courseTypeName = courseTypeName;
	}

	/**
	 * 课程状态-已失效0
	 */
	public static  final int COURSE_STATUS_DELETED = 0;
	/**
	 * 课程状态-终止1
	 */
	public static  final int COURSE_STATUS_TERMINATION = 1;
	/**
	 * 课程状态-未提交2
	 */
	public static  final int COURSE_STATUS_NOT_SUBMITED = 2;

	/**
	 * 课程状态-已提交3
	 */
	public static  final int COURSE_STATUS_SUBMITED = 3;

	/**
	 * 课程状态-未通过4
	 */
	public static  final int COURSE_STATUS_NOT_PASS = 4;

	/**
	 * 课程状态-已通过6
	 */
	public static  final int COURSE_STATUS_PASS = 6;
	
	/**
	 * 课程状态-已发布5
	 */
	public static  final int COURSE_STATUS_REGISTERED = 5;

	/**
	 * 课程状态-已过期7
	 */
	public static  final int COURSE_STATUS_EXPIRED = 7;
	
	/**
	 * 课程公开范围-指定学生0
	 */
	public static  final int COURSE_RANGE_STUDENT = 0;

	/**
	 * 课程公开范围-指定院系1
	 */
	public static  final int COURSE_RANGE_DEPARTMENT = 1;

	/**
	 * 课程公开范围-指定学校2
	 */
	public static  final int COURSE_RANGE_SCHOOL = 2;

	/**
	 * 课程公开范围-指定平台3
	 */
	public static  final int COURSE_RANGE_PLATFORM = 3;

	/**
	 * 课程类型等级-入门0
	 */
	public static  final int COURSE_TYPE_LEVEL_BASE = 0;

	/**
	 * 课程类型等级-初级1
	 */
	public static  final int COURSE_TYPE_LEVEL_PRIMARY = 1;

	/**
	 * 课程类型等级-中级2
	 */
	public static  final int COURSE_TYPE_LEVEL_MIDDLE = 2;

	/**
	 * 课程类型等级-高级3
	 */
	public static  final int COURSE_TYPE_LEVEL_HIGH = 3;

	/**
	 * 课程类型等级-实践4
	 */
	public static  final int COURSE_TYPE_LEVEL_PRACTICE = 4;

	/**
	 * 课程不是自启动-0
	 */
	public static final int COURSE_NOT_AUTO_START = 0;

	/**
	 * 课程不是自启动-1
	 */
	public static final int COURSE_AUTO_START = 1;

	public static class CourseStateSerializer implements ObjectSerializer {

		@Override
		public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
			Integer i = (Integer) object;
			switch (i) {
				case COURSE_STATUS_REGISTERED:
					serializer.write("可用");
					break;
				case COURSE_STATUS_TERMINATION:
					serializer.write("不可用");
					break;
			}
		}
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", collegeId=" + collegeId
				+ ", courseName=" + courseName + ", teacherId=" + teacherId
				+ ", specialtyId=" + specialtyId + ", description="
				+ description + ", createTime=" + createTime
				+ ", coursePicFileId=" + coursePicFileId + ", coursePicUrl="
				+ coursePicUrl + ", commentAllowable=" + commentAllowable
				+ ", useCount=" + useCount + ", avgScore=" + avgScore
				+ ", evaluateAmount=" + evaluateAmount + ", status=" + status
				+ ", courseType=" + courseType + ", courseTypeName="
				+ courseTypeName + ", courseTypeLevel=" + courseTypeLevel
				+ ", openRange=" + openRange + ", courseStartUpType="
				+ courseStartUpType + ", courseStratTime=" + courseStratTime
				+ ", updateTime=" + updateTime + "]";
	}
}
