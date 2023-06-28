package com.awslabplatform.aws;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.elasticloadbalancingv2.AmazonElasticLoadBalancing;
import com.amazonaws.services.elasticloadbalancingv2.AmazonElasticLoadBalancingClientBuilder;
import com.amazonaws.services.elasticloadbalancingv2.model.DeleteLoadBalancerRequest;
import com.amazonaws.services.elasticloadbalancingv2.model.DeleteTargetGroupRequest;
import com.amazonaws.services.elasticloadbalancingv2.model.DescribeLoadBalancersRequest;
import com.amazonaws.services.elasticloadbalancingv2.model.DescribeLoadBalancersResult;
import com.amazonaws.services.elasticloadbalancingv2.model.DescribeTargetGroupsRequest;
import com.amazonaws.services.elasticloadbalancingv2.model.DescribeTargetGroupsResult;
import com.amazonaws.services.elasticloadbalancingv2.model.LoadBalancer;
import com.amazonaws.services.elasticloadbalancingv2.model.TargetGroup;

public class AWSELBClient {
	private AWSCredentialsProvider credentialsProvider;
	private List<Regions> regionList = new ArrayList<Regions>();
	private static Logger log = LoggerFactory.getLogger(AWSResourceClient.class);
	public AWSELBClient(AWSCredentialsProvider credentialsProvider){
		this.credentialsProvider=credentialsProvider;
		regionList.add(Regions.CN_NORTH_1);
		regionList.add(Regions.CN_NORTHWEST_1);
	}
	/**
	 * 删除负载均衡
	 */
	public void rmELBs(){
		for(Regions region:regionList){
			AmazonElasticLoadBalancing elbclient = AmazonElasticLoadBalancingClientBuilder.standard()
					.withCredentials(credentialsProvider)
					.withRegion(region)
					.build();
				
			//调用删除方法
			rmElb(elbclient);
		}
	}
	//删除负载均衡
	private void rmElb(AmazonElasticLoadBalancing elbclient){
		DescribeLoadBalancersRequest dRequest = new DescribeLoadBalancersRequest();
		DescribeLoadBalancersResult dResult = elbclient.describeLoadBalancers(dRequest);
		List<LoadBalancer> loadBalancers = dResult.getLoadBalancers();
		for(LoadBalancer elb:loadBalancers){
			DeleteLoadBalancerRequest request = new DeleteLoadBalancerRequest().withLoadBalancerArn(elb.getLoadBalancerArn());
			try {
				elbclient.deleteLoadBalancer(request);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
				System.out.println("删除负载均衡 失败！"+e.getMessage());
			}
		}
		rmTargetGroup(elbclient);
	}
	//删除目标群组
	private void rmTargetGroup(AmazonElasticLoadBalancing elbclient){
		DescribeTargetGroupsRequest tGrequest = new DescribeTargetGroupsRequest();
		DescribeTargetGroupsResult result = elbclient.describeTargetGroups(tGrequest);
		List<TargetGroup> targetGroups = result.getTargetGroups();
		for(TargetGroup targetGroup:targetGroups){
			DeleteTargetGroupRequest request = new DeleteTargetGroupRequest().withTargetGroupArn(targetGroup.getTargetGroupArn());
			elbclient.deleteTargetGroup(request);
		}
	}
}
