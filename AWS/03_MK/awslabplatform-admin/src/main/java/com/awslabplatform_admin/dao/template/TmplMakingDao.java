package com.awslabplatform_admin.dao.template;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.awslabplatform_admin.dao.base.BaseDao;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Template;

/**
 * 模板制作DAO接口
 * @author weixin
 * @version 2018-4-11
 */
@Repository("TmplMakingDao")
public interface TmplMakingDao extends BaseDao<Template, String> {
	/**
	 * 插入模板制作信息
	 * @author weixin
	 * @version 2018年4月11日
	 * @param awsInstance
	 * @return
	 */
	int insertTmpl(Template template);
	
	

	/**
	 * 更新模板信息
	 * @author weixin
	 * @version 2018年4月11日
	 * @param awsInstance
	 * @return
	 */
	int updateTmpl(Template template);
	
	/**
	 * 查询模板列表数据
	 * @author weix
	 * @date 2018年4月15日  
	 * @param pageInfo
	 * @return
	 */
	List<Template> listTmplTbData(PageInfo pageInfo);
	
	/**
	 * 查询模板列表总数
	 * @author weix
	 * @date 2018年4月15日  
	 * @param pageInfo
	 * @return
	 */
	int getTmplTbTotal(PageInfo pageInfo);
	
	/**
	 * 查询模板基本信息
	 * @author weixin
	 * @version 2018年4月16日
	 * @param condition
	 * @return
	 */
	Template getTemplateBaseInfo(Map<String, Object> condition);
	
	/**
	 * 查询模板制作信息
	 * @author weixin
	 * @version 2018年4月16日
	 * @param condition
	 * @return
	 */
	Template getTemplateMakeInfo(Map<String, Object> condition);
	
	/**
	 * 查询模板详细信息
	 * @author weix
	 * @date 2018年5月27日  
	 * @param condition
	 * @return
	 */
	Template getTemplateDetailInfo(Map<String, Object> condition);
	
	/**
	 * 查询模板数量
	 * @author weixin
	 * @version 2018年4月16日
	 * @param condition
	 * @return
	 */
	int getTemplateTotal(Map<String, Object> condition);
	
}
