package com.awslabplatform_admin.service.billing.impl;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.awslabplatform_admin.dao.awsAccountManage.AwsIamsDao;
import com.awslabplatform_admin.dao.billing.BillingDao;
import com.awslabplatform_admin.dao.billing.ImportBillDao;
import com.awslabplatform_admin.entity.AwsIams;
import com.awslabplatform_admin.entity.Billing;
import com.awslabplatform_admin.entity.QuotaAllocation;
import com.awslabplatform_admin.service.amazonaws.impl.S3ServiceImpl;
import com.awslabplatform_admin.service.base.impl.BaseServiceImpl;
import com.awslabplatform_admin.service.billing.BillingService;


/**
 * 账单Billingservice 接口实现类
 *
 * @author wy
 */
@Service("BillingService")
public class BillingServiceImpl extends BaseServiceImpl<BillingDao, Billing, String> implements BillingService {
	@Autowired
	private ImportBillDao  importBillDao;
	
	@Autowired
	private AwsIamsDao awsIamsDao;
	/**
	 * 获取月份账单
	 */
	@Override
	public Billing getMonthBill(Map<String, Object> map) {
		return baseDao.selMonthBill(map);
	}
	/**
	 * 定时每天4点导入最新账单
	 */
	@Scheduled(cron="0 55 23 * * ?")   //每天23:55点执行一次
	@Override
	public void importBill() {
		AwsIams platformIAM = awsIamsDao.getPlatformIAM();
		File billFile = new S3ServiceImpl().getBillFile(platformIAM);
		importBillDao.importBill(billFile.getPath());
		billFile.delete();
	}
	
	/**
	 * 查询各个院系每个月份总额度 和已花费金额 和已开发票金额
	 */
//	@Override
//	public Billing getDepartmentsCost(Map<String, Object> map) {
//		return baseDao.departmentsCost(map);
//	}
//	
	/**
	 * 查询每个院系的总额度和已开发票金额 
	 */
	@Override
	public QuotaAllocation getACombination(Map<String, Object> map) {
		return baseDao.aCombination(map);
	}
	
	/**
	 * 修改总额度
	 */
	@Override
	public boolean insertTotalamount(QuotaAllocation quotaAllocation) {
		return baseDao.insertTotalamount(quotaAllocation);
	}
	
	/**
	 * 修改已开发票金额
	 */
	@Override
	public boolean updateInvoice( Map<String,Object> map) {
		return baseDao.updateInvoice(map);
	}

	



	


}
