package com.awslabplatform_admin.controller.courseManage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.awslabplatform_admin.common.Common;
import com.awslabplatform_admin.common.Dictionary;
import com.awslabplatform_admin.common.message.CourseMessage;
import com.awslabplatform_admin.common.message.TmplMessage;
import com.awslabplatform_admin.entity.AwsInstance;
import com.awslabplatform_admin.entity.Course;
import com.awslabplatform_admin.entity.CourseCheckHistory;
import com.awslabplatform_admin.entity.DictionaryData;
import com.awslabplatform_admin.entity.Experiment;
import com.awslabplatform_admin.entity.ExperimentGroup;
import com.awslabplatform_admin.entity.FileInfo;
import com.awslabplatform_admin.entity.Mechanism;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Policy;
import com.awslabplatform_admin.entity.Report;
import com.awslabplatform_admin.entity.Result;
import com.awslabplatform_admin.entity.Student;
import com.awslabplatform_admin.entity.Template;
import com.awslabplatform_admin.entity.User;
import com.awslabplatform_admin.service.awsAccountManage.AwsIamsService;
import com.awslabplatform_admin.service.connmon.FileInfoService;
import com.awslabplatform_admin.service.courseManage.CourseService;
import com.awslabplatform_admin.service.courseManage.ExperimentGroupService;
import com.awslabplatform_admin.service.courseManage.ExperimentService;
import com.awslabplatform_admin.service.mechanismManage.MechanismService;
import com.awslabplatform_admin.service.policy.PolicyService;
import com.awslabplatform_admin.service.systemManage.DictionaryDataService;
import com.awslabplatform_admin.service.template.TemplateService;
import com.awslabplatform_admin.service.template.TmplMakingService;
import com.awslabplatform_admin.service.uploadService.uploadService;
import com.awslabplatform_admin.service.userManage.UserService;
import com.awslabplatform_admin.util.JsonTool;
import com.awslabplatform_admin.util.MyException;
import com.awslabplatform_admin.util.ResultUtil;
import com.awslabplatform_admin.util.ShiroUtil;
import com.awslabplatform_admin.util.TimeUtils;
import com.awslabplatform_admin.util.UUIDUtils;

@Controller
@RequestMapping("course")
public class CourseController {

	/**
	 * 课程service
	 */
	@Autowired
	private CourseService courseService;
	/**
	 * 实验组service
	 */
	@Autowired
	private  ExperimentGroupService  expGroupService;
	/**
	 * 实验service
	 */
	@Autowired
	private ExperimentService expService;
	/**
	 * 文件上传service
	 */
	@Autowired
	private uploadService uploadsv;
	/**
	 * 文件描述service
	 */
	@Autowired
	private FileInfoService fileService;

	/**
	 * 机构管理service
	 */
	@Autowired
	private MechanismService mechanismService;
	
	/**
	 * 数据字典管理service
	 */
	@Autowired
	private DictionaryDataService dictionaryDataService;

	/**
	 * 模板管理service(模板库)
	 */
	@Autowired
	private TemplateService templateService;

	/**
	 * 模板管理service(个人模板)
	 */
	@Autowired
    private TmplMakingService tmplMakingService;
	
	/**
	 * 用户管理service
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * awsIam管理service
	 */
	@Autowired
	private AwsIamsService awsIamsService;

	/**
	 * 策略管理service
	 */
	@Autowired
	private PolicyService policyService;
	
