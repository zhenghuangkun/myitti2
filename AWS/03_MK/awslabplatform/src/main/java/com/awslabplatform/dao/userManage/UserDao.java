package com.awslabplatform.dao.userManage;

import org.apache.ibatis.annotations.Param;

import com.awslabplatform.dao.base.BaseDao;
import com.awslabplatform.entity.Student;
import com.awslabplatform.entity.StudentTag;

/**
 * 用户管理dao 接口
 * @author weix
 *
 */
public interface UserDao extends BaseDao<Student, Integer>{

	//通过邮箱查找用户
	public Student getStudentByEmail(String email);

	//修改用户邮箱
	public void updateUserEmailByUserId(@Param("userId") String userId, @Param("Email") String Email);

	//通过手机查找用户
	public Student getStudentByPhone(String phone);

	//获取用户密码
	public String getPwdByUser(String userId);

	//更新正在进行的实验id_satckName
	void updateExperiment(@Param("id") String id, @Param("experimentId_stackName") String experimentId_stackName);

	Student selectById(@Param("id") String id);

	//修改用户密码
	public void updatePwdByUserId(@Param("userId") String userId, @Param("userPwd") String userPwd);

	/**
	 * 更新用户信息：生日、住址
	 */
	public void updateUserInfo(Student student);

	/**
	 * 更新用户密码
	 * @param student
	 */
	public void updateStudentPwd(Student student);

	/**
	 * 更新用户头像
	 * @param student
	 */
	public void updateStudentFace(Student student);
}
