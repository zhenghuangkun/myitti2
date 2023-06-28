package com.awslabplatform_admin.service.amazonaws;

import com.awslabplatform_admin.entity.FileInfo;



public interface KeyPairService {

	/**
	 * 创建密钥
	 * @author zhenghuangkun
	 * @version 2018年4月10日
	 * @param keyName
	 * @return
	 */
	public FileInfo createKeyPair(String keyName);

	/**
	 *
	 * @author weixin
	 * @version 2018年4月16日
	 * @return
	 */
	public Boolean deleteKeyPair(String keyName);

	/**
	 *  课程创建密钥
	 * @author CH
	 * @version 2019年3月18日
	 * @return
	 */

	public FileInfo createExperimentKeyPair(String keyName,String experimentId);

	/**
	 *  课程删除密钥
	 * @author CH
	 * @version 2019年3月18日
	 * @return
	 */

	public Boolean deleteExperimentKeyPair(String keyName,String experimentId);
}
