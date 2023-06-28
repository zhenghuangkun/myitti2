package com.awslabplatform_admin.dao.common;

import java.util.List;

import com.awslabplatform_admin.dao.base.BaseDao;
import com.awslabplatform_admin.entity.FileInfo;

/**
 * 文件信息管理DAO接口
 * @author weixin
 * @version 2018-3-23
 */
public interface FileInfoDao extends BaseDao<FileInfo, String> {
	/**
	 * 更新文件信息
	 * @author weixin
	 * @version 2018年3月28日
	 * @param fileInfo
	 * @return
	 */
	Boolean updateFileInfo(FileInfo fileInfo);

	/**
	 * 更新文件信息
	 * @author weixin
	 * @version 2018年3月28日
	 * @param fileInfoList
	 * @return
	 */
	Boolean updateListFileInfo(List<FileInfo> fileInfoList);

	/**
	 * 获取单个文件
	 **/
	FileInfo getFileInfoById(String fileId);

	/**
	 * 删除多个（根据关联ID删除）
	 **/
	int deleteMultiByCorrelationId(String[] correlationArray);

	/**
	 * 删除单个（根据FileId删除）
	 */
	void deleteMultiByFileId(String fileId);

	/**
	 * 获取秘钥
	 */
	List<FileInfo> getKeyByFileInfoType();
	
	/**
	 * 判断密钥是否存在
	 * @param keyName
	 * @return
	 */
	int selectKeyPairByName(String keyName);
}
