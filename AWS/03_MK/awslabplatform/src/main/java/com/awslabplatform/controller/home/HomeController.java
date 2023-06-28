package com.awslabplatform.controller.home;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.awslabplatform.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.awslabplatform.entity.Course;
import com.awslabplatform.service.courseManage.CourseService;
import com.github.pagehelper.PageInfo;
/**
 *
 * @author lijf
 * @date 2018年4月7日 下午4:47:36
 */
@Controller
public class HomeController {
	/**
	 * log
	 */
	private static Logger log = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private CourseService courseService;

	/**
	 * 首页跳转
	 * @return
	 */
	@RequestMapping(value = "/home",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String goHome(Model model, HttpServletRequest request,String orderBy,String currentPage) {
		if(orderBy!=null&&!"".equals(orderBy)){
			model.addAttribute("orderBy", orderBy);
		}else{
			orderBy="useCount";
		}
		orderBy=orderBy+" DESC";
		if(currentPage==null||"".equals(currentPage)){
			currentPage="1";
		}
		Integer current = Integer.parseInt(currentPage);
		int mypageSize = 8;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("status", 5);
		PageInfo<Course> pageInfo = courseService.listByCondition(condition, current, mypageSize, orderBy);
		model.addAttribute(pageInfo);
		Student student = (Student) request.getSession().getAttribute("currentUser");
		if (null!=student){
			if (null==student.getPicFileId()){
				model.addAttribute("defaultFace","http://120.79.132.170/group1/M00/00/09/rBD8JFrQc9OAbJISAACExmnyTeY704_big.jpg");
			}
		}
		return "home";
	}
}
