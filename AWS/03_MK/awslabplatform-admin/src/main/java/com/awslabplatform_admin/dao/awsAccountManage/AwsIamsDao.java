package com.awslabplatform_admin.dao.awsAccountManage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.awslabplatform_admin.dao.base.BaseDao;
import com.awslabplatform_admin.entity.AwsIams;
import com.awslabplatform_admin.entity.PageInfo;

/**
 * AWSIAM DAO
 * @author wy
 *
 */
public interface AwsIamsDao extends BaseDao<AwsIams, String>{
	
	/**
	 * 根据表格选中的行获取的ID，来获取IAM User账户数据信息 
	 * @param awsIams
	 * @return AWS 2.0
	 */
	List<AwsIams> selectAwsIamData(AwsIams awsIams);
	
	
	/**
	 * 获取AWS IAM的数据，将数据显示在表格上
	 * @param pageInfo
	 */
	List<AwsIams> getAwsIamData(PageInfo pageInfo);

	/**
	 * 查询AWS IAM数据的数量
	 * @param pageInfo
	 */
	int countAwsIams(PageInfo pageInfo);
	
	/**
	 * 假删除AWS IAM数据的数据信息
	 * @param id
	 */
	boolean deleteAwsIamData(@Param("id")String id);
	
	/**
	 * 查询添加IAMName名称与awsAccount是否已经存在
	 * @param aWSIam
	 */
	int countAwsIamtData(AwsIams awsIams);

	/**
	 * CH version 2.0
	 * 根据条件获取AWS iAM的数据，将数据显示在编辑的页面上
	 * @param departmentId 院校ID
	 * @param useType 运用类型 （不登录控制台）
	 * @param IamKind 使用人员类型（实验）
	 * @param userState 用户状态（激活）
	 * @param accountStause 付款账号删除状态
	 * @param accountActive 付款账号激活状态
	 * @return
	 */
	AwsIams selectIdAwsIamData(@Param("departmentId") String departmentId, @Param("useType")String useType,
							   @Param("IamKind") String IamKind,
							   @Param("userState") String userState,@Param("isActive") String isActive,
							   @Param("isDelete")String isDelete,@Param("accountStause") String accountStause,@Param("accountActive") String accountActive);
	
	/**
	 * 编辑是判断数据库的数量是否存在
	 */
	int countEditAwsIamData(PageInfo pageInfo);
	
	/**
	 * 根据条件accountId和可用IAM来获取获取AWS IAM的数据 
	 * @param awsIams
	 */
	List<AwsIams> findByIAMData(AwsIams awsIams);
	/**
	 * 查找平台管理员的IAM用于定时导入账单
	 * @return AwsIams
	 */
	AwsIams getPlatformIAM();
}
