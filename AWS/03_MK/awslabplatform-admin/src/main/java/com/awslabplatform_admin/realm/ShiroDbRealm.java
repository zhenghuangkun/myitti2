package com.awslabplatform_admin.realm;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.awslabplatform_admin.common.Common;
import com.awslabplatform_admin.common.ShiroPermissionConstant;
import com.awslabplatform_admin.entity.Role;
import com.awslabplatform_admin.entity.User;
import com.awslabplatform_admin.service.systemManage.RoleService;
import com.awslabplatform_admin.service.userManage.UserService;

public class ShiroDbRealm extends AuthorizingRealm {

	/**
	 * 用户service
	 */
	@Autowired
	private UserService userService;

	/**
	 * 角色service
	 */
	@Autowired
	private RoleService roleService;

	/**
	 * 日志
	 */
	private static final Logger log = LoggerFactory.getLogger(ShiroDbRealm.class);

	public ShiroDbRealm() {
        super();
    }


	/**
	 * 用户授权
	 *
	 * @param principalCollection
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// 从 principals获取主身份信息
        // 将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型）
		User user = (User) principalCollection.getPrimaryPrincipal();

		if(user == null) {
			log.error("用户不存在");
			return null;
		}


		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("userName", user.getUserId());
		condition.put("state", Common.STATE_ACTIVE);
        List<Role> roles = roleService.listRoleByUserId(condition);
        if (roles.size() > 0 ) {
            for (Role role : roles) {
            	// 将上边查询到授权信息填充到simpleAuthorizationInfo对象中
            	simpleAuthorizationInfo.addRole(role.getRoleName());
            }
        } else {
        	log.error("该用户未授权角色");
        	return null;
        }

		return simpleAuthorizationInfo;
	}

	/**
	 * 登陆认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken) throws AuthenticationException {
		User user = null;
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;;
		user = userService.getByUserEmail(token.getUsername());
		if (null == user){
			user = userService.getByUserPhone(token.getUsername());
		}
		if (user != null) {
			if (Common.STATE_ACTIVE  == user.getUserState().intValue()) {
				return new SimpleAuthenticationInfo(user, user.getUserPwd(), ByteSource.Util.bytes(ShiroPermissionConstant.PWDSalt), getName());
			} else {
				log.error(token.getUsername() + "不可使用");
				throw new LockedAccountException();
			}
		} else {
			throw new UnknownAccountException("用户不存在");
		}



	}

	@Override
	protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws AuthenticationException {
		super.assertCredentialsMatch(token, info);
	}

    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }
    /**
     *
    * @Title: clearAuthz
    * @Description: TODO 清楚缓存的授权信息
    * @return void    返回类型
     */
    public void clearAuthz(){
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }


}

