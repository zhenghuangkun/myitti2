package com.awslabplatform.service.courseManage.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.awslabplatform.dao.courseManage.CourseDao;
import com.awslabplatform.dao.experimentManage.ExperimentDao;
import com.awslabplatform.entity.Course;
import com.awslabplatform.entity.CourseComment;
import com.awslabplatform.entity.CourseType;
import com.awslabplatform.entity.Experiment;
import com.awslabplatform.entity.MyCourse;
import com.awslabplatform.entity.RouteTotle;
import com.awslabplatform.entity.Student;
import com.awslabplatform.entity.User;
import com.awslabplatform.service.base.impl.BaseServiceImpl;
import com.awslabplatform.service.courseManage.CourseService;
import com.awslabplatform.util.UUIDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 *
 * @author lijf
 * date 2018年3月18日 上午10:39:00
 */
@Service("courseService")
public class CourseServiceImpl extends BaseServiceImpl<CourseDao, Course, String> implements CourseService {

	@Autowired
	private CourseDao courseDao;
	@Autowired
	private ExperimentDao experimentDao;
	@Override
	public Course selectByPrimaryKey(String id) {
		return baseDao.selectByPrimaryKey(id);
	}

	@Override
	public List<CourseType> listCourseType() {
		return courseDao.listCourseType();
	}

	@Override
	public PageInfo<Course>  listByCondition(Map<String, Object> condition,int pageNum,int pageSize,String orderBy) {
		pageNum = pageNum==0?1:pageNum;//页码，从1开始
        pageSize = pageSize==0?8:pageSize;//每页记录数
        PageHelper.startPage(pageNum, pageSize);
        if(orderBy!=null&&!"".equals(orderBy)){
        	PageHelper.orderBy(orderBy);
        }
        Map<String, Object> pram = new HashMap<String,Object>();
        pram.put("condition", condition);
		List<Course> courseList = courseDao.listByCondition(pram);
		PageInfo<Course> pageInfo = new PageInfo<Course>(courseList);
        return pageInfo;
	}

	@Override
	public PageInfo<Course> searchCourse(Map<String, Object> condition,int pageNum, int pageSize, String orderBy) {
		pageNum = pageNum==0?1:pageNum;//页码，从1开始
        pageSize = pageSize==0?8:pageSize;//每页记录数
        PageHelper.startPage(pageNum, pageSize);
        if(orderBy!=null&&!"".equals(orderBy)){
        	PageHelper.orderBy(orderBy);
        }
        Map<String, Object> pram = new HashMap<String,Object>();
        pram.put("condition", condition);
		List<Course> courseList = courseDao.searchCourse(pram);
		PageInfo<Course> pageInfo = new PageInfo<Course>(courseList);
        return pageInfo;
	}

	@Override
	public RouteTotle getTotleTime(String courseType) {
		return courseDao.getRouteTotle(courseType);
	}

	@Override
	public List<MyCourse> listMyCourse(String studentId) {
		return courseDao.listMyCourse(studentId);
	}

	@Override
	public void addCourseComment(CourseComment courseComment) {
		courseComment.setCommentId(UUIDUtils.getUUID());
		courseComment.setCommentTime(new Date());
		//新增一条评论
		courseDao.addCourseComment(courseComment);
		Course course = courseDao.selectByPrimaryKey(courseComment.getCourseId());
		int evaluateAmount=course.getEvaluateAmount()+1;
		//course.setAvgScore(avgScore);
		course.setEvaluateAmount(evaluateAmount);
		//修改课程评价数和平均分
		courseDao.updateScore(course);
	}

	@Override
	public List<CourseComment> getCourseComments(String courseId) {
		return courseDao.getCourseComments(courseId);
	}

	@Override
	public User getTeacherByCourseId(String courseId) {
		return courseDao.getTeacherByCourseId(courseId);
	}

	/**
	 * 获取课程by 实验Id
	 * @param courseId
	 * @return
	 */
	@Override
	public Course getCourseById(String courseId){
		return baseDao.getCourseById(courseId);
	}

	/**
	 * 获取实验Id by 课程Id
	 * @param experimentId
	 * @return
	 */
	public String getCourseIdByExperimentId(String experimentId){
		return baseDao.getCourseIdByExperimentId(experimentId);
	}

	@Override
	public void addMycourse(Student student, Experiment experiment) {
		//添加学生的课程记录
		MyCourse myCourse = courseDao.findOneRecord(student.getId(), experiment.getCourseId());
		if(myCourse==null){//第一次学习这个课程
			myCourse=new MyCourse();
			myCourse.setId(UUIDUtils.getUUID());
			myCourse.setStudentId(student.getId());
			myCourse.setCourseId(experiment.getCourseId());
			Course course = courseDao.selectByPrimaryKey(experiment.getCourseId());
			myCourse.setCourseName(course.getCourseName());
			myCourse.setCoursePic(course.getCoursePicFileId());
			myCourse.setLastExperimentNo(experiment.getExperimentNo());
			myCourse.setLastExperimentId(experiment.getExperimentId());
			myCourse.setLastExperimentName(experiment.getExperimentName());
			List<Experiment> experimentList = experimentDao.listByCourseId(experiment.getCourseId());
			myCourse.setExperimentCount(experimentList.size());
			courseDao.addMyCourse(myCourse);
			//课程学习人数+1
			course.setUseCount(course.getUseCount()+1);
			courseDao.updateScore(course);
		}else{
			myCourse.setLastExperimentNo(experiment.getExperimentNo());
			myCourse.setLastExperimentId(experiment.getExperimentId());
			myCourse.setLastExperimentName(experiment.getExperimentName());
			courseDao.addExperiment(myCourse);
		}
	}
}
