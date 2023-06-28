package com.awslabplatform.service.experimentManage.impl;

import com.amazonaws.services.cloudformation.model.StackEvent;
import com.amazonaws.services.cloudformation.model.Tag;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.KeyPair;
import com.awslabplatform.aws.*;
import com.awslabplatform.dao.courseManage.CourseDao;
import com.awslabplatform.dao.experimentManage.ExperimentDao;
import com.awslabplatform.dao.experimentManage.ExperimentRecordDao;
import com.awslabplatform.dao.userManage.UserDao;
import com.awslabplatform.entity.*;
import com.awslabplatform.service.base.impl.BaseServiceImpl;
import com.awslabplatform.service.courseManage.CourseService;
import com.awslabplatform.service.experimentManage.ExperimentService;
import com.awslabplatform.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static com.awslabplatform.common.Common.*;

/**
 * 实验service实现类
 * @author lijf
 * date 2018年3月20日 下午2:22:54
 */

@Service("experimentService")
public class ExperimentServiceImpl extends BaseServiceImpl<ExperimentDao, Experiment, String> implements ExperimentService {
	/**
	 * log
	 */
	private static Logger log = LoggerFactory.getLogger(ExperimentServiceImpl.class);
	@Autowired
	private ExperimentDao experimentDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private ExperimentRecordDao recordDao;
	@Autowired
	private CourseService courseService;
	@Override
	public List<Experiment> listByCourseId(String courseId) {
		return experimentDao.listByCourseId(courseId);
	}

