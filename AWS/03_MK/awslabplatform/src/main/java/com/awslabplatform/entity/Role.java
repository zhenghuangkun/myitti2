package com.awslabplatform.entity;

import java.io.Serializable;


/**
 * 角色
 *
 * @author weix
 * @version 2018-3-4
 */
public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1849722778610094293L;
	/**
	 * 角色ID
	 */
	private String roleId;
	/**
	 * 角色名
	 */
	private String roleName;
	/**
	 * 角色描述
	 */
	private String roleDESC;

	/**
	 * 角色类型（1、超级管理员2、院系管理员3、教师、4学生）
	 */
	private Integer roleType;
	/**
	 * 创建者
	 */
	private String createBy;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 更新者
	 */
	private String updateBy;
	/**
	 * 修改时间
	 */
	private String updateTime;
	/**
	 * 权限配置时间
	 */
	private String authorityTime;
	
	/**
	 * 状态
	 */
	private Integer state;
	
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleDESC() {
		return roleDESC;
	}

	public void setRoleDESC(String roleDESC) {
		this.roleDESC = roleDESC;
	}

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getAuthorityTime() {
		return authorityTime;
	}

	public void setAuthorityTime(String authorityTime) {
		this.authorityTime = authorityTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
