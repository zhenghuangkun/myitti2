package com.awslabplatform_admin.service.systemManage;

import java.util.List;
import java.util.Map;

import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Role;
import com.awslabplatform_admin.service.base.BaseService;

/**
* 角色管理Service层
*
* @author  weix
* @version 2017/9/26
*/
public interface RoleService extends BaseService<Role, String> {
    /**
     *
     * 查询分页信息
     * @param PageInfo
     * @return
     **/
    void getRolePageInfo(PageInfo pageInfo);

    /**
     *
     * 删除角色
     * @param param
     * @return
     **/
    boolean deleteRole(Map<String,Object> param);

    /**
     *
     * 查询角色是否存在
     * @param condtion
     * @return
     **/
    boolean existsRole(Map<String,Object> condition);

    /**
     * 更新角色权限
     * @param roleId
     * @param menuList
     * @return
     */
    boolean updateRolePermission(String roleId, String[] menuList);

    /**
     * 根据用户ID获取菜单
     * @param condition
     * @return
     */
	List<Role> listRoleByUserId(Map<String,Object> condition);
	
	/**
	 * 查询角色，用来显示对应用户的用户类型
	 * @param role
	 */
	List<Role> findByRoleData(Role role);
	
	/**
	 * 插入角色与用户的关联表
	 * @param condition
	 */
	void insertRoleUser(Map<String,Object> condition);
	
	/**
	 * 修改角色与用户的关联表
	 * @param condition
	 */
	void updateRoleUser(Map<String,Object> condition);

}
