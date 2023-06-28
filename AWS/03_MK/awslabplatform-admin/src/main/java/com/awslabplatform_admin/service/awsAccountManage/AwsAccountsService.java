package com.awslabplatform_admin.service.awsAccountManage;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Sheet;

import org.apache.ibatis.annotations.Param;

import com.awslabplatform_admin.entity.AwsAccounts;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.service.base.BaseService;
import com.awslabplatform_admin.service.mechanismManage.MechanismService;

/**
* AWSAccount的Service层
*
* @author  czy
* @version 2018/3/22
*/
public interface AwsAccountsService extends BaseService<AwsAccounts, String> {
	
	/**
	 * 查询添加AWS Account名称与AccountId是否已经存在
	 * @param aWSAccount
	 */
	int countAwsAccountData(AwsAccounts awsAccounts);
	
	/**
     * 查询分页信息
     * @param pageInfo
     **/
	void getAwsAccountPageInfo(PageInfo pageInfo,MechanismService mechanismService);
	
	/**
	 * 根据条件获取AWS Account的数据，将数据显示在编辑的页面上
	 * @param awsAccounts
	 */
	AwsAccounts selectIdAwsAccountData(AwsAccounts awsAccounts);
	
	/**
	 * 删除AWS Account数据的数据信息
	 * @param id
	 */
	boolean deleteAwsAccountData(@Param("id")String id);
	
	/**
	 * 编辑是判断数据库的数量是否存在
	 */
	int countEditAwsAccountData(PageInfo pageInfo);
	
	/**
	 * 根据条件org和主账号类型来获取获取AWS Account的数据
	 * @param awsAccounts
	 */
	List<AwsAccounts> findByOrgAwsAccountData(AwsAccounts awsAccounts);
	
	/**
     * 查询集合数据用来下拉列表的显示
     */
    List<AwsAccounts> getAllAccountList(AwsAccounts awsAccounts);
    
    /**
     * 根据当前登陆者awsAccount查询出account id
     * @param awsAccounts
     * @return
     */
    AwsAccounts getUserAccount(@Param("awsAccount")String awsAccount);
    
    /**
	 * 表格数据导入进行封装
	 * @param sheet
	 * @param rowCount
	 * @param columnCount
	 * @param mag
	 * @return
	 */
	List<AwsAccounts> insertExcelData(Sheet sheet,MechanismService mechanismService,HashMap<String, Object> mag,HttpServletRequest request);

	/**
	 * 导出批量导入数据的错误数据以Excel的形式列出
	 * @param tableList
	 * @param request
	 */
	void errorExportExcel(List<AwsAccounts> tableList,HttpServletRequest request);
}
