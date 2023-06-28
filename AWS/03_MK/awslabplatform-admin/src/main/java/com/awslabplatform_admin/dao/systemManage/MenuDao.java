package com.awslabplatform_admin.dao.systemManage;

import java.util.List;
import java.util.Map;

import com.awslabplatform_admin.dao.base.BaseDao;
import com.awslabplatform_admin.entity.Menu;
import com.awslabplatform_admin.entity.PageInfo;

/**
 * 菜单管理DAO
 * @author weix
 *
 */
public interface MenuDao extends BaseDao<Menu, String>{

	/**
	 * 查找用户对应菜单
	 * @param condition
	 * @return
	 */
	List<Menu> listMenuByUserName(Map<String, Object> condition);

	/**
	 * 查询列表数据
	 * @param info
	 * @return
	 */
	List<Menu> listMenuInfo(PageInfo info);

	/**
	 * 查询列表总数
	 * @param info
	 * @return
	 */
	int getMenuInfoTotal(PageInfo info);

	/**
	 *
	 * 删除角色
	 * @param param
	 */
	void deleteMenu(Map<String,Object> param);

	/**
	 * 获取菜单数量
	 * @param condition
	 * @return
	 */
	int getMenuNum(Map<String,Object> condition);

	
	/**
	 * 查询角色权限
	 * @param roleId 角色ID
	 */
	List<Integer> listRoleMenu( String roleId );
}
