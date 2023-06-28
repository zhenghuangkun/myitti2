package com.awslabplatform.controller.route;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.awslabplatform.entity.Course;
import com.awslabplatform.entity.CourseType;
import com.awslabplatform.entity.RouteTotle;
import com.awslabplatform.service.courseManage.CourseService;
import com.github.pagehelper.PageInfo;
/**
 *
 * @author lijf
 * @date 2018年4月7日 下午4:47:36
 */
@Controller
public class RouteController {
	/**
	 * log
	 */
	//private static Logger log = LoggerFactory.getLogger(RouteController.class);
	@Autowired
	private CourseService courseService;

	/**
	 * 路径
	 * @return
	 */
	@RequestMapping(value = "/route",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String route(Model model) {
		//查找课程类型
		List<CourseType> courseTypeList = courseService.listCourseType();
		model.addAttribute(courseTypeList);
		return "route";
	}

	/**
	 * 路径详情
	 * @return
	 */
	@RequestMapping(value = "/routedetail",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String routeDetail(Model model,String itemId) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("courseType", itemId);
		condition.put("status", 5);
		PageInfo<Course> pageInfo = courseService.listByCondition(condition, 0, 0, null);
		model.addAttribute("pageInfo",pageInfo);
		//此类课程总时间
		RouteTotle routeTotle = courseService.getTotleTime(itemId);
		int totleTime=routeTotle.getTotleTime()/60+1;
		model.addAttribute("totleTime", totleTime);
		model.addAttribute("userCount", routeTotle.getUserCount());
		return "routedetail";
	}
}
