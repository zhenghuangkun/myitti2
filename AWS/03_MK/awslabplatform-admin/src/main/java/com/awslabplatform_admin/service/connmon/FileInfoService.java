package com.awslabplatform_admin.service.connmon;

import java.util.List;
import com.awslabplatform_admin.entity.FileInfo;
import com.awslabplatform_admin.service.base.BaseService;

/**
* 文件信息管理Service层
*
* @author  weix
* @version 2018-3-23
*/
public interface FileInfoService extends BaseService<FileInfo, String> {
	/**
	 * 插入文件信息
	 * @param fileInfoList
	 * @return
	 */
	Boolean insertFileInfo(List<FileInfo> fileInfoList);

	/**
	 * 插入文件信息
	 * @param fileInfo
	 * @return
	 */
	Boolean insertFileInfo(FileInfo fileInfo);

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
	Boolean updateFileInfo(List<FileInfo> fileInfoList);

	/**
	 * 根据ID查询FileInfo
	 */
	FileInfo getFileInfoById(String fileId);

	/**
	 * 删除多个（根据关联ID删除）
	 * @param correlationArray
	 * @return
	 */
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
	boolean checkKeyPairExist(String keyName);
}
