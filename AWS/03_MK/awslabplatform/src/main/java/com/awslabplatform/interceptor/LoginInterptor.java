package com.awslabplatform.interceptor;

import com.awslabplatform.entity.Student;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//登录拦截器
		Student currentuser = (Student)request.getSession().getAttribute("currentUser");
		if (null != currentuser){
			System.out.println("已登录");
			//已登录
			return true;
		}else {
			//未登录，重定向到login页面
			response.sendRedirect(request.getContextPath()+"/login");
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
}
