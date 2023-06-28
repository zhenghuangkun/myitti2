package com.awslabplatform_admin.controller.personnalManage;

import com.awslabplatform_admin.common.FreeMarker;
import com.awslabplatform_admin.common.VerCode;
import com.awslabplatform_admin.common.message.UserMessage;
import com.awslabplatform_admin.entity.Result;
import com.awslabplatform_admin.entity.User;
import com.awslabplatform_admin.service.userManage.UserService;
import com.awslabplatform_admin.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class personnalController {

	@Autowired
	private UserService userService;

	@Autowired
	private GenerateVerificationCodeUtils generateVerificationCodeUtils;

	@Autowired
	private MailSenderUtils mailSenderUtils;
	/**
	 * 个人信息页面
	 */
	@RequestMapping(value = "/personnalInfo", method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String personnalInfo() {
		return "/personnalInfo/personnalInfo";
	}

	/**
	 * 修改密码页面
	 */
	@RequestMapping(value = "/personnalPwd", method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String personnalPwd() {
		return "/personnalInfo/personnalPwd";
	}

	/**
	 * 修改密码
	 * @param oldPwd
	 * @param newPwd
	 * @param confirmPwd
	 * @return
	 */
	@RequestMapping(value = "/personnalEditPwd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Result personnalEditPwd(String oldPwd, String newPwd, String confirmPwd){
		User user = ShiroUtil.getCurrentUser();
		if (null==oldPwd && null==newPwd && null==confirmPwd){//输入为空
			return ResultUtil.getResult(false, UserMessage.EDIT_USER_PWD_ERROR);
		}
		if (!newPwd.equals(confirmPwd)){//密码不一致
			return ResultUtil.getResult(false, UserMessage.EDIT_USER_PWD_ERROR);
		}
		user.setUserPwd(RandomPasswordUtil.getHash(newPwd));
		userService.updateUserPwd(user);
		return ResultUtil.getResult(true, UserMessage.EDIT_USER_PWD_SUCCESS);
	}

	@RequestMapping(value = "/sendEditPwdVerCode")
	public Result sendEditPwdVerCode(String Email){
		if (null != Email){
			User user = ShiroUtil.getCurrentUser();
			generateVerificationCodeUtils.saveVerificationCode(Email, VerCode.EDIT_USER_PWD_VER);
			mailSenderUtils.send(user.getRealName(), generateVerificationCodeUtils.getVerificationCode(Email,VerCode.EDIT_USER_PWD_VER),Email, FreeMarker.editPwdTemplate);
		}else {
			return ResultUtil.getResult(false, UserMessage.SEND_EDIT_PWD_EMAIL_CODE_ERROR);
		}
		return ResultUtil.getResult(true, UserMessage.SEND_EDIT_PWD_EMAIL_CODE_SUCCESS);
	}
}
