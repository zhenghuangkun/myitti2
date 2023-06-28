package com.awslabplatform_admin.service.courseManage.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.awslabplatform_admin.common.message.CourseMessage;
import com.awslabplatform_admin.dao.courseManage.CourseDao;
import com.awslabplatform_admin.entity.*;
import com.awslabplatform_admin.service.amazonaws.KeyPairService;
import com.awslabplatform_admin.service.base.impl.BaseServiceImpl;
import com.awslabplatform_admin.service.connmon.FileInfoService;
import com.awslabplatform_admin.service.courseManage.CourseService;
import com.awslabplatform_admin.service.courseManage.ExperimentGroupService;
import com.awslabplatform_admin.service.mechanismManage.MechanismService;
import com.awslabplatform_admin.service.uploadService.uploadService;
import com.awslabplatform_admin.util.*;
import com.awslabplatform_admin.util.thread.SendMailThread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * 课程管理service 接口实现类
 *
 * @author zhenghk
 */
@Service("CourseService")
public class CourseServiceImpl extends BaseServiceImpl<CourseDao, Course, Integer> implements CourseService {

	/**
	 * 文件上传service
	 */
	@Autowired
	private uploadService upService;

	/**
	 * 实验组service
	 */
	@Autowired
	private  ExperimentGroupService  expGroupService;

	/**
	 * 密钥service
	 */
	@Autowired
	private KeyPairService keyPairService;

	/**
	 * 文件管理service
	 */
	@Autowired
	private FileInfoService fileService;

	/**
	 * 课程管理service
	 */
	@Autowired
	private CourseService courseService;

	/**
	 * 邮件发送service
	 */
	@Autowired
	private MailSenderUtils mailSenderUtils;
	
	/**
	 * 机构管理的service
	 */
    @Autowired
    private  MechanismService  mechanismService;
	
	/**
	 * 更新课程信息
	 * @param course
	 * @return
	 */
	@Transactional(propagation= Propagation.REQUIRED)
	public int updateCourseInfo(Course course){
		return baseDao.updateCourseInfo(course);
	}

	/**
	 * 添加课程信息
	 * @param course
	 * @return
	 */
	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public int addCourseInfo(Course course) {
		return baseDao.addCourseInfo(course);
	}

	@Override
	public Course findCourseByCourseId(String courseId) {

		return baseDao.findCourseByCourseId(courseId);
	}

	@Override
	public List<Course> findCourseByCourseName(String courseName) {

		return baseDao.findCourseByCourseName(courseName);
	}

	/**
	 * 判断课程是否存在
	 * @param courseName 课程名
	 * @return
	 */
	public Boolean checkCourseExist(String courseName){
		List<Course> courseList = findCourseByCourseName(courseName);
		
		if(courseList.size() > 0){
			return true;
		}
		
		return false;
	}
	
	@Override
	public List<Course> findCourseByTeachName(String teachName) {

		return null;
	}

	@Override
	public List<Course> findCourseByStatus(int status) {

		return null;
	}

	@Override
	public List<Course> findCourseByCourseType(int courseType) {

		return null;
	}

	@Override
	public long countByCourseType(int userType) {

		return 0;
	}

	@Override
	public long countByTeachName(String teachName) {

		return 0;
	}

	@Override
	public int countCourse(Map<String, Object> condition) {

		return 0;
	}

	@Override
	public List<Course> listCourseInfo(PageInfo pageInfo){

		return baseDao.listCourseInfo(pageInfo);
	}

	@Override
	public int getCourseInfoTotal(PageInfo pageInfo) {
		/*查询分页数据*/
		pageInfo.setData(baseDao.listCourseInfo(pageInfo));
		/*查询总数*/
		int total = baseDao.getCourseInfoTotal(pageInfo);
		pageInfo.setRecordsTotal(total);
		pageInfo.setRecordsFiltered(total);//过滤记录数，dataTable必要参数
		return 0;
	}

	/**
	 * 分页查询课程评论信息
	 * @param pageInfo
	 * @return
	 */
	public List<CourseComment> getCourseCommentInfo(PageInfo pageInfo){

		return baseDao.getCourseCommentInfo(pageInfo);
	}

	/**
	 * 统计课程评论数量(分页)
	 * @param pageInfo
	 * @return
	 */
    public int getCourseCommentInfoTotal(PageInfo pageInfo){
    	/*查询分页数据*/
		pageInfo.setData(baseDao.getCourseCommentInfo(pageInfo));
		/*查询总数*/
		int total = baseDao.getCourseCommentInfoTotal(pageInfo);
		pageInfo.setRecordsTotal(total);
		pageInfo.setRecordsFiltered(total);//过滤记录数，dataTable必要参数
		return 0;
    }

