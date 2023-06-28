package com.awslabplatform_admin.service.amazonaws;
import java.util.List;

import com.amazonaws.services.ec2.model.Instance;
import com.awslabplatform_admin.entity.AwsStack;
import com.awslabplatform_admin.entity.InstanceImage;
public interface AmazonEC2Service {
	
	/**
	 * 获取EC2实例描述
	 * @author weixin
	 * @version 2018年4月10日
	 * @param instanceIds
	 */
	public List<Instance> describeInstances(AwsStack awsStack);
	
	/**
	 * 创建实例镜像
	 * @author weixin
	 * @version 2018年4月13日
	 * @param awsStack
	 */
	public String createImage(AwsStack awsStack, String instanceId, String imageName);
	
	/**
	 * 删除镜像
	 * @author weix
	 * @date 2018年4月16日  
	 * @param instanceImage
	 * @return
	 */
	public Boolean deleteImage(InstanceImage instanceImage);
}