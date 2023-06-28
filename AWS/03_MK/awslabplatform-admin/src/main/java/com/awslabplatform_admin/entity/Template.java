package com.awslabplatform_admin.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotBlank;


/**
 * 模板实体类
 * @author weix
 * @version 2018-3-19
 */
public class Template implements Serializable {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 模板ID
	 */
	private String tmplId;
	/**
	 * 模板名称
	 */
	@NotBlank(message="{validation.common.NotBlank}")
	private String tmplName;

	/**
	 * 模板类型
	 */
	private String tmplType;
	
	/**
	 * 关联模板ID
	 */
	private String associatedTmplId;
	
	/**
	 * 关联模板名称
	 */
	private String associatedTmplName;
	
	/**
	 * 模板类型名称
	 */
	private String tmplTypeName;
	
 	/**
	 * 模板价格
	 */
	private BigDecimal tmplPrice;
	/**
	 * 模板描述 
	 */
	private String description;
	
	/**
	 * 收藏人数
	 */
	private Integer collectCount;
	
	/**
	 * 操作人
	 */
	private String operator;
	/**
	 * 操作时间
	 */
	private String optTime;
	/**
	 * 发布时间
	 */
	private String releaseTime;
	
	/**
	 * 发布状态
	 */
	private Integer releaseStatus;
	
	/**
	 * 发布范围（1平台2院系）
	 */
	private Integer releaseRange;
	
	/**
	 * 发布院系ID
	 */
	private String releaseDept;
	
	/**
	 * 发布审核状态
	 */
	private Integer releaseReviewState;
	
	/**
	 * 发布审核时间
	 */
	private String releaseReviewTime;
	
	/**
	 * 发布者
	 */
	private String releaser;

	/**
	 * 评价人数
	 */
	private Integer  evaluationNum;
	/**
	 * 评价平均分
	 */
	private BigDecimal evaluateAvgrate;
	
	/**
	 * 模板缩略图
	 */
	private FileInfo tmplImg;
	
	/**
	 * 模板资源脚本
	 */
	private FileInfo tmplScript;
	
	/**
	 * 模板收藏时间
	 */
	private String collectionTime;
	
	/**
	 * 模板状态
	 */
	private Integer  state;
	
	/**
	 * 总评价数
	 */
	private Integer evaluateAmount;
	
	/**
	 * 模板来源标识
	 */
	private Integer tmplFromFlag;
	
	
	public String getTmplId() {
		return tmplId;
	}
	public void setTmplId(String tmplId) {
		this.tmplId = tmplId;
	}
	public String getTmplName() {
		return tmplName;
	}
	public void setTmplName(String tmplName) {
		this.tmplName = tmplName;
	}
	public String getTmplType() {
		return tmplType;
	}
	public void setTmplType(String tmplType) {
		this.tmplType = tmplType;
	}
	public BigDecimal getTmplPrice() {
		return tmplPrice;
	}
	public void setTmplPrice(BigDecimal tmplPrice) {
		this.tmplPrice = tmplPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getReleaseStatus() {
		return releaseStatus;
	}
	public void setReleaseStatus(Integer releaseStatus) {
		this.releaseStatus = releaseStatus;
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
	public String getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}
	public BigDecimal getEvaluateAvgrate() {
		return evaluateAvgrate;
	}
	public void setEvaluateAvgrate(BigDecimal evaluateAvgrate) {
		this.evaluateAvgrate = evaluateAvgrate;
	}
	public Integer getEvaluateAmount() {
		return evaluateAmount;
	}
	public void setEvaluateAmount(Integer evaluateAmount) {
		this.evaluateAmount = evaluateAmount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public FileInfo getTmplImg() {
		return tmplImg;
	}
	public void setTmplImg(FileInfo tmplImg) {
		this.tmplImg = tmplImg;
	}
	public FileInfo getTmplScript() {
		return tmplScript;
	}
	public void setTmplScript(FileInfo tmplScript) {
		this.tmplScript = tmplScript;
	}
	public String getReleaser() {
		return releaser;
	}
	public void setReleaser(String releaser) {
		this.releaser = releaser;
	}
	
	public String getCollectionTime() {
		return collectionTime;
	}
	public void setCollectionTime(String collectionTime) {
		this.collectionTime = collectionTime;
	}
	public String getTmplTypeName() {
		return tmplTypeName;
	}
	public void setTmplTypeName(String tmplTypeName) {
		this.tmplTypeName = tmplTypeName;
	}
	public Integer getEvaluationNum() {
		return evaluationNum;
	}
	public void setEvaluationNum(Integer evaluationNum) {
		this.evaluationNum = evaluationNum;
	}
	public Integer getCollectCount() {
		return collectCount;
	}
	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}
	public String getAssociatedTmplId() {
		return associatedTmplId;
	}
	public void setAssociatedTmplId(String associatedTmplId) {
		this.associatedTmplId = associatedTmplId;
	}
	public Integer getTmplFromFlag() {
		return tmplFromFlag;
	}
	public void setTmplFromFlag(Integer tmplFromFlag) {
		this.tmplFromFlag = tmplFromFlag;
	}
	public Integer getReleaseRange() {
		return releaseRange;
	}
	public void setReleaseRange(Integer releaseRange) {
		this.releaseRange = releaseRange;
	}
	public Integer getReleaseReviewState() {
		return releaseReviewState;
	}
	public void setReleaseReviewState(Integer releaseReviewState) {
		this.releaseReviewState = releaseReviewState;
	}
	public String getReleaseReviewTime() {
		return releaseReviewTime;
	}
	public void setReleaseReviewTime(String releaseReviewTime) {
		this.releaseReviewTime = releaseReviewTime;
	}
	public String getReleaseDept() {
		return releaseDept;
	}
	public void setReleaseDept(String releaseDept) {
		this.releaseDept = releaseDept;
	}
	public String getAssociatedTmplName() {
		return associatedTmplName;
	}
	public void setAssociatedTmplName(String associatedTmplName) {
		this.associatedTmplName = associatedTmplName;
	}
	
}
