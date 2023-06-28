package com.awslabplatform_admin.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 实验组类
 * @author zhenghk
 */
public class ExperimentGroup implements Serializable {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 组ID
	 */
	private String groupId;

	/**
	 * 组名
	 */
	private String groupName;

	/**
	 * 组名(ztree使用)
	 */
	private String name;

	/**
	 * 创建者Id
	 */
	private String createAuthorId;
	
	/**
	 * 创建者Name
	 */
	private String createAuthorName;
	
	/**
	 * 创建日期
	 */
	private Date createDate;
	
	/**
	 * 状态 0 不可用   1 可用
	 */
	private Integer state;

	/**
	 * 组人数
	 */
	private Integer memberNum;
	
	/**
	 * 关联课程数
	 */
	private Integer courseNum;
	
	/**
	 * 是否选中(默认为false 不选中, ztree 使用)
	 */
	private Boolean checked = false;

	/**
	 * 组用户成员(ztree 使用)
	 */
	private List<Student> children;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreateAuthorId() {
		return createAuthorId;
	}

	public void setCreateAuthorId(String createAuthorId) {
		this.createAuthorId = createAuthorId;
	}

	public String getCreateAuthorName() {
		return createAuthorName;
	}

	public void setCreateAuthorName(String createAuthorName) {
		this.createAuthorName = createAuthorName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(Integer memberNum) {
		this.memberNum = memberNum;
	}

	public Integer getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(Integer courseNum) {
		this.courseNum = courseNum;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public List<Student> getChildren() {
		return children;
	}

	public void setChildren(List<Student> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "ExperimentGroup [groupId=" + groupId + ", groupName="
				+ groupName + ", name=" + name + ", createAuthorId="
				+ createAuthorId + ", createAuthorName=" + createAuthorName
				+ ", createDate=" + createDate + ", state=" + state
				+ ", memberNum=" + memberNum + ", courseNum=" + courseNum
				+ ", checked=" + checked + ", children=" + children + "]";
	}

}
