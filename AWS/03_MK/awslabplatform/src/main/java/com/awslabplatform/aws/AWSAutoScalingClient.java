package com.awslabplatform.aws;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.autoscaling.AmazonAutoScaling;
import com.amazonaws.services.autoscaling.AmazonAutoScalingClientBuilder;
import com.amazonaws.services.autoscaling.model.AutoScalingGroup;
import com.amazonaws.services.autoscaling.model.DeleteAutoScalingGroupRequest;
import com.amazonaws.services.autoscaling.model.DeleteLaunchConfigurationRequest;
import com.amazonaws.services.autoscaling.model.DescribeAutoScalingGroupsResult;
import com.amazonaws.services.autoscaling.model.DescribeLaunchConfigurationsResult;
import com.amazonaws.services.autoscaling.model.LaunchConfiguration;

public class AWSAutoScalingClient {
	private AWSCredentialsProvider credentialsProvider;
	private List<Regions> regionList = new ArrayList<Regions>();
	private static Logger log = LoggerFactory.getLogger(AWSResourceClient.class);
	
	public AWSAutoScalingClient(AWSCredentialsProvider credentialsProvider){
		this.credentialsProvider=credentialsProvider;
		regionList.add(Regions.CN_NORTH_1);
		regionList.add(Regions.CN_NORTHWEST_1);
	}
	
	public void rmAutoScaling(){
		for(Regions region:regionList){
			AmazonAutoScaling autoscalingclient = AmazonAutoScalingClientBuilder.standard()
			.withCredentials(credentialsProvider)
			.withRegion(region)
			.build();
			
			DescribeAutoScalingGroupsResult gResult = autoscalingclient.describeAutoScalingGroups();
			List<AutoScalingGroup> autoScalingGroups = gResult.getAutoScalingGroups();
			for(AutoScalingGroup autoScalingGroup:autoScalingGroups){
				DeleteAutoScalingGroupRequest request = new DeleteAutoScalingGroupRequest()
					.withAutoScalingGroupName(autoScalingGroup.getAutoScalingGroupName())
					.withForceDelete(true);
				autoscalingclient.deleteAutoScalingGroup(request);
			}
			rmAutoScalingItem(autoscalingclient);
		}
	}
	
	private void rmAutoScalingItem(AmazonAutoScaling autoscalingclient){
		
		DescribeLaunchConfigurationsResult result = autoscalingclient.describeLaunchConfigurations();
		List<LaunchConfiguration> launchConfigurations = result.getLaunchConfigurations();
		
		for(LaunchConfiguration launchConfiguration :launchConfigurations){
			DeleteLaunchConfigurationRequest request = new DeleteLaunchConfigurationRequest()
				.withLaunchConfigurationName(launchConfiguration.getLaunchConfigurationName());
			autoscalingclient.deleteLaunchConfiguration(request);
		}
	}
}
