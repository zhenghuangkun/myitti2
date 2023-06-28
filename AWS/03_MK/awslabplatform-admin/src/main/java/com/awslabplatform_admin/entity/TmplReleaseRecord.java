package com.awslabplatform_admin.entity;

import java.io.Serializable;


/**
 * 模板发布记录
 * @author weix
 * @date 2018年5月14日
 */
public class TmplReleaseRecord implements Serializable {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 记录ID
	 */
	private String recordId;
	
	/**
	 * 模板ID
	 */
	private String tmplId;
	
	/**
	 * 操作
	 */
	private Integer operation;
	
	/**
	 * 发布范围
	 */
	private Integer releaseRange;
	
	/**
	 * 发布院系ID
	 */
	private String releaseDept;
	
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

	public String getTmplId() {
		return tmplId;
	}

	public void setTmplId(String tmplId) {
		this.tmplId = tmplId;
	}

	public Integer getReleaseRange() {
		return releaseRange;
	}

	public void setReleaseRange(Integer releaseRange) {
		this.releaseRange = releaseRange;
	}

	public String getReleaseDept() {
		return releaseDept;
	}

	public void setReleaseDept(String releaseDept) {
		this.releaseDept = releaseDept;
	}
	

}
