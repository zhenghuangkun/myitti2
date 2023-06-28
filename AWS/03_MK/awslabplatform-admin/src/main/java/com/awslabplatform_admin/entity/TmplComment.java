package com.awslabplatform_admin.entity;

import java.io.Serializable;

/**
 * 模板评论实体类
 * @author weix
 * @version 2018-4-2
 */
public class TmplComment implements Serializable {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 评论ID
	 */
	private String commentId;
	
	/**
	 * 用户ID
	 */
	private String userId;
	
	/**
	 * 用户真实姓名
	 */
	private String userRealName;
	
	/**
	 * 用户头像地址
	 */
	private String userImageUrl;
	
	/**
	 * 模板ID
	 */
	private String tmplId;
	
	/**
	 * 评论内容
	 */
	private String content;
	
	/**
	 * 星级
	 */
	private Integer stars;
	
	/**
	 * 评论时间
	 */
	private String commentTime;
	
	/**
	 * 状态
	 */
	private Integer state;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTmplId() {
		return tmplId;
	}

	public void setTmplId(String tmplId) {
		this.tmplId = tmplId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStars() {
		return stars;
	}

	public void setStars(Integer stars) {
		this.stars = stars;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	
	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getUserImageUrl() {
		return userImageUrl;
	}

	public void setUserImageUrl(String userImageUrl) {
		this.userImageUrl = userImageUrl;
	}
}
