package com.awslabplatform_admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awslabplatform_admin.common.Common;
import com.awslabplatform_admin.common.Dictionary;
import com.awslabplatform_admin.entity.*;
import com.awslabplatform_admin.service.connmon.FileInfoService;
import com.awslabplatform_admin.service.mechanismManage.MechanismService;
import com.awslabplatform_admin.service.policy.PolicyService;
import com.awslabplatform_admin.service.systemManage.DictionaryDataService;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.awslabplatform_admin.service.template.TemplateService;
import com.awslabplatform_admin.service.template.TmplMakingService;
import com.awslabplatform_admin.util.ShiroUtil;
import com.awslabplatform_admin.util.UUIDUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MenuPageController {
    /**模板管理service*/
    @Autowired
    private  TemplateService  templateService;

    @Autowired
    private TmplMakingService tmplMakingService;
    
    @Autowired
	private MechanismService mechanismService;

    @Autowired
	private DictionaryDataService dictionaryDataService;

	@Autowired
	private FileInfoService fileInfoService;
	
	/**
	 * 策略管理service
	 */
	@Autowired
	private PolicyService policyService;
	/**
	 * 学生管理
	 * @return
	 */
	@RequiresRoles(value={"teacher","admin"}, logical=Logical.OR)
	@RequestMapping(value = "/usermgt/home",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String usermgtHome() {
		return "/userManage/student_home";
	}

	/**
	 * 平台管理
	 * @return
	 */
	@RequiresRoles(value={"teacher","admin"}, logical=Logical.OR)
	@RequestMapping(value = "/usermgt/platform", method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String usermgtPtf() {
		return "/userManage/userPtf";
	}

	/**
	 * 院系管理
	 * @return
	 */
	@RequiresRoles(value={"admin"}, logical=Logical.OR)
	@RequestMapping(value = "/usermgt/department", method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String usermgtDept() {
		return "/userManage/userDept";
	}

	/**
	 * 教师、助教管理
	 * @return
	 */
	@RequiresRoles(value={"admin"}, logical=Logical.OR)
	@RequestMapping(value = "/usermgt/teacher", method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String usermgtTea() {
		return "/userManage/userTea";
	}

	/**
	 * 学生管理
	 * @return
	 */
	@RequiresRoles(value={"admin"}, logical=Logical.OR)
	@RequestMapping(value = "/usermgt/student", method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String usermgtStu() {
		return "/userManage/userStu";
	}


	/**
	 * 菜单管理
	 * @return
	 */
	@RequiresRoles(value={"admin"}, logical=Logical.OR)
	@RequestMapping(value = "/systemgt/menu",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String systemgtMenu() {
		return "/systemManage/menu";
	}

	/**
	 * 实验组管理
	 * @return
	 */
	@RequiresRoles(value={"teacher"}, logical=Logical.OR)
	@RequestMapping(value = "/coursemgt/experimentGroup",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String coursemgtExperimentGroup(Model m) {
		
		// 保存所有院系信息
		List<Mechanism> collegeList = new ArrayList<Mechanism>();

		collegeList = mechanismService.getParentMechanismList(ShiroUtil.getCurrentUser().getSchoolId());
		
		m.addAttribute("collegeList", collegeList);
		return "/courseManage/experimentGroup";
	}

	/**
	 * 课程管理
	 * @return
	 */
	@RequiresRoles(value={"teacher"}, logical=Logical.OR)
	@RequestMapping(value = "/coursemgt/courseinfo",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String coursemgtCourseInfo() {
		return "/courseManage/courseManage";
	}


	/**
	 * 添加课程
	 * @return
	 */
	@RequiresRoles(value={"teacher"}, logical=Logical.OR)
	@RequestMapping(value = "/coursemgt/addcourse",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String coursemgtAddCourse(Model m) {
		String uuid = UUIDUtils.getUUID();
		m.addAttribute("courseUid", uuid);
		m.addAttribute("requestType", "add");
		String teacherId = ShiroUtil.getCurrentUser().getUserId();
		if(teacherId != null && !teacherId.isEmpty()){
			m.addAttribute("teacherId", teacherId);
		}

		//获取教师所属院系信息
		//Mechanism teacherInfo = mechanismService.getMechanismData(ShiroUtil.getCurrentUser().getDepartmentId());
		// 保存所有院系信息
		List<Mechanism> collegeList = new ArrayList<Mechanism>();

		collegeList = mechanismService.getParentMechanismList(ShiroUtil.getCurrentUser().getSchoolId());

		//获取课程类型
		String courseTypeDicId = dictionaryDataService.getDicIdByDicCode(Dictionary.DICTIONARY_COURSETYPE.getDicCode());
		List<DictionaryData> courseTypeList = new ArrayList<DictionaryData>();
		courseTypeList = dictionaryDataService.getCourseTypeData(courseTypeDicId);


		//获取课程等级
		String courseLevelDicId = dictionaryDataService.getDicIdByDicCode(Dictionary.DICTIONARY_COURSELEVEL.getDicCode());
		List<DictionaryData> courseLevelList = new ArrayList<DictionaryData>();
		courseLevelList = dictionaryDataService.getCourseLevelData(courseLevelDicId);

		//获取实验模板(模板库)
		List<Template> templateList = templateService.getTemplateList();

		//获取实验模板(个人模板)
		List<Template> persionTemplateList = tmplMakingService.getTemplateList();
		
		//获取秘钥
		List<FileInfo> keyList = fileInfoService.getKeyByFileInfoType();

		// 取得策略list
		Map<String, Object> condition=new HashMap<String, Object>();
		condition.put("status", 1);
		List<Policy> policyList = policyService.listBycondition(condition);
		m.addAttribute("policyList", policyList);
		
		m.addAttribute("collegeList", collegeList);
		//m.addAttribute("teacherInfo", teacherInfo);
		m.addAttribute("courseTypeList", courseTypeList);
		m.addAttribute("courseLevelList", courseLevelList);
		m.addAttribute("templateList", templateList);
		m.addAttribute("persionTemplateList", persionTemplateList);
		m.addAttribute("keyList", keyList);
		return "/courseManage/addCourse";
	}

	/**
	 * 课程审核列表
	 * @return
	 */
	@RequiresRoles(value={"admin"}, logical=Logical.OR)
	@RequestMapping(value = "/resourceReview/courserwManageList",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String resourcerwCourseManageList() {
		return "/resourceReview/courseReviewManageList";
	}

	/**
	 * 课程审核列表
	 * @return
	 */
	@RequiresRoles(value={"admin", "teacher"}, logical=Logical.OR)
	@RequestMapping(value = "/coursemg/experimentGroupDetail",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String experimentGroupDetail() {
		return "/courseManage/experimentGroupDetailInfo";
	}



	/**
	 * 资源概览
	 * @return
	 */
	@RequiresRoles(value={"teacher", "admin"}, logical=Logical.OR)
	@RequestMapping(value = "/home/overview",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String homeOverview() {
		int rotype=ShiroUtil.getCurrentUser().getRoleType();
		if (rotype==Common.ROLE_TYPE_ADMIN ||rotype==Common.ROLE_TYPE_PLATFORM) {
			return "/home/platformOverview";
		}else if(rotype==Common.ROLE_TYPE_DEPARTMENTS){
			return "/home/collegeOverview";
		}else{
			return "/home/teacherOverview";
		}	
	}
	
	@RequestMapping(value = "/home/platformOverview",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String platformOverview() {	
			return "/home/platformOverview";
	}	
	
	@RequestMapping(value = "/tactics/tacticsManagement",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String tacticsManagement() {	
			return "/tactics/tacticsManagement";
	}

	/**
	 * 平台AWS账号池
	 * @return
	 */
	@RequiresRoles(value={"admin"}, logical=Logical.OR)
	@RequestMapping(value = "/awsManage/awsAccount",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String awsAccount() {
		return "/awsAccount/awsAccount";
	}

	/**
	 * 院系AWS账号池
	 * @return
	 */
	@RequiresRoles(value={"admin"}, logical=Logical.OR)
	@RequestMapping(value = "/awsManage/awsIAM",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String awsIAM() {
		return "/awsIAM/awsIamPool";
	}

	/**
	 * 数据字典
	 * @return
	 */
	@RequiresRoles(value={"admin"}, logical=Logical.OR)
	@RequestMapping(value = "/systemgt/dictionaryData",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String systemgtDictionaryData() {
		return "/systemManage/dictionaryData";
	}

	/**
	 * 机构管理
	 * @return
	 */
	@RequiresRoles(value={"admin"}, logical=Logical.OR)
	@RequestMapping(value = "/mechanismManage/mechanism",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String mechanismData() {
		return "/mechanismManage/mechanism";
	}

	/**
	 *首页
	 * @return
	 */
	@RequestMapping(value = "/index",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String pageLogin(HttpServletRequest req, HttpServletResponse resp){
		return "/login";
	}
}
