package com.awslabplatform_admin.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 模板申请实体类
 * @author weix
 * @version 2018-4-4
 */
public class TmplUseApply implements Serializable {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 申请ID
	 */
	private String applyId;
	
	/**
	 * 模板ID
	 */
	private String tmplId;
	
	/**
	 * 模板名称
	 */
	private String tmplName;
	
	/**
	 * 模板类型名称
	 */
	private String tmplTypeName;
	
	/**
	 * 申请人
	 */
	private String applicant;
	
	/**
	 * 申请时间
	 */
	private String applyTime;
	
	/**
	 * 堆栈ID
	 */
	private String stackId;
	
	/**
	 * 堆栈名称
	 */
	private String stackName;
	/**
	 * 连接密钥
	 */
	private String keyPairUrl;

	/**
	 * 使用开始时间
	 */
	private String useStartTime;
	
	/**
	 * 使用结束时间
	 */
	private String useEndTime;
	
	/**
	 * 使用时长
	 */
	private Long useTimeLength;
	
	/**
	 * 实际使用时长
	 */
	private Long actualUseLength;
	
	/**
	 * 总消费
	 */
	private BigDecimal totalPrice;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 堆栈状态
	 */
	private String stackState;
	
	/**
	 * 堆栈删除时间
	 */
	private String stackDeleteTime;
	
	/**
	 * 审核状态
	 */
	private Integer reviewState;
	
	/**
	 * 审核记录
	 */
	private List<TmplReivewRecord> tmplReivewRecord;
	
	/**
	 * 堆栈信息
	 */
	private AwsStack  awsStack;
	
	/**
	 * 实例信息
	 */
	List<AwsInstance> awsInstances;
	
	public List<AwsInstance> getAwsInstances() {
		return awsInstances;
	}

	public void setAwsInstances(List<AwsInstance> awsInstances) {
		this.awsInstances = awsInstances;
	}

	public List<TmplReivewRecord> getTmplReivewRecord() {
		return tmplReivewRecord;
	}

	public void setTmplReivewRecord(List<TmplReivewRecord> tmplReivewRecord) {
		this.tmplReivewRecord = tmplReivewRecord;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getTmplId() {
		return tmplId;
	}

	public void setTmplId(String tmplId) {
		this.tmplId = tmplId;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getReviewState() {
		return reviewState;
	}

	public void setReviewState(Integer reviewState) {
		this.reviewState = reviewState;
	}

	public String getTmplName() {
		return tmplName;
	}

	public void setTmplName(String tmplName) {
		this.tmplName = tmplName;
	}

	public String getTmplTypeName() {
		return tmplTypeName;
	}

	public void setTmplTypeName(String tmplTypeName) {
		this.tmplTypeName = tmplTypeName;
	}

	public Long getUseTimeLength() {
		return useTimeLength;
	}

	public void setUseTimeLength(Long useTimeLength) {
		this.useTimeLength = useTimeLength;
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

	public Long getActualUseLength() {
		return actualUseLength;
	}

	public void setActualUseLength(Long actualUseLength) {
		this.actualUseLength = actualUseLength;
	}

	public AwsStack getAwsStack() {
		return awsStack;
	}

	public void setAwsStack(AwsStack awsStack) {
		this.awsStack = awsStack;
	}

	public String getKeyPairUrl() {
		return keyPairUrl;
	}

	public void setKeyPairUrl(String keyPairUrl) {
		this.keyPairUrl = keyPairUrl;
	}
}
