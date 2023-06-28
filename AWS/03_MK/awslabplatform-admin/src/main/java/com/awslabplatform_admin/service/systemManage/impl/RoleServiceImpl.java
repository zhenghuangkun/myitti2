package com.awslabplatform_admin.service.systemManage.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awslabplatform_admin.dao.systemManage.RoleDao;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Role;
import com.awslabplatform_admin.realm.ShiroDbRealm;
import com.awslabplatform_admin.service.base.impl.BaseServiceImpl;
import com.awslabplatform_admin.service.systemManage.RoleService;
import com.awslabplatform_admin.util.TimeUtils;

/**
 * 角色管理service 接口实现类
 * @author weix
 * @version 2018-2-28
 */
@Service("RoleService")
public class RoleServiceImpl extends BaseServiceImpl<RoleDao, Role, String> implements RoleService {

	@Autowired
	ShiroDbRealm realm;

	/**
     * 查询分页信息
     * @param pageInfo
     * @return
     **/
	@Override
	public void getRolePageInfo(PageInfo pageInfo) {
		/*查询分页数据*/
		pageInfo.setData(baseDao.listRoleInfo(pageInfo));
		/*查询总数*/
		int total = baseDao.getRoleInfoTotal(pageInfo);
		pageInfo.setRecordsTotal(total);
		pageInfo.setRecordsFiltered(total);//过滤记录数，dataTable必要参数
	}

	/**
     * 删除角色
     * @param param
     * @return
     **/
	@Override
	public boolean deleteRole(Map<String,Object> param) {
		long size = baseDao.countUserSize((String) param.get("roleId"));
		if(size > 0){
			return false;
		}else{
			baseDao.deleteRole(param);
			return true;
		}
	}

	/**
     *
     * 查询角色是否存在
     * @param condition
     * @return
     **/
	@Override
	public boolean existsRole(Map<String, Object> condition) {

		String roleId = (String) condition.get("roleId");
		String roleName = (String) condition.get("roleName");

		/*根据roleId判断是否是修改操作*/
		if(roleId != null){
			Role oldRole = baseDao.selectByCondition(condition);
			if(oldRole != null){
				/*判断用户名是否相同，如果相同则不需要判断角色是否存在*/
				if(roleName.equals(oldRole.getRoleName())){
					return false;
				}
			}

		}

		/*判断角色是否存在*/
		if(baseDao.getRoleNum(condition) > 0 ){
			return true;
		}

		return false;
	}

	/**
	 * 更新角色权限
	 */
	@Override
	public boolean updateRolePermission(String roleId, String[] menuList) {
		if(roleId == null){
			return false;
		}
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("roleId", roleId);//角色ID

		/*删除当前角色权限*/
		baseDao.deleteRolePermisson(roleId);

		/*插入当前角色权限*/
		if(menuList != null){
			param.put("menuList", menuList);//勾选菜单
			baseDao.insertRolePermisson(param);
		}

		/*更新授权时间*/
		param.put("authorityTime", TimeUtils.currentTime());//角色ID
		baseDao.updateAuthorityTime(param);

		return true;
	}
	/**
	 * 查询用户对应角色
	 */
	@Override
	public List<Role> listRoleByUserId(Map<String, Object> condition) {
		return baseDao.listRoleByUserId(condition);
	}

	/**
	 * 查询角色，用来显示对应用户的用户类型
	 */
	@Override
	public List<Role> findByRoleData(Role role) {

		return baseDao.findByRoleData(role);
	}

	/**
	 * 插入角色与用户的关联表
	 * @param condition
	 */
	@Override
	public void insertRoleUser(Map<String, Object> condition) {
		
		baseDao.insertRoleUser(condition);
	}

	/**
	 * 修改角色与用户的关联表
	 * @param condition
	 */
	@Override
	public void updateRoleUser(Map<String, Object> condition) {

		baseDao.updateRoleUser(condition);
	}

}