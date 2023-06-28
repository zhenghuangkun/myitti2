package com.awslabplatform.common.message;


/**
 * 登录消息处理
 * @author weix
 * @version 2018-3-19
 */
public enum LoginMessage implements Message {

	/**
	 * 登录成功消息
	 */
	LOGIN_SUCESS(10000 , "登录成功"),
	/**
	 * 登录错误消息
	 */
	LOGIN_VERIFITIONCODE_ERROR(10001 , "验证码输入错误"),
	LOGIN_ACCOUNT_OVER(10002 , "登录次数超过5次，锁定10分钟" ),
	LOGIN_UNKNOW_ERROR(10003 , "凭据认证失败,未找到对应的账号"),
	LOGIN_USER_LOCK(10004 , "用户已被禁用"),
	/**
	 * 会话超时消息
	 */
	SESSION_OVERTIME(10005 , "会话超时，请重新登录"),
	MAIL_SEND_ERROR(10006, "邮件发送失败"),
	ACCOUNT_OR_PASSWORD_ERROR(10007,"账户名密码错误");

	private int code;//消息代码
    private String content;//消息内容


    private LoginMessage(int code,  String content) {
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
