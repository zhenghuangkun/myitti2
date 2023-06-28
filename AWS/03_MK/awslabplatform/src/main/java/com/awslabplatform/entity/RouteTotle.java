package com.awslabplatform.entity;

import java.io.Serializable;

public class RouteTotle implements Serializable{
	private Integer totleTime;
	private Integer userCount;
	public Integer getTotleTime() {
		return totleTime;
	}
	public void setTotleTime(Integer totleTime) {
		this.totleTime = totleTime;
	}
	public Integer getUserCount() {
		return userCount;
	}
	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}
}
