package com.awslabplatform_admin.service.courseManage;

import java.util.List;
import java.util.Map;

import com.awslabplatform_admin.entity.*;
import com.awslabplatform_admin.service.awsAccountManage.AwsIamsService;
import com.awslabplatform_admin.service.base.BaseService;
import com.awslabplatform_admin.service.connmon.FileInfoService;
import com.awslabplatform_admin.service.uploadService.uploadService;


/**
 * 课程管理service
 * @author zhenghk
 *
 */
public interface CourseService extends BaseService<Course, Integer> {

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
	 * 判断课程是否存在
	 * @param courseName 课程名
	 * @return
	 */
	Boolean checkCourseExist(String courseName);
	
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
	 * @param info
	 * @return
	 */
	List<Course> listCourseInfo(PageInfo info);

	/**
	 * 统计课程数量(分页)
	 * @param info
	 * @return
	 */
    int getCourseInfoTotal(PageInfo info);


	Long[] getAllCourseIds();

	/**
	 * 解析json并转成课程对象
	 * @param jsonStr
	 * @return
	 */
	public Course parseJsonToCourse(String jsonStr);

	/**
	 * 通过条件查找课程名
	 * @param conditions
	 * @param status
	 * @return
	 */
	/*List<Course> searchCourseByConditions(String conditions, int status);*/

	/**
	 * 删除课程
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
	 * 统计课程评论数量(分页)
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
	 * 发送课程信息给学生
	 * @param course
	 * @param students
	 * @return
	 */
	public Boolean sendMessageToStudent(Course course, List<Student> students);
	
	/**
	 * 创建新的密钥
	 * @param keyName
	 * @param awsIamsService
	 * @param fileService
	 * @return
	 */
	public Result createNewKeyPair(String keyName);


	/**
	 * 统计课程人数
	 * @param courseId
	 * @return
	 */
	public int countCourseNumber(String courseId);
	
	/**
	 * 统计课程人数
	 * @param course
	 * @return
	 */
	public int countCourseNumber(Course course);
	
	/**
	 * 查询课程学生
	 * @param courseId
	 * @param openRange
	 * @return
	 */
	public List<Student> selectCourseStudents(String courseId);
	
	/**
	 * 查询课程学生
	 * @param course
	 * @return
	 */
	public List<Student> selectCourseStudents(Course course);
	
	/**
	 * 查询已经发布未发布课程的数量
	 * @return
	 */
	int getCountCourseYes(Map<String,Object> condition);
	
	
	/**
	 * 查询当前登陆者老师的课程已学习总人数
	 * @return
	 */
	int getLearning(Map<String,Object> condition);
	
	/**
	 * 查询当前登陆者院系管理员 看到的本院系教师课程数量前十排行
	 * @return
	 */
	List<Course>getCourseAmount(Map<String,Object> condition);
	
	/**
	 * 查询当前登陆者院系管理员 看到的本院系已审核和未审核的课程
	 * @return
	 */
	int getAuditCourse(Map<String,Object> condition);
	
	/**
	 * 自动发布课程
	 * @param courseId
	 */
	void autoLaunchCourse(String courseId);
	
	/**
	 * 审核通过后判断课程自动发布的时间是否过期了，过期了自动发布课程
	 * @param courseId
	 */
	void checkAutoLaunchCourse(String courseId);
	
	/**
	 * 审核通过后判断课程自动发布的时间是否过期了，过期了自动发布课程
	 * @param course
	 */
	void checkAutoLaunchCourse(Course course);
	
	
}
