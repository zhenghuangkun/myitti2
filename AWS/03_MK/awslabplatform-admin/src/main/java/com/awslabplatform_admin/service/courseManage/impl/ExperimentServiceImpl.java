package com.awslabplatform_admin.service.courseManage.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.awslabplatform_admin.entity.*;

import com.awslabplatform_admin.service.userManage.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amazonaws.services.cloudformation.model.Stack;
import com.amazonaws.services.cloudformation.model.StackResource;
import com.amazonaws.services.ec2.model.Instance;
import com.awslabplatform_admin.common.Common;
import com.awslabplatform_admin.common.message.TmplMessage;
import com.awslabplatform_admin.dao.common.QuartzJobDao;
import com.awslabplatform_admin.dao.courseManage.ExperimentDao;
import com.awslabplatform_admin.dao.resourceReview.TmplReviewDao;
import com.awslabplatform_admin.dao.template.TmplInstanceDao;
import com.awslabplatform_admin.service.amazonaws.AmazonEC2Service;
import com.awslabplatform_admin.service.amazonaws.KeyPairService;
import com.awslabplatform_admin.service.amazonaws.StackService;
import com.awslabplatform_admin.service.base.impl.BaseServiceImpl;
import com.awslabplatform_admin.service.connmon.FileInfoService;
import com.awslabplatform_admin.service.courseManage.CourseService;
import com.awslabplatform_admin.service.courseManage.ExperimentService;
import com.awslabplatform_admin.service.template.TemplateService;
import com.awslabplatform_admin.service.template.impl.TemplateServiceImpl;
import com.awslabplatform_admin.task.ExperimentResourceTerminateJob;
import com.awslabplatform_admin.task.QuartzManager;
import com.awslabplatform_admin.task.ResourceTerminateJob;
import com.awslabplatform_admin.util.MyException;
import com.awslabplatform_admin.util.QuartzCronDateUtils;
import com.awslabplatform_admin.util.ResultUtil;
import com.awslabplatform_admin.util.ShiroUtil;
import com.awslabplatform_admin.util.TimeUtils;
import com.awslabplatform_admin.util.UUIDUtils;

import static com.awslabplatform_admin.common.Common.*;

/**
 * 实验组管理service 接口实现类
 * @author zhenghk
 *
 */
@Service("ExperimentService")
public class ExperimentServiceImpl extends BaseServiceImpl<ExperimentDao, Experiment, String> implements ExperimentService {

	/**
	 * 模板service
	 */
	@Autowired
	private TemplateService tempService;
	
	/**
	 * 密钥service
	 */
	@Autowired
	private KeyPairService keyPairService;
	
	/**
	 * 文件service
	 */
	@Autowired
	private FileInfoService fileInfoService;
	/**
	 * 课程service
	 */
	@Autowired
	private CourseService courseService;
	
    
    /**堆栈services*/
    @Autowired
    private  StackService  stackService;
    
	/**
	 * 实例service
	 */
	@Autowired
	private AmazonEC2Service amazonEC2Service;
    
    /**模板实例dao*/
    @Autowired
    private  TmplInstanceDao  tmplInstanceDao;
    /*CH version 2.0*/
	@Autowired
    private UserService userService;
    /**任务dao*/
    @Autowired
    private  QuartzJobDao quartzJobDao;
	
	/**
	 * 查找所有课程实验信息
	 *
	 */
	@Override
	public List<Experiment> listCourseExperiment() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
    *
    * 查询实验信息
    * @param pageInfo
    * @return
    **/
	@Override
	public void getExperimentPageInfo(PageInfo pageInfo) {
		/*查询分页数据*/
		pageInfo.setData(baseDao.listCourseExperimentInfo(pageInfo));
		/*查询总数*/
		int total = baseDao.getCourseExperimentInfoTotal(pageInfo);
		pageInfo.setRecordsTotal(total);
		pageInfo.setRecordsFiltered(total);//过滤记录数，dataTable必要参数
		return;

	}