	@Override
	public Long[] getAllCourseIds() {

		return null;
	}

	/**
	 * 通过课程ID查找课程实验组
	 * @param courseId
	 * @return
	 */
	@Override
	public List<ExperimentGroup> findCourseExperimentGroupByCourseId(String courseId){
		return baseDao.findCourseExperimentGroupByCourseId(courseId);
	}

	@Override
	public Course parseJsonToCourse(String jsonStr) {

		try{
			/*Course course = new Course();

			JSONObject jObjCourse = JSONObject.parseObject(jsonStr);

			//获取UUID
			String uuId = jObjCourse.getString("courseUuid");
			if(uuId == null){
				return null;
			}

			//获取UUID
			String teacherId = jObjCourse.getString("teacherId");
			if(teacherId == null){
				return null;
			}

			//获取学院Id
			int collegeId = jObjCourse.getIntValue("collegeId");
			if(collegeId <= 0){
				return null;
			}


			//获取课程名
			String courseName = jObjCourse.getString("courseName");
			if(courseName == null){
				return null;
			}

			//获取课程类型
			int courseType = jObjCourse.getIntValue("courseTypeName");
			if(courseType <= 0){
				return null;
			}
			//获取课程类型
			int courseTypeLevel = jObjCourse.getIntValue("courseTypeLevel");
			if(courseTypeLevel > Course.COURSE_TYPE_LEVEL_PRACTICE){
				return null;
			}
			//获取专业名
			int specialtyId = jObjCourse.getIntValue("specialtyId");
			if(specialtyId <= 0){
				return null;
			}

			//获取课程是否自启动
			String courseStartUpType = jObjCourse.getString("courseStartUpType");
			if(courseStartUpType != null && courseStartUpType.equals("on")){
				//获取课程开始时间
				String courseStratTime = jObjCourse.getString("courseStratTime");
				if(courseStratTime == null){
					return null;
				}

				// 设置自启动时间
				course.setCourseStratTime(courseStratTime);
			}else{
				course.setCourseStratTime("");
			}


			//获取课程共享范围
			String openRangeStr = jObjCourse.getString("openRange");
			if(openRangeStr == null){
				return null;
			}

			//获取课程描述
			String description = jObjCourse.getString("description");
			if(description == null){
				return null;
			}

			// 设置课程ID
			course.setCourseId(uuId);
			// 设置教师Id
			course.setTeacherId(teacherId);
			// 设置学院ID
			course.setCollegeId(collegeId);
			// 设置课程名
			course.setCourseName(courseName);
			// 设置课程类型名
			course.setCourseType(courseType);
			// 设置课程类型等级
			course.setCourseTypeLevel(courseTypeLevel);
			// 设置专业名
			course.setSpecialtyId(specialtyId);
			// 设置课程描述
			course.setDescription(description);



			// 设置共享范围
			try {
				course.setOpenRange(Integer.parseInt(openRangeStr));
			} catch (NumberFormatException  e) {
				course.setOpenRange(0);
			}
			*/

			Course course = new Course();
			JSONObject jObjCourse = JSONObject.parseObject(jsonStr);
			course = jObjCourse.toJavaObject(Course.class);
			if(course == null){
				return null;
			}
			//获取UUID
			String uuId = jObjCourse.getString("courseUuid");
			if(uuId == null){
				return null;
			}

			//获取课程是否自启动
			String courseStartUpTypeVal = jObjCourse.getString("courseStartUpTypeVal");
			if(courseStartUpTypeVal != null && courseStartUpTypeVal.equals("on")){
				// 设置课程为自启动
				course.setCourseStartUpType(Course.COURSE_AUTO_START);
			}else{
				course.setCourseStartUpType(Course.COURSE_NOT_AUTO_START);
				course.setCourseStratTime(null);
			}
			
			//course.setCourseStartUpType(Course.COURSE_NOT_AUTO_START);
			//course.setCourseStratTime(null);

			// 设置课程ID
			course.setCourseId(uuId);
			// 设置课程使用人数为0
			course.setUseCount(0);
			// 设置课程平均分为0
			course.setAvgScore(0.0f);
			// 设置课程评论数为0
			course.setEvaluateAmount(0);

			// 设置课程状态为未已提交
			course.setStatus(Course.COURSE_STATUS_SUBMITED);
			// 设置创建时间和更新时间
			String date = TimeUtils.currentTime();
			course.setCreateTime(date);
			course.setUpdateTime(date);
			return course;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 搜索课程
	 */
	/*@Override
	List<Course> searchCourseByConditions(String conditions, int status){

	}*/

	/**
	 * 删除课程
	 */
	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public void deleteCourseById(String courseId){
		baseDao.deleteCourseById(courseId);
	}

	/**
	 * 通过课程ID查找审核结果
	 */
	@Override
	public List<CourseCheckHistory> getCourseCheckHistory(String courseId){
		return baseDao.getCourseCheckHistory(courseId);
	}

	/**
	 * 通过教师Id查找老师
	 */
	@Override
	public Teacher getTeacherById(String userId){
		return baseDao.getTeacherById(userId);
	}

	/**
	 * 通过评论Id查找评论
	 */
	/**
	 * 查询课程评论
	 */
	public List<CourseComment> getCommentByCourseId(String courseId){
		return baseDao.getCommentByCourseId(courseId);
	}

	/**
	 * 通过courseId查找groupId
	 */
	public List<String> getGroupIdByCourseId(String courseId){
		return baseDao.getGroupIdByCourseId(courseId);
	}

	/**
	 * 新增一个课程审核结果
	 */
	@Transactional(propagation= Propagation.REQUIRED)
	public void insertCourseCheckHistory(CourseCheckHistory courseCheckHistory){
		baseDao.insertCourseCheckHistory(courseCheckHistory);
	}

	/**
	 * 更新courseCheckHistory
	 */
	@Transactional(propagation= Propagation.REQUIRED)
	public void updateCourseCheckHistory(CourseCheckHistory courseCheckHistory){
		baseDao.updateCourseCheckHistory(courseCheckHistory);
	}

	/**
	 * 发送课程信息给学生
	 * @param course
	 * @param students
	 * @return
	 */
	public Boolean sendMessageToStudent(Course course, List<Student> students){
		
		if(course == null || students == null){
			return false;
		}
		
		Teacher teacher = courseService.getTeacherById(course.getTeacherId());
		
		SendMailThread sendMailThread = new SendMailThread(course, students, teacher, mailSenderUtils);
		
		Thread sendThread = new Thread(sendMailThread);
		sendThread.start();
		
		return true;
	}

	/**
	 * 创建新的密钥
	 * @param keyName
	 * @return
	 */
	public Result createNewKeyPair(String keyName){

		FileInfo fileinfo = keyPairService.createKeyPair(keyName);

		if(fileinfo == null){
			return ResultUtil.getResult(true, CourseMessage.CREATE_KEYPAIR_ERR);
		}

		// 写入密钥到数据库
		fileService.insertFileInfo(fileinfo);


		return ResultUtil.getResult(true, CourseMessage.CREATE_KEYPAIR_SUCCESS);
	}

	/**
	 * 统计课程人数
	 * @param courseId
	 * @return
	 */
	public int countCourseNumber(String courseId){
		Course course = baseDao.findCourseByCourseId(courseId);
		return countCourseNumber(course);
	}

	/**
	 * 统计课程人数
	 * @param course
	 * @return
	 */
	public int countCourseNumber(Course course){
		if(course == null){
			return 0;
		}

		int number = 0;

		int openRange = course.getOpenRange();
		if(openRange == Course.COURSE_RANGE_STUDENT){
			// 取得课程实验组list
			List<String> groupList = expGroupService.findCourseExperimentGroupByCourseId(course.getCourseId());

			number = baseDao.countCourseNumberByGroup(groupList);

		}else if(openRange == Course.COURSE_RANGE_DEPARTMENT){

			/*// 取得教师Id
			String collegeId = ShiroUtil.getCurrentUser().getDepartmentId();*/
			//取得学院Id
			String collegeId = course.getCollegeId();
			number = baseDao.countCourseNumberByCollegeId(collegeId);

		}else if(openRange == Course.COURSE_RANGE_SCHOOL){

			// 取得学校ID
			String schoolId = ShiroUtil.getCurrentUser().getSchoolId();
			
			// 判断是否是超级管理员
			if(schoolId == null || "0".equals(schoolId)){
				// 根据课程的collegeId 找到学校ID
				String collegeId = course.getCollegeId();
				Mechanism mechanism = mechanismService.getMechanismData(collegeId);
				if(mechanism != null){
					// 取得学校ID
					schoolId = mechanism.getMechanismPid();
				}
			}
			
			number = baseDao.countCourseNumberBySchoolId(schoolId);

		}else{
			// 其他情况返回0
			number = 0;
		}

		return number;
	}


	/**
	 * 查询课程学生
	 * @param courseId
	 * @return
	 */
	public List<Student> selectCourseStudents(String courseId){

		if(courseId == null){
			return null;
		}

		if(courseId.equals("")){
			return null;
		}

		Course course = baseDao.findCourseByCourseId(courseId);

		return selectCourseStudents(course);
	}

	/**
	 * 查询课程学生
	 * @param course
	 * @return
	 */
	public List<Student> selectCourseStudents(Course course){
		if(course == null){
			return null;
		}

		List<Student> students = null;
		int openRange = course.getOpenRange();
		if(openRange == Course.COURSE_RANGE_STUDENT){
			// 取得课程实验组list-其他地方实现
			//通过courseId查询课程关联的组ID集合 groupId
			List<String> groupIdList = courseService.getGroupIdByCourseId(course.getCourseId());

			if(groupIdList.size() <= 0){
				return null;
			}

			// 取得所有组学生
			students = baseDao.selectCourseStudentByGroupIdList(groupIdList);

		}else if(openRange == Course.COURSE_RANGE_DEPARTMENT){

			// 取得学院Id
			/*String collegeId = ShiroUtil.getCurrentUser().getDepartmentId();*/
			
			String collegeId = course.getCollegeId();
			students = baseDao.selectCourseStudentByCollegeId(collegeId);

		}else if(openRange == Course.COURSE_RANGE_SCHOOL){

			// 取得学校ID
			String schoolId = ShiroUtil.getCurrentUser().getSchoolId();
			
			// 判断是否是超级管理员
			if(schoolId == null || "0".equals(schoolId)){
				// 根据课程的collegeId 找到学校ID
				String collegeId = course.getCollegeId();
				Mechanism mechanism = mechanismService.getMechanismData(collegeId);
				if(mechanism != null){
					// 取得学校ID
					schoolId = mechanism.getMechanismPid();
				}
			}
			
			students = baseDao.selectCourseStudentBySchoolId(schoolId);

		}else{
			// 其他情况返回0
			return null;
		}

		return students;
	}

	/**
	 * 查询已经发布未发布课程的数量
	 */
	@Override
	public int getCountCourseYes(Map<String,Object> condition) {
		return baseDao.countCourseYes(condition);
	}

	/**
	 * 查询当前登陆者老师的课程已学习总人数
	 */
	@Override
	public int getLearning(Map<String,Object> condition) {
		return baseDao.learning(condition);
	}

	/**
	 * 查询当前登陆者院系管理员 看到的本院系教师课程数量前十排行
	 */
	@Override
	public List<Course> getCourseAmount(Map<String,Object> condition) {
		return baseDao.courseAmount(condition);
	}

	/**
	 * 查询当前登陆者院系管理员 看到的本院系已审核和未审核的课程
	 */
	@Override
	public int getAuditCourse(Map<String,Object> condition) {
		return baseDao.auditCourse(condition);
	}


	/**
	 * 自动发布课程
	 * @param courseId
	 */
	@Override
	public void autoLaunchCourse(String courseId){
		
		Course course = findCourseByCourseId(courseId);
		
		if(course.getStatus() == Course.COURSE_STATUS_PASS){
			// 自动更新课程
			autoUpdateCourse(course);
		} 
		
	}
	
	public void autoUpdateCourse(Course course){
		course.setStatus(Course.COURSE_STATUS_REGISTERED);
		
		/* 取得课程所有学生 */
		List<Student> students = selectCourseStudents(course);
		
		sendMessageToStudent(course, students);
		
		// 更新课程信息
		updateCourseInfo(course);
	}
	
	/**
	 * 审核通过后判断课程自动发布的时间是否过期了，过期了自动发布课程
	 * @param courseId
	 */
	public void checkAutoLaunchCourse(String courseId){
		Course course = findCourseByCourseId(courseId);
		
		checkAutoLaunchCourse(course);
	}
	
	/**
	 * 审核通过后判断课程自动发布的时间是否过期了，过期了自动发布课程
	 * @param course
	 */
	public void checkAutoLaunchCourse(Course course){
		

		String courseStartTimeStr = course.getCourseStratTime();
		String currentTimeStr = TimeUtils.currentTime();
		
		Date courseStartDate = TimeUtils.getDateString(courseStartTimeStr);
		Date currentDate = TimeUtils.getDateString(currentTimeStr);
		
		long courseStartLong = courseStartDate.getTime();
		long currentLong = currentDate.getTime();
		
		if(courseStartLong < currentLong){
			// 自动更新课程
			autoUpdateCourse(course);
		}
	}
	
}
