package com.awslabplatform.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.awslabplatform.common.Common;
import com.awslabplatform.common.VerCode;
import com.awslabplatform.common.message.EditUserInfoMessage;
import com.awslabplatform.entity.*;
import com.awslabplatform.service.courseManage.CourseService;
import com.awslabplatform.service.reportManage.ReportService;
import com.awslabplatform.service.userManage.UserService;
import com.awslabplatform.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import com.awslabplatform.service.fileInfoService.FileInfoService;


@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private ReportService reportService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private UserService userService;

	@Autowired
	private GenerateVerificationCodeUtils generateVerificationCodeUtils;

	@Autowired
	private MailSenderUtils mailSenderUtils;

	@Autowired
	private FileInfoService fileInfoService;
	/**
	 * 用户信息
	 * @return
	 */
	@RequestMapping(value = "/userInfo",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String user(HttpServletRequest request, HttpServletResponse response, Model model) {
		Student student = (Student) request.getSession().getAttribute("currentUser");
		List<Report> reportList = reportService.listStudentReport(student.getId());
		model.addAttribute("reportList", reportList);
		List<MyCourse> myCourseList = courseService.listMyCourse(student.getId());
		model.addAttribute("myCourseList", myCourseList);
		return "user";
	}

	/**
	 * 修改用户信息，现在只能修改生日和住址
	 * @param birthday
	 * @param address
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/editUserInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Result editUserInfo(String birthday, String address, HttpServletRequest request, HttpServletResponse response){
		Student student = (Student) request.getSession().getAttribute("currentUser");
		if (null != student){
			student.setBirthday(birthday);
			student.setAddress(address);
			userService.updateUserInfo(student);
			request.getSession().setAttribute("currentUser", student);
			return ResultUtil.getResult(true, EditUserInfoMessage.EDIT_USER_INFO_SUCCESS);
		}
		return ResultUtil.getResult(false, EditUserInfoMessage.EDIT_USER_INFOR_ERROR);
	}

	/**
	 * 修改邮箱
	 * @param newEmail
	 * @param verCode
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/editUserEmail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Result editUserEmail(String newEmail, String verCode, HttpServletRequest request){
		Student student = (Student)request.getSession().getAttribute("currentUser");
		if (null==student){
			return ResultUtil.getResult(false, EditUserInfoMessage.LOGIN_UNKNOW_ERROR);
		}
		if (!verCode.equals(generateVerificationCodeUtils.getVerificationCode(newEmail,VerCode.EDIT_EMAIL_VER))){
			return ResultUtil.getResult(false, EditUserInfoMessage.EDIT_VERIFITIONCODE_ERROR);
		}
		userService.updateEmailByUserId(student.getId(), newEmail);
		return ResultUtil.getResult(true, EditUserInfoMessage.EDIT_USER_EMAIL_SUCCESS);
	}

	/**
	 * 修改密码
	 * @param oldPwd
	 * @param newPwd
	 * @param confirmPwd
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/editUserPassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Result resetPassword(String oldPwd, String newPwd, String confirmPwd, HttpServletRequest request, HttpServletResponse response){
		Student student = (Student)request.getSession().getAttribute("currentUser");
		if (null==student){
			return ResultUtil.getResult(false, EditUserInfoMessage.LOGIN_USER_LOCK);
		}
		if (!RandomPasswordUtil.getHash(oldPwd).equals(student.getUserPwd())){
			return ResultUtil.getResult(false, EditUserInfoMessage.EDIT_USER_PWD_OLD_VER_ERROR);
		}
		if (!newPwd.equals(confirmPwd)){
			return ResultUtil.getResult(false, EditUserInfoMessage.EDIT_USER_PWD_CONFIRM_VER_ERROR);
		}

		student.setUserPwd(RandomPasswordUtil.getHash(newPwd));
		userService.updateStudentPwd(student);
		return ResultUtil.getResult(true, EditUserInfoMessage.EDIT_USER_PWD_SCUESS);
	}

	/**
	 * 生成验证码
	 * @param Email
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sendEditEmailVerCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Result sendEmailVerCode(String Email, HttpServletRequest request){
		Student student = (Student)request.getSession().getAttribute("currentUser");
		try{
			if (null != Email && null!=student){
				generateVerificationCodeUtils.saveVerificationCode(Email, VerCode.EDIT_EMAIL_VER);
				System.out.println(generateVerificationCodeUtils.getVerificationCode(Email,VerCode.EDIT_EMAIL_VER));
				mailSenderUtils.send(student.getRealName(), generateVerificationCodeUtils.getVerificationCode(Email,VerCode.EDIT_EMAIL_VER),Email);
			}
		}catch (Exception e){
			return ResultUtil.getResult(false, EditUserInfoMessage.SEND_EDIT_USER_EMAIL_ERROR);
		}
		return ResultUtil.getResult(true, EditUserInfoMessage.SEND_EDIT_USER_EMAIL_SUCCESS);
	}

	@RequestMapping(value = "/uploadStuFace", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Result uploadStuFace(String image, HttpServletRequest request,HttpServletResponse response) throws IOException{
		String url = null ;
		try{
			MultipartFile file = base64ToMultipart.base64ToMultipart(image);
			Student student = (Student) request.getSession().getAttribute("currentUser");
			FileInfo fileInfo = FastDFSUtils.uploadPic(file.getBytes(), file.getOriginalFilename(), file.getSize(), student.getId());
			fileInfo.setCorrelationId(student.getId());
			fileInfoService.insertFileInfo(fileInfo);
			url = fileInfo.getFileUrl();
			student.setPicFileId(fileInfo.getFileId());
			userService.updateUserInfo(student);
			student.setPicFileUrl(url);
			request.getSession().setAttribute("currentUser",student);
		}catch (Exception e){
			return ResultUtil.getResult(false, EditUserInfoMessage.EDIT_USER_FACE_ERROR);
		}
		return ResultUtil.getResult(true, EditUserInfoMessage.EDIT_USER_FACE_SUCCESS, url);
	}
}
