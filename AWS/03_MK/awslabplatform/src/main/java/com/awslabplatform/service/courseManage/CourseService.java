package com.awslabplatform.service.courseManage;

import java.util.List;
import java.util.Map;

import com.awslabplatform.entity.Course;
import com.awslabplatform.entity.CourseComment;
import com.awslabplatform.entity.CourseType;
import com.awslabplatform.entity.Experiment;
import com.awslabplatform.entity.MyCourse;
import com.awslabplatform.entity.RouteTotle;
import com.awslabplatform.entity.Student;
import com.awslabplatform.entity.User;
import com.awslabplatform.service.base.BaseService;
import com.github.pagehelper.PageInfo;

/**
 * 课程管理service
 * @author lijf
 * @date 2018年3月18日 上午10:29:45
 */
public interface CourseService extends BaseService<Course, String> {

    /**
	 * 查询（任意字段返回多个结果）
	 *
	 * @param {field，value}
	 * @return
	 **/
    PageInfo<Course>  listByCondition(Map<String, Object> condition,int pageNum,int pageSize,String orderBy);
    /**
	 * 列出所有课程类型
	 * @return
	 */
	List<CourseType> listCourseType();

	/**
	 * 搜索课程
	 * @param condition
	 * @param pageNum
	 * @param pageSize
	 * @param orderBy
	 * @return
	 */
	PageInfo<Course>  searchCourse(Map<String, Object> condition,int pageNum,int pageSize,String orderBy);
	/**
	 * 某课程类型的总时间,使用人数
	 * @param courseType
	 * @return
	 */
	RouteTotle getTotleTime(String courseType);
	/**
	 * 列出我的课程
	 * @param studentId
	 * @return
	 */
	List<MyCourse> listMyCourse(String studentId);

	/**
	 * 添加课程评论
	 * @param courseComment
	 */
	void addCourseComment(CourseComment courseComment);
	/**
	 * 列出课程评论
	 * @param courseId
	 * @return
	 */
	List<CourseComment> getCourseComments(String courseId);
	/**
	 * 查找该课程的教师
	 * @param courseId
	 * @return
	 */
	User getTeacherByCourseId(String courseId);

	/**
	 * 获取实验by 实验Id
	 * @param courseId
	 * @return
	 */
	public Course getCourseById(String courseId);

	/**
	 * 获取实验Id by 课程Id
	 * @param experimentId
	 * @return
	 */
	public String getCourseIdByExperimentId(String experimentId);
	/**
	 * 添加学生的我的课程记录
	 * @param student
	 * @param experiment
	 */
	public void addMycourse(Student student,Experiment experiment);
}
