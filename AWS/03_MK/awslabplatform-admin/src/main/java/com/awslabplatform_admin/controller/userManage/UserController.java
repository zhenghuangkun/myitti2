package com.awslabplatform_admin.controller.userManage;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.awslabplatform_admin.common.Common;
import com.awslabplatform_admin.common.ShiroPermissionConstant;
import com.awslabplatform_admin.common.message.UserMessage;
import com.awslabplatform_admin.entity.AwsAccounts;
import com.awslabplatform_admin.entity.AwsIams;
import com.awslabplatform_admin.entity.FederationInfo;
import com.awslabplatform_admin.entity.Mechanism;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Result;
import com.awslabplatform_admin.entity.Role;
import com.awslabplatform_admin.entity.Student;
import com.awslabplatform_admin.entity.User;
import com.awslabplatform_admin.service.awsAccountManage.AwsAccountsService;
import com.awslabplatform_admin.service.awsAccountManage.AwsIamsService;
import com.awslabplatform_admin.service.federation.Federation;
import com.awslabplatform_admin.service.federation.Impl.FederationImpl;
import com.awslabplatform_admin.service.mechanismManage.MechanismService;
import com.awslabplatform_admin.service.systemManage.RoleService;
import com.awslabplatform_admin.service.userManage.UserService;
import com.awslabplatform_admin.util.ExportChannelExcel;
import com.awslabplatform_admin.util.JsonTool;
import com.awslabplatform_admin.util.Md5Util;
import com.awslabplatform_admin.util.ResultUtil;
import com.awslabplatform_admin.util.ShiroUtil;
import com.awslabplatform_admin.util.UUIDUtils;
import com.awslabplatform_admin.util.WriteJsonUtil;

import static com.awslabplatform_admin.common.Common.*;

/**
* 用户管理的Controller层
*
* @author   czy
* @version  2018-4-01
*/
@RestController
@RequestMapping("userManage")
public class UserController {

	/**用户管理的service*/
	@Autowired
	private UserService userService;

	/**AWSAccount的service*/
    @Autowired
    private  AwsAccountsService  awsAccountService;

    /**IAM的service*/
    @Autowired
    private  AwsIamsService awsIamsService;

    /**角色service*/
    @Autowired
    private  RoleService  roleService;
    
    /**机构管理的service*/
    @Autowired
    private  MechanismService  mechanismService;

    private static final Log log = LogFactory.getLog(UserController.class);
    /**
     * 根据学校的名称及当前登录者的院系，来获取对应院系的下拉列表
     * @param response
     * @param request
     */
	@RequestMapping("/getDepartmentName")
	public void getDepartmentNameData(String mechanismPid,HttpServletResponse response){
		
		Mechanism mechanism=new Mechanism();
		mechanism.setMechanismPid(mechanismPid);
		if(!(ShiroUtil.getCurrentUser().getRoleType().equals(ROLE_TYPE_PLATFORM))){
			mechanism.setMechanismId(ShiroUtil.getCurrentUser().getDepartmentId());
		}
		List<Mechanism> mechanismList=mechanismService.getAllMechanismList(mechanism);
		/*数据返回*/
		WriteJsonUtil.writeJsonObject(mechanismList, response);
	}

	/**
	 * 获取用户管理的数据，将数据显示在表格上
	 * @param dicId
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/selectUserManageData")
	public void selectAwsAccountData(int roleType,String searchData,int start, int length, int draw,HttpServletResponse response) throws IOException{
		Map<String,Object> condition  = new HashMap<String, Object>();
		if(ShiroUtil.getCurrentUser().getDepartmentId()!=null){
			/*CH version 2.0*/
			if(!ShiroUtil.getCurrentUser().getRoleType().equals(ROLE_TYPE_PLATFORM)){
				condition.put("departmentId", ShiroUtil.getCurrentUser().getDepartmentId());//获取当前登陆者所属院系
			}

		}
		/*condition.put("userStateDept", Common.USER_STATE_ACTIVE);*///用户管理可用状态1
		condition.put("roleType", roleType);//用户类型ID
		condition.put("searchData", searchData.trim());//查询的条件
		PageInfo pageInfo = new PageInfo(start, length, draw);
		pageInfo.setCondition(condition);
		userService.getUserManageData(pageInfo);
		/*数据返回*/
		JsonTool.toJson(pageInfo,response);
	}

	/**
	 * 获取用户管理学生的数据，将数据显示在表格上
	 * @param dicId
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/selectUserStuManageData")
	public void selectUserStuManageData(String inputUnitName,int start, int length, int draw,HttpServletResponse response) throws IOException{
		Map<String,Object> condition  = new HashMap<String, Object>();
		if(ShiroUtil.getCurrentUser().getDepartmentId()!=null){
			/*CH version 2.0*/
			if(!ShiroUtil.getCurrentUser().getRoleType().equals(ROLE_TYPE_PLATFORM)){
				condition.put("departmentId", ShiroUtil.getCurrentUser().getDepartmentId());//获取当前登陆者所属院系
			}
		}
		condition.put("searchData", inputUnitName.trim());//查询的条件
		/*condition.put("userState", Common.STUDENT_STATE_ACTIVE);*/
		PageInfo pageInfo = new PageInfo(start, length, draw);
		pageInfo.setCondition(condition);
		userService.getUserStuPageInfo(pageInfo);
		/*数据返回*/
		JsonTool.toJson(pageInfo,response);
	}

	/**
     * 根据条件org和主账号类型来获取获取AWS Account的数据
     * @param response
     * @param request
     */
	@RequestMapping("/findByOrgAwsAccountData")
	public void findByOrgAwsAccountData(String org,String departmentId,HttpServletResponse response){
		AwsAccounts awsAccounts =new AwsAccounts();
		awsAccounts.setOrg(org);//机构ID
		awsAccounts.setDepartmentId(departmentId);
		if("".equals(departmentId)){
			awsAccounts.setIsPayingAccount(Common.ACCOUNT_ISPAYING);//主账户
		}else{
			awsAccounts.setIsPayingAccount(Common.ACCOUNT_NOTPAYING);//不为主账户
		}
		List<AwsAccounts> list=awsAccountService.findByOrgAwsAccountData(awsAccounts);
		/*数据返回*/
		WriteJsonUtil.writeJsonObject(list, response);
	}

	/** 注销原因：用不到IAM管理 CH
	 * 根据条件获取AWS IAM的数据，将数据显示在编辑的页面上
	 * @param id
	 */
