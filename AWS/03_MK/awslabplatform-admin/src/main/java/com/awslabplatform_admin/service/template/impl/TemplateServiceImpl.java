package com.awslabplatform_admin.service.template.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.awslabplatform_admin.entity.*;
import com.awslabplatform_admin.service.userManage.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.cloudformation.model.Parameter;
import com.amazonaws.services.cloudformation.model.Stack;
import com.amazonaws.services.cloudformation.model.StackResource;
import com.amazonaws.services.cloudformation.model.Tag;
import com.amazonaws.services.ec2.model.Instance;
import com.awslabplatform_admin.common.Common;
import com.awslabplatform_admin.common.message.TmplMessage;
import com.awslabplatform_admin.dao.common.QuartzJobDao;
import com.awslabplatform_admin.dao.resourceReview.TmplReviewDao;
import com.awslabplatform_admin.dao.template.InstanceImageDao;
import com.awslabplatform_admin.dao.template.TemplateDao;
import com.awslabplatform_admin.service.amazonaws.AmazonEC2Service;
import com.awslabplatform_admin.service.amazonaws.KeyPairService;
import com.awslabplatform_admin.service.amazonaws.StackService;
import com.awslabplatform_admin.service.base.impl.BaseServiceImpl;
import com.awslabplatform_admin.service.connmon.FileInfoService;
import com.awslabplatform_admin.service.template.TemplateService;
import com.awslabplatform_admin.task.QuartzManager;
import com.awslabplatform_admin.task.ResourceTerminateJob;
import com.awslabplatform_admin.util.QuartzCronDateUtils;
import com.awslabplatform_admin.util.ResultUtil;
import com.awslabplatform_admin.util.ShiroUtil;
import com.awslabplatform_admin.util.TimeUtils;
import com.awslabplatform_admin.util.UUIDUtils;
import com.awslabplatform_admin.dao.template.TmplInstanceDao;

import static com.awslabplatform_admin.common.Common.*;

/**
 * 模板管理service 接口实现类
 * @author weix
 * @version 2018-3-19
 */
@Service("TemplateService")
public class TemplateServiceImpl extends BaseServiceImpl<TemplateDao, Template, String> implements TemplateService {
	//private static Logger log = LoggerFactory.getLogger(TemplateServiceImpl.class);

    /**文件信息管理service*/
    @Autowired
    private  FileInfoService  fileInfoService;
    
    /**堆栈services*/
    @Autowired
    private  StackService  stackService;
    /*CH version 2.0*/
	@Autowired
	private UserService userService;
	/**
	 * 密钥service
	 */
	@Autowired
	private KeyPairService keyPairService;
	/**
	 * 实例service
	 */
	@Autowired
	private AmazonEC2Service amazonEC2Service;
    
    /**模板实例dao*/
    @Autowired
    private  TmplInstanceDao  tmplInstanceDao;
    
    /**模板审核dao*/
    @Autowired
    private  TmplReviewDao  tmplReviewDao;
    
    /**
     * 镜像DAO 
     */
    @Autowired
    private InstanceImageDao instanceImageDao;
    
    /**任务dao*/
    @Autowired
    private  QuartzJobDao quartzJobDao;
    
    /**
	 * 插入模板
	 */
	@Override
	public Boolean insertTemplate (Template tmpl) {
		/*判断模板数据库是否存在*/
		if ( existsTemplate(false, tmpl) ) {
			return false;
		} 
		/*文件信息入库*/
		FileInfo imgInfo = tmpl.getTmplImg();
		/*判断是否有上传模板缩略图*/
		if (imgInfo != null) {
			fileInfoService.insertFileInfo(tmpl.getTmplImg());
		}
		fileInfoService.insertFileInfo(tmpl.getTmplScript());
		
		/*模板信息入库*/
		tmpl.setTmplFromFlag(Common.TEMPLATE_FROM_FLAG_PLATFORM);//设置模板来源
		tmpl.setOptTime(TimeUtils.currentTime());		/*设置操作时间*/
		tmpl.setOperator(ShiroUtil.getCurrentUserId());  /*设置当前操作者*/
		tmpl.setState(Common.STATE_ACTIVE);   /*设置状态可用*/
		baseDao.insert(tmpl);
		return true;
	}
	
