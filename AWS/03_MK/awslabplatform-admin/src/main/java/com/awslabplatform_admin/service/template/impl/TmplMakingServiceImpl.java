package com.awslabplatform_admin.service.template.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awslabplatform_admin.common.Common;
import com.awslabplatform_admin.common.message.TmplMessage;
import com.awslabplatform_admin.dao.resourceReview.TmplReleaseDao;
import com.awslabplatform_admin.dao.template.InstanceImageDao;
import com.awslabplatform_admin.dao.template.TemplateDao;
import com.awslabplatform_admin.dao.template.TmplInstanceDao;
import com.awslabplatform_admin.dao.template.TmplMakingDao;
import com.awslabplatform_admin.entity.FileInfo;
import com.awslabplatform_admin.entity.InstanceImage;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Result;
import com.awslabplatform_admin.entity.Template;
import com.awslabplatform_admin.entity.TmplReleaseRecord;
import com.awslabplatform_admin.service.amazonaws.AmazonEC2Service;
import com.awslabplatform_admin.service.connmon.FileInfoService;
import com.awslabplatform_admin.service.template.TmplMakingService;
import com.awslabplatform_admin.util.ResultUtil;
import com.awslabplatform_admin.util.ShiroUtil;
import com.awslabplatform_admin.util.TimeUtils;
import com.awslabplatform_admin.util.UUIDUtils;

/**
 * 模板制作service实现
 * @author weix
 * @date 2018年4月15日
 */
@Service("TmplMakingService")
public class TmplMakingServiceImpl implements TmplMakingService {
	
    /**模板实例dao*/
    @Autowired
    private  TmplInstanceDao  tmplInstanceDao;
	
    /**模板镜像dao*/
    @Autowired
    private InstanceImageDao instanceImageDao;
    
    /**模板制作dao*/
    @Autowired
    private TmplMakingDao tmplMakingDao;
    
    /**AWSEC2 service*/
    @Autowired
    private AmazonEC2Service amazonEC2Service;
    
    /**文件信息管理service*/
    @Autowired
    private  FileInfoService  fileInfoService;
    
    /**模板发布dao*/
    @Autowired
    private  TmplReleaseDao  tmplReleaseDao;
    
    /**模板dao*/
    @Autowired
    private  TemplateDao  templateDao;
    
	/**
	 * 查询实例列表
	 * @author weix
	 * @date 2018年4月15日  
	 * @param page
	 * @param pageSize
	 * @param sort
	 * @param keyword
	 * @return
	 */
	@Override
	public PageInfo listTmplInstanceTb(Integer start, Integer length, Integer draw, String keyword) {
		/*查询条件*/
		Map<String,Object> condition  = new HashMap<String, Object>();
		condition.put("keyword", keyword);//关键词
		condition.put("operator", ShiroUtil.getCurrentUserId());//用户ID
		
		/*分页对象*/
		PageInfo pageInfo = new PageInfo(start, length, draw);
		pageInfo.setCondition(condition);
		
		/*查询分页数据*/
		pageInfo.setData(tmplInstanceDao.listTmplInstanceTbData(pageInfo));
		
		/*查询总数*/
		int total = tmplInstanceDao.getTmplInstanceTbTotal(pageInfo);
		pageInfo.setRecordsTotal(total);
		pageInfo.setRecordsFiltered(total);//过滤记录数，dataTable必要参数
		return pageInfo;
	}

	/**
	 * 查询实例镜像列表
	 * @author weix
	 * @date 2018年4月15日  
	 * @param page
	 * @param pageSize
	 * @param sort
	 * @param keyword
	 * @return
	 */
	@Override
	public PageInfo listTmplInstanceImageTb(Integer start, Integer length, Integer draw, String keyword) {
		/*查询条件*/
		Map<String,Object> condition  = new HashMap<String, Object>();
		condition.put("keyword", keyword);//关键词
		condition.put("operator", ShiroUtil.getCurrentUserId());//用户ID
		
		/*分页对象*/
		PageInfo pageInfo = new PageInfo(start, length, draw);
		pageInfo.setCondition(condition);
		
		/*查询分页数据*/
		pageInfo.setData(instanceImageDao.listTmplImageTbData(pageInfo));
		
		/*查询总数*/
		int total = instanceImageDao.getTmplImageTbTotal(pageInfo);
		pageInfo.setRecordsTotal(total);
		pageInfo.setRecordsFiltered(total);//过滤记录数，dataTable必要参数
		return pageInfo;
	}

