package com.awslabplatform_admin.service.awsAccountManage.impl;
import java.util.List;

import org.springframework.stereotype.Service;

import com.awslabplatform_admin.dao.awsAccountManage.AwsIamsDao;
import com.awslabplatform_admin.entity.AwsIams;import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.service.awsAccountManage.AwsIamsService;
import com.awslabplatform_admin.service.base.impl.BaseServiceImpl;
/**
* AwsIams的Service层
*
* @author  wy
* @version 2018/3/22
*/
@Service("AwsIamsService")
public class AwsIamsServiceImpl extends BaseServiceImpl<AwsIamsDao, AwsIams, String> implements AwsIamsService {

	/**
     * 查询分页信息
     * @param pageInfo
     **/
	@Override
	public void getAwsIamPageInfo(PageInfo pageInfo) {
		List<AwsIams> list=baseDao.getAwsIamData(pageInfo);
		/*查询分页数据*/
		pageInfo.setData(list);
		/*查询总数*/
		int total = baseDao.countAwsIams(pageInfo);
		pageInfo.setRecordsTotal(total);
		pageInfo.setRecordsFiltered(total);//过滤记录数，dataTable必要参数
		
	}

	/**
	 * 根据表格选中的行获取的ID，来获取IAM User账户数据信息 
	 * @param awsIams
	 * @return AWS 2.0
	 */
	public List<AwsIams> selectAwsIamData(AwsIams awsIams){
		
		return baseDao.selectAwsIamData(awsIams);
	}
	
	/**
	 * 假删除AWS IAM数据的数据信息
	 * @param id
	 */
	@Override
	public boolean deleteAwsIamData(String id) {
		return baseDao.deleteAwsIamData(id);
	}

	/**
	 * 查询添加IAMName名称与awsAccount是否已经存在
	 * @param awsIams
	 */
	@Override
	public int countAwsIamtData(AwsIams awsIams) {
		return baseDao.countAwsIamtData(awsIams);
	}

	/**
	 * CH version 2.0
	 * 根据条件获取AWS iAM的数据，将数据显示在编辑的页面上
	 * @param departmentId 院系ID
	 * @param useType 运用类型 （不登录控制台）
	 * @param iamKind 使用人员类型（实验）
	 * @param userState 用户状态（激活）
	 * @param accountStause 付款账号删除状态
	 * @param accountActive 付款账号激活状态
	 * @return
	 */
	@Override
	public AwsIams selectIdAwsIamData(String departmentId,String useType,String iamKind,String userState,String isActive,String isDelete,String accountStause,String accountActive) {
		return baseDao.selectIdAwsIamData(departmentId,useType,iamKind,userState,isActive,isDelete,accountStause,accountActive);
	}
	

	/**
	 * 编辑是判断数据库的数量是否存在
	 */
	@Override
	public int countEditAwsIamData(PageInfo pageInfo) {
		return baseDao.countEditAwsIamData(pageInfo);
	}

	/**
	 * 根据条件accountId和可用IAM来获取获取AWS IAM的数据 
	 * @param awsIams
	 */
	@Override
	public List<AwsIams> findByIAMData(AwsIams awsIams) {

		return baseDao.findByIAMData(awsIams);
	}
	
	
	
	
	
	
}
