package com.awslabplatform.aws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.cloudformation.AmazonCloudFormation;
import com.amazonaws.services.cloudformation.AmazonCloudFormationClientBuilder;
import com.amazonaws.services.cloudformation.model.CreateStackRequest;
import com.amazonaws.services.cloudformation.model.CreateStackResult;
import com.amazonaws.services.cloudformation.model.Parameter;
import com.awslabplatform.entity.CredentialsAndRegion;

/**
 * 基于模板的aws实验实验类
 * @author lijf
 * date 2018年3月21日 上午9:34:12
 */
public class StackService {
	/**
	 * log
	 */
	private static Logger log = LoggerFactory.getLogger(StackService.class);
	private CredentialsAndRegion credentialsAndRegion;
	
	public StackService(CredentialsAndRegion credentialsAndRegion){
		this.credentialsAndRegion=credentialsAndRegion;
	}
	
	public String starStack(StartStackRequest startStackRequest) {
		AmazonCloudFormation cloudFormation = AmazonCloudFormationClientBuilder.standard()
				.withRegion(credentialsAndRegion.getRegion())
				.withCredentials(STSUtil.getCredential(credentialsAndRegion.getAccessKeyID(), credentialsAndRegion.getAccessKey()))
				.build();
			Parameter keypair = new Parameter().withParameterKey("KeyName").withParameterValue(startStackRequest.getKeyName());
			Parameter AdministratorPassword = new Parameter().withParameterKey("AdministratorPassword").withParameterValue(startStackRequest.getWindowsPassword());
//			Parameter DBPassword = new Parameter().withParameterKey("DBPassword").withParameterValue("DBPassword");
//			Parameter DBUser = new Parameter().withParameterKey("DBUser").withParameterValue("DBUser");
//			Parameter DBRootPassword = new Parameter().withParameterKey("DBRootPassword").withParameterValue("DBRootPassword");
//			Parameter InstanceType = new Parameter().withParameterKey("InstanceType").withParameterValue(startStackRequest.getInstanceType());
			Boolean needPassword = startStackRequest.getTemplateBody().contains("AdministratorPassword");
			CreateStackRequest createStackRequest=null;
			if(needPassword){
			 createStackRequest = new CreateStackRequest()
				.withTemplateBody(startStackRequest.getTemplateBody())
				.withTags(startStackRequest.getTags())
				.withStackName(startStackRequest.getStackName())
				.withParameters(keypair,AdministratorPassword);
			}else{
				createStackRequest = new CreateStackRequest()
				.withTemplateBody(startStackRequest.getTemplateBody())
				.withTags(startStackRequest.getTags())
				.withStackName(startStackRequest.getStackName())
				.withParameters(keypair);
			}
			CreateStackResult createStackResult = cloudFormation.createStack(createStackRequest);
			log.debug("创建堆栈");
			return createStackResult.getStackId();
	}
	
	
	
}
