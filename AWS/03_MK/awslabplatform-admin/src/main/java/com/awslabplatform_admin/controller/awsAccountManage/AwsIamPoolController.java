package com.awslabplatform_admin.controller.awsAccountManage;

import java.io.File;
import java.io.IOException;
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
import com.awslabplatform_admin.common.message.IamMessage;
import com.awslabplatform_admin.entity.AwsIamPool;
import com.awslabplatform_admin.entity.AwsIams;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Result;
import com.awslabplatform_admin.service.awsAccountManage.AwsAccountsService;
import com.awslabplatform_admin.service.awsAccountManage.AwsIamPoolService;
import com.awslabplatform_admin.service.awsAccountManage.AwsIamsService;
import com.awslabplatform_admin.util.ExportChannelExcel;
import com.awslabplatform_admin.util.JsonTool;
import com.awslabplatform_admin.util.ResultUtil;
import com.awslabplatform_admin.util.WriteJsonUtil;


/**
* AWSIAMPool的Controller层
*
* @author   czy
* @version  2019-3-21
*/
@Controller
@RequestMapping("/awaiamPool")
public class AwsIamPoolController {
    
	/**AwsIamPool的service*/
    @Autowired
    private AwsIamPoolService awsIamPoolService;
    
    /**AwsIams的service*/
    @Autowired
    private AwsIamsService awsIamsService;
    
    /**AWSAccount的service*/
    @Autowired
    private  AwsAccountsService  awsAccountService;
    
    private static final Log log = LogFactory.getLog(AwsIamPoolController.class);
	/**
	 * 获取AWSIamPool的数据，将数据显示在表格上
	 * @param dicId
	 * @param response
	 * @throws IOException 
	 */
    @RequestMapping("/selectAwsIamPoolData")
    public void selectAwsIamPoolData(String inputUnitName,int inputState,String accountIdCheck,int start, int length, int draw,HttpServletResponse response) throws IOException{
    	Map<String,Object> condition  = new HashMap<String, Object>();
		condition.put("accountID", inputUnitName.trim());//IAM 用户名
		condition.put("isUsed", inputState);//是否被使用(状态)
		condition.put("payAccountID", accountIdCheck.trim());
		condition.put("isDelete", Common.IAMPOOL_STATE_ACTIVE);
		PageInfo pageInfo = new PageInfo(start, length, draw);
		pageInfo.setCondition(condition);
		awsIamPoolService.getAwsIamPoolPageInfo(pageInfo);
		/*数据返回*/
		JsonTool.toJson(pageInfo,response);
    }

