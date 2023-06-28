package com.awslabplatform_admin.service.billing;

import java.util.Map;

import com.awslabplatform_admin.entity.Billing;
import com.awslabplatform_admin.entity.QuotaAllocation;
import com.awslabplatform_admin.service.base.BaseService;

/**
 * 账单Billingservice
 * @author wy
 *
 */
public interface BillingService extends BaseService<Billing, String> {
	
	/**
	 * 获取月份账单
	 * @return
	 */
	 Billing getMonthBill(Map<String,Object> map);
	 /**
	  * 导入账单到数据库
	  */
	 void importBill();
	 
    /**
	 * 查询各个院系每个月份总额度 和已花费金额 和已开发票金额
	 * @param map
	 * @return
	 */
//	 Billing getDepartmentsCost(Map<String,Object> map);
	 
    /**
	 * 查询每个院系的总额度和已开发票金额 
	 * @param map
	 * @return
	 */
	QuotaAllocation getACombination(Map<String,Object> map);
	
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