	/**
	 * 添加多条实验信息
	 * @param experimentList
	 * @return
	 */
	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public int addMultiExperiment(List<Experiment> experimentList){
		return baseDao.addMultiExperiment(experimentList);
	}

	/**
	 * 更新实验信息
	 * @param experiment
	 * @return
	 */
	@Transactional(propagation= Propagation.REQUIRED)
	public int updateExperiment (Experiment experiment){
		return baseDao.updateExperiment(experiment);
	}
	/**
	 * 更新多条实验信息
	 * @param experimentList
	 * @return
	 */
	@Transactional(propagation= Propagation.REQUIRED)
	public int updateMultiExperiment(List<Experiment> experimentList){
		return baseDao.updateMultiExperiment(experimentList);
	}

	/**
    *
    * 删除实验
    * @param experimentId
    * @return
    **/
	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public void deleteExperimentByExperimentId(String experimentId) {
		// TODO Auto-generated method stub
		baseDao.deleteExperimentByExperimentId(experimentId);
	}

	/**
    *
    * 删除多个实验信息
    * @param experimentArray
    * @return
    **/
	@Transactional(propagation= Propagation.REQUIRED)
	public int deleteMultiExperimentByExperimentId(String[] experimentArray){
		return baseDao.deleteMultiExperimentByExperimentId(experimentArray);
	}

	/**
	 * 解析json字符串并转成实验对象
	 * @param jsonStr
	 * @return
	 */
	@Override
	public List<Experiment> parseJsonToExperiment(String jsonStr) throws MyException{
		List<Experiment> expList = null;
		int experimentNo = 0;
		try {
			expList = new ArrayList<Experiment>();
			JSONArray jary = JSONObject.parseArray(jsonStr);
			expList = jary.toJavaList(Experiment.class);

		} catch (Exception e) {

			return null;
		}

		if(expList != null && expList.size() > 0){
			
			// 设置时间格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			for(Experiment experiment : expList){
				experimentNo ++;
				String startTimeStr = experiment.getStartTime();
				String endTimeStr = experiment.getEndTime();
				
				// 开始时间判断
				if(startTimeStr == null || "".endsWith(startTimeStr)){
					// 取得当前时间
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(TimeUtils.getDateString(TimeUtils.currentTime()));
					// 设置开始时间为当前时间
					experiment.setStartTime(sdf.format(calendar.getTime()));
					
					if(endTimeStr == null || "".endsWith(endTimeStr)){
						
						calendar.add(Calendar.DAY_OF_MONTH, 30);  //加上30天
						
						// 设置结束时间为当前时间+30天
						experiment.setEndTime(sdf.format(calendar.getTime()));
					}else{
						
						Calendar endCalendar = Calendar.getInstance();
						endCalendar.setTime(TimeUtils.getDateString(endTimeStr));
						
						int ret = calendar.compareTo(endCalendar);
						if(ret >= 0){
							throw new MyException("课程结束时间小于开始时间", experimentNo, "课程结束时间小于开始时间");
						}
						
						
					}
				}
				
				else if(startTimeStr != null){
					// 取得开始时间
					Calendar calendar = Calendar.getInstance();
					System.out.println(calendar.toString());
					calendar.setTime(TimeUtils.getDateString(startTimeStr));
					
					// 设置开始时间
					experiment.setStartTime(sdf.format(calendar.getTime()));
					
					// 结束时间为空
					if(endTimeStr == null || "".endsWith(endTimeStr)){
						
						calendar.add(Calendar.DAY_OF_MONTH, 30);  //加上30天
						
						// 设置结束时间为开始时间+30天
						experiment.setEndTime(sdf.format(calendar.getTime()));
						System.out.println(calendar.toString());
						
					}else{
						Calendar endCalendar = Calendar.getInstance();
						endCalendar.setTime(TimeUtils.getDateString(endTimeStr));
						
						int ret = calendar.compareTo(endCalendar);
						if(ret >= 0){
							throw new MyException("课程结束时间小于开始时间", experimentNo, "课程结束时间小于开始时间");
						}
							
					}
				}
			
			}
		}
		
