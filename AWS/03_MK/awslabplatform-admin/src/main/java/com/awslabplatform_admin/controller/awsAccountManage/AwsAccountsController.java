package com.awslabplatform_admin.controller.awsAccountManage;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.awslabplatform_admin.common.Common;
import com.awslabplatform_admin.common.message.AccountMessage;
import com.awslabplatform_admin.entity.AwsAccounts;
import com.awslabplatform_admin.entity.Mechanism;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Result;
import com.awslabplatform_admin.service.awsAccountManage.AwsAccountsService;
import com.awslabplatform_admin.service.mechanismManage.MechanismService;
import com.awslabplatform_admin.util.ExportChannelExcel;
import com.awslabplatform_admin.util.JsonTool;
import com.awslabplatform_admin.util.ResultUtil;
import com.awslabplatform_admin.util.ShiroUtil;
import com.awslabplatform_admin.util.WriteJsonUtil;


/**
* AWSAccount的Controller层
*
* @author   czy
* @version  2019-3-22
*/
@Controller
@RequestMapping("/awsaccount")
public class AwsAccountsController {

	/**AWSAccount的service*/
    @Autowired
    private  AwsAccountsService  awsAccountService;
    /**机构管理的service*/
    @Autowired
    private  MechanismService  mechanismService;
    
    private static final Log log = LogFactory.getLog(AwsAccountsController.class);
	
    /**
     * 显示机构的下拉列表，将下拉列表显示在添加数据的页面上
     * @param response
     * @param request
     */
	@RequestMapping("/findMechanismName")
	public void findMechanismNameData(String mechanismPid,HttpServletResponse response){
		
		Mechanism mechanism=new Mechanism();
		if("0".equals(mechanismPid) && !(ShiroUtil.getCurrentUser().getRoleType().equals(Common.ROLE_TYPE_PLATFORM)) && !(ShiroUtil.getCurrentUser().getRoleType().equals(Common.ROLE_TYPE_ADMIN))){
			mechanism.setMechanismId(ShiroUtil.getCurrentUser().getSchoolId());
		}else{
			mechanism.setMechanismPid(mechanismPid);
		}
		List<Mechanism> mechanismList=mechanismService.getAllMechanismList(mechanism);
		/*数据返回*/
		WriteJsonUtil.writeJsonObject(mechanismList, response);
	}
	
    /**
     * 提交添加AWS Account账户信息数据
     * @param response
     * @param request
     */
	@RequestMapping(value="/addAwsAccount", method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result addAwsAccountData(@RequestBody AwsAccounts awsAccounts){
		awsAccounts.setAccountId(awsAccounts.getAccountId().trim());
		awsAccounts.setAccountName(awsAccounts.getAccountName().trim());
		awsAccounts.setCreateTime(new Date());
		awsAccounts.setAccountStause(Common.ACCOUNT_STATE_ACTIVE);
		if(awsAccountService.countAwsAccountData(awsAccounts)>0){//判断accountId与accountName数据是否存在
			
			return ResultUtil.getResult(false, AccountMessage.EXIST_ACCOUNT_DATA);//
		}else{
			
			awsAccountService.insert(awsAccounts);
			return ResultUtil.getResult(true, AccountMessage.ADD_ACCOUNT_SUCCES);//
		}
	}
	
	/**
	 * 获取AWS Account的数据，将数据显示在表格上
	 * @param dicId
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/selectAwsAccountData")
	public void selectAwsAccountData(String inputUnitName,String inputUserRealName,String inputState,int start, int length, int draw,HttpServletResponse response) throws IOException{
		Map<String,Object> condition  = new HashMap<String, Object>();
		condition.put("accountId", inputUnitName.trim());//付款账户ID
		condition.put("accountName", inputUserRealName.trim());//付款账户名称
		condition.put("org", inputState.trim());//所属机构
		condition.put("accountStause", Common.ACCOUNT_STATE_ACTIVE);//查询没有删除的数据
		PageInfo pageInfo = new PageInfo(start, length, draw);
		pageInfo.setCondition(condition);
		awsAccountService.getAwsAccountPageInfo(pageInfo,mechanismService);
		/*数据返回*/
		JsonTool.toJson(pageInfo,response);
	}
	
