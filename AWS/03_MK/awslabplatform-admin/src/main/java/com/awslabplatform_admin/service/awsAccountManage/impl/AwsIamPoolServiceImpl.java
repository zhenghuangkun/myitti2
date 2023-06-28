package com.awslabplatform_admin.service.awsAccountManage.impl;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.awslabplatform_admin.common.Common;
import com.awslabplatform_admin.dao.awsAccountManage.AwsIamPoolDao;
import com.awslabplatform_admin.entity.AwsAccounts;
import com.awslabplatform_admin.entity.AwsIamPool;
import com.awslabplatform_admin.entity.AwsIams;
import com.awslabplatform_admin.entity.IAMUserInfo;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.service.awsAccountManage.AwsAccountsService;
import com.awslabplatform_admin.service.awsAccountManage.AwsIamPoolService;
import com.awslabplatform_admin.service.awsAccountManage.AwsIamsService;
import com.awslabplatform_admin.service.base.impl.BaseServiceImpl;
import com.awslabplatform_admin.service.gainAwsIam.IAMService;
import com.awslabplatform_admin.util.ExportChannelExcel;
/**
* AwsIams的Service层
*
* @version 2018/3/22
*/
@Service("AwsIamPoolService")
public class AwsIamPoolServiceImpl extends BaseServiceImpl<AwsIamPoolDao, AwsIamPool, String> implements AwsIamPoolService {

	 private static final Log log = LogFactory.getLog(AwsIamPoolServiceImpl.class);
	/**
     * 查询分页信息
     * @param pageInfo
     **/
	@Override
	public void getAwsIamPoolPageInfo(PageInfo pageInfo) {
		List<AwsIamPool> list=baseDao.getAwsIamPoolData(pageInfo);
		/*查询分页数据*/
		pageInfo.setData(list);
		/*查询总数*/
		int total = baseDao.countAwsIamPool(pageInfo);
		pageInfo.setRecordsTotal(total);
		pageInfo.setRecordsFiltered(total);//过滤记录数，dataTable必要参数
		
	}
	
	/**
	 * 获取AWSIamPool的数据，将数据显示在表格上
	 * @param pageInfo
	 */
	@Override
	public List<AwsIamPool> getAwsIamPoolData(PageInfo pageInfo) {
		return baseDao.getAwsIamPoolData(pageInfo);
	}

	/**
	 * 查询AWSIamPool数据的数量
	 * @param pageInfo
	 */
	@Override
	public int countAwsIamPool(PageInfo pageInfo) {
		return baseDao.countAwsIamPool(pageInfo);
	}

	/**
	 * 假删除AWSIamPool数据的数据信息  0：未删除  1：删除
	 * @param id
	 */
	@Override
	public boolean deleteAwsIamPoolData(String id, Integer isDelete) {
		return baseDao.deleteAwsIamPoolData(id, isDelete);
	}

	/**
	 * 根据条件获取AWSIAMPool的数据，将数据显示在编辑的页面上
	 * @param awsIamPool
	 */
	@Override
	public AwsIamPool selectIdAwsIamPoolData(AwsIamPool awsIamPool) {
		return baseDao.selectIdAwsIamPoolData(awsIamPool);
	}

	/**
	 *  查询添加accountID是否已经存在
	 */
	@Override
	public int countAwsIamPoolData(AwsIamPool awsIamPool) {
		
		return baseDao.countAwsIamPoolData(awsIamPool);
	}
	
	/**
	 * 编辑是判断数据库的数量是否存在 
	 * @param pageInfo
	 */
	@Override
	public int countEditAwsIamPoolData(PageInfo pageInfo) {

		return baseDao.countEditAwsIamPoolData(pageInfo);
	}

	/**
	 * 根据对应的付款账户及付款名称的条件获取AWSIAMPool的数据
	 * @param awsIamPool
	 * @return
	 */
	@Override
	public List<AwsIamPool> selectAwsIamPoolData(AwsIamPool awsIamPool){
		
		return baseDao.selectAwsIamPoolData(awsIamPool);
	}
	
