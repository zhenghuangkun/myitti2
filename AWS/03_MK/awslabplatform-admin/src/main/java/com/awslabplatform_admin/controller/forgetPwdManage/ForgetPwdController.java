package com.awslabplatform_admin.controller.forgetPwdManage;

import com.awslabplatform_admin.common.FreeMarker;
import com.awslabplatform_admin.common.Regex;
import com.awslabplatform_admin.common.VerCode;
import com.awslabplatform_admin.common.message.ForgetPwdMessage;
import com.awslabplatform_admin.entity.Result;
import com.awslabplatform_admin.entity.User;
import com.awslabplatform_admin.service.userManage.UserService;
import com.awslabplatform_admin.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping(value = "/ForgetPwd")
public class ForgetPwdController {
	@Autowired
	private UserService userService;

	@Autowired
	private GenerateVerificationCodeUtils generateVerificationCodeUtils;

	@Autowired
	private MailSenderUtils mailSenderUtils;

	@RequestMapping(value = "/adminForgetPwd", method = RequestMethod.GET)
	public String forgetPwd() {
		return "forgetPwd";
	}

	/**
	 * @param account
	 * @param imgVerCode
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/confirmAccount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Result confirmAccount(String account, String imgVerCode, HttpServletRequest request, HttpServletResponse response) {
		Pattern email = Pattern.compile(Regex.RULE_EMAIL);
		Pattern phone = Pattern.compile(Regex.RULE_PHONE);
		Map<String, String> map = new HashMap<String, String>();
		map.put("账户", account);
		//正则表达式的匹配器
		Matcher email_matcher = email.matcher(account);
		Matcher phone_matcher = phone.matcher(account);
		if (!imgVerCode.toLowerCase().equals((String) request.getSession().getAttribute("imgVerCode"))) {//图片验证码不正确
			return ResultUtil.getResult(false, ForgetPwdMessage.IMG_VER_CODE_ERROR, account);
		}
		if (email_matcher.matches()) {
			if (null != userService.getByUserEmail(account)) {
				request.getSession().setAttribute("EmailAccount", account);
				return ResultUtil.getResult(true, ForgetPwdMessage.VER_USER_SUCCESS);
			}
		} else if (phone_matcher.matches()) {
			if (null != userService.getByUserPhone(account)) {
				request.getSession().setAttribute("PhoneAccount", account);
				return ResultUtil.getResult(true, ForgetPwdMessage.VER_USER_SUCCESS);
			}
		}
		return ResultUtil.getResult(false, ForgetPwdMessage.VER_USER_ERROR, account);
	}

	/**
	 * @param verCode
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/securityVerification", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Result securityVerification(String verCode, HttpServletRequest request, HttpServletResponse response) {
		String Email = (String) request.getSession().getAttribute("EmailAccount");
		String Phone = (String) request.getSession().getAttribute("PhoneAccount");
		if (null == verCode) {
			return ResultUtil.getResult(true, ForgetPwdMessage.VER_USER_EMAIL_ERROR);
		}
		if (null != Email) {
			if (verCode.equals(generateVerificationCodeUtils.getVerificationCode(Email, VerCode.FORGET_PWD_VER))) {
				return ResultUtil.getResult(true, ForgetPwdMessage.VER_USER_EMAIL_SUCCESS);
			}
		} else if (null != Phone) {
			//TODO
			System.out.println("暂不支持手机号");
		}
		return ResultUtil.getResult(false, ForgetPwdMessage.VER_USER_EMAIL_ERROR);
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Result resetPassword(String newPwd, String confirmPwd, HttpServletRequest request, HttpServletResponse response) {
		String Email = (String) request.getSession().getAttribute("EmailAccount");
		String Phone = (String) request.getSession().getAttribute("PhoneAccount");
		User user = null;
		if (null == newPwd || null == confirmPwd) {
			return ResultUtil.getResult(false, ForgetPwdMessage.EDIT_USER_PASSWORD_ERROR);
		}

		if (null != Email) {
			user = userService.getByUserEmail(Email);
			if (null != user) {
				user.setUserPwd(RandomPasswordUtil.getHash(newPwd));
				userService.updateUserPwd(user);
				return ResultUtil.getResult(true, ForgetPwdMessage.EDIT_USER_PASSWORD_SUCCESS);
			}
		} else if (null != Phone) {
			System.out.println("没有手机号这个功能");
		}

		return ResultUtil.getResult(false, ForgetPwdMessage.EDIT_USER_PASSWORD_ERROR);
	}


	//生成图片验证码
	@RequestMapping(value = "/createImgVerCode", method = RequestMethod.GET)
	@ResponseBody
	public void createLoginCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imageVerCode = ImageVerificationUtil.outputCaptcha(request, response);
		request.getSession().setAttribute("imgVerCode", imageVerCode.toLowerCase());
		System.out.println(request.getSession().getAttribute("imgVerCode"));
		//sendEditEmailVerificationCode("wead867@vip.qq.com","123", request, response);

	}

	//发送邮箱验证码
	@RequestMapping(value = "/sendForgetPwdEmailVerCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Result sendEmailVerCode(String Email) {
		try {
			if (null != Email) {
				User user = userService.getByUserEmail(Email);
				generateVerificationCodeUtils.saveVerificationCode(Email, VerCode.FORGET_PWD_VER);
				System.out.println(generateVerificationCodeUtils.getVerificationCode(Email,VerCode.FORGET_PWD_VER));
				mailSenderUtils.send(user.getRealName(), generateVerificationCodeUtils.getVerificationCode(Email,VerCode.FORGET_PWD_VER),Email, FreeMarker.forgetTemplate);
			}
		} catch (Exception e) {
			return ResultUtil.getResult(false, ForgetPwdMessage.SEND_EMAIL_VER_CODE_ERROR);
		}
		return ResultUtil.getResult(true, ForgetPwdMessage.SEND_EMAIL_VER_CODE_SUCCESS);
	}
}
