package com.awslabplatform_admin.service.amazonaws;

import java.io.File;

import com.awslabplatform_admin.entity.AwsIams;
import com.awslabplatform_admin.entity.AwsStack;
/**
 * s3储存桶服务
 * @author lijf
 * @date 2018年5月14日 下午5:15:24
 */
public interface S3Service {
	/**
	 * 从S3中得到账单文件
	 * @param awsStack 访问凭证和区域
	 * @return
	 */
	public File getBillFile(AwsIams iam);
}
