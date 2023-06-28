package com.awslabplatform.service.fileInfoService;

import com.awslabplatform.entity.FileInfo;
import com.awslabplatform.service.base.BaseService;

public interface FileInfoService extends BaseService<FileInfo, String> {

	/**
	 * 保存文件
	 * @param fileInfo
	 */
	public void insertFileInfo(FileInfo fileInfo);
}
