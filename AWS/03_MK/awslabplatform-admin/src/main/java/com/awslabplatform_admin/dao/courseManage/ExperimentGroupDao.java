package com.awslabplatform_admin.dao.courseManage;

import java.util.List;
import java.util.Map;






import org.apache.ibatis.annotations.Param;

import com.awslabplatform_admin.dao.base.BaseDao;
import com.awslabplatform_admin.entity.ExperimentGroup;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Student;
import com.awslabplatform_admin.entity.User;

/**
 * 实验组管理DAO
 * @author zhenghk
 *
 */
public interface ExperimentGroupDao extends BaseDao<ExperimentGroup, String>{

	/**
	 * 查找实验组
	 * @param condition
	 * @return
	 */
	List<ExperimentGroup> listExperimentGroupByGroupName(String groupName);

	/**
	 * 查找课程实验组
	 * @param condition
	 * @return
	 */
	List<String> findCourseExperimentGroupByCourseId(String courseId);

	/**
	 * 查询所有实验组数量
	 * @param info
	 * @return
	 */
	int listExperimentGroupTotal(PageInfo pageInfo);
	
	/**
	 * 查询实验组
	 * @param pageInfo
	 * @return
	 */
	List<ExperimentGroup> listExperimentGroupInfo(PageInfo pageInfo);

	/**
	 * 查询所有实验组信息(当前老师的所有实验组)
	 * @return
	 */
	List<ExperimentGroup> listAllGroup(@Param("createAuthorId") String createAuthorId);

	/**
	 * 查询组用户
	 * @return
	 */
	List<User> listGroupUser();

	/**
	 * 查询组用户
	 * @param groupId 组Id
	 * @return
	 */
	List<User> listUserByGroupId(String groupId);


	/**
	 * 查询列表总数
	 * @param info
	 * @return
	 */
	int getExperimentGroupInfoTotal(PageInfo info);

	/**
	 *
	 * 删除实验组
	 * @param param
	 */
	void deleteExperimentGroup(Map<String,Object> param);

	/**
	 * 获取实验组数量
	 * @param condition
	 * @return
	 */
	int getExperimentGroupNum(Map<String,Object> condition);


	/**
	 * 查询用户Id是否在实验组
	 * @param userId 用户Id
	 * @param experimentGroupId 实验组ID
	 * @return
	 */
	Boolean findUserIdInExperimentGroup( String userId, String experimentGroupId );

	/**
	 * 查询用户名是否在实验组
	 * @param userName 用户名
	 * @param experimentGroupId 实验组ID
	 * @return
	 */
	Boolean findUserNameInExperimentGroup( String userName, String experimentGroupId );

	/**
	 * 更新课程实验组-指定用户为选中
	 * @param groupList
	 * @return
	 */
	int updateExperimentGroupUserCheckState(List<ExperimentGroup> groupList);

	/**
	 * 添加课程实验组映射
	 * @param courseId
	 * @param groupList
	 * @return
	 */
	int addCourseExperimentGroupMapping(@Param("courseId")String courseId, @Param("groupList")List<ExperimentGroup> groupList);

	/**
	 * 删除课程实验组映射
	 * @param courseId
	 * @return
	 */
	int deleteCourseExperimentGroupMapping(@Param("courseId")String courseId);


	/**
	 * 通过实验组Id查找实验组(ztree用)
	 */
	ExperimentGroup getExperimentGroupByGroupId(String groupId);


	/**
	 * 通过实验组Id查找实验组(非ztree用)
	 */
	ExperimentGroup getExperimentGroupByGroupIdNoZtree(String groupId);
	
	
	/**
	 * 取得学生信息
	 * @param pageInfo
	 * @return
	 */
	List<Student> getStudentInfo(PageInfo pageInfo);

	/**
	 * 取得学生信息数量
	 * @param pageInfo
	 * @return
	 */
	int getStudentInfoTotal(PageInfo pageInfo);

	/**
	 * 新增一个实验组
	 */
	public void addNewGroup(ExperimentGroup experimentGroup);
	
	/**
	 * 添加多个学生到实验组
	 */
	int addMultiStudentToExperimentGroup(@Param("groupId") String groupId, @Param("studentArray")String[] studentArray);
	
	
	/**
	 * 更新实验组
	 */
	public int updateExperimentGroup(ExperimentGroup experimentGroup);
	
	/**
	 * 删除实验组的学生
	 */
	int deleteStudentFromExperimentGroup(@Param("groupId") String groupId);
	
	
	/**
	 * 禁用实验组
	 * @param groupId
	 * @return
	 */
	public int disableExperimentGroup(@Param("groupId") String groupId);
	
	/**
	 * 启用实验组
	 * @param groupId
	 * @return
	 */
	public int enableExperimentGroup(@Param("groupId") String groupId);
}
