package com.awslabplatform_admin.service.template;

import java.util.List;

import com.awslabplatform_admin.entity.InstanceImage;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Result;
import com.awslabplatform_admin.entity.Template;
import com.awslabplatform_admin.entity.TmplReleaseRecord;


/**
 * 模板制作service
 * @author weix
 * @date 2018年4月15日
 */
public interface TmplMakingService{
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
	PageInfo listTmplInstanceTb(Integer start, Integer length, Integer draw, String keyword);
	
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
	PageInfo listTmplInstanceImageTb(Integer start, Integer length, Integer draw, String keyword);
	
	
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
	PageInfo listTmplmakingTb(Integer start, Integer length, Integer draw, String keyword);
	
	
	/**
	 * 更新镜像
	 * @author weix
	 * @date 2018年4月15日  
	 * @param page
	 * @param pageSize
	 * @param sort
	 * @param keyword
	 * @return
	 */
	Result updateInstanceImage(InstanceImage instanceImage);

	/**
	 * 删除镜像
	 * @author weix
	 * @date 2018年4月15日  
	 * @param page
	 * @param pageSize
	 * @param sort
	 * @param keyword
	 * @return
	 */
	Result deleteInstanceImage(String imageId);
	
	/**
	 * 增加模板
	 * @author weixin
	 * @version 2018年4月16日
	 * @param tmpl
	 * @return
	 */
	Result addTemplate(Template tmpl);
	
	/**
	 * 更新模板
	 * @author weixin
	 * @version 2018年4月16日
	 * @param tmpl
	 * @return
	 */
	Result updateTemplate(Template tmpl);
	
	/**
	 * 删除模板
	 * @author weixin
	 * @version 2018年4月16日
	 * @param tmpl
	 * @return
	 */
	Result deleteTemplate(String  tmplId);
	
	/**
	 * 获取模板信息
	 * @author weixin
	 * @version 2018年4月16日
	 * @param tmplId
	 * @return
	 */
	Template getTemplateInfo(String tmplId);
	
	/**
	 * 查询所有template
	 */
	List<Template> getTemplateList();
	
	/**
	 * 查询模板制作信息
	 * @author weix
	 * @date 2018年5月6日  
	 * @param tmplId 模板ID
	 * @return
	 */
	Template getTemplateMakeInfo(String tmplId);
	
	/**
	 * 发布模板
	 * @author weix
	 * @date 2018年5月14日  
	 * @param tmpl 
	 * @return
	 */
	Result tmplReleaseApply(TmplReleaseRecord tmplRecord);
	
	/**
	 * 模板发布、取消发布
	 * @author weix
	 * @date 2018年5月22日  
	 * @param tmplId
	 * @param state
	 * @return
	 */
	Result tmplRelease(String tmplId, Integer state);
	
	/**
	 * 获取模板详细信息
	 * @author weix
	 * @date 2018年5月27日  
	 * @param tmplId
	 * @return
	 */
	Template getTemplateDetailInfo(String tmplId); 
	
}
