package com.awslabplatform_admin.service.template;

import com.awslabplatform_admin.entity.AwsStack;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Result;
import com.awslabplatform_admin.entity.Template;
import com.awslabplatform_admin.entity.TmplUseApply;
import com.awslabplatform_admin.service.base.BaseService;

import java.util.List;
import java.util.Map;

/**
* 模板管理Service层
*
* @author  weix
* @version 2018-3-19
*/
public interface TemplateService extends BaseService<Template, String> {
	/**
	 * 插入模板
	 * @param tmpl
	 * @return
	 */
	Boolean insertTemplate(Template tmpl);
	
	/**
	 * 编辑模板
	 * @param tmpl
	 * @return
	 */
	Boolean updateTemplate(Template tmpl);
	
	/**
	 * 查询推荐缩略图列表
	 * @param page 页码
	 * @param sort 排序
	 * @param keyword	搜索关键词
	 * @return	分页数据
	 */
	List<Template> listIndexTmplThumbnail(String keyword);
	
	/**
	 * 查询所有模板缩略图列表
	 * @param page 页码
	 * @param sort 排序
	 * @param keyword	搜索关键词
	 * @return	分页数据
	 */
	PageInfo  listAllTmplThumbnail(Integer page, String sort, String keyword);
	
	/**
	 * 查询模板设计缩略图列表
	 * @param page 页码
	 * @param sort 排序
	 * @param keyword	搜索关键词
	 * @return	分页数据
	 */
	PageInfo  listTmplDesignThumbnail(Integer page, String sort, String keyword);
	
	
	/**
	 * 查询收藏模板缩略图列表
	 * @param page 页码
	 * @param keyword	搜索关键词
	 * @return	分页数据
	 */
	PageInfo  listTmplcollection(Integer page,  String keyword);
	
	
	/**
	 * 删除模板
	 *	@param tmplId 模板ID
	 * @author weixin
	 * @version 2018年3月26日
	 * @return
	 */
	Boolean  deleteTemplate(String tmplId);
	/**
	 * 获取模板
	 * @author weixin
	 * @version 2018年3月27日
	 * @param tmplId 模板ID
	 * @return
	 */
	Template getTemplateInfo(String tmplId);
	/**
	 * 发布模板
	 * @author weixin
	 * @version 2018年3月28日
	 * @param tmplId
	 * @return
	 */
	Boolean releaseTemplate(String tmplId);
	
	/**
	 * 查询所有template
	 */
	List<Template> getTemplateList();

	/**
	 * 通过ID查找某个具体的Template
	 */
	Template getTemplateById(String tmplId);
	
	/**
	 * 模板收藏
	 * @author weixin
	 * @version 2018年4月2日
	 * @param tmplId 模板ID
	 * @return
	 */
	Result tmplCollection(String tmplId);
	
	/**
	 * 模板取消收藏
	 * @author weixin
	 * @version 2018年4月2日
	 * @param tmplId 模板ID
	 * @return
	 */
	Result tmplCollectionCancel(String tmplId);
	
	/**
	 * 获取收藏模板
	 * @author weixin
	 * @version 2018年4月2日
	 * @param tmplId 模板ID
	 * @return
	 */
	Map<String,Object> getTmplCollection(String tmplId);
	
	/**
	 * 启动模板资源
	 * @author weixin
	 * @version 2018年4月9日
	 * @param tmplUseApply
	 * @return
	 */
	Result startTmplResource(TmplUseApply tmplUseApply);
	
	/**
	 * 获取资源创建状态
	 * @author weixin
	 * @version 2018年4月10日
	 * @param tmplUseApply
	 * @return
	 */
	Result getResourceCreateState(TmplUseApply tmplUseApply);
	
	/**
	 * 释放模板资源
	 * @author weixin
	 * @version 2018年4月9日
	 * @param tmplUseApply
	 * @return
	 */
	Result terminationTmplResource(TmplUseApply tmplUseApply);
	
	/**
	 * 镜像制作
	 * @author weixin
	 * @version 2018年4月14日
	 * @param tmplUseApply
	 * @return
	 */
	Result instanceImageMake(String tmplId, String applyId, String instanceId, String imageDescribe);
	
	/**
	 * 获取模板堆栈相关信息
	 * @author weixin
	 * @version 2018年4月9日
	 * @param condition
	 * @return
	 */
	AwsStack getAwsStackInfo(Map<String,Object> condition);
}
