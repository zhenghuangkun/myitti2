package com.awslabplatform.dao.experimentManage;

import java.util.List;

import com.awslabplatform.entity.*;
import org.apache.ibatis.annotations.Param;

import com.awslabplatform.dao.base.BaseDao;

/**
 * 实验Dao
 * @author lijf
 * date 2018年3月20日 下午2:14:01
 */
public interface ExperimentDao extends BaseDao<Experiment, String>{

	List<Experiment> listByCourseId(@Param("courseId")  String courseId);

	Experiment selectByPrimaryKey(@Param("experimentId") String experimentId);

	/**
	 * 用学生正在进行的实验id查出 区域和凭证
	 * @param experimentId
	 * @return
	 */
	CredentialsAndRegion getCredentialsAndRegion(@Param("experimentId") String experimentId);

	/**
	 * 《CH 修改获取凭证的方式》
	 * 用学生id查出所在院系管理员 凭证(不登录控制台实验)
	 * @param studentId 学生id
	 * @param useType 运用类型（不登录控制台）
	 * @param iamKind 使用人员类型（实验）
	 * @param isActive 账号池账号激活状态
	 * @param isDelete 账号池账号删除状态
	 * @param accountStause 付款账号删除状态
	 * @param accountActive 付款账号激活状态
	 * @return
	 */
	CredentialsAndRegion getCredential(@Param("studentId") String studentId,@Param("useType") String useType,@Param("iamKind") String iamKind,
									   @Param("isActive") String isActive,@Param("isDelete")String isDelete,
	                                   @Param("accountStause") String accountStause,@Param("accountActive") String accountActive);

	/**
	 * 获取实验组ID
	 * @param courseId
	 * @return
	 */
	public String getExperimentGroupIdByCourseId(String courseId);

	/**
	 *
	 * @param groupId
	 * @param userId
	 * @return
	 */
	public String getIdByExperimentGroupIdAnduserId(@Param("groupId") String groupId, @Param("userId") String userId);

	/**
	 * CH version 2.0
	 * 获取登录控制台实验的IAM信息
	 *
	 * @param studentId 学生id
	 * @param iamKind 使用人员类型（实验）
	 * @param isActive 账号池账号激活状态
	 * @param isDelete 账号池账号删除状态
	 * @param accountStause 付款账号删除状态
	 * @param accountActive 付款账号激活状态
	 * @return
	 */

	public StudentExperment getIamInformation(@Param("studentId") String studentId,@Param("iamKind") String iamKind,
											  @Param("isActive") String isActive,
											  @Param("isDelete")String isDelete,
											  @Param("accountStause") String accountStause,@Param("accountActive") String accountActive);

//	/**
//	 * CH version 2.0
//	 * 获取账号池中的账号
//	 * @param isActive 账号池账号激活状态
//	 * @param isDelete 账号池账号删除状态
//	 * @param currentUserId 学生Id
//	 * @return
//	 */
//	public AccountPool getAccountPoolInformation(@Param("currentUserId") String currentUserId,@Param("isActive") String isActive,
//												 @Param("isDelete")String isDelete);

	/**
	 * CH version 2.0
	 * 从账号池分配给使用者控制台实验账号
	 * @param studentId 学生Id
	 * @param useType 账号池账号应用类型（控制台）
	 * @param used （账号池登录控制台实验账号使用状态，已使用）
	 * @param currentUserName 学生姓名
	 * @param isUsed （账号池登录控制台实验账号使用状态，未使用）
	 * @param nowTime 修改时间
	 * @param isActive 账号池账号激活状态
	 * @param isDelete 账号池账号删除状态
	 * @param accountStause 付款账号删除状态
	 * @param accountActive 付款账号激活状态
	 * @return
	 */
	public int updateControlAccountPool(@Param("studentId") String studentId,@Param("useType") String useType,@Param("used") String used,@Param("currentUserName")String currentUserName,@Param("isUsed") String isUsed,
								 @Param("nowTime") String nowTime,@Param("isActive") String isActive,
								 @Param("isDelete")String isDelete,  @Param("accountStause") String accountStause,@Param("accountActive") String accountActive );

//	/**
//	 * 判断是否已经分配给账号
//	 * CH version 2.0
//	 * @param payAccountId 付款账号
//	 * @param studentId 学生ID
//	 * @param useType 运用类型（不登录控制台）
//	 * @param isUsed  使用状态
//	 * @param isActive 账号池账号激活状态
//	 * @param isDelete 账号池账号删除状态
//	 * @return
//	 */
//	public String getAccountPoolId(@Param("payAccountId") String payAccountId,@Param("studentId")String studentId,@Param("useType")String useType,@Param("isUsed") String isUsed,@Param("isActive") String isActive,
//								 @Param("isDelete")String isDelete
//								 );

	/**
	 * 获取该实验的策略
	 * @param experimentId 实验ID
	 * @param status 策略状态
	 * @return
	 */
	public Policys getPolicyInformation(@Param("experimentId") String experimentId, @Param("status") String status);

	/**
	 * 修改实验IAM密码
	 * @param id IAMid
	 * @param password 密码
	 * @return
	 */
	public int  updateIamPassword(@Param("id") String id,@Param("password")String password );

	/**
	 * 修改账号池账号休息（账号池收回账号）
	 * @param isUsed 使用状态
	 * @param currentUserID 学生id
	 * @param nowTime 修改时间
	 * @return
	 */
	public int  updateAccountPoolisUesd(@Param("isUsed") String isUsed,@Param("currentUserID") String currentUserID,
									  @Param("nowTime") String nowTime
									);


	/**
	 * CH version 2.0
	 * 控制台实验凭证
	 * @param studentId 学生Id
	 * @param iamKind 使用人员类型
	 * @return
	 */
	public CredentialsAndRegion getControlCredential(@Param("studentId") String studentId,@Param("iamKind") String iamKind);

	/**
	 * CH version 2.0
	 * 判断账号池里是否有可用账号
	 * @param studentId 学生Id
	 * @param useType 运用类型（登录控制台）
	 * @param isActive 账号池账号激活状态
	 * @param isDelete 账号池账号删除状态
	 * @param accountStause 付款账号删除状态
	 * @param accountActive 付款账号激活状态
	 * @return
	 */
//	public String selectControlAccount(@Param("studentId") String studentId,@Param("useType") String useType,@Param("isActive") String isActive,
									//		@Param("isDelete")String isDelete,@Param("accountStause") String accountStause,@Param("accountActive") String accountActive);
}
