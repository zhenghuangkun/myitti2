package com.awslabplatform_admin.service.courseManage.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.awslabplatform_admin.entity.Experiment;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.awslabplatform_admin.dao.base.BaseDao;
import com.awslabplatform_admin.dao.courseManage.ExperimentGroupDao;
import com.awslabplatform_admin.entity.ExperimentGroup;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Student;
import com.awslabplatform_admin.entity.User;
import com.awslabplatform_admin.service.base.impl.BaseServiceImpl;
import com.awslabplatform_admin.service.courseManage.ExperimentGroupService;

/**
 * 实验组管理service 接口实现类
 * @author zhenghk
 *
 */
@Service("ExperimentGroupService")
public class ExperimentGroupServiceImpl extends BaseServiceImpl<ExperimentGroupDao, ExperimentGroup, String> implements ExperimentGroupService {

	/**
	 * 通过组名获取实验组列表
	 * @param groupName 组名
	 * @return List<ExperimentGroup> 实验组用户集合
	 */
	@Override
	public List<ExperimentGroup> listExperimentGroupByGroupName(String groupName) {
		// TODO Auto-generated method stub
		return baseDao.listExperimentGroupByGroupName(groupName);
	}

	/**
	 * 查找课程实验组
	 * @param courseId
	 * @return
	 */
	@Override
	public List<String> findCourseExperimentGroupByCourseId(String courseId){
		return baseDao.findCourseExperimentGroupByCourseId(courseId);
	}

	/**
	 * 通过组Id查找所有组用户列表
	 * @param groupId 组Id
	 * @return
	 */
	@Override
	public List<User> listUserByGroupId(String groupId) {
		// TODO Auto-generated method stub
		return baseDao.listUserByGroupId(groupId);
	}

	/**
	 * 查找所有组信息(当前老师的所有实验组)
	 *
	 */
	@Override
	public List<ExperimentGroup> listAllGroup(String createAuthorId) {
		// TODO Auto-generated method stub
		return baseDao.listAllGroup(createAuthorId);
	}

	/**
    *
    * 查询实验组信息（所有的实验组）
    * @param pageInfo
    * @return
    **/
	@Override
	public void getExperimentGroupPageInfo(PageInfo pageInfo) {
		/*查询分页数据*/
		pageInfo.setData(baseDao.listExperimentGroupInfo(pageInfo));
		/*查询总数*/
		int total = baseDao.listExperimentGroupTotal(pageInfo);
		pageInfo.setRecordsTotal(total);
		pageInfo.setRecordsFiltered(total);//过滤记录数，dataTable必要参数
		return ;
	}

	
   /**
    *
    * 删除实验组
    * @param param
    * @return
    **/
	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public void deleteExperimentGroup(Map<String, Object> param) {
		// TODO Auto-generated method stub

	}

	/**
	 * 更新课程实验组-指定用户为选中
	 * @param groupList
	 * @return
	 */
	@Transactional(propagation= Propagation.REQUIRED)
	public int updateExperimentGroupUserCheckState(List<ExperimentGroup> groupList){

		return baseDao.updateExperimentGroupUserCheckState(groupList);
	}

	/**
	 * 添加课程实验组映射
	 * @param courseId
	 * @param groupList
	 * @return
	 */
	@Transactional(propagation= Propagation.REQUIRED)
	public int addCourseExperimentGroupMapping(String courseId, List<ExperimentGroup> groupList){

		return baseDao.addCourseExperimentGroupMapping(courseId, groupList);
	}

	/**
	 * 删除课程实验组映射
	 * @param courseId
	 * @return
	 */
	@Transactional(propagation= Propagation.REQUIRED)
	public int deleteCourseExperimentGroupMapping(String courseId){
		return baseDao.deleteCourseExperimentGroupMapping(courseId);
	}

