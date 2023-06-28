package com.awslabplatform_admin.controller.mechanismManage;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.awslabplatform_admin.common.Common;
import com.awslabplatform_admin.entity.Mechanism;
import com.awslabplatform_admin.service.mechanismManage.MechanismService;
import com.awslabplatform_admin.service.userManage.UserService;
import com.awslabplatform_admin.util.ShiroUtil;
import com.awslabplatform_admin.util.UUIDUtils;
import com.awslabplatform_admin.util.WriteJsonUtil;

import static com.awslabplatform_admin.common.Common.ROLE_TYPE_PLATFORM;


/**
* 机构的Controller层
*
* @author   czy
* @version  2018-3-19
*/
@Controller
@RequestMapping("/mechanism")
public class MechanismController {

	/**机构管理的service*/
    @Autowired
    private  MechanismService  mechanismService;
    
    @Autowired
	private UserService userService;

    /**
     * 查询数据字典父项的数据，将数据传回到前台进行显示
     * @param response
     * @param request
     */
	@RequestMapping("/getMechanismList")
	public void selectDictionaryData(HttpServletResponse response){
		String department=null;
		//String
		/*CH version 2.0*/
		if(!ShiroUtil.getCurrentUser().getRoleType().equals(ROLE_TYPE_PLATFORM)){
			department = ShiroUtil.getCurrentUser().getDepartmentId();
		}
		List<Mechanism> mechanismList=mechanismService.getMechanismList(department);

		/*数据返回*/
		WriteJsonUtil.writeJsonObject(mechanismList, response);
	}

	/**
     * 添加机构名称数据，将数据传到数据库存储起来
     * @param response
     * @param request
     */
	@RequestMapping("/addMechanismData")
	public void addMechanismData(Mechanism mechanism,HttpServletResponse response){
		mechanism.setCreateTime(new Date());
		Map<String,Object> map = new HashMap<String, Object>();
		if(mechanismService.countMechanismData(mechanism)>0){
			map.put("success", false);
		}else {
			mechanism.setMechanismId(UUIDUtils.getUUID());
			mechanismService.insert(mechanism);
			map.put("success", true);
		}

		/*数据返回*/
		WriteJsonUtil.writeJsonObject(map, response);
	}

	/**
     * 删除机构名称数据，将数据传到数据库进行删除对应的数据
     * @param response
     * @param request
     */
	@RequestMapping("/deleteMechanismData")
	public void deleteMechanismData(String mechanismId,String deleteId,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("mechanismId", deleteId);
		map.put("userState", Common.STATE_ACTIVE);//user与Student表 1可用状态
		map.put("accountStause", Common.ACCOUNT_STATE_ACTIVE);//Auccount表 0是可用状态
		String[] id=mechanismId.split(",");	
		if (userService.countStudentId(map)==0) {//学生表
			if(userService.countUserAccount(map)==0){//用户表  Account表			
				for (int i = 0; i < id.length; i++) {
					mechanismService.deleteMechanismData(id[i]);
				}
				map.put("success", true);
			}else{			
				map.put("success", false);
			}
		}else{		
			map.put("success", false);
		}
			
		/*数据返回*/
		WriteJsonUtil.writeJsonObject(map, response);
	}

	/**
     * 根据ID获取机构数据，将数据传到前台显示出来
     * @param response
     * @param request
     */
	@RequestMapping("/getMechanismData")
	public void getMechanismData(String mechanismId,HttpServletResponse response){
		Mechanism mechanism=mechanismService.getMechanismData(mechanismId);
		/*数据返回*/
		WriteJsonUtil.writeJsonObject(mechanism, response);
	}

	/**
     * 编辑机构数据，将数据提交到数据库进行修改数据
     * @param divId
     * @param response
     */
	@RequestMapping("/editMechanismData")
	public void editMechanismData(Mechanism mechanism,String copyMechanismName,HttpServletResponse response,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		if(mechanism.getMechanismName().equals(copyMechanismName)){
			mechanismService.updateByPrimaryKeySelective(mechanism);
			map.put("success", true);
		}else{
			if(mechanismService.countMechanismData(mechanism)>0){
				map.put("success", false);
			}else{
				mechanismService.updateByPrimaryKeySelective(mechanism);
				map.put("success", true);
			}
		}
		/*数据返回*/
		WriteJsonUtil.writeJsonObject(map, response);
	}



}
