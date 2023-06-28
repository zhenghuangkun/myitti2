package com.awslabplatform_admin.dao.userManage;

import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Student;

import org.apache.ibatis.annotations.Param;

import com.awslabplatform_admin.dao.base.BaseDao;
import com.awslabplatform_admin.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 用户管理dao 接口
 * @author weix
 *
 */
public interface UserDao extends BaseDao<User, Integer>{

	/**
	 * 查询根据不同的用户类型查询用户管理的数据，显示在表格的列表上
	 * @param pageInfo
	 */
    List<User> selectUserManageData(PageInfo pageInfo);

    /**
     * 查询根据不同的用户类型查询用户管理的总条数
     * @param pageInfo
     */
	int countUserManage(PageInfo pageInfo);

	/**
	 * 通过用户名获取用户对象
	 * @param userName
	 */
	User getByUserEmail(@Param("email") String email);

	User getByUserPhone(@Param("phoneNum") String phoneNum);

	/**
	 * 通过评论表的userId获取学生
	 */
	public Student getStudentById(String userId);

	/**
	 * 通过groupId查找所有userId（学生Id）
	 */
	public List<String> getUserIdbyGroupId(String groupId);

	/**
	 * 假删除用户管理数据信息
	 * @param userId
	 * @return
	 */
	boolean deleteUserData(Map<String,Object> map);


	/**
	 * 根据条件获取用户平台管理员的数据，将数据显示在编辑的页面上
	 * @param user
	 * @return
	 */
	User selectIdUserData(User user);

	/**
	 * 查询添加姓名是否已经存在
	 * @param user
	 */
	int countUserData(User user);

	/**
	 * 查询用户管理学生的数据，显示在表格的列表上
	 * @param pageInfo
	 */
    List<Student> selectUserStuManageData(PageInfo pageInfo);

    /**
     * 查询用户管理学生的总条数
     * @param pageInfo
     */
	int countUserStuManage(PageInfo pageInfo);

	/**
	 * 查询添加学生学号是否已经存在
	 * @param user
	 */
	int countUserStuData(Student student);

	/**
	 * 添加学生
	 * @param student
	 * @return
	 */
	boolean addUserStu(Student student);

	/**
	 * 假删除激活学生数据信息
	 * @param userId
	 * @return
	 */
	boolean deleteUserStuData(Map<String,Object> map);

	/**
	 * 更新用户密码
	 * @param user
	 */
	public void updateUserPwd(User user);
	

	/**
	 * 根据条件获取学生的数据，将数据显示在编辑的页面上
	 * @param user
	 * @return
	 */
	Student selectIdUserStuData(Student student);
	
	/**
	 * 编辑学生
	 * @param student
	 * @return
	 */
	boolean editUserStu(Student student);

	

	/**
	 * 查询正在做实验的学生，显示在表格的列表上
	 * @param pageInfo
	 */
    List<Student> selectUserStuexperiment(PageInfo pageInfo);

    /**
     * 查询正在做实验的学生总数
     * @param pageInfo
     */
	int countUserManageStuexperiment(PageInfo pageInfo);
	
	/**
	 * 查询出所有的院系 accountId 和院系名称
	 * @param map
	 * @return
	 */
	List<User> seleAllDepartments(Map<String,Object> map);
	
	/**
	 * 获取用户中的数据是否存在院系和学校的机构管理是否被用了
	 * @param map
	 * @return
	 */
	int countUserAccount(Map<String,Object> map);
	
	int countStudentId(Map<String,Object> map);

}
