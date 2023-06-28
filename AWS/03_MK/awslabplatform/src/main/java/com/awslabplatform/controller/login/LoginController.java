package com.awslabplatform.controller.login;


import com.awslabplatform.common.Regex;
import com.awslabplatform.entity.Result;
import com.awslabplatform.entity.Student;
import com.awslabplatform.service.userManage.UserService;
import com.awslabplatform.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awslabplatform.common.message.LoginMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class LoginController {

	/**
	 * log
	 */
	private static Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;


	/**
	 * 用户登录处理
	 *
	 * @param loginInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login/userLogin", method = RequestMethod.POST)
	@ResponseBody
	/*@SystemLog(module = "执行认证", operation = "通过用户名密码请求认证", comment = "用户 ${#user.username} 登录")*/
	public Result login(String loginInfo, String userPwd, String imgVerCode, HttpServletRequest request, HttpServletResponse response,String referrer) throws IOException {

		log.debug(loginInfo + "用户登录");

		Student student = null;
		Pattern email = Pattern.compile(Regex.RULE_EMAIL);
		Pattern phone = Pattern.compile(Regex.RULE_PHONE);
		//正则表达式的匹配器
		Matcher email_matcher = email.matcher(loginInfo);
		Matcher phone_matcher = phone.matcher(loginInfo);

		if (null != request.getSession().getAttribute("imgVerCode") && request.getSession().getAttribute("imgVerCode").equals(imgVerCode.toLowerCase())){
			if (email_matcher.matches()){

				if (null!=userService.getStudentByEmail(loginInfo) && RandomPasswordUtil.getHash(userPwd).equals(userService.getStudentByEmail(loginInfo).getUserPwd())){

					student = userService.getStudentByEmail(loginInfo);

				}else {

					System.out.println("账号密码错误");
					return ResultUtil.getResult(false, LoginMessage.ACCOUNT_OR_PASSWORD_ERROR);
				}
			}else if (phone_matcher.matches()){ //判断是否为手机号

				if (null!=userService.getStudentByPhone(loginInfo) && RandomPasswordUtil.getHash(userPwd).equals(userService.getStudentByPhone(loginInfo).getUserPwd())){

					student = userService.getStudentByPhone(loginInfo);

				}else {

					System.out.println("账号密码错误");
					return ResultUtil.getResult(false, LoginMessage.ACCOUNT_OR_PASSWORD_ERROR);
				}
			}
		}else {

			System.out.println("验证码错误");
			return ResultUtil.getResult(false, LoginMessage.LOGIN_VERIFITIONCODE_ERROR);

		}
		if (null != student){
			
			request.getSession().setAttribute("currentUser",student);
		}else {

			//账号密码错误
			System.out.println("账号密码错误");
			return ResultUtil.getResult(false, LoginMessage.ACCOUNT_OR_PASSWORD_ERROR);

		}
		return ResultUtil.getResult(true, LoginMessage.LOGIN_SUCESS,referrer);
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {

		if (null != request.getSession().getAttribute("currentUser")){
			request.getSession().removeAttribute("currentUser");
			System.out.println("用户退出");
		}
		return "redirect:/home";
	}

}
