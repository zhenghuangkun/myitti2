package com.awslabplatform_admin.dao.template;

import java.util.List;
import java.util.Map;

import com.awslabplatform_admin.dao.base.BaseDao;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.TmplComment;

/**
 * 模板评论DAO接口
 * @author weixin
 * @version 2018-4-2
 */
public interface TmplCommentDao extends BaseDao<TmplComment, String> {
	/**
	 * 插入模板评论
	 * @author weixin
	 * @version 2018年4月2日
	 * @param tmplComment
	 * @return
	 */
	int insertTmplComment(TmplComment tmplComment);
	
	/**
	 * 删除模板评论
	 * @author weixin
	 * @version 2018年4月2日
	 * @param tmplComment
	 * @return
	 */
	void deleteTmplComment(Map<String, Object> condition);
	
	/**
	 * 查询模板评论
	 * @author weixin
	 * @version 2018年4月2日
	 * @param info
	 * @return
	 */
	List<TmplComment> listTmplCommentData(PageInfo info);
	
	/**
	 * 查询模板评论总数
	 * @author weixin
	 * @version 2018年4月2日
	 * @param info
	 * @return
	 */
	int getTmplCommentTotal(PageInfo info);
	
}
