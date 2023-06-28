package com.awslabplatform_admin.interceptor;

import com.awslabplatform_admin.util.GenerateVerificationCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RiskControlInterceptor implements HandlerInterceptor {
	@Autowired
	private GenerateVerificationCodeUtils generateVerificationCodeUtils;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String sessionId = request.getSession().getId();
		if (null!=generateVerificationCodeUtils.getRiskControlVerCode(sessionId)){//重复发送了
			request.setAttribute("message", "请不要重复发送验证码，60秒以后再试");
			request.getRequestDispatcher(request.getContextPath()+"/login/userLogin").forward(request, response);
			return false;
		}else {
			generateVerificationCodeUtils.saveRiskControlVerCode(sessionId);
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
}
