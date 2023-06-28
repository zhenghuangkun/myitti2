package com.awslabplatform.common.message;

public enum EditUserInfoMessage implements Message {
	/**
	 * 修改信息成功消息
	 */
	EDIT_SUCESS(10000 , "登录成功"),
	/**
	 * 修改信息错误消息
	 */
	EDIT_USER_EMAIL_VER_CODE_SUCCESS(10005,"邮箱验证码校验成功"),
	EDIT_USER_EMAIL_VER_CODE_ERROR(10006,"邮箱验证码校验失败"),

	EDIT_USER_PWD_SCUESS(10007, "修改密码成功,请重新登陆"),
	EDIT_USER_PWD_OLD_VER_ERROR(10008,"修改密码失败,输入原密码错误"),
	EDIT_USER_PWD_CONFIRM_VER_ERROR(10009, "修改密码失败，确认密码不一致"),
	EDIT_USER_PWD_NULL_ERROR(10010,"空密码错误"),
	EDIT_USER_INFO_SUCCESS(10011,"修改用户信息成功"),
	EDIT_USER_INFOR_ERROR(10012,"修改用户信息失败"),
	EDIT_USER_EMAIL_SUCCESS(10013,"修改用户邮箱成功"),
	EDIT_USER_EMAIL_ERROR(10014,"修改用户邮箱svb"),


	EDIT_VERIFITIONCODE_ERROR(10001 , "验证码输入错误"),
	EDIT_ACCOUNT_OVER(10002 , "连续发送验证码次数超过5次，锁定10分钟" ),
	LOGIN_UNKNOW_ERROR(10003 , "凭据认证失败,未找到对应的账号"),
	LOGIN_USER_LOCK(10004 , "用户已被禁用"),
	SEND_EDIT_USER_EMAIL_ERROR(10005,"发送邮箱验证码失败"),
	SEND_EDIT_USER_EMAIL_SUCCESS(10006,"发送邮箱验证码成功"),
	EDIT_USER_FACE_SUCCESS(10007,"修改用户头像成功"),
	EDIT_USER_FACE_ERROR(10008,"修改用户头像失败");
	private int code;//消息代码
	private String content;//消息内容


	private EditUserInfoMessage(int code,  String content) {
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
