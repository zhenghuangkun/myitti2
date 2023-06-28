package com.awslabplatform_admin.controller.courseManage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.awslabplatform_admin.common.Common;
import com.awslabplatform_admin.common.ShiroPermissionConstant;
import com.awslabplatform_admin.common.message.CourseMessage;
import com.awslabplatform_admin.entity.ExperimentGroup;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Result;
import com.awslabplatform_admin.entity.User;
import com.awslabplatform_admin.service.courseManage.ExperimentGroupService;
import com.awslabplatform_admin.util.*;


/**
* 实验组管理Controller层
*
* @author   zhenghk
* @version  2018-3-15
*/
@Controller
@RequestMapping("/experiment")
public class ExperimentGroupController {

	/**日志*/
    private static Logger log = LoggerFactory.getLogger(ExperimentGroupController.class);

    /**实验组service*/
    @Autowired
    private  ExperimentGroupService  expGroupService;

    /**
     * 查询实验组
     * @param groupName 组名
     * @param userName	用户名
     * @param response
     */
	@RequestMapping("/selectGroup")
	public void selectExperimentGroup(String groupName, String userName,
			HttpServletResponse response) throws IOException {
		
	}

    
	/**
	 * 添加实验组
	 * @param group 实验组
	 * @param response
	 */
	@RequestMapping("/addGroup")
	public void addExperimentGroup(ExperimentGroup group, HttpServletResponse response){

	}

	/**
	 * 更新实验组
	 * @param group 更新实验组对象
	 * @param response
	 */
	@RequestMapping("/updateGroup")
	public void updateExperimentGroup(ExperimentGroup group, HttpServletResponse response){
		
	}

	/**
	 * 删除菜单
	 * @param group 删除实验组对象
	 * @param response
	 */
	@RequestMapping("/deleteGroup")
	public void deleteExperimentGroup(ExperimentGroup group, HttpServletResponse response){

	}

	/**
	 * 取得所有实验组
	 * @param response
	 */
	@RequestMapping("/getAllGroup")
	public void selectAllExperimentGroup(String courseId, HttpServletResponse response){
		// 修改课程时，将课程实验组选中
		List<String> expGroupList = null;
		System.out.println(courseId);
		if(courseId != null && !courseId.isEmpty()){
			expGroupList = expGroupService.findCourseExperimentGroupByCourseId(courseId);
			System.out.println(expGroupList);
			
		}
		
		String createAuthorId = "";
		
		int userRoleType = ShiroUtil.getCurrentUser().getRoleType();

		// 当前用户为教师或者助教，设置教师ID查询条件
		if(userRoleType == User.TEARCH_USER || userRoleType == User.ASSISTANT_USER){
			createAuthorId = ShiroUtil.getCurrentUserId();
		}
		
		// 取得所有实验组
		List<ExperimentGroup> groupList = expGroupService.listAllGroup(createAuthorId);
		
		//reData.put("groups", groupList);
		if(expGroupList != null){
			if(groupList != null){
				for (ExperimentGroup experimentGroup : groupList) {
					if(expGroupList.contains(experimentGroup.getGroupId())){
						experimentGroup.setChecked(true);
					}
				}
			}
		}
		
		
		//System.out.println(JSON.toJSONStringWithDateFormat(groupList, "yyyy-MM-dd HH:mm:ss"));
		WriteJsonUtil.writeJsonObject(groupList, response);
	}

	@RequestMapping(value = "/disableExperimentGroup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	@ResponseBody
	public Result disableExperimentGroup(String groupId) throws SQLException{
		// 实验组Id为null返回false  实验组不存在
		if(groupId == null){
			return ResultUtil.getResult(false, CourseMessage.NO_EXIST_SUCCES);
		}
		
		// 实验组Id为空返回false  实验组不存在
		if(groupId.isEmpty()){
			return ResultUtil.getResult(false, CourseMessage.NO_EXIST_SUCCES);
		}
		
		// 禁用实验组
		int ret = expGroupService.disableExperimentGroup(groupId);
		if(ret <= 0){
			return ResultUtil.getResult(false, CourseMessage.NO_EXIST_SUCCES);
		}
		
		return ResultUtil.getResult(true, CourseMessage.DISABLE_EXPERIMENT_GROUP_SUCCESS);
	}
	
	@RequestMapping(value = "/enableExperimentGroup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	@ResponseBody
	public Result enableExperimentGroup(String groupId){
		// 实验组Id为null返回false  实验组不存在
		if(groupId == null){
			return ResultUtil.getResult(false, CourseMessage.NO_EXIST_SUCCES);
		}
		
		// 实验组Id为空返回false  实验组不存在
		if(groupId.isEmpty()){
			return ResultUtil.getResult(false, CourseMessage.NO_EXIST_SUCCES);
		}
		
		// 启用实验组
		int ret = expGroupService.enableExperimentGroup(groupId);
		if(ret <= 0){
			return ResultUtil.getResult(false, CourseMessage.NO_EXIST_SUCCES);
		}
		
		return ResultUtil.getResult(true, CourseMessage.ENABLE_EXPERIMENT_GROUP_SUCCESS);
	}
	
}
