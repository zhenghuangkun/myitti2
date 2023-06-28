package com.awslabplatform_admin.service.amazonaws.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.awslabplatform_admin.entity.AwsIams;
import com.awslabplatform_admin.service.amazonaws.S3Service;
import com.awslabplatform_admin.util.PropertyUtil;
import com.awslabplatform_admin.util.amazonaws.STSUtil;

public class S3ServiceImpl implements S3Service {

	@Override
	public File getBillFile(AwsIams iam) {
		//从配置文件中获得accountId和放账单的储存桶名称
		PropertyUtil propertyUtil = new PropertyUtil("payAccount.properties");
		String accountId = propertyUtil.getProperty("accountId");
		if(accountId==null||""==accountId){
			accountId=iam.getAwsAccount();
		}
		String bucket_name = propertyUtil.getProperty("bucket_name");
		String accessKeyID = iam.getAccessKeyID();
		String accessKey = iam.getAccessKey();
		String region = iam.getRegion();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
		String date = simpleDateFormat.format(new Date());
		String key_name=accountId+"-aws-billing-csv-"+date+".csv";
		File cvsFile = new File(key_name);
		try {
			AmazonS3 s3=AmazonS3ClientBuilder.standard()
					.withCredentials(STSUtil.getCredential(accessKeyID, accessKey))
					.withRegion(region)
					.build();
			S3Object o = s3.getObject(bucket_name, key_name);
			S3ObjectInputStream s3is = o.getObjectContent();
			FileOutputStream fos = new FileOutputStream(cvsFile);
			byte[] read_buf = new byte[1024];
			int read_len = 0;
			while ((read_len = s3is.read(read_buf)) > 0) {
			    fos.write(read_buf, 0, read_len);
			}
			s3is.close();
			fos.close();
			return cvsFile;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
