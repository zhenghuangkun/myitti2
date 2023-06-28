package com.awslabplatform_admin.controller.overviewManage;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.COMM_FAILURE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.organizations.model.Account;
import com.awslabplatform_admin.common.Common;
import com.awslabplatform_admin.common.message.IamMessage;
import com.awslabplatform_admin.entity.AwsAccounts;
import com.awslabplatform_admin.entity.Billing;
import com.awslabplatform_admin.entity.Course;
import com.awslabplatform_admin.entity.Experiment;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.QuotaAllocation;
import com.awslabplatform_admin.entity.Student;
import com.awslabplatform_admin.entity.User;
import com.awslabplatform_admin.service.awsAccountManage.AwsAccountsService;
import com.awslabplatform_admin.service.billing.BillingService;
import com.awslabplatform_admin.service.courseManage.CourseService;
import com.awslabplatform_admin.service.courseManage.ExperimentService;
import com.awslabplatform_admin.service.userManage.UserService;
import com.awslabplatform_admin.util.JsonTool;
import com.awslabplatform_admin.util.ResultUtil;
import com.awslabplatform_admin.util.ShiroUtil;
import com.awslabplatform_admin.util.UUIDUtils;
import com.awslabplatform_admin.util.WriteJsonUtil;

@RestController
@RequestMapping("overview")
public class overviewController {

	/**实验的service*/
    @Autowired
    private ExperimentService  experimentService;
    
    /**用户管理的service*/
	@Autowired
	private UserService userService;
	
	/**
	 * 课程service
	 */
	@Autowired
	private CourseService courseService;
	
	/**AWSAccount的service*/
	@Autowired
	private AwsAccountsService accountService;
	
	/**账单的service*/
	@Autowired
	private BillingService billingService;
    
	/**
	 * 查询正在做实验的学生，显示在表格的列表上查询分页信息
	 * @param start
	 * @param length
	 * @param draw
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/selectUserStuexperiment")
	public void selectUserStuexperiment(int start, int length, int draw,HttpServletResponse response) throws IOException{
	    Map<String,Object> condition  = new HashMap<String, Object>();
	    condition.put("teacherId", ShiroUtil.getCurrentUser().getUserId());
	    condition.put("userState", Common.STUDENT_STATE_ACTIVE);
	    PageInfo pageInfo = new PageInfo(start, length, draw);
	    pageInfo.setCondition(condition);
		userService.getUserStuexperiment(pageInfo);
		JsonTool.toJson(pageInfo,response);
	}
	
	/**
	 * 查询已经发布和未发布的课程的数量
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/countCourseYes")
	public void countCourseYes(String release,HttpServletResponse response){
	   Map<String,Object> condition  = new HashMap<String, Object>();
	   condition.put("teacherId", ShiroUtil.getCurrentUser().getUserId());
	   condition.put("status", release);
	   int count=courseService.getCountCourseYes(condition);
		WriteJsonUtil.writeJsonObject(count, response);
	}
	
	/**
	 * 查询当前登陆者老师的课程已学习总人数
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/haveBeenStudying")
	public void haveBeenStudying(HttpServletResponse response){
	   Map<String,Object> condition  = new HashMap<String, Object>();
	   condition.put("teacherId", ShiroUtil.getCurrentUser().getUserId());
	   int count=courseService.getLearning(condition);
	   WriteJsonUtil.writeJsonObject(count, response);
	}
	
	
	/**
	 * 查询当前登陆者院系管理员 看到的本院系教师课程数量前十排行
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/selectCourseAmount")
	public void selectCourseAmount(HttpServletResponse response){
		Map<String,Object> condition  = new HashMap<String, Object>();
	    condition.put("departmentId", ShiroUtil.getCurrentUser().getDepartmentId());
	    condition.put("status", Common.COURSE_STATE_DELETE);
	    List<Course> courseList=courseService.getCourseAmount(condition);
	    WriteJsonUtil.writeJsonObject(courseList, response);
		
	}
	
	/**
	 * 查询当前登陆者院系管理员 看到的本院系已审核和未审核的课程
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/auditCourse")
	public void auditCourse (String status,HttpServletResponse response){
	   Map<String,Object> condition  = new HashMap<String, Object>();
	   condition.put("collegeId", ShiroUtil.getCurrentUser().getDepartmentId());
	   condition.put("status", status);
	   int count=courseService.getAuditCourse(condition);
		WriteJsonUtil.writeJsonObject(count, response);
	}
	
	
	/**
	 * 根据用户选择月份 和登陆者院系account 查询本月总花费
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/selectCostMoney")
	public void selectCostMoney(String month,HttpServletResponse response){
		if(!("0".equals(ShiroUtil.getCurrentUser().getAwsAccount()))){
			String awsAccount= ShiroUtil.getCurrentUser().getAwsAccount();
			AwsAccounts accounts=accountService.getUserAccount(awsAccount);//根据当前登陆者的AWSaccountid 获取accountid
			Map<String,Object> map  = new HashMap<String, Object>();
			 map.put("moneyAccountId", accounts.getAccountId());
			 map.put("moneymonth",month+"-01");
			 Billing bill=billingService.getMonthBill(map);
			 WriteJsonUtil.writeJsonObject(bill, response);
		}

	}
	
	/**
	 * 查询出所有的院系 accountId 和院系名称
	 */
	@RequestMapping("/seleAllDepartments")
	public void seleAllDepartments(HttpServletResponse response){
		 Map<String,Object> map  = new HashMap<String, Object>();
		 map.put("isPayingAccount", Common.ACCOUNT_NOTPAYING);
		 List<User> list=userService.getAllDepartments(map);
		 WriteJsonUtil.writeJsonObject(list, response);
	}
	
	
	/**
	 *  查询各个院系每个月份总额度 和已花费金额 和已开发票金额
	 * @param response
	 */
	@RequestMapping("/seleDepartmentsCost")
	public void seleDepartmentsCost(String accountId,String month,HttpServletResponse response){
		 Map<String,Object> map  = new HashMap<String, Object>();
		 map.put("moneyAccountId", accountId);
		 map.put("moneymonth",month+"-01");
		 Billing bill=billingService.getMonthBill(map);
		 WriteJsonUtil.writeJsonObject(bill, response);
	}
	
