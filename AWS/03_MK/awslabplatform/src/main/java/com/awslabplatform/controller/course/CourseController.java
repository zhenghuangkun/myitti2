package com.awslabplatform.controller.course;


import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.awslabplatform.aws.IAMClient;
import com.awslabplatform.entity.*;

import com.awslabplatform.service.userManage.UserService;
import com.awslabplatform.util.FileUtil;
import com.awslabplatform.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awslabplatform.service.courseManage.CourseService;
import com.awslabplatform.service.experimentManage.ExperimentService;
import com.awslabplatform.util.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import static com.awslabplatform.common.Common.*;

/**
 *
 * @author lijf
 * @date 2018年4月7日 下午4:47:36
 */
@Controller
public class CourseController {
	/**
	 * log
	 */
	private static Logger log = LoggerFactory.getLogger(CourseController.class);
	@Autowired
	private CourseService courseService;
	@Autowired
	private UserService userService;
	@Autowired
	private ExperimentService experimentService;
	/**
	 * 添加评论
	 * @return
	 */
	@RequestMapping(value = "/addComment",  method = RequestMethod.POST , produces = "text/html; charset=UTF-8")
	public String addComment(String courseId,String content,int rating,HttpServletRequest request) {
		Student student = (Student) request.getSession().getAttribute("currentUser");
		if(student==null){
			return "redirect:/login";
		}
		CourseComment courseComment = new CourseComment();
		courseComment.setContent(content);
		courseComment.setCourseId(courseId);
		courseComment.setScore(rating);
		courseComment.setUserId(student.getId());
		courseService.addCourseComment(courseComment);
		return "redirect:/coursedetail?courseId="+courseId;
	}
	@RequestMapping(value = "/getComments",  method = RequestMethod.POST)
	@ResponseBody
	public Result getComments(int page, int pageSize, String courseId ){
		PageHelper.startPage(page, pageSize, "commentTime DESC");
		List<CourseComment> courseComments = courseService.getCourseComments(courseId);
		PageInfo<CourseComment> pageInfo = new PageInfo<CourseComment>(courseComments);
		return ResultUtil.getResult(true, "success", pageInfo);
	}
	/**
	 * 课程列表
	 * @return
	 */
	@RequestMapping(value = "/courselist",   produces = "text/html; charset=UTF-8")
	public String courselist(Model model,String itemId,String currentPage,String pageSize,String courseName) {
		//查找课程类型
		List<CourseType> courseTypeList = courseService.listCourseType();
		model.addAttribute(courseTypeList);
		Map<String, Object> condition = new HashMap<String, Object>();
		//课程类型不为空时加入条件
		if(itemId!=null&&!"".equals(itemId)){
			condition.put("courseType", itemId);
			model.addAttribute("itemId",itemId);
		}
		condition.put("status", 5);
		if(currentPage==null||"".equals(currentPage)){
			currentPage="1";
		}
		Integer current = Integer.parseInt(currentPage);
		if(pageSize==null||"".equals(pageSize)){
			pageSize="0";
		}
		Integer mypageSize = Integer.parseInt(pageSize);
		PageInfo<Course> pageInfo=null;
		//搜索课程
		if(courseName!=null&&!"".equals(courseName.trim())){
			condition.put("courseName", courseName);
			model.addAttribute("courseName",courseName);
			pageInfo = courseService.searchCourse(condition, current, mypageSize, null);
		}else{
		//得到分页信息
			pageInfo = courseService.listByCondition(condition, current, mypageSize, null);
		}
		model.addAttribute(pageInfo);
		return "courselist";
	}
	/**
	 * 课程详情
	 * @return
	 */
	@RequestMapping(value = "/coursedetail",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String coursedetail(String courseId,Model model) {
		//课程信息
		Course course = courseService.selectByPrimaryKey(courseId);
		model.addAttribute(course);
		User teacher = courseService.getTeacherByCourseId(courseId);
		model.addAttribute("teacher", teacher);
		//本课程的实验
		List<Experiment> experimentList = experimentService.listByCourseId(courseId);
		model.addAttribute(experimentList);
		return "coursedetail";
	}


}
