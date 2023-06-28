package com.awslabplatform_admin.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 机构表entity
 * 
 * @author czy
 *
 */
public class Mechanism implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 12121334232322L;

	private String mechanismId;// 机构管理自增长Id

	private String mechanismName;// 机构管理名称

	private String mechanismdDescribe;// 机构管理描述

	private String mechanismPid;// 机构父项Id 只有一级默认为0

	private String mechanismSort;// 机构管理排序标识

	private Date createTime;// 创建时间
	
	private List<Mechanism> childMechanism;//子菜单

	public String getMechanismId() {
		return mechanismId;
	}

	public void setMechanismId(String mechanismId) {
		this.mechanismId = mechanismId;
	}

	public String getMechanismName() {
		return mechanismName;
	}

	public void setMechanismName(String mechanismName) {
		this.mechanismName = mechanismName;
	}

	public String getMechanismdDescribe() {
		return mechanismdDescribe;
	}

	public void setMechanismdDescribe(String mechanismdDescribe) {
		this.mechanismdDescribe = mechanismdDescribe;
	}

	public String getMechanismPid() {
		return mechanismPid;
	}

	public void setMechanismPid(String mechanismPid) {
		this.mechanismPid = mechanismPid;
	}

	public String getMechanismSort() {
		return mechanismSort;
	}

	public void setMechanismSort(String mechanismSort) {
		this.mechanismSort = mechanismSort;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Mechanism> getChildMechanism() {
		return childMechanism;
	}

	public void setChildMechanism(List<Mechanism> childMechanism) {
		this.childMechanism = childMechanism;
	}

	

}
