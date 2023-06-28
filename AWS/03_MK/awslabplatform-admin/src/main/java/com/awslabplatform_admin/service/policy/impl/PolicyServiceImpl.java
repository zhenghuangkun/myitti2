package com.awslabplatform_admin.service.policy.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awslabplatform_admin.dao.policy.PolicyDao;
import com.awslabplatform_admin.entity.Policy;
import com.awslabplatform_admin.service.policy.PolicyService;
import com.awslabplatform_admin.util.UUIDUtils;

/**
 * 策略接口实现类
 * @author lijf
 * @date 2018年5月24日 下午2:45:20
 */
@Service("policyService")
public class PolicyServiceImpl implements PolicyService {
	@Autowired
	private PolicyDao policyDao;
	@Override
	public void addPolicy(Policy policy) {
		policy.setId(UUIDUtils.getUUID());
		policy.setCreateTime(new Date());
		policyDao.addPolicy(policy);
	}

	@Override
	public void updatePolicy(Policy policy) {
		policyDao.updatePolicy(policy);
	}

	@Override
	public void deletePolicy(String id) {
		policyDao.deletePolicy(id);
	}

	@Override
	public Policy selectPolicy(String id) {
		return policyDao.selectPolicy(id);
	}

	@Override
	public List<Policy> listBycondition(Map<String, Object> condition) {
		Map<String, Object> pram = new HashMap<String,Object>();
        pram.put("condition", condition);
		return policyDao.listBycondition(pram);
	}

}
