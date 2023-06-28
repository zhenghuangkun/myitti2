package com.awslabplatform_admin.controller.systemManage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.awslabplatform_admin.common.Common;
import com.awslabplatform_admin.entity.Menu;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.service.systemManage.MenuService;
import com.awslabplatform_admin.util.*;


/**
* 菜单管理Controller层
*
* @author   WEIX
* @version  2017-9-30
*/
@Controller
@RequestMapping("/menu")
public class MenuController {

	/**日志*/
    private static Logger log = LoggerFactory.getLogger(MenuController.class);

    /**菜单service*/
    @Autowired
    private  MenuService  menuService;

    /**
     * 查询菜单
     * @param menuName 菜单名称
     * @param menuLevel	菜单等级
     * @param start	从第n条开始
     * @param length 每页显示条数
     * @param draw 绘制计数器（前后端dataTable标识）
     * @param response
     */
	@RequestMapping("/selectMenu")
	public void selectMenu(String menuName, Integer menuLevel,  int start, int length, int draw,
			HttpServletResponse response) throws IOException {
		/*设置查询条件*/
		Map<String,Object> condition  = new HashMap<String, Object>();
		condition.put("menuName", menuName.trim());//菜单名称
		condition.put("menuLevel", menuLevel);//菜单等级
		condition.put("state", Common.STATE_ACTIVE);//状态

		/*分页查询菜单*/
		PageInfo pageInfo = new PageInfo(start, length, draw);
		pageInfo.setCondition(condition);
		menuService.getMenuPageInfo(pageInfo);

		/*数据返回*/
		JsonTool.toJson(pageInfo, response);
	}

    /**
     * 查询父菜单
     * @param menuLevel 菜单等级
     * @param response
     */
	@RequestMapping("/selectParentMenu")
	public void selectParentMenu(Integer menuLevel,  HttpServletResponse response){
		/*设置查询条件*/
		Map<String,Object> condition  = new HashMap<String, Object>();
		condition.put("menuLevel", menuLevel - 1);//查询上级菜单
		condition.put("state", Common.STATE_ACTIVE);//状态

		/*数据返回*/
		WriteJsonUtil.writeJsonArray(menuService.listByCondition(condition), response);
	}


	/**
	 * 添加菜单
	 * @param menu 菜单实体类
	 * @param response
	 */
	@RequestMapping("/addMenu")
	public void addMenu(Menu menu, HttpServletResponse response){

		Map<String,Object> message = new HashMap<String, Object>();

		//校验菜单名称
		if(Validator.isEmpty(menu.getMenuName())){
			message.put("sucess", false);
			message.put("message", "请输入菜单名称!");
			WriteJsonUtil.writeJsonObject(message, response);
			return;
		}

		String currentTime = TimeUtils.currentTime();//获取系统当前时间

		/*插入菜单*/
		menu.setOperator(ShiroUtil.getCurrentUsername());//设置操作人
		menu.setCreateTime(currentTime);//设置添加时间
		menu.setUpdateTime(currentTime);//设置修改时间
		menu.setState(Common.STATE_ACTIVE);//设置初始化状态
		menuService.insert(menu);

		/*返回成功提示*/
		message.put("sucess", true);
		WriteJsonUtil.writeJsonObject(message, response);
	}

	/**
	 * 修改菜单
	 * @param menu 菜单对象
	 * @param response
	 */
	@RequestMapping("/updateMenu")
	public void updateMenu(Menu menu, HttpServletResponse response){
		Map<String,Object> message = new HashMap<String, Object>();

		if(Validator.isEmpty(menu.getMenuName())){
			message.put("sucess", false);
			message.put("message", "请输入菜单名称!");
			WriteJsonUtil.writeJsonObject(message, response);
			return;
		}

		String currentTime = TimeUtils.currentTime();//获取系统当前时间

		/*更新菜单*/
		menu.setOperator(ShiroUtil.getCurrentUsername());//设置操作人
		menu.setUpdateTime(currentTime);//设置修改时间
		menu.setState(Common.STATE_ACTIVE);
		menuService.updateByPrimaryKeySelective(menu);

		/*返回成功提示*/
		message.put("sucess", true);
		WriteJsonUtil.writeJsonObject(message, response);
	}

	/**
	 * 删除菜单
	 * @param menuId 菜单名ID
	 * @param response
	 */
	@RequestMapping("/deleteMenu")
	public void deleteMenu(Integer menuId, HttpServletResponse response){

		Map<String,Object> message = new HashMap<String, Object>();
		/*删除菜单*/
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("menuId", menuId);//菜单Id
		param.put("opeartor", ShiroUtil.getCurrentUsername());//设置操作人
		param.put("state", Common.STATE_DELETE);//设置删除状态
		menuService.deleteMenu(param);

		/*返回成功提示*/
		message.put("sucess", true);
		WriteJsonUtil.writeJsonObject(message, response);
	}

	/**
	 * 获取菜单树
	 * @param response
	 */
	@RequestMapping("/getMenuTree")
	public void selectMenuTree(String roleId, HttpServletResponse response){
		Map<String,Object> reData = new HashMap<String, Object>();

		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("state", Common.STATE_ACTIVE);//设置未删除状态
		reData.put("menus", menuService.listByCondition(condition));//查询所有菜单

		reData.put("roleMenus", menuService.listRoleMenu(roleId));//查询角色菜单权限

		WriteJsonUtil.writeJsonObject(reData, response);
	}

}
