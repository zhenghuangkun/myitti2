package com.awslabplatform_admin.service.gainAwsIam;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.AccessKey;
import com.amazonaws.services.identitymanagement.model.AttachUserPolicyRequest;
import com.amazonaws.services.identitymanagement.model.CreateAccessKeyRequest;
import com.amazonaws.services.identitymanagement.model.CreateAccessKeyResult;
import com.amazonaws.services.identitymanagement.model.CreateLoginProfileRequest;
import com.amazonaws.services.identitymanagement.model.CreateUserRequest;
import com.amazonaws.services.identitymanagement.model.CreateUserResult;
import com.amazonaws.services.identitymanagement.model.DeleteAccessKeyRequest;
import com.amazonaws.services.identitymanagement.model.DeleteLoginProfileRequest;
import com.amazonaws.services.identitymanagement.model.DeleteUserRequest;
import com.amazonaws.services.identitymanagement.model.DeleteUserResult;
import com.awslabplatform_admin.entity.IAMUserInfo;
import com.awslabplatform_admin.util.PasswordUtil;
/**
 * 
 * @author lijf
 * @date 2019年3月5日 
 */
public class IAMService {
	AmazonIdentityManagement iam ;
	private static AWSCredentialsProvider credentialsProvider;
	private BasicAWSCredentials credential;
	private Regions regions=Regions.CN_NORTH_1;
	/**
	 * 构造方法 ,调用IAMService ，需要管理用户IAM密钥对。
	 * @param accessKeyID account 下的IAM keyID
	 * @param accessKey account 下的IAM keyID
	 * @param Regions regions  可以为null,不设定默认为Regions.CN_NORTH_1
	 * @author Lijf  
	 * @date 2019年3月15日
	 */
	public IAMService(String accessKeyID,String accessKey,Regions regions){
		credential = new BasicAWSCredentials(accessKeyID, accessKey);
		if(regions!=null){
			this.regions=Regions.CN_NORTH_1;
		}
		credentialsProvider= new AWSStaticCredentialsProvider(credential);
		iam = AmazonIdentityManagementClientBuilder.standard()
			.withCredentials(credentialsProvider)
			.withRegion(this.regions)
			.build();
	}
	/**
	 * 创建IAM用户
	 * @param username IAM用户名
	 * @return IAM用户
	 */
	public IAMUserInfo createUser (){
		IAMUserInfo iamUserInfo = new IAMUserInfo();
		String userName="UPT"+System.currentTimeMillis();
		CreateUserRequest request = new CreateUserRequest().withUserName(userName);
		//创建IAM用户
		CreateUserResult response = iam.createUser(request);
		String Arn = response.getUser().getArn();
		String accountID=Arn.substring(Arn.indexOf("::")+2, Arn.lastIndexOf(":"));
		//创建控制台登录密码
		String password = PasswordUtil.NewPasswd(10);		
		createPassword(password, userName);
		//创建密钥对
		AccessKey accessKey = createAccessKey(userName);
		//生成登录链接
		String consoleLoginLink="https://"+accountID+".signin.amazonaws.cn/console";
		iamUserInfo.setIAMName(userName);
		iamUserInfo.setPassword(password);
		iamUserInfo.setAccessKeyID(accessKey.getAccessKeyId());
		iamUserInfo.setAccessKey(accessKey.getSecretAccessKey());
		iamUserInfo.setConsoleLoginLink(consoleLoginLink);
		return iamUserInfo;
	}
	/**
	 * 删除IAM用户
	 * @param userName IAM用户名
	 * @return 
	 */
	public DeleteUserResult deleteUser(String userName,String accessKeyID){
		try {
			//删除用户前要行删除控制台登陆密码。
			deletePassword(userName);
			//删除用户前要行删除AccessKey
			deleteAccessKey(userName,accessKeyID);
			DeleteUserRequest deleteUserRequest = new DeleteUserRequest(userName);
			DeleteUserResult deleteUserResult = iam.deleteUser(deleteUserRequest);
			return deleteUserResult;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 重置登陆控制台密码
	 * @param IAMUserName IAM用户名
	 * @return  newPassword 返回新的密码
	 */
	public String changePassword(String IAMUserName){
		String newPassword = PasswordUtil.NewPasswd(10);
		deletePassword(IAMUserName);
		createPassword(newPassword, IAMUserName);
		return newPassword;
	}
	/**
	 * 为IAM用户附加一个AdministratorAccess策略
	 * @param IAMUserName IAM用户名
	 */
	public void attachPolicyToUser(String IAMUserName){
		AttachUserPolicyRequest request = new AttachUserPolicyRequest()
			.withUserName(IAMUserName)
			.withPolicyArn("arn:aws:iam::aws:policy/AdministratorAccess");
		iam.attachUserPolicy(request);
	}
	/**
	 * 创建秘钥
	 * @param userName
	 * @return
	 */
	private AccessKey createAccessKey(String userName){
		CreateAccessKeyRequest request = new CreateAccessKeyRequest().withUserName(userName);
		CreateAccessKeyResult createAccessKeyResult = iam.createAccessKey(request);		
		return createAccessKeyResult.getAccessKey();
	}
	/**
	 * 删除密码
	 * @param userName 
	 * @param accessKeyID
	 */
	private void deleteAccessKey(String userName,String accessKeyID){
		DeleteAccessKeyRequest request = new DeleteAccessKeyRequest();
		request.withUserName(userName);
		request.withAccessKeyId(accessKeyID);
		iam.deleteAccessKey(request);
	}
	
	/**
	 * 为IAM用户 创建控制台登陆密码
	 * @param password 密码
	 * @param userName IAM用户名
	 */
	private void createPassword(String password,String userName){
		CreateLoginProfileRequest request = new CreateLoginProfileRequest();
		request.withPassword(password)
			.withUserName(userName)
			.withPasswordResetRequired(false);
		iam.createLoginProfile(request);		
		
	}
	/**
	 * 删除控制台密码
	 * @param userName IAM用户名
	 */
	private void deletePassword(String userName){
		DeleteLoginProfileRequest request = new DeleteLoginProfileRequest();
		request.withUserName(userName)
			.withRequestCredentialsProvider(credentialsProvider);
		iam.deleteLoginProfile(request);		
	}
}
