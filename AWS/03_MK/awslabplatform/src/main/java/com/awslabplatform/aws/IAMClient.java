package com.awslabplatform.aws;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.AccessKey;
import com.amazonaws.services.identitymanagement.model.AccessKeyMetadata;
import com.amazonaws.services.identitymanagement.model.AttachUserPolicyRequest;
import com.amazonaws.services.identitymanagement.model.AttachedPolicy;
import com.amazonaws.services.identitymanagement.model.CreateAccessKeyRequest;
import com.amazonaws.services.identitymanagement.model.CreateAccessKeyResult;
import com.amazonaws.services.identitymanagement.model.CreateLoginProfileRequest;
import com.amazonaws.services.identitymanagement.model.CreatePolicyRequest;
import com.amazonaws.services.identitymanagement.model.CreatePolicyResult;
import com.amazonaws.services.identitymanagement.model.CreateUserRequest;
import com.amazonaws.services.identitymanagement.model.CreateUserResult;
import com.amazonaws.services.identitymanagement.model.DeleteAccessKeyRequest;
import com.amazonaws.services.identitymanagement.model.DeleteLoginProfileRequest;
import com.amazonaws.services.identitymanagement.model.DeleteUserRequest;
import com.amazonaws.services.identitymanagement.model.DeleteUserResult;
import com.amazonaws.services.identitymanagement.model.DetachUserPolicyRequest;
import com.amazonaws.services.identitymanagement.model.EntityAlreadyExistsException;
import com.amazonaws.services.identitymanagement.model.GetPolicyRequest;
import com.amazonaws.services.identitymanagement.model.GetPolicyResult;
import com.amazonaws.services.identitymanagement.model.GetUserRequest;
import com.amazonaws.services.identitymanagement.model.GetUserResult;
import com.amazonaws.services.identitymanagement.model.Group;
import com.amazonaws.services.identitymanagement.model.ListAccessKeysRequest;
import com.amazonaws.services.identitymanagement.model.ListAccessKeysResult;
import com.amazonaws.services.identitymanagement.model.ListAttachedUserPoliciesRequest;
import com.amazonaws.services.identitymanagement.model.ListAttachedUserPoliciesResult;
import com.amazonaws.services.identitymanagement.model.ListGroupsForUserRequest;
import com.amazonaws.services.identitymanagement.model.ListGroupsForUserResult;
import com.amazonaws.services.identitymanagement.model.ListUserPoliciesRequest;
import com.amazonaws.services.identitymanagement.model.ListUserPoliciesResult;
import com.amazonaws.services.identitymanagement.model.ListUsersResult;
import com.amazonaws.services.identitymanagement.model.Policy;
import com.amazonaws.services.identitymanagement.model.RemoveUserFromGroupRequest;
import com.amazonaws.services.identitymanagement.model.User;
import com.awslabplatform.entity.IAMUserInfo;
import com.awslabplatform.util.PasswordUtil;
/**
 * 
 * @author lijf
 * @date 2019年3月5日 
 */
