package com.awslabplatform.common.message;

public enum  RoleMessage implements Message  {

	NAN_ROLE_ERROR(11000 , "很抱歉，您无法查看该实验");


	private int code;//消息代码
	private String content;//消息内容


	private RoleMessage(int code,  String content) {
		this.code = code;
		this.content = content;
	}

	/**
	 * 获取消息代码
	 * @return
	 */
	@Override
	public int getCode() {
		return this.code;
	}

	/**
	 * 获取消息内容
	 * @return
	 */
	@Override
	public String getContent() {
		return this.content;
	}

	/**
	 *控制台输出消息内容
	 */
	@Override
	public void console() {
		System.out.println(this.code + ":" + this.content);
	}
}
