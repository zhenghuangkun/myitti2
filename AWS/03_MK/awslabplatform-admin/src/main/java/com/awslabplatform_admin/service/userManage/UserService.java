package com.awslabplatform_admin.service.userManage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;

import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Result;
import com.awslabplatform_admin.entity.Student;
import com.awslabplatform_admin.entity.User;
import com.awslabplatform_admin.service.awsAccountManage.AwsIamsService;
import com.awslabplatform_admin.service.base.BaseService;
import com.awslabplatform_admin.service.mechanismManage.MechanismService;
import com.awslabplatform_admin.service.systemManage.RoleService;

/**
 * 用户管理service
 * @author WEIX
 *
 */
public interface UserService extends BaseService<User, Integer> {

	/**
	 * 通过用户名获取用户对象
	 * @param userName
	 * @return
	 */
	public User getByUserEmail(String email);

	public User getByUserPhone(String phoneNum);

	/**
     * 查询根据不同的用户类型查询用户管理的数据，显示在表格的列表上查询分页信息
     * @param pageInfo
     **/
	void getUserManageData(PageInfo pageInfo);

	/**
     * 查询用户管理学生数据，显示在表格的列表上查询分页信息
     * @param pageInfo
     **/
	void getUserStuPageInfo(PageInfo pageInfo);

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
	 * 添加用户数据
	 * @param user
	 * @return
	 */
	Result addUserData(User user,AwsIamsService awsIamsService,RoleService roleService);

	/**
	 * 编辑用户数据
	 * @param user
	 * @return
	 */
	Result editUser(User user,AwsIamsService awsIamsService,RoleService roleService);


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
	 * 下载Excel模板
	 * @param request
	 * @param response
	 */
	Result downloadExcelModel(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 表格数据导入进行封装
	 * @param sheet
	 * @param rowCount
	 * @param columnCount
	 * @param mag
	 * @return
	 */
	List<Student> insertExcelData(Sheet sheet,MechanismService mechanismService,HashMap<String, Object> mag,HttpServletRequest request);

	/**
	 * 导出批量导入数据的错误数据以Excel的形式列出
	 * @param tableList
	 * @param request
	 */
	void errorExportExcel(List<Student> tableList,HttpServletRequest request);

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
	 * 编辑学生数据
	 * @param student
	 * @return
	 */
	Result editUserStudent(Student student);
	
	/**
	 * 查询正在做实验的学生，显示在表格的列表上查询分页信息
	 * @param pageInfo
	 */
    void getUserStuexperiment(PageInfo pageInfo);
    
    /**
	 * 查询出所有的院系 accountId 和院系名称
	 * @param map
	 * @return
	 */
	List<User> getAllDepartments(Map<String,Object> map);
	
	/**
	 * 获取用户中的数据是否存在院系和学校的机构管理是否被用了
	 * @param map
	 * @return
	 */
	int countUserAccount(Map<String,Object> map);
	
	int countStudentId(Map<String,Object> map);

}