	@Override
	public Experiment selectByid(String experimentId) {
		return experimentDao.selectByPrimaryKey(experimentId);
	}
	// CH 修改获取凭证的方法
	@Override
	public ExperimentRecord startExp(Student student,String experimentId,String useType,String iamKind, String isActive,String isDelete,String accountStause,String accountActive) {
		Collection<Tag> tags =new ArrayList<Tag>();
		//添加标签
		if(student.getMechanismName()!=null&&!"".equals(student.getMechanismName())){
			tags.add(new Tag().withKey("college").withValue(student.getMechanismName()));
		}
		if(student.getMajor()!=null&&!"".equals(student.getMajor())){
			tags.add(new Tag().withKey("major").withValue(student.getMajor()));
		}
		if(student.getGrade()!=null&&!"".equals(student.getGrade())){
			tags.add(new Tag().withKey("grade").withValue(student.getGrade()));
		}
		if(student.getClassName()!=null&&!"".equals(student.getClassName())){
			tags.add(new Tag().withKey("class").withValue(student.getClassName()));
		}
		if(student.getRealName()!=null&&!"".equals(student.getRealName())){
			tags.add(new Tag().withKey("Name").withValue(student.getRealName()));
		}
		if(student.getEmail()!=null&&!"".equals(student.getEmail())){
			tags.add(new Tag().withKey("UserName").withValue(student.getEmail()));
		}
		//取唯一堆栈名，aws中stackName不能以数字开头，在前加字符
		String stackName="s"+UUIDUtils.getUUID();

		ExperimentRecord experimentRecord = new ExperimentRecord();
		//查出实验信息
		Experiment experiment=experimentDao.selectByPrimaryKey(experimentId);
		if(experiment==null){
			experimentRecord.setReason(NOT_FOUND_EXPERIMENT);
			log.error("未找到实验信息示");
			return experimentRecord;
		}
		
		//得到凭证和区域
		CredentialsAndRegion credentialsAndRegion = experimentDao.getCredential(student.getId(),useType,iamKind,isActive,isDelete,accountStause,accountActive);
		if(credentialsAndRegion==null){
			log.error("未找到凭证!");
			experimentRecord.setReason(AWS_EXPRIMENT_NOTFOUND);
			return experimentRecord;
		}
		if(experiment.getRegion()!=null&&!"".equals(experiment.getRegion().trim())){
			credentialsAndRegion.setRegion(experiment.getRegion());
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
		String stopTime = simpleDateFormat.format(new Date(new Date().getTime()+1000*60*experiment.getRuntime()));

		//添加学生的课程记录
		courseService.addMycourse(student, experiment);
		
		//创建密钥
		String keyName = "k"+UUIDUtils.getUUID();
		KeyPair keyPair = new EC2Service(credentialsAndRegion).createKeyPair(keyName);
		String keyUrl=null;
		try {
			FileInfo uploadFile = FastDFSUtils.uploadPic(keyPair.getKeyMaterial().getBytes(), keyName+".pem", keyPair.getKeyMaterial().getBytes().length, student.getId());
			keyUrl = uploadFile.getFileUrl();
		} catch (Exception e) {
			log.debug("上传文件，shrio冲突，功能正常。");
		}
		String windowsPassword=PasswordUtil.NewPasswd(10);//生成密码，只在windows实例中才会使用。
		StartStackRequest startStackRequest = new StartStackRequest()
			//.withInstanceType(experiment.getInstanceType()==null?"t2.micro":experiment.getInstanceType())
			.withKeyName(keyPair.getKeyName())
			.withStackName(stackName)
			.withTemplateBody(FileUtil.readRemoteFile(experiment.getTemplateUrl()))
			.withWindowsPassword(windowsPassword)
			.withTags(tags);
		//启动资源
		new StackService(credentialsAndRegion).starStack(startStackRequest);



		//String experimentId_stackName_endTime_keyUrl_iamRegisterUrl=experimentId+"_"+stackName+"_"+stopTime+"_"+keyUrl+"_"+keyName;
		//为学生加入正在进行的实验

		experimentRecord.setId(UUIDUtils.getUUID());
		experimentRecord.setStudentId(student.getId());
		experimentRecord.setExperimentId(experimentId);
		experimentRecord.setStackName(stackName);
		experimentRecord.setStartTime(new Date());
		experimentRecord.setStopTime(stopTime);
		experimentRecord.setKeyUrl(keyUrl);
		experimentRecord.setKeyName(keyName);
		experimentRecord.setWindowsPassword(windowsPassword);
		experimentRecord.setStatus(0);
		recordDao.addOne(experimentRecord);
		//userDao.updateExperiment(student.getId(), experimentId_stackName_endTime_keyUrl_iamRegisterUrl);
		return experimentRecord;

	}
	// CH 修改获取凭证的方式
	@Override
	public void deleteStack(String studentId,String shutexperimentId,String useType,String iamKind,String isActive,String isDelete,String accountStause,String accountActive) {
		ExperimentRecord runningExperiment = recordDao.getRunning(studentId);
		if(runningExperiment!=null){
			String experimentId=runningExperiment.getExperimentId();
			if(!shutexperimentId.equals(experimentId)){
				return;//如果要关的实验资源和正在进行的不一样，，不关闭，防止提早结束后定时关闭现在资源
			}
			String stackName=runningExperiment.getStackName();

			//（修改区域）
			CredentialsAndRegion credentialsAndRegion = experimentDao.getCredential(studentId,useType,iamKind,
					                                                   isActive,isDelete,accountStause,accountActive);
			//查出实验信息
			Experiment experiment=experimentDao.selectByPrimaryKey(experimentId);
			if(experiment.getRegion()!=null&&!"".equals(experiment.getRegion().trim())){
				credentialsAndRegion.setRegion(experiment.getRegion());
			}
			new ResourceService(credentialsAndRegion).deleteStack(stackName);
			//删除密钥
			String keyName=runningExperiment.getKeyName();
			new EC2Service(credentialsAndRegion).deleteKeyPair(keyName);
			//学生正在进行的实验改为null
			runningExperiment.setEndTime(new Date());
			runningExperiment.setStatus(1);
			//记录改为结束状态
			recordDao.update(runningExperiment);
			//userDao.updateExperiment(studentId, null);
			//添加学生已完成实验id
			MyCourse myCourse = courseDao.findOneRecord(studentId, experiment.getCourseId());
			if(myCourse!=null){
				String experimentIds=myCourse.getFinishedExperimentIds();
				if(experimentIds==null||"".equals(experimentIds.trim())){
					experimentIds=experimentId;
				}else{
					experimentIds=experimentIds+"_"+experimentId;
				}
				myCourse.setFinishedExperimentIds(experimentIds);
				courseDao.addExperiment(myCourse);
			}
		}
	}

	/**
	 * 查找学生正在进行实验的实例
	 * CH 修改获取凭证的方法
	 */
	@Override
	public List<Instance> updataInstanceBystudent(ExperimentRecord runningExperiment,String useType,String iamKind,String isActive,String isDelete,String accountStause,String accountActive) {
			String experimentId=runningExperiment.getExperimentId();
			String stackName=runningExperiment.getStackName();
		    //修改区域
			CredentialsAndRegion credentialsAndRegion = experimentDao.getCredential(runningExperiment.getStudentId(),useType,iamKind,
					                                                                isActive,isDelete,accountStause,accountActive);
			//查出实验信息
			Experiment experiment=experimentDao.selectByPrimaryKey(experimentId);
			if(experiment.getRegion()!=null&&!"".equals(experiment.getRegion().trim())){
				credentialsAndRegion.setRegion(experiment.getRegion());
			}
			try {
				
				Collection<String> instanceIds = new ResourceService(credentialsAndRegion).getInstanceIdByStackName(stackName);
				return new ResourceService(credentialsAndRegion).getInstance(instanceIds);
			} catch (Exception e) {
				log.debug("aws资源不存在！");
				//学生正在进行的实验改为null
				//userDao.updateExperiment(runningExperiment.getStudentId(), null);
				runningExperiment.setEndTime(new Date());
				runningExperiment.setStatus(1);
				recordDao.update(runningExperiment);
				//删除密钥
				String keyName=runningExperiment.getKeyName();
				new EC2Service(credentialsAndRegion).deleteKeyPair(keyName);
			}
			return null;
	}
    // CH 修改获取凭证的方式 version
	@Override
	public List<StackEvent> getStackEvents(ExperimentRecord experimentRecord,String useType,String iamKind, String isActive,String isDelete,String accountStause,String accountActive) {
		String experimentId=experimentRecord.getExperimentId();
		String stackName=experimentRecord.getStackName();
		//(修改位置)
		CredentialsAndRegion credentialsAndRegion = experimentDao.getCredential(experimentRecord.getStudentId(),useType,iamKind,isActive,isDelete,accountStause,accountActive);

		//查出实验信息
		Experiment experiment=experimentDao.selectByPrimaryKey(experimentId);
		if(experiment.getRegion()!=null&&!"".equals(experiment.getRegion().trim())){
			credentialsAndRegion.setRegion(experiment.getRegion());
		}
		return new ResourceService(credentialsAndRegion).getStackEvents(stackName);
	}

	/**
	 * 获取实验组ID
	 * @param courseId
	 * @return
	 */
	@Override
	public String getExperimentGroupIdByCourseId(String courseId){
		 return baseDao.getExperimentGroupIdByCourseId(courseId);
	}

	/**
	 *
	 * @param groupId
	 * @param userId
	 * @return
	 */
	@Override
	public String getIdByExperimentGroupIdAnduserId(String groupId, String userId){
		return baseDao.getIdByExperimentGroupIdAnduserId(groupId, userId);
	}

	@Override
	public ExperimentRecord getRunningExperiment(String studentId) {
		return recordDao.getRunning(studentId);
	}
	/*CH 注销该方法 version 2.0*/
	/*@Override
	public String getAwsLoginUrl(Student student, Experiment experiment,String useType,String iamKind,String iamStatus,String userState, String isActive,String isDelete,String accountStause,String accountActive) {
		//为学生生成联盟登录链接
		String awsUrl="";
		if(experiment.getPolicyFileUrl()!=null&&!"".equals(experiment.getPolicyFileUrl())){
			FederationImpl federationImpl = new FederationImpl();
			FederationInfo federationInfo = new FederationInfo();
			//得到凭证和区域(修改位置)
			CredentialsAndRegion credentialsAndRegion = experimentDao.getCredential(student.getId(),useType,iamKind,iamStatus,userState,isActive,isDelete,accountStause,accountActive);
			if(credentialsAndRegion==null){
				log.error("未找到本课程教师的凭证!");
				return null;
			}
			if(experiment.getRegion()!=null&&!"".equals(experiment.getRegion().trim())){
				credentialsAndRegion.setRegion(experiment.getRegion());
			}
			federationInfo.setRealName(student.getEmail());//当前登录的用户邮箱
			federationInfo.setAccess_key_id(credentialsAndRegion.getAccessKeyID());//IAM 访问key ID
			federationInfo.setSecret_key(credentialsAndRegion.getAccessKey());//IAM 访问key
			federationInfo.setConsoleURL("https://console.amazonaws.cn/ec2/v2/home?region="+credentialsAndRegion.getRegion()+"#Instances:sort=instanceId");//aws 控制台链接，精确到控制台对应功能路径
			federationInfo.setIssuerURL("");//请求者URL，填写我们自己系统的URL
			federationInfo.setDurationSeconds(experiment.getRuntime()*60);
			String policy=FileUtil.readRemoteFile(experiment.getPolicyFileUrl());
			policy=policy.replace("${aws:username}", student.getEmail());
			federationInfo.setPolicy(policy);
			try {
				awsUrl=federationImpl.getAwsFederationeUrl(federationInfo);
			} catch (Exception e) {
				log.debug("获取联盟登录链接出错！");
			}
		}else{
			log.debug("该实验未配置策略，不能作联盟登录！");
		}
		return awsUrl;
	}*/

	@Override
	public StudentExperment selectExperimentIam(Student student,String controlUseType, String iamKind,
												  String isActive, String isDelete, String accountStause, String accountActive, String usedState,String isUsed)
	{
		   StudentExperment studentExperment=null;
			String nowTime = TimeUtils.currentTime();
			int i =experimentDao.updateControlAccountPool(student.getId(),controlUseType,usedState,student.getRealName(),isUsed,nowTime,isActive,isDelete,accountStause,accountActive);
			//大于零表示账号获取成功
			if(i>0){
				//获取对应的账号的IAM信息
				studentExperment =experimentDao.getIamInformation(student.getId(),iamKind,
						isActive,isDelete,accountStause,accountActive);
				if(studentExperment==null){
					updateAccountPool(student,isUsed);
					studentExperment =new StudentExperment();
					studentExperment.setPoolUsed(AWS_CREADENTALS_NOTUSED);
					return studentExperment;
				}else{
					studentExperment.setPoolUsed(AWS_ACCOUNTPOOL_SUCCESS);
					return studentExperment;
				}

			}else{
				studentExperment =new StudentExperment();
				studentExperment.setPoolUsed(AWS_ACCOUNTPOOL_NOUSED);
				return studentExperment;
			}
	}

	@Override
	public Policys attachPoricy(String experimentId, String status) {
		return experimentDao.getPolicyInformation(experimentId,status);
	}

//	@Override
//	public CredentialsAndRegion selectProof(Student student, String useType, String IamKind, String isActive, String isDelete, String accountStause, String accountActive) {
//
//		return experimentDao.getCredential(student.getId(),useType,IamKind,isActive,isDelete,accountStause,accountActive);
//	}

	@Override
	public int updateCredentialPassword(StopExperment stopExperment){
        String newPassword =new IAMClient(stopExperment.getAccessKeyID(),stopExperment.getAccessKey(),null).changePassword(stopExperment.getIamName());
		return experimentDao.updateIamPassword(stopExperment.getId(),newPassword);
	}

	@Override
	public int updateAccountPool(Student student,String isUsed) {
		String nowTime = TimeUtils.currentTime();
		return experimentDao.updateAccountPoolisUesd(isUsed,student.getId(),nowTime);
	}

	@Override
	public StudentExperment startControlExp(Student student,String experimentId,String controlUseType,String iamKind,String controlIamKind,String isActive, String isDelete, String accountStause, String accountActive,String usedState,String status,String isUsed){
		String studentId =student.getId();
		StudentExperment studentExperment=new StudentExperment();
		/*获取实验信息*/
		Experiment experiment = selectByPrimaryKey(experimentId);
		if(experiment==null){
          studentExperment.setPoolUsed(NOT_FOUND_EXPERIMENT);
          return studentExperment;
		}
		/*获取IAM账号信息并获取账号*/
		studentExperment =selectExperimentIam(student,controlUseType,iamKind,isActive,isDelete,accountStause,accountActive,usedState,isUsed);
		/*获取账号成功后*/
		if(studentExperment!=null&&studentExperment.getPoolUsed().equals(AWS_ACCOUNTPOOL_SUCCESS)){
				/*获取管理员凭证*/
			CredentialsAndRegion credentialsAndRegion =experimentDao.getControlCredential(studentId,controlIamKind);
				//CredentialsAndRegion credentialsAndRegion =selectProof(student,controlUseType,controlIamKind,isActive,isDelete,accountStause,accountActive,studentExperment.getAccountId());
				if(credentialsAndRegion!=null){
					/*获取实验凭证*/
					Policys policys =attachPoricy(experimentId,status);
					if(policys!=null){
							if(studentExperment.getPoolUsed().equals(AWS_ACCOUNTPOOL_SUCCESS)){
								/*对分配好的账号给与策略*/
								/*策略名不能为中文名*/
								String policyName = Validator.isChinese(policys.getName());
								try {
									new IAMClient(credentialsAndRegion.getAccessKeyID(),credentialsAndRegion.getAccessKey(),null).attachCustomPolicy(studentExperment.getIamName(),FileUtil.readRemoteFile(policys.getFileUrl()),policyName,studentExperment.getAccountId());
								} catch (Exception e) {
									e.printStackTrace();
									/*绑定失败重新将账号放回池中*/
									updateAccountPool(student,isUsed);
									studentExperment.setPoolUsed(AWS_POLICY_NOUSED);
									return studentExperment;
								}
								/*将实验添加到进行实验中*/
								ExperimentRecord runningExperiment=addStartControlExpRecord(studentId,experiment);
								/*将实验一些信息添加到返回类*/
								studentExperment.setEndTime(runningExperiment.getStopTime());
								studentExperment.setExperimentId(runningExperiment.getExperimentId());
								studentExperment.setRunningTime(experiment.getRuntime());
								/*添加到学生课程中去*/
								courseService.addMycourse(student, experiment);
							}
					}else{
						updateAccountPool(student,isUsed);
						studentExperment.setPoolUsed(AWS_POLICY_NULL);
					}
				}else{
					updateAccountPool(student,isUsed);
					studentExperment.setPoolUsed(AWS_CREADENTALS_NOTUSED);
				}
		}
		return studentExperment;
	}

	@Override
	public void stopControlExp(String studentId,String shutexperimentId,String iamKind, String controlIamKind,String isUsed,String status) {
		Student  student = userDao.selectById(studentId);
		ExperimentRecord runningExperiment = recordDao.getRunning(studentId);
		if(runningExperiment!=null){
			//获取账号池实验者使用的账号
			//AccountPool accountPool=experimentDao.getAccountPoolInformation(studentId,isActive,isDelete);
			//获取实验IAM
//			if(shutexperimentId==null&&"".equals(shutexperimentId)){
//				shutexperimentId=runningExperiment.getExperimentId();
//			}
			if(!shutexperimentId.equals(runningExperiment.getExperimentId())){
				return;//如果要关的实验资源和正在进行的不一样，，不关闭，防止提早结束后定时关闭现在资源
			}

			CredentialsAndRegion credentialsAndRegion =experimentDao.getControlCredential(studentId,iamKind);
//			CredentialsAndRegion credentialsAndRegion= selectProof(student,controlUseType,iamKind,
//					isActive,isDelete,accountStause,accountActive,accountPool.getAccountId());
			//获取管理的凭证
            CredentialsAndRegion controlCredentialsAndRegion =experimentDao.getControlCredential(studentId,controlIamKind);
//			CredentialsAndRegion controlCredentialsAndRegion= selectProof(student,controlUseType,controlIamKind,
//					isActive,isDelete,accountStause,accountActive,accountPool.getAccountId());
			//将所需要的停止的信息保存到一个类中
			StopExperment stopExperment =new StopExperment();
			stopExperment.setId(credentialsAndRegion.getId());
			stopExperment.setAccountId(credentialsAndRegion.getAccountId());
			stopExperment.setIamName(credentialsAndRegion.getIamName());
			stopExperment.setAccessKeyID(controlCredentialsAndRegion.getAccessKeyID());
			stopExperment.setAccessKey(controlCredentialsAndRegion.getAccessKey());
			//获取策略
			Policys policys =attachPoricy(shutexperimentId,status);
			//释放策略
            String policyName =Validator.isChinese(policys.getName());
            new IAMClient(stopExperment.getAccessKeyID(),stopExperment.getAccessKey(),null).detachUserPolicy(stopExperment.getIamName(),stopExperment.getAccountId(),policyName);
			//将使用的账号放回账号池中
			updateAccountPool(student,isUsed);
			//修改实验IAM的密码
			updateCredentialPassword(stopExperment);
			String experimentId=runningExperiment.getExperimentId();
			Experiment experiment=experimentDao.selectByPrimaryKey(experimentId);
			runningExperiment.setEndTime(new Date());
			runningExperiment.setStatus(1);
			//记录改为结束状态
			recordDao.update(runningExperiment);
			//userDao.updateExperiment(studentId, null);
			MyCourse myCourse = courseDao.findOneRecord(studentId, experiment.getCourseId());
			if(myCourse!=null){
				String experimentIds=myCourse.getFinishedExperimentIds();
				if(experimentIds==null||"".equals(experimentIds.trim())){
					experimentIds=experimentId;
				}else{
					experimentIds=experimentIds+"_"+experimentId;
				}
				myCourse.setFinishedExperimentIds(experimentIds);
				courseDao.addExperiment(myCourse);
			}
			//删除资源
			System.out.println("开始释放资源 "+new Date());
			new AWSResourceClient(stopExperment.getAccessKeyID(),stopExperment.getAccessKey()).deleteAwsResouces();
			System.out.println("结束释放资源 "+new Date());
			//删除多余Iam用户
			List<String> iamNames = new ArrayList<String>();
			iamNames.add(controlCredentialsAndRegion.getIamName());
			iamNames.add(stopExperment.getIamName());
			new IAMClient(stopExperment.getAccessKeyID(),stopExperment.getAccessKey(), null).deleteAlluserExcept(iamNames);
			System.out.println("删除用户完毕 "+new Date());
		}
	}

	@Override
	public ExperimentRecord addStartControlExpRecord(String studentId, Experiment experiment) {
		String experimentId =experiment.getExperimentId();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
		String stopTime = simpleDateFormat.format(new Date(new Date().getTime()+1000*60*experiment.getRuntime()));
		ExperimentRecord experimentRecord = new ExperimentRecord();
		experimentRecord.setId(UUIDUtils.getUUID());
		experimentRecord.setStudentId(studentId);
		experimentRecord.setExperimentId(experimentId);
		experimentRecord.setStartTime(new Date());
		experimentRecord.setStopTime(stopTime);
		experimentRecord.setStatus(0);
		recordDao.addOne(experimentRecord);
		//String experimentId_endTime=experimentId+"_"+stopTime;
		//userDao.updateExperiment(studentId,experimentId_endTime);
		return experimentRecord;
	}

	@Override
	public StudentExperment runControlExperiment(Student student,String iamKind,  String isActive, String isDelete, String accountStause, String accountActive) {

		StudentExperment studentExperment = experimentDao.getIamInformation(student.getId(),iamKind,
					isActive,isDelete,accountStause,accountActive);
		if(studentExperment!=null){
			studentExperment.setPoolUsed(AWS_ACCOUNTPOOL_THIS_USED);
		}
		return studentExperment;
	}
}
