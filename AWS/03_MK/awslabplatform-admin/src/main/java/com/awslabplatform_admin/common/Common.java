
package com.awslabplatform_admin.common;

/**
 * 系统静态变量
 * @author weix
 *
 */
public class Common {
	
	/*系统*/
	public static final String SESSION_KEY = "curr_user";

	public static final Integer STATE_DELETE= 0;//删除状态

	public static final Integer STATE_ACTIVE = 1;//可用状态
	
	public static final String USERPWD = "AWS@123";//默认密码
	
	public static final Integer ROLE_TYPE_ADMIN = 0;//超级管理员
	
	public static final Integer ROLE_TYPE_PLATFORM= 1;//平台管理员
	
	public static final Integer ROLE_TYPE_DEPARTMENTS= 2;//院系管理员
	
	public static final Integer ROLE_TYPE_TEACHER = 3;//教师
	
	public static final Integer ROLE_TYPE_ASSISTANT = 4;//助教
	
	public static final Integer ROLE_TYPE_STUDENT = 5;//学生
	
	/*模板管理*/
	public static final String FILE_DISTINGUIDH_TMPL_SCRIPT = "tmplscript";//模板资源脚本文件标识
	
	public static final String FILE_DISTINGUIDH_TMPL_IMG = "tmplimg";//模板缩略图文件标识
	
	public static final Integer TEMPLATE_LIST_PAGE_SIZE = 20;//模板设计缩略图列表每页显示个数
	
	public static final String TEMPLATE_SORT_OPTTIME = "optTime";//模板缩略图列表默认时间排序
	
	public static final String TEMPLATE_SORT_COLLECTTIME = "collectionTime";//模板收藏列表默认收藏时间排序
	
	public static final String TEMPLATE_LIST_PAGE_ORDER = "DESC";//模板缩略图列表默认降序
	
	public static final String TEMPLATE_SORT_USECOUNT = "useCount";//模板列表使用量排序字段
	
	public static final String TEMPLATE_SORT_RELEASETIME = "releaseTime";//模板列表发布时间排序字段
	
	public static final Integer TEMPLATE_DESCRIPTION_LENGTH = 30;//模板缩略图列表描述字段显示长度
	
	public static final Integer TEMPLATE_RELEASE_FALSE = 0;//模板未发布状态
	
	public static final Integer TEMPLATE_RELEASE_TRUE = 1;//模板已发布状态
	
	public static final Integer TEMPLATE_RELEASE_REVIEW_NONE = 0;//模板未提交发布审核
	
	public static final Integer TEMPLATE_RELEASE_REVIEW_WAIT = 1;//模板已发布审核中
	
	public static final Integer TEMPLATE_RELEASE_REVIEW_PASS = 2;//模板已发布审核通过
	
	public static final Integer TEMPLATE_RELEASE_REVIEW_REFUSE = 3;//模板已发布审核拒绝
	
	public static final Integer TEMPLATE_SHOW_SIZE = 4;//推荐模板显示数量
	
	public static final String TEMPLATE_COMMENT_SORT_COMMENTTIME= "commentTime";//模板评论默认按时间排序
	
	public static final String TEMPLATE_COMMENT_SORT_ORDER = "DESC";//模板评论默认降序
	
	public static final Integer TEMPLATE_COMMENT_PAGE_SIZE = 10;//模板评论每页显示数量
	
	public static final String  TEMPLATE_STACK_NAME_FLAG = "T";//堆栈名称
	
	public static final Integer TEMPLATE_USE_APPLY_STATE_REVIEW_WAIT = 1;//模板测试申请等待审核状态
	
	public static final Integer TEMPLATE_USE_APPLY_STATE_REVIEW_PASS = 2;//模板测试申请审核通过
	
	public static final String TEMPLATE_STACK_STATE_CREATE_NO = "CREATE_NO";//资源未创建
	
	public static final String TEMPLATE_STACK_STATE_CREATE_COMPLETE = "CREATE_COMPLETE";//模板堆栈创建完成标识
	
	public static final String TEMPLATE_STACK_STATE_CREATE_IN_PROGRESS = "CREATE_IN_PROGRESS";//堆栈创建中
	
	public static final String TEMPLATE_STACK_STATE_CREATE_FAILED = "CREATE_FAILED";//堆栈创建失败
	
	public static final String TEMPLATE_STACK_STATE_DELETE_COMPLETE = "DELETE_COMPLETE";//堆栈删除完成
	
	public static final String TEMPLATE_RESOURCE_TYPE_EC2_INSTANCE = "AWS::EC2::Instance";//ec2实例类型
	
	public static final String TEMPLATE_RESOURCE_INSTANCE_STATE_TERMINATED = "terminated";//实例终止状态
	
	public static final String TEMPLATE_RESOURCE_INSTANCE_STATE_RUNNING = "running";//实例运行状态
	
