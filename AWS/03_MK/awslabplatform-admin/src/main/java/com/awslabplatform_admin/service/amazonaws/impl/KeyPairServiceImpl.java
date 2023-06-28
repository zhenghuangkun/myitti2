package com.awslabplatform_admin.service.amazonaws.impl;

import com.awslabplatform_admin.entity.User;
import com.awslabplatform_admin.service.courseManage.ExperimentService;
import com.awslabplatform_admin.service.userManage.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.CreateKeyPairRequest;
import com.amazonaws.services.ec2.model.CreateKeyPairResult;
import com.amazonaws.services.ec2.model.DeleteKeyPairRequest;
import com.amazonaws.services.ec2.model.KeyPair;
import com.awslabplatform_admin.entity.AwsIams;
import com.awslabplatform_admin.entity.FileInfo;
import com.awslabplatform_admin.service.amazonaws.KeyPairService;
import com.awslabplatform_admin.service.awsAccountManage.AwsIamsService;
import com.awslabplatform_admin.service.connmon.FileInfoService;
import com.awslabplatform_admin.service.uploadService.uploadService;
import com.awslabplatform_admin.util.ShiroUtil;
import com.awslabplatform_admin.util.amazonaws.STSUtil;

import static com.awslabplatform_admin.common.Common.*;


/**
 * AWS堆栈Services
 * @author weixin
 * @version 2018年4月9日
 */
@Service("KeyPairService")
public class KeyPairServiceImpl implements KeyPairService{
	
	/**
	 * 文件上传service
	 */
	@Autowired
	private uploadService uploadsv;
	/**
	 * 文件描述service
	 */
	@Autowired
	private FileInfoService fileService;
	/* CH version 2.0 */
	@Autowired
	private UserService userService;
	/**
	 * aws service
	 */
	@Autowired
	private AwsIamsService awsIamsService;
	@Autowired
	private ExperimentService experimentService;
	
