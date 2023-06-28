package com.awslabplatform_admin.service.amazonaws.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.amazonaws.services.cloudformation.AmazonCloudFormation;
import com.amazonaws.services.cloudformation.AmazonCloudFormationClientBuilder;
import com.amazonaws.services.cloudformation.model.AlreadyExistsException;
import com.amazonaws.services.cloudformation.model.AmazonCloudFormationException;
import com.amazonaws.services.cloudformation.model.CreateStackRequest;
import com.amazonaws.services.cloudformation.model.CreateStackResult;
import com.amazonaws.services.cloudformation.model.DeleteStackRequest;
import com.amazonaws.services.cloudformation.model.DescribeStackResourcesRequest;
import com.amazonaws.services.cloudformation.model.DescribeStackResourcesResult;
import com.amazonaws.services.cloudformation.model.DescribeStacksRequest;
import com.amazonaws.services.cloudformation.model.DescribeStacksResult;
import com.amazonaws.services.cloudformation.model.InsufficientCapabilitiesException;
import com.amazonaws.services.cloudformation.model.LimitExceededException;
import com.amazonaws.services.cloudformation.model.Stack;
import com.amazonaws.services.cloudformation.model.StackResource;
import com.amazonaws.services.cloudformation.model.TokenAlreadyExistsException;
import com.awslabplatform_admin.entity.AwsStack;
import com.awslabplatform_admin.service.amazonaws.StackService;
import com.awslabplatform_admin.util.FileUtil;
import com.awslabplatform_admin.util.amazonaws.STSUtil;

/**
 * AWS堆栈Services
 * @author weixin
 * @version 2018年4月9日
 */
@Service("StackService")
public class StackServiceImpl implements StackService{
	/**日志*/
    private static Logger log = LoggerFactory.getLogger(StackServiceImpl.class);
	/**
	 * 创建堆栈
	 */
	@Override
	public String createStack(AwsStack awsStack) {
		/*获取凭证对象*/
		AmazonCloudFormation cloudFormation = AmazonCloudFormationClientBuilder.standard()
				.withRegion(awsStack.getRegion())
				.withCredentials(STSUtil.getCredential(awsStack.getAccessKeyID(), awsStack.getAccessKey()))
				.build();
		/*堆栈创建请求对象*/
		String tmplBody = FileUtil.readRemoteFile(awsStack.getTmplUrl());
		//判断文件内容是否为空
		if ( "".equals(tmplBody)) {
			return "";
		}
		CreateStackRequest createStackRequest = new CreateStackRequest()
			.withTemplateBody(tmplBody)
			.withTags(awsStack.getTags())
			.withStackName(awsStack.getStackName()).withParameters(awsStack.getParameters());
		/*创建堆栈*/
		CreateStackResult createStackResult = null; 
		try {
			createStackResult = cloudFormation.createStack(createStackRequest);
		} catch (LimitExceededException e) {
			log.error(e.getErrorCode() + " : " +  e.getErrorMessage());
			return "";
		} catch (AlreadyExistsException e) {
			log.error(e.getErrorCode() + " : " +  e.getErrorMessage());
			return "";
		} catch (TokenAlreadyExistsException e) {
			log.error(e.getErrorCode() + " : " +  e.getErrorMessage());
			return "";
		} catch (InsufficientCapabilitiesException e) {
			log.error(e.getErrorCode() + " : " +  e.getErrorMessage());
			return "";
		} catch (Exception e) {
			log.error(e.getMessage());
			return "";
		}
		/*返回堆栈ID*/
		return createStackResult.getStackId();
	}
	
	/**
	 * 获取堆栈描述
	 * @param  awsStack
	 */
	@Override
	public List<Stack> getDescribeStacks(AwsStack awsStack){
		/*设置凭证*/
		AmazonCloudFormation cloudFormation = AmazonCloudFormationClientBuilder.standard()
				.withRegion(awsStack.getRegion())
				.withCredentials(STSUtil.getCredential(awsStack.getAccessKeyID(), awsStack.getAccessKey()))
				.build();
		/*查询堆栈*/
		DescribeStacksRequest describeStacksRequest = new DescribeStacksRequest().withStackName(awsStack.getStackName());
		DescribeStacksResult stackResult;
		try {
			 stackResult = cloudFormation.describeStacks(describeStacksRequest);
		} catch ( AmazonCloudFormationException e) {
			log.error(e.getErrorCode() + " : " +  e.getErrorMessage());
			return null;
		} catch ( Exception e) {
			log.error(e.getMessage());
			return null;
		}
		/*返回堆栈集*/
		return stackResult.getStacks();
	}

	/**
	 * 获取堆栈资源信息
	 */
	@Override
	public List<StackResource>  describeStackResources(AwsStack awsStack) {
		/*设置凭证*/
		AmazonCloudFormation cloudFormation = AmazonCloudFormationClientBuilder.standard()
				.withRegion(awsStack.getRegion())
				.withCredentials(STSUtil.getCredential(awsStack.getAccessKeyID(), awsStack.getAccessKey()))
				.build();
		DescribeStackResourcesRequest describeStackResourceRequest = new DescribeStackResourcesRequest().withStackName(awsStack.getStackName());
		DescribeStackResourcesResult sescribeStackResourceResult = null;
		try {
			 sescribeStackResourceResult = cloudFormation.describeStackResources(describeStackResourceRequest);
		} catch (AmazonCloudFormationException e) {
			log.error(e.getErrorCode() + " : " +  e.getErrorMessage());
			return null;
		}
		
		return sescribeStackResourceResult.getStackResources();
	}

	/**
	 * 删除堆栈
	 */
	@Override
	public Boolean deleteStack(AwsStack awsStack) {
		/*设置凭证*/
		AmazonCloudFormation cloudFormation = AmazonCloudFormationClientBuilder.standard()
				.withRegion(awsStack.getRegion())
				.withCredentials(STSUtil.getCredential(awsStack.getAccessKeyID(), awsStack.getAccessKey()))
				.build();
		
		DeleteStackRequest deleteStackRequest = new DeleteStackRequest().withStackName(awsStack.getStackName());
		try {
			cloudFormation.deleteStack(deleteStackRequest);
		} catch (TokenAlreadyExistsException e) {
			log.error(e.getErrorCode() + " : " +  e.getErrorMessage());
			return false;
		} catch ( AmazonCloudFormationException e) {
			log.error(e.getErrorCode() + " : " +  e.getErrorMessage());
			return false;
		} catch ( Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * 计算模板估价（暂时不支持中国地区）
	 * @author weixin
	 * @version 2018年4月10日
	 * @param awsStack
	 * @return
	 */
//	@Override
//	public EstimateTemplateCostResult estimateTemplateCost(AwsStack awsStack) {
//		/*设置凭证*/
//		AmazonCloudFormation cloudFormation = AmazonCloudFormationClientBuilder.standard()
//				.withRegion(awsStack.getRegion())
//				.withCredentials(STSUtil.getCredential(awsStack.getAccessKeyID(), awsStack.getAccessKey()))
//				.build();
//		/*堆栈创建请求对象*/
//		String tmplBody = FileUtil.readRemoteFile(awsStack.getTmplUrl());
//		EstimateTemplateCostRequest estimateTemplateCostRequest = new EstimateTemplateCostRequest().withTemplateBody(tmplBody);
//		EstimateTemplateCostResult stimateTemplateCostResult = cloudFormation.estimateTemplateCost(estimateTemplateCostRequest);
//		return stimateTemplateCostResult;
//	}
}
