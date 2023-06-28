package com.awslabplatform_admin.entity;

import java.util.Date;

public class Log {

	private long id;                     //日志id
	private String className;            //类名
	private String methodName;           //方法名
	private String module;               //模块名
	private String operation;            //操作
	private Date createTime;             //操作时间
	private String userId;                  //用户ID
	private String userName;             //用户名
	private String result;               //执行结果
	private String unitName;             //单位名称
	private String exception = "none";   //异常信息
	private String comment;              //备注

	public Log() {
	}

	public Log(String className, String methodName, String module, String operation, Date createTime, String userId, String userName, String unitName, String comment) {
		this.className = className;
		this.methodName = methodName;
		this.module = module;
		this.operation = operation;
		this.createTime = createTime;
		this.userId = userId;
		this.userName = userName;
		this.unitName = unitName == null ? "" : unitName;
		this.comment = comment == null ? "" : comment;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
