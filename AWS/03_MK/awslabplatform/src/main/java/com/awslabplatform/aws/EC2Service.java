package com.awslabplatform.aws;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.CreateKeyPairRequest;
import com.amazonaws.services.ec2.model.CreateKeyPairResult;
import com.amazonaws.services.ec2.model.DeleteKeyPairRequest;
import com.amazonaws.services.ec2.model.KeyPair;
import com.awslabplatform.entity.CredentialsAndRegion;


public class EC2Service {
	private AmazonEC2 amazonEC2Client;
	public EC2Service(CredentialsAndRegion credentialsAndRegion){
		//创建ec2客户端
    	 amazonEC2Client = AmazonEC2ClientBuilder.standard()
    		.withRegion(credentialsAndRegion.getRegion())
    		.withCredentials(STSUtil.getCredential(credentialsAndRegion.getAccessKeyID(), credentialsAndRegion.getAccessKey()))
    		.build();
	}
	public KeyPair createKeyPair(String keyName){
		//创建密钥对
    	CreateKeyPairRequest createKeyPairRequest = new CreateKeyPairRequest(keyName);
    	CreateKeyPairResult createKeyPairResult = amazonEC2Client.createKeyPair(createKeyPairRequest);
    	KeyPair keyPair = createKeyPairResult.getKeyPair();
    	return keyPair;
	}
	public void deleteKeyPair(String keyName){
		DeleteKeyPairRequest deleteKeyPairRequest = new DeleteKeyPairRequest(keyName);
		amazonEC2Client.deleteKeyPair(deleteKeyPairRequest);
	}
}
