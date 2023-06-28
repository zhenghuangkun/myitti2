package com.awslabplatform.dao.fileInfoDao;

import com.awslabplatform.dao.base.BaseDao;
import com.awslabplatform.entity.FileInfo;

public interface FileInfoDao extends BaseDao<FileInfo, String> {

	/**
	 * 保存文件
	 * @param fileInfo
	 */
	public void insertFileInfo(FileInfo fileInfo);
}
