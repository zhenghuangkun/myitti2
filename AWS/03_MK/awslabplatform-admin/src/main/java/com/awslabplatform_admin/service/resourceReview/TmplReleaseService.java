package com.awslabplatform_admin.service.resourceReview;

import java.util.List;

import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Result;
import com.awslabplatform_admin.entity.Template;
import com.awslabplatform_admin.entity.TmplReleaseRecord;


/**
 * 模板发布service
 * @author weix
 * @date 2018年5月17日
 */
public interface TmplReleaseService {
	/**
	 * 查询模板发布列表
	 * @author weix
	 * @date 2018年5月17日  
	 * @param reviewState
	 * @param keyword
	 * @param start
	 * @param length
	 * @param draw
	 * @return
	 */
	PageInfo listTmplReleaseInfo(Integer reviewState, String keyword, int start, int length,int draw);
	
	/**
	 * 获取模板发布审核信息
	 * @author weix
	 * @date 2018年5月20日  
	 * @param tmplId
	 * @return
	 */
	Template getTmplReleaseReviewInfo(String tmplId);
	
	/**
	 * 获取模板发布审核记录
	 * @author weix
	 * @date 2018年5月16日  
	 * @param tmplId
	 * @return
	 */
	List<TmplReleaseRecord> listReleaseRecordInfo(String tmplId);
	
	/**
	 * 模板发布审核
	 * @author weix
	 * @date 2018年5月20日  
	 * @param tmplReleaseRecord
	 * @return
	 */
	Result  addTmplReleaseReivew(TmplReleaseRecord tmplReleaseRecord);
}
