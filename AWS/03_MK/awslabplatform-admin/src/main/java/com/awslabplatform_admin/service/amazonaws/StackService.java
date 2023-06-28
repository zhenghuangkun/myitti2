package com.awslabplatform_admin.service.amazonaws;
import java.util.List;

import com.amazonaws.services.cloudformation.model.Stack;
import com.amazonaws.services.cloudformation.model.StackResource;
import com.awslabplatform_admin.entity.AwsStack;
public interface StackService {
	
	/**
	 * 创建堆栈
	 * @author weixin
	 * @version 2018年4月9日
	 * @param awsStack
	 * @return
	 */
	public String createStack(AwsStack awsStack);
	
	/**
	 * 获取堆栈描述
	 * @param  awsStack
	 */
	public List<Stack> getDescribeStacks(AwsStack awsStack);
	
	/**
	 * 获取堆栈资源信息
	 * @author weixin
	 * @version 2018年4月10日
	 * @param awsStack
	 * @return
	 */
	public List<StackResource> describeStackResources(AwsStack awsStack);
	
	/**
	 * 删除堆栈
	 * @author weixin
	 * @version 2018年4月11日
	 * @param awsStack
	 */
	public Boolean deleteStack(AwsStack awsStack);
	
}