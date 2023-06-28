package com.awslabplatform.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.awslabplatform.entity.Student;
import com.awslabplatform.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awslabplatform.common.message.EditUserInfoMessage;
import com.awslabplatform.entity.Result;
import com.awslabplatform.service.courseManage.CourseService;
import com.awslabplatform.service.reportManage.ReportService;
import com.awslabplatform.service.userManage.UserService;

/**
 *
 * @author lijf
 * date 2018年3月18日 下午4:06:47
 */
@Controller
public class PageController {
	@Autowired
	private CourseService courseService;

	@Autowired
	private MailSenderUtils mailSenderUtils;

	@Autowired
	private UserService userService;
	@Autowired
	private ReportService reportService;

	/**
	 * 登陆页面
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String login(Model model, HttpServletRequest request, HttpServletResponse response,String URL) throws IOException {
		return "login";
	}



	//发送Email
	@RequestMapping(value = "/sendEmailVerificationCode")
	public void sendEditEmailVerificationCode(String Email,String condition, HttpServletRequest request, HttpServletResponse response) throws IOException {
		mailSenderUtils.send("Henry", condition, Email);
	}


	//生成图片验证码
	@RequestMapping(value = "/createLoginCode", method = RequestMethod.GET)
	@ResponseBody
	public void createLoginCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imageVerCode = ImageVerificationUtil.outputCaptcha(request, response);
		request.getSession().setAttribute("imgVerCode", imageVerCode.toLowerCase());
		System.out.println(request.getSession().getAttribute("imgVerCode"));
		//sendEditEmailVerificationCode("wead867@vip.qq.com","123", request, response);

	}


	//修改密码
	@RequestMapping(value = "/editPwd")
	@ResponseBody
	public Result editPwd(String OldPwd, String NewPwd, String ConfirmPwd, HttpServletRequest request){
		if (null != OldPwd && null != NewPwd && null != ConfirmPwd){
			Student currentUser = (Student) request.getSession().getAttribute("currentUser");
			if (RandomPasswordUtil.getHash(OldPwd).equals(currentUser.getUserPwd())){
				if (NewPwd.equals(ConfirmPwd)){
					userService.updatePwdByUserId(currentUser.getId(), NewPwd);
				}else {
					return ResultUtil.getResult(false, EditUserInfoMessage.EDIT_USER_PWD_CONFIRM_VER_ERROR);
				}
			}else {
				return ResultUtil.getResult(false, EditUserInfoMessage.EDIT_USER_PWD_OLD_VER_ERROR);
			}
		}else {
			return ResultUtil.getResult(false, EditUserInfoMessage.EDIT_USER_PWD_NULL_ERROR);
		}
		return ResultUtil.getResult(true, EditUserInfoMessage.EDIT_USER_PWD_SCUESS);
	}

	//修改邮箱
	@RequestMapping(value = "/editEmail")
	@ResponseBody
	public Result editEmail(String newEmail, String verCode, HttpServletRequest request, HttpServletResponse response){
		if (null!=newEmail && null!=verCode){
			Student  student = (Student) request.getSession().getAttribute("currentUser");
		}
		return null ;
	}




}
