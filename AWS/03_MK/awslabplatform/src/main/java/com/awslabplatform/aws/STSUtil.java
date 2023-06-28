package com.awslabplatform.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.auth.STSAssumeRoleSessionCredentialsProvider;
import com.amazonaws.auth.STSSessionCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.amazonaws.services.securitytoken.model.AssumeRoleResult;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.amazonaws.services.securitytoken.model.GetSessionTokenRequest;
import com.amazonaws.services.securitytoken.model.GetSessionTokenResult;

public class STSUtil {
	/**
	 * 获得临时凭证
	 * @return AWSStaticCredentialsProvider
	 */
	public static AWSCredentialsProvider getTempToken(){
		//启动sts服务		
		AWSSecurityTokenService sts_client = AWSSecurityTokenServiceClientBuilder.standard()
				.withRegion(Regions.AP_NORTHEAST_2)
				.withCredentials(getToken())//但是在启动sts服务的时候先要有凭证
				.build();
		AssumeRoleRequest assumeRoleRequest = new AssumeRoleRequest().withRoleArn("arn:aws:iam::587776956497:role/EC2FULL");
		AssumeRoleResult assumeRole = sts_client.assumeRole(assumeRoleRequest);
		Credentials credentials  = assumeRole.getCredentials();//用assumeRole可以得到凭证
		
		//得到临时凭证
		GetSessionTokenRequest session_token_request = new GetSessionTokenRequest();
		session_token_request.setDurationSeconds(7200); // 7200秒
		GetSessionTokenResult session_token_result = sts_client.getSessionToken(session_token_request);
		Credentials session_creds = session_token_result.getCredentials();
		
		BasicSessionCredentials sessionCredentials = new BasicSessionCredentials(
				   session_creds.getAccessKeyId(),
				   session_creds.getSecretAccessKey(),
				   session_creds.getSessionToken());
		return new AWSStaticCredentialsProvider(sessionCredentials);
	}
	public static AWSCredentialsProvider getToken(){
		//先得到本地默认凭证
		ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
		//AWSCredentials credentials = credentialsProvider.getCredentials();
		return credentialsProvider;
	}
	/**
	 * 获取调用API凭证
	 * @param access_key_id
	 * @param secret_key_id
	 * @return
	 */
	public static AWSCredentialsProvider getCredential(String access_key_id,String secret_key_id){
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(access_key_id, secret_key_id);
		return new AWSStaticCredentialsProvider(awsCreds);
	}
	public static AWSCredentialsProvider  getRoleCredential(){
		//启动sts服务
		AWSSecurityTokenService sts_client = AWSSecurityTokenServiceClientBuilder.standard()
				.withRegion(Regions.AP_NORTHEAST_2)
				.withCredentials(getToken())//但是在启动sts服务的时候先要有凭证
				.build();
		AssumeRoleRequest assumeRoleRequest = new AssumeRoleRequest()
			.withRoleArn("arn:aws:iam::587776956497:role/EC2FULL")
			.withDurationSeconds(3600)
			.withRoleSessionName("Bob");
		AssumeRoleResult assumeRole = sts_client.assumeRole(assumeRoleRequest);
		Credentials credentials  = assumeRole.getCredentials();//用assumeRole可以得到凭证
		BasicSessionCredentials temporaryCredentials = new BasicSessionCredentials(credentials.getAccessKeyId(), credentials.getSecretAccessKey(), credentials.getSessionToken());
		
		return new STSSessionCredentialsProvider(temporaryCredentials);
	}
}
