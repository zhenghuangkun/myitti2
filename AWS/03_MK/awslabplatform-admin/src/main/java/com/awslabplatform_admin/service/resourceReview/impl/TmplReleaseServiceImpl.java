package com.awslabplatform_admin.service.resourceReview.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awslabplatform_admin.common.Common;
import com.awslabplatform_admin.common.message.TmplMessage;
import com.awslabplatform_admin.dao.resourceReview.TmplReleaseDao;
import com.awslabplatform_admin.dao.template.TmplMakingDao;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Result;
import com.awslabplatform_admin.entity.Template;
import com.awslabplatform_admin.entity.TmplReleaseRecord;
import com.awslabplatform_admin.entity.User;
import com.awslabplatform_admin.service.resourceReview.TmplReleaseService;
import com.awslabplatform_admin.util.ResultUtil;
import com.awslabplatform_admin.util.ShiroUtil;
import com.awslabplatform_admin.util.TimeUtils;
import com.awslabplatform_admin.util.UUIDUtils;

import static com.awslabplatform_admin.common.Common.ROLE_TYPE_PLATFORM;

/**
 * 模板发布Impl 
 * @author weix
 * @date 2018年5月17日
 */
@Service("TmplReleaseService")
public class TmplReleaseServiceImpl  implements TmplReleaseService {
    
    /**模板发布审核DAO*/
    @Autowired
    private  TmplReleaseDao  tmplReleaseDao;
    
    /**模板制作dao*/
    @Autowired
    private TmplMakingDao tmplMakingDao;

	@Override
	public PageInfo listTmplReleaseInfo(Integer reviewState, String keyword,
			int start, int length, int draw) {
		/*查询条件*/
		Map<String,Object> condition  = new HashMap<String, Object>();
		User user = ShiroUtil.getCurrentUser();
		condition.put("keyword", keyword.trim());//关键词
		condition.put("state", Common.STATE_ACTIVE);//模板状态
		condition.put("releaseReviewState", reviewState);//审核状态
		/*CH version   判断是不是平台管理员*/
		if(user.getRoleType().equals(ROLE_TYPE_PLATFORM)){
			user.setDepartmentId(null);
		}
		condition.put("departmentId", user.getDepartmentId());//获取院系ID
		/*分页对象*/
		PageInfo pageInfo = new PageInfo(start, length, draw);
		//查询数据
		return getReleaseTbInfo(condition, pageInfo);
	}
	
	/**
	 * 查询申请列表分页数据库
	 * @author weixin
	 * @version 2018年4月8日
	 * @param condition
	 * @param pageInfo
	 * @return
	 */
	public PageInfo getReleaseTbInfo(Map<String,Object> condition,PageInfo pageInfo ) {
		pageInfo.setCondition(condition);
		/*查询分页数据*/
		pageInfo.setData(tmplReleaseDao.listTmplReleaseTbData(pageInfo));
		/*查询总数*/
		int total = tmplReleaseDao.getTmplReleaseTbTotal(pageInfo);
		pageInfo.setRecordsTotal(total);
		pageInfo.setRecordsFiltered(total);//过滤记录数，dataTable必要参数
		return pageInfo;
	}

	/**
	 * 获取模板发布审核信息
	 * @author weix
	 * @date 2018年5月20日  
	 * @param tmplId
	 * @return
	 */
	@Override
	public Template getTmplReleaseReviewInfo(String tmplId) {
		Map<String, Object> condition=  new HashMap<String, Object>();
		condition.put("tmplId", tmplId);//设置模板名称
		return tmplReleaseDao.getTmplReleaseReviewInfo(condition);
	}
	
	/**
	 * 获取模板发布记录
	 * @author weix
	 * @date 2018年5月16日  
	 * @param tmplId
	 * @return
	 */
	@Override
	public List<TmplReleaseRecord> listReleaseRecordInfo(String tmplId) {
		return tmplReleaseDao.listReleaseRecordInfo(tmplId);
	}
	
	/**
	 * 模板发布审核
	 * @author weix
	 * @date 2018年5月21日  
	 * @param tmplReleaseRecord
	 * @return
	 */
	@Override
	public Result addTmplReleaseReivew(TmplReleaseRecord tmplReleaseRecord) {
		
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("tmplId", tmplReleaseRecord.getTmplId());
		condition.put("state", Common.STATE_ACTIVE);
		Template retTmpl = tmplMakingDao.getTemplateBaseInfo(condition);
		
		//模板是否存在
		if (retTmpl == null) {
			return ResultUtil.getResult(false, TmplMessage.TMPLATE_NO_EXISTS);
		}
		
		/**
		 * 添加模板发布记录
		 */
		String currentTime = TimeUtils.currentTime();
		tmplReleaseRecord.setRecordId(UUIDUtils.getUUID());//记录ID
		tmplReleaseRecord.setOperator(ShiroUtil.getCurrentUserId());//操作人
		tmplReleaseRecord.setReleaseRange(retTmpl.getReleaseRange());//范围
		tmplReleaseRecord.setReleaseDept(retTmpl.getReleaseDept());//院系
		tmplReleaseRecord.setOptTime(currentTime);//时间
		tmplReleaseDao.addTmplReleaseRecord(tmplReleaseRecord);
		
		/**
		 * 更新模板
		 */
		Template tmpl = new Template();
		tmpl.setTmplId(tmplReleaseRecord.getTmplId());//模板ID
		tmpl.setReleaseReviewState(tmplReleaseRecord.getOperation());//操作
		tmpl.setReleaseReviewTime(currentTime);
		tmplMakingDao.updateTmpl(tmpl);
		return ResultUtil.getResult(true, TmplMessage.TMPLATE_RELEASE_REVIEW_SUCCESS);
	}

}