	/**
	 * 解析json字符串并转成实验组对象
	 * @param jsonStr
	 * @return
	 */
	@Override
	public List<ExperimentGroup> parseJsonToExperimentGroup(String jsonStr){

		List<ExperimentGroup> experimentGroup = null;
		List<Student> children = null;

		try{
			experimentGroup = new ArrayList<ExperimentGroup>();

			JSONArray jexparray =  JSONObject.parseArray(jsonStr);

			//获取组ID
			int size = jexparray.size();

			for(int i = 0; i < size; i++){
				ExperimentGroup expGroup = new ExperimentGroup();
				children = new ArrayList<Student>();

				// 获取json 对象
				JSONObject obj = jexparray.getJSONObject(i);

				// 取得组Id
				String groupId = obj.getString("groupId");
				// 取得课程实验组里包含的用户
				String childrenStr = obj.getString("children");

				JSONArray childrenArray = JSONObject.parseArray(childrenStr);

				int childrenSize = childrenArray.size();

				for(int j = 0; j < childrenSize; j++){
					// 获取组用户json 对象
					JSONObject childrenObj = childrenArray.getJSONObject(j);

					// 获取用户ID
					String id = childrenObj.getString("id");
					//String checked = childrenObj.getString("checked");
					Boolean checked = childrenObj.getBoolean("checked");

					// 判断是否选中
					if(checked){
						// 保存用户到组成员children
						Student student = new Student();

						// 设置用户Id
						student.setId(id);

						children.add(student);
					}

				}
				// 设置组ID
				expGroup.setGroupId(groupId);
				// 添加组用户成员
				expGroup.setChildren(children);
				// 添加组到list里
				experimentGroup.add(expGroup);
			}


		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

		return experimentGroup;
	}

	/**
	 * 搜索tb_course_experimentgroup表，找出groupId
	 */
	@Override
	public List<String> getGroupIdByCourse(String courseId){
		return baseDao.findCourseExperimentGroupByCourseId(courseId);
	}

	/**
	 * 通过groupId查找 ExperimentGroup实体类集合(ztree用)
	 */
	@Override
	public ExperimentGroup getExperimentGroupByGroupId(String groupId){
		return	baseDao.getExperimentGroupByGroupId(groupId);
	}

	/**
	 * 通过实验组Id查找实验组(非ztree用)
	 */
	@Override
	public ExperimentGroup getExperimentGroupByGroupIdNoZtree(String groupId){
		return baseDao.getExperimentGroupByGroupIdNoZtree(groupId);
	}
	
	/**
	 * 取得学生信息
	 * @param pageInfo
	 * @return
	 */
	public void getStudentInfo(PageInfo pageInfo){
		/*查询分页数据*/
		pageInfo.setData(baseDao.getStudentInfo(pageInfo));
		/*查询总数*/
		int total = baseDao.getStudentInfoTotal(pageInfo);
		pageInfo.setRecordsTotal(total);
		pageInfo.setRecordsFiltered(total);//过滤记录数，dataTable必要参数
		return;
	}

	/**
	 * 新增一个实验组
	 */
	@Transactional(propagation= Propagation.REQUIRED)
	public void addNewGroup(ExperimentGroup experimentGroup){
		baseDao.addNewGroup(experimentGroup);
	}
	
	/**
	 * 添加多个学生到实验组
	 */
	@Transactional(propagation= Propagation.REQUIRED)
	public int addMultiStudentToExperimentGroup(String groupId, String[] studentArray){
		return baseDao.addMultiStudentToExperimentGroup(groupId, studentArray);
	}
	
	/**
	 * 更新实验组
	 */
	@Transactional(propagation= Propagation.REQUIRED)
	public int updateExperimentGroup(ExperimentGroup experimentGroup){
		return baseDao.updateExperimentGroup(experimentGroup);
	}
	
	/**
	 * 更新实验组的学生
	 */
	@Transactional(propagation= Propagation.REQUIRED)
	public boolean updateExperimentGroupStudent(String groupId, String[] studentArray){
		
		int ret = baseDao.deleteStudentFromExperimentGroup(groupId);
		
		if(ret < 0){
			return false;
		}
		
		ret = addMultiStudentToExperimentGroup(groupId, studentArray);
		
		if(ret < 0){
			return false;
		}
		return true;
	}
	
	/**
	 * 禁用实验组
	 * @param groupId
	 * @return
	 */
	@Transactional(propagation= Propagation.REQUIRED)
	public int disableExperimentGroup(@Param("groupId") String groupId){
		return baseDao.disableExperimentGroup(groupId);
	}
	
	/**
	 * 启用实验组
	 * @param groupId
	 * @return
	 */
	@Transactional(propagation= Propagation.REQUIRED)
	public int enableExperimentGroup(@Param("groupId") String groupId){
		return baseDao.enableExperimentGroup(groupId);
	}
}
