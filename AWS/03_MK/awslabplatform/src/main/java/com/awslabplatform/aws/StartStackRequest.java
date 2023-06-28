package com.awslabplatform.aws;

import java.util.Collection;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cloudformation.model.Tag;
/**
 * @param credential 教师API调用凭证
 * @param stackName aws堆栈名称（不可重复）
 * @param keypair 连接实例时的密钥
 * @param templateURL 模板URL(S3储存桶中)
 * @param tags 标签，如：班级   姓名
 * @author lijf
 * date 2018年3月21日 上午10:27:01
 */
public class StartStackRequest {
	private AWSCredentialsProvider credential;
	private String stackName;
	private String keyName;
	private String templateBody;
	private Collection<Tag> tags;
	private String instanceType="t2.micro";
	private Regions Region=Regions.CN_NORTH_1;
	private String windowsPassword;
	
	
	public StartStackRequest withWindowsPassword(String windowsPassword){
		this.windowsPassword=windowsPassword;
		return this;
	}
	public StartStackRequest withCredential(AWSCredentialsProvider credential){
		this.credential=credential;
		return this;
	}
	public StartStackRequest withStackName(String stackName){
		this.stackName=stackName;
		return this;
	}
	public StartStackRequest withKeyName(String keyName){
		this.keyName=keyName;
		return this;
	}
	public StartStackRequest withTemplateBody(String templateBody){
		this.templateBody=templateBody;
		return this;
	}
	public StartStackRequest withTags(Collection<Tag> tags){
		this.tags=tags;
		return this;
	}
	public StartStackRequest withInstanceType(String instanceType){
		this.instanceType=instanceType;
		return this;
	}
	public AWSCredentialsProvider getCredential() {
		return credential;
	}
	public void setCredential(AWSCredentialsProvider credential) {
		this.credential = credential;
	}
	public String getStackName() {
		return stackName;
	}
	public void setStackName(String stackName) {
		this.stackName = stackName;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getTemplateBody() {
		return templateBody;
	}
	public void setTemplateBody(String templateBody) {
		this.templateBody = templateBody;
	}
	public Collection<Tag> getTags() {
		return tags;
	}
	public void setTags(Collection<Tag> tags) {
		this.tags = tags;
	}
	public String getInstanceType() {
		return instanceType;
	}
	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}
	public Regions getRegion() {
		return Region;
	}
	public void setRegion(Regions region) {
		Region = region;
	}
	public String getWindowsPassword() {
		return windowsPassword;
	}
	public void setWindowsPassword(String windowsPassword) {
		this.windowsPassword = windowsPassword;
	}
	
}
