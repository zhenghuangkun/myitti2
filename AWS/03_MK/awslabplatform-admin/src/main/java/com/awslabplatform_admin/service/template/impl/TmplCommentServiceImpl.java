package com.awslabplatform_admin.service.template.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.awslabplatform_admin.common.Common;
import com.awslabplatform_admin.common.message.TmplMessage;
import com.awslabplatform_admin.dao.template.TmplCommentDao;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Result;
import com.awslabplatform_admin.entity.TmplComment;
import com.awslabplatform_admin.service.base.impl.BaseServiceImpl;
import com.awslabplatform_admin.service.template.TmplCommentService;
import com.awslabplatform_admin.util.ResultUtil;
import com.awslabplatform_admin.util.ShiroUtil;
import com.awslabplatform_admin.util.TimeUtils;
import com.awslabplatform_admin.util.UUIDUtils;

/**
 * 模板管理service 接口实现类
 * @author weix
 * @version 2018-3-19
 */
@Service("TmplCommentService")
public class TmplCommentServiceImpl extends BaseServiceImpl<TmplCommentDao, TmplComment, String> implements TmplCommentService {

	/**
	 * 模板评论发表
	 */
	@Override
	public Result addTmplComment(TmplComment tmplComment) {
		tmplComment.setCommentId(UUIDUtils.getUUID());//设置评论ID
		tmplComment.setUserId(ShiroUtil.getCurrentUserId());//设置评论人
		tmplComment.setCommentTime(TimeUtils.currentTime());//设置评论时间
		tmplComment.setState(Common.STATE_ACTIVE);
		baseDao.insertTmplComment(tmplComment);
		return ResultUtil.getResult(true, TmplMessage.TMPLATE_COMMENT_SUCCESS);
	}

	/**
	 * 删除模板评论
	 * @author weixin
	 * @version 2018年4月2日
	 * @param tmplComment
	 * @return
	 */
	@Override
	public Result deleteTmplComment(String commentId) {
		Map<String, Object> condition  = new HashMap<String, Object>();
		condition.put("commentId", commentId);//设置评论ID
		condition.put("state", Common.STATE_DELETE);//设置删除状态
		baseDao.deleteTmplComment(condition);
		return ResultUtil.getResult(true, TmplMessage.TMPLATE_COMMENT_DELETE_SUCCESS);
	}
	
	/**
	 * 查询模板评论
	 */
	@Override
	public PageInfo listTmplComment(Integer page, String tmplId) {
		Map<String, Object> condition=  new HashMap<String, Object>();
		condition.put("tmplId", tmplId);//模板ID
		condition.put("state", Common.STATE_ACTIVE);//查询状态可以用
		PageInfo pageInfo = new PageInfo(page ,  Common.TEMPLATE_COMMENT_PAGE_SIZE, 
				Common.TEMPLATE_COMMENT_SORT_COMMENTTIME, Common.TEMPLATE_COMMENT_SORT_ORDER);//创建分页对象
		pageInfo.setCondition(condition);
		List<TmplComment> tmplList = baseDao.listTmplCommentData(pageInfo);
		pageInfo.setData(tmplList);//获取记录
		pageInfo.setRecordsTotal(baseDao.getTmplCommentTotal(pageInfo));//获取总数
		return pageInfo;
	}
	
}
