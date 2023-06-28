package com.awslabplatform.service.fileInfoService.Impl;

import com.awslabplatform.dao.fileInfoDao.FileInfoDao;
import com.awslabplatform.entity.FileInfo;
import com.awslabplatform.service.base.impl.BaseServiceImpl;
import com.awslabplatform.service.fileInfoService.FileInfoService;
import org.springframework.stereotype.Service;

@Service("fileInfoService")
public class FileInfoServiceImpl extends BaseServiceImpl<FileInfoDao, FileInfo, String> implements FileInfoService {

	/**
	 * 保存文件
	 * @param fileInfo
	 */
	public void insertFileInfo(FileInfo fileInfo){
		baseDao.insertFileInfo(fileInfo);
	}
}