	/**
	 * 添加院系账户及IAM账户
	 * @param awsIamPool
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class }) 
	public void insertAwsIamPoolData(AwsIamPool awsIamPool,AwsIamsService awsIamsService) {
	   try{
			awsIamPool.setCreateTime(new Date());
			insert(awsIamPool);
			
			//添加表示管理人员使用IAM数据
			AwsIams awsIams=new AwsIams();
			awsIams.setAwsAccount(awsIamPool.getId());//关联Account ID
			awsIams.setiAMName(awsIamPool.getIAMName());//IAM 用户名
			awsIams.setPassword(awsIamPool.getPassword());//密码
			awsIams.setAccessKeyID(awsIamPool.getAccessKeyID());//PK
			awsIams.setAccessKey(awsIamPool.getAccessKey());//SK
			awsIams.setIAMKind(Common.IAM_KIND_AUSER);//0: 表示管理人员使用
			awsIams.setCreateTime(new Date());//创建时间
			awsIamsService.insert(awsIams);
		
			IAMService iAMServices=new IAMService(awsIamPool.getAccessKeyID(), awsIamPool.getAccessKey(), null);
			IAMUserInfo iAMUserInfo=iAMServices.createUser();
			if(awsIamPool.getUseType()==Common.IAMPOOL_USERTYPE){
				iAMServices.attachPolicyToUser(iAMUserInfo.getIAMName());
			}
			//添加表示实验者使用IAM数据
			insertIamUser(awsIamPool.getId(),iAMUserInfo,awsIamsService);
			
			log.debug("添加院系账户及IAM账户！");
	    }catch(Exception a){
		    log.debug("添加院系账户及IAM账户异常！");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	/**
	 * 添加表示实验者使用IAM数据
	 * @param accountID
	 * @param iAMService
	 * @param awsIamsService
	 */
	public void insertIamUser(String accountID,IAMUserInfo iAMUserInfo,AwsIamsService awsIamsService){
		
		AwsIams awsIam=new AwsIams();
		awsIam.setAwsAccount(accountID);//关联Account ID
		awsIam.setiAMName(iAMUserInfo.getIAMName());//IAM 用户名
		awsIam.setPassword(iAMUserInfo.getPassword());//密码
		awsIam.setAccessKeyID(iAMUserInfo.getAccessKeyID());//PK
		awsIam.setAccessKey(iAMUserInfo.getAccessKey());//SK
		awsIam.setConsoleLoginLink(iAMUserInfo.getConsoleLoginLink());
		awsIam.setIAMKind(Common.IAM_KIND_EUSER);//1：表示实验者使用
		awsIam.setCreateTime(new Date());//创建时间
		awsIamsService.insert(awsIam);
		
	}

	/**
	 * 修改院系账户及IAM账户
	 * @param awsIamPool
	 * @param awsIamsService
	 */
	@Override
	public void updateByAwsIamPoolData(AwsIamPool awsIamPool,AwsIamsService awsIamsService){
		
		updateByPrimaryKeySelective(awsIamPool);
		
		//修改表示管理人员使用IAM数据
		AwsIams awsIams=new AwsIams();
		awsIams.setId(awsIamPool.getIAMId());//IAMId
		awsIams.setAwsAccount(awsIamPool.getId());//关联Account ID
		awsIams.setiAMName(awsIamPool.getIAMName());//IAM 用户名
		awsIams.setPassword(awsIamPool.getPassword());//密码
		awsIams.setAccessKeyID(awsIamPool.getAccessKeyID());//PK
		awsIams.setAccessKey(awsIamPool.getAccessKey());//SK
		awsIamsService.updateByPrimaryKeySelective(awsIams);
	}
	