	/**
	 * 更新模板
	 */
	@Override
	public Boolean updateTemplate(Template tmpl) {
		/*判断模板数据库是否存在*/
		if ( existsTemplate(true, tmpl) ) {
			return false;
		}
		/*文件信息入库*/
		FileInfo imgInfo = tmpl.getTmplImg();
		FileInfo scriptInfo = tmpl.getTmplScript();
		/*判断是否有上传模板缩略图*/
		if (imgInfo != null) {
			fileInfoService.updateFileInfo(imgInfo);
		}
		/*判断是否有更改资源脚本*/
		if (scriptInfo != null) {
			fileInfoService.updateFileInfo(scriptInfo);
		}
		
		/*模板信息入库*/
		tmpl.setOptTime(TimeUtils.currentTime());		/*设置操作时间*/
		tmpl.setOperator(ShiroUtil.getCurrentUserId());  /*设置当前操作者*/
		baseDao.updateByPrimaryKeySelective(tmpl);
		return true;
	}
	
	/**
	 * 查询推荐模板缩略图列表
	 */
	@Override
	public List<Template> listIndexTmplThumbnail(String keyword) {
		Map<String, Object> condition=  new HashMap<String, Object>();
		condition.put("keyword", keyword);//设置搜索关键词
		condition.put("state", Common.STATE_ACTIVE);//模板状态可以用
		condition.put("releaseStatus", Common.TEMPLATE_RELEASE_TRUE);//发布状态
		condition.put("releaseDept", ShiroUtil.getCurrentUser().getDepartmentId());
		condition.put("releaseRangePlat", Common.TEMPLATE_RELEASE_RANGE_PLATFORM);//设置模板来源-平台
		condition.put("releaseRangeDept", Common.TEMPLATE_RELEASE_RANGE_DEPT);//设置模板来源-院系
		condition.put("descriptionLength", Common.TEMPLATE_DESCRIPTION_LENGTH);//文本描述截图长度
		condition.put("showSize", Common.TEMPLATE_SHOW_SIZE);//显示个数
		condition.put("sort", Common.TEMPLATE_SORT_RELEASETIME);//排序
		return  baseDao.listIndexTmplThumbnail(condition);
	}
	
	/**
	 * 查询所有模板缩略图列表
	 */
	@Override
	public PageInfo listAllTmplThumbnail(Integer page, String sort, String keyword) {
		Map<String, Object> condition=  new HashMap<String, Object>();
		condition.put("keyword", keyword);//设置搜索关键词
		condition.put("state", Common.STATE_ACTIVE);//模板状态可以用
		condition.put("releaseStatus", Common.TEMPLATE_RELEASE_TRUE);//发布状态
		condition.put("releaseDept", ShiroUtil.getCurrentUser().getDepartmentId());
		condition.put("releaseRangePlat", Common.TEMPLATE_RELEASE_RANGE_PLATFORM);//设置模板来源-平台
		condition.put("releaseRangeDept", Common.TEMPLATE_RELEASE_RANGE_DEPT);//设置模板来源-院系
		condition.put("descriptionLength", Common.TEMPLATE_DESCRIPTION_LENGTH);//文本描述截图长度
		if ("".equals(sort)) {
			sort = Common.TEMPLATE_SORT_OPTTIME;//设置默认时间排序
		}
		PageInfo pageInfo = new PageInfo(page , Common.TEMPLATE_LIST_PAGE_SIZE, sort, Common.TEMPLATE_LIST_PAGE_ORDER);//创建分页对象
		pageInfo.setCondition(condition);
		List<Template> tmplList = baseDao.listTmplThumbnailData(pageInfo);
		pageInfo.setData(tmplList);//获取记录
		pageInfo.setRecordsTotal(baseDao.getTmplThumbnailTotal(pageInfo));//获取总数
		return pageInfo;
	}
	
