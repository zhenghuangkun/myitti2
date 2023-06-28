package com.awslabplatform.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.awslabplatform.common.message.RoleMessage;
import com.awslabplatform.entity.Course;
import com.awslabplatform.entity.Student;
import com.awslabplatform.service.courseManage.CourseService;
import com.awslabplatform.service.experimentManage.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RoleInterptor implements HandlerInterceptor {
	@Autowired
	private CourseService courseService;

	@Autowired
	private ExperimentService experimentService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String experimentId = request.getParameter("experimentId");
		String courseId = courseService.getCourseIdByExperimentId(experimentId);
		Course course = courseService.getCourseById(courseId);
		Student student = (Student) request.getSession().getAttribute("currentUser");

		JSONObject json = new JSONObject();
		json.put("result", RoleMessage.NAN_ROLE_ERROR);
		response.setContentType("application/json;charset=UTF-8");
		if (course.getOpenRange() == 0){//范围为组
			String groupId = experimentService.getExperimentGroupIdByCourseId(courseId);
			//搜索是否有这个记录
			String id = experimentService.getIdByExperimentGroupIdAnduserId(groupId,student.getId());
			if (null!=id){
				return true;
			}else {
				request.setAttribute("message", "对不起，您不在此课程的实验组中，如有疑问请联系该课程的老师。");
				request.getRequestDispatcher("/coursedetail?courseId="+courseId).forward(request, response);
				return false;
			}
		}else if (course.getOpenRange() == 1){//范围为学院
			//该学生不是该课程所属学院的
			if (!course.getCollegeId().equals(student.getMechanismId())){
				request.setAttribute("message", "对不起，您不在此课程的实验组中，如有疑问请联系该课程的老师。");
				request.getRequestDispatcher("/coursedetail?courseId="+courseId).forward(request, response);
				return false;
			}else {
				return true;
			}
		}
//		else if (course.getOpenRange() == 2){//范围为学校
//			//该学生不是该课程所属学校的
//			if (!course.getCollegeId().equals(student.getSchoolId())){
//				request.setAttribute("message", "sorry,这是其它学校私有课程");
//				request.getRequestDispatcher(request.getContextPath()+"/coursedetail?courseId="+courseId).forward(request, response);
//				return false;
//			}else {
//				return true;
//			}
//		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
}