	/**日志*/
    private static Logger log = LoggerFactory.getLogger(KeyPairServiceImpl.class);
	
    
    /**
	 * CH version 2.0
	 * 创建密钥
	 * @author zhenghuangkun
	 * @version 2018年4月10日
	 * @param keyName
	 * @return
	 */
	public FileInfo createKeyPair(String keyName){
		// 从数据中判断密钥是否存在
		Boolean eflag = fileService.checkKeyPairExist(keyName);

		// 密钥存在，返回false
		if(eflag){
			return null;
		}
		String userId =ShiroUtil.getCurrentUserId();
		User user =new User();
		user.setUserId(userId);
		user =userService.selectIdUserData(user);
		// 取得当前用户的学院Id
		String departmentId = user.getDepartmentId();//ShiroUtil.getCurrentUser().getDepartmentId();
		//User user =ShiroUtil.getCurrentUser();
		/*AwsIams temp = new AwsIams();
		temp.setId(IAMID);*/
		// 取得IAM信息
		AwsIams	iamInfo = awsIamsService.selectIdAwsIamData(departmentId,AWS_ACCOUNTPOOL_USETYPE,AWS_IAM_IAMKIND,
				String_STATE_ACTIVE,AWS_ACCOUNTPOOL_ISACTIVE,AWS_ACCOUNTPOOL_ISDELETE,AWS_ACCOUNT_ACCOUNTSTAUSE,AWS_ACCOUNT_ISACTIVE);

		// 取得失败
		if(iamInfo == null){
			return null;
		}
		if(iamInfo.getRegion()==null||iamInfo.getRegion().equals("")){
			iamInfo.setRegion(TEMPLATE_REGION);
		}

		//取得keyId,key
		String access_key_id = iamInfo.getAccessKeyID();
		String secret_key_id = iamInfo.getAccessKey();
		//创建ec2客户端
    	AmazonEC2 amazonEC2Client = AmazonEC2ClientBuilder.standard()
    		.withRegion(iamInfo.getRegion())
    		.withCredentials(STSUtil.getCredential(access_key_id, secret_key_id))
    		.build();

		//创建密钥对
    	String privateKey = "";
    	try{
    		CreateKeyPairRequest createKeyPairRequest = new CreateKeyPairRequest();
        	createKeyPairRequest.withKeyName(keyName);
        	CreateKeyPairResult createKeyPairResult = amazonEC2Client.createKeyPair(createKeyPairRequest);
        	KeyPair keyPair = createKeyPairResult.getKeyPair();
        	privateKey = keyPair.getKeyMaterial();
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
    	
    	
    	FileInfo fileinfo = uploadsv.writeRemoteFile(keyName, "pem", privateKey);
		
		// 添加密钥文件添加到文件管理表
		fileinfo.setFileName(keyName);
		fileinfo.setFileType("pem");
		fileinfo.setDistinguishFlag("key");
		
		return fileinfo;
		
	}

	/**
	 *   CH version 2.0
	 * 删除密钥
	 */
	@Override
	public Boolean deleteKeyPair(String keyName) {
		// 取得当前用户的学院Id CH
		//String departmentId = ShiroUtil.getCurrentUser().getDepartmentId();
		String userId =ShiroUtil.getCurrentUserId();
		User user =new User();
		user.setUserId(userId);
		user =userService.selectIdUserData(user);
		// 取得当前用户的学院Id
		String departmentId = user.getDepartmentId();
//		AwsIams temp = new AwsIams();
//		temp.setId(IAMID);

		// 取得IAM信息 CH
		AwsIams iamInfo = awsIamsService.selectIdAwsIamData(departmentId,AWS_ACCOUNTPOOL_USETYPE,AWS_IAM_IAMKIND,
				String_STATE_ACTIVE
				,AWS_ACCOUNTPOOL_ISACTIVE,AWS_ACCOUNTPOOL_ISDELETE,AWS_ACCOUNT_ACCOUNTSTAUSE,AWS_ACCOUNT_ISACTIVE);

		// 取得失败
		if(iamInfo == null){
			return false;
		}
		if(iamInfo.getRegion()==null||iamInfo.getRegion().equals("")){
			iamInfo.setRegion(TEMPLATE_REGION);
		}
		
		//创建ec2客户端
    	AmazonEC2 amazonEC2Client = AmazonEC2ClientBuilder.standard()
    		.withRegion(iamInfo.getRegion())
    		.withCredentials(STSUtil.getCredential( iamInfo.getAccessKeyID(), iamInfo.getAccessKey()))
    		.build();
    	DeleteKeyPairRequest deleteKeyPairRequest = new DeleteKeyPairRequest().withKeyName(keyName);
    	try {
    		amazonEC2Client.deleteKeyPair(deleteKeyPairRequest);
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
    	
		return true;
	}
	/**
	 * CH version 2.0
	 * @param keyName
	 * @param experimentId
	 * @return
	 */
	@Override
	public FileInfo createExperimentKeyPair(String keyName,String experimentId){
		// 从数据中判断密钥是否存在
		Boolean eflag = fileService.checkKeyPairExist(keyName);

		// 密钥存在，返回false
		if(eflag){
			return null;
		}

		// 取得当前用户的学院Id
		String userId =ShiroUtil.getCurrentUserId();
		User user =new User();
		user.setUserId(userId);
		user =userService.selectIdUserData(user);
		// 取得当前用户的学院Id
		String departmentId = user.getDepartmentId();
		//String departmentId = ShiroUtil.getCurrentUser().getDepartmentId();
		//User user =ShiroUtil.getCurrentUser();
		/*AwsIams temp = new AwsIams();
		temp.setId(IAMID);*/
		// 取得IAM信息
		AwsIams iamInfo = awsIamsService.selectIdAwsIamData(departmentId,AWS_ACCOUNTPOOL_USETYPE,AWS_IAM_IAMKIND,
				String_STATE_ACTIVE,AWS_ACCOUNTPOOL_ISACTIVE,AWS_ACCOUNTPOOL_ISDELETE,AWS_ACCOUNT_ACCOUNTSTAUSE,AWS_ACCOUNT_ISACTIVE);

		// 取得失败
		if(iamInfo == null){
			return null;
		}
		String region=  experimentService.getExperimentRegion(experimentId);
		iamInfo.setRegion(region);

		//取得keyId,key
		String access_key_id = iamInfo.getAccessKeyID();
		String secret_key_id = iamInfo.getAccessKey();
		//创建ec2客户端
		AmazonEC2 amazonEC2Client = AmazonEC2ClientBuilder.standard()
				.withRegion(iamInfo.getRegion())
				.withCredentials(STSUtil.getCredential(access_key_id, secret_key_id))
				.build();

		//创建密钥对
		String privateKey = "";
		try{
			CreateKeyPairRequest createKeyPairRequest = new CreateKeyPairRequest();
			createKeyPairRequest.withKeyName(keyName);
			CreateKeyPairResult createKeyPairResult = amazonEC2Client.createKeyPair(createKeyPairRequest);
			KeyPair keyPair = createKeyPairResult.getKeyPair();
			privateKey = keyPair.getKeyMaterial();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}


		FileInfo fileinfo = uploadsv.writeRemoteFile(keyName, "pem", privateKey);

		// 添加密钥文件添加到文件管理表
		fileinfo.setFileName(keyName);
		fileinfo.setFileType("pem");
		fileinfo.setDistinguishFlag("key");

		return fileinfo;

	}

	/**
	 *  CH version 2.0
	 *  课程删除密钥
	 */
	@Override
	public Boolean deleteExperimentKeyPair(String keyName,String experimentId) {
		// 取得当前用户的学院id CH
		String userId =ShiroUtil.getCurrentUserId();
		User user =new User();
		user.setUserId(userId);
		user =userService.selectIdUserData(user);
		// 取得当前用户的学院Id
		String departmentId = user.getDepartmentId();
		//String departmentId = ShiroUtil.getCurrentUser().getDepartmentId();
//		AwsIams temp = new AwsIams();
//		temp.setId(IAMID);

		// 取得IAM信息 CH
		AwsIams iamInfo = awsIamsService.selectIdAwsIamData(departmentId,AWS_ACCOUNTPOOL_USETYPE,AWS_IAM_IAMKIND,
				String_STATE_ACTIVE,AWS_ACCOUNTPOOL_ISACTIVE,AWS_ACCOUNTPOOL_ISDELETE,AWS_ACCOUNT_ACCOUNTSTAUSE,AWS_ACCOUNT_ISACTIVE);

		// 取得失败
		if(iamInfo == null){
			return false;
		}
		String region=  experimentService.getExperimentRegion(experimentId);
		iamInfo.setRegion(region);
		//创建ec2客户端
		AmazonEC2 amazonEC2Client = AmazonEC2ClientBuilder.standard()
				.withRegion(iamInfo.getRegion())
				.withCredentials(STSUtil.getCredential( iamInfo.getAccessKeyID(), iamInfo.getAccessKey()))
				.build();
		DeleteKeyPairRequest deleteKeyPairRequest = new DeleteKeyPairRequest().withKeyName(keyName);
		try {
			amazonEC2Client.deleteKeyPair(deleteKeyPairRequest);
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}

		return true;
	}
}
