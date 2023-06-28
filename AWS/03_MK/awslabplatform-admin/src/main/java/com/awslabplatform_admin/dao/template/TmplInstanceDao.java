package com.awslabplatform_admin.dao.template;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.awslabplatform_admin.dao.base.BaseDao;
import com.awslabplatform_admin.entity.AwsInstance;
import com.awslabplatform_admin.entity.PageInfo;

/**
 * 模板实例DAO接口
 * @author weixin
 * @version 2018-4-11
 */
@Repository("TmplInstanceDao")
public interface TmplInstanceDao extends BaseDao<AwsInstance, String> {
	/**
	 * 插入模板实例信息
	 * @author weixin
	 * @version 2018年4月11日
	 * @param awsInstance
	 * @return
	 */
	int insertTmplInstance(AwsInstance awsInstance);
	
	/**
	 * 插入模板实例
	 * @author weixin
	 * @version 2018年4月11日
	 * @param awsInstance
	 * @return
	 */
	int insertmplInstanceList(List<AwsInstance> awsInstances);
	
	
	/**
	 * 查询模板实例信息
	 * @author weixin
	 * @version 2018年4月11日
	 * @param info
	 * @return
	 */ 
	List<AwsInstance> listTmplInstance(Map<String,Object> condition);
	

	/**
	 * 更新模板实例
	 * @author weixin
	 * @version 2018年4月11日
	 * @param awsInstances
	 * @return
	 */
	int updateTmplInstances(List<AwsInstance> awsInstances);
	
	/**
	 * 更新模板实例
	 * @author weixin
	 * @version 2018年4月11日
	 * @param awsInstance
	 * @return
	 */
	int updateTmplInstance(AwsInstance awsInstance);
	
	/**
	 * 查询模板实例列表数据
	 * @author weix
	 * @date 2018年4月15日  
	 * @param pageInfo
	 * @return
	 */
	List<AwsInstance> listTmplInstanceTbData(PageInfo pageInfo);
	
	/**
	 * 查询模板实例列表总数
	 * @author weix
	 * @date 2018年4月15日  
	 * @param pageInfo
	 * @return
	 */
	int getTmplInstanceTbTotal(PageInfo pageInfo);
	
}