public class IAMClient {
	AmazonIdentityManagement iam ;
	private static AWSCredentialsProvider credentialsProvider;
	private BasicAWSCredentials credential;
	private Regions regions=Regions.CN_NORTHWEST_1;
	private static Logger log = LoggerFactory.getLogger(AWSResourceClient.class);
	/**
	 * 构造方法 ,调用IAMService ，需要管理用户IAM密钥对。
	 * @param accessKeyID account 下的IAM keyID
	 * @param accessKey account 下的IAM keyID
	 * @param Regions regions  可以为null,不设定默认为Regions.CN_NORTH_1
	 * @author Lijf  
	 * @date 2019年3月15日
	 */
	public IAMClient(String accessKeyID,String accessKey,Regions regions){
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
	public void deleteUser(String userName){
		try {
			//删除用户前要行删除控制台登陆密码。
			deletePassword(userName);
			//移出用户组
			outToGroup(userName);
			//删除用户前要行删除AccessKey
			deleteAccessKey(userName);
			//解除用户所有策略
			detachAllPolicy(userName);
			DeleteUserRequest deleteUserRequest = new DeleteUserRequest(userName);
			DeleteUserResult deleteUserResult = iam.deleteUser(deleteUserRequest);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除用户失败："+e.getMessage());
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
	 * 为IAM用户附加一个AdministratorAccess策略,
	 * 给IAM用户附加一个策略只能用PolicyArn，需要创建策略到账户中存放才会有PolicyArn。
	 * @param IAMUserName IAM用户名
	 */
	public void attachPolicyToUser(String IAMUserName){
		AttachUserPolicyRequest request = new AttachUserPolicyRequest()
			.withUserName(IAMUserName)
			.withPolicyArn("arn:aws:iam::aws:policy/AdministratorAccess");
		iam.attachUserPolicy(request);
	}
	/**
	 * 为用户配置策略
	 * @param IAMUserName  需要配置策略的目标IAM用户名
	 * @param policyDocument 策略json文本,从实验中获取
	 * @param policyName  策略名称 ，不可以为中文
	 * @param accountID IAM所属的accountID
	 */
	public void attachCustomPolicy(String IAMUserName,String policyDocument,String policyName,String accountID){
		Policy policy = createPolicy(policyDocument, policyName,accountID);
		AttachUserPolicyRequest request = new AttachUserPolicyRequest()
		.withUserName(IAMUserName)
		.withPolicyArn(policy.getArn());
		iam.attachUserPolicy(request);
	}
	/**
	 * 解除用户的策略
	 * @param IAMUserName 需要解除策略的目标IAM用户名
	 * @param accountID IAM所属的accountID
	 * @param policyName 添加时的策略名称 
	 */
	public void detachUserPolicy(String IAMUserName,String accountID,String policyName){
		String policyArn="arn:aws-cn:iam::"+accountID+":policy/"+policyName;
		DetachUserPolicyRequest detachUserPolicyRequest = new DetachUserPolicyRequest();
		detachUserPolicyRequest.withUserName(IAMUserName)
			.withPolicyArn(policyArn);
		iam.detachUserPolicy(detachUserPolicyRequest);
	}
	/**
	 * 为用户创建策略
	 * @param policyDocument
	 * @param policyName
	 * @return
	 */
	private Policy createPolicy(String policyDocument,String policyName,String accountID){
		Policy policy;
		String policyArn="arn:aws-cn:iam::"+accountID+":policy/"+policyName;
		
		//创建一个策略，如果已有就查出返回
		
		try {
			CreatePolicyRequest request = new CreatePolicyRequest();
			request.withPolicyDocument(policyDocument)
				.withPolicyName(policyName);
			CreatePolicyResult result = iam.createPolicy(request);
			policy = result.getPolicy();
		} catch (EntityAlreadyExistsException e) {
			GetPolicyRequest getPolicyRequest = new GetPolicyRequest();
			getPolicyRequest.setPolicyArn(policyArn);
			GetPolicyResult getPolicyResult = iam.getPolicy(getPolicyRequest);
			policy = getPolicyResult.getPolicy();
		}
		return policy;
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
	private void deleteAccessKey(String userName){
		ListAccessKeysRequest listAccessKeysRequest = new ListAccessKeysRequest().withUserName(userName);
		ListAccessKeysResult result = iam.listAccessKeys(listAccessKeysRequest);
		List<AccessKeyMetadata> accessKeyMetadatas = result.getAccessKeyMetadata();
		for(AccessKeyMetadata accessKeyMetadata :accessKeyMetadatas ){
			DeleteAccessKeyRequest request = new DeleteAccessKeyRequest();
			request.withUserName(userName);
			request.withAccessKeyId(accessKeyMetadata.getAccessKeyId());
			iam.deleteAccessKey(request);
		}
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
		try {
			iam.deleteLoginProfile(request);
		} catch (Exception e) {
			log.error("删除控制台密码失败："+e.getMessage());
		}		
	}
	/**
	 * 删除 所有iam,集合中的除外
	 * @param iamNames 不删除的iam用户名List
	 */
	public void deleteAlluserExcept(List<String> iamNames){
		
		ListUsersResult result = iam.listUsers();
		List<User> users = result.getUsers();
		for(User user:users){
			boolean needDel=true;
			for(String iamName:iamNames){
				if(iamName.equals(user.getUserName())){
					needDel=false;
					break;
				}
			}
			if(needDel){
				try {
					deleteUser(user.getUserName());
				} catch (Exception e) {
					e.printStackTrace();
					log.error("删除用户失败："+e.getMessage());
				}
			}
		}		
	}
	//解除用户所有策略
	private void detachAllPolicy(String userName) {
		GetUserRequest getUserRequest = new GetUserRequest().withUserName(userName);
		GetUserResult userResult = iam.getUser(getUserRequest);
		User user = userResult.getUser();
		String ArnPrefix=user.getArn().substring(0, user.getArn().lastIndexOf(":")+1);
		ListUserPoliciesRequest listUserPoliciesRequest = new ListUserPoliciesRequest(userName);
		ListUserPoliciesResult result = iam.listUserPolicies(listUserPoliciesRequest);
		ListAttachedUserPoliciesRequest listAttachedUserPoliciesRequest = new ListAttachedUserPoliciesRequest().withUserName(userName);
		ListAttachedUserPoliciesResult listPoliciesResult = iam.listAttachedUserPolicies(listAttachedUserPoliciesRequest);
		List<AttachedPolicy> attachedPolicies = listPoliciesResult.getAttachedPolicies();
		for(AttachedPolicy attachedPolicy:attachedPolicies){
			DetachUserPolicyRequest detachUserPolicyRequest = new DetachUserPolicyRequest()
			.withUserName(userName)
			.withPolicyArn(attachedPolicy.getPolicyArn());
		try {
			//删除附加策略
			iam.detachUserPolicy(detachUserPolicyRequest);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("解除用户（"+userName+"）的附加策略（"+attachedPolicy.getPolicyName()+"）失败："+e.getMessage());
		}
		}
		List<String> policyNames = result.getPolicyNames();
		for(String policyName:policyNames){
			DetachUserPolicyRequest detachUserPolicyRequest = new DetachUserPolicyRequest()
				.withUserName(userName)
				.withPolicyArn(ArnPrefix+"policy/"+policyName);
			try {
				//删除内连策略
				iam.detachUserPolicy(detachUserPolicyRequest);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("解除用户（"+userName+"）的策略（"+policyName+"）失败："+e.getMessage());
			}
		}
	}
	//移出用户组
	private void outToGroup(String userName){
		ListGroupsForUserRequest listGroupsForUserRequest = new ListGroupsForUserRequest(userName);
		ListGroupsForUserResult listResult = iam.listGroupsForUser(listGroupsForUserRequest);
		List<Group> groups = listResult.getGroups();
		for(Group group:groups){
			RemoveUserFromGroupRequest removeUserFromGroupRequest = new RemoveUserFromGroupRequest(group.getGroupName(), userName);
			try {
				iam.removeUserFromGroup(removeUserFromGroupRequest);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("移出用户组失败："+e.getMessage());
			}
		}
	}
}
