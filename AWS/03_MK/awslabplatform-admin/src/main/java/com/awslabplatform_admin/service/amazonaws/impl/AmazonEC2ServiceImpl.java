package com.awslabplatform_admin.service.amazonaws.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.CreateImageRequest;
import com.amazonaws.services.ec2.model.CreateImageResult;
import com.amazonaws.services.ec2.model.DeregisterImageRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.awslabplatform_admin.entity.AwsStack;
import com.awslabplatform_admin.entity.InstanceImage;
import com.awslabplatform_admin.service.amazonaws.AmazonEC2Service;
import com.awslabplatform_admin.util.amazonaws.STSUtil;

/**
 * AWSEC2 Services
 * @author weixin
 * @version 2018年4月13日
 */
@Service("AmazonEC2Service")
public class AmazonEC2ServiceImpl implements AmazonEC2Service{
	/**日志*/
    private static Logger log = LoggerFactory.getLogger(AmazonEC2ServiceImpl.class);

	/**
	 * 获取EC2实例信息描述
	 */
	@Override
	public List<Instance> describeInstances(AwsStack awsStack) {
		AmazonEC2 amazonEC2Client = AmazonEC2ClientBuilder.standard()
	    		.withRegion(awsStack.getRegion())
	    		.withCredentials(STSUtil.getCredential(awsStack.getAccessKeyID(), awsStack.getAccessKey()))
	    		.build();
	    DescribeInstancesRequest describeInstancesRequest = new DescribeInstancesRequest().withInstanceIds(awsStack.getInstanceIds());
	    DescribeInstancesResult describeInstancesResult = amazonEC2Client.describeInstances(describeInstancesRequest);
	    List<Instance> instances = new ArrayList<Instance>();
	    for (Reservation reservation : describeInstancesResult.getReservations()) {
	    	instances.add(reservation.getInstances().get(0));
	    }
	    return instances;
	}

	/**
	 * 创建实例镜像
	 */
	@Override
	public String createImage(AwsStack awsStack,String instanceId,String imageName) {
		AmazonEC2 amazonEC2Client = AmazonEC2ClientBuilder.standard()
	    		.withRegion(awsStack.getRegion())
	    		.withCredentials(STSUtil.getCredential(awsStack.getAccessKeyID(), awsStack.getAccessKey()))
	    		.build();
		CreateImageRequest createImageRequest = new CreateImageRequest().withInstanceId(instanceId).withName(imageName);
		CreateImageResult createImageResult = null;
		try {
			createImageResult = amazonEC2Client.createImage(createImageRequest);
		} catch (Exception e) {
			log.error(e.getMessage());
			return "";
		}
		if (createImageResult == null) {
			return "";
		}
		return createImageResult.getImageId();
	}

	/**
	 * 删除镜像
	 * @author weix
	 * @date 2018年4月16日  
	 * @param instanceImage
	 * @return
	 */
	@Override
	public Boolean deleteImage(InstanceImage instanceImage) {
		AmazonEC2 amazonEC2Client = AmazonEC2ClientBuilder.standard()
	    		.withRegion(instanceImage.getRegion())
	    		.withCredentials(STSUtil.getCredential(instanceImage.getAccessKeyID(), instanceImage.getAccessKey()))
	    		.build();
		DeregisterImageRequest deregisterImageRequest = new DeregisterImageRequest().withImageId(instanceImage.getImageId());
		try {
			amazonEC2Client.deregisterImage(deregisterImageRequest);
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		
		return true;
	}


}