	/**
	 * 根据条件获取AWSIAMPool的数据，将数据显示在编辑的页面上
	 * @param id
	 */
	@RequestMapping("/getAwsIamPoolData")
	public void getAwsIamPoolData(String id,HttpServletResponse response){
		try {
			AwsIamPool awsIamPool =new AwsIamPool();
			awsIamPool.setId(id);
			awsIamPool.setIAMKind(Common.IAM_KIND_AUSER);
			JsonTool.toJson(awsIamPoolService.selectIdAwsIamPoolData(awsIamPool),response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * 编辑AWSIAMPool账户信息数据
     * @param response
     * @param request
     */
	@RequestMapping(value="/editAwsIamPool", method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result editAwsIam(@RequestBody AwsIamPool awsIamPool){
		Map<String,Object> condition  = new HashMap<String, Object>();
		PageInfo pageInfo = new PageInfo();
		if(awsIamPool.getCopyAccountID().equals(awsIamPool.getAccountID()) && awsIamPool.getCopyIamName().equals(awsIamPool.getIAMName())){
			awsIamPoolService.updateByAwsIamPoolData(awsIamPool,awsIamsService);
			return ResultUtil.getResult(true, IamMessage.EDIT_IAMPOOl_SUCCESS);
		}else{
			if(awsIamPool.getCopyAccountID().equals(awsIamPool.getAccountID()) && !(awsIamPool.getCopyIamName().equals(awsIamPool.getIAMName()))){
				condition.put("IAMName", awsIamPool.getIAMName());
			}
			if(!(awsIamPool.getCopyAccountID().equals(awsIamPool.getAccountID())) && awsIamPool.getCopyIamName().equals(awsIamPool.getIAMName())){
				condition.put("accountID", awsIamPool.getAccountID());
			}
			if(!(awsIamPool.getCopyAccountID().equals(awsIamPool.getAccountID())) && !(awsIamPool.getCopyIamName().equals(awsIamPool.getIAMName()))){
				condition.put("accountID", awsIamPool.getAccountID());
				condition.put("IAMName", awsIamPool.getIAMName());
			}
			condition.put("isActive", Common.IAMPOOL_NOTACTIVE);
			pageInfo.setCondition(condition);
			if(awsIamPoolService.countEditAwsIamPoolData(pageInfo)>0){//EDIT_ACCOUNTOOl_ERROR
				return ResultUtil.getResult(false, IamMessage.EDIT_ACCOUNTOOl_ERROR);
			}else{
				awsIamPoolService.updateByAwsIamPoolData(awsIamPool,awsIamsService);
				return ResultUtil.getResult(true, IamMessage.EDIT_IAMPOOl_SUCCESS);
			}
		}
	}
	
	/**
     * 提交添加AWS IAM账户数据
     * @param response
     * @param request
     */
	@RequestMapping(value="/addAwsIamPool", method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result addAwsIamPoolData(@RequestBody AwsIamPool awsIamPool){
		awsIamPool.setAccountID(awsIamPool.getAccountID().trim());
		awsIamPool.setIsActive(Common.IAMPOOL_NOTACTIVE);
		AwsIams awsIams=new AwsIams();
		awsIams.setiAMName(awsIamPool.getIAMName());
		if(awsIamPoolService.countAwsIamPoolData(awsIamPool)>0 || awsIamsService.countAwsIamtData(awsIams)>0){//判断AccountID数据是否存在
			return ResultUtil.getResult(false, IamMessage.EXIST_IAMPOOl_DATA);
		}else{	
			awsIamPoolService.insertAwsIamPoolData(awsIamPool,awsIamsService);
			return ResultUtil.getResult(true, IamMessage.ADD_IAMPOOL_SUCCES);
		}
	}
	
    /**
     * 假删除AWS IAM列表上的数据，将数据传到数据库进行修改IAMStatus对应的值
     * @param response
     * @param request
     */
	@RequestMapping("/deleteAwsIamPoolData")
	public void deleteAwsIamPoolData(String id,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String, Object>();
		AwsIamPool awsIamPool=new AwsIamPool();
		awsIamPool.setId(id);
		awsIamPool.setIsDelete(Common.IAMPOOL_STATE_DELETE);
		if(awsIamPoolService.updateByPrimaryKeySelective(awsIamPool)){//假删除成功
			map.put("success", true);
		}else {
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
		AwsIamPool awsIamPool=new AwsIamPool();
		awsIamPool.setId(id);
		if(isActive==Common.IAMPOOL_NOTACTIVE){
			awsIamPool.setIsActive(Common.IAMPOOL_ISACTIVE);
		}else{
			awsIamPool.setIsActive(Common.IAMPOOL_NOTACTIVE);
		}
		if(awsIamPoolService.updateByPrimaryKeySelective(awsIamPool)){
			map.put("success", true);
		}else{
			map.put("success", false);
		}
		/*数据返回*/
		WriteJsonUtil.writeJsonObject(map, response);
	}
	
	/**
	 * 根据表格选中的行ID，来获取IAM User账户数据信息
	 * @param id
	 */
	@RequestMapping("/getAwsIamData")
	public void getAwsIamData(String id,HttpServletResponse response) throws IOException{
		AwsIams awsIams =new AwsIams();
		awsIams.setAwsAccount(id);
		List<AwsIams> list=awsIamsService.selectAwsIamData(awsIams);
		/*数据返回*/
		WriteJsonUtil.writeJsonObject(list, response);
	}	
	
	
	/**
	 * 根据对应的付款账户及付款名称的条件获取AWSIAMPool的数据
	 * @param awsIamPool
	 * @return
	 */
	@RequestMapping("/getByAwsIamPoolList")
	public void getByAwsIamPoolList(String copyAccountId,String copyAccountName,HttpServletResponse response) throws IOException{
		AwsIamPool awsIamPool =new AwsIamPool();
		awsIamPool.setPayAccountID(copyAccountId);
		awsIamPool.setPayAccountName(copyAccountName);
		List<AwsIamPool> list=awsIamPoolService.selectAwsIamPoolData(awsIamPool);
		/*数据返回*/
		WriteJsonUtil.writeJsonObject(list, response);
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
            awsIamPoolService.insertExcelData(sheet,awsIamsService,awsAccountService,mag,request);
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
    	String fileName="导出AWS院系异常数据.xls";
    	String contextPath = request.getServletContext().getRealPath("/");
    	String urlRoot = contextPath + "static/exportDoc/" + fileName;
    	ExportChannelExcel.downloadFile(fileName,contextPath,urlRoot,response, request);//下载文档
    }
}
