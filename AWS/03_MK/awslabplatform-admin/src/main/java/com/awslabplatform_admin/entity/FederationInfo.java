package com.awslabplatform_admin.entity;


/**
 * 联盟登录pojo
 * @author yuzhh
 *
 */
public class FederationInfo {
	
	/**
	 * IAM 访问key ID
	 */
	private String access_key_id;
	
	/**
	 * IAM 访问 key
	 */
	private String secret_key;
	
	/**
	 * 请求者URL，填写我们自己系统的URL
	 */
	private String issuerURL;
	
	/**
	 * aws 控制台链接，精确到控制台对应功能路径
	 */
	private String consoleURL;
	
	/**
	 * 联盟登录终端URL
	 */
	private static final String SIGNINURL = "https://signin.amazonaws.cn/federation";
	
	
	/**
	 * 登录者姓名
	 */
	private String realName;
	
	/**
	 * AWS STS 检索的凭证持续时间
	 */
	private Integer durationSeconds;
	
	/**
	 * 策略脚本
	 */
	private String policy;
	
	/**
	 * 
	 * 创建IAM policy
	 */
	private static final String CREATEIAMPOLICY = "{\"Version\":\"2012-10-17\",\"Statement\":["
			+ "{\"Action\":\"sns:*\",\"Effect\":\"Allow\",\"Resource\":\"*\"},"
			+ "{\"Action\":\"iam:*\",\"Effect\":\"Allow\",\"Resource\":\"*\"}"
			+ "]"
			+ "}";
	
	/**
	 * EC2 访问policy
	 */
	private static final String EC2POLICY = "{\"Version\":\"2012-10-17\",\"Statement\":["
			+ "{\"Action\":\"sns:*\",\"Effect\":\"Allow\",\"Resource\":\"*\"},"
			+ "{\"Action\":\"EC2:*\",\"Effect\":\"Allow\",\"Resource\":\"*\"}"
			+ "]"
			+ "}";
	
	/**
	 * aws console iam URL
	 */
	private static final String AWSCONSOLEIAMURL = "https://console.amazonaws.cn/iam/home?region=cn-north-1#/home";
	
	
	
	public static String getAwsconsoleiamurl() {
		return AWSCONSOLEIAMURL;
	}

	public static String getCreateiampolicy() {
		return CREATEIAMPOLICY;
	}

	public static String getEc2policy() {
		return EC2POLICY;
	}

	public String getAccess_key_id() {
		return access_key_id;
	}

	public void setAccess_key_id(String access_key_id) {
		this.access_key_id = access_key_id;
	}

	public String getSecret_key() {
		return secret_key;
	}

	public void setSecret_key(String secret_key) {
		this.secret_key = secret_key;
	}

	public String getIssuerURL() {
		return issuerURL;
	}

	public void setIssuerURL(String issuerURL) {
		this.issuerURL = issuerURL;
	}

	public String getConsoleURL() {
		return consoleURL;
	}

	public void setConsoleURL(String consoleURL) {
		this.consoleURL = consoleURL;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getDurationSeconds() {
		return durationSeconds;
	}

	public void setDurationSeconds(Integer durationSeconds) {
		this.durationSeconds = durationSeconds;
	}

	public String getPolicy() {
		return policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public static String getSigninurl() {
		return SIGNINURL;
	}
	
	
	
	
	
	

}