	/**
	 * 根据条件获取AWS Account的数据，将数据显示在编辑的页面上
	 * @param id
	 */
	@RequestMapping("/getAwsAccountData")
	public void getAwsAccountData(String id,HttpServletResponse response){
		try {
			AwsAccounts awsAccounts =new AwsAccounts();
		    awsAccounts.setId(id);
			JsonTool.toJson(awsAccountService.selectIdAwsAccountData(awsAccounts),response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取主账号的下拉列表数据
	 * @param school
	 */
	@RequestMapping("/getByHostAwsAccount")
	public void getByHostAwsAccount(String school,HttpServletResponse response){
		AwsAccounts awsAccounts =new AwsAccounts();
		awsAccounts.setOrg(school);
		awsAccounts.setDepartmentId("");
		awsAccounts.setIsPayingAccount(Common.ACCOUNT_ISPAYING);
		List<AwsAccounts> list= awsAccountService.findByOrgAwsAccountData(awsAccounts);
		WriteJsonUtil.writeJsonObject(list, response);
	}
	
	/**
     * 编辑AWS Account账户信息数据
     * @param response
     * @param request
     */
	@RequestMapping(value="/editAwsAccount", method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result editAwsAccount(@RequestBody AwsAccounts awsAccounts){
		Map<String,Object> condition  = new HashMap<String, Object>();
		PageInfo pageInfo = new PageInfo();
		if(awsAccounts.getCopyAccountId().equals(awsAccounts.getAccountId()) && awsAccounts.getCopyAccountName().equals(awsAccounts.getAccountName())){
			awsAccountService.updateByPrimaryKeySelective(awsAccounts);
			return ResultUtil.getResult(true, AccountMessage.EDIT_ACCOUNT_SUCCESS);//
		}else if (!awsAccounts.getCopyAccountId().equals(awsAccounts.getAccountId()) && awsAccounts.getCopyAccountName().equals(awsAccounts.getAccountName())) {
			condition.put("accountId", awsAccounts.getAccountId());
			condition.put("accountName", "");
			condition.put("accountStause", Common.ACCOUNT_STATE_ACTIVE);//查询没有删除的数据
			pageInfo.setCondition(condition);
			if(awsAccountService.countEditAwsAccountData(pageInfo)>0){
				return ResultUtil.getResult(false, AccountMessage.EDIT_ACCOUNTID_ERROR);//
			}else{
				awsAccountService.updateByPrimaryKeySelective(awsAccounts);
				return ResultUtil.getResult(true, AccountMessage.EDIT_ACCOUNT_SUCCESS);//
			}
		}else if(awsAccounts.getCopyAccountId().equals(awsAccounts.getAccountId()) && !awsAccounts.getCopyAccountName().equals(awsAccounts.getAccountName())){
			condition.put("accountId", "");
			condition.put("accountName", awsAccounts.getAccountName());
			condition.put("accountStause", Common.ACCOUNT_STATE_ACTIVE);//查询没有删除的数据
			pageInfo.setCondition(condition);
			if(awsAccountService.countEditAwsAccountData(pageInfo)>0){
				return ResultUtil.getResult(false, AccountMessage.EDIT_ACCOUNTNAME_ERROR);//
			}else{
				awsAccountService.updateByPrimaryKeySelective(awsAccounts);
				return ResultUtil.getResult(true, AccountMessage.EDIT_ACCOUNT_SUCCESS);//
			}
		}else{
			awsAccountService.updateByPrimaryKeySelective(awsAccounts);
			return ResultUtil.getResult(true, AccountMessage.EDIT_ACCOUNT_SUCCESS);//
		}
	}

	/**
     * 删除AWS Account列表上的数据，将数据传到数据库进行删除对应的数据
     * @param id
     * @param response
     */
	@RequestMapping("/deleteAwsAccountData")
	public void deleteAwsAccountData(String id,HttpServletResponse response,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		AwsAccounts awsAccounts=new AwsAccounts();
		awsAccounts.setAccountStause(Common.ACCOUNT_STATE_DELETE);
		awsAccounts.setId(id);
		if(awsAccountService.updateByPrimaryKeySelective(awsAccounts)){
			map.put("success", true);
		}else{
			map.put("success", false);
		}
		/*数据返回*/
		WriteJsonUtil.writeJsonObject(map, response);
	}
	
	/**
     * 激活与失效功能
     * @param id
     * @param response
     */
	@RequestMapping("/activateOrFailure")
	public void activateOrFailure(String id,Integer isActive,HttpServletResponse response,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		AwsAccounts awsAccounts=new AwsAccounts();
		awsAccounts.setId(id);
		if(isActive==Common.ACCOUNT_NOTACTIVE){
			awsAccounts.setIsActive(Common.ACCOUNT_ISACTIVE);
		}else{
			awsAccounts.setIsActive(Common.ACCOUNT_NOTACTIVE);
		}
		if(awsAccountService.updateByPrimaryKeySelective(awsAccounts)){
			map.put("success", true);
		}else{
			map.put("success", false);
		}
		/*数据返回*/
		WriteJsonUtil.writeJsonObject(map, response);
	}
	
	/**
	 * 上传导入Excel数据中的设备信息
	 * @param   uploadfile 文件MultipartFile response 返回对象
	 * @throws Exception 
	 */
	@RequestMapping(value= "/uploadExcel" , produces = "text/html; charset=utf-8")
	@ResponseBody
	public String uploadExcel(@RequestParam("file") MultipartFile file,HttpServletRequest request, HttpServletResponse response) throws Exception {		
		HashMap<String, Object> mag=new HashMap<String, Object>();
		/*获取文件路径和文件名*/
		String fileRealPath = request.getServletContext().getRealPath("/static/uploadExcel");
		String worksPathsFileName = file.getOriginalFilename(); // 获取上传文件的文件名
		try {		
			//此处也可以使用Spring提供的MultipartFile.transferTo(File dest)方法实现文件的上传  
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(fileRealPath, worksPathsFileName));         
		} catch (IOException e) {		
			mag.put("resultMessage", "Excel导入数据异常");
		}
		 importEmployee(fileRealPath+"/"+worksPathsFileName,mag,request);
		 
		 JSONObject json = JSONObject.fromObject(mag);
		 return json.toString();
	}
	
   /**
    * 将Execl的数据遍历存储到数据库中
    * @param fis
    * @param mag
    */
	public void importEmployee(String fis,HashMap<String, Object> mag,HttpServletRequest request) {  
       try {  
        	File file=new File(fis);
            //打开文件  
            Workbook book = Workbook.getWorkbook(file);  
            //得到第一个工作表对象  
            Sheet sheet = book.getSheet(0);  
            awsAccountService.insertExcelData(sheet,mechanismService,mag,request);
        } catch (Exception e) {
        	log.error("批量导入数据有异常！");
        }
    }
	
	/**
	 * 导出批量异常数据
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/errorExportExcelData")														
    public void errorExportExcelData(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	String fileName="导出AWS平台异常数据.xls";
    	String contextPath = request.getServletContext().getRealPath("/");
    	String urlRoot = contextPath + "static/exportDoc/" + fileName;
    	ExportChannelExcel.downloadFile(fileName,contextPath,urlRoot,response, request);//下载文档
    }
	
	/**
	 * 获取对应的所有的可用及已经激活的Account数据
	 * 2019/3/19
	 * @param response
	 */
	@RequestMapping("/getAllAccountList")
	public void getAllAccountList(String payAccountData,HttpServletResponse response){
		AwsAccounts awsAccounts=new AwsAccounts();
		awsAccounts.setAccountName(payAccountData.trim());
		awsAccounts.setAccountStause(Common.ACCOUNT_STATE_ACTIVE);
		awsAccounts.setIsActive(Common.ACCOUNT_NOTACTIVE);
		List<AwsAccounts> list=awsAccountService.getAllAccountList(awsAccounts);
		/*数据返回*/
		WriteJsonUtil.writeJsonObject(list, response);
	}
}
