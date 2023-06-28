package com.awslabplatform.dao.courseManage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.awslabplatform.dao.base.BaseDao;
import com.awslabplatform.entity.Course;
import com.awslabplatform.entity.CourseComment;
import com.awslabplatform.entity.CourseType;
import com.awslabplatform.entity.MyCourse;
import com.awslabplatform.entity.RouteTotle;
import com.awslabplatform.entity.User;

/**
 *  课程Dao
 * @author lijf
 * date 2018年3月18日 上午10:15:59
 */
public interface CourseDao extends BaseDao<Course, String>{

	/**
	 * 通过课程名称获取
	 * @param courseName
	 * @return courseName
	 */
	Course getBycourseName(@Param("courseName") String courseName);
	/**
	 * 列出所有课程类型
	 * @return
	 */
	List<CourseType> listCourseType();
	/**
	 * 搜索课程
	 * @param condition 如key=courseName
	 * @return
	 */
	List<Course> searchCourse(Map<String, Object> condition);
	/**
	 * 某课程类型的总时间
	 * @param courseType
	 * @return
	 */
	RouteTotle getRouteTotle(String courseType);
	/**
	 * 添加新的课程记录
	 * @param myCourse
	 */
	void addMyCourse(MyCourse myCourse);
	/**
	 * 添加新的实验记录
	 * @param myCourse
	 */
	void addExperiment(MyCourse myCourse);
	/**
	 * 列出我的课程记录
	 * @param studentId
	 * @return
	 */
	List<MyCourse> listMyCourse(String studentId);
	/**
	 * <!-- 	查找指定的课程记录 -->
	 * @param studentId
	 * @param courseId
	 * @return
	 */
	MyCourse findOneRecord(@Param("studentId")String studentId,@Param("courseId")String courseId);
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
	 * 更新课程平均分
	 * @param course
	 */
	void updateScore(Course course);
	/**
	 * 查找该课程的教师
	 * @param courseId
	 * @return
	 */
	User getTeacherByCourseId(String courseId);

	/**
	 * 获取课程by 实验Id
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

}
