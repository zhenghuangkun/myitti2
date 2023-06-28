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

import org.omg.CORBA.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.awslabplatform_admin.common.Common;
import com.awslabplatform_admin.dao.awsAccountManage.AwsAccountsDao;
import com.awslabplatform_admin.entity.AwsAccounts;
import com.awslabplatform_admin.entity.Mechanism;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.service.awsAccountManage.AwsAccountsService;
import com.awslabplatform_admin.service.base.impl.BaseServiceImpl;
import com.awslabplatform_admin.service.mechanismManage.MechanismService;
import com.awslabplatform_admin.util.ExportChannelExcel;
import com.awslabplatform_admin.util.UUIDUtils;

/**
* AWSAccount的Service层
*
* @author  czy
* @version 2018/3/22
*/
@Service("AwsAccountsService")
public class AwsAccountsServiceImpl extends BaseServiceImpl<AwsAccountsDao, AwsAccounts, String> implements AwsAccountsService {
	/**日志*/
    private static Logger log = LoggerFactory.getLogger(AwsAccountsServiceImpl.class);
    
	/**
	 * 查询添加AWS Account名称与AccountId是否已经存在
	 * @param aWSAccount
	 */
	@Override
	public int countAwsAccountData(AwsAccounts awsAccounts) {

		return baseDao.countAwsAccountData(awsAccounts);
	}
	
	/**
     * 查询分页信息
     * @param pageInfo
     **/
	@Override
	public void getAwsAccountPageInfo(PageInfo pageInfo,MechanismService mechanismService) {
		/*查询分页数据*/
		pageInfo.setData(baseDao.getAwsAccountData(pageInfo));
		/*查询总数*/
		int total = baseDao.countAwsAccounts(pageInfo);
		pageInfo.setRecordsTotal(total);
		pageInfo.setRecordsFiltered(total);//过滤记录数，dataTable必要参数	
		log.info("获取表格查询数据信息！");
	}

	/**
	 * 删除AWS Account数据的数据信息
	 * @param id
	 */
	@Override
	public boolean deleteAwsAccountData(String id) {
		
		return baseDao.deleteAwsAccountData(id);
	}

	/**
	 * 根据条件获取AWS Account的数据，将数据显示在编辑的页面上
	 * @param awsAccounts
	 */
	@Override
	public AwsAccounts selectIdAwsAccountData(AwsAccounts awsAccounts) {

		return baseDao.selectIdAwsAccountData(awsAccounts);
	}

	/**
	 * 编辑是判断数据库的数量是否存在
	 */
	@Override
	public int countEditAwsAccountData(PageInfo pageInfo) {

		return baseDao.countEditAwsAccountData(pageInfo);
	}

	/**
	 * 根据条件org和主账号类型来获取获取AWS Account的数据
	 * @param awsAccounts
	 */
	@Override
	public List<AwsAccounts> findByOrgAwsAccountData(AwsAccounts awsAccounts) {

		return baseDao.findByOrgAwsAccountData(awsAccounts);
	}

	/**
     * 查询集合数据用来下拉列表的显示
     */
	@Override
	public List<AwsAccounts> getAllAccountList(AwsAccounts awsAccounts) {
		return baseDao.getAllAccountList(awsAccounts);
	}

	/**
	 * 根据当前登陆者awsAccount查询出account id
	 */
	@Override
	public AwsAccounts getUserAccount(String awsAccount) {
		return baseDao.getUserAccount(awsAccount);
	}
	
