package com.awslabplatform.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.awslabplatform.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.awslabplatform.entity.FileInfo;
import com.awslabplatform.util.FastDFSUtils;

/**
 *
 * @author lijf
 * @date 2018年4月3日 上午10:55:14
 */
@Controller
public class UploadFileController {
	@Autowired
    private HttpServletRequest request;
    @RequestMapping(value ="/upload",method=RequestMethod.POST)
    @ResponseBody
    public Object UpLoadImg(@RequestParam(value="myFileName")MultipartFile mf,HttpServletRequest request) {

    //获取源文件
    FileInfo uploadPic=null;
    System.out.println(mf.getOriginalFilename()+mf.getSize());
    try {
    	Student student = (Student) request.getSession().getAttribute("currentUser");
    	String studentId="secretUser";
    	studentId=student.getId();
		uploadPic = FastDFSUtils.uploadPic(mf.getBytes(), mf.getOriginalFilename(), mf.getSize(), studentId);
	} catch (Exception e) {

	}
    Map<String, String> map = new HashMap<String, String>();
    map.put("data",uploadPic.getFileUrl());//这里应该是项目路径
    return map;//将图片地址返回
    }
}
