package com.awslabplatform.aws;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cloudformation.AmazonCloudFormation;
import com.amazonaws.services.cloudformation.AmazonCloudFormationClientBuilder;
import com.amazonaws.services.cloudformation.model.DeleteStackRequest;
import com.amazonaws.services.cloudformation.model.DescribeStacksResult;
import com.amazonaws.services.cloudformation.model.Stack;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DeleteInternetGatewayRequest;
import com.amazonaws.services.ec2.model.DeleteKeyPairRequest;
import com.amazonaws.services.ec2.model.DeleteNetworkAclRequest;
import com.amazonaws.services.ec2.model.DeleteNetworkInterfaceRequest;
import com.amazonaws.services.ec2.model.DeleteRouteTableRequest;
import com.amazonaws.services.ec2.model.DeleteSecurityGroupRequest;
import com.amazonaws.services.ec2.model.DeleteSubnetRequest;
import com.amazonaws.services.ec2.model.DeleteVpcEndpointsRequest;
import com.amazonaws.services.ec2.model.DeleteVpcPeeringConnectionRequest;
import com.amazonaws.services.ec2.model.DeleteVpcRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.DescribeInternetGatewaysResult;
import com.amazonaws.services.ec2.model.DescribeKeyPairsResult;
import com.amazonaws.services.ec2.model.DescribeNetworkAclsResult;
import com.amazonaws.services.ec2.model.DescribeNetworkInterfacesResult;
import com.amazonaws.services.ec2.model.DescribeRouteTablesResult;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsResult;
import com.amazonaws.services.ec2.model.DescribeSubnetsResult;
import com.amazonaws.services.ec2.model.DescribeVpcEndpointsResult;
import com.amazonaws.services.ec2.model.DescribeVpcPeeringConnectionsResult;
import com.amazonaws.services.ec2.model.DescribeVpcsResult;
import com.amazonaws.services.ec2.model.DetachInternetGatewayRequest;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InternetGateway;
import com.amazonaws.services.ec2.model.InternetGatewayAttachment;
import com.amazonaws.services.ec2.model.KeyPairInfo;
import com.amazonaws.services.ec2.model.NetworkAcl;
import com.amazonaws.services.ec2.model.NetworkInterface;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RouteTable;
import com.amazonaws.services.ec2.model.SecurityGroup;
import com.amazonaws.services.ec2.model.Subnet;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;
import com.amazonaws.services.ec2.model.Vpc;
import com.amazonaws.services.ec2.model.VpcEndpoint;
import com.amazonaws.services.ec2.model.VpcPeeringConnection;
import com.amazonaws.services.glacier.AmazonGlacier;
import com.amazonaws.services.glacier.AmazonGlacierClientBuilder;
import com.amazonaws.services.glacier.model.DeleteVaultRequest;
import com.amazonaws.services.glacier.model.DescribeVaultOutput;
import com.amazonaws.services.glacier.model.ListVaultsRequest;
import com.amazonaws.services.glacier.model.ListVaultsResult;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListVersionsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.S3VersionSummary;
import com.amazonaws.services.s3.model.VersionListing;
/**
 * 删除aws中的资源 
 * @author lijf
 * @date 2019年3月27日 下午1:49:40
 */
