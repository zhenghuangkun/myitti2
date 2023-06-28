package com.awslabplatform.aws;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;

public class AWSDynamoDBClient {
	private AWSCredentialsProvider credentialsProvider;
	private List<Regions> regionList = new ArrayList<Regions>();
	private static Logger log = LoggerFactory.getLogger(AWSResourceClient.class);
	
	public AWSDynamoDBClient(AWSCredentialsProvider credentialsProvider){
		this.credentialsProvider=credentialsProvider;
		regionList.add(Regions.CN_NORTH_1);
		regionList.add(Regions.CN_NORTHWEST_1);
	}
	
	public void rmDynamoDB(){
		for(Regions region:regionList){
			AmazonDynamoDB dyNamoDBclient = AmazonDynamoDBClientBuilder.standard()
				.withCredentials(credentialsProvider)
				.withRegion(region)
				.build();
			ListTablesResult tableResult = dyNamoDBclient.listTables();
			List<String> tableNames = tableResult.getTableNames();
			for(String tableName:tableNames){
				DeleteTableRequest delRequest = new DeleteTableRequest(tableName);
				try {
					dyNamoDBclient.deleteTable(delRequest);
				} catch (Exception e) {
					e.printStackTrace();
					log.error(e.getMessage());
				}
			}
		}
	}
	
}
