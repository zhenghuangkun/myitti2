package com.awslabplatform.aws;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;



import org.json.JSONObject;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.amazonaws.services.securitytoken.model.GetFederationTokenRequest;
import com.amazonaws.services.securitytoken.model.GetFederationTokenResult;


/**
 * 联盟登录实现类
 * @author yuzhh
 *
 */
/*@Service("FederationImpl")*/
public class FederationImpl {

	/**
	 * 获取联盟登录到AWS 控制台指定功能URL
	 */
	public String getAwsFederationeUrl(FederationInfo federationInfo) throws Exception{
		
		/*******************************************
		 * (1)重要参数的判定
		 ******************************************/
		if(federationInfo.getAccess_key_id() == null){
			
			throw new Exception("access_key_id cont not be null");
		}
		
		/**
		 * access_key 参数判断
		 */
		if(federationInfo.getSecret_key() == null){
			
			throw new Exception("secret_key cont not be null");
		}
		
		/**
		 * issuerURL 参数判断
		 */
		if(federationInfo.getIssuerURL() == null){
			
			throw new Exception("issuerURL cont not be null");
		}
		
		/**
		 * consoleURL 参数判断
		 */
		if(federationInfo.getConsoleURL() == null){
			
			throw new Exception("consoleURL cont not be null");
		}
		
		/**
		 * 如果用户名传入为空，设置默认名字
		 */
		if(federationInfo.getRealName() == null){
			
			federationInfo.setRealName("aws");
		}
		
		/**
		 * 如果STS 凭证持续时间未设置，默认一个小时
		 */
		if(federationInfo.getDurationSeconds() == null){
			
			federationInfo.setDurationSeconds(3600);
		}
		
		/**
		 * Policys 参数判断
		 */
		if(federationInfo.getPolicy() == null){
			
			throw new Exception("Policys cont not be null");
		}
		
		/******************************
		 * (2)获取凭证
		 *****************************/
		BasicAWSCredentials credentials = new BasicAWSCredentials(federationInfo.getAccess_key_id(),federationInfo.getSecret_key());
		//AWSSecurityTokenServiceClient stsClient = new AWSSecurityTokenServiceClient(credentials);
		AWSSecurityTokenService stsClient = AWSSecurityTokenServiceClientBuilder.standard()
				.withRegion(Regions.CN_NORTH_1)
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.build();
		GetFederationTokenRequest getFederationTokenRequest = new GetFederationTokenRequest();
		getFederationTokenRequest.setDurationSeconds(federationInfo.getDurationSeconds());
		getFederationTokenRequest.setName(federationInfo.getRealName());
		
		/**
		 * 设置临时凭证策略
		 */
		getFederationTokenRequest.setPolicy(federationInfo.getPolicy());

		/**
		 * 获取联盟登录临时凭证
		 * A sample policy for accessing Amazon Simple Notification Service (Amazon SNS) in the console.
		 */
		GetFederationTokenResult federationTokenResult = stsClient.getFederationToken(getFederationTokenRequest);
		Credentials federatedCredentials = federationTokenResult.getCredentials();
		
		
		/************************************
		 * (3)获取联盟登录token
		 ************************************/
		// Create the sign-in token using temporary credentials,
		// including the access key ID, secret access key, and security token.
		String sessionJson = String.format("{\"%1$s\":\"%2$s\",\"%3$s\":\"%4$s\",\"%5$s\":\"%6$s\"}",
				"sessionId",federatedCredentials.getAccessKeyId(),
				"sessionKey", federatedCredentials.getSecretAccessKey(),
				"sessionToken", federatedCredentials.getSessionToken());
		
		String getSigninTokenURL = FederationInfo.getSigninurl() + 
				"?Action=getSigninToken" +
				"&SessionType=json&Session=" + URLEncoder.encode(sessionJson,"UTF-8");

		/**
		 * URL解析
		 */
		URL url = new URL(getSigninTokenURL);
		
		/**
		 * 发送请求到aws 联盟终端节点获取sign-in token
		 */
		URLConnection conn = url.openConnection();

		/**
		 * 读取联盟终端节点返回的sign-in token
		 */
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String returnContent = bufferReader.readLine();

		/**
		 * 将返回的json串转json对象并且获取SigninToken 字符串信息
		 */
		String signinToken = new JSONObject(returnContent).getString("SigninToken");
		
		
		/**********************************************
		 * （4）组装AWS 控制台登录URL
		 **********************************************/
		String signinTokenParameter = "&SigninToken=" + URLEncoder.encode(signinToken, "UTF-8");
		String issuerParameter = "&Issuer=" + URLEncoder.encode(federationInfo.getIssuerURL(), "UTF-8");
		String destinationParameter = "&Destination=" + URLEncoder.encode(federationInfo.getConsoleURL(), "UTF-8");
		String loginURL = FederationInfo.getSigninurl() + "?Action=login" + signinTokenParameter + issuerParameter + destinationParameter;
		
		return loginURL;
	}

}