	/**
	 * 查询每个院系的总额度和已开发票金额 
	 * @param response
	 */
	@RequestMapping("/selACombination")
	public void selACombination(String accountId,String totalMonth,HttpServletResponse response){
		 Map<String,Object> map  = new HashMap<String, Object>();
		 map.put("accountId", accountId);
		 map.put("totalMonth", totalMonth+"-01");
		 QuotaAllocation quotaAllocation=billingService.getACombination(map);
		 WriteJsonUtil.writeJsonObject(quotaAllocation, response);
	}
	

	/**
	 * 总额度和已开发票金额添加或修改
	 * @param linesNum 总额度
	 * @param accountId
	 * @param response
	 */
	@RequestMapping("/editLines")
	public void editLines(String ModifyTheContent,String invoiceByvalue,String accountId,int quotaId,String totalMonth, HttpServletResponse response){
		boolean success=false;
		String time=totalMonth+"-01";
		if(quotaId==0){//为0时进行插入
	         QuotaAllocation quota=new QuotaAllocation();
	         quota.setId(UUIDUtils.getUUID());
	         quota.setAccountId(accountId);
	         quota.setTotalMonth(time);
	         if(ModifyTheContent!=null){
	        	 quota.setTotalAmount(ModifyTheContent);
	         }else{
	        	 quota.setInvoiceAmount(invoiceByvalue);
	         }
			 if(billingService.insertTotalamount(quota)){//插入总额成功
				 success= true;
			}else {
				success= false;
			}
			/*数据返回*/
			 WriteJsonUtil.writeJsonObject(success, response);
		}else if(quotaId==1){//为1时进行修改
			Map<String,Object> map  = new HashMap<String, Object>();
			 if(ModifyTheContent!=null){
				 map.put("totalAmount", ModifyTheContent);
			 }else{
				 map.put("invoiceByvalue", invoiceByvalue);
			 }
			 map.put("accountId", accountId);
			 map.put("totalMonth", time);
	         if(billingService.updateInvoice(map)){//修改总额成功
	        	 success= true;
	 		}else {
	 			success= false;
	 		}
	 		/*数据返回*/
	 		WriteJsonUtil.writeJsonObject(map, response);
		}
	}

}