	/**
	 * 查询模板制作列表
	 * @author weix
	 * @date 2018年4月15日  
	 * @param page
	 * @param pageSize
	 * @param sort
	 * @param keyword
	 * @return
	 */
	@Override
	public PageInfo listTmplmakingTb(Integer start, Integer length, Integer draw, String keyword) {
		/*查询条件*/
		Map<String,Object> condition  = new HashMap<String, Object>();
		condition.put("keyword", keyword);//关键词
		condition.put("tmplFromFlag", Common.TEMPLATE_FROM_FLAG_TEACHER);//来源
		condition.put("operator", ShiroUtil.getCurrentUserId());//用户ID
		
		/*分页对象*/
		PageInfo pageInfo = new PageInfo(start, length, draw);
		pageInfo.setCondition(condition);
		
		/*查询分页数据*/
		pageInfo.setData(tmplMakingDao.listTmplTbData(pageInfo));
		
		/*查询总数*/
		int total = tmplMakingDao.getTmplTbTotal(pageInfo);
		pageInfo.setRecordsTotal(total);
		pageInfo.setRecordsFiltered(total);//过滤记录数，dataTable必要参数
		return pageInfo;
	}
	
	/**
	 * 更新镜像
	 * @author weix
	 * @date 2018年4月16日  
	 * @param imageId
	 * @return
	 */
	@Override
	public Result updateInstanceImage(InstanceImage instanceImage) {
		
		/**更新镜像数据*/
		instanceImage.setOperator(ShiroUtil.getCurrentUserId());//当前用户
		instanceImage.setOptTime(TimeUtils.currentTime());//当前时间
		instanceImageDao.updateTmplInstanceImage(instanceImage);
		return ResultUtil.getResult(true, TmplMessage.TMPLATE_INSTANCE_AMI_UPDATE_SUCCESS);
	}
	
