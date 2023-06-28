package com.awslabplatform_admin.controller.awsAccountManage;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awslabplatform_admin.common.Common;
import com.awslabplatform_admin.common.Dictionary;
import com.awslabplatform_admin.common.message.IamMessage;
import com.awslabplatform_admin.entity.AwsAccounts;
import com.awslabplatform_admin.entity.AwsIams;
import com.awslabplatform_admin.entity.DictionaryData;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Result;
import com.awslabplatform_admin.service.awsAccountManage.AwsAccountsService;
import com.awslabplatform_admin.service.awsAccountManage.AwsIamsService;
import com.awslabplatform_admin.service.systemManage.DictionaryDataService;
import com.awslabplatform_admin.service.userManage.UserService;
import com.awslabplatform_admin.util.JsonTool;
import com.awslabplatform_admin.util.ResultUtil;
import com.awslabplatform_admin.util.ShiroUtil;
import com.awslabplatform_admin.util.WriteJsonUtil;


/**
* AWSIAM的Controller层
*
* @author   czy
* @version  2018-3-21
*/
@Controller
@RequestMapping("/awaiam")
public class AwsIamController {
	/**AwsIams的service*/
    @Autowired
    private  AwsIamsService awsIamsService;
    
	/**AWSAccount的service*/
    @Autowired
    private  AwsAccountsService awsAccountsService;
    
    /**
	 * 数据字典service
	 */
	@Autowired
	private DictionaryDataService sictionaryDataService;
	
	@Autowired
	private UserService userService;

	/**
     * 查询AWSAccount的数据，将数据传回到前台进行显示
     * @param response
     * @param request
     */
	@RequestMapping("/getAllAccountList")
	public void getAllAccountList(AwsAccounts awsAccounts,HttpServletResponse response){
		List<AwsAccounts> list=awsAccountsService.getAllAccountList(awsAccounts);
		/*数据返回*/
		WriteJsonUtil.writeJsonObject(list, response);
	}
	
	/**
	 * 获取AWS IAM的数据，将数据显示在表格上
	 * @param dicId
	 * @param response
	 * @throws IOException 
	 */
    @RequestMapping("/selectAwsIamData")
    public void selectAwsIamData(String inputUnitName,int inputState,int inputIAMStatus,int start, int length, int draw,HttpServletResponse response) throws IOException{
    	Map<String,Object> condition  = new HashMap<String, Object>();
		condition.put("iAMName", inputUnitName);//IAM 用户名
		condition.put("isUsed", inputState);//是否被使用(状态)
		condition.put("iAMStatus", inputIAMStatus);//IAM 状态（0 可用 1 不可用）
		condition.put("userIam", ShiroUtil.getCurrentUser().getIAM());//获取当前登陆用户的IAM
		condition.put("userRoleType", ShiroUtil.getCurrentUser().getRoleType());//获取当前登陆用户的用户角色类型
		PageInfo pageInfo = new PageInfo(start, length, draw);
		pageInfo.setCondition(condition);
		awsIamsService.getAwsIamPageInfo(pageInfo);
		/*数据返回*/
		JsonTool.toJson(pageInfo,response);
    }
	
	/**
	 * IAM类型
	 */
	@RequestMapping("/pageDesginIamlate")
	public void pageDesginIamlate(Model model,HttpServletResponse response)throws IOException {
		List<DictionaryData>  iamTypeList=null;
		if("0".equals(ShiroUtil.getCurrentUser().getIAM()) || "".equals(ShiroUtil.getCurrentUser().getIAM())|| ShiroUtil.getCurrentUser().getRoleType()==1){
			iamTypeList=  sictionaryDataService.getSubItemListData(Dictionary.DICTIONARY_IAMTYPE.getDicCode());//获取数据字典数据
		}else{
			HashMap<String, Object> map=new HashMap<String, Object>();
			map.put("dicCode", Dictionary.DICTIONARY_IAMTYPE.getDicCode());
			iamTypeList=sictionaryDataService.getIamTypeData(map);
		}
/*		获取IAM类型
		iamTypeList=  sictionaryDataService.getSubItemListData(Dictionary.DICTIONARY_IAMTYPE.getDicCode());//获取数据字典数据
		获取IAM类型
		model.addAttribute("iAMType", iamTypeList);
		获取UUID
		model.addAttribute("iamId", UUIDUtils.getUUID());*/
		WriteJsonUtil.writeJsonObject(iamTypeList, response);
	}

	/** 注销原因：用不到IAM管理 CH
	 * 根据条件获取AWS IAM的数据，将数据显示在编辑的页面上
	 * @param id
	 */
//	@RequestMapping("/getAwsIamData")
//	public void getAwsAccountData(String id,HttpServletResponse response){
//		try {
//			AwsIams awsIams =new AwsIams();
//			awsIams.setId(id);
//			JsonTool.toJson(awsIamsService.selectIdAwsIamData(awsIams),response);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	/**
     * 编辑AWS IAM账户信息数据
     * @param response
     * @param request
     */
	@RequestMapping(value="/editAwsIam", method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result editAwsIam(@RequestBody AwsIams awsIamsw){
		Map<String,Object> condition  = new HashMap<String, Object>();
		PageInfo pageInfo = new PageInfo();
		if(awsIamsw.getCopyIamName().equals(awsIamsw.getiAMName())){
			awsIamsService.updateByPrimaryKeySelective(awsIamsw);
			return ResultUtil.getResult(true, IamMessage.EDIT_IAM_SUCCESS);
		}else{
			condition.put("iAMName", awsIamsw.getiAMName());
			pageInfo.setCondition(condition);
			if(awsIamsService.countEditAwsIamData(pageInfo)>0){
				return ResultUtil.getResult(false, IamMessage.EDIT_IAMIAMName_ERROR);
			}else{
				awsIamsService.updateByPrimaryKeySelective(awsIamsw);
				return ResultUtil.getResult(true, IamMessage.EDIT_IAM_SUCCESS);
			}
		}
	}
	
	/**
     * 提交添加AWS IAM账户数据
     * @param response
     * @param request
     */
	@RequestMapping(value="/addAwsIam", method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result addAwsIamData(@RequestBody AwsIams awsIams){
		if(awsIamsService.countAwsIamtData(awsIams)>0){//判断IAMName数据是否存在
			return ResultUtil.getResult(false, IamMessage.EXIST_IAM_DATA);
		}else{	
		    awsIams.setIsUsed(Common.IAM_ISUSER);//添加默认0(未分配)
		    awsIams.setiAMStatus(Common.IAM_STATE_ACTIVE);//添加默认0(可用状态)
			awsIams.setCreateTime(new Date());
			awsIamsService.insert(awsIams);		
			return ResultUtil.getResult(true, IamMessage.ADD_IAM_SUCCES);
		}
	}
    
	
    
    /**
     * 假删除AWS IAM列表上的数据，将数据传到数据库进行修改IAMStatus对应的值
     * @param response
     * @param request
     */
	@RequestMapping("/deleteAwsIamData")
	public void deleteAwsIamData(String id,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String, Object>();
		if(awsIamsService.deleteAwsIamData(id)){//假删除成功
			map.put("success", true);
		}else {
			map.put("success", false);
		}
		/*数据返回*/
		WriteJsonUtil.writeJsonObject(map, response);
	}
}