		return expList;
	}

	/**
	 * 通过courseId查找tb_experiment
	 */
	@Override
	public List<Experiment> getExperimentByCourseId(String courseId){
		return baseDao.getExperimentByCourseId(courseId);
	}

	/**
	 * 通过一个学生Id、课程Id查找出所有的实验报告
	 */
	public List<Report> getCourseReportByStudentId(String courseId, String studentId){
		return baseDao.getCourseReportByStudentId(courseId, studentId);
	}

	/**
	 * 通过学生ID和实验ID查找出一个实验报告
	 */
	public Report getCourseReportByStudentIdAndExperimentId(String experimentId, String studentId){
		return baseDao.getCourseReportByStudentIdAndExperimentId(experimentId, studentId);
	}

	/**
	 * 更新实验报告信息
	 */
	@Transactional(propagation= Propagation.REQUIRED)
	public void uodateCourseReport(Report report){
		baseDao.uodateCourseReport(report);
	}

	/**
	 * 取得实验信息
	 * @param experimentId
	 * @return
	 */
	public Experiment getExperimentInfo(String experimentId){
		return baseDao.getExperimentInfo(experimentId);
	}
	
	/**
	 * 更新实验资源信息
	 * @param experiment
	 * @return
	 */
	public int updateExperimentResourceInfo(Experiment experiment){
		return baseDao.updateExperimentResourceInfo(experiment);
	}

	/**
	 * CH version 2.0 修改查询的方法，添加查询条件
	 * 获取申请使用信息
	 * @author weixin
	 * @version 2018年4月12日
	 * @param templateId
	 * @param experimentId
	 * @return
	 */
	public Experiment getTmplUseApplyInfo(String templateId, String experimentId) {

		Experiment experimentInfo = baseDao.getExperimentInfo(experimentId);

		/**查询信息*/
		Map<String, Object> condition = new HashMap<String, Object>();
		String userId =ShiroUtil.getCurrentUserId();
		User user =new User();
		user.setUserId(userId);
		user =userService.selectIdUserData(user);
		// 取得当前用户的学院Id
		String departmentId = user.getDepartmentId();
		/**获取查询AWS堆栈相关信息*/
		condition.clear();
		condition.put("tmplId", templateId);//模板ID
		condition.put("userId", userId);//当前用户ID
		condition.put("userState", Common.STATE_ACTIVE);//可用状态
		condition.put("useType",AWS_ACCOUNTPOOL_USETYPE);//账号的运用类型
		condition.put("iamKind",AWS_IAM_IAMKIND);//IAM账号为实验者使用
		condition.put("departmentId",departmentId);//当前用户的区域ID
		condition.put("isActive",AWS_ACCOUNTPOOL_ISACTIVE);//账号池账号被激活的状态
		condition.put("isDelete",AWS_ACCOUNTPOOL_ISDELETE);//账号池账号删除标志
		condition.put("accountStause",AWS_ACCOUNT_ACCOUNTSTAUSE);//付款账号的删除状态
		condition.put("accountActive",AWS_ACCOUNT_ISACTIVE);//付款账号的激活状态
		AwsStack awsStack=  tempService.getAwsStackInfo(condition);
		if(awsStack!=null){
			awsStack.setRegion(experimentInfo.getExperimentRegionId());
		}
		experimentInfo.setAwsStack(awsStack);

		/**信息返回*/
		return experimentInfo;
	}

	/**
	 * CH 修改课程创建密钥的方法
	 * 启动模板资源
	 */
	@Override
	public Result startTmplResource(String templateId, String experimentId) {
		
		
		/*获取实验的堆栈信息*/
		Experiment experimentInfo = getTmplUseApplyInfo(templateId , experimentId);
		
		//判断实验信息是否存在
		if ( experimentInfo == null) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_REVIEW_REFUSE); 
		}
		
		//取得课程信息并判断课程状态是否是已发布
		Course course = courseService.findCourseByCourseId(experimentInfo.getCourseId());
		if(course == null){
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_START_FAILURE); 
		}
		
		if(course.getStatus() != Course.COURSE_STATUS_REGISTERED){
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_START_FAILURE); 
		}
		
		
		//判断是否资源已被创建
		String stackState = experimentInfo.getStackState();
		if ( Common.TEMPLATE_STACK_STATE_CREATE_COMPLETE.equals(stackState) || 
				Common.TEMPLATE_STACK_STATE_CREATE_IN_PROGRESS.equals(stackState)) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_START_FAILURE); 
		}
		
		//判断堆栈信息是否存在
		AwsStack awsStack = experimentInfo.getAwsStack(); 
		if (awsStack == null) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_RESOURCE_CREATE_NULL);
		}
		
		/**创建密钥*/
		String keyName = Common.TEMPLATE_STACK_NAME_FLAG + UUIDUtils.getUUID();
		/**chenhan 2019/3/18 update for V2.0*/
		FileInfo fileInfo = keyPairService.createExperimentKeyPair(keyName,experimentId);
		if (fileInfo == null) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_RESOURCE_CREATE_FAILURE);
		}
		//密钥入库
		fileInfo.setCorrelationId(keyName);
		fileInfoService.insertFileInfo(fileInfo);
		
		/**创建堆栈*/
		String stackName = Common.TEMPLATE_STACK_NAME_FLAG + UUIDUtils.getUUID();//设置堆栈名称
		awsStack.setStackName(stackName);//设置堆栈名称
		//设置模板参数
		TemplateServiceImpl.setStackParameters(awsStack,keyName);
		//设置模板Tags
		TemplateServiceImpl.setStackTags(awsStack);
		String stackId = stackService.createStack(awsStack);
		if ("".equals(stackId)) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_RESOURCE_CREATE_FAILURE); 
		}

		/**更新信息入库*/
		experimentInfo.setStackId(stackId);//设置堆栈ID
		experimentInfo.setStackName(stackName);//设置堆栈名称
		experimentInfo.setStackState(Common.TEMPLATE_STACK_STATE_CREATE_IN_PROGRESS);//堆栈创建中状态

		//信息入库
		updateExperimentResourceInfo(experimentInfo);
		
		/**结果返回*/
		return ResultUtil.getResult(true, TmplMessage.TMPLATE_START_SUCCESS, stackName); 
	}
	
	/**
	 * 获取资源创建状态
	 */
	@Override
	public Result getResourceCreateState(String templateId, String experimentId) {
		
		/*获取实验的堆栈信息*/
		Experiment experimentInfo = getTmplUseApplyInfo(templateId , experimentId);
		
		//判断申请信息是否存在
		if ( experimentInfo == null) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_REVIEW_REFUSE); 
		}
		
		//判断堆栈信息是否存在
		AwsStack awsStack = experimentInfo.getAwsStack(); 
		if (awsStack == null) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_START_ERROR);
		}
		
		/**获取AWS堆栈状态信息*/
		awsStack.setStackName(experimentInfo.getStackName());//设置堆栈名称
		List<Stack> stacks = stackService.getDescribeStacks(awsStack);
		//判断AWS堆栈信息是否存在
		if (stacks == null) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_RESOURCE_CREATE_FAILURE);
		}
		
		/**AWS堆栈创建完成*/
		if (Common.TEMPLATE_STACK_STATE_CREATE_COMPLETE.equals(stacks.get(0).getStackStatus())) {
			
			//判断是否资源已被创建
			if ( !Common.TEMPLATE_STACK_STATE_CREATE_IN_PROGRESS.equals(experimentInfo.getStackState())) {
				return ResultUtil.getResult(false, TmplMessage.TMPLATE_START_ERROR); 
			}

			//获取实例对象中需要的信息
			List<AwsInstance> awsInstances= listAwsInstanceInfo(awsStack);
			if (awsInstances == null) {
				return ResultUtil.getResult(false, TmplMessage.TMPLATE_START_ERROR);
			}
			
			/**启动定时任务*/
			experimentInfo.setAwsStack(awsStack);
			String stackName = experimentInfo.getStackName();
			//计算资源结束时间
			String currentTime = TimeUtils.currentTime();
			Long timeDifference= (long) (experimentInfo.getRuntime() * 60 *1000);
			String afterTime =  TimeUtils.getAfterTime(currentTime, timeDifference);
			if ("".equals(afterTime)) {
				return ResultUtil.getResult(false, TmplMessage.TMPLATE_RESOURCE_CREATE_FAILURE); 
			}
			
			String cron = QuartzCronDateUtils.getCron(afterTime);//时间格式转换
			experimentInfo.setStackState(Common.TEMPLATE_STACK_STATE_CREATE_COMPLETE);
			QuartzManager.addJob( stackName,Common.TEMPLATE_APPLY_TERMINATED_JOB_GROUP_NAME, 
					stackName, Common.TEMPLATE_APPLY_TERMINATED_JOB_GROUP_NAME, ExperimentResourceTerminateJob.class, cron, experimentInfo);
			
			/**插入定时任务数据*/
			QuartzJob quartzJob = TemplateServiceImpl.getQuartzJob(stackName, Common.TEMPLATE_APPLY_TERMINATED_JOB_GROUP_NAME, ResourceTerminateJob.class.getName());
			quartzJobDao.insertQuartzJob(quartzJob);
			
			//更新信息堆栈状态
			experimentInfo.setStackState(Common.TEMPLATE_STACK_STATE_CREATE_COMPLETE);
			experimentInfo.setUseStartTime(currentTime);//开始时间
			experimentInfo.setUseEndTime(afterTime);//结束时间
			updateExperimentResourceInfo(experimentInfo);
			
			//返回消息
			experimentInfo.setAwsInstances(awsInstances);
			experimentInfo.setUseEndTime(afterTime);
			return ResultUtil.getResult(true, TmplMessage.TMPLATE_START_SUCCESS, experimentInfo);
		}
		
		/**AWS堆栈创建中*/
		if (Common.TEMPLATE_STACK_STATE_CREATE_IN_PROGRESS.equals(stacks.get(0).getStackStatus())){
			return ResultUtil.getResult(true, TmplMessage.TMPLATE_START_SUCCESS,Common.TEMPLATE_STACK_STATE_CREATE_IN_PROGRESS);
		}
		
		/**AWS堆栈创建失败*/
		else if (Common.TEMPLATE_STACK_STATE_CREATE_FAILED.equals(stacks.get(0).getStackStatus())){
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_RESOURCE_CREATE_FAILURE);
		} 
		
		/* 创建失败-重新设置数据信息 */
		else if("ROLLBACK_COMPLETE".equals(stacks.get(0).getStackStatus())){
			//更新信息堆栈状态
			experimentInfo.setStackState(Common.TEMPLATE_STACK_STATE_CREATE_NO);
			updateExperimentResourceInfo(experimentInfo);
			/**返回结果*/
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_RESOURCE_CREATE_FAILURE);
		}
		
		else{
			/**返回结果*/
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_RESOURCE_CREATE_FAILURE);
		}
		
	}


	/**
	 * CH 修改课程删除密钥的方法
	 * version 2.0
	 * 释放模板资源
	 */
	@Override
	public Result terminationTmplResource(String templateId, String experimentId) {
		/*获取实验的堆栈信息*/
		Experiment experimentInfo = getTmplUseApplyInfo(templateId , experimentId);
		
		//判断申请信息是否存在
		if ( experimentInfo == null) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_REVIEW_REFUSE); 
		}
		
		//判断是否资源已成功创建 
		if ( !Common.TEMPLATE_STACK_STATE_CREATE_COMPLETE.equals(experimentInfo.getStackState())) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_RESOURCE_DELETE_ERROR_NO_EXISTS); 
		}
		
		//判断堆栈信息是否存在
		AwsStack awsStack = experimentInfo.getAwsStack(); 
		if (awsStack == null) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_RESOURCE_DELETE_ERROR); 
		}
		
		// 取得实例信息
		List<AwsInstance> awsInstancesTmp = listExperimentInstance(experimentInfo.getExperimentId());
		experimentInfo.setAwsInstances(awsInstancesTmp);
		
		/**更新信息*/
		Experiment updateExperimentInfo = new Experiment();
		updateExperimentInfo.setExperimentId(experimentInfo.getExperimentId());//设置实验Id
		updateExperimentInfo.setStackState(Common.TEMPLATE_STACK_STATE_DELETE_COMPLETE);//设置删除状态
		String currentTime = TimeUtils.currentTime();//获取当前时间
		
		//获取实际使用时长
		String useStartTime = experimentInfo.getUseStartTime();//开始使用时间
		Long actualUseLength = TimeUtils.getMinutesDifference(TimeUtils.getDateString(useStartTime), TimeUtils.getDateString(currentTime));//
		updateExperimentInfo.setActualUseLength(actualUseLength);//实例使用时长
		updateExperimentInfo.setStackDeleteTime(currentTime);//删除堆栈时间
		updateExperimentResourceInfo(updateExperimentInfo);
		
		/**更新实例状态*/
		List<AwsInstance> awsInstances = experimentInfo.getAwsInstances();
		for (AwsInstance awsInstance : awsInstances) {
			awsInstance.setInstanceState(Common.TEMPLATE_RESOURCE_INSTANCE_STATE_TERMINATED);
			awsInstance.setOperator(ShiroUtil.getCurrentUserId());
			awsInstance.setOptTime(TimeUtils.currentTime());
		}
		tmplInstanceDao.updateTmplInstances(awsInstances);
		
		
		/**删除定时任务*/
		String stackName = experimentInfo.getStackName();
		QuartzManager.removeJob( stackName,Common.TEMPLATE_APPLY_TERMINATED_JOB_GROUP_NAME, 
				stackName, Common.TEMPLATE_APPLY_TERMINATED_JOB_GROUP_NAME);
		
		/**更新数据库定时任务状态*/
		QuartzJob quartzJob = new QuartzJob();
		quartzJob.setJobName(stackName);
		quartzJob.setJobState(Common.STATE_DELETE);
		quartzJob.setJobEndTime(TimeUtils.currentTime());
		quartzJobDao.updateQuartzJob(quartzJob);

		/**chenhan 2019/3/18 update for V2.0*/
		if ( !keyPairService.deleteExperimentKeyPair(experimentInfo.getAwsInstances().get(0).getKeyName(),experimentInfo.getExperimentId()) ) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_RESOURCE_DELETE_ERROR);
		}
		
		/**删除堆栈*/
		awsStack.setStackName(experimentInfo.getStackName());
		if (!stackService.deleteStack(awsStack)) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_RESOURCE_DELETE_ERROR); 
		}
		/**返回结果*/
		return ResultUtil.getResult(true, TmplMessage.TMPLATE_RESOURCE_DELETE_SUCCESS); 
	}
	
	/**
	 * 镜像制作
	 */
	@Override
	public Result AMIMake(String tmplId, String experimentId, String instanceId) {
		/*获取实验的堆栈信息*/
		Experiment experimentInfo = getTmplUseApplyInfo(tmplId , experimentId);
		
		//判断申请信息是否存在
		if ( experimentInfo == null) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_REVIEW_REFUSE); 
		}
		
		//判断是否资源已成功创建 
		if ( !Common.TEMPLATE_STACK_STATE_CREATE_COMPLETE.equals(experimentInfo.getStackState())) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_RESOURCE_DELETE_ERROR_NO_EXISTS); 
		}
		
		//判断堆栈信息是否存在
		AwsStack awsStack = experimentInfo.getAwsStack(); 
		if (awsStack == null) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_RESOURCE_DELETE_ERROR); 
		}
		
		/**创建镜像*/
		String imageName = Common.TEMPLATE_INSTANCE_AMI_NAME_HEAD + UUIDUtils.getUUID() ;
		String imageId = amazonEC2Service.createImage(awsStack,instanceId, imageName);
		if ("".endsWith(imageId)) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_INSTANCE_AMI_CREATE_ERROR); 
		}
		
		/**更新实例信息*/
		AwsInstance awsInstance = new AwsInstance();
		awsInstance.setInstanceId(instanceId);//实例ID
		//awsInstance.setImageId(imageId);//镜像ID
		//awsInstance.setImageName(imageName);//镜像名称
		tmplInstanceDao.updateTmplInstance(awsInstance);
		
		/**返回消息*/
		return ResultUtil.getResult(true, TmplMessage.TMPLATE_INSTANCE_AMI_CREATE_SUCCESS, imageId); 
	}
	
	/**
	 * 获取实例信息
	 * @author zhenghuangkun
	 * @version 2018年4月11日
	 * @param awsStack
	 * @return
	 */
	public List<AwsInstance> listAwsInstanceInfo(AwsStack awsStack) {
		
		//获取AWS堆栈资源
		List<StackResource> stackResources = stackService.describeStackResources(awsStack);
		if  (stackResources == null) {
			return null;
		}
		//获取实例ID
		List<String> instanceIds = new ArrayList<String>();
		List<AwsInstance> awsInstances = new ArrayList<AwsInstance>();
		for ( StackResource stackResource : stackResources) {
			/*判断资源类型为实例类型*/
			if (Common.TEMPLATE_RESOURCE_TYPE_EC2_INSTANCE.equals(stackResource.getResourceType())) {
				AwsInstance awsInstance= new AwsInstance();
				String instanceId = stackResource.getPhysicalResourceId();//实例ID
				String instanceName = stackResource.getLogicalResourceId();//实例名称
				
				awsInstance.setInstanceId(instanceId);
				awsInstance.setInstanceName(instanceName);
				
				instanceIds.add(instanceId);//保存ID
				awsInstances.add(awsInstance);//保存实例对象
			}
		}
		awsStack.setInstanceIds(instanceIds);
		
		//获取AWS堆栈实例实例对象
		List<Instance> instances =  amazonEC2Service.describeInstances(awsStack);
		if  (instances == null) {
			return null;
		}
		
		//获取需要的实例信息
		for ( Instance instance : instances) {
			for ( AwsInstance awsInstance : awsInstances) {
				String instanceId = instance.getInstanceId();
				String awsInstanceId = awsInstance.getInstanceId();
				if (instanceId.equals(awsInstanceId) ) {
					awsInstance.setId(UUIDUtils.getUUID());//设置ID
					awsInstance.setCorrelationId(awsStack.getStackName());//设置关联ID
					awsInstance.setInstanceType(instance.getInstanceType());//实例类型
					awsInstance.setKeyName(instance.getKeyName());//密钥名
					awsInstance.setPrivateIpAddress(instance.getPrivateIpAddress());//私有IP
					awsInstance.setPublicIpAddress(instance.getPublicIpAddress());//共有IP
					awsInstance.setInstanceState(instance.getState().getName());//实例状态
					awsInstance.setOperator(ShiroUtil.getCurrentUserId());
					awsInstance.setOptTime(TimeUtils.currentTime());
				}
			}
		}
		
		//插入实例数据
		tmplInstanceDao.insertmplInstanceList(awsInstances);
		
		/**查出实例，包含密钥链接*/
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("stackName", awsStack.getStackName());
		return tmplInstanceDao.listTmplInstance(condition);
		
	}
	
	
	/**
	 * 取得实验的实例信息
	 * @param experimentId
	 * @return
	 */
	public List<AwsInstance> listExperimentInstance(String experimentId){
		return baseDao.listExperimentInstance(experimentId);
	}
	/**
	 * CH version 2.0
	 * 取得实验区域
	 * @param experimentId 实验ID
	 * @return
	 */
	@Override
	public String getExperimentRegion(String experimentId){

		return baseDao.getExperimentRegion(experimentId);
	}
}
