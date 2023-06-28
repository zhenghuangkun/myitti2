package com.awslabplatform.util;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FilenameUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 上传图片到FastDFS
 * @author Henry
 *
 */
public class FastDFSUtils {
	public static com.awslabplatform.entity.FileInfo uploadPic(byte[] pic, String name, long size, String operator) throws IOException {
		String path = null;
		//ClientGlobal 读取配置文件
		ImageIO.setUseCache(false);
		ClassPathResource resource = new ClassPathResource("fdfs_client.conf");
		//FileInfo对象填充
		com.awslabplatform.entity.FileInfo fileInfo = new com.awslabplatform.entity.FileInfo();
		InputStream is = new ByteArrayInputStream(pic);
		BufferedImage buff = ImageIO.read(is);

		try {
			//读取配置文件
			ClientGlobal.init(resource.getClassLoader().getResource("fdfs_client.conf").getPath());
			//创建TrackerClient客户端
			TrackerClient trackerClient = new TrackerClient();
			TrackerServer trackerServer = trackerClient.getConnection();
			//创建StorageClient客户端
			StorageServer storageServer = null;
			StorageClient1 storageClient1 = new StorageClient1(trackerServer,storageServer);
			//处理文件名准备上传至FastDFS
			String ext = FilenameUtils.getExtension(name);
			//文件描述
			NameValuePair[] meta_list = new NameValuePair[3];
			meta_list[0] = new NameValuePair("fileName", name);
			meta_list[1] = new NameValuePair("fileExt", ext);
			meta_list[2] = new NameValuePair("fileSize", String.valueOf(size));
			//上传图片
			PropertiesConfiguration propsConfig=new PropertiesConfiguration();
			propsConfig.load("fdfs_server_name.properties");
			path = propsConfig.getString("server_host")+storageClient1.upload_file1(pic, ext, meta_list);
			if (null != buff){//是图片
				fileInfo.setImgWidth(buff.getWidth());
				fileInfo.setImgHight(buff.getHeight());
			}
			fileInfo.setFileId(UUIDUtils.getUUID());
			fileInfo.setFileUrl(path);
			fileInfo.setFileSize(size);
			fileInfo.setFileType(ext);
			fileInfo.setOperator(operator);
			fileInfo.setOptTime(TimeUtils.currentTime());
			fileInfo.setState(1);
			fileInfo.setFileName(name);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileInfo;
	}
}
