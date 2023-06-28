package com.awslabplatform_admin.controller.systemManage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.awslabplatform_admin.common.Common;
import com.awslabplatform_admin.common.Dictionary;
import com.awslabplatform_admin.common.message.SysMgMessage;
import com.awslabplatform_admin.entity.DictionaryData;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Role;
import com.awslabplatform_admin.service.systemManage.DictionaryDataService;
import com.awslabplatform_admin.service.systemManage.RoleService;
import com.awslabplatform_admin.util.*;


/**
* 角色管理Controller层
*
* @author   weix
* @version  2018-2-28
*/
@Controller
@RequestMapping("/role")
public class RoleController {

	/**日志*/
    private static Logger log = LoggerFactory.getLogger(RoleController.class);

    /**角色service*/
    @Autowired
    private  RoleService  roleService;
    
    /**数据字典service*/
    @Autowired
    private DictionaryDataService dictionaryDataService;
    
    /**
	 * 角色管理菜单点击
	 * 
	 * @param 无
	 * @author yuzhh
	 * @date 2018-03-27
	 */
	@RequiresRoles(value={"admin"}, logical=Logical.OR)
	@RequestMapping(value = "/rolemg",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String systemgtRole(Model model) {
		
		//List<DictionaryData> roleType = dictionaryDataService.getSubItemListData(Dictionary.DICTIONARY_ROLETYPE.getDicCode());
		//model.addAttribute("roleType", roleType);
		return "/systemManage/role";
	}
	
	
	/**
	 * 权限管理菜单点击
	 * @return
	 * @author yuzhh
	 * @date 2018-03-28
	 */
	@RequiresRoles(value={"admin"}, logical=Logical.OR)
	@RequestMapping(value = "/permission",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String systemgtPermission(Model model) {
		
		/**
		 * 数据字典获取角色类型数据
		 */
		List<DictionaryData> roleType = dictionaryDataService.getSubItemListData(Dictionary.DICTIONARY_ROLETYPE.getDicCode());
		model.addAttribute("roleType", roleType);
		return "/systemManage/permission";
	}

    /**
     * 查询角色
     * @param roleName 角色名称
     * @param roleType 角色类型保存数据字典表中的UUID
     * @param start 从第N条开始
     * @param length 每页显示N条
     * @param response
     */
	@RequestMapping("/selectRole")
	public void selectRole(String roleName, String roleType,  int start, int length, int draw,
			HttpServletResponse response) throws IOException {
		/*设置查询条件*/
		Map<String,Object> condition  = new HashMap<String, Object>();
		condition.put("roleName", roleName.trim());//角色名称
		condition.put("roleType", roleType);//角色类型
		condition.put("state", Common.STATE_ACTIVE);//状态

		/*分页查询角色*/
		PageInfo pageInfo = new PageInfo(start, length, draw);
		pageInfo.setCondition(condition);
		roleService.getRolePageInfo(pageInfo);

		/*数据返回*/
		//WriteJsonUtil.writeJsonObject(pageInfo, response);

		JsonTool.toJson(pageInfo,response);
	}


	/**
	 * 添加角色
	 * @param role 角色实体类
	 * @param response
	 */
	@RequestMapping("/addRole")
	public void addRole(Role role, HttpServletResponse response){

		Map<String,Object> message = new HashMap<String, Object>();

		if(Validator.isEmpty(role.getRoleName())){
			message.put("sucess", false);
			message.put("message", "请输入角色名称");
			WriteJsonUtil.writeJsonObject(message, response);
			return;
		}

		String currentTime = TimeUtils.currentTime();//获取系统当前时间

		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("roleName", role.getRoleName());
		condition.put("state", Common.STATE_ACTIVE);//未删除状态
		
		/*判断角色是否存在*/
		if(roleService.existsRole(condition)){
			message.put("sucess", false);
			message.put("message", SysMgMessage.ROLE_EXISTS.getContent());
			WriteJsonUtil.writeJsonObject(message, response);
			return;
		}

		/*插入角色*/
		role.setCreateBy(ShiroUtil.getCurrentUsername());//设置操作人
		role.setUpdateBy(ShiroUtil.getCurrentUsername());
		role.setCreateTime(currentTime);//设置添加时间
		role.setUpdateTime(currentTime);//设置修改时间
		role.setState(Common.STATE_ACTIVE);//设置状态
		roleService.insert(role);

		/*返回成功提示*/
		message.put("sucess", true);
		WriteJsonUtil.writeJsonObject(message, response);
	}

	/**
	 * 修改角色
	 * @param role 角色对象
	 * @param response
	 */
	@RequestMapping("/updateRole")
	public void updateRole(Role role, HttpServletResponse response){
		Map<String,Object> message = new HashMap<String, Object>();

		if(Validator.isEmpty(role.getRoleName())){
			message.put("sucess", false);
			message.put("message", "请输入角色名称");
			WriteJsonUtil.writeJsonObject(message, response);
			return;
		}

		String currentTime = TimeUtils.currentTime();//获取系统当前时间

		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("roleId", role.getRoleId());
		condition.put("roleName", role.getRoleName());
		condition.put("state", Common.STATE_ACTIVE);//未删除状态
		if(roleService.existsRole(condition)){
			message.put("sucess", false);
			message.put("message", SysMgMessage.ROLE_EXISTS.getContent());
			WriteJsonUtil.writeJsonObject(message, response);
			return;
		}

		/*更新角色*/
		role.setUpdateBy(ShiroUtil.getCurrentUsername());
		role.setUpdateTime(currentTime);//设置修改时间
		roleService.updateByPrimaryKeySelective(role);

		/*返回成功提示*/
		message.put("sucess", true);
		WriteJsonUtil.writeJsonObject(message, response);
	}

	/**
	 * 删除角色
	 * @param roleId 角色id
	 * @param response
	 */
	@RequestMapping("/deleteRole")
	public void deleteRole(String roleId, HttpServletResponse response){

		Map<String,Object> message = new HashMap<String, Object>();

		/*删除角色*/
		Map<String,Object> param  = new HashMap<String, Object>();
		param.put("roleId", roleId);//角色Id
		param.put("updateBy", ShiroUtil.getCurrentUsername());//设置操作人
		param.put("state", Common.STATE_DELETE);//设置删除状态
		boolean re = roleService.deleteRole(param);

		/*返回成功提示*/
		message.put("sucess", re);
		WriteJsonUtil.writeJsonObject(message, response);
	}

	/**
	 * 设置角色权限
	 * @param roleId 角色id
	 * @param response
	 */
	@RequestMapping("/setRolePermisson")
	public void getRolePermisson(String roleId, String[] menuIdList,HttpServletResponse response){
		Map<String,Object> message = new HashMap<String, Object>();
		message.put("sucess", roleService.updateRolePermission(roleId, menuIdList));
		message.put("message", SysMgMessage.PERMISSION_SET_ERROR.getContent());
		WriteJsonUtil.writeJsonObject(message, response);
	}
	
}
