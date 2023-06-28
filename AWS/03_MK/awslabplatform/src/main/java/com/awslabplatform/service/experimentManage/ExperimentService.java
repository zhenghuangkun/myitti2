package com.awslabplatform.service.experimentManage;

import java.util.List;

import com.amazonaws.services.cloudformation.model.StackEvent;
import com.amazonaws.services.ec2.model.Instance;
import com.awslabplatform.entity.*;
import com.awslabplatform.service.base.BaseService;

/**
 * 实验管理service
 * @author lijf
 * date 2018年3月20日 下午2:18:34
 */
public interface ExperimentService extends BaseService<Experiment, String> {

	List<Experiment> listByCourseId(String courseId);
	Experiment selectByid(String experimentId);

	/**CH version 2.0 修改方法中获取凭证的方法
	 * 启动实验
	 * @param student 学生信息
	 * @param experimentId 实验ID
	 * @param useType 运用类型（不登录控制台）
	 * @param iamKind 使用人员类型（实验）
	 * @param isActive 账号池账号激活状态
	 * @param isDelete 账号池账号删除状态
	 * @param accountStause 付款账号删除状态
	 * @param accountActive 付款账号激活状态
	 * @return
	 */

	ExperimentRecord startExp(Student student,String experimentId,String useType,String iamKind,String isActive,String isDelete,String accountStause,String accountActive);

	/**
	 * CH version 2.0 修改方法中获取凭证的方法
	 * 删除堆栈
	 * @param studentId 学生Id
	 * @param shutexperimentId 实验Id
	 * @param useType 运用类型（不登录控制台）
	 * @param iamKind 使用人员类型（实验）
	 * @param isActive 账号池账号激活状态
	 * @param isDelete 账号池账号删除状态
	 * @param accountStause 付款账号删除状态
	 * @param accountActive 付款账号激活状态
	 */
	void deleteStack(String studentId,String shutexperimentId,String useType,String iamKind,String isActive,String isDelete,String accountStause,String accountActive);

	/**
	 * CH version 2.0 修改方法中获取凭证的方法
	 * 得到其正运行的实例
	 * @param runningExperiment
	 * @param useType 运用类型（不登录控制台）
	 * @param iamKind 使用人员类型（实验）
	 * @param isActive 账号池账号激活状态
	 * @param isDelete 账号池账号删除状态
	 * @param accountStause 付款账号删除状态
	 * @param accountActive 付款账号激活状态
	 * @return
	 */
	List<Instance> updataInstanceBystudent(ExperimentRecord runningExperiment,String useType,String iamKind,String isActive,String isDelete,String accountStause,String accountActive);

	/**
	 * CH version 2.0 修改方法中获取凭证的方法
	 * 获取堆栈启动、删除状态
	 * @param experimentRecord
	 * @param useType 运用类型（不登录控制台）
	 * @param iamKind 使用人员类型（实验）
	 * @param isActive 账号池账号激活状态
	 * @param isDelete 账号池账号删除状态
	 * @param accountStause 付款账号删除状态
	 * @param accountActive 付款账号激活状态
	 * @return
	 */
	List<StackEvent> getStackEvents(ExperimentRecord experimentRecord,String useType,String iamKind, String isActive,String isDelete,String accountStause,String accountActive);

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
	public String getIdByExperimentGroupIdAnduserId(String groupId, String userId);
	/**
	 * 查找 学生正在进行的实验
	 * @param studentId
	 * @return
	 */
	ExperimentRecord getRunningExperiment(String studentId);
	/*CH 注销该方法 version 2.0*/
	//String getAwsLoginUrl(Student student,Experiment experiment,String useType,String iamKind,String iamStatus,String userState, String isActive,String isDelete,String accountStause,String accountActive);


