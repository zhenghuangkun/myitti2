package com.awslabplatform_admin.controller.login;


import com.awslabplatform_admin.common.*;
import com.awslabplatform_admin.entity.Result;
import com.awslabplatform_admin.service.userManage.UserService;
import com.awslabplatform_admin.util.*;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.awslabplatform_admin.annotation.SystemLog;
import com.awslabplatform_admin.common.Regex;
import com.awslabplatform_admin.common.message.LoginMessage;
import com.awslabplatform_admin.entity.MailInfo;
import com.awslabplatform_admin.entity.Menu;
import com.awslabplatform_admin.entity.User;
import com.awslabplatform_admin.service.systemManage.MenuService;
import com.awslabplatform_admin.service.userManage.UserService;

import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class LoginController {

	/**
	 * log
	 */
	private static Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private MenuService menuService;

	@Autowired
	private UserService userService;

	@Autowired
	private GenerateVerificationCodeUtils generateVerificationCodeUtils;

	@Autowired
	private MailSenderUtils mailSenderUtils;

	/**
	 * 用户登录处理
	 *
	 * @param loginInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login/userLogin", method = RequestMethod.POST)
	@SystemLog(module = "执行认证", operation = "通过用户名密码请求认证", comment = "用户 ${#user.username} 登录")
	public void login(String loginInfo, String userPwd, String verCode, HttpServletRequest request, HttpServletResponse response) throws IOException {

		log.debug(loginInfo + "用户登录");

		//获取认证主体，如果主体已存在，则将当前的主体退出
		Subject subject = SecurityUtils.getSubject();

		//sendEmailVerCode("熊宝宝","wead867@vip.qq.com","USER_VER");
		/**
		 * 存储登录返回前端的值
		 */
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			/**
			 * shiro 登录认证和授权
			 */
			/*UsernamePasswordToken token = new UsernamePasswordToken(loginInfo, userPwd);
			subject.login(token);
			JsonTool.toJson(true, String.valueOf(LoginMessage.LOGIN_SUCESS.getCode()), null, response);
			loadUserInfo(loginInfo, verCode, request, response, subject, resultMap);*/
			//验证输入的邮箱/手机号验证码是否正确，不正确则返回输入错误信息
			//if (("".equals(verCode))){
				/*CH 邮箱功能修改，将下列注释清除，上一行代码删除即可使用 (修改位置三)*/
			if (!("".equals(verCode))){
				Boolean result = loadUserInfo(loginInfo, verCode, request, response, subject, resultMap);
				//验证码校验失败
				if (!result){
					resultMap.put("code", LoginMessage.VALIDATION_CODE.getCode());
					resultMap.put("username", loginInfo);
					resultMap.put("errorInfo", LoginMessage.VALIDATION_CODE.getContent());
					JsonTool.toJson(resultMap, response);
				}
				UsernamePasswordToken token = new UsernamePasswordToken(loginInfo, userPwd);
				subject.login(token);
				JsonTool.toJson(true, String.valueOf(LoginMessage.LOGIN_SUCESS.getCode()), null, response);
			}else {
				resultMap.put("code", LoginMessage.VALIDATION_CODE.getCode());
				resultMap.put("username", loginInfo);
				resultMap.put("errorInfo", LoginMessage.VALIDATION_CODE.getContent());
				JsonTool.toJson(resultMap, response);
			}
		} catch (LockedAccountException ex) {
			resultMap.put("code", LoginMessage.LOGIN_USER_LOCK.getCode());
			resultMap.put("username", loginInfo);
			resultMap.put("errorInfo", LoginMessage.LOGIN_USER_LOCK.getContent());
			JsonTool.toJson(resultMap, response);

		} catch (ExcessiveAttemptsException e) {
			resultMap.put("code", LoginMessage.LOGIN_ACCOUNT_OVER.getCode());
			resultMap.put("username", loginInfo);
			resultMap.put("errorInfo", LoginMessage.LOGIN_ACCOUNT_OVER.getContent());
			JsonTool.toJson(resultMap, response);

		} catch (AuthenticationException ex) {
			resultMap.put("code" , LoginMessage.LOGIN_UNKNOW_ERROR.getCode());
			resultMap.put("username" ,  loginInfo);
			resultMap.put("errorInfo",  LoginMessage.LOGIN_UNKNOW_ERROR.getContent());
			JsonTool.toJson(resultMap, response);
		} catch (Exception e) {
			System.out.print("Send ERROR !!!");
			System.out.print(e.toString());
			log.error("error", e);
			JsonTool.toJson(false, "发生未知异常" + e.getMessage(), response);
		}
	}

	private Boolean loadUserInfo(String loginInfo,String verCode,HttpServletRequest request, HttpServletResponse response, Subject subject, Map<String, Object> resultMap) throws IOException {
		/**
		 * 获取数据库中的menu 然后将菜单数据转换成树形结构
		 */
		//正则表达式的模式 编译正则表达式
		User user = null;
		Pattern email = Pattern.compile(Regex.RULE_EMAIL);
		Pattern phone = Pattern.compile(Regex.RULE_PHONE);
		//正则表达式的匹配器
		Matcher email_matcher = email.matcher(loginInfo);
		Matcher phone_matcher = phone.matcher(loginInfo);

		if (email_matcher.matches()) {
			/*CH 邮箱功能修改，将下列注释清除即可使用 (修改位置二)*/
			if (generateVerificationCodeUtils.validationCode(loginInfo, verCode, VerCode.LOGIN_VER)) {
				user = userService.getByUserEmail(loginInfo);
				//校验成功，登录删除redis验证码
				generateVerificationCodeUtils.deleteVerificationCode(loginInfo,VerCode.LOGIN_VER);
			} else {
				return false;
			}
		} else if (phone_matcher.matches()) { //判断是否为手机号
			user = userService.getByUserPhone(loginInfo);
			//TODO 检测手机验证码是否正确
		}
		Map<String, Object> condition = new HashMap<>();
		condition.put("state", Common.STATE_ACTIVE);
		condition.put("userId", user.getUserId());
		List<Menu> menuList = menuService.listMenuByUserId(condition);
		if (menuList.size() != 0) {
			List<Menu> menuTree = FormatTreeUtils.formatTree(menuList);

			/**
			 * 将登录实体的对象放入session 中
			 */
			Session session = subject.getSession();
			session.setAttribute(Common.SESSION_KEY, subject.getPrincipal());

			/**
			 * 将菜单列表转成json 格式数据
			 */
			session.setAttribute("menus", menuTree);
			session.setAttribute("userRealName", user.getRealName());
			session.setAttribute("departmentName", user.getDepartmentName());
			session.setAttribute("schoolName", user.getMechanismName());
			
			//String menus = request.getSession().getAttribute("userName").toString();
			//System.out.println(menus);
		} else {
			log.error("menuList is null");
		}
		return true;
	}


	@RequestMapping(value = "/routing", method = RequestMethod.GET)
	public String routing(@SessionAttribute List<Menu> menus) {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			if (Validator.isNotEmpty(menus)) {
				try {
					String menuUrl = menus.get(0).getChildMenus().get(0).getMenuUrl();
					return "redirect:" + menuUrl;
				} catch (Exception e) {
					log.error("读取菜单错误", e);
				}
			}
		}
		/*if (ShiroUtil.hasOneRoles(RoleIdCommon.CENTER_ADMIN_LIST_STRING) || currentUser.hasRole(String.valueOf(RoleIdCommon.ADMINISTRATOR)) ) {
			return "redirect:/pages/tennement/overView_admin.jsp";
		}else if(currentUser.hasRole(String.valueOf(RoleIdCommon.TENANTS)) || currentUser.hasRole(String.valueOf(RoleIdCommon.VIP_TENANTS))){
			return "redirect:/pages/home/home.jsp";
		}else if(currentUser.hasRole(String.valueOf(RoleIdCommon.SUPPLIER_LangChao)) || currentUser.hasRole(String.valueOf(RoleIdCommon.SUPPLIER_TaiJi))){
			return "redirect:/pages/productSearch/cloud_search.jsp";
		}*/
		return "redirect:/index";
	}

	@RequestMapping(value = "/sendEmailVerCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Result sendEmailVerCode(String Email){
		User user = userService.getByUserEmail(Email);
		try{
			if (null!=user){
				/*CH 邮箱功能修改，将注释清除即可使用 (修改位置一)*/
				generateVerificationCodeUtils.saveVerificationCode(Email, VerCode.LOGIN_VER);
				System.out.println(generateVerificationCodeUtils.getVerificationCode(Email,VerCode.LOGIN_VER));
				mailSenderUtils.send(user.getRealName(), generateVerificationCodeUtils.getVerificationCode(Email,VerCode.LOGIN_VER), Email, FreeMarker.loginTemplate);
			}else {
				return ResultUtil.getResult(false,LoginMessage.USER_NOT_FOUND);
			}
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.getResult(false,LoginMessage.MAIL_SEND_ERROR);
		}
		return ResultUtil.getResult(true,LoginMessage.MAIL_SEND_SUCCESS);
	}
}
