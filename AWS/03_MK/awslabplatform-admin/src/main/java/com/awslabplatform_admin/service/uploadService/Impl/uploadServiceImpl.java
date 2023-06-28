package com.awslabplatform_admin.service.uploadService.Impl;

import com.awslabplatform_admin.entity.FileInfo;
import org.springframework.stereotype.Service;
import java.io.IOException;


@Service("uploadService")
public class uploadServiceImpl implements com.awslabplatform_admin.service.uploadService.uploadService{

	public FileInfo uploadPic(byte[] pic, String name, long size) throws IOException {

		return com.awslabplatform_admin.util.FastDFSUtils.uploadPic(pic, name, size);
	}
	
	
	/**
	 * 
	 * @author zhenghk
	 * @version 2018年4月10日
	 * @param fileName
	 * @param suffix
	 * @param content
	 * @return
	 */
	public FileInfo writeRemoteFile(String fileName, String suffix, String content){
		
		/*判断写入的内容是否为空*/
		if ("".endsWith(content) || content == null) {
			return null;
		}
		
		/*判断文件名的内容是否为空*/
		if ("".endsWith(fileName) || fileName == null) {
			return null;
		}
		
		/*判断后缀的内容是否为空*/
		if ("".endsWith(suffix) || suffix == null) {
			return null;
		}
		
		FileInfo fileinfo = null;
		
		try {
			/*File tempFile = File.createTempFile(fileName, "."+suffix);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile), "UTF-8"));
		
			//写入文件 
			bw.write(content);
			bw.flush();
			bw.close();
			*/
			long size = content.getBytes().length;
			fileinfo = com.awslabplatform_admin.util.FastDFSUtils.uploadPic(content.getBytes(), fileName+"."+suffix, size);
			
			// 程序退出时直接删除临时文件
			/*tempFile.delete();*/
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}
		
		return fileinfo;
	}
}
