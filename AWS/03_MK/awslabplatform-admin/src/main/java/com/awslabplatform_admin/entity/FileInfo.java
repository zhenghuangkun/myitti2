package com.awslabplatform_admin.entity;

import java.io.Serializable;

/**
 * 文件信息实体类
 * @author weix
 * @version 2018-3-23
 */
public class FileInfo implements Serializable {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 文件ID
	 */
	private String fileId;


	/**
	 * 文件名
	 */
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 关联ID
	 */
	private String correlationId;

	/**
	 * 文件路径
	 */
	private String fileUrl;

	/**
	 * 文件类型(后缀)
	 */
	private String fileType;

	/**
	 * 文件大小(kb）
	 */
	private Long fileSize;

	/**
	 * 图片宽度(px）
	 */
	private Integer imgWidth;

	/**
	 * 图片高度(px)
	 */
	private Integer imgHight;

	/**
	 * 区分标识
	 */
	private String distinguishFlag;

	/**
	 * 操作人
	 */
	private String operator;

	/**
	 * 操作时间
	 */
	private String optTime;

	/**
	 * 文件状态(0、不可用1、可用 ）
	 */
	private Integer state;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public Integer getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(Integer imgWidth) {
		this.imgWidth = imgWidth;
	}

	public Integer getImgHight() {
		return imgHight;
	}

	public void setImgHight(Integer imgHight) {
		this.imgHight = imgHight;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDistinguishFlag() {
		return distinguishFlag;
	}

	public void setDistinguishFlag(String distinguishFlag) {
		this.distinguishFlag = distinguishFlag;
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
