package com.awslabplatform.entity;

public class Policys {

	private String id;

	private String name;

	private String description;

	private String fileUrl;

	private String status;

	public Policys(String id, String name, String description, String fileUrl, String status) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.fileUrl = fileUrl;
		this.status = status;
	}

	public Policys() {
	}

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

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
