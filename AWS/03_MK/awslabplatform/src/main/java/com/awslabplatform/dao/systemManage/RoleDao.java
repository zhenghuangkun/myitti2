package com.awslabplatform.dao.systemManage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.awslabplatform.dao.base.BaseDao;
import com.awslabplatform.entity.PageInfo;
import com.awslabplatform.entity.Role;

/**
 * 角色管理DAO接口
 * @author weix
 * @version 2017/10/8
 */
public interface RoleDao extends BaseDao<Role, String> {

	/*查询列表数据*/
	List<Role> listRoleInfo(PageInfo info);

	/*查询列表总数*/
	int getRoleInfoTotal(PageInfo info);

	/*删除角色*/
	void deleteRole(Map<String,Object> param);

	/*获取角色数量*/
	int getRoleNum(Map<String,Object> condition);

	/**
	 * 删除角色权限
	 * @param roleId 角色ID
	 */
	void deleteRolePermisson( String roleId );

	/**
	 * 插入角色权限
	 * @param param
	 */
	void insertRolePermisson( Map<String,Object> param );

	/**
	 * 更新授权时间
	 * @param roleId 角色ID
	 * @param authorityTime 授权时间
	 */
	void updateAuthorityTime( Map<String,Object> param );

	/**
	 * 查询角色拥有的用户个数
	 * @param roleId
	 * @return
	 */
	long countUserSize(@Param("roleId") String roleId);
	
    /**
     * 查询用户对应角色
     * @param condition
     * @return
     */
	List<Role> listRoleByUserId(Map<String,Object> condition);

}
