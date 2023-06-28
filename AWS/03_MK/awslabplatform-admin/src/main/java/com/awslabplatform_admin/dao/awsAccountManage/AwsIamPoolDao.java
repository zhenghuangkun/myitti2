package com.awslabplatform_admin.dao.awsAccountManage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.awslabplatform_admin.dao.base.BaseDao;
import com.awslabplatform_admin.entity.AwsIamPool;
import com.awslabplatform_admin.entity.PageInfo;

/**
 * AWSIAMPOOL DAO
 * 2019/03/18
 */
public interface AwsIamPoolDao extends BaseDao<AwsIamPool, String>{
	
	/**
	 * 获取AWSIamPool的数据，将数据显示在表格上
	 * @param pageInfo
	 */
	List<AwsIamPool> getAwsIamPoolData(PageInfo pageInfo);
	
	/**
	 * 查询AWSIamPool数据的数量
	 * @param pageInfo
	 */
	int countAwsIamPool(PageInfo pageInfo);
	
	/**
	 * 查询添加accountID是否已经存在
	 * @param awsIamPool
	 * @return
	 */
	int countAwsIamPoolData(AwsIamPool awsIamPool);
	
	/**
	 * 假删除AWSIamPool数据的数据信息  0：未删除  1：删除
	 * @param id
	 */
	boolean deleteAwsIamPoolData(@Param("id")String id,@Param("isDelete")Integer isDelete);
	
	/**
	 * 根据条件获取AWSIAMPool的数据，将数据显示在编辑的页面上
	 * @param awsIamPool
	 */
	AwsIamPool selectIdAwsIamPoolData(AwsIamPool awsIamPool);

	/**
	 * 编辑是判断数据库的数量是否存在 
	 * @param pageInfo
	 */
	int countEditAwsIamPoolData(PageInfo pageInfo);
	
	/**
	 * 根据对应的付款账户及付款名称的条件获取AWSIAMPool的数据
	 * @param awsIamPool
	 * @return
	 */
	List<AwsIamPool> selectAwsIamPoolData(AwsIamPool awsIamPool);
}