	/**
	 * 表格数据导入进行封装
	 * @param sheet
	 * @param rowCount
	 * @param columnCount
	 * @param mag
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class }) 
	public List<AwsIamPool> insertExcelData(Sheet sheet,AwsIamsService awsIamsService,AwsAccountsService awsAccountService,HashMap<String, Object> mag,HttpServletRequest request){
		List<AwsIamPool> tableList = new ArrayList<AwsIamPool>();
		 for (int i = 1; i < sheet.getRows(); i++) {
			 String accountID=sheet.getCell(0, i).getContents();
			 String payAccountID=sheet.getCell(1, i).getContents();
			 String payAccountName=sheet.getCell(2, i).getContents();
			if("".equals(accountID.trim()) && "".equals(payAccountID.trim()) && "".equals(payAccountName.trim())){continue;}
			try{
				AwsIamPool awsIamPool = new AwsIamPool();
				awsIamPool.setAccountID(accountID.trim());//付款账号
				awsIamPool.setIsActive(Common.IAMPOOL_NOTACTIVE);//可用状态
				if(countAwsIamPoolData(awsIamPool)>0){
					System.out.println(1/0);
				}
				
				AwsAccounts awsAccount =new AwsAccounts();
				awsAccount.setAccountStause(Common.ACCOUNT_STATE_ACTIVE);
				awsAccount.setAccountId(payAccountID.trim());
				awsAccount.setAccountName(payAccountName.trim());
				if(awsAccountService.countAwsAccountData(awsAccount)>0){//判断accountId与accountName数据是否存在
					awsIamPool.setPayAccountID(payAccountID.trim());//付款账号
					awsIamPool.setPayAccountName(payAccountName.trim());//付款账号名称
				}else{
					System.out.println(1/0);
				}
				
				String upt=sheet.getCell(3, i).getContents();
				if("是".equals(upt.trim())){
					awsIamPool.setUseType(Common.IAMPOOL_NOTUSERTYPE);//0 登陆控制台
				}else if("否".equals(upt.trim())){
					awsIamPool.setUseType(Common.IAMPOOL_USERTYPE);//1 不登陆控制台
				}else{
					System.out.println(1/0);
				} 
				awsIamPool.setCreateTime(new Date());
				insert(awsIamPool);
				
				AwsIams awsIams=new AwsIams();
				awsIams.setAwsAccount(awsIamPool.getId());//关联Account ID
				String iAMName=sheet.getCell(4, i).getContents();
				awsIams.setiAMName(iAMName.trim());//IAM 用户名
				if("".equals(iAMName.trim())|| awsIamsService.countAwsIamtData(awsIams)>0){
					System.out.println(1/0);
				}
				String password=sheet.getCell(5, i).getContents();
				String accessKeyID=sheet.getCell(6, i).getContents();
				String accessKey=sheet.getCell(7, i).getContents();
				if("".equals(password.trim()) || "".equals(password.trim()) || "".equals(accessKey.trim())){
					System.out.println(1/0);
				}
				awsIams.setPassword(password.trim());//密码
				awsIams.setAccessKeyID(accessKeyID.trim());//PK
				awsIams.setAccessKey(accessKey.trim());//SK
				
				awsIams.setIAMKind(Common.IAM_KIND_AUSER);//0: 表示管理人员使用
				awsIams.setCreateTime(new Date());//创建时间
				awsIamsService.insert(awsIams);
				
				IAMService iAMServices=new IAMService(awsIams.getAccessKeyID(), awsIams.getAccessKey(), null);
				IAMUserInfo iAMUserInfo=iAMServices.createUser();
				if(awsIamPool.getUseType()==Common.IAMPOOL_USERTYPE){
					iAMServices.attachPolicyToUser(iAMUserInfo.getIAMName());
				}
				//添加表示实验者使用IAM数据
				insertIamUser(awsIamPool.getId(),iAMUserInfo,awsIamsService);
				
 				mag.put("resultMessage", "数据导入成功！");
 			}catch(Exception a){
 				AwsIamPool awsIamPools = new AwsIamPool();
 				awsIamPools.setAccountID(sheet.getCell(0, i).getContents());//AcountID
 				awsIamPools.setPayAccountID(sheet.getCell(1, i).getContents());//关联付款账号
 				awsIamPools.setPayAccountName(sheet.getCell(2, i).getContents());//关联付款账户名称
 				awsIamPools.setUseTypeName(sheet.getCell(3, i).getContents());// 是否console登录
 				awsIamPools.setIAMName(sheet.getCell(4, i).getContents());//IAM User
 				awsIamPools.setPassword(sheet.getCell(5, i).getContents());//PassWord
 				awsIamPools.setAccessKeyID(sheet.getCell(6, i).getContents());//PK
 				awsIamPools.setAccessKey(sheet.getCell(7, i).getContents());//SK
				tableList.add(awsIamPools);
				
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
 			}
         }
		 
		 if(tableList.size()>0){
         	errorExportExcel(tableList,request);
         	mag.put("errorMessage", "导入数据异常数:"+tableList.size());
         }
		return tableList;
	}
	
	
	/**
	 * 导出批量导入数据的错误数据以Excel的形式列出
	 * @param tableList
	 * @param request
	 */
	public void errorExportExcel(List<AwsIamPool> tableList,HttpServletRequest request){
		try {
			Label lab = null;
			String fileName="导出AWS院系异常数据.xls";
			String contextPath = request.getServletContext().getRealPath("/");
	    	String urlRoot = contextPath + "static/exportDoc/";
		    new File(urlRoot).mkdirs();
			String[] title={"AcountID","关联付款账号","关联付款账户名称","是否console登录","IAM User","PassWord","PK","SK"};
			String filePath=urlRoot+fileName;
			WritableWorkbook workbook= Workbook.createWorkbook(new File(filePath));
			WritableSheet sheet = workbook.createSheet(fileName, 0);	  //单元格
			ExportChannelExcel.jxlUtil(lab,title,sheet,workbook);
			errorExcelData(title,tableList, lab, sheet);
	    	ExportChannelExcel.writeOrClose(workbook);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 组合数据，将数据拼接到excel的表格中
	 * @param list
	 * @param lab
	 * @param sheet
	 */
	public static void errorExcelData(String[] title,List<AwsIamPool> list,Label lab,WritableSheet sheet){
		try {
		 for (int j = 0; j < title.length; j++) {sheet.setColumnView(j, 25);}//设置列宽
		 for(int i=0;i<list.size();i++){
			 sheet.setRowView(i+1,380);
        	 lab = new Label(0,i+1,list.get(i).getAccountID());sheet.addCell(lab); //AcountID
        	 lab = new Label(1,i+1,list.get(i).getPayAccountID()); sheet.addCell(lab); //关联付款账号
        	 lab = new Label(2,i+1,list.get(i).getPayAccountName()); sheet.addCell(lab); //关联付款账户名称
        	 lab = new Label(3,i+1,list.get(i).getUseTypeName()); sheet.addCell(lab); //是否console登录
        	 lab = new Label(4,i+1,list.get(i).getIAMName()); sheet.addCell(lab); //IAM User
        	 lab = new Label(5,i+1,list.get(i).getPassword()); sheet.addCell(lab); //PassWord
        	 lab = new Label(6,i+1,list.get(i).getAccessKeyID()); sheet.addCell(lab); //PK
        	 lab = new Label(7,i+1,list.get(i).getAccessKey()); sheet.addCell(lab); //SK
         }
		} catch (Exception e) {
			e.printStackTrace();
        }
	}
}