	/**
	 * 查询设计模板缩略图列表
	 */
	@Override
	public PageInfo listTmplDesignThumbnail(Integer page, String sort, String keyword) {
		Map<String, Object> condition=  new HashMap<String, Object>();
		condition.put("keyword", keyword);//设置搜索关键词
		condition.put("state", Common.STATE_ACTIVE);//查询状态可以用
		condition.put("descriptionLength", Common.TEMPLATE_DESCRIPTION_LENGTH);//文本描述截图长度
		condition.put("tmplFromFlag", Common.TEMPLATE_FROM_FLAG_PLATFORM);//设置模板来源
		PageInfo pageInfo = new PageInfo(page , Common.TEMPLATE_LIST_PAGE_SIZE, Common.TEMPLATE_SORT_OPTTIME, Common.TEMPLATE_LIST_PAGE_ORDER);//创建分页对象
		pageInfo.setCondition(condition);
		List<Template> tmplList = baseDao.listTmplThumbnailData(pageInfo);
		pageInfo.setData(tmplList);//获取记录
		pageInfo.setRecordsTotal(baseDao.getTmplThumbnailTotal(pageInfo));//获取总数
		return pageInfo;
	}

	/**
	 * 删除模板
	 */
	@Override
	public Boolean deleteTemplate(String tmplId) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("tmplId", tmplId);//设置模板ID
		condition.put("state", Common.STATE_DELETE);//设置删除状态
		condition.put("optTime", TimeUtils.currentTime());		/*设置操作时间*/
		condition.put("operator", ShiroUtil.getCurrentUserId());  /*设置当前操作者*/
		
