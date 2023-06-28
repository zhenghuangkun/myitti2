package com.awslabplatform_admin.service.policy;

import java.util.List;
import java.util.Map;

import com.awslabplatform_admin.entity.Billing;
import com.awslabplatform_admin.entity.Policy;
import com.awslabplatform_admin.entity.QuotaAllocation;
import com.awslabplatform_admin.service.base.BaseService;

/**
 * 策略Service
 * @author lijf
 * @date 2018年5月24日 下午2:40:15
 */
public interface PolicyService {
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
	void deletePolicy(String id);
	/**
	 * 根据id查找一个策略
	 * @param policy
	 */
	Policy selectPolicy(String id);
	/**
	 * 查找符合条件的策略
	 * @param policy
	 */
	List<Policy> listBycondition(Map<String, Object> condition);

}
