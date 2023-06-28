package com.awslabplatform_admin.service.resourceReview.impl;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awslabplatform_admin.common.Common;
import com.awslabplatform_admin.common.message.TmplMessage;
import com.awslabplatform_admin.dao.resourceReview.TmplReviewDao;
import com.awslabplatform_admin.dao.template.TemplateDao;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Result;
import com.awslabplatform_admin.entity.Template;
import com.awslabplatform_admin.entity.TmplReivewRecord;
import com.awslabplatform_admin.entity.TmplUseApply;
import com.awslabplatform_admin.entity.User;
import com.awslabplatform_admin.service.resourceReview.TmplReviewService;
import com.awslabplatform_admin.util.ResultUtil;
import com.awslabplatform_admin.util.ShiroUtil;
import com.awslabplatform_admin.util.TimeUtils;
import com.awslabplatform_admin.util.UUIDUtils;

import static com.awslabplatform_admin.common.Common.ROLE_TYPE_PLATFORM;

/**
 * 模板审核service实现
 * @author weixin
 * @version 2018年4月4日
 */
@Service("TmplReviewService")
public class TmplReviewServiceImpl  implements TmplReviewService {
    /**模板DAO*/
    @Autowired
    private  TemplateDao  templateDao;
    
    /**模板审核DAO*/
    @Autowired
    private  TmplReviewDao  tmplReviewDao;
	
