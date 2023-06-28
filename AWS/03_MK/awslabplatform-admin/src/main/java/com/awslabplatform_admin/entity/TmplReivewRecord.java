package com.awslabplatform_admin.entity;

import java.io.Serializable;


/**
 * 模板审核记录实体类
 * @author weix
 * @version 2018-4-4
 */
public class TmplReivewRecord implements Serializable {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 记录ID
	 */
	private String recordId;
	
	/**
	 * 模板申请ID
	 */
	private String applyId;
	
	/**
	 * 操作
	 */
	private Integer operation;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 当前操作人
	 */
	private String operator;
	
	/**
	 * 操作时间
	 */
	private String optTime;

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public Integer getOperation() {
		return operation;
	}

	public void setOperation(Integer operation) {
		this.operation = operation;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOptTime() {
		return optTime;
	}
	public void setOptTime(String optTime) {
		this.optTime = optTime;
	}
	

}
