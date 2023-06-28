package com.awslabplatform_admin.service.connmon.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.awslabplatform_admin.common.message.SysMgMessage;
import com.awslabplatform_admin.dao.common.FileInfoDao;
import com.awslabplatform_admin.entity.FileInfo;
import com.awslabplatform_admin.service.base.impl.BaseServiceImpl;
import com.awslabplatform_admin.service.connmon.FileInfoService;

/**
 * 文件信息管理service 接口实现类
 * @author weix
 * @version 2018-3-23
 */
@Service("FileInfoService")
public class FileInfoServiceImpl extends BaseServiceImpl<FileInfoDao, FileInfo, String> implements FileInfoService {
	private static Logger log = LoggerFactory.getLogger(FileInfoServiceImpl.class);

	/**
	 * 插入文件信息
	 * @param fileInfoList
	 * @return
	 */
	@Override
	public Boolean insertFileInfo(List<FileInfo> fileInfoList) {
		if (fileInfoList == null || fileInfoList.size() == 0) {
			log.error(SysMgMessage.FILEINFO_INSERT_MESSAGE.getCode() + " : " + SysMgMessage.FILEINFO_INSERT_MESSAGE.getContent());
			return false;
		}
		baseDao.insertList(fileInfoList);
		return true;
	}
	/**
	 * 插入文件信息
	 * @param fileInfo
	 * @return
	 */
	@Override
	public Boolean insertFileInfo(FileInfo fileInfo) {
		if (fileInfo == null) {
			log.error(SysMgMessage.FILEINFO_INSERT_MESSAGE.getCode() + " : " + SysMgMessage.FILEINFO_INSERT_MESSAGE.getContent());
			return false;
		}
		baseDao.insert(fileInfo);
		return true;
	}

	/**
	 * 更新文件信息
	 */
	@Override
	public Boolean updateFileInfo(FileInfo fileInfo) {
		if (fileInfo == null) {
			log.error(SysMgMessage.FILEINFO_INSERT_MESSAGE.getCode() + " : " + SysMgMessage.FILEINFO_INSERT_MESSAGE.getContent());
			return false;
		}
		baseDao.updateFileInfo(fileInfo);
		return true;
	}

	/**
	 * 更新文件信息
	 */
	@Override
	public Boolean updateFileInfo(List<FileInfo> fileInfoList) {
		if (fileInfoList == null || fileInfoList.size() == 0) {
			log.error(SysMgMessage.FILEINFO_INSERT_MESSAGE.getCode() + " : " + SysMgMessage.FILEINFO_INSERT_MESSAGE.getContent());
			return false;
		}
		baseDao.updateListFileInfo(fileInfoList);
		return true;
	}

	/**
	 * 根据ID查询FileInfo
	 */
	@Override
	public FileInfo getFileInfoById(String fileId){
		return baseDao.getFileInfoById(fileId);
	}

	/**
	 * 删除多个（根据关联ID删除）
	 * @param correlationArray
	 * @return
	 */
	public int deleteMultiByCorrelationId(String[] correlationArray){
		return baseDao.deleteMultiByCorrelationId(correlationArray);
	}

	/**
	 * 删除单个（根据FileId删除）
	 */
	@Override
	public void deleteMultiByFileId(String fileId){
		baseDao.deleteMultiByFileId(fileId);
	}

	/**
	 * 获取秘钥
	 */
	@Override
	public List<FileInfo> getKeyByFileInfoType(){
		return baseDao.getKeyByFileInfoType();
	}
	
	/**
	 * 判断密钥是否存在
	 * @param keyName
	 * @return
	 */
	public boolean checkKeyPairExist(String keyName){
		int ret = baseDao.selectKeyPairByName(keyName);
		
		if(ret == 0){
			return false;
		}
		
		return true;
	}
}
