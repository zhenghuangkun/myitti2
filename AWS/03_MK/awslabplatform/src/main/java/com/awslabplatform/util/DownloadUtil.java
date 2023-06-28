package com.awslabplatform.util;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * 用于处理下载文件时，火狐浏览器文件名乱码问题
 */
public class DownloadUtil {

	public static void parseHeader (String fileName, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String USER_AGENT = request.getHeader("USER-AGENT");
		UserAgent userAgent = UserAgent.parseUserAgentString(USER_AGENT);
		Browser browser = userAgent.getBrowser();
		String name;
		if (browser == Browser.FIREFOX) {
			name =  new String(fileName.getBytes("UTF-8"), "iso-8859-1");
		} else {
			name = URLEncoder.encode(fileName, "UTF-8");
		}

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + name);
	}
	public static void download(String fileUrl,HttpServletResponse response){
		URL url;
		InputStream is = null;
		URLConnection connection;
		BufferedOutputStream bos = null;  
		try {
			/*远程读取文件*/
			url = new URL(fileUrl);
			connection = url.openConnection();
			is = connection.getInputStream();
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[1024];    
	        int bytesRead;    
	        while (-1 != (bytesRead = is.read(buff, 0, buff.length))) {    
	            bos.write(buff, 0, bytesRead);    
	        }
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
    		try {
				if(is!=null) is.close();
				if(bos!=null) bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
	    }  
	}
}