	/**
	 * 表格数据导入进行封装
	 * @param sheet
	 * @param rowCount
	 * @param mag
	 * @throws SystemException
	 * @throws java.text.ParseException
	 * @throws NumberFormatException
	 */
	@Override
	public List<AwsAccounts> insertExcelData(Sheet sheet,MechanismService mechanismService,HashMap<String, Object> mag,HttpServletRequest request) throws SystemException{
		List<AwsAccounts> tableList = new ArrayList<AwsAccounts>();
		 for (int i = 1; i < sheet.getRows(); i++) {
			 String accountId=sheet.getCell(0, i).getContents();//付款账号
			 String accountName=sheet.getCell(1, i).getContents();//付款账号名称
			 String org=sheet.getCell(2, i).getContents();//机构
			 String departmentId=sheet.getCell(3, i).getContents();//学院
			if("".equals(accountId.trim()) && "".equals(accountName.trim()) && "".equals(org.trim()) && "".equals(departmentId.trim())){continue;}
			try{
				AwsAccounts account = new AwsAccounts();
				account.setAccountId(accountId.trim());//付款账号
				account.setAccountName(accountName.trim());//付款账号名称
				account.setAccountStause(Common.ACCOUNT_STATE_ACTIVE);//可用状态
				if(countAwsAccountData(account)>0 || "".equals(accountId.trim()) || "".equals(accountName.trim())){
					System.out.println(1/0);
				}
				String mechanismId=mechanismService.getMechanismId(getMechanism(org.trim(),null));
				String mechanismIdo=mechanismService.getMechanismId(getMechanism(departmentId.trim(),mechanismId));
				if(mechanismId==null || mechanismIdo==null){
					System.out.println(1/0);
				}else{
					account.setOrg(mechanismId);//所属学校(机构)
					account.setDepartmentId(mechanismIdo);// 学院(系别)
				}
				
				String upt=sheet.getCell(4, i).getContents();
				if("是".equals(upt.trim())){
					account.setIsUPT(Common.ACCOUNT_ISUPT);//为UPT
				}else if("否".equals(upt.trim())){
					account.setIsUPT(Common.ACCOUNT_NOTUPT);//不为UPT
				}else{
					System.out.println(1/0);
				} 
				
				String iam=sheet.getCell(5, i).getContents();
				String ak=sheet.getCell(6, i).getContents();
				String sk=sheet.getCell(7, i).getContents();
				if("".equals(ak.trim()) || "".equals(iam.trim()) || "".equals(sk.trim())){
					System.out.println(1/0);
				}
				account.setIAM(iam.trim());//IAM User
				account.setAK(ak.trim());//AK
				account.setSK(sk.trim());//SK
				
				account.setId(UUIDUtils.getUUID());//UUID
				account.setCreateTime(new Date());//创建时间
				
				insert(account);//添加数据

 				mag.put("resultMessage", "数据导入成功！");
 			}catch(Exception a){
 				AwsAccounts awsAccounts = new AwsAccounts();
 				awsAccounts.setAccountId(sheet.getCell(0, i).getContents());//付款账号
 				awsAccounts.setAccountName(sheet.getCell(1, i).getContents());//付款账号名称
 				awsAccounts.setOrg(sheet.getCell(2, i).getContents());//所属学校(机构)
 				awsAccounts.setDepartmentId(sheet.getCell(3, i).getContents());// 学院(系别)
 				awsAccounts.setUPTName(sheet.getCell(4, i).getContents());//是否UPT所有
 				awsAccounts.setIAM(sheet.getCell(5, i).getContents());//IAM User
 				awsAccounts.setAK(sheet.getCell(6, i).getContents());//AK
 				awsAccounts.setSK(sheet.getCell(7, i).getContents());//SK
				tableList.add(awsAccounts);
 			}
         }
		 
		 if(tableList.size()!=0){
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
	@Override
	public void errorExportExcel(List<AwsAccounts> tableList,HttpServletRequest request) {
		try {
			Label lab = null;
			String fileName="导出AWS平台异常数据.xls";
			String contextPath = request.getServletContext().getRealPath("/");
	    	String urlRoot = contextPath + "static/exportDoc/";
		    new File(urlRoot).mkdirs();
			String[] title={"付款账号","付款账号名称","所属学校(机构)","学院(系别)","是否UPT所有","IAM User","AK","SK"};
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
	public static void errorExcelData(String[] title,List<AwsAccounts> list,Label lab,WritableSheet sheet){
		try {
		 for (int j = 0; j < title.length; j++) {sheet.setColumnView(j, 25);}//设置列宽
		 for(int i=0;i<list.size();i++){
			 sheet.setRowView(i+1,380);
        	 lab = new Label(0,i+1,list.get(i).getAccountId());sheet.addCell(lab); //付款账号
        	 lab = new Label(1,i+1,list.get(i).getAccountName()); sheet.addCell(lab); //付款账号名称
        	 lab = new Label(2,i+1,list.get(i).getOrg()); sheet.addCell(lab); //所属学校(机构)
        	 lab = new Label(3,i+1,list.get(i).getDepartmentId()); sheet.addCell(lab); //学院(系别)
        	 lab = new Label(4,i+1,list.get(i).getUPTName()); sheet.addCell(lab); //是否UPT所有
        	 lab = new Label(5,i+1,list.get(i).getIAM()); sheet.addCell(lab); //IAM User
        	 lab = new Label(6,i+1,list.get(i).getAK()); sheet.addCell(lab); //AK
        	 lab = new Label(7,i+1,list.get(i).getSK()); sheet.addCell(lab); //SK
         }
		} catch (Exception e) {
			e.printStackTrace();
        }
	}

	/**
	 * 将要查询的Mechanism进行封装
	 * @param mechanismName
	 * @param mechanismPid
	 * @return
	 */
	public Mechanism getMechanism(String mechanismName,String mechanismPid){
		Mechanism mechanism=new Mechanism();
		if("".equals(mechanismName)){
			System.out.println(1/0);
		}
		mechanism.setMechanismPid(mechanismPid);
		mechanism.setMechanismName(mechanismName);
		return mechanism;
	}
}