/*	@RequestMapping("/getAwsIamDatas")
	public void getAwsIamDatas(String id,HttpServletResponse response){
		try {
			AwsIams awsIams =new AwsIams();
			awsIams.setId(id);
			JsonTool.toJson(awsIamsService.selectIdAwsIamData(awsIams),response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * 查询角色，用来显示对应用户的用户类型
	 * @param roleType
	 */
	@RequestMapping("/findByRoleData")
	public void findByRoleData(String roleType,HttpServletResponse response){
		Role role =new Role();
		role.setRoleType(roleType);
		role.setState(Common.STATE_ACTIVE);
		List<Role> roleList=roleService.findByRoleData(role);
		WriteJsonUtil.writeJsonObject(roleList, response);
	}

	/**
     * 根据条件id获取账户内容的数据
     * @param response
     * @param request
     */
	@RequestMapping("/getAwsAccounts")
	public void getAwsAccounts(String id,HttpServletResponse response){
		AwsAccounts awsAccounts =new AwsAccounts();
		awsAccounts.setId(id);//选中的id
		AwsAccounts getawsAccounts=awsAccountService.selectIdAwsAccountData(awsAccounts);
		/*数据返回*/
		WriteJsonUtil.writeJsonObject(getawsAccounts, response);
	}

	/**
     * 根据条件accountId和可用IAM来获取获取AWS IAM的数据
     * @param response
     * @param request
     */
	@RequestMapping("/findByIAMData")
	public void findByIAMData(String accountId,String iamType,HttpServletResponse response){
		AwsIams awsIams =new AwsIams();
		awsIams.setAwsAccount(accountId);;//AwsAccountID
		awsIams.setIsUsed(Common.IAM_ISUSER);//可用IAM
		awsIams.setItemValue(iamType);
		awsIams.setiAMStatus(Common.ACCOUNT_STATE_ACTIVE);;//可用状态IAM
		List<AwsIams> list=awsIamsService.findByIAMData(awsIams);
		/*数据返回*/
		WriteJsonUtil.writeJsonObject(list, response);
	}

	/**
     * 假删除激活用户平台管理员列表上的数据，将数据传到数据库进行修改userState对应的值
     * @param response
     * @param request
     */
	@RequestMapping("/deleteUserData")
	public void deleteUserData(String userId,int userState,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("userState", userState);
		if(userService.deleteUserData(map)){//假删除激活成功
			map.put("success", true);
		}else {
			map.put("success", false);
		}
		/*数据返回*/
		WriteJsonUtil.writeJsonObject(map, response);
	}

	/**
	 * 根据条件获取用户平台管理员的数据，将数据显示在编辑的页面上
	 * @param id
	 */
	@RequestMapping("/getUserData")
	public void getUserData(String userId,HttpServletResponse response){
		try {
			User user=new User();
			user.setUserId(userId);
			JsonTool.toJson(userService.selectIdUserData(user),response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
     * 提交添加用户平台管理员数据
     * @param response
     * @param request
     */
	@RequestMapping(value="/addUser", method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result addUser(@RequestBody User user){

		return userService.addUserData(user,awsIamsService,roleService);
	}

	/**
	 * 提交编辑用户平台管理员数据
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/editUser", method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result editUser(@RequestBody User user){

		return userService.editUser(user,awsIamsService,roleService);
	}
	
	/**
     * 提交添加用户管理学生数据
     * @param response
     * @param request
     */
	@RequestMapping(value="/addUserStu", method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result addUserStu(@RequestBody Student student){
		student.setStuId(student.getStuId().trim());
		student.setPhoneNum(student.getPhoneNum().trim());
		student.setEmail(student.getEmail().trim());
		if(userService.countUserStuData(student)>0){//判断学生学号、邮箱或电话号码是否存在
			return ResultUtil.getResult(false, UserMessage.EXIST_USERSTUID_DATA);
		}else{		
			student.setId(UUIDUtils.getUUID());
			student.setUserPwd(Md5Util.md5(student.getStuId().substring(student.getStuId().length()-6,student.getStuId().length()), ShiroPermissionConstant.PWDSalt));
			student.setUserState(Common.STUDENT_STATE_ACTIVE);//可用
			userService.addUserStu(student);
			return ResultUtil.getResult(true, UserMessage.ADD_USER_SUCCES);
		}
	}
	
	/**
	 * 提交编辑用户管理学生数据
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/editUserStu", method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result editUserStu(@RequestBody Student student){
		
		return userService.editUserStudent(student);
	}
	
	/**
     * 假删除激活学生列表上的数据，将数据传到数据库进行修改userState对应的值
     * @param response
     * @param request
     */
	@RequestMapping("/deleteUserStuData")
	public void deleteUserStuData(String id,int userState,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("userState", userState);
		if(userService.deleteUserStuData(map)){
			map.put("success", true);
		}else {
			map.put("success", false);
		}
		/*数据返回*/
		WriteJsonUtil.writeJsonObject(map, response);
	}

	
	/**
	 *下载批量导入数据库的Excel表格模板
	 * @throws PortalException 
	 */	
	@RequestMapping("/downloadExcelModel")
	public Result downloadExcelModel(HttpServletRequest request, HttpServletResponse response){		
		
		return userService.downloadExcelModel(request, response);
	}
	
	@RequestMapping("/exportExcelModel")														
    public void exportExcelModel(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	String fileName="批量导入数据模板.xls";
    	String contextPath = request.getServletContext().getRealPath("/");
    	String urlRoot = contextPath + "static\\exportDoc\\" + fileName;
    	ExportChannelExcel.downloadFile(fileName,contextPath,urlRoot,response, request);//下载文档
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
            userService.insertExcelData(sheet,mechanismService,mag,request);
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
    	String fileName="导出异常数据.xls";
    	String contextPath = request.getServletContext().getRealPath("/");
    	String urlRoot = contextPath + "static/exportDoc/" + fileName;
    	ExportChannelExcel.downloadFile(fileName,contextPath,urlRoot,response, request);//下载文档
    }
	
	/**
	 * 根据条件获取学生的数据，将数据显示在编辑的页面上
	 * @param id
	 */
	@RequestMapping("/getUserStuData")
	public void getUserStuData(String id,HttpServletResponse response){
		try {
			Student student=new Student();
			student.setId(id);
			JsonTool.toJson(userService.selectIdUserStuData(student),response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * CH 修改原因：更换获取凭证方式，搜索条件与方法变更 用用户的院系ID来获取该学院的实验IAM
	 * 之前的方法为根据当前登录的人信息来获取联盟登录的路径，将获取的路径显示在页面上
	 * （可能要需要这块功能负责自行修改成符合业务的Service）
	 * @param 
	 */
	@RequestMapping("/getIamRegisterUrl")
	public void getIamRegisterUrlData(HttpServletResponse response){
		try {
			Federation federation = new FederationImpl();
			FederationInfo federationInfo =new FederationInfo();
			federationInfo.setRealName(ShiroUtil.getCurrentUser().getEmail());//当前登录的用户邮箱
			User user=userService.getByUserEmail(ShiroUtil.getCurrentUser().getEmail());//根据当前登入的用户邮箱来获取其iamid
//			AwsIams awsIams=new AwsIams();
//			awsIams.setId(user.getIAM());
			String departmentId=user.getDepartmentId();//用户院系ID
			/*CH version 2.0*/
			AwsIams awsIamsdata=awsIamsService.selectIdAwsIamData(departmentId,AWS_ACCOUNTPOOL_USETYPE,AWS_IAM_IAMKIND,String_STATE_ACTIVE,AWS_ACCOUNTPOOL_ISACTIVE,AWS_ACCOUNTPOOL_ISDELETE,AWS_ACCOUNT_ACCOUNTSTAUSE,AWS_ACCOUNT_ISACTIVE);//获取当该区的iam的accessKeyID和accessKey
			federationInfo.setAccess_key_id(awsIamsdata.getAccessKeyID());//IAM 访问key ID
			federationInfo.setSecret_key(awsIamsdata.getAccessKey());//IAM 访问key
			federationInfo.setConsoleURL("https://console.amazonaws.cn/iam/home?region=cn-north-1#/home");//aws 控制台链接，精确到控制台对应功能路径
			federationInfo.setIssuerURL("http://localhost:8080/awslabplatform-admin/home/overview");//请求者URL，填写我们自己系统的URL
			federationInfo.setPolicy(FederationInfo.getCreateiampolicy());//策略脚本
		
			String iamRegisterUrl=federation.getAwsFederationeUrl(federationInfo);
			
			JsonTool.toJson(iamRegisterUrl,response);
		} catch (Exception e) {
			System.out.println("获取getIamRegisterUrl错误！");
		}
	}
}


