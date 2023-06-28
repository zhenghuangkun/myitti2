package com.awslabplatform_admin.dao.awsAccountManage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.awslabplatform_admin.dao.base.BaseDao;
import com.awslabplatform_admin.entity.AwsAccounts;
import com.awslabplatform_admin.entity.PageInfo;

/**
 * AWSAccount DAO
 * @author czy
 *
 */
public interface AwsAccountsDao extends BaseDao<AwsAccounts, String>{

	/**
	 * 查询添加AWS Account名称与AccountId是否已经存在
	 * @param awsAccounts
	 */
	int countAwsAccountData(AwsAccounts awsAccounts);
	
	/**
	 * 获取AWS Account的数据，将数据显示在表格上
	 * @param pageInfo
	 */
	List<AwsAccounts> getAwsAccountData(PageInfo pageInfo);
	
	/**
	 * 查询AWS Account数据的数量
	 * @param pageInfo
	 */
	int countAwsAccounts(PageInfo pageInfo);
	
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
	
}
