package com.awslabplatform_admin.dao.courseManage;

import java.util.List;
import java.util.Map;

import com.awslabplatform_admin.dao.base.BaseDao;
import com.awslabplatform_admin.entity.*;

import org.apache.ibatis.annotations.Param;


/**
 * 课程管理dao 接口
 * @author zhenghk
 *
 */
public interface CourseDao extends BaseDao<Course, Integer>{

	/**
	 * 更新课程信息
	 * @param course
	 * @return
	 */
	int updateCourseInfo(Course course);

	/**
	 * 添加课程信息
	 * @param course
	 * @return
	 */
	int addCourseInfo(Course course);

	/**
	 * 通过课程ID查找课程信息
	 * @param courseId
	 * @return
	 */
	Course findCourseByCourseId(String courseId);

	/**
	 * 通过课程名查找课程信息
	 * @param courseName
	 * @return
	 */
	List<Course> findCourseByCourseName(String courseName);

	/**
	 * 通过课程ID查找课程实验组
	 * @param courseId
	 * @return
	 */
	List<ExperimentGroup> findCourseExperimentGroupByCourseId(String courseId);

	/**
	 * 通过教师名查找课程信息
	 * @param teachName
	 * @return
	 */
	List<Course> findCourseByTeachName(String teachName);

	/**
	 * 通过课程状态查找课程信息
	 * @param status
	 * @return
	 */
	List<Course> findCourseByStatus(int status);

	/**
	 * 通过课程类型查找课程信息
	 * @param courseType
	 * @return
	 */
	List<Course> findCourseByCourseType(int courseType);

	/**
	 * 统计所有课程
	 * @param userType
	 * @return
	 */
	long countByCourseType(int userType);

	/**
	 * 统计教师的课程
	 * @param teachName
	 * @return
	 */
	long countByTeachName(String teachName);




	/**
	 * 查询是否有相同的课程
	 * @param condition
	 * @return
	 */
	int countCourse(Map<String, Object> condition);

	/**
	 * 分页查询课程信息
	 * @param pageInfo
	 * @return
	 */
	List<Course> listCourseInfo(PageInfo pageInfo);

	/**
	 * 统计课程数量(分页)
	 * @param pageInfo
	 * @return
	 */
    int getCourseInfoTotal(PageInfo pageInfo);


	Long[] getAllCourseIds();

	/**
	 * 根据条件查找课程
	 */
	/*List<Course> searchCourseById(@Param("courseId") String courseId, @Param("status") int status);*/


	/**
	 * 根据Id删除课程
	 */
	public void deleteCourseById(String courseId);

	/**
	 * 通过课程ID查找审核结果
	 */
	public List<CourseCheckHistory> getCourseCheckHistory(String courseId);

	/**
	 * 通过教师Id查找老师
	 */
	public Teacher getTeacherById(String userId);

	/**
	 * 查询课程评论
	 */
	public List<CourseComment> getCommentByCourseId(String courseId);

	/**
	 * 通过courseId查找groupId
	 */
	public List<String> getGroupIdByCourseId(String courseId);

	/**
	 * 分页查询课程评论信息
	 * @param pageInfo
	 * @return
	 */
	public List<CourseComment> getCourseCommentInfo(PageInfo pageInfo);

	/**
	 * 分页查询课程评论信息
	 * @param pageInfo
	 * @return
	 */
	public int getCourseCommentInfoTotal(PageInfo pageInfo);

	/**
	 * 新增一个课程审核结果
	 */
	public void insertCourseCheckHistory(CourseCheckHistory courseCheckHistory);

	/**
	 * 更新courseCheckHistory
	 */
	public void updateCourseCheckHistory(CourseCheckHistory courseCheckHistory);
	
	/**
	 * 统计课程人数
	 * @param collageId 学院Id
	 * @return
	 */
	public int  countCourseNumberByCollegeId(String collegeId);
	
	/**
	 * 统计课程人数
	 * @param schoolId 学校ID
	 * @return
	 */
	public int  countCourseNumberBySchoolId(String schoolId);
	
	/**
	 * 统计课程人数
	 * @param groupList 课程组list
	 * @return
	 */
	public int  countCourseNumberByGroup(List<String> groupList);
	
	/**
	 * 查询课程学生
	 * @param groupIdList 组Id 集合
	 * @return
	 */
	public List<Student> selectCourseStudentByGroupIdList(List<String> groupIdList);
	
	/**
	 * 查询课程学生
	 * @param collageId 学院Id
	 * @return
	 */
	public List<Student> selectCourseStudentByCollegeId(String collegeId);
	
	/**
	 * 查询课程学生
	 * @param schoolId 学校ID
	 * @return
	 */
	public List<Student> selectCourseStudentBySchoolId(String schoolId);
	
	/**
	 * 查询已经发布未发布课程的数量
	 * @return
	 */
	int countCourseYes(Map<String,Object> condition);

	
	/**
	 * 查询当前登陆者老师的课程已学习总人数
	 * @return
	 */
	int learning(Map<String,Object> condition);
	
	/**
	 * 查询当前登陆者院系管理员 看到的本院系教师课程数量前十排行
	 * @return
	 */
	List<Course>courseAmount(Map<String,Object> condition);
	
	/**
	 * 查询当前登陆者院系管理员 看到的本院系已审核和未审核的课程
	 * @return
	 */
	int auditCourse(Map<String,Object> condition);
}
