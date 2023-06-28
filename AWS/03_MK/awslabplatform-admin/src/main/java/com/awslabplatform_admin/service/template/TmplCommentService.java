package com.awslabplatform_admin.service.template;

import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Result;
import com.awslabplatform_admin.entity.TmplComment;
import com.awslabplatform_admin.service.base.BaseService;


/**
* 模板评论Service层
*
* @author  weix
* @version 2018-4-2
*/
public interface TmplCommentService extends BaseService<TmplComment, String> {
	/**
	 * 添加模板评论
	 * @author weixin
	 * @version 2018年4月2日
	 * @param tmplId
	 * @return
	 */
	Result addTmplComment(TmplComment tmplComment);
	
	/**
	 * 删除模板评论
	 * @author weixin
	 * @version 2018年4月2日
	 * @param tmplId
	 * @return
	 */
	Result deleteTmplComment(String commentId);
	
	
	/**
	 * 查询模板评论列表
	 * @author weixin
	 * @version 2018年4月2日
	 * @param page
	 * @param tmplId
	 * @return
	 */
	PageInfo  listTmplComment(Integer page, String tmplId);
}
