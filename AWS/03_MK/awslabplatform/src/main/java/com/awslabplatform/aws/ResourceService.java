package com.awslabplatform.aws;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.cloudformation.AmazonCloudFormation;
import com.amazonaws.services.cloudformation.AmazonCloudFormationClientBuilder;
import com.amazonaws.services.cloudformation.model.AmazonCloudFormationException;
import com.amazonaws.services.cloudformation.model.DeleteStackRequest;
import com.amazonaws.services.cloudformation.model.DescribeStackEventsRequest;
import com.amazonaws.services.cloudformation.model.DescribeStackEventsResult;
import com.amazonaws.services.cloudformation.model.DescribeStackResourcesRequest;
import com.amazonaws.services.cloudformation.model.DescribeStackResourcesResult;
import com.amazonaws.services.cloudformation.model.StackEvent;
import com.amazonaws.services.cloudformation.model.StackResource;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.resourcegroupstaggingapi.AWSResourceGroupsTaggingAPI;
import com.amazonaws.services.resourcegroupstaggingapi.AWSResourceGroupsTaggingAPIClientBuilder;
import com.amazonaws.services.resourcegroupstaggingapi.model.GetResourcesRequest;
import com.amazonaws.services.resourcegroupstaggingapi.model.GetResourcesResult;
import com.amazonaws.services.resourcegroupstaggingapi.model.ResourceTagMapping;
import com.amazonaws.services.resourcegroupstaggingapi.model.TagFilter;
import com.awslabplatform.entity.CredentialsAndRegion;

/**
 * 
 * @author lijf
 * date 2018年3月21日 上午10:42:17
 */
public  class ResourceService {
	/**
	 * log
	 */
	private static Logger log = LoggerFactory.getLogger(ResourceService.class);
	private AWSResourceGroupsTaggingAPI tagClient;
	private CredentialsAndRegion credentialsAndRegion;
	public ResourceService(CredentialsAndRegion credentialsAndRegion){
		this.credentialsAndRegion = credentialsAndRegion;
		tagClient = AWSResourceGroupsTaggingAPIClientBuilder
				.standard()
				.withRegion(credentialsAndRegion.getRegion())
				.build();
	}
	/**
	 * 根据标签搜索已存在的资源
	 * @return List<ResourceTagMapping>
	 */
	public List<Instance> getResourcesByTag(Collection<TagFilter> tagFilters){
		GetResourcesRequest getResourcesRequest = new GetResourcesRequest()
			.withResourceTypeFilters("ec2:instance")
			.withTagFilters(tagFilters);
		GetResourcesResult getResourcesResult = tagClient.getResources(getResourcesRequest);
		List<ResourceTagMapping> resourceTagMappingList = getResourcesResult.getResourceTagMappingList();
		String resourceARN = resourceTagMappingList.get(0).getResourceARN();
		String instanceId = resourceARN.substring(resourceARN.indexOf("i-"));
		Collection<String> instanceIds = new ArrayList<String>();
		instanceIds.add(instanceId);
		return getInstance(instanceIds);
	}
	/**
	 * 用实例Id获得实例
	 * @param instanceId
	 * @return Instance
	 */
	public List<Instance> getInstance(Collection<String> instanceIds){
		if(instanceIds==null||instanceIds.size()==0){
			return null;
		}
		AmazonEC2 amazonEC2Client = AmazonEC2ClientBuilder.standard()
	    		.withRegion(credentialsAndRegion.getRegion())
	    		.withCredentials(STSUtil.getCredential(credentialsAndRegion.getAccessKeyID(), credentialsAndRegion.getAccessKey()))
	    		.build();
		
	    DescribeInstancesRequest request = new DescribeInstancesRequest().withInstanceIds(instanceIds);
	    DescribeInstancesResult response = amazonEC2Client.describeInstances(request);
	    List<Instance> instances = new ArrayList<Instance>();
	    for(Reservation reservation:response.getReservations()){
	    	for(Instance instance:reservation.getInstances()){
	    		instances.add(instance);
	    	}
	    }
		return instances;
	}
	/**
	 * 根据堆栈名称查找实例Id
	 * @param stackName 堆栈名称
	 * @return
	 */
	public Collection<String> getInstanceIdByStackName(String stackName){
		AmazonCloudFormation cloudFormation = AmazonCloudFormationClientBuilder.standard()
				.withRegion(credentialsAndRegion.getRegion())
				.withCredentials(STSUtil.getCredential(credentialsAndRegion.getAccessKeyID(), credentialsAndRegion.getAccessKey()))
				.build();
		DescribeStackResourcesRequest describeStackResourcesRequest = new DescribeStackResourcesRequest()
			.withStackName(stackName);
		
		DescribeStackResourcesResult describeStackResources = cloudFormation.describeStackResources(describeStackResourcesRequest);
		List<StackResource> stackResources = describeStackResources.getStackResources();
		Collection<String> instanceIds = new ArrayList<String>();
		for(StackResource stackResource:stackResources){
			if("AWS::EC2::Instance".equals(stackResource.getResourceType())){
				instanceIds.add(stackResource.getPhysicalResourceId());
			}
		}
		return instanceIds;
	}
	/**
	 * 删除堆栈
	 * @param stackName 堆栈名称
	 */
	public void deleteStack(String stackName) {
		AmazonCloudFormation cloudFormation = AmazonCloudFormationClientBuilder.standard()
				.withRegion(credentialsAndRegion.getRegion())
				.withCredentials(STSUtil.getCredential(credentialsAndRegion.getAccessKeyID(), credentialsAndRegion.getAccessKey()))
				.build();
		DeleteStackRequest deleteStackRequest = new DeleteStackRequest().withStackName(stackName);
		
		cloudFormation.deleteStack(deleteStackRequest);
		log.debug("删除堆栈");
	}
	/**
	 * 获取堆栈启动、关闭状态
	 * @param stackName
	 */
	public List<StackEvent> getStackEvents(String stackNameOrId){
		try {
		AmazonCloudFormation cloudFormation = AmazonCloudFormationClientBuilder.standard()
				.withRegion(credentialsAndRegion.getRegion())
				.withCredentials(STSUtil.getCredential(credentialsAndRegion.getAccessKeyID(), credentialsAndRegion.getAccessKey()))
				.build();
		DescribeStackEventsRequest describeStackEventsRequest = new DescribeStackEventsRequest()
				.withStackName(stackNameOrId);
		
			
		DescribeStackEventsResult stackEventsResult = cloudFormation.describeStackEvents(describeStackEventsRequest);
		List<StackEvent> stackEvents = stackEventsResult.getStackEvents();
		//日志记录最新状态、原因
		log.debug("TYPE："+stackEvents.get(0).getResourceType()+"状态："+stackEvents.get(0).getResourceStatus()+"状态原因："+stackEvents.get(0).getResourceStatusReason());
		
		return stackEvents;
		} catch (AmazonCloudFormationException e) {
			log.error("这个堆栈已经关闭或不存在！");
			return null;
		}
	}
}