	/**
	 * 删除镜像
	 * @author weix
	 * @date 2018年4月16日  
	 * @param imageId
	 * @return
	 */
	@Override
	public Result deleteInstanceImage(String imageId) {
		/**查询凭证*/
		Map<String,Object> condition  = new HashMap<String, Object>();
		condition.put("userState", Common.STATE_ACTIVE);//状态
		condition.put("iamState", Common.ACCOUNT_STATE_ACTIVE);//状态
		condition.put("userId", ShiroUtil.getCurrentUserId());//用户ID
		InstanceImage instanceImage = instanceImageDao.getUserCredentialsInfo(condition);
		if (instanceImage == null ) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_INSTANCE_AMI_DELETE_FAILURE);
		}
		
		/**删除镜像*/
		instanceImage.setImageId(imageId);
		if (!amazonEC2Service.deleteImage(instanceImage)) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_INSTANCE_AMI_DELETE_FAILURE);
		}
		
		/**更新镜像数据*/
		instanceImage.setImageState(Common.STATE_DELETE);//状态
		instanceImage.setOperator(ShiroUtil.getCurrentUserId());//当前用户
		instanceImage.setOptTime(TimeUtils.currentTime());//当前时间
		instanceImageDao.updateTmplInstanceImage(instanceImage);
		
		return ResultUtil.getResult(true, TmplMessage.TMPLATE_INSTANCE_AMI_DELETE_SUCCESS);
	}
	
	/**
	 * 添加模板
	 */
	@Override
	public Result addTemplate(Template tmpl) {
		/*判断模板数据库是否存在*/
		if ( existsTemplate(false, tmpl) ) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_EXISTS);
		}
		
		//插入文件
		fileInfoService.insertFileInfo(tmpl.getTmplScript());
		
		/*判断模板是否存在*/
		Map<String, Object> condition=  new HashMap<String, Object>();
		condition.put("state", Common.STATE_ACTIVE);//设置模板状态
		condition.put("tmplId", tmpl.getAssociatedTmplId());//设置模板名称
		Template retTmpl = tmplMakingDao.getTemplateBaseInfo(condition);//根据ID查询模板
		
		
		
		//插入模板
		tmpl.setTmplPrice(retTmpl.getTmplPrice());//价格
		tmpl.setTmplType(retTmpl.getTmplType());//类型
		tmpl.setOperator(ShiroUtil.getCurrentUserId());//当前用户ID
		tmpl.setOptTime(TimeUtils.currentTime());//操作时间
		tmpl.setState(Common.STATE_ACTIVE);//状态
		tmpl.setTmplFromFlag(Common.TEMPLATE_FROM_FLAG_TEACHER);//来源标识
		tmplMakingDao.insertTmpl(tmpl);
		
		//返回结果
		return ResultUtil.getResult(true, TmplMessage.TMPLATE_ADD_SUCCESS);
	}

	/**
	 * 更新模板
	 */
	@Override
	public Result updateTemplate(Template tmpl) {
		/*判断模板数据库是否存在*/
		if ( existsTemplate(true, tmpl) ) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_EXISTS);
		}
		/*文件信息入库*/
		FileInfo scriptInfo = tmpl.getTmplScript();
		/*判断是否有更改资源脚本*/
		if (scriptInfo != null) {
			fileInfoService.updateFileInfo(scriptInfo);
		}
		
		/*模板信息入库*/
		tmpl.setOptTime(TimeUtils.currentTime());		/*设置操作时间*/
		tmpl.setOperator(ShiroUtil.getCurrentUserId());  /*设置当前操作者*/
		tmplMakingDao.updateTmpl(tmpl);
		
		//返回结果
		return ResultUtil.getResult(true, TmplMessage.TMPLATE_EDIT_SUCCESS);
	}

	
	
	/**
	 * 删除模板
	 */
	@Override
	public Result deleteTemplate(String tmplId) {
		Template tmpl = new Template();
		tmpl.setTmplId(tmplId);;//设置模板ID
		tmpl.setState(Common.STATE_DELETE);//设置删除状态
		tmpl.setOptTime(TimeUtils.currentTime());//操作时间
		tmpl.setOperator(ShiroUtil.getCurrentUserId());//操作者
		tmplMakingDao.updateTmpl(tmpl);
		//返回结果
		return ResultUtil.getResult(true, TmplMessage.TMPLATE_DELETE_SUCCESS);
	}
	
	/**
	 * 查询模板信息
	 */
	@Override
	public Template getTemplateInfo(String tmplId) {
		Map<String, Object> condition=  new HashMap<String, Object>();
		condition.put("tmplId", tmplId);//模板ID
		condition.put("state", Common.STATE_ACTIVE);//设置模板状态
		return tmplMakingDao.getTemplateMakeInfo(condition);
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
			Template retTmpl = tmplMakingDao.getTemplateBaseInfo(condition);//根据ID查询模板
			if (retTmpl == null) {
				return true;
			}
			/*判断模板名称是否发生变更*/
			if (retTmpl.getTmplName().equals(tmpl.getTmplName())) {
				return false;
			}
		}
		condition.put("tmplName", tmpl.getTmplName());//设置模板名称
		if ( tmplMakingDao.getTemplateTotal(condition) > 0 ) {
			return true;
		} 
		return false;
	}

	
	/**
	 * 查询模板制作信息
	 * @author weix
	 * @date 2018年5月6日  
	 * @param tmplId 模板ID 
	 * @return
	 */
	@Override
	public Template getTemplateMakeInfo(String tmplId) {
		return templateDao.getTemplateById(tmplId);//根据ID查询模板
	}

	/**
	 * 发布模板
	 * @author weix
	 * @date 2018年5月14日  
	 * @param tmplRecord 
	 * @return
	 */
	@Override
	public Result tmplReleaseApply(TmplReleaseRecord tmplReleaseRecord) {
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("tmplId", tmplReleaseRecord.getTmplId());
		condition.put("state", Common.STATE_ACTIVE);
		Template retTmpl = tmplMakingDao.getTemplateBaseInfo(condition);
		//模板是否存在
		if (retTmpl == null) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_NO_EXISTS);
		}
		
		//模板是否已发布
		if (Common.TEMPLATE_RELEASE_TRUE.equals(retTmpl.getReleaseStatus())) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_RELEASE_EXISTS);
		}
		
		//模板发布是否在审核中
		if (Common.TEMPLATE_RELEASE_REVIEW_WAIT.equals(retTmpl.getReleaseReviewState())) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_RELEASE_REVIEW_WAITTING);
		}
		
		/**
		 * 添加模板发布记录
		 */
		String currentTime = TimeUtils.currentTime();
		tmplReleaseRecord.setRecordId(UUIDUtils.getUUID());//记录ID
		tmplReleaseRecord.setOperation(Common.TEMPLATE_RELEASE_REVIEW_WAIT);//状态
		tmplReleaseRecord.setOperator(ShiroUtil.getCurrentUserId());//操作人
		tmplReleaseRecord.setOptTime(currentTime);//时间
		tmplReleaseDao.addTmplReleaseRecord(tmplReleaseRecord);
		
		/**
		 * 更新模板
		 */
		Template tmpl = new Template();
		tmpl.setTmplId(tmplReleaseRecord.getTmplId());//模板ID
		tmpl.setReleaseRange(tmplReleaseRecord.getReleaseRange());//发布范围
		tmpl.setReleaseDept(tmplReleaseRecord.getReleaseDept());//发布院系
		tmpl.setReleaser(ShiroUtil.getCurrentUserId());
		tmpl.setReleaseReviewState(Common.TEMPLATE_RELEASE_REVIEW_WAIT);
		tmpl.setReleaseReviewTime(currentTime);
		tmplMakingDao.updateTmpl(tmpl);
		return ResultUtil.getResult(true, TmplMessage.TMPLATE_RELEASE_APPLY_SUCCESS);
	}

	/**
	 * 模板发布、取消发布
	 * @author weix
	 * @date 2018年5月22日  
	 * @param tmplId
	 * @param state
	 * @return
	 */
	@Override
	public Result tmplRelease(String tmplId, Integer state) {
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("tmplId", tmplId);
		condition.put("state", Common.STATE_ACTIVE);
		Template retTmpl = tmplMakingDao.getTemplateBaseInfo(condition);
		//模板是否存在
		if (retTmpl == null) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_NO_EXISTS);
		}
		
		//模板发布是否审核通过
		if (!Common.TEMPLATE_RELEASE_REVIEW_PASS.equals(retTmpl.getReleaseReviewState())) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_RELEASE_REVIEW_REFUSE);
		}
		
		if (Common.TEMPLATE_RELEASE_TRUE.equals(state)) {
			//模板是否已发布
			if (Common.TEMPLATE_RELEASE_TRUE.equals(retTmpl.getReleaseStatus())) {
				return ResultUtil.getResult(false, TmplMessage.TMPLATE_RELEASE_EXISTS);
			}
		}
		
		/**
		 * 更新模板发布状态并返回消息
		 */
		Template tmpl = new Template();
		tmpl.setTmplId(tmplId);
		tmpl.setReleaser(ShiroUtil.getCurrentUserId());
		tmpl.setReleaseStatus(state);
		
		if (Common.TEMPLATE_RELEASE_TRUE.equals(state)) {
			tmpl.setReleaseTime(TimeUtils.currentTime());
			tmplMakingDao.updateTmpl(tmpl);
			return ResultUtil.getResult(true, TmplMessage.TMPLATE_RELEASE_SUCCESS);
		} else {//取消
			tmpl.setReleaseReviewState(Common.TEMPLATE_RELEASE_REVIEW_NONE);//设置发布审核状态为未审核
			tmpl.setReleaseDept("");
			tmplMakingDao.updateTmpl(tmpl);
			return ResultUtil.getResult(true, TmplMessage.TMPLATE_RELEASE_CANCEL_REFUSE);
		}
		
	}
	
	/**
	 * 模板
	 * @author weix
	 * @date 2018年5月27日  
	 * @return
	 */
	@Override
	public List<Template> getTemplateList() {
		Map<String, Object> condition=  new HashMap<String, Object>();
		condition.put("state", Common.STATE_ACTIVE);//设置模板状态
		condition.put("releaseStatus", Common.TEMPLATE_RELEASE_TRUE);//已发布状态
		condition.put("operator", ShiroUtil.getCurrentUserId());//当前用户
		condition.put("tmplFromFlag", Common.TEMPLATE_FROM_FLAG_TEACHER);//模板来源
		return templateDao.getTemplateList(condition);
	}

	/**
	 * 获取模板详细信息
	 * @author weix
	 * @date 2018年5月27日  
	 * @param tmplId
	 * @return
	 */
	@Override
	public Template getTemplateDetailInfo(String tmplId) {
		Map<String, Object> condition=  new HashMap<String, Object>();
		condition.put("tmplId", tmplId);//模板ID
		condition.put("state", Common.STATE_ACTIVE);//设置模板状态
		return tmplMakingDao.getTemplateDetailInfo(condition);
	}


}
