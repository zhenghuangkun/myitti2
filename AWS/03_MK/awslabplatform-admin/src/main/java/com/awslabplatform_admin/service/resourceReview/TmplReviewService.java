package com.awslabplatform_admin.service.resourceReview;

import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Result;
import com.awslabplatform_admin.entity.TmplReivewRecord;
import com.awslabplatform_admin.entity.TmplUseApply;


/**
* 模板评论Service层
*
* @author  weix
* @version 2018-4-2
*/
public interface TmplReviewService {
	/**
	 * 模板测试申请
	 * @author weixin
	 * @version 2018年4月4日
	 * @param tmplUseApply
	 * @return
	 */
	Result addTmplUseApply(TmplUseApply tmplUseApply);
	
	/**
	 * 模板测试重新申请
	 * @author weixin
	 * @version 2018年4月8日
	 * @param tmplUseApply
	 * @return
	 */
	Result updateTmplUseApply(TmplUseApply tmplUseApply);
	
	/**
	 * 添加模板审核记录
	 * @author weixin
	 * @version 2018年4月7日
	 * @param reivewRecord
	 * @return
	 */
	Result addTmplReivew(TmplReivewRecord reivewRecord);
	
	/**
	 * 查询模板申请列表
	 * @author weixin
	 * @version 2018年4月4日
	 * @param keyword
	 * @param start
	 * @param length
	 * @param draw
	 * @return
	 */
	PageInfo listTmplUseApplyInfo(Integer reviewState, String keyword,  int start, int length, int draw);
	
	/**
	 * 查询模板审核列表
	 * @author weixin
	 * @version 2018年4月8日
	 * @param reviewState
	 * @param keyword
	 * @param start
	 * @param length
	 * @param draw
	 * @return
	 */
	PageInfo listTmplReviewInfo(Integer reviewState, String keyword, int start, int length,int draw);
	
	/**
	 * 获取模板试用申请信息
	 * @author weixin
	 * @version 2018年4月7日
	 * @param applyId
	 * @return
	 */
	TmplUseApply getTmplUseApplyInfo(String applyId);
	
}
