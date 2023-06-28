package com.awslabplatform_admin.service.systemManage;

import java.util.List;
import java.util.Map;

import com.awslabplatform_admin.entity.Menu;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.service.base.BaseService;

/**
* 菜单管理Service层
*
* @author  weix
* @version 2018/3/4
*/
public interface MenuService extends BaseService<Menu, String> {

	/**
	 * 通过用户名获取菜单列表
	 * @param condition 查询条件
	 * @return List<Menu> 菜单集合
	 */
	public List<Menu> listMenuByUserId(Map<String, Object> condition);

    /**
     *
     * 查询分页信息
     * @param PageInfo
     * @return
     **/
    void getMenuPageInfo(PageInfo pageInfo);

    /**
     *
     * 删除菜单
     * @param param
     * @return
     **/
    void deleteMenu(Map<String,Object> param);
    
    /**
     * 根据角色ID获取角色菜单权限
     * @param roleId
     * @return
     */
    List<Integer> listRoleMenu(String roleId);

}
