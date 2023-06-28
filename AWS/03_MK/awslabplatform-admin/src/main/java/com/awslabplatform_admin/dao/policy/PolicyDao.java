package com.awslabplatform_admin.dao.policy;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.awslabplatform_admin.entity.Policy;

/**
 * 策略Dao
 * @author lijf
 * @date 2018年5月24日 上午10:23:52
 */
public interface PolicyDao{
	/**
	 * 新增一个策略
	 * @param policy
	 */
	void addPolicy(Policy policy);
	/**
	 * 修改一个策略
	 * @param policy
	 */
	void updatePolicy(Policy policy);
	/**
	 * 根据id删除一个策略
	 * @param policy
	 */
	void deletePolicy(@Param("id")String id);
	/**
	 * 根据id查找一个策略
	 * @param policy
	 */
	Policy selectPolicy(@Param("id") String id);
	/**
	 * 查找符合条件的策略
	 * @param policy
	 */
	List<Policy> listBycondition(Map<String, Object> condition);
}