		//删除模板
		baseDao.deleteTemplate(condition);
		return true;
	}
	/**
	 * 查询模板信息
	 */
	@Override
	public Template getTemplateInfo(String tmplId) {
		Map<String, Object> condition=  new HashMap<String, Object>();
		condition.put("tmplId", tmplId);//设置模板名称
		condition.put("state", Common.STATE_ACTIVE);//设置模板状态
		return baseDao.getTemplateInfo(condition);
	}
	/**
	 * 发布模板
	 */
	@Override
	public Boolean releaseTemplate(String tmplId) {
		Template tmpl = new Template();
		tmpl.setTmplId(tmplId);//设置模板ID
		/*判断模板数据库是否存在*/
		if ( !existsTemplate(false, tmpl) ) {
			return false;
		}
		tmpl.setReleaseTime(TimeUtils.currentTime());//设置发布时间
		tmpl.setReleaseStatus(Common.TEMPLATE_RELEASE_TRUE);//设置发布状态
		tmpl.setReleaser(ShiroUtil.getCurrentUserId());//设置发布人
		baseDao.updateByPrimaryKeySelective(tmpl);
		return true;
	}
	
	/**
	 * 判断模板数据库是否存在
	 * @author weixin
	 * @version 2018年3月28日
	 * @param isEdit 是否为编辑
	 * @param tmpl 模板对象
	 * @return
	 */
	public Boolean existsTemplate(Boolean isEdit , Template tmpl){
		/*判断模板是否存在*/
		Map<String, Object> condition=  new HashMap<String, Object>();
		condition.put("state", Common.STATE_ACTIVE);//设置模板状态
		if (isEdit) {
			condition.put("tmplId", tmpl.getTmplId());//设置模板名称
			Template retTmpl = baseDao.selectByCondition(condition);//根据ID查询模板
			if (retTmpl == null ) {
				return true;
			}
			/*判断模板名称是否发生变更*/
			if (retTmpl.getTmplName().equals(tmpl.getTmplName())) {
				return false;
			}
		}
		condition.put("tmplName", tmpl.getTmplName());//设置模板名称
		if ( baseDao.getTemplateTotal(condition) > 0 ) {
			return true;
		} 
		return false;
	}
	

	/**
	 * 查询所有模板
	 */
	@Override
	public List<Template> getTemplateList(){
		Map<String, Object> condition=  new HashMap<String, Object>();
		condition.put("state", Common.STATE_ACTIVE);//设置模板状态
		condition.put("releaseStatus", Common.TEMPLATE_RELEASE_TRUE);//已发布状态
		condition.put("releaseDept", ShiroUtil.getCurrentUser().getDepartmentId());//当前院系
		condition.put("releaseRangePlat", Common.TEMPLATE_RELEASE_RANGE_PLATFORM);//设置模板来源-平台
		condition.put("releaseRangeDept", Common.TEMPLATE_RELEASE_RANGE_DEPT);//设置模板来源-院系
		return baseDao.getTemplateList(condition);
	}

	/**
	 * 根据模板ID查询模板
	 * @author weix
	 * @date 2018年5月27日  
	 * @param tmplId
	 * @return
	 */
	@Override
	public Template getTemplateById(String tmplId){
		return baseDao.getTemplateById(tmplId);
	}

	/**
	 * 模板收藏
	 */
	@Override
	public Result tmplCollection(String tmplId) {
		Template tmpl = new Template();
		tmpl.setTmplId(tmplId);//设置模板ID
		/*判断模板数据库是否存在*/
		if ( !existsTemplate(false, tmpl) ) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_NO_EXISTS);
		}
		
		Map<String,Object> tmplCollection  = new HashMap<String, Object>();
		tmplCollection.put("tmplId", tmplId);//模板ID
		tmplCollection.put("userId", ShiroUtil.getCurrentUserId());//当前用户ID
		tmplCollection.put("collectionTime", TimeUtils.currentTime());//当前时间
		
		/*判断模板是否已收藏*/
		if (baseDao.getTmplCollection(tmplCollection) != null) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_COLLECTION_EXISTS);
		}
		String collectionId = UUIDUtils.getUUID();
		tmplCollection.put("collectionId", collectionId);//收藏ID
		/*插入收藏记录*/
		baseDao.insertTmplCollection(tmplCollection);
		
		/*返回结果*/
		return ResultUtil.getResult(true, TmplMessage.TMPLATE_COLLECTION_SUCCESS, collectionId);
	}

	/**
	 * 模板取消收藏
	 */
	@Override
	public Result tmplCollectionCancel(String tmplId) {
		Map<String,Object> tmplCollection  = new HashMap<String, Object>();
		tmplCollection.put("tmplId", tmplId);//模板ID
		tmplCollection.put("userId", ShiroUtil.getCurrentUserId());//当前用户ID
		
		/*判断模板是否已收藏*/
		if ("".equals(baseDao.getTmplCollection(tmplCollection))) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_COLLECTION_NOEXISTS);
		}
		
		/*删除收藏记录*/
		baseDao.deleteTmplCollection(tmplCollection);
		
		/*返回结果*/
		return ResultUtil.getResult(true, TmplMessage.TMPLATE_COLLECTION_CANCEL_SUCCESS, "");
	}

	/**
	 * 获取模板收藏ID
	 */
	@Override
	public Map<String,Object> getTmplCollection(String tmplId) {
		Map<String,Object> tmplCollection  = new HashMap<String, Object>();
		tmplCollection.put("tmplId", tmplId);//模板ID
		tmplCollection.put("userId", ShiroUtil.getCurrentUserId());//当前用户ID
		
		//返回收藏ID
		return baseDao.getTmplCollection(tmplCollection);
	}

	/**
	 * 获取收藏模板
	 */
	@Override
	public PageInfo listTmplcollection(Integer page, String keyword) {
		Map<String, Object> condition=  new HashMap<String, Object>();
		condition.put("keyword", keyword);//设置搜索关键词
		condition.put("state", Common.STATE_ACTIVE);//查询状态可以用
		condition.put("releaseStatus", Common.TEMPLATE_RELEASE_TRUE);//已发布状态
		condition.put("descriptionLength", Common.TEMPLATE_DESCRIPTION_LENGTH);//文本描述截图长度
		condition.put("userId", ShiroUtil.getCurrentUserId());//设置当前用户ID
		PageInfo pageInfo = new PageInfo(page , Common.TEMPLATE_LIST_PAGE_SIZE, Common.TEMPLATE_SORT_COLLECTTIME, Common.TEMPLATE_LIST_PAGE_ORDER);//创建分页对象
		pageInfo.setCondition(condition);
		List<Template> tmplList = baseDao.listTmplThumbnailData(pageInfo);
		pageInfo.setData(tmplList);//获取记录
		pageInfo.setRecordsTotal(baseDao.getTmplThumbnailTotal(pageInfo));//获取总数
		return pageInfo;
	}

	
	/**
	 * 启动模板资源
	 */
	@Override
	public Result startTmplResource(TmplUseApply tmplUseApply) {
		/**获取申请和堆栈信息*/
		tmplUseApply = getTmplUseApplyInfo(tmplUseApply.getTmplId() , tmplUseApply.getApplyId());
		
		//判断申请信息是否存在
		if ( tmplUseApply == null) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_REVIEW_REFUSE); 
		}
		
		//判断是否资源已被创建
		if ( !Common.TEMPLATE_STACK_STATE_CREATE_NO.equals(tmplUseApply.getStackState())) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_START_FAILURE); 
		}
		
		//判断堆栈信息是否存在
		AwsStack awsStack = tmplUseApply.getAwsStack(); 
		if (awsStack == null) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_RESOURCE_CREATE_NULL);
		}
		
		/**创建密钥*/
		String keyName = Common.TEMPLATE_STACK_NAME_FLAG + UUIDUtils.getUUID();
		FileInfo fileInfo = keyPairService.createKeyPair(keyName);
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
		setStackParameters(awsStack,keyName);
		//设置模板Tags
		setStackTags(awsStack);
		if(awsStack.getRegion()==null||awsStack.getRegion().equals("")){
			awsStack.setRegion(TEMPLATE_REGION);
		}
		String stackId = stackService.createStack(awsStack);
		if ("".equals(stackId)) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_RESOURCE_CREATE_FAILURE); 
		}

		/**更新申请信息入库*/
		TmplUseApply updateApply = new TmplUseApply();
		updateApply.setApplyId(tmplUseApply.getApplyId());
		updateApply.setStackId(stackId);//设置堆栈ID
		updateApply.setStackName(stackName);//设置堆栈名称
		updateApply.setStackState(Common.TEMPLATE_STACK_STATE_CREATE_IN_PROGRESS);//堆栈创建中状态

		//信息入库
		tmplReviewDao.updateTmplUseApply(updateApply);
		
		/**结果返回*/
		return ResultUtil.getResult(true, TmplMessage.TMPLATE_START_SUCCESS, stackName); 
	}

	/**
	 * 获取资源创建状态
	 */
	@Override
	public Result getResourceCreateState(TmplUseApply tmplUseApply) {
		
		/**获取申请和堆栈信息*/
		tmplUseApply = getTmplUseApplyInfo(tmplUseApply.getTmplId() , tmplUseApply.getApplyId());
		
		//判断申请信息是否存在
		if ( tmplUseApply == null) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_REVIEW_REFUSE); 
		}
		
		//判断堆栈信息是否存在
		AwsStack awsStack = tmplUseApply.getAwsStack(); 
		if (awsStack == null) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_START_ERROR);
		}
		if(awsStack.getRegion()==null||awsStack.getRegion().equals("")){
			awsStack.setRegion(TEMPLATE_REGION);
		}
		/**获取AWS堆栈状态信息*/
		awsStack.setStackName(tmplUseApply.getStackName());//设置堆栈名称
		List<Stack> stacks = stackService.getDescribeStacks(awsStack);
		//判断AWS堆栈信息是否存在
		if (stacks == null) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_START_ERROR);
		}
		if(awsStack.getRegion()==null||awsStack.getRegion().equals("")){
			awsStack.setRegion(TEMPLATE_REGION);
		}
		/**AWS堆栈创建完成*/
		if (Common.TEMPLATE_STACK_STATE_CREATE_COMPLETE.equals(stacks.get(0).getStackStatus())) {
			
			//判断是否资源已被创建
			if ( !Common.TEMPLATE_STACK_STATE_CREATE_IN_PROGRESS.equals(tmplUseApply.getStackState())) {
				return ResultUtil.getResult(false, TmplMessage.TMPLATE_START_ERROR); 
			}

			//获取实例对象中需要的信息
			List<AwsInstance> awsInstances= listAwsInstanceInfo(awsStack);
			if (awsInstances == null) {
				return ResultUtil.getResult(false, TmplMessage.TMPLATE_START_ERROR);
			}
			tmplUseApply.setAwsInstances(awsInstances);
			
			//计算资源结束时间
			String currentTime = TimeUtils.currentTime();
			Long timeDifference= (tmplUseApply.getUseTimeLength() * 60*1000);
			String afterTime =  TimeUtils.getAfterTime(currentTime, timeDifference);
			if ("".equals(afterTime)) {
				return ResultUtil.getResult(false, TmplMessage.TMPLATE_RESOURCE_CREATE_FAILURE); 
			}
			
			String stackName = tmplUseApply.getStackName();
			
			/**插入定时任务数据*/
			QuartzJob quartzJob = getQuartzJob(stackName, Common.TEMPLATE_APPLY_TERMINATED_JOB_GROUP_NAME, ResourceTerminateJob.class.getName());
			quartzJobDao.insertQuartzJob(quartzJob);
			
			//更新信息堆栈状态
			TmplUseApply updateApply = new TmplUseApply();
			updateApply.setApplyId(tmplUseApply.getApplyId());
			updateApply.setStackState(Common.TEMPLATE_STACK_STATE_CREATE_COMPLETE);
			updateApply.setUseStartTime(currentTime);//开始时间
			updateApply.setUseEndTime(afterTime);//结束时间
			tmplReviewDao.updateTmplUseApply(updateApply);
			
			
			/**启动定时任务*/
			tmplUseApply.setAwsStack(awsStack);
			String cron = QuartzCronDateUtils.getCron(afterTime);//时间格式转换
			tmplUseApply.setStackState(Common.TEMPLATE_STACK_STATE_CREATE_COMPLETE);
			QuartzManager.addJob( stackName,Common.TEMPLATE_APPLY_TERMINATED_JOB_GROUP_NAME, 
					stackName, Common.TEMPLATE_APPLY_TERMINATED_JOB_GROUP_NAME, ResourceTerminateJob.class, cron, tmplUseApply);
			
			//返回消息
			updateApply.setAwsInstances(awsInstances);
			updateApply.setUseEndTime(afterTime);
			return ResultUtil.getResult(true, TmplMessage.TMPLATE_START_SUCCESS, updateApply);
		}
		
		/**AWS堆栈创建中*/
		if (Common.TEMPLATE_STACK_STATE_CREATE_IN_PROGRESS.equals(stacks.get(0).getStackStatus())){
			return ResultUtil.getResult(true, TmplMessage.TMPLATE_START_SUCCESS,Common.TEMPLATE_STACK_STATE_CREATE_IN_PROGRESS);
		}
		
		/**AWS堆栈创建失败*/
		if (Common.TEMPLATE_STACK_STATE_CREATE_FAILED.equals(stacks.get(0).getStackStatus())){
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_RESOURCE_CREATE_FAILURE);
		} 
		
		/**回滚返回结果*/
		TmplUseApply updateApply = new TmplUseApply();
		updateApply.setApplyId(tmplUseApply.getApplyId());
		updateApply.setStackState(Common.TEMPLATE_STACK_STATE_CREATE_NO);
		tmplReviewDao.updateTmplUseApply(updateApply);
		return ResultUtil.getResult(false, TmplMessage.TMPLATE_RESOURCE_CREATE_FAILURE,"");
	}
	
	
	/**
	 * CH
	 * 释放模板资源
	 */
	@Override
	public Result terminationTmplResource(TmplUseApply tmplUseApply) {
		/**获取申请信息*/
		tmplUseApply = getTmplUseApplyInfo(tmplUseApply.getTmplId() , tmplUseApply.getApplyId());
		
		//判断申请信息是否存在
		if ( tmplUseApply == null) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_REVIEW_REFUSE); 
		}
		
		//判断是否资源已成功创建 
		if ( !Common.TEMPLATE_STACK_STATE_CREATE_COMPLETE.equals(tmplUseApply.getStackState())) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_RESOURCE_DELETE_ERROR_NO_EXISTS); 
		}
		
		//判断堆栈信息是否存在
		AwsStack awsStack = tmplUseApply.getAwsStack(); 
		if (awsStack == null) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_RESOURCE_DELETE_ERROR); 
		}
		if(awsStack.getRegion()==null||awsStack.getRegion().equals("")){
			awsStack.setRegion(TEMPLATE_REGION);
		}
		/**更新实例状态*/
		List<AwsInstance> awsInstances = tmplUseApply.getAwsInstances();
		for (AwsInstance awsInstance : awsInstances) {
			awsInstance.setInstanceState(Common.TEMPLATE_RESOURCE_INSTANCE_STATE_TERMINATED);
			awsInstance.setOperator(ShiroUtil.getCurrentUserId());
			awsInstance.setOptTime(TimeUtils.currentTime());
		}
		tmplInstanceDao.updateTmplInstances(awsInstances);
		
		/**删除定时任务*/
		String stackName = tmplUseApply.getStackName();
		QuartzManager.removeJob( stackName,Common.TEMPLATE_APPLY_TERMINATED_JOB_GROUP_NAME, 
				stackName, Common.TEMPLATE_APPLY_TERMINATED_JOB_GROUP_NAME);
		
		/**更新数据库定时任务状态*/
		QuartzJob quartzJob = new QuartzJob();
		quartzJob.setJobName(stackName);
		quartzJob.setJobState(Common.STATE_DELETE);
		quartzJob.setJobEndTime(TimeUtils.currentTime());
		quartzJobDao.updateQuartzJob(quartzJob);
		
		/**更新信息*/
		TmplUseApply updateUseApply = new TmplUseApply();
		updateUseApply.setApplyId(tmplUseApply.getApplyId());
		updateUseApply.setStackState(Common.TEMPLATE_STACK_STATE_DELETE_COMPLETE);//设置删除状态
		String currentTime = TimeUtils.currentTime();//获取当前时间
		
		//获取实际使用时长
		String useStartTime = tmplUseApply.getUseStartTime();//开始使用时间
		Long actualUseLength = TimeUtils.getMinutesDifference(TimeUtils.getDateString(useStartTime), TimeUtils.getDateString(currentTime));//
		updateUseApply.setActualUseLength(actualUseLength);//实例使用时长
		updateUseApply.setStackDeleteTime(currentTime);//删除堆栈时间
		tmplReviewDao.updateTmplUseApply(updateUseApply);
		
		/**删除密钥*/
		if ( !keyPairService.deleteKeyPair(tmplUseApply.getAwsInstances().get(0).getKeyName()) ) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_RESOURCE_DELETE_ERROR); 
		}
		
		/**删除堆栈*/
		awsStack.setStackName(tmplUseApply.getStackName());
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
	public Result instanceImageMake(String tmplId, String applyId, String instanceId, String imageDescribe) {
		/**获取申请信息*/
		TmplUseApply tmplUseApply = getTmplUseApplyInfo(tmplId, applyId);
		
		//判断申请信息是否存在
		if ( tmplUseApply == null) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_REVIEW_REFUSE); 
		}
		
		//判断是否资源已成功创建 
		if ( !Common.TEMPLATE_STACK_STATE_CREATE_COMPLETE.equals(tmplUseApply.getStackState())) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_RESOURCE_DELETE_ERROR_NO_EXISTS); 
		}
		
		//判断堆栈信息是否存在
		AwsStack awsStack = tmplUseApply.getAwsStack(); 
		if (awsStack == null) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_RESOURCE_DELETE_ERROR); 
		}
		
		/**创建镜像*/
		String imageName = Common.TEMPLATE_INSTANCE_AMI_NAME_HEAD + UUIDUtils.getUUID() ;
		String imageId =amazonEC2Service.createImage(awsStack,instanceId, imageName);
		if ("".endsWith(imageId)) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_INSTANCE_AMI_CREATE_ERROR); 
		}
		
		/**插入镜像信息*/
		InstanceImage instanceImage = new InstanceImage();
		instanceImage.setId(UUIDUtils.getUUID());
		instanceImage.setInstanceId(instanceId);//实例ID
		instanceImage.setImageId(imageId);//镜像ID
		instanceImage.setImageName(imageName);//镜像名称
		instanceImage.setImageDescribe(imageDescribe);//镜像描述
		instanceImage.setImageState(Common.STATE_ACTIVE);
		instanceImage.setOperator(ShiroUtil.getCurrentUserId());
		instanceImage.setOptTime(TimeUtils.currentTime());
		instanceImageDao.insertTmplInstanceImage(instanceImage);
		
		/**返回消息*/
		return ResultUtil.getResult(true, TmplMessage.TMPLATE_INSTANCE_AMI_CREATE_SUCCESS, imageId); 
	}
	
	
	/**
	 * 获取实例信息
	 * @author weixin
	 * @version 2018年4月11日
	 * @param instances
	 * @param applyId
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
	 * 设置密钥参数
	 * @author weixin
	 * @version 2018年4月11日
	 */
	public  static void setStackParameters(AwsStack awsStack, String KeyName){
		List<Parameter> parameters = new ArrayList<Parameter>();
		Parameter keypair = new Parameter().withParameterKey("KeyName").withParameterValue(KeyName);
		parameters.add(keypair);
		awsStack.setParameters(parameters);
	}
	
	/**
	 * 设置堆栈标签
	 * @author weixin
	 * @version 2018年4月12日
	 * @param awsStack
	 */
	public static void setStackTags(AwsStack awsStack) {
		List<Tag> tags = new ArrayList<Tag>();
		Tag tag = new Tag().withKey(Common.TEMPLATE_TEACHER_TAG_KEY_SCHOOL).withValue(awsStack.getSchool());//学校
		tags.add(tag);
		tag = new Tag().withKey(Common.TEMPLATE_TEACHER_TAG_KEY_DEPARTMENT).withValue(awsStack.getDepartment());//院系
		tags.add(tag);
		tag = new Tag().withKey(Common.TEMPLATE_TEACHER_TAG_KEY_REALNAME).withValue(awsStack.getRealName());//真实姓名
		tags.add(tag);
		awsStack.setTags(tags);
	}
	
	/**
	 * 获取任务对象
	 * @author weixin
	 * @version 2018年4月12日
	 * @param jobName
	 * @param JobGroup
	 * @param jobClass
	 * @return
	 */
	public static QuartzJob getQuartzJob(String job, String JobGroup, String jobClass) {
		QuartzJob quartzJob = new QuartzJob();
		quartzJob.setJobId(UUIDUtils.getUUID());
		quartzJob.setJobName(job);//任务名
		quartzJob.setJobGroupName(JobGroup);
		quartzJob.setTriggerName(job);//触发器名
		quartzJob.setTriggerGroupName(JobGroup);
		quartzJob.setJobStartTime(TimeUtils.currentTime());//启动任务时间
		quartzJob.setJobClass(jobClass);
		quartzJob.setJobState(Common.STATE_ACTIVE);
		return quartzJob;
	}
	
	/**
	 * CH version 2.0 修改查询的方法，添加查询条件
	 * 获取申请使用信息
	 * @author weixin
	 * @version 2018年4月12日
	 * @param tmplId
	 * @param applyId
	 * @return
	 */
	public TmplUseApply getTmplUseApplyInfo(String tmplId, String applyId) {
		/**查询信息*/
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("applyId", applyId);
		condition.put("reviewState", Common.TEMPLATE_USE_APPLY_STATE_REVIEW_PASS);
		TmplUseApply tmplUseApply = tmplReviewDao.getTmplUseApplyInfo(condition);

		 String userId =ShiroUtil.getCurrentUserId();
		 User user =new User();
		 user.setUserId(userId);
		 user =userService.selectIdUserData(user);
		/**获取查询AWS堆栈相关信息*/
		condition.clear();
		condition.put("tmplId", tmplId);//模板ID
		condition.put("userId",user.getUserId());//当前用户ID
		condition.put("userState", Common.STATE_ACTIVE);//可用状态
		condition.put("useType",AWS_ACCOUNTPOOL_USETYPE);//账号的运用类型
		condition.put("iamKind",AWS_IAM_IAMKIND);//IAM账号为实验者使用
		condition.put("departmentId",user.getDepartmentId());//当前用户的区域ID
		condition.put("isActive",AWS_ACCOUNTPOOL_ISACTIVE);//账号池账号被激活的状态
		condition.put("isDelete",AWS_ACCOUNTPOOL_ISDELETE);//账号池账号删除标志
		condition.put("accountStause",AWS_ACCOUNT_ACCOUNTSTAUSE);//付款账号的删除状态
		condition.put("accountActive",AWS_ACCOUNT_ISACTIVE);//付款账号的激活状态
		AwsStack awsStack=  baseDao.getAwsStackInfo(condition);
		tmplUseApply.setAwsStack(awsStack);
		
		/**信息返回*/
		return tmplUseApply;
	}
	
	/**
	 * 获取模板堆栈相关信息
	 * @author weixin
	 * @version 2018年4月9日
	 * @param condition
	 * @return
	 */
	public AwsStack getAwsStackInfo(Map<String,Object> condition){
		return baseDao.getAwsStackInfo(condition);
	}
}
