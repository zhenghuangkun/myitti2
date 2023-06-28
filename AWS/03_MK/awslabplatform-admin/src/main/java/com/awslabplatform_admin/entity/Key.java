package com.awslabplatform_admin.entity;

import java.io.Serializable;
import java.util.Date;

public class Key implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 122121212123231L;

	private String keyId;

	private String keyName;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	private String createAuthor;

	private Date createDate;

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

	public String getCreateAuthor() {
		return createAuthor;
	}

	public void setCreateAuthor(String createAuthor) {
		this.createAuthor = createAuthor;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