public class AWSResourceClient {
	private static AWSCredentialsProvider credentialsProvider;
	private String defaultVpcId;
	private BasicAWSCredentials credential;
	private List<Regions> regionList = new ArrayList<Regions>();
	private static Logger log = LoggerFactory.getLogger(AWSResourceClient.class);
	/**
	 * 
	 * @param accessKeyID 管理者AK
	 * @param accessKey  管理者SK
	 * @author Lijf  
	 * @date 2019年3月27日
	 */
	public AWSResourceClient(String accessKeyID,String accessKey){
		this.credential = new BasicAWSCredentials(accessKeyID, accessKey);
		credentialsProvider= new AWSStaticCredentialsProvider(credential);
		regionList.add(Regions.CN_NORTH_1);
		regionList.add(Regions.CN_NORTHWEST_1);
	}
	/**
	 * 依次删除cloudFormation autoScaling EC2  S3 Glacier Elb DynamoDB
	 */
	public void deleteAwsResouces(){
		try {
			deleteCloudFormation();
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		try {
			rmAutoScaling();
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		try {
			deleteEC2Resources();
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		try {
			deleteS3Resources();
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		try {
			deleteGlacierResources();
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		try {
			rmELBs();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		try {
			rmDynamoDB();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
	/**
	 * 先查询，如果有CloudFormation，该账号中北京区及宁夏区的所有CloudFormation都会被删除
	 */
	public void deleteCloudFormation(){
		for(Regions region:regionList){
			AmazonCloudFormation cloudFormation = AmazonCloudFormationClientBuilder
					.standard()
					.withCredentials(credentialsProvider)
					.withRegion(region)
					.build();
			List<Stack> stacks = getAllCloudFormation(cloudFormation);
			if(stacks!=null&&stacks.size()>0){
				for(Stack stack:stacks){
					DeleteStackRequest request = new DeleteStackRequest().withStackName(stack.getStackName());
					cloudFormation.deleteStack(request);
					System.out.println("操作区域Region:"+region);
					System.out.println("已删除堆栈:"+stack.getStackName());
				}
			}
		}
	}
	/**
	 * 获取单个区域中的所有CloudFormation
	 * @param cloudFormation AmazonCloudFormation
	 * @return 所有堆栈的集合
	 */
	private List<Stack> getAllCloudFormation(AmazonCloudFormation cloudFormation){
		DescribeStacksResult result = cloudFormation.describeStacks();
		List<Stack> stacks = result.getStacks();
		return stacks;
	}
	/**
	 * 删除所有EC2实例 instance
	 */
	public void deleteEC2Resources(){
		for(Regions region:regionList){
			AmazonEC2 ec2Client = AmazonEC2ClientBuilder.standard()
		    		.withRegion(region)
		    		.withCredentials(credentialsProvider)
		    		.build();
			DescribeInstancesResult response = ec2Client.describeInstances();
			List<Reservation> reservations = response.getReservations();
			Collection<String> instanceIds =new ArrayList<String>();
			for(Reservation reservation:reservations){
				List<Instance> instances = reservation.getInstances();
				for(Instance instance:instances){
					if(instance.getState().getCode()!=48)
					instanceIds.add(instance.getInstanceId());
				}
			}
			if(instanceIds.size()>0){
				System.out.println("实例数量："+instanceIds.size());
				TerminateInstancesRequest request = new TerminateInstancesRequest().withInstanceIds(instanceIds);
				ec2Client.terminateInstances(request);
			}
			//在删除vpc前，要先依次删除如下内容，最后删除VPC
			detachGateways(ec2Client);
			rmGateways(ec2Client);
			rmSubnet(ec2Client);
			rmRouteTables(ec2Client);
			rmEndpoints(ec2Client);
			rmSecurity(ec2Client);
			rmVpcPeeringConnections(ec2Client);
			rmNetowrkAcls(ec2Client);
			rmNetowrkInterfaces(ec2Client);
			rmVpc(ec2Client);
			//删除密钥对
			rmKeyPairs(ec2Client);
		}
	}
	/**
	 * 删除所有的存储桶
	 */
	private void deleteS3Resources(){
		for(Regions region:regionList){
			AmazonS3 s3 = AmazonS3ClientBuilder.standard()
					.withRegion(region)
					.withCredentials(credentialsProvider)
					.build();
			//获取所有存储桶
			List<Bucket> buckets = listBuckst(s3);
			for(Bucket bucket:buckets){
		        try {
		        	//以下代码参考自：https://docs.aws.amazon.com/zh_cn/sdk-for-java/v1/developer-guide/examples-s3-buckets.html
		        	//https://github.com/awsdocs/aws-doc-sdk-examples/blob/master/java/example_code/s3/src/main/java/aws/example/s3/DeleteBucket.java
		            ObjectListing object_listing = s3.listObjects(bucket.getName());
		            while (true) {
		                for (Iterator<?> iterator =
		                        object_listing.getObjectSummaries().iterator();
		                        iterator.hasNext();) {
		                    S3ObjectSummary summary = (S3ObjectSummary)iterator.next();
		                    s3.deleteObject(bucket.getName(), summary.getKey());
		                }

		                if (object_listing.isTruncated()) {
		                    object_listing = s3.listNextBatchOfObjects(object_listing);
		                } else {
		                    break;
		                }
		            };

		            VersionListing version_listing = s3.listVersions(
		                    new ListVersionsRequest().withBucketName(bucket.getName()));
		            while (true) {
		                for (Iterator<?> iterator =
		                        version_listing.getVersionSummaries().iterator();
		                        iterator.hasNext();) {
		                    S3VersionSummary vs = (S3VersionSummary)iterator.next();
		                    s3.deleteVersion(
		                    		bucket.getName(), vs.getKey(), vs.getVersionId());
		                }

		                if (version_listing.isTruncated()) {
		                    version_listing = s3.listNextBatchOfVersions(
		                            version_listing);
		                } else {
		                    break;
		                }
		            }

		            s3.deleteBucket(bucket.getName());
		        } catch (AmazonServiceException e) {
		            System.err.println(e.getErrorMessage());
		        }
			}
		}
	}

	private List<Bucket> listBuckst(AmazonS3 s3Client) {
		return s3Client.listBuckets();
	}
	
	private void deleteGlacierResources(){
		for(Regions region:regionList){
			AmazonGlacier client = AmazonGlacierClientBuilder
					.standard()
					.withCredentials(credentialsProvider)
					.withRegion(region)
					.build();
			ListVaultsRequest request = new ListVaultsRequest();
			ListVaultsResult response = client.listVaults(request);
			List<DescribeVaultOutput> vaultList = response.getVaultList();
			for(DescribeVaultOutput valut:vaultList){
				DeleteVaultRequest deleteRequest = new DeleteVaultRequest().withVaultName(valut.getVaultName());
				client.deleteVault(deleteRequest);
			}
		}
	}
	//detach  all gateways associated with the vpc
	private void detachGateways(AmazonEC2 ec2Client){
		DescribeInternetGatewaysResult gatewayResult = ec2Client.describeInternetGateways();
		List<InternetGateway> internetGateways = gatewayResult.getInternetGateways();
		DescribeVpcsResult vpcResult = ec2Client.describeVpcs();
		List<Vpc> vpcs = vpcResult.getVpcs();
		for(Vpc vpc:vpcs){
			if(vpc.isDefault()){
				defaultVpcId=vpc.getVpcId();
				continue;
			}
			for(InternetGateway gateway:internetGateways){
				List<InternetGatewayAttachment> attachments = gateway.getAttachments();
				boolean hasdefaultVpc=false;
				for(InternetGatewayAttachment attachment:attachments){
					String vpcId = attachment.getVpcId();
					if(vpcId.equals(defaultVpcId)){
						hasdefaultVpc=true;
						break;
					}
				}
				if(hasdefaultVpc){
					continue;
				}
				DetachInternetGatewayRequest gatewayRequest = new DetachInternetGatewayRequest()
					.withInternetGatewayId(gateway.getInternetGatewayId())
					.withVpcId(vpc.getVpcId());
				try {
					ec2Client.detachInternetGateway(gatewayRequest);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("解除网关失败！网关Id为："+gateway.getInternetGatewayId());
				}
			}
		}
	}
	/**
	 * 删除网关
	 * @param ec2Client
	 */
	private void rmGateways(AmazonEC2 ec2Client){
		DescribeInternetGatewaysResult gatewayResult = ec2Client.describeInternetGateways();
		List<InternetGateway> internetGateways = gatewayResult.getInternetGateways();
		for(InternetGateway gateway:internetGateways){
			boolean hasDefaultVpc=false;
			List<InternetGatewayAttachment> attachments = gateway.getAttachments();
			for(InternetGatewayAttachment attachment:attachments){
				String vpcId = attachment.getVpcId();
				if(vpcId.equals(defaultVpcId)){
					hasDefaultVpc=true;
					break;
				}
			}
			if(hasDefaultVpc){
				continue;
			}
			DeleteInternetGatewayRequest deleteInternetGatewayRequest = new DeleteInternetGatewayRequest().withInternetGatewayId(gateway.getInternetGatewayId());
			try {
				ec2Client.deleteInternetGateway(deleteInternetGatewayRequest);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("删除网关失败！网关id为："+gateway.getInternetGatewayId());
			}
		}
	}
	//删除路由表
	private void rmRouteTables(AmazonEC2 ec2Client){
		DescribeRouteTablesResult routeTablesResult = ec2Client.describeRouteTables();
		List<RouteTable> routeTables = routeTablesResult.getRouteTables();
		for(RouteTable routeTable:routeTables){
			if(routeTable.getVpcId().equals(defaultVpcId)){
				continue;
			}
			DeleteRouteTableRequest request = new DeleteRouteTableRequest().withRouteTableId(routeTable.getRouteTableId());
			try {
				ec2Client.deleteRouteTable(request);
			} catch (Exception e) {
				//e.printStackTrace();
				log.error("删除路由表失败！路由表id为："+routeTable.getRouteTableId());
				System.out.println("删除路由表失败！路由表id为："+routeTable.getRouteTableId());
			}
		}
	}
	//删除endpoints
	private void rmEndpoints(AmazonEC2 ec2Client){
		DescribeVpcEndpointsResult describeVpcEndpoints = ec2Client.describeVpcEndpoints();
		List<VpcEndpoint> vpcEndpoints = describeVpcEndpoints.getVpcEndpoints();
		List<String> vpcEndpointIds=new ArrayList<String>();
		for(VpcEndpoint vpcEndpoint:vpcEndpoints){
			vpcEndpointIds.add(vpcEndpoint.getVpcEndpointId());
		}
		if(vpcEndpointIds.size()>0){
			DeleteVpcEndpointsRequest request = new DeleteVpcEndpointsRequest().withVpcEndpointIds(vpcEndpointIds);
			try {
				ec2Client.deleteVpcEndpoints(request);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("删除endpoints失败！");
			}
		}
	}
	
	//删除安全组
	private void rmSecurity(AmazonEC2 ec2Client){
		DescribeSecurityGroupsResult describeSecurityGroups = ec2Client.describeSecurityGroups();
		List<SecurityGroup> securityGroups = describeSecurityGroups.getSecurityGroups();
		for(SecurityGroup group:securityGroups){
			if(group.getGroupName().equals("default")){
				continue;
			}
			DeleteSecurityGroupRequest request = new DeleteSecurityGroupRequest().withGroupId(group.getGroupId());
			try {
				ec2Client.deleteSecurityGroup(request);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("删除安全组失败！:"+e.getMessage());
			}
		}
	}
	//删除VpcPeeringConnections
	private void rmVpcPeeringConnections(AmazonEC2 ec2Client){
		DescribeVpcPeeringConnectionsResult result = ec2Client.describeVpcPeeringConnections();
		List<VpcPeeringConnection> vpcPeeringConnections = result.getVpcPeeringConnections();
		for(VpcPeeringConnection vpcPeeringConnection:vpcPeeringConnections){
			DeleteVpcPeeringConnectionRequest request = new DeleteVpcPeeringConnectionRequest().withVpcPeeringConnectionId(vpcPeeringConnection.getVpcPeeringConnectionId());
			try {
				ec2Client.deleteVpcPeeringConnection(request);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("删除VpcPeeringConnection失败！VpcPeeringConnectionId:"+vpcPeeringConnection.getVpcPeeringConnectionId());
			}
		}
	}
	//删除netowrk acl
	private void rmNetowrkAcls(AmazonEC2 ec2Client){
		DescribeNetworkAclsResult result = ec2Client.describeNetworkAcls();
		List<NetworkAcl> networkAcls = result.getNetworkAcls();
		for(NetworkAcl netowrkAcl:networkAcls){
			if(netowrkAcl.isDefault()){
				continue;
			}
			DeleteNetworkAclRequest request = new DeleteNetworkAclRequest().withNetworkAclId(netowrkAcl.getNetworkAclId()); 
			try {
				ec2Client.deleteNetworkAcl(request);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("删除netowrk acl 失败！netowrk acl Id:"+netowrkAcl.getNetworkAclId());
			}
		}
	}
	//删除network interfaces
	private void rmNetowrkInterfaces(AmazonEC2 ec2Client){
		DescribeNetworkInterfacesResult result = ec2Client.describeNetworkInterfaces();
		List<NetworkInterface> networkInterfaces = result.getNetworkInterfaces();
		for(NetworkInterface networkInterface:networkInterfaces){
			DeleteNetworkInterfaceRequest request = new DeleteNetworkInterfaceRequest().withNetworkInterfaceId(networkInterface.getNetworkInterfaceId());
			try {
				ec2Client.deleteNetworkInterface(request);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("删除network interfaces 失败！network interfaces Id:"+networkInterface.getNetworkInterfaceId());
			}
		}
	}
	//删除子网
	private void rmSubnet(AmazonEC2 ec2Client){
		DescribeSubnetsResult result = ec2Client.describeSubnets();
		List<Subnet> subnets = result.getSubnets();
		for(Subnet subnet:subnets){
			if(subnet.getVpcId().equals(defaultVpcId)){
				continue;
			}
			DeleteSubnetRequest request = new DeleteSubnetRequest().withSubnetId(subnet.getSubnetId());
			try {
				ec2Client.deleteSubnet(request);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("删除子网 失败！子网 Id:"+subnet.getSubnetId());
			}
		}
	}
	//删除Vpc
	private void rmVpc(AmazonEC2 ec2Client){
		DescribeVpcsResult vpcResult = ec2Client.describeVpcs();
		List<Vpc> vpcs = vpcResult.getVpcs();
		for(Vpc vpc:vpcs){
			if(vpc.isDefault()){
				continue;
			}
			DeleteVpcRequest request = new DeleteVpcRequest().withVpcId(vpc.getVpcId());
			try {
				ec2Client.deleteVpc(request);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("删除Vpc 失败！Vpc Id:"+vpc.getVpcId());
			}
			
		}
	}
	/**
	 * 删除ELB
	 */
	public void rmELBs(){
		new AWSELBClient(credentialsProvider).rmELBs();
	}
	/**
	 * 删除DynamoDB中所有的表
	 */
	public void rmDynamoDB(){
		new AWSDynamoDBClient(credentialsProvider).rmDynamoDB();
	}
	/**
	 * 删除AutoScaling 及 LaunchConfiguration
	 */
	public void rmAutoScaling(){
		new AWSAutoScalingClient(credentialsProvider).rmAutoScaling();
	}
	/**
	 * 删除所有密钥对
	 * @param ec2Client
	 */
	public void rmKeyPairs(AmazonEC2 ec2Client){
		DescribeKeyPairsResult keyResult = ec2Client.describeKeyPairs();
		List<KeyPairInfo> keyPairs = keyResult.getKeyPairs();
		for(KeyPairInfo keyPairInfo:keyPairs){
			DeleteKeyPairRequest request = new DeleteKeyPairRequest().withKeyName(keyPairInfo.getKeyName());
			ec2Client.deleteKeyPair(request);
		}
	}
}
