package com.awslabplatform_admin.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


/**
 * 实验类
 * @author zhenghk
 */
public class Experiment implements Serializable {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 实验ID
	 */
	private String experimentId;
	/**
	 * 实验序号
	 */
	private Integer experimentNo;
	/**
	 * 实验模板ID
	 */
	private String templateId;
	/**
	 * 实验模板名
	 */
	private String templetName;
	/**
	 * 课程ID
	 */
	private String courseId;
	/**
	 * 实验名称
	 */
	private String experimentName;
	/**
	 * 实验区域Id
	 */
	private String experimentRegionId;
	/**
	 * 实验区域名
	 */
	private String experimentRegionName;
	/**
	 * 实验描述
	 */
	private String experimentDescription;
	/**
	 * 实验创建时间
	 */
	private String createTime;
	/**
	 * 实验指南文件描述
	 */
	private FileInfo guideInfo;
	
	/**
	 * 实验指南文件Id
	 */
	private String guideFileId;
	/**
	 * 实验指南文件名
	 */
	private String guideName;
	
	/**
	 * 实验指南URL
	 */
	private String guideUrl;
	
	/**
	 * 实验时长
	 */
	private Integer runtime;
	/**
	 * 实验类型
	 */
	private Integer exeperimentType;
	/**
	 * 实验密钥
	 */
	private String keyFile;
	/**
	 * 实验密钥ID
	 */
	private String keyId;
	/**
	 * 密钥名
	 */
	private String keyName;
	
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	
	/**
	 * 总价格
	 */
	private BigDecimal totalPrice;
	
	/**
	 * 堆栈Id
	 */
	private String stackId;
	
	/**
	 * 堆栈名
	 */
	private String stackName;
	
	/**
	 * 栈状态
	 */
	private String stackState;
	
	/**
	 * 栈删除时间
	 */
	private String stackDeleteTime;
	
	/**
	 * 开始实验时间
	 */
	private String useStartTime;
	
	/**
	 * 开始实验时间
	 */
	private String useEndTime;

	/**
	 * 实际使用时长
	 */
	private Long actualUseLength;


	/**
	 * 堆栈信息
	 */
	private AwsStack awsStack;

	/**
	 * 实例信息
	 */
	List<AwsInstance> awsInstances;
	
	private Integer templateOrigin;
	
	private String policyId;
	
	private String policyName;
	
	public Integer getTemplateOrigin() {
		return templateOrigin;
	}

	public void setTemplateOrigin(Integer templateOrigin) {
		this.templateOrigin = templateOrigin;
	}

	public String getExperimentId() {
		return experimentId;
	}

	public void setExperimentId(String experimentId) {
		this.experimentId = experimentId;
	}

	public Integer getExperimentNo() {
		return experimentNo;
	}

	public void setExperimentNo(Integer experimentNo) {
		this.experimentNo = experimentNo;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTempletName() {
		return templetName;
	}

	public void setTempletName(String templetName) {
		this.templetName = templetName;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getExperimentName() {
		return experimentName;
	}

	public void setExperimentName(String experimentName) {
		this.experimentName = experimentName;
	}

	public String getExperimentDescription() {
		return experimentDescription;
	}

	public void setExperimentDescription(String experimentDescription) {
		this.experimentDescription = experimentDescription;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public FileInfo getGuideInfo() {
		return guideInfo;
	}

	public void setGuideInfo(FileInfo guideInfo) {
		this.guideInfo = guideInfo;
	}

	public String getGuideFileId() {
		return guideFileId;
	}

	public void setGuideFileId(String guideFileId) {
		this.guideFileId = guideFileId;
	}

	public String getGuideName() {
		return guideName;
	}

	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}

	public String getGuideUrl() {
		return guideUrl;
	}

	public void setGuideUrl(String guideUrl) {
		this.guideUrl = guideUrl;
	}

	public Integer getRuntime() {
		return runtime;
	}

	public void setRuntime(Integer runtime) {
		this.runtime = runtime;
	}

	public Integer getExeperimentType() {
		return exeperimentType;
	}

	public void setExeperimentType(Integer exeperimentType) {
		this.exeperimentType = exeperimentType;
	}

	public String getKeyFile() {
		return keyFile;
	}

	public void setKeyFile(String keyFile) {
		this.keyFile = keyFile;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStackId() {
		return stackId;
	}

	public void setStackId(String stackId) {
		this.stackId = stackId;
	}

	public String getStackName() {
		return stackName;
	}

	public void setStackName(String stackName) {
		this.stackName = stackName;
	}

	public String getStackState() {
		return stackState;
	}

	public void setStackState(String stackState) {
		this.stackState = stackState;
	}

	public String getStackDeleteTime() {
		return stackDeleteTime;
	}

	public void setStackDeleteTime(String stackDeleteTime) {
		this.stackDeleteTime = stackDeleteTime;
	}

	public String getUseStartTime() {
		return useStartTime;
	}

	public void setUseStartTime(String useStartTime) {
		this.useStartTime = useStartTime;
	}

	public String getUseEndTime() {
		return useEndTime;
	}

	public void setUseEndTime(String useEndTime) {
		this.useEndTime = useEndTime;
	}

	public Long getActualUseLength() {
		return actualUseLength;
	}

	public void setActualUseLength(Long actualUseLength) {
		this.actualUseLength = actualUseLength;
	}
	
	public void setActualUseLength(Integer actualUseLength) {
		this.actualUseLength = actualUseLength.longValue();
	}
	
	public AwsStack getAwsStack() {
		return awsStack;
	}

	public void setAwsStack(AwsStack awsStack) {
		this.awsStack = awsStack;
	}

	public List<AwsInstance> getAwsInstances() {
		return awsInstances;
	}

	public void setAwsInstances(List<AwsInstance> awsInstances) {
		this.awsInstances = awsInstances;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getExperimentRegionId() {
		return experimentRegionId;
	}

	public void setExperimentRegionId(String experimentRegionId) {
		this.experimentRegionId = experimentRegionId;
	}

	public String getExperimentRegionName() {
		return experimentRegionName;
	}

	public void setExperimentRegionName(String experimentRegionName) {
		this.experimentRegionName = experimentRegionName;
	}

	@Override
	public String toString() {
		return "Experiment [experimentId=" + experimentId + ", experimentNo="
				+ experimentNo + ", templateId=" + templateId
				+ ", templetName=" + templetName + ", courseId=" + courseId
				+ ", experimentName=" + experimentName
				+ ", experimentRegionId=" + experimentRegionId
				+ ", experimentRegionName=" + experimentRegionName
				+ ", experimentDescription=" + experimentDescription
				+ ", createTime=" + createTime + ", guideInfo=" + guideInfo
				+ ", guideFileId=" + guideFileId + ", guideName=" + guideName
				+ ", guideUrl=" + guideUrl + ", runtime=" + runtime
				+ ", exeperimentType=" + exeperimentType + ", keyFile="
				+ keyFile + ", keyId=" + keyId + ", keyName=" + keyName
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", totalPrice=" + totalPrice + ", stackId=" + stackId
				+ ", stackName=" + stackName + ", stackState=" + stackState
				+ ", stackDeleteTime=" + stackDeleteTime + ", useStartTime="
				+ useStartTime + ", useEndTime=" + useEndTime
				+ ", actualUseLength=" + actualUseLength + ", awsStack="
				+ awsStack + ", awsInstances=" + awsInstances
				+ ", templateOrigin=" + templateOrigin + ", policyId="
				+ policyId + ", policyName=" + policyName + "]";
	}


}
