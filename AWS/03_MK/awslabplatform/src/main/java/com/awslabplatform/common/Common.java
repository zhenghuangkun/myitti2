
package com.awslabplatform.common;

/**
 * 系统静态变量
 * @author weix
 *
 */
public class Common {

	public static final String SESSION_KEY = "curr_user";

	public static final int STATE_DELETE= 0;//删除状态

	public static final int STATE_ACTIVE = 1;//活动状态

	public static final int ROLE_TYPE_ADMIN = 1;//超级管理员

	public static final int ROLE_TYPE_DEPARTMENTS= 2;//院系管理员

	public static final int ROLE_TYPE_TEACHER = 3;//教师

	public static final int ROLE_TYPE_STUDENT = 4;//学生

	public static final String IMG_URL = "http://120.79.132.170";

	/*CH 新增参数 version 2.0*/

	public static final String AWS_IAM_IAMKIND="1";//IAM账号为使用方式（1：表示实验者使用）

	public static final String AWS_IAM_CONTROL_IAMKIND="0";//IAM账号为使用方式(0: 表示管理人员使用)

	public static final String AWS_ACCOUNTPOOL_USETYPE="1";//账号池账号为不登录控制台

	public static final String AWS_ACCOUNTPOOL_CONTROL_USETYPE="0";//账号池账号为登录控制台

	public static final String AWS_ACCOUNTPOOL_ISACTIVE ="0";//账号池账号激活状态（0：激活 1：失效）

	public static final String AWS_ACCOUNTPOOL_ISDELETE="0";//账号池账号是否删除状态（删除标志 0：未删除  1：删除）

	public static final String AWS_ACCOUNT_ISACTIVE="0";//付费账号激活状态（0：激活 1：失效）

	public static final String AWS_ACCOUNT_ACCOUNTSTAUSE="0";//付费账号删除状态（1 激活 0 失效）

	public static final  String AWS_CONTROL_EXPERMENT="1";//控制台实验类型(判断是否有进行实验)

	public static final  String AWS_POLICY_STATUS="1";//表示策略状态为可用


	/*账号池 账号使用状态*/
	public static final  String AWS_ACCOUNTPOOL_USED="1";//表示账号池该账号为使用状态

	public static final  String AWS_ACCOUNTPOOL_NOT_USED="0";//表示账号池该账号为未使用状态



	/*控制台实验取账号池账号*/

	public static final  String AWS_POLICY_NULL="0";//表示找不到相关策略

	public static final String AWS_ACCOUNTPOOL_SUCCESS="1";//表示账号池成功获取账号，并修改完成

	public static final  String AWS_ACCOUNTPOOL_THIS_USED="2";//表示账号池已经分配给改学生账号了

	public static final String AWS_ACCOUNTPOOL_NOUSED="3";//表示账号池的应用在控制台账号没有可使用完

	public static final  String  AWS_CREADENTALS_NOTUSED="-2";//表示该账号凭证不能使用

	public static final  String AWS_POLICY_NOUSED="-3";//表示对应实验的策略不可用

	public static final  String AWS_CONTROL_ERROR="-4";//表示对应实验的策略不可用
	/*不登录控制台实验*/


	public static final  String NOT_FOUND_EXPERIMENT="-1";//没有找到该实验信息

	public static final  String AWS_EXPRIMENT_NOTFOUND="-2";//未找到实验账号（凭证）


}

