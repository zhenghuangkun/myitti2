package com.awslabplatform_admin.util;


import java.util.ArrayList;
import java.util.List;

import com.awslabplatform_admin.entity.Menu;

/**
 * 将菜单list 转成树形结构
 * @author yuzhh
 *
 */
public class FormatTreeUtils {


	/**
	 * 将菜单list 转化成树形结构
	 * @param rootMenu
	 * @return
	 */
	public static List<Menu> formatTree(List<Menu> rootMenu){

		List<Menu> menuList = new ArrayList<Menu>();
		// 先找到所有的一级菜单
	    for (int i = 0; i < rootMenu.size(); i++) {
	        // 一级菜单parentId为0
	        if (0 == rootMenu.get(i).getMenuPID()) {
	            menuList.add(rootMenu.get(i));
	        }
	    }

	    // 为一级菜单设置子菜单，getChild是递归调用的
	    for (Menu menu : menuList) {
	        menu.setChildMenus(getChild(menu.getMenuId(), rootMenu));
	    }
		return menuList;

	}

	/**
	 * 递归查找子菜单
	 *
	 * @param id
	 *            当前菜单id
	 * @param rootMenu
	 *            要查找的列表
	 * @return
	 */
	private static List<Menu> getChild(int id, List<Menu> rootMenu) {
	    // 子菜单
	    List<Menu> childList = new ArrayList<>();
	    for (Menu menu : rootMenu) {
	        // 遍历所有节点，将父菜单id与传过来的id比较
	        if (menu.getMenuPID()!= 0) {
	            if (menu.getMenuPID() == id) {
	                childList.add(menu);
	            }
	        }
	    }
	    // 把子菜单的子菜单再循环一遍
	    for (Menu menu : childList) {
	    	// 没有url子菜单还有子菜单
	        //if(StringUtils.isEmpty(menu.getMenuUrl())) {
	            // 递归
	            menu.setChildMenus(getChild(menu.getMenuId(), rootMenu));
	        //}
	    }
	    // 递归退出条件
	    if (childList.size() == 0) {
	        return null;
	    }
	    return childList;
	}
}