	@RequestMapping("/getCourse")
	public void getCourse(String courseIdOrName, int start, int length, int draw,
	                             @RequestParam(defaultValue = "-1") int state, @RequestParam(defaultValue = "0") int checkState, HttpServletResponse response) {
		/* 设置查询条件 */
		Map<String, Object> condition = new HashMap<>();
		if(courseIdOrName != null && !courseIdOrName.isEmpty()){
			condition.put("courseIdOrName", courseIdOrName.trim());
		}

		if(state != -1){
			condition.put("state", state);
		}
		
		
		// 取得教师Id
		String teacherId = ShiroUtil.getCurrentUser().getUserId();

		int userRoleType = ShiroUtil.getCurrentUser().getRoleType();

		// 当前用户为教师或者助教，添加教师ID查询条件
		if(userRoleType == User.TEARCH_USER || userRoleType == User.ASSISTANT_USER){
			condition.put("teacherId", teacherId);
		}

		/*资源审核 取得同一院系里的课程且课程状态为已提交*/
		if(checkState != 0){
			condition.put("checkState", checkState);
			
			// 判断当前是不是院系管理员
			if(userRoleType == User.DEPARTMENT_MANAGE_USER){
				// 是院系管理员-取得当前管理员的学院ID-添加检索条件-检索学院相同的课程
				String collegeId = ShiroUtil.getCurrentUser().getDepartmentId();
				condition.put("collegeId", collegeId);
			}
			
		}
		
		/* 分页查询课程 */
		PageInfo pageInfo = new PageInfo(start, length, draw);
		pageInfo.setCondition(condition);
		courseService.getCourseInfoTotal(pageInfo);
		/* 数据返回 */
		//return pageInfo;
		try {
			JsonTool.toJson(pageInfo, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取得所有实验组信息
	 * @param findValue
	 * @param response
	 */
	@RequestMapping("/getAllExperimentGroupInfo")
	public void getAllExperimentGroupInfo(String groupName, int start, int length, int draw,
            								@RequestParam(defaultValue = "-1") int state, HttpServletResponse response) {
		/* 设置查询条件 */
		Map<String, Object> condition = new HashMap<>();
		if(groupName != null && !groupName.isEmpty()){
			condition.put("groupName", groupName.trim());
		}

		// 取得教师Id
		String teacherId = ShiroUtil.getCurrentUser().getUserId();

		int userRoleType = ShiroUtil.getCurrentUser().getRoleType();

		// 当前用户为教师或者助教，添加教师ID查询条件
		if(userRoleType == User.TEARCH_USER || userRoleType == User.ASSISTANT_USER){
			condition.put("teacherId", teacherId);
		}
				
		/* 分页查询课程 */
		PageInfo pageInfo = new PageInfo(start, length, draw);
		pageInfo.setCondition(condition);
		// 取得所有实验组
		expGroupService.getExperimentGroupPageInfo(pageInfo);
		/* 数据返回 */
		try {
			JsonTool.toJson(pageInfo, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 取得所有学生信息-配置实验组-学生查询用
	 * @param findValue
	 * @param response
	 */
	@RequestMapping("/getStudentInfo")
	public void getStudentInfo(String findValue, String collegeId, int start, int length, int draw, HttpServletResponse response) {
		/* 设置查询条件 */
		Map<String, Object> condition = new HashMap<>();
		if(findValue != null && !findValue.isEmpty()){
			condition.put("findValue", findValue.trim());
		}

		if(collegeId != null && !collegeId.isEmpty() && !collegeId.equals("0")){
			condition.put("collegeId", collegeId.trim());
		}
		
		/* 分页查询课程 */
		PageInfo pageInfo = new PageInfo(start, length, draw);
		pageInfo.setCondition(condition);
		expGroupService.getStudentInfo(pageInfo);
		/* 数据返回 */
		try {
			JsonTool.toJson(pageInfo, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/addCourse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Result addCourse(String requestType, HttpServletRequest request) {

		List<Experiment> experimentList = null;

		// 获取json数据
		String courseJsonStr = request.getParameter("course");
		String groupJsonStr = request.getParameter("groupIdList");
		String experimentJsonStr = request.getParameter("experimentList");
		String coursePicInfoStr = request.getParameter("coursePicInfo");

		// 课程数据设置成功判断
		Course courseTmp = courseService.parseJsonToCourse(courseJsonStr);
		if(courseTmp == null){
			return ResultUtil.getResult(false, CourseMessage.ADD_COURSE_ERR);
		}
		
		// 判断课程名是否已经存在
		Boolean eFlag = courseService.checkCourseExist(courseTmp.getCourseName());
		if(eFlag){
			return ResultUtil.getResult(false, CourseMessage.COURSE_NAME_EXIST);
		}
		
		
		//判断自启动时时间是否是小于当前时间
		/*if(courseTmp.getCourseStartUpType() == Course.COURSE_AUTO_START){
			String nowTime = TimeUtils.currentTime(); //取得当前时间
			String startUpTime = courseTmp.getCourseStratTime(); //取得自启动时间
			
			try {
				Date dtNowTime = TimeUtils.getDateString(nowTime);
				Date dtStartUpTime = TimeUtils.getDateString(startUpTime);
				long nowTimeMs = dtNowTime.getTime(); // 取得当前时间的毫秒值
				long startUpTimeMs = dtStartUpTime.getTime();  // 取得课程自启动时间的毫秒值
				
				// 课程启动时间小于当前时间 返回错误。
				if(startUpTimeMs < nowTimeMs){
					return ResultUtil.getResult(false, CourseMessage.COURSE_STARTUP_TIME_ERR);
				}
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			
		}*/

		// 课程图片设置成功判断
		if(coursePicInfoStr == null){
			return ResultUtil.getResult(false, CourseMessage.ADD_COURSE_ERR);
		}
		FileInfo coursePicInfo = JSONObject.parseObject(coursePicInfoStr, FileInfo.class);

		// 实验组数据设置成功判断
		List<ExperimentGroup> groupListTmp = expGroupService.parseJsonToExperimentGroup(groupJsonStr);
		if(groupListTmp == null && courseTmp.getOpenRange() == Course.COURSE_RANGE_STUDENT){
			return ResultUtil.getResult(false, CourseMessage.ADD_COURSE_EXPERIMENT_GROUP_NG);
		}

		// 实验信息设置
		if(experimentJsonStr != null && experimentJsonStr.isEmpty()){
			return ResultUtil.getResult(false, CourseMessage.ADD_COURSE_ERR_NOT_EXPERIMENT);
		}
		if(experimentJsonStr != null){
			try{
				experimentList = expService.parseJsonToExperiment(experimentJsonStr);
			}catch(MyException e){
				int experimentNo = e.getExperimentNo();
				
				return ResultUtil.getResult(false, "实验"+experimentNo+"的时间格式错误," + e.getErrorCode());
			}
			
		}

		// 添加或者保存课程
		if(requestType.equals("add") || requestType.equals("save")){

			// 取得添加完课程的ID用于插入实验信息
			String courseId = courseTmp.getCourseId();

			//保存课程
			if(requestType.equals("save")){
				//设置课程状态为未提交
				courseTmp.setStatus(Course.COURSE_STATUS_NOT_SUBMITED);
			}

			// 保存课程信息到数据库
			courseTmp.setCoursePicFileId(coursePicInfo.getFileId());
			int ret = courseService.addCourseInfo(courseTmp);
			if(ret <= 0){
				return ResultUtil.getResult(false, CourseMessage.ADD_COURSE_ERR);
			}

			// 保存课程图片信息到数据库
			coursePicInfo.setCorrelationId(courseId);
			fileService.insert(coursePicInfo);

			if(courseTmp.getOpenRange() == Course.COURSE_RANGE_STUDENT){
				// 保存课程实验组信息
				ret = expGroupService.addCourseExperimentGroupMapping(courseTmp.getCourseId(), groupListTmp);
				if(ret <= 0){
					return ResultUtil.getResult(false, CourseMessage.ADD_COURSE_ERR);
				}
			}



			// 获取当前时间，设置实验的创建时间为当前时间
			String date = TimeUtils.currentTime();
			if(experimentList != null && experimentList.size() > 0){
				int experimentNo = 1;
				List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
				for(Experiment experiment : experimentList){
					String experimentId = UUIDUtils.getUUID();
					experiment.setExperimentId(experimentId);
					experiment.setExperimentNo(experimentNo);
					experiment.setCourseId(courseId);
					experiment.setCreateTime(date);
					experiment.setExeperimentType(1);
					experimentNo++;
					if(experiment.getGuideInfo() != null){

						FileInfo experimentInfo = experiment.getGuideInfo();
						//System.out.println(experimentInfo.getFileName());
						experimentInfo.setCorrelationId(experimentId);
						fileInfoList.add(experimentInfo);

					}

				}

				// 添加实验信息到实验表
				ret = expService.addMultiExperiment(experimentList);
				System.out.println(ret);
				if(ret <= 0){
					return ResultUtil.getResult(false, CourseMessage.ADD_COURSE_EXPERIMENT_NG);
				}

				// 添加实验指南文件描述到文件管理表
				fileService.insertFileInfo(fileInfoList);
			}


		}else{
			return ResultUtil.getResult(false, CourseMessage.ADD_COURSE_ERR);
		}
		
		// 自动发布且是提交审核了，添加定时器
		if(courseTmp.getCourseStartUpType() == 1 && requestType.equals("add")){
			TimerTask task=new TimerTask() {
				@Override
				public void run() {
					courseService.autoLaunchCourse(courseTmp.getCourseId());
				}
			};
			
			String startTime = courseTmp.getCourseStratTime();
			
			Timer timer = new Timer();
			timer.schedule(task, TimeUtils.getDateString(startTime));
			
		}
		
		return ResultUtil.getResult(true, CourseMessage.ADD_COURSE_SUCCES);
	}

	@RequestMapping(value="/modifyCourseInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Result modifyCourseInfo(String requestType, String[] deleteExperimentArray, HttpServletRequest request) {
		List<Experiment> experimentList = null;

		// 获取json数据
		String courseJsonStr = request.getParameter("course");
		String groupJsonStr = request.getParameter("groupIdList");
		String experimentJsonStr = request.getParameter("experimentList");
		String coursePicInfoStr = request.getParameter("coursePicInfo");

		// 课程数据设置成功判断
		Course courseTmp = courseService.parseJsonToCourse(courseJsonStr);
		if(courseTmp == null){
			return ResultUtil.getResult(false, CourseMessage.MODIFY_COURSE_ERR);
		}

		// 取得修改前的课程信息
		Course preCourse = courseService.findCourseByCourseId(courseTmp.getCourseId());
		// 修改前后的课程名不一致，判断课名是否已经存在
		if(!preCourse.getCourseName().equals(courseTmp.getCourseName())){
			// 判断课程名是否已经存在
			Boolean eFlag = courseService.checkCourseExist(courseTmp.getCourseName());
			if(eFlag){
				return ResultUtil.getResult(false, CourseMessage.COURSE_NAME_EXIST);
			}
		}
		
		
		
		//判断自启动时时间是否是小于当前时间
		/*if(courseTmp.getCourseStartUpType() == Course.COURSE_AUTO_START){
			String nowTime = TimeUtils.currentTime(); //取得当前时间
			String startUpTime = courseTmp.getCourseStratTime(); //取得自启动时间
			
			try {
				Date dtNowTime = TimeUtils.getDateString(nowTime);
				Date dtStartUpTime = TimeUtils.getDateString(startUpTime);
				long nowTimeMs = dtNowTime.getTime(); // 取得当前时间的毫秒值
				long startUpTimeMs = dtStartUpTime.getTime();  // 取得课程自启动时间的毫秒值
				
				// 课程启动时间小于当前时间 返回错误。
				if(startUpTimeMs < nowTimeMs){
					return ResultUtil.getResult(false, CourseMessage.COURSE_STARTUP_TIME_ERR);
				}
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			
		}*/
		
		// 课程图片设置成功判断
		FileInfo coursePicInfo = JSONObject.parseObject(coursePicInfoStr, FileInfo.class);

		// 实验组数据设置成功判断
		List<ExperimentGroup> groupListTmp = expGroupService.parseJsonToExperimentGroup(groupJsonStr);
		if(groupListTmp == null && courseTmp.getOpenRange() == Course.COURSE_RANGE_STUDENT){
			return ResultUtil.getResult(false, CourseMessage.ADD_COURSE_EXPERIMENT_GROUP_NG);
		}

		// 实验信息设置
		if(experimentJsonStr != null && experimentJsonStr.isEmpty()){
			return ResultUtil.getResult(false, CourseMessage.ADD_COURSE_ERR_NOT_EXPERIMENT);
		}
		if(experimentJsonStr != null){
			try{
				experimentList = expService.parseJsonToExperiment(experimentJsonStr);
			}catch(MyException e){
				int experimentNo = e.getExperimentNo();
				
				return ResultUtil.getResult(false, "实验"+experimentNo+"的时间格式错误," + e.getErrorCode());
			}
			
		}

		// 修改或者保存课程
		if(requestType.equals("modify") || requestType.equals("save")){

			// 取得添加完课程的ID用于插入实验信息
			String courseId = courseTmp.getCourseId();

			//保存课程
			if(requestType.equals("save")){
				//设置课程状态为未提交
				courseTmp.setStatus(Course.COURSE_STATUS_NOT_SUBMITED);
			}

			// 更新课程信息到数据库
			int ret = courseService.updateCourseInfo(courseTmp);
			if(ret <= 0){
				return ResultUtil.getResult(false, CourseMessage.MODIFY_COURSE_ERR);
			}

			// 保存课程图片信息到数据库
			if(coursePicInfo != null){
				coursePicInfo.setCorrelationId(courseId);
				// 修改课程图片
				fileService.updateFileInfo(coursePicInfo);
			}

			if(courseTmp.getOpenRange() == Course.COURSE_RANGE_STUDENT){
				// 保存课程实验组map信息,删除-重新添加
				expGroupService.deleteCourseExperimentGroupMapping(courseId);
				expGroupService.addCourseExperimentGroupMapping(courseId, groupListTmp);

			}

			// 删除实验判断
			if(deleteExperimentArray != null && deleteExperimentArray.length > 0){
				ret = fileService.deleteMultiByCorrelationId(deleteExperimentArray);
				if(ret <= 0){
					return ResultUtil.getResult(false, CourseMessage.SAVE_COURSE_ERR);
				}

				ret = expService.deleteMultiExperimentByExperimentId(deleteExperimentArray);
				if(ret <= 0){
					return ResultUtil.getResult(false, CourseMessage.SAVE_COURSE_ERR);
				}
			}

			// 获取当前时间，设置实验的创建时间为当前时间
			String date = TimeUtils.currentTime();
			if(experimentList != null && experimentList.size() > 0){
				int experimentNo = 1;
				List<Experiment> addExperimentList = new ArrayList<Experiment>();
				List<Experiment> updateExperimentList = new ArrayList<Experiment>();
				List<FileInfo> addFileInfoList = new ArrayList<FileInfo>();
				List<FileInfo> updateFileInfoList = new ArrayList<FileInfo>();
				for(Experiment experiment : experimentList){

					String beforExperimentId = experiment.getExperimentId();
					if(beforExperimentId != null && !beforExperimentId.isEmpty()){
						// 更新操作
						experiment.setCourseId(courseId);
						experiment.setExperimentNo(experimentNo);
						experiment.setCreateTime(date);
						experiment.setExeperimentType(1);

						if(experiment.getGuideInfo() != null){
							// 更新实验指南
							FileInfo uExperimentInfo = experiment.getGuideInfo();
							uExperimentInfo.setCorrelationId(beforExperimentId);
							// 添加需要更新的文件
							updateFileInfoList.add(uExperimentInfo);
						}
						// 添加需要更新的实验
						updateExperimentList.add(experiment);
						//expService.updateExperiment(experiment);
					}else{
						String experimentId = UUIDUtils.getUUID();
						experiment.setExperimentId(experimentId);
						experiment.setExperimentNo(experimentNo);
						experiment.setCourseId(courseId);
						experiment.setCreateTime(date);
						experiment.setExeperimentType(1);
						if(experiment.getGuideInfo() != null){
							FileInfo experimentInfo = experiment.getGuideInfo();
							experimentInfo.setCorrelationId(experimentId);
							addFileInfoList.add(experimentInfo);

						}
						addExperimentList.add(experiment);
					}
					experimentNo++;
				}

				// 更新实验列表
				if(updateExperimentList.size() > 0){
					ret = expService.updateMultiExperiment(updateExperimentList);
					if(ret <= 0){
						return ResultUtil.getResult(false, CourseMessage.ADD_COURSE_EXPERIMENT_NG);
					}
				}

				// 添加实验信息到实验表
				if(addExperimentList.size() > 0){
					ret = expService.addMultiExperiment(addExperimentList);
					if(ret <= 0){
						return ResultUtil.getResult(false, CourseMessage.ADD_COURSE_EXPERIMENT_NG);
					}
				}


				// 更新实验指南文件描述到文件管理表
				if(updateFileInfoList.size() > 0){
					fileService.updateFileInfo(updateFileInfoList);
				}

				// 添加实验指南文件描述到文件管理表
				if(addFileInfoList.size() > 0){
					fileService.insertFileInfo(addFileInfoList);
				}

			}

		}

		// 修改后是自动发布且提交审核了，添加定时器
		if(courseTmp.getCourseStartUpType() == 1 && requestType.equals("modify")){
			TimerTask task=new TimerTask() {
				@Override
				public void run() {
					courseService.autoLaunchCourse(courseTmp.getCourseId());
				}
			};
			
			String startTime = courseTmp.getCourseStratTime();
			
			Timer timer = new Timer();
			timer.schedule(task, TimeUtils.getDateString(startTime));
		}
		
		return ResultUtil.getResult(true, CourseMessage.ADD_COURSE_SUCCES);
	}

	/**
	 * 修改课程
	 * @return
	 */
	@RequestMapping(value = "/modifycourse",  method = RequestMethod.POST , produces = "text/html; charset=UTF-8")
	public String coursemgtModifyCourse(String courseId, Model m) {
		System.out.println(courseId);
		Course course = courseService.findCourseByCourseId(courseId);
		m.addAttribute("courseUid", courseId);
		m.addAttribute("requestType", "modify");


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
		//获取秘钥
		List<FileInfo> keyList = fileService.getKeyByFileInfoType();
		//获取实验模板
		List<Template> templateList = templateService.getTemplateList();

		//获取实验模板(个人模板)
		List<Template> persionTemplateList = tmplMakingService.getTemplateList();
		
		List<Mechanism> specialtyList = getProfessional(course.getCollegeId());
		
		// 取得策略list
		Map<String, Object> condition=new HashMap<String, Object>();
		condition.put("status", 1);
		List<Policy> policyList = policyService.listBycondition(condition);
		m.addAttribute("policyList", policyList);
		
		m.addAttribute("course", course);
		m.addAttribute("courseTypeList",courseTypeList);
		m.addAttribute("courseLevelList",courseLevelList);
		//m.addAttribute("teacherInfo", teacherInfo);
		m.addAttribute("collegeList", collegeList);
		m.addAttribute("specialtyList",specialtyList);
		m.addAttribute("templateList",templateList);
		m.addAttribute("persionTemplateList",persionTemplateList);
		m.addAttribute("keyList",keyList);
		
		return "/courseManage/modifyCourse";
	}

	@RequestMapping("/getExperimentUuid")
	@ResponseBody
	public Result getExperimentUuid(@RequestParam(value="file") MultipartFile guideName) {
		FileInfo fileinfo = null;
		try {
			fileinfo = uploadsv.uploadPic(guideName.getBytes(), guideName.getOriginalFilename(), guideName.getSize());
			

		} catch (IOException /*Exception*/ e) {
			e.printStackTrace();
			return ResultUtil.getResult(false, CourseMessage.ADD_COURSE_EXPERIMENT_NG);
		}

		return ResultUtil.getResult(true, CourseMessage.ADD_COURSE_EXPERIMENT_OK, fileinfo);
	}

	@RequestMapping("/modifyExperimentGuide")
	@ResponseBody
	public Result modifyExperimentGuide(@RequestParam(value="file") MultipartFile guideName) {
		FileInfo fileinfo = null;
		try {
			fileinfo = uploadsv.uploadPic(guideName.getBytes(), guideName.getOriginalFilename(), guideName.getSize());
			

		} catch (IOException /*Exception*/ e) {
			e.printStackTrace();
			return ResultUtil.getResult(false, CourseMessage.ADD_COURSE_EXPERIMENT_NG);
		}

		return ResultUtil.getResult(true, CourseMessage.ADD_COURSE_EXPERIMENT_OK, fileinfo);
	}

	@RequestMapping("/uploadCoursePic")
	@ResponseBody
	public Result uploadCoursePic(@RequestParam(value="file") MultipartFile coursePic, HttpServletResponse response) {
		FileInfo fileinfo = null;

		try {
			fileinfo = uploadsv.uploadPic(coursePic.getBytes(), coursePic.getOriginalFilename(), coursePic.getSize());

			
		} catch (IOException /*Exception*/ e) {
			e.printStackTrace();
			return ResultUtil.getResult(false, CourseMessage.COURSEPIC_UPLOAD_NG);
		}

		return ResultUtil.getResult(true, CourseMessage.COURSEPIC_UPLOAD_OK, fileinfo);
	}

	@RequestMapping("/uploadCourseTypePic")
	@ResponseBody
	public Result uploadCourseTypePic(@RequestParam(value="file") MultipartFile courseTypePic, HttpServletResponse response) {
		FileInfo fileinfo = null;

		try {
			fileinfo = uploadsv.uploadPic(courseTypePic.getBytes(), courseTypePic.getOriginalFilename(), courseTypePic.getSize());

			
		} catch (IOException /*Exception*/ e) {
			e.printStackTrace();
			return ResultUtil.getResult(false, CourseMessage.COURSEPIC_UPLOAD_NG);
		}

		return ResultUtil.getResult(true, CourseMessage.COURSEPIC_UPLOAD_OK, fileinfo);
	}
	
	@RequestMapping("/modifyCoursePic")
	@ResponseBody
	public Result modifyCoursePic(@RequestParam(value="file")MultipartFile coursePic, HttpServletResponse response) {
		FileInfo fileinfo = null;
		try {
			fileinfo = uploadsv.uploadPic(coursePic.getBytes(), coursePic.getOriginalFilename(), coursePic.getSize());

			
		} catch (IOException /*Exception*/ e) {
			e.printStackTrace();
			return ResultUtil.getResult(false, CourseMessage.COURSEPIC_UPLOAD_NG);
		}

		return ResultUtil.getResult(true, CourseMessage.COURSEPIC_UPLOAD_OK, fileinfo);
	}

	@RequestMapping("/getCourseExperiment")
	public void getCourseExperiment(String courseId, int start, int length, int draw,
            @RequestParam(defaultValue = "-1") int state, HttpServletResponse response) throws IOException {
		/*设置查询条件*/
		Map<String,Object> condition  = new HashMap<String, Object>();
		condition.put("courseId", courseId.trim());//课程Id
		condition.put("state", Common.STATE_ACTIVE);//状态

		/*分页查询角色*/
		PageInfo pageInfo = new PageInfo(start, length, draw);
		pageInfo.setCondition(condition);
		expService.getExperimentPageInfo(pageInfo);


		JsonTool.toJson(pageInfo, response);
	}

	private static final Logger log = LoggerFactory.getLogger(CourseController.class);


	/**
	 *在用户选取了所属院系后，返回所属院系下所有的专业名
	 */
	@RequestMapping("/getProfessional")
	@ResponseBody
	public List<Mechanism> getProfessional(String mechanismId){
		List<Mechanism> mechanisms = new ArrayList<Mechanism>();
		mechanisms = mechanismService.getProfessionalByMechanismId(mechanismId);
		return mechanisms;
	}

	
	
	@RequestMapping("/getTemplateAllInfo")
	@ResponseBody
	public Result getTemplateAllInfo(@RequestParam(defaultValue = "0") int templateOrigin) {
		
		List<Template> templateList = null;
		if(templateOrigin == 0){
			//获取实验模板(模板库)
			templateList = templateService.getTemplateList();
		}else{
			//获取实验模板(个人模板)
			templateList = tmplMakingService.getTemplateList();
		}
		
		
		return ResultUtil.getResult(true, CourseMessage.GET_ALL_TEMPLATE_SUCCESS, templateList);
	}		
			
	/**
	 * 模板描述
	 */
	@RequestMapping("/getTemplateInfo")
	@ResponseBody
	public Template getTemplateInfo(String tmplId, int templateOrigin){
		
		if(templateOrigin == 0){
			return templateService.getTemplateById(tmplId) ;
		}
		
		return tmplMakingService.getTemplateMakeInfo(tmplId) ;
	}

	/**
	 * 发布课程
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value = "/launchCourse")
	@ResponseBody
	public Result launchCourse(String courseId){
		Course course = courseService.findCourseByCourseId(courseId);
		
		// 设置课程状态为已发布
		course.setStatus(Course.COURSE_STATUS_REGISTERED);
		
		
		/* 取得课程所有学生 */
		List<Student> students = courseService.selectCourseStudents(course);
		
		/* 发送短信通知学生 */
		Boolean ret = courseService.sendMessageToStudent(course, students);
		
		if(ret == false){
			return ResultUtil.getResult(false, CourseMessage.COURSE_LAUNCH_ERR);
		}
		
		// 更新课程状态信息
		courseService.updateCourseInfo(course);
		return ResultUtil.getResult(true, CourseMessage.COURSE_LAUNCH_SUCCESS);
	}
	
	/**
	 * 克隆
	 */
	@RequestMapping(value = "/courseClone")
	@ResponseBody
	public Result courseClone(String courseId, String courseName) throws IOException {
		Course course = courseService.findCourseByCourseId(courseId);
		
		// 判断课程名是否已经存在
		Boolean eFlag = courseService.checkCourseExist(courseName);
		if(eFlag){
			return ResultUtil.getResult(false, CourseMessage.COURSE_NAME_EXIST);
		}
				
		//查找图片文件信息
		FileInfo coursePicFile = fileService.getFileInfoById(course.getCoursePicFileId());
		//查找tb_course_experimentgroup 中的groupId
		List<String> groupId = expGroupService.getGroupIdByCourse(courseId);

		//查询实验
		List<Experiment> experiments = expService.getExperimentByCourseId(courseId);

		String courseTmpId = UUIDUtils.getUUID();

		//查询实验文件
		List<FileInfo> guideFiles = new ArrayList<FileInfo>();
		for (Experiment experiment : experiments){
			FileInfo fileInfo = fileService.getFileInfoById(experiment.getGuideFileId());

			experiment.setCourseId(courseTmpId);
			String experimentUid = UUIDUtils.getUUID();
			experiment.setExperimentId(experimentUid);
			fileInfo.setCorrelationId(experimentUid);
			fileInfo.setFileId(UUIDUtils.getUUID());
			experiment.setGuideInfo(fileInfo);

			guideFiles.add(fileInfo);
		}


		//新建course
		course.setCourseId(courseTmpId);

		//新建一份图片信息
		//上传一份新的图片
		FileInfo newPicFileInfo = coursePicFile;
		newPicFileInfo.setFileId(UUIDUtils.getUUID());
		newPicFileInfo.setCorrelationId(courseTmpId);
		if (!fileService.insertFileInfo(newPicFileInfo)){//如果文件信息保存数据库失败
			return ResultUtil.getResult(false, CourseMessage.COURSE_PIC_FILE_CLONE_ERR);
		}

		course.setCoursePicFileId(newPicFileInfo.getFileId());
		course.setStatus(Course.COURSE_STATUS_NOT_SUBMITED);
		course.setCourseName(courseName);
		course.setUseCount(0);
		courseService.addCourseInfo(course);

		//创建tb_course_experimentgroup关系
		List<ExperimentGroup> experimentGroupList = new ArrayList<ExperimentGroup>();
		for (String g: groupId) {
			//填充experimentGroupList，给下面的addCourseExperimentGroupMapping用
			ExperimentGroup experimentGroup = expGroupService.getExperimentGroupByGroupId(g);
			experimentGroupList.add(experimentGroup);
		}
		if (experimentGroupList.size()>0){//创建关系表
			expGroupService.addCourseExperimentGroupMapping(courseTmpId, experimentGroupList);
		}


		if (!fileService.insertFileInfo(guideFiles)){//如果文件信息保存数据库失败
			return ResultUtil.getResult(false, CourseMessage.GUIDE_FILED_CLONE_ERR);
		}
		expService.addMultiExperiment(experiments);


		return ResultUtil.getResult(true, CourseMessage.COURSEPIC_UPLOAD_OK);
	}

	@RequestMapping(value = "/courseDelete")
	@ResponseBody
	public Result courseDelete(String courseId){
		Course course = courseService.findCourseByCourseId(courseId);
		/*FileInfo picFileInfo = fileService.getFileInfoById(course.getCoursePicFileId());
		List<Experiment> experiments = expService.getExperimentByCourseId(courseId);
		List<FileInfo> guideFiles = new ArrayList<FileInfo>();
		for (Experiment experiment : experiments){
			guideFiles.add(fileService.getFileInfoById(experiment.getGuideFileId()));
			//删除实验
			expService.deleteExperimentByExperimentId(experiment.getExperimentId());
		}

		//删除课程
		courseService.deleteCourseById(courseId);
		//删除图片文件
		fileService.deleteMultiByFileId(picFileInfo.getFileId());
		//删除指南
		for (FileInfo f : guideFiles) {
			fileService.deleteMultiByFileId(f.getFileId());
		}
		//删除tb_course_experimentgroup数据
		expGroupService.deleteCourseExperimentGroupMapping(courseId);*/

		// 课程状态
		int status = course.getStatus();
		
		// 课程已发布
		if(status == Course.COURSE_STATUS_REGISTERED){
			// 取得user role
			int userRoleType = ShiroUtil.getCurrentUser().getRoleType();

			// 当前用户为教师或者助教，添加教师ID查询条件
			if(userRoleType != User.PLATFORM_MANAGE_USER){
				return ResultUtil.getResult(false, CourseMessage.COURSE_DELETE_ERR_FOR_PLATFORM_MANAGE_USER);
			}
		// 课程已失效
		}else if(status == Course.COURSE_STATUS_DELETED){
			return ResultUtil.getResult(true, CourseMessage.COURSE_DELETE_SUCCESS);
		}

		
		courseService.deleteCourseById(courseId);
		return ResultUtil.getResult(true, CourseMessage.COURSE_DELETE_SUCCESS);
	}


	@RequestMapping(value = "/getCourseDetailInfo",  method = RequestMethod.POST , produces = "text/html; charset=UTF-8")
	public String getCourseDetailInfo(String courseId, Model model){
		Course course = courseService.findCourseByCourseId(courseId);

		int courseNumber = courseService.countCourseNumber(course);
		model.addAttribute("courseNumber" , courseNumber);//课程人数
		model.addAttribute("college", mechanismService.getMechanismById(course.getCollegeId()));//获取院系
		model.addAttribute("specialty", mechanismService.getMechanismById(course.getSpecialtyId()));//获取院系
		model.addAttribute("course" , course);//课程
		model.addAttribute("coursePicFile", fileService.getFileInfoById(course.getCoursePicFileId()).getFileUrl());//获取课程图标
		
		List<CourseCheckHistory> courseCheckHistory = courseService.getCourseCheckHistory(courseId);
		model.addAttribute("courseCheckHistory", courseCheckHistory);//获取审核结果
		model.addAttribute("courseTypeName",dictionaryDataService.getDicDataNameByItemId(course.getCourseType()));//获取课程类型
		model.addAttribute("courseTypeLevelName",dictionaryDataService.getDicDataNameByItemId(course.getCourseTypeLevel()));//获取课程等级
		model.addAttribute("Teacher", courseService.getTeacherById(course.getTeacherId()));//获取老师ID
		List<Experiment> experimentList = expService.getExperimentByCourseId(courseId);
		model.addAttribute("Experiment", experimentList);//获取实验列表
		//model.addAttribute("courseComments",courseComments);//获取评论主体
		//model.addAttribute("user",students);//获取评论学生
		return "/courseManage/courseDetailInfo";
	}

	@RequestMapping(value = "/showExperimentGroupStudent")
	@ResponseBody
	public Result showExperimentGroupStudent(String courseId){
		// 取得课程信息
		Course course = courseService.findCourseByCourseId(courseId);

		// 取得课程共享范围
		int cOpenRange = course.getOpenRange();
		
		
		if(cOpenRange == Course.COURSE_RANGE_STUDENT){
			//通过courseId查询groupId
			List<String> groupIds = courseService.getGroupIdByCourseId(courseId);
			List<ExperimentGroup> experimentGroupList = new ArrayList<ExperimentGroup>();
			
			for (String g:groupIds) {
				
				ExperimentGroup experimentGroup = expGroupService.getExperimentGroupByGroupIdNoZtree(g);
				
				experimentGroupList.add(experimentGroup);
			}
			
			return ResultUtil.getResult(true, CourseMessage.GET_EXPERIMENT_GROUP_SUCCESS, experimentGroupList);
		}
		
		
		return ResultUtil.getResult(true, CourseMessage.GET_EXPERIMENT_GROUP_SUCCESS);
	}
	
	
	@RequestMapping(value = "/getCourseReviewDetailInfo",  method = RequestMethod.POST , produces = "text/html; charset=UTF-8")
	public String showCourseReviewDetailInfo(String courseId, Model model){
		Course course = courseService.findCourseByCourseId(courseId);

		int courseNumber = courseService.countCourseNumber(course);
		model.addAttribute("courseNumber" , courseNumber);//课程人数
		
		model.addAttribute("college", mechanismService.getMechanismById(course.getCollegeId()));//获取院系
		model.addAttribute("specialty", mechanismService.getMechanismById(course.getSpecialtyId()));//获取院系
		model.addAttribute("course" , course);//课程
		model.addAttribute("coursePicFile", fileService.getFileInfoById(course.getCoursePicFileId()).getFileUrl());//获取课程图标
		model.addAttribute("courseTypeName",dictionaryDataService.getDicDataNameByItemId(course.getCourseType()));//获取课程类型
		model.addAttribute("courseTypeLevelName",dictionaryDataService.getDicDataNameByItemId(course.getCourseTypeLevel()));//获取课程等级
		model.addAttribute("Teacher", courseService.getTeacherById(course.getTeacherId()));//获取老师ID
		model.addAttribute("Experiment", expService.getExperimentByCourseId(courseId));//获取实验列表

		return "/resourceReview/courseReviewDetailInfo";
	}

	@RequestMapping(value = "/showCourseDetailInfo",  method = RequestMethod.POST , produces = "text/html; charset=UTF-8")
	public String showCourseDetailInfo(String courseId, Model model){
		Course course = courseService.findCourseByCourseId(courseId);

		int courseNumber = courseService.countCourseNumber(course);
		model.addAttribute("courseNumber" , courseNumber);//课程人数
		
		model.addAttribute("college", mechanismService.getMechanismById(course.getCollegeId()));//获取院系
		model.addAttribute("specialty", mechanismService.getMechanismById(course.getSpecialtyId()));//获取院系
		model.addAttribute("course" , course);//课程
		model.addAttribute("coursePicFile", fileService.getFileInfoById(course.getCoursePicFileId()).getFileUrl());//获取课程图标
		model.addAttribute("courseCheckHistory", courseService.getCourseCheckHistory(courseId));//获取审核结果
		model.addAttribute("courseTypeName",dictionaryDataService.getDicDataNameByItemId(course.getCourseType()));//获取课程类型
		model.addAttribute("courseTypeLevelName",dictionaryDataService.getDicDataNameByItemId(course.getCourseTypeLevel()));//获取课程等级
		model.addAttribute("Teacher", courseService.getTeacherById(course.getTeacherId()));//获取老师ID
		model.addAttribute("Experiment", expService.getExperimentByCourseId(courseId));//获取实验列表
		
		return "/resourceReview/showCourseDetailInfo";
	}
	
	
	@RequestMapping(value = "/getCourseCommentInfo",  method = RequestMethod.POST)
	@ResponseBody
	public Result getCourseCommentInfo(int page, int pageSize, String courseId ){
		/* 设置查询条件 */
		Map<String, Object> condition = new HashMap<>();


		condition.put("courseId", courseId);


		/* 分页查询课程 */
		PageInfo pageInfo = new PageInfo(page, pageSize);
		pageInfo.setCondition(condition);


		courseService.getCourseCommentInfoTotal(pageInfo);


		return ResultUtil.getResult(true, CourseMessage.COURSE_DELETE_SUCCESS, pageInfo);
	}

	@RequestMapping(value = "/getCourseReport", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public String getCourseReport(String courseId, int openRange, Model model){
		
		Course course = courseService.findCourseByCourseId(courseId);
		int cOpenRange = course.getOpenRange();
		
		// 写入共享范围
		model.addAttribute("openRange", cOpenRange);
		
		if(cOpenRange == Course.COURSE_RANGE_STUDENT){
			//通过courseId查询groupId
			List<String> groupIds = courseService.getGroupIdByCourseId(courseId);
			List<ExperimentGroup> experimentGroupList = new ArrayList<ExperimentGroup>();
			
			for (String g:groupIds) {
				/*List<String> userIds = userService.getUserIdbyGroupId(g);
				List<Student> students = new ArrayList<Student>();
				for (String userId : userIds){
					Student student = userService.getStudentById(userId);
					students.add(student);
				}*/
				
				ExperimentGroup experimentGroup = expGroupService.getExperimentGroupByGroupIdNoZtree(g);
				//experimentGroup.setChildren(students);
				experimentGroupList.add(experimentGroup);
			}
			//将若干组集合添加至model，如group 1 ：组ID， 组名，List<Student>（该组下所有的学生）
			model.addAttribute("experimentGroupList",experimentGroupList);
			
			//默认加载一个学生的实验报告信息
			if (experimentGroupList.size()>0){//如果实验组List不为空
				Student student = experimentGroupList.get(0).getChildren().get(0);
				if (null != student){
					model.addAttribute("firstStudent", student);
					model.addAttribute("courseReport", getStudentCourseReport(courseId, student.getId()));
				}else {
					//如果experimentGroupList中的元素在学生表里没有
					model.addAttribute("courseReport", null);
				}
				
			}else {
				//如果experimentGroupList为空
				model.addAttribute("courseReportList", null);
			}
		}else if(cOpenRange == Course.COURSE_RANGE_DEPARTMENT){
			List<Student> students = courseService.selectCourseStudents(course);
			
			if(students.size() > 0){
				// 取得第一个学生信息
				Student firstStudent = students.get(0);
				
				// 写入院系信息
				model.addAttribute("collegeId", firstStudent.getMechanismId());
				model.addAttribute("collegeName", firstStudent.getMechanismName());
				
				// 写入第一个学生信息
				model.addAttribute("firstStudent", firstStudent);
				
				// 写入第一个学生的实验报告
				model.addAttribute("courseReport", getStudentCourseReport(courseId, firstStudent.getId()));
			}
			
			
			model.addAttribute("students", students);
		}else if(cOpenRange == Course.COURSE_RANGE_SCHOOL){
			List<Student> students = courseService.selectCourseStudents(course);
			
			if(students.size() > 0){
				// 取得第一个学生信息
				Student firstStudent = students.get(0);
				
				// 写入学校信息
				model.addAttribute("schoolId", students.get(0).getSchoolId());
				model.addAttribute("schoolName", students.get(0).getSchoolName());
				
				// 写入第一个学生信息
				model.addAttribute("firstStudent", firstStudent);
				
				// 写入第一个学生的实验报告
				model.addAttribute("courseReport", getStudentCourseReport(courseId, firstStudent.getId()));
			}
			
			model.addAttribute("students", students);
		}
		
		model.addAttribute("courseId",courseId);
		return "/courseManage/courseExperimentReview";
	}

	@RequestMapping(value = "/getStudentCourseReport", method = RequestMethod.POST)
	@ResponseBody
	public List<Report> getStudentCourseReport(String courseId, String studentId){
		List<Report> courseReportList = expService.getCourseReportByStudentId(courseId, studentId);
		return courseReportList ;
	}

	@RequestMapping(value = "/courseAudit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Result courseAudit(String courseId, int flag, String content){
		
		Course course = courseService.findCourseByCourseId(courseId);
		
		CourseCheckHistory courseCheckHistory = new CourseCheckHistory();
		courseCheckHistory.setId(UUIDUtils.getUUID());
		courseCheckHistory.setUserId(ShiroUtil.getCurrentUser().getUserId());
		courseCheckHistory.setCourseId(courseId);
		courseCheckHistory.setTime(new Date());
		courseCheckHistory.setContent(content);

		if (flag==1){//课程审核通过
			courseCheckHistory.setCheckStatus(flag);
			courseService.insertCourseCheckHistory(courseCheckHistory);
			course.setStatus(Course.COURSE_STATUS_PASS);//改变课程状态-已通过
			courseService.updateCourseInfo(course);
			
			courseService.checkAutoLaunchCourse(course);
			return ResultUtil.getResult(true, CourseMessage.COURSE_THROUGH_AUDIT_SUCCESS);
		}else if (flag == 0){//课程审核不通过
			courseCheckHistory.setCheckStatus(flag);
			courseService.insertCourseCheckHistory(courseCheckHistory);
			course.setStatus(Course.COURSE_STATUS_NOT_PASS);
			courseService.updateCourseInfo(course);
			return ResultUtil.getResult(true, CourseMessage.COURSE_THROUGH_AUDIT_FAIL);
		}
		
		return ResultUtil.getResult(false, CourseMessage.COURSE_AUDIT_ERROR);
	}

	@RequestMapping(value = "addNewExperimentGroup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Result addNewExperimentGroup(String groupName, String[] studentArray, String groupId){

		if(groupId != null && !groupId.equals("")){
			//新建一个实验组
			ExperimentGroup experimentGroup = new ExperimentGroup();
			experimentGroup.setGroupId(groupId);
			experimentGroup.setGroupName(groupName);
			experimentGroup.setCreateAuthorId(ShiroUtil.getCurrentUserId());
			experimentGroup.setCreateDate(new Date());
			experimentGroup.setState(1);
			int ret = expGroupService.updateExperimentGroup(experimentGroup);
			if(ret < 0){
				return ResultUtil.getResult(false, CourseMessage.ADD_EXPERIMENT_GROUP_ERR);
			}
			
			boolean flag = expGroupService.updateExperimentGroupStudent(groupId, studentArray);
			if(flag == false){
				return ResultUtil.getResult(false, CourseMessage.MODIFY_EXPERIMENT_GROUP_ERR);
			}
			return ResultUtil.getResult(true, CourseMessage.MODIFY_EXPERIMENT_GROUP_SUCCESS);
		}
		
		
		//新建一个实验组
		ExperimentGroup experimentGroup = new ExperimentGroup();
		experimentGroup.setGroupId(UUIDUtils.getUUID());
		experimentGroup.setGroupName(groupName);
		experimentGroup.setCreateAuthorId(ShiroUtil.getCurrentUserId());
		experimentGroup.setCreateDate(new Date());
		experimentGroup.setState(1);
		expGroupService.addNewGroup(experimentGroup);
		
		int ret = expGroupService.addMultiStudentToExperimentGroup(experimentGroup.getGroupId(), studentArray);
		if(ret < 0){
			return ResultUtil.getResult(false, CourseMessage.ADD_EXPERIMENT_GROUP_ERR);
		}
		return ResultUtil.getResult(true, CourseMessage.ADD_EXPERIMENT_GROUP_SUCCESS);
	}

	
	@RequestMapping(value = "/appraiseReport",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	@ResponseBody
	public Result appraiseReport(String experimentId, String studentId, String comment, int score){
		Report report = expService.getCourseReportByStudentIdAndExperimentId(experimentId, studentId);
		report.setComment(comment);
		report.setScore(score);
		report.setRemarkTime(new Date());
		expService.uodateCourseReport(report);
		return ResultUtil.getResult(true, CourseMessage.APPRAISE_REPORT_SUCCESS);
	}
	
	@RequestMapping(value = "/createNewKeyPair",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	@ResponseBody
	public Result createNewKeyPair(String keyName){
		
		return courseService.createNewKeyPair(keyName);
		
	}
	
	
	@RequestMapping(value="/addCourseType", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Result addCourseType(String courseTypeName, HttpServletRequest request) {
		
		/*判断课程类型名是否为空*/
		if ("".endsWith(courseTypeName) || courseTypeName == null) {
			return ResultUtil.getResult(false, CourseMessage.ADD_COURSE_TYPE_ERR);
		}
		
		// 获取json数据
		String courseTypePicInfoStr = request.getParameter("courseTypePicInfo");

		if(courseTypePicInfoStr == null){
			return ResultUtil.getResult(false, CourseMessage.ADD_COURSE_TYPE_ERR);
		}
		
		// 查询课程类型是否已存在
		// 1.获取课程类型dicId
		String courseTypeDicId = dictionaryDataService.getDicIdByDicCode(Dictionary.DICTIONARY_COURSETYPE.getDicCode());
		
		// 2.判断课程类型是否存在
		Boolean existFlag = dictionaryDataService.checkCourseTypeExistByDicIdAndName(courseTypeDicId, courseTypeName);
		if(existFlag){
			// 已存在
			return ResultUtil.getResult(false, CourseMessage.COURSE_TYPE_EXIST);
		}
		
		// 3.不存在则添加到数据库
		// 3.1 取得课程类型数量
		int total = dictionaryDataService.countCourseTypeTotal(courseTypeDicId);
		
		// 3.2取得UUID
		String itemId = UUIDUtils.getUUID();
		
		// 3.3 设置数据
		DictionaryData dictionaryData = new DictionaryData();
		dictionaryData.setItemId(itemId);
		dictionaryData.setItemName(courseTypeName);
		dictionaryData.setItemValue(Integer.toString(total+1));  //当前课程类型总数+1
		dictionaryData.setDicId(courseTypeDicId);
		
		// 3.4 设置数据
		int ret = dictionaryDataService.addCourseTypeDateToSubItem(dictionaryData);
		if(ret <= 0){
			//添加失败
			return ResultUtil.getResult(false, CourseMessage.ADD_COURSE_TYPE_ERR);
		}
		
		// 4 课程类型图片设置成功判断
		FileInfo coursePicInfo = JSONObject.parseObject(courseTypePicInfoStr, FileInfo.class);
		if(coursePicInfo == null){
			return ResultUtil.getResult(false, CourseMessage.ADD_COURSE_TYPE_ERR);
		}
		
		// 4.1 设置课程类型图片的关联ID=itemId
		coursePicInfo.setCorrelationId(itemId);
		
		// 4.2 插入课程类型图片到数据库
		fileService.insertFileInfo(coursePicInfo);
		
		
		return ResultUtil.getResult(true, CourseMessage.ADD_COURSE_TYPE_SUCCESS, itemId);
	}
	
	/* 启动课程实验关联函数 */
	/**
	 * 资源启动
	 * @author zhenghuangkun
	 * @version 2018年4月15日
	 * @param experimentInfo
	 * @return
	 */
	@RequestMapping(value = "/startCourseExperiment",  method = RequestMethod.POST  , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result startResource(String[] experimentInfo) {
		if(experimentInfo != null){
			String templateId = experimentInfo[0];
			String experimentId = experimentInfo[1];
			return expService.startTmplResource(templateId, experimentId);
		}
		
		return ResultUtil.getResult(false, TmplMessage.TMPLATE_START_FAILURE);
	}
	/**
	 * 资源创建查询
	 * @author zhenghuangkun
	 * @version 2018年4月15日
	 * @param experimentInfo
	 * @return
	 */
	@RequestMapping(value = "/getExperimentResState",  method = RequestMethod.POST  , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result getResourceState(String[] experimentInfo) {
		
		if(experimentInfo != null){
			String templateId = experimentInfo[0];
			String experimentId = experimentInfo[1];
			
			return expService.getResourceCreateState(templateId, experimentId);
		}
		
		return ResultUtil.getResult(false, TmplMessage.TMPLATE_REVIEW_REFUSE); 
	}
	
	/**
	 * 资源释放
	 * @author zhenghuangkun
	 * @version 2018年4月15日
	 * @param experimentInfo
	 * @return
	 */
	@RequestMapping(value = "/terminationExperimentRes",  method = RequestMethod.POST  , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result terminationResource(String[] experimentInfo) {
		if(experimentInfo != null){
			String templateId = experimentInfo[0];
			String experimentId = experimentInfo[1];
			
			return expService.terminationTmplResource(templateId, experimentId);
		}
		
		return ResultUtil.getResult(false, TmplMessage.TMPLATE_REVIEW_REFUSE);
	}
	
	/**
	 * 镜像制作
	 * @author zhenghuangkun
	 * @version 2018年4月15日
	 * @param experimentInfo
	 * @return
	 */
	@RequestMapping(value = "/experimentAMIMake",  method = RequestMethod.POST  , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result AMIMake(String[] experimentInfo) {
		if(experimentInfo != null){
			String templateId = experimentInfo[0];
			String experimentId = experimentInfo[1];
			
			//return expService.AMIMake(templateId, experimentId, instanceId);
		}
		
		//return templateService.AMIMake(tmplId,applyId,instanceId);
		return ResultUtil.getResult(false, TmplMessage.TMPLATE_REVIEW_REFUSE);
	}
	
	/**
	 * 取得实验实验信息
	 * @author zhenghuangkun
	 * @version 2018年4月16日
	 * @param experimentInfo
	 * @return
	 */
	@RequestMapping(value = "/getExperimentResInfo",   method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result getExperimentResInfo(String[] experimentInfo) {
		
		if(experimentInfo != null){
			//String templateId = experimentInfo[0];
			String experimentId = experimentInfo[1];
			
			List<AwsInstance> eInstance = expService.listExperimentInstance(experimentId);
			
			if(eInstance != null && eInstance.size() > 0){
				return ResultUtil.getResult(true, CourseMessage.GET_EXPERIMENT_INSTANCE_SUCCESS, eInstance);
			}
			
			return ResultUtil.getResult(false, CourseMessage.GET_EXPERIMENT_INSTANCE_SUCCESS, null);
		}
		
		return ResultUtil.getResult(false, TmplMessage.TMPLATE_RESOURCE_DELETE_ERROR);
	}
}
