package com.awslabplatform_admin.dao.billing;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.awslabplatform_admin.dao.base.BaseDao;
import com.awslabplatform_admin.entity.Billing;
import com.awslabplatform_admin.entity.QuotaAllocation;


/**
 * BillingDao DAO
 * @author wy
 *
 */
public interface BillingDao extends BaseDao<Billing, String>{
	
	/**
	 * 获取月份账单
	 * @return
	 */
	Billing selMonthBill(Map<String,Object> map);

	/**
	 * 查询各个院系每个月份总额度 和已花费金额 和已开发票金额
	 * @param map
	 * @return
	 */
	//Billing departmentsCost(Map<String,Object> map);
	
	/**
	 * 查询每个院系的总额度和已开发票金额 
	 * @param map
	 * @return
	 */
	QuotaAllocation aCombination(Map<String,Object> map);
	
	/**
	 * 修改总额度
	 * @param quota
	 * @return
	 */
	boolean  insertTotalamount(QuotaAllocation quotaAllocation);
	
	/**
	 * 修改已开发票金额
	 * @param quota
	 * @return
	 */
	boolean updateInvoice( Map<String,Object> map);
	
}
