package com.awslabplatform_admin.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 策略实体类
 * @author lijf
 * @date 2018年5月24日 上午10:17:16
 */
public class Policy implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 策略名称
	 */
	private String name;
	/**
	 * 策略描述
	 */
	private String description;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 策略文件Url
	 */
	private String fileUrl;
	/**
	 * 状态：0删除，1可用（默认）
	 */
	private int status=1;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Policy [id=" + id + ", name=" + name + ", description="
				+ description + ", createTime=" + createTime + ", fileUrl="
				+ fileUrl + ", status=" + status + "]";
	}

}
