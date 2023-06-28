package com.awslabplatform_admin.dao.template;

import java.util.List;
import java.util.Map;

import com.awslabplatform_admin.dao.base.BaseDao;
import com.awslabplatform_admin.entity.AwsStack;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Template;
import com.awslabplatform_admin.entity.TmplComment;

/**
 * 模板管理DAO接口
 * @author weixin
 * @version 2018-3-21
 */
public interface TemplateDao extends BaseDao<Template, String> {
	/**
	 * 查询模板缩略图列表数据
	 * @author weixin
	 * @version 2018年3月26日
	 * @param info 
	 * @return
	 */
	List<Template> listTmplThumbnailData(PageInfo info);
	
	/**
	 * 查询推荐模板列表
	 * @author weix
	 * @date 2018年5月24日  
	 * @param info
	 * @return
	 */
	List<Template> listIndexTmplThumbnail(Map<String,Object> condition);

	/**
	 * 查询列表总数
	 * @author weixin
	 * @version 2018年3月26日
	 * @param info
	 * @return
	 */
	int getTmplThumbnailTotal(PageInfo info);
	
	/**
	 * 删除模板
	 * @author weixin
	 * @version 2018年3月26日
	 * @param param
	 */
	void deleteTemplate(Map<String,Object> param);
	
    /**
    *
    * 查询模板个数
    * @param condtion 
    * @return
    **/
   int getTemplateTotal(Map<String,Object> condition);
   
   
	/**
	 * 查询所有模板
	 */
	List<Template> getTemplateList(Map<String,Object> condition);

	/**
	 * 根据ID查找某个Template
	 */
	Template getTemplateById(String tmplId);
	
	/**
	 * 插入模板收藏记录
	 * @author weixin
	 * @version 2018年4月2日
	 */
	void insertTmplCollection(Map<String,Object> tmplCollection);
	
	/**
	 * 删除模板收藏记录
	 * @author weixin
	 * @version 2018年4月2日
	 */
	void deleteTmplCollection(Map<String,Object> tmplCollection);
	
	/**
	 * 查询模板收藏数
	 * @author weixin
	 * @version 2018年4月2日
	 */
	Map<String,Object> getTmplCollection(Map<String,Object> tmplCollection);
	
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
	 * 获取模板信息
	 * @author weixin
	 * @version 2018年4月3日
	 * @param condition
	 * @return
	 */
	Template getTemplateInfo(Map<String, Object> condition);
	
	/**
	 * 获取模板堆栈相关信息
	 * @author weixin
	 * @version 2018年4月9日
	 * @param condition
	 * @return
	 */
	AwsStack getAwsStackInfo(Map<String,Object> condition);
}
