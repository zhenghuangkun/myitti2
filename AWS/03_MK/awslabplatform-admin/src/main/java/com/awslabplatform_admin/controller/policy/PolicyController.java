package com.awslabplatform_admin.controller.policy;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.awslabplatform_admin.entity.FileInfo;
import com.awslabplatform_admin.entity.Policy;
import com.awslabplatform_admin.service.policy.PolicyService;
import com.awslabplatform_admin.util.FastDFSUtils;
/**
 * 
 * @author lijf
 * @date 2018年5月24日 下午3:49:31
 */
@Controller
public class PolicyController {

	@Autowired
	private PolicyService policyService;


	/**
	 * 策略列表
	 */
	@RequestMapping(value = "/listPolicy")
	public String listPolicy(Model model) {
		Map<String, Object> condition=new HashMap<String, Object>();
		condition.put("status", 1);
		List<Policy> policyList = policyService.listBycondition(condition);
		model.addAttribute("policyList", policyList);
		return "/policy/listPolicy";
	}
	/**
	 * 添加策略
	 */
	@RequestMapping(value = "/addPolicy")
	public String addPolicy(MultipartFile myfile,String name,String description) {
		try {
			Policy policy = new Policy();
			FileInfo fileInfo = FastDFSUtils.uploadPic(myfile.getBytes(), myfile.getOriginalFilename(), myfile.getSize());
			policy.setFileUrl(fileInfo.getFileUrl());
			policy.setName(name);
			policy.setDescription(description);
			policyService.addPolicy(policy);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/listPolicy";
	}
	
	/**
	 * 删除策略
	 */
	@RequestMapping(value = "/deletePolicy")
	public String deletePolicy(String id) {
		Policy policy = new Policy();
		policy.setId(id);
		policy.setStatus(0);
		policyService.updatePolicy(policy);
		return "redirect:/listPolicy";
	}
	/**
	 * 修改策略
	 */
	@RequestMapping(value = "/updatePolicy")
	public String updatePolicy(String id,String name,String description,MultipartFile myfile) {
		try {
			Policy policy = new Policy();
			if(myfile!=null&&!"".equals(myfile)){
				FileInfo fileInfo = FastDFSUtils.uploadPic(myfile.getBytes(), myfile.getOriginalFilename(), myfile.getSize());
				policy.setFileUrl(fileInfo.getFileUrl());
			}
			policy.setId(id);
			policy.setName(name);
			policy.setDescription(description);
			policyService.updatePolicy(policy);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/listPolicy";
	}
}
