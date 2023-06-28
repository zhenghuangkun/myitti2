package com.awslabplatform_admin.service.federation;

import com.awslabplatform_admin.entity.FederationInfo;

/**
 * 联盟登录接口
 * @author yuzhh
 *
 */
public interface Federation {
	
	/**
	 * 获取联盟登录到AWS控制台URL
	 * @param federationInfo
	 * @return
	 * @throws Exception 
	 */
	public String getAwsFederationeUrl( FederationInfo federationInfo) throws Exception;

}
