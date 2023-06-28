package com.awslabplatform.service.userManage;

import com.awslabplatform.entity.Student;
import com.awslabplatform.service.base.BaseService;

/**
 * 用户管理service
 * @author WEIX
 *
 */
public interface UserService extends BaseService<Student, Integer> {

	/**
	 * 联合student表、user表查询用户
	 */
	public Student getStudentByEmail(String email);

	/**
	 * 联合student表、user表查询用户
	 */

	public Student getStudentByPhone(String phone);


	public String getPwdByUserId(String userId);

	Student getStudentById(String id);

	public void updateEmailByUserId(String id, String Email);

	public void updatePwdByUserId(String id, String Pwd);

	/**
	 * 更新用户信息：生日、住址
	 */
	public void updateUserInfo(Student student);

	/**
	 * 更新用户密码
	 */
	public void updateStudentPwd(Student student);

	/**
	 * 更新用户头像
	 * @param student
	 */
	public void updateStudentFace(Student student);
}
