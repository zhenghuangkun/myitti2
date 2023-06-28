package com.awslabplatform.service.userManage.impl;

import com.awslabplatform.util.RandomPasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awslabplatform.dao.userManage.UserDao;
import com.awslabplatform.entity.Student;
import com.awslabplatform.service.base.impl.BaseServiceImpl;
import com.awslabplatform.service.userManage.UserService;

/**
 * 用户管理service 接口实现类
 *
 * @author WEIX
 */
@Service("UserService")
public class UserServiceImpl extends BaseServiceImpl<UserDao, Student, Integer> implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public Student getStudentByEmail(String email){
		return baseDao.getStudentByEmail(email) ;
	}

	@Override
	public Student getStudentByPhone(String phone){
		return baseDao.getStudentByPhone(phone);
	}

	@Override
	public String getPwdByUserId(String userId){
		return baseDao.getPwdByUser(userId);
	}

	@Override
	public Student getStudentById(String id) {
		return userDao.selectById(id);
	}

	@Override
	public void updateEmailByUserId(String userId, String Email){
		userDao.updateUserEmailByUserId(userId, Email);
	}

	@Override
	public void updatePwdByUserId(String id, String Pwd){
		String hashPwd = RandomPasswordUtil.getHash(Pwd);
		userDao.updatePwdByUserId(id, hashPwd);
	}

	/**
	 * 更新用户信息：生日、住址
	 */
	@Override
	public void updateUserInfo(Student student){
		baseDao.updateUserInfo(student);
	}

	/**
	 * 更新用户密码
	 */
	public void updateStudentPwd(Student student){
		baseDao.updateStudentPwd(student);
	}


	/**
	 * 更新用户头像
	 * @param student
	 */
	public void updateStudentFace(Student student){
		baseDao.updateStudentFace(student);
	}
}
