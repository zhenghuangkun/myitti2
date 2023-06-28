package com.awslabplatform.controller.forgetPwd;

import com.awslabplatform.common.Regex;
import com.awslabplatform.common.VerCode;
import com.awslabplatform.common.message.ForgetPwdMessage;
import com.awslabplatform.entity.Result;
import com.awslabplatform.entity.Student;
import com.awslabplatform.service.userManage.UserService;
import com.awslabplatform.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class ForgetPwd {

	@Autowired
	private UserService userService;

	@Autowired
	private GenerateVerificationCodeUtils generateVerificationCodeUtils;

	@Autowired
	private MailSenderUtils mailSenderUtils;
	@RequestMapping(value = "/forgetPwd", method = RequestMethod.GET)
	public String forgetPwd(){
		return "forgetPwd";
	}

	/**
	 *
	 * @param account
	 * @param imgVerCode
	 * @param request
	 * @param response
	 * @return
	 * 确认账户，参数1邮箱，参数2图片验证码。
	 */
	@RequestMapping(value = "/confirmAccount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Result confirmAccount(String account, String imgVerCode, HttpServletRequest request, HttpServletResponse response){
		Pattern email = Pattern.compile(Regex.RULE_EMAIL);
		Pattern phone = Pattern.compile(Regex.RULE_PHONE);
		Map<String,String> map = new HashMap<String, String>();
		map.put("账户",account);
		//正则表达式的匹配器
		Matcher email_matcher = email.matcher(account);
		Matcher phone_matcher = phone.matcher(account);
		if (imgVerCode.toLowerCase().equals((String)request.getSession().getAttribute("imgVerCode"))){//验证图片验证码是否正确
			if (email_matcher.matches()){
				if (null!=userService.getStudentByEmail(account)){
					request.getSession().setAttribute("EmailAccount",account);
					return ResultUtil.getResult(true, ForgetPwdMessage.VER_USER_SUCCESS);
				}
			}else if (phone_matcher.matches()){
				if (null!=userService.getStudentByPhone(account)){
					request.getSession().setAttribute("PhoneAccount",account);
					return ResultUtil.getResult(true, ForgetPwdMessage.VER_USER_SUCCESS);
				}
			}
		}else{
			return ResultUtil.getResult(false,ForgetPwdMessage.IMG_VER_CODE_ERROR, account);
		}

		return ResultUtil.getResult(false, ForgetPwdMessage.VER_USER_ERROR, account);
	}

	/**
	 *
	 * @param verCode
	 * @param request
	 * @param response
	 * @return
	 * 安全验证 参数 邮箱验证码
	 */
	@RequestMapping(value = "/securityVerification", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Result securityVerification(String verCode, HttpServletRequest request, HttpServletResponse response){
		String Email = (String)request.getSession().getAttribute("EmailAccount");
		String Phone = (String)request.getSession().getAttribute("PhoneAccount");
		String test = null ;
		if (null == verCode){
			return ResultUtil.getResult(true, ForgetPwdMessage.VER_USER_EMAIL_ERROR);
		}
		if (null!=Email){
			test = generateVerificationCodeUtils.getVerificationCode(Email, VerCode.FORGET_PWD_VER);
			if (verCode.equals(generateVerificationCodeUtils.getVerificationCode(Email, VerCode.FORGET_PWD_VER))){
				return ResultUtil.getResult(true, ForgetPwdMessage.VER_USER_EMAIL_SUCCESS);
			}
		}else if (null!=Phone){
			//TODO
			System.out.println("暂不支持手机号");
		}
		return ResultUtil.getResult(false, ForgetPwdMessage.VER_USER_EMAIL_ERROR);
	}

	/**
	 *
	 * @param newPwd
	 * @param confirmPwd
	 * @param request
	 * @param response
	 * @return
	 * 重设密码	新密码，确认密码
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Result resetPassword(String newPwd, String confirmPwd, HttpServletRequest request, HttpServletResponse response){
		String Email = (String)request.getSession().getAttribute("EmailAccount");
		String Phone = (String)request.getSession().getAttribute("PhoneAccount");
		Student student = null ;
		if (null!=newPwd && null!=confirmPwd){
			if (null != Email){
				student = userService.getStudentByEmail(Email);
				if (null != student){
					student.setUserPwd(RandomPasswordUtil.getHash(newPwd));
					userService.updateStudentPwd(student);
					return ResultUtil.getResult(true, ForgetPwdMessage.EDIT_USER_PASSWORD_SUCCESS);
				}
			}else if (null != Phone){

			}
		}
		return ResultUtil.getResult(false, ForgetPwdMessage.EDIT_USER_PASSWORD_ERROR);
	}

	//发送邮箱验证码
	@RequestMapping(value = "/sendForgetPwdEmailVerCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Result sendEmailVerCode(String Email){
		try{
			if (null != Email){
				Student student = userService.getStudentByEmail(Email);
				generateVerificationCodeUtils.saveVerificationCode(Email,VerCode.FORGET_PWD_VER);
				mailSenderUtils.send(student.getRealName(), generateVerificationCodeUtils.getVerificationCode(Email,VerCode.FORGET_PWD_VER),Email);
				System.out.println(generateVerificationCodeUtils.getVerificationCode(Email,VerCode.FORGET_PWD_VER));
			}
		}catch (Exception e){
			return ResultUtil.getResult(false, ForgetPwdMessage.SEND_EMAIL_VER_CODE_ERROR);
		}
		return ResultUtil.getResult(true, ForgetPwdMessage.SEND_EMAIL_VER_CODE_SUCCESS);
	}
}
