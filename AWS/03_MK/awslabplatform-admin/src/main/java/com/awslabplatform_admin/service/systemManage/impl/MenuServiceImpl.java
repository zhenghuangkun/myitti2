package com.awslabplatform_admin.service.systemManage.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.awslabplatform_admin.dao.systemManage.MenuDao;
import com.awslabplatform_admin.entity.Menu;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.service.base.impl.BaseServiceImpl;
import com.awslabplatform_admin.service.systemManage.MenuService;

/**
 * 菜单管理service 接口实现类
 * @author WEIX
 *
 */
@Service("MenuService")
public class MenuServiceImpl extends BaseServiceImpl<MenuDao, Menu, String> implements MenuService {

	/**
	 * 通过用户名获取菜单列表
	 * @param userName 用户名
	 * @return List<Menu> 菜单集合
	 */
	@Override
	public List<Menu> listMenuByUserId(Map<String, Object> condition) {
		return baseDao.listMenuByUserName(condition);
	}

	/**
     * 查询分页信息
     * @param pageInfo
     * @return
     **/
	@Override
	public void getMenuPageInfo(PageInfo pageInfo) {
		/*查询分页数据*/
		pageInfo.setData(baseDao.listMenuInfo(pageInfo));
		/*查询总数*/
		int total = baseDao.getMenuInfoTotal(pageInfo);
		pageInfo.setRecordsTotal(total);
		pageInfo.setRecordsFiltered(total);//过滤记录数，dataTable必要参数
	}

	/**
     * 删除菜单
     * @param param
     * @return
     **/
	@Override
	public void deleteMenu(Map<String,Object> param) {
		baseDao.deleteMenu(param);
	}

	/**
	 * 根据角色ID获取角色菜单权限
	 */
	@Override
	public List<Integer> listRoleMenu(String roleId) {
		return baseDao.listRoleMenu(roleId);
	}

}