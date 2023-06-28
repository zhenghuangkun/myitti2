package com.awslabplatform_admin.service.awsAccountManage;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Sheet;

import org.apache.ibatis.annotations.Param;

import com.awslabplatform_admin.entity.AwsIamPool;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.service.base.BaseService;
/**
* AwsIamPool的Service层
*
* @version 2019/3/21
*/
public interface AwsIamPoolService extends BaseService<AwsIamPool, String> {
	
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
	 * 添加院系账户及IAM账户
	 * @param awsIamPool
	 */
	void insertAwsIamPoolData(AwsIamPool awsIamPool,AwsIamsService awsIamsService);
	
	/**
     * 查询分页信息
     * @param pageInfo
     **/
	void getAwsIamPoolPageInfo(PageInfo pageInfo);
	
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
	 * 修改院系账户及IAM账户
	 * @param awsIamPool
	 * @param awsIamsService
	 */
	void updateByAwsIamPoolData(AwsIamPool awsIamPool,AwsIamsService awsIamsService);
	
	
	/**
	 * 表格数据导入进行封装
	 * @param sheet
	 * @param rowCount
	 * @param columnCount
	 * @param mag
	 * @return
	 */
	List<AwsIamPool> insertExcelData(Sheet sheet,AwsIamsService awsIamsService,AwsAccountsService awsAccountService,HashMap<String, Object> mag,HttpServletRequest request);
	
	
	/**
	 * 导出批量导入数据的错误数据以Excel的形式列出
	 * @param tableList
	 * @param request
	 */
	void errorExportExcel(List<AwsIamPool> tableList,HttpServletRequest request);
	
	/**
	 * 根据对应的付款账户及付款名称的条件获取AWSIAMPool的数据
	 * @param awsIamPool
	 * @return
	 */
	List<AwsIamPool> selectAwsIamPoolData(AwsIamPool awsIamPool);
}
