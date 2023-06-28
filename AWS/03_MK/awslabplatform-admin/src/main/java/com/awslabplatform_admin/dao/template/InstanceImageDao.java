package com.awslabplatform_admin.dao.template;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.awslabplatform_admin.dao.base.BaseDao;
import com.awslabplatform_admin.entity.InstanceImage;
import com.awslabplatform_admin.entity.PageInfo;

/**
 * 模板实例DAO接口
 * @author weixin
 * @version 2018-4-11
 */
@Repository("InstanceImageDao")
public interface InstanceImageDao extends BaseDao<InstanceImage, String> {
	/**
	 * 插入模板实例镜像信息
	 * @author weixin
	 * @version 2018年4月15日
	 * @param instanceImage
	 * @return
	 */
	int insertTmplInstanceImage(InstanceImage instanceImage);
	
	
	/**
	 * 查询模板实例镜像信息
	 * @author weixin
	 * @version 2018年4月15日
	 * @param condition
	 * @return
	 */ 
	List<InstanceImage> listTmplInstanceImage(Map<String,Object> condition);
	
	/**
	 * 更新模板镜像
	 * @author weixin
	 * @version 2018年4月15日
	 * @param instanceImage
	 * @return
	 */
	int updateTmplInstanceImage(InstanceImage instanceImage);
	
	
	/**
	 * 查询模板镜像列表数据
	 * @author weix
	 * @date 2018年4月15日  
	 * @param pageInfo
	 * @return
	 */
	List<InstanceImage> listTmplImageTbData(PageInfo pageInfo);
	
	/**
	 * 查询模板镜像列表总数
	 * @author weix
	 * @date 2018年4月15日  
	 * @param pageInfo
	 * @return
	 */
	int getTmplImageTbTotal(PageInfo pageInfo);
	
	/**
	 * 获取用户凭证
	 * @author weix
	 * @date 2018年4月16日  
	 * @param condition
	 * @return
	 */
	InstanceImage getUserCredentialsInfo(Map<String,Object> condition);
	
}