	@Override
	public Result addTmplUseApply(TmplUseApply tmplUseApply) {
		String applicant = ShiroUtil.getCurrentUserId();
//		String tmplId = tmplUseApply.getTmplId();
		/*模板重复申请校验*/
//		if (!existsUseApply(applicant , tmplId, Common.TEMPLATE_USE_APPLY_STATE_REVIEW_WAIT)) {
//			return ResultUtil.getResult(true, TmplMessage.TMPLATE_USE_APPLY_FAILURE);
//		}
		
		String applyId =  UUIDUtils.getUUID();
		String currentTime = TimeUtils.currentTime();
		
		/*查询模板信息*/
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("tmplId", tmplUseApply.getTmplId());
		condition.put("state", Common.STATE_ACTIVE);
		Template tmpl = templateDao.selectByCondition(condition);
		if (tmpl == null) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_NO_EXISTS);
		}
		
		/*插入模板试用申请*/
		tmplUseApply.setApplyId(applyId);//申请ID
		tmplUseApply.setApplicant(applicant);//设置申请人
		tmplUseApply.setApplyTime(currentTime);//申请时间
		tmplUseApply.setTotalPrice(tmpl.getTmplPrice().multiply(new BigDecimal(tmplUseApply.getUseTimeLength())));//计算总消费
		tmplUseApply.setReviewState(Common.TEMPLATE_USE_APPLY_STATE_REVIEW_WAIT);//设置审核状态为等待审核
		tmplReviewDao.insertTmplUseApply(tmplUseApply);
		
		/*插入流程记录*/
		TmplReivewRecord reivewRecord = new TmplReivewRecord();//创建审核流程记录对象
		reivewRecord.setRecordId(UUIDUtils.getUUID());//记录ID
		reivewRecord.setApplyId(applyId);//申请ID
		reivewRecord.setOperation(Common.TEMPLATE_USE_APPLY_STATE_REVIEW_WAIT);//流程当前状态
		reivewRecord.setOperator(applicant);//当前操作人
		reivewRecord.setOptTime(currentTime);//当前操作时间
		reivewRecord.setRemark(tmplUseApply.getRemark());//备注
		tmplReviewDao.insertTmplReviewRecord(reivewRecord);
		/*结果返回*/
		return ResultUtil.getResult(true, TmplMessage.TMPLATE_USE_APPLY_SUCCESS);
	}

	/**
	 * 校验是否重复申请
	 * @author weixin
	 * @version 2018年4月4日
	 * @param applicant
	 * @param tmplId
	 * @param state
	 * @return
	 */
	public Boolean existsUseApply(String applicant, String tmplId, Integer state){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("applicant", applicant);//设置申请人
		condition.put("tmplId", tmplId);//设置模板ID
		condition.put("state", state);//设置状态
		/*查询该模板申请数量*/
		if  (tmplReviewDao.getUseApplyTotal(condition) > 0) {
			return false;
		}
		return true;
	}

	/**
	 * 查询模板申请列表
	 */
	@Override
	public PageInfo listTmplUseApplyInfo(Integer reviewState, String keyword, int start, int length,int draw) {
		/*查询条件*/
		Map<String,Object> condition  = new HashMap<String, Object>();
		User user = ShiroUtil.getCurrentUser();
		condition.put("keyword", keyword);//关键词
		condition.put("applicant", user.getUserId());//获取当前用户ID
		condition.put("state", Common.STATE_ACTIVE);//模板状态
		condition.put("reviewState", reviewState);//审核状态
		/*CH version   判断是不是平台管理员*/
		if(user.getRoleType().equals(ROLE_TYPE_PLATFORM)){
			user.setDepartmentId(null);
		}
		condition.put("departmentId", user.getDepartmentId());//获取院系ID
		/*分页对象*/
		PageInfo pageInfo = new PageInfo(start, length, draw);
		//查询数据
		return getUserApplyTbInfo(condition, pageInfo);
	}
	
	/**
	 * 查询模板申审核列表
	 */
	@Override
	public PageInfo listTmplReviewInfo(Integer reviewState, String keyword, int start, int length,int draw) {
		/*查询条件*/
		Map<String,Object> condition  = new HashMap<String, Object>();
		User user = ShiroUtil.getCurrentUser();
		condition.put("keyword", keyword.trim());//关键词
		condition.put("state", Common.STATE_ACTIVE);//模板状态
		condition.put("reviewState", reviewState);//审核状态
		/*CH version   判断是不是平台管理员*/
		if(user.getRoleType().equals(ROLE_TYPE_PLATFORM)){
			user.setDepartmentId(null);
		}
		condition.put("departmentId", user.getDepartmentId());//获取院系ID
		/*分页对象*/
		PageInfo pageInfo = new PageInfo(start, length, draw);
		//查询数据
		return getUserApplyTbInfo(condition, pageInfo);
	}
	
	/**
	 * 查询申请列表分页数据库
	 * @author weixin
	 * @version 2018年4月8日
	 * @param condition
	 * @param pageInfo
	 * @return
	 */
	public PageInfo getUserApplyTbInfo(Map<String,Object> condition,PageInfo pageInfo ) {
		pageInfo.setCondition(condition);
		/*查询分页数据*/
		pageInfo.setData(tmplReviewDao.listTmplUseApplyTbData(pageInfo));
		/*查询总数*/
		int total = tmplReviewDao.getTmplUseApplyTbTotal(pageInfo);
		pageInfo.setRecordsTotal(total);
		pageInfo.setRecordsFiltered(total);//过滤记录数，dataTable必要参数
		return pageInfo;
	}
	
	/**
	 * 获取模板申请信息
	 */
	@Override
	public TmplUseApply getTmplUseApplyInfo(String applyId) {
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("applyId", applyId);//申请ID
		return tmplReviewDao.getTmplUseApplyInfo(condition);
	}

	/**
	 * 添加模板审核记录
	 */
	@Override
	public Result addTmplReivew(TmplReivewRecord reivewRecord) {
		String applyId = reivewRecord.getApplyId();
		
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("applyId", applyId);//申请ID
		condition.put("state", Common.TEMPLATE_USE_APPLY_STATE_REVIEW_WAIT);
		if (tmplReviewDao.getUseApplyTotal(condition) != 1) {
			/*返回结果*/
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_REVIEW_FAILURE);
		}
		
		/*更新申请记录*/
		TmplUseApply tmplUseApply = new TmplUseApply();
		tmplUseApply.setApplyId(applyId);//申请ID
		tmplUseApply.setReviewState(reivewRecord.getOperation());//状态
		tmplReviewDao.updateTmplUseApply(tmplUseApply);
		/*插入流程记录*/
		reivewRecord.setRecordId(UUIDUtils.getUUID());//设置记录ID
		reivewRecord.setOperator(ShiroUtil.getCurrentUserId());//当前登录者用户ID
		reivewRecord.setOptTime(TimeUtils.currentTime());//当前操作时间
		tmplReviewDao.insertTmplReviewRecord(reivewRecord);
		
		/*返回结果*/
		return ResultUtil.getResult(true, TmplMessage.TMPLATE_REVIEW_SUCCESS);
	}

	/**
	 * 模板测试重新申请
	 */
	@Override
	public Result updateTmplUseApply(TmplUseApply tmplUseApply) {
		String applicant = ShiroUtil.getCurrentUserId();		
		String currentTime = TimeUtils.currentTime();
		
		/*查询模板信息*/
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("tmplId", tmplUseApply.getTmplId());
		condition.put("state", Common.STATE_ACTIVE);
		Template tmpl = templateDao.selectByCondition(condition);
		if (tmpl == null) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_NO_EXISTS);
		}
		
		/*插入模板试用申请*/
		tmplUseApply.setApplyTime(currentTime);//申请时间
		tmplUseApply.setTotalPrice(tmpl.getTmplPrice().multiply(new BigDecimal(tmplUseApply.getUseTimeLength())));//计算总消费
		tmplUseApply.setReviewState(Common.TEMPLATE_USE_APPLY_STATE_REVIEW_WAIT);//设置审核状态为等待审核
		tmplReviewDao.updateTmplUseApply(tmplUseApply);
		
		/*插入流程记录*/
		TmplReivewRecord reivewRecord = new TmplReivewRecord();//创建审核流程记录对象
		reivewRecord.setRecordId(UUIDUtils.getUUID());//记录ID
		reivewRecord.setApplyId(tmplUseApply.getApplyId());//申请ID
		reivewRecord.setOperation(Common.TEMPLATE_USE_APPLY_STATE_REVIEW_WAIT);//流程当前状态
		reivewRecord.setOperator(applicant);//当前操作人
		reivewRecord.setOptTime(currentTime);//当前操作时间
		reivewRecord.setRemark(tmplUseApply.getRemark());//备注
		tmplReviewDao.insertTmplReviewRecord(reivewRecord);
		/*结果返回*/
		return ResultUtil.getResult(true, TmplMessage.TMPLATE_USE_APPLY_SUCCESS);
	}

}
