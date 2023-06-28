package com.awslabplatform_admin.service.courseManage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.awslabplatform_admin.entity.Experiment;
import com.awslabplatform_admin.entity.ExperimentGroup;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Student;
import com.awslabplatform_admin.entity.User;
import com.awslabplatform_admin.service.base.BaseService;

/**
* 实验组管理Service层
*
* @author  zhenghk
* @version 2018/3/15
*/
public interface ExperimentGroupService extends BaseService<ExperimentGroup, String> {

	/**
	 * 通过组名获取实验组列表
	 * @param groupName 组名
	 * @return List<ExperimentGroup> 实验组用户集合
	 */
	public List<ExperimentGroup> listExperimentGroupByGroupName(String groupName);

	/**
	 * 查找课程实验组
	 * @param condition
	 * @return
	 */
	List<String> findCourseExperimentGroupByCourseId(String courseId);

	/**
	 * 查找所有组用户列表
	 * @param groupId 组Id
	 * @return
	 */
	public List<User> listUserByGroupId(String groupId);

	/**
	 * 查找所有组信息(当前老师的所有实验组)
	 *
	 */
	public List<ExperimentGroup> listAllGroup(String createAuthorId);


    /**
     *
     * 查询实验组信息（所有的实验组）
     * @param PageInfo
     * @return
     **/
	public void getExperimentGroupPageInfo(PageInfo pageInfo);

	
    /**
     *
     * 删除实验组
     * @param param
     * @return
     **/
	public void deleteExperimentGroup(Map<String,Object> param);

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
	int addCourseExperimentGroupMapping(String courseId, List<ExperimentGroup> groupList);

	/**
	 * 删除课程实验组映射
	 * @param courseId
	 * @return
	 */
	int deleteCourseExperimentGroupMapping(String courseId);

	/**
	 * 解析json字符串并转成实验组对象
	 * @param jsonStr
	 * @return
	 */
	public List<ExperimentGroup> parseJsonToExperimentGroup(String jsonStr);

	/**
	 * 搜索tb_course_experimentgroup表，找出groupId
	 */
	public List<String> getGroupIdByCourse(String courseId);

	/**
	 * 通过groupId查找 ExperimentGroup实体类集合(ztree用)
	 */
	public ExperimentGroup getExperimentGroupByGroupId(String groupId);

	/**
	 * 通过实验组Id查找实验组(非ztree用)
	 */
	public ExperimentGroup getExperimentGroupByGroupIdNoZtree(String groupId);
	
	/**
	 * 取得学生信息
	 * @param pageInfo
	 * @return
	 */
	public void getStudentInfo(PageInfo pageInfo);

	/**
	 * 新增一个实验组
	 */
	public void addNewGroup(ExperimentGroup experimentGroup);
	
	/**
	 * 添加多个学生到实验组
	 */
	public int addMultiStudentToExperimentGroup(String groupId, String[] studentArray);
	
	/**
	 * 更新实验组
	 */
	public int updateExperimentGroup(ExperimentGroup experimentGroup);
	
	/**
	 * 更新实验组的学生
	 */
	public boolean updateExperimentGroupStudent(String groupId, String[] studentArray);
	
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