	public static final String TEMPLATE_TEACHER_TAG_KEY_SCHOOL = "SCHOOL";//模板老师学校标签
	
	public static final String TEMPLATE_TEACHER_TAG_KEY_DEPARTMENT = "DEPARTMENT";//模板老师院系
	
	public static final String TEMPLATE_TEACHER_TAG_KEY_REALNAME = "REALNAME";//模板老师姓名标签
	
	public static final String TEMPLATE_APPLY_TERMINATED_JOB_GROUP_NAME = "APPLY_TERMINATED";//教师申请使用资源终止任务组名（QuartzManager）
	
	public static final String TEMPLATE_INSTANCE_AMI_NAME_HEAD = "AMI-";//镜像ID头
	
	public static final Integer TEMPLATE_FROM_FLAG_PLATFORM = 1;//模板来源-平台
	
	public static final Integer TEMPLATE_FROM_FLAG_TEACHER = 2;//模板来源-教师
	
	public static final Integer TEMPLATE_RELEASE_RANGE_PLATFORM = 1;//模板发布范围-平台
	
	public static final Integer TEMPLATE_RELEASE_RANGE_DEPT = 2;//模板发布范围-院系
	
	/*数据字典父项*/
	public static final Integer DIC_STATE_DELETE= 1;//删除状态

	public static final Integer DIC_STATE_ACTIVE = 0;//可用状态
	
	/*Aws Account数据*/
	public static final Integer ACCOUNT_STATE_DELETE= 1;//删除状态

	public static final Integer ACCOUNT_STATE_ACTIVE = 0;//可用状态
	
	public static final Integer ACCOUNT_ISPAYING = 1;//为主账号
	
	public static final Integer ACCOUNT_NOTPAYING = 0;//不为主账号
	
	public static final Integer ACCOUNT_ISACTIVE = 1;//为失效
	
	public static final Integer ACCOUNT_NOTACTIVE = 0;//为激活
	
	public static final Integer ACCOUNT_ISUPT = 1;//为UPT
	
	public static final Integer ACCOUNT_NOTUPT = 0;//不为UPT
	
	public static final Integer IAM_ISUSER = 0;//未分配
	
	public static final Integer IAM_ALREADY_ISUSER = 1;//分配
	
	public static final Integer IAM_STATE_DELETE= 1;//删除状态
	
	public static final Integer IAM_STATE_ACTIVE = 0;//可用状态
	
	public static final Integer IAM_KIND_AUSER = 0;//0:表示管理人员使用    
	
	public static final Integer IAM_KIND_EUSER = 1;//1：表示实验者使用
	
	public static final Integer IAMPOOL_STATE_DELETE= 1;//删除状态
	
	public static final Integer IAMPOOL_STATE_ACTIVE = 0;//可用状态
	
	public static final Integer IAMPOOL_ISACTIVE = 1;//为失效
	
	public static final Integer IAMPOOL_NOTACTIVE = 0;//为激活
	
	public static final Integer IAMPOOL_USERTYPE = 1;// 1 不登陆控制台
	
	public static final Integer IAMPOOL_NOTUSERTYPE = 0;//0 登陆控制台
	
	/*用户管理的数据*/
	public static final Integer USER_STATE_ACTIVE= 1;//可用状态

	public static final Integer USER_STATE_DELETE = 0;//删除状态
	
	/*学生管理的数据*/
	public static final Integer STUDENT_STATE_ACTIVE= 1;//可用状态

	public static final Integer STUDENT_STATE_DELETE = 0;//删除状态
	
	/*课程管理的数据*/
	public static final Integer COURSE_STATE_DELETE=0;//删除状态

	/*CH 新添参数 */
	public static final String TEMPLATE_REGION ="cn-north-1";//模板地区 （用于模板启用）

	/*IAM账号 CH 新添参数 version 2.0*/

	public static final String AWS_IAM_IAMSTATUS="1";//IAM账号被激活状态

	public static final String AWS_IAM_IAMKIND="1";//IAM账号为实验者使用

	/* 账号池的数据 CH 新添参数 version 2.0*/

	public static final String AWS_ACCOUNTPOOL_USETYPE="1";//账号池账号运用类型( 0 登录控制台  1 不登陆控制台)

	public static final String AWS_ACCOUNTPOOL_ISACTIVE ="0";//账号池账号激活状态（0：激活 1：失效）

	public static final String AWS_ACCOUNTPOOL_ISDELETE="0";//账号池账号是否删除状态（删除标志 0：未删除  1：删除）

	/*付费账号的数据 CH 新添参数 version*/

	public static final String AWS_ACCOUNT_ISACTIVE="0";//付费账号激活状态（0：激活 1：失效）

	public static final String AWS_ACCOUNT_ACCOUNTSTAUSE="0";//付费账号删除状态（1 激活 0 失效）

	/*用户状态*/
	public static final String String_STATE_ACTIVE = "1";//可用状态(String)CH 新添参数
}