	/**
	 * CH version 2.0
	 * 获取控制台实验的IAM信息
	 * @param student 学生信息
	 * @param controlUseType 运用类型（登录控制台）
	 * @param iamKind 使用人员类型（实验）
	 * @param isActive 账号池账号激活状态
	 * @param isDelete 账号池账号删除状态
	 * @param accountStause 付款账号删除状态
	 * @param accountActive 付款账号激活状态
	 * @param usedState 账号池账号被使用的状态
	 * @return
	 */
	StudentExperment selectExperimentIam(Student student,String controlUseType, String iamKind,
										   String isActive, String isDelete, String accountStause, String accountActive, String usedState,String isUsed);

	/**
	 * CH version 2.0
	 * 获取该实验策略
	 * @param experimentId 实验ID
	 * @param status 策略状态(激活状态)
	 * @return
	 */
	Policys attachPoricy(String experimentId, String status);

//	/**
//	 * CH version 2.0
//	 * 获取凭证
//	 * @param student 学生信息
//	 * @param useType 运用类型（不登录控制台）
//	 * @param IamKind 使用人员类型
//	 * @param isActive 账号池账号激活状态
//	 * @param isDelete 账号池账号删除状态
//	 * @param accountStause 付款账号删除状态
//	 * @param accountActive 付款账号激活状态
//	 * @return
//	 */
//	CredentialsAndRegion selectProof(Student student,String useType,  String IamKind, String isActive, String isDelete, String accountStause, String accountActive);


	/**
	 * CH version 2.0
	 * 修改IAM实验账号密码
	 * @param stopExperment  停止实验需要的信息
	 * @return
	 */

	int updateCredentialPassword(StopExperment stopExperment);

	/**
	 * CH version 2.0
	 * 控制台实验结束，释放账号池中用户使用状态
	 * @param student 学生信息
	 * @param isUsed 账号池账号使用的状态（未使用状态）
	 * @return
	 */

	int updateAccountPool(Student student,String isUsed);

	/**
	 * CH version 2.0
	 * 开启控制台实验
	 * @param student 学生信息
	 * @param experimentId 实验Id
	 * @param controlUseType 应用类型（控制台实验）
	 * @param iamKind 使用人员类型（实验员）
	 * @param controlIamKind 使用人员类型（管理员）
	 * @param isActive 账号池账号激活状态
	 * @param isDelete 账号池账号删除状态
	 * @param accountStause 付款账号删除状态
	 * @param accountActive 付款账号激活状态
	 * @param usedState 账号池里账号的使用状态
	 * @param status  策略状态
	 * @return
	 */
	StudentExperment startControlExp(Student student,String experimentId,String controlUseType,String iamKind,String controlIamKind,String isActive, String isDelete, String accountStause, String accountActive,String usedState,String status,String isUsed);

	/**
	 *  CH verison 2.0
	 *   停止控制台实验
	 * @param studentId 学生ID
	 * @param shutexperimentId 实验Id
	 * @param iamKind 使用人员类型（实验员）
	 * @param controlIamKind 使用人员类型（管理员）
	 * @param isUsed 账号池账号使用的状态
	 * @param status 策略状态
	 */

    public void stopControlExp(String studentId,String shutexperimentId,String iamKind, String controlIamKind,String isUsed,String status);

	/**
	 *CH version 2.0
	 * 将要进行的实验记录
	 * @param studentId 学生ID
	 * @param experiment 实验信息
	 * @return
	 */

    public ExperimentRecord addStartControlExpRecord(String studentId,Experiment experiment);

	/**
	 * CH version 2.0
	 * 判断是否已有控制台实验进行中并获取相对应的IAM 信息
	 * @param student 学生信息
	 * @param iamKind 使用人员类型（实验员）
	 * @param isActive 账号池账号激活状态
	 * @param isDelete 账号池账号删除状态
	 * @param accountStause 付款账号删除状态
	 * @param accountActive 付款账号激活状态
	 * @return
	 */
    public StudentExperment runControlExperiment(Student student,String iamKind,
												 String isActive, String isDelete, String accountStause, String accountActive);
}
