package com.awslabplatform_admin.service.uploadService;

import com.awslabplatform_admin.entity.FileInfo;

import java.io.IOException;

public interface uploadService {
	public FileInfo uploadPic(byte[] pic, String name, long size) throws IOException;
	
	/**
	 * 
	 * @author zhenghk
	 * @version 2018年4月10日
	 * @param fileName
	 * @param suffix
	 * @param content
	 * @return
	 */
	public FileInfo writeRemoteFile(String fileName, String suffix, String content);
}