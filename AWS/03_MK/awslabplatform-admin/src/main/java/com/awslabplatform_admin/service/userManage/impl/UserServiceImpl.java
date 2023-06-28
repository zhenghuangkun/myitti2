package com.awslabplatform_admin.service.userManage.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.omg.CORBA.SystemException;
import org.springframework.stereotype.Service;

import com.awslabplatform_admin.common.Common;
import com.awslabplatform_admin.common.ShiroPermissionConstant;
import com.awslabplatform_admin.common.message.IamMessage;
import com.awslabplatform_admin.common.message.UserMessage;
import com.awslabplatform_admin.dao.userManage.UserDao;
import com.awslabplatform_admin.entity.Mechanism;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Result;
import com.awslabplatform_admin.entity.Student;
import com.awslabplatform_admin.entity.User;
import com.awslabplatform_admin.service.awsAccountManage.AwsIamsService;
import com.awslabplatform_admin.service.base.impl.BaseServiceImpl;
import com.awslabplatform_admin.service.mechanismManage.MechanismService;
import com.awslabplatform_admin.service.systemManage.RoleService;
import com.awslabplatform_admin.service.userManage.UserService;
import com.awslabplatform_admin.util.CommonDataUtil;
import com.awslabplatform_admin.util.ExportChannelExcel;
import com.awslabplatform_admin.util.Md5Util;
import com.awslabplatform_admin.util.ResultUtil;
import com.awslabplatform_admin.util.ShiroUtil;
import com.awslabplatform_admin.util.UUIDUtils;

/**
 * 用户管理service 接口实现类
 *
 * @author WEIX
 */
@Service("UserService")

public class UserServiceImpl extends BaseServiceImpl<UserDao, User, Integer> implements UserService {

	/**
     * 查询根据不同的用户类型查询用户管理的数据，显示在表格的列表上查询分页信息
     * @param pageInfo
     **/
	@Override
	public void getUserManageData(PageInfo pageInfo) {
		List<User> list=baseDao.selectUserManageData(pageInfo);
		for (int i = 0; i < list.size(); i++) {
			if(ShiroUtil.getCurrentUser().getRoleType()==Common.ROLE_TYPE_ADMIN){
				list.get(i).setCurrentUserId(list.get(i).getUserId());
				list.get(i).setCurrentCreateBy(list.get(i).getRealName());
			}else{
				list.get(i).setCurrentUserId(ShiroUtil.getCurrentUser().getUserId());
				list.get(i).setCurrentCreateBy(ShiroUtil.getCurrentUser().getRealName());
			}
		}
		/*查询分页数据*/
		pageInfo.setData(list);
		/*查询总数*/
		int total = baseDao.countUserManage(pageInfo);
		pageInfo.setRecordsTotal(total);
		pageInfo.setRecordsFiltered(total);//过滤记录数，dataTable必要参数
	}

	/**
	 * 添加用户数据
	 */
	@Override
	public Result addUserData(User user,AwsIamsService awsIamsService,RoleService roleService) {
		String userId=UUIDUtils.getUUID();
		user.setUserId(userId);
		user.setUserState(Common.USER_STATE_ACTIVE);
		user.setCreateBy(ShiroUtil.getCurrentUser().getRealName());
		user.setCreateTime(new Date());
		user.setUserPwd(Md5Util.md5(Common.USERPWD,ShiroPermissionConstant.PWDSalt));
		user.setPhoneNum(user.getPhoneNum().trim());
		user.setEmail(user.getEmail().trim());
		if(countUserData(user)>0){//判断邮箱或电话号码是否存在
			return ResultUtil.getResult(false, UserMessage.EXIST_USER_DATA);
		}else{
			insert(user);//添加用户
			/*AwsIams awsIamsw= new AwsIams();
			awsIamsw.setId(user.getIAM());
			awsIamsw.setIsUsed(Common.IAM_ALREADY_ISUSER);
			awsIamsService.updateByPrimaryKeySelective(awsIamsw);//将已经用掉的IAM设置为1*/
			
			HashMap<String, Object> condition=new HashMap<String, Object>();
			condition.put("userId", userId);
			condition.put("roleId", user.getRoleId());
			roleService.insertRoleUser(condition);//添加用户与角色的关联数据
			return ResultUtil.getResult(true, UserMessage.ADD_USER_SUCCES);
		}
	}

	/**
	 * 编辑用户数据
	 */
	@Override
	public Result editUser(User user, AwsIamsService awsIamsService,RoleService roleService) {
		Map<String,Object> map  = new HashMap<String, Object>();
		map.put("userId", user.getUserId());
		map.put("roleId", user.getRoleId());
		roleService.updateRoleUser(map);
		if(user.getCopyPhoneNum().equals(user.getPhoneNum().trim()) && user.getCopyEmail().equals(user.getEmail().trim())){
			updateByPrimaryKeySelective(user);
			/**
			 * 动态更改subject的用户属性 aws2.0
			 */
			if(ShiroUtil.getCurrentUser().getUserId().equals(user.getUserId())){
				User getUser=new User();
				getUser.setUserId(user.getUserId());
				ShiroUtil.setUser(selectIdUserData(getUser));
			}
			
			return ResultUtil.getResult(true, IamMessage.EDIT_IAM_SUCCESS);
		}else{
			User userTrue=new User();
			if(user.getCopyPhoneNum().equals(user.getPhoneNum().trim()) && !user.getCopyEmail().equals(user.getEmail().trim())){
				userTrue.setPhoneNum("");
				userTrue.setEmail(user.getEmail().trim());
			}else if(!user.getCopyPhoneNum().equals(user.getPhoneNum().trim()) && user.getCopyEmail().equals(user.getEmail().trim())){
				userTrue.setPhoneNum(user.getPhoneNum().trim());
				userTrue.setEmail("");
			}else if(!user.getCopyPhoneNum().equals(user.getPhoneNum().trim()) && !user.getCopyEmail().equals(user.getEmail().trim())){
				userTrue.setPhoneNum(user.getPhoneNum().trim());
				userTrue.setEmail(user.getEmail().trim());
			}
			if(countUserData(userTrue)>0){
				return ResultUtil.getResult(false, UserMessage.EDIT_USERRealName_ERROR);
			}else{
				updateByPrimaryKeySelective(user);
				/**
				 * 动态更改subject的用户属性 aws2.0
				 */
				if(ShiroUtil.getCurrentUser().getUserId().equals(user.getUserId())){
					User getUser=new User();
					getUser.setUserId(user.getUserId());
					ShiroUtil.setUser(selectIdUserData(getUser));
				}
				
				return ResultUtil.getResult(true, UserMessage.EDIT_USER_SUCCESS);
			}
		}
	}
	
	/**
	 * 编辑学生数据
	 * @param student
	 * @return
	 */
	@Override
	public Result editUserStudent(Student student){
		if(student.getCopyStuId().equals(student.getStuId().trim()) && student.getCopyPhoneNum().equals(student.getPhoneNum().trim()) && 
				   student.getCopyEmail().equals(student.getEmail().trim())){
					
			editUserStu(student);
			return ResultUtil.getResult(true, UserMessage.EDIT_USER_SUCCESS);
		}else{
			Student floag=new Student();
			if(student.getCopyStuId().equals(student.getStuId().trim()) && !student.getCopyPhoneNum().equals(student.getPhoneNum().trim()) && !student.getCopyEmail().equals(student.getEmail().trim())){
				floag.setStuId("");
				floag.setPhoneNum(student.getPhoneNum().trim());
				floag.setEmail(student.getEmail().trim());
			}else if(!student.getCopyStuId().equals(student.getStuId().trim()) && student.getCopyPhoneNum().equals(student.getPhoneNum().trim()) && !student.getCopyEmail().equals(student.getEmail().trim())){
				floag.setStuId(student.getStuId().trim());
				floag.setPhoneNum("");
				floag.setEmail(student.getEmail().trim());
			}else if(!student.getCopyStuId().equals(student.getStuId().trim()) && !student.getCopyPhoneNum().equals(student.getPhoneNum().trim()) && student.getCopyEmail().equals(student.getEmail().trim())){
				floag.setStuId(student.getStuId().trim());
				floag.setPhoneNum(student.getPhoneNum().trim());
				floag.setEmail("");
			}else if(student.getCopyStuId().equals(student.getStuId().trim()) && student.getCopyPhoneNum().equals(student.getPhoneNum().trim()) && !student.getCopyEmail().equals(student.getEmail().trim())){
				floag.setStuId("");
				floag.setPhoneNum("");
				floag.setEmail(student.getEmail().trim());
			}else if(student.getCopyStuId().equals(student.getStuId().trim()) && !student.getCopyPhoneNum().equals(student.getPhoneNum().trim()) && student.getCopyEmail().equals(student.getEmail().trim())){
				floag.setStuId("");
				floag.setPhoneNum(student.getPhoneNum().trim());
				floag.setEmail("");
			}else if(!student.getCopyStuId().equals(student.getStuId().trim()) && student.getCopyPhoneNum().equals(student.getPhoneNum().trim()) && student.getCopyEmail().equals(student.getEmail().trim())){
				floag.setStuId(student.getStuId().trim());
				floag.setPhoneNum("");
				floag.setEmail("");
			}else if(!student.getCopyStuId().equals(student.getStuId().trim()) && !student.getCopyPhoneNum().equals(student.getPhoneNum().trim()) && !student.getCopyEmail().equals(student.getEmail().trim())){
				floag.setStuId(student.getStuId().trim());
				floag.setPhoneNum(student.getPhoneNum().trim());
				floag.setEmail(student.getEmail().trim());
			}
			if(countUserStuData(floag)>0){
				return ResultUtil.getResult(false, UserMessage.EXIST_USERSTUID_DATA);
			}else{
				editUserStu(student);
				return ResultUtil.getResult(true, UserMessage.EDIT_USER_SUCCESS);
			}
		}		
	}

	/**
	 * 下载Excel模板
	 */
	@Override
	public Result downloadExcelModel(HttpServletRequest request,HttpServletResponse response) {
		try {
			Label lab = null;
	        //取得根目录路径，将当前的excel表格存入工程的路径下
	        String contextPath = request.getServletContext().getRealPath("/");
			String rootPath = contextPath + "/static/exportDoc";
	        String replace = rootPath.replace("\\", "/");
	        new File(replace).mkdirs();
	    	String fileName="批量导入数据模板.xls";
	    	String[] title = {"学 号","姓 名","学 校","学院(系别)","专 业","年 级","班 级","电 话","邮 箱","出生年月","居住地址"};
	    	String filePath=rootPath+"/"+fileName;
			WritableWorkbook workbook=Workbook.createWorkbook(new File(filePath));
			WritableSheet sheet = workbook.createSheet(fileName, 0);	  //单元格
			ExportChannelExcel.jxlUtil(lab,title,sheet,workbook);
	    	 for (int j = 0; j < title.length; j++) {
	    		 sheet.setColumnView(j, 25);//设置列宽
	    		 if(j<1){sheet.setRowView(j+1,380);}
	    	 }
	    	 lab = new Label(0,1,"1250303029");sheet.addCell(lab); // 学号
	    	 lab = new Label(1,1,"张三");sheet.addCell(lab); // 姓名
	    	 lab = new Label(2,1,"厦门大学");sheet.addCell(lab); // 学校
	    	 lab = new Label(3,1,"厦门大学-数学与计算机科学学院");sheet.addCell(lab); // 学院(系别)
	    	 lab = new Label(4,1,"计算机科学与技术");sheet.addCell(lab); // 专业
	    	 lab = new Label(5,1,"大一");sheet.addCell(lab); // 年级
	    	 lab = new Label(6,1,"1班");sheet.addCell(lab); // 班级
	    	 lab = new Label(7,1,"1804610XXXX");sheet.addCell(lab); // 电话
	    	 lab = new Label(8,1,"71656***@qq.com");sheet.addCell(lab); // 邮箱
	    	 lab = new Label(9,1,"2000-11-11");sheet.addCell(lab); // 出身年月
	    	 lab = new Label(10,1,"厦门***201号21栋6#612室");sheet.addCell(lab); // 居住地址
			 ExportChannelExcel.writeOrClose(workbook);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultUtil.getResult(true, UserMessage.DOWNLOAD_EXCEL);
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
	public List<Student> insertExcelData(Sheet sheet,MechanismService mechanismService,HashMap<String, Object> mag,HttpServletRequest request) throws SystemException{
		List<Student> tableList = new ArrayList<Student>();
		 for (int i = 1; i < sheet.getRows(); i++) {
			 String stuId=sheet.getCell(0, i).getContents();//学号
			 String email=sheet.getCell(8, i).getContents();//邮箱
			 String org=sheet.getCell(2, i).getContents();//学校
			if("".equals(stuId.trim()) && "".equals(email.trim()) && "".equals(org.trim())){continue;}
			try{
				Student student = new Student();
				student.setStuId(stuId.trim());//学号
				String phoneNum=sheet.getCell(7, i).getContents();
				student.setPhoneNum(phoneNum.trim());//电话
				student.setEmail(email.trim());//邮箱
				if(countUserStuData(student)>0 || "".equals(stuId.trim()) || "".equals(email.trim())){
					System.out.println(1/0);
				}
				student.setUserPwd(Md5Util.md5((stuId.trim()).substring((stuId.trim()).length()- 6),ShiroPermissionConstant.PWDSalt));//密码获取学号后六位
				String realName=sheet.getCell(1, i).getContents();
				student.setRealName(realName.trim());//姓名
				
				String departmentId=sheet.getCell(3, i).getContents();
				String major=sheet.getCell(4, i).getContents();
				String grade=sheet.getCell(5, i).getContents();
				String classes=sheet.getCell(6, i).getContents();
				String mechanismId=mechanismService.getMechanismId(getMechanism(org.trim(),null));
				String mechanismIdo=mechanismService.getMechanismId(getMechanism(departmentId.trim(),mechanismId));
				String mechanismIdt=mechanismService.getMechanismId(getMechanism(major.trim(),mechanismIdo));
				String mechanismIdf=mechanismService.getMechanismId(getMechanism(grade.trim(),mechanismIdt));
				String mechanismIdx=mechanismService.getMechanismId(getMechanism(classes.trim(),mechanismIdf));
				if(mechanismId==null || mechanismIdo==null || mechanismIdt==null || mechanismIdf==null || mechanismIdx==null){
					System.out.println(1/0);
				}else{
					student.setSchoolId(mechanismId);//学校
					student.setMechanismId(mechanismIdo);// 学院(系别)
					student.setMajor(mechanismIdt);//专业
					student.setGrade(mechanismIdf);//年级
					student.setClasses(mechanismIdx);//班级
				}
				
				if(!(CommonDataUtil.isMobile(phoneNum.trim())) || !(CommonDataUtil.checkEmail(email.trim()))){
					System.out.println(1/0);
				}
				
				String birthday=sheet.getCell(9, i).getContents();
				if("".equals(birthday.trim()) || null==birthday.trim()){
					student.setBirthday(birthday.trim());//出生年月
				}else if((birthday.trim()).contains("/")){//判断出生日期是否是带"/"
					String dateString=(birthday.trim()).replaceAll("/","-");
					if(CommonDataUtil.isValidDate(dateString)){
						student.setBirthday(dateString);//出生年月
					}else{
						System.out.println(1/0);
					}
				}else{
					if(CommonDataUtil.isValidDate(birthday.trim())){//判断出生日期是否正确
						student.setBirthday(birthday.trim());//出生年月
					}else{
						System.out.println(1/0);
					}
				}
				String address=sheet.getCell(10, i).getContents();
				student.setAddress(address.trim());//地址
				student.setUserState(Common.STUDENT_STATE_ACTIVE);//可用
				student.setId(UUIDUtils.getUUID());

				addUserStu(student);//添加数据

 				mag.put("resultMessage", "数据导入成功！");
 			}catch(Exception a){
 				Student students = new Student();
 				students.setStuId(sheet.getCell(0, i).getContents());//学号
 				students.setRealName(sheet.getCell(1, i).getContents());//姓名
 				students.setSchoolId(sheet.getCell(2, i).getContents());//学校
 				students.setMechanismId(sheet.getCell(3, i).getContents());// 学院(系别)
 				students.setMajor(sheet.getCell(4, i).getContents());//专业
 				students.setGrade(sheet.getCell(5, i).getContents());//年级
 				students.setClasses(sheet.getCell(6, i).getContents());//班级
 				students.setPhoneNum(sheet.getCell(7, i).getContents());//电话
 				students.setEmail(sheet.getCell(8, i).getContents());//邮箱
 				students.setBirthday(sheet.getCell(9, i).getContents());//出生年月
 				students.setAddress(sheet.getCell(10, i).getContents());//地址
				tableList.add(students);
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
	public void errorExportExcel(List<Student> tableList,HttpServletRequest request) {
		try {
			Label lab = null;
			String fileName="导出异常数据.xls";
			String contextPath = request.getServletContext().getRealPath("/");
	    	String urlRoot = contextPath + "static/exportDoc/";
		    new File(urlRoot).mkdirs();
			String[] title={"学 号","姓 名","学 校","学院(系别)","专 业","年 级","班 级","电 话","邮 箱","出生年月","居住地址"};
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
	public static void errorExcelData(String[] title,List<Student> list,Label lab,WritableSheet sheet){
		try {
		 for (int j = 0; j < title.length; j++) {sheet.setColumnView(j, 25);}//设置列宽
		 for(int i=0;i<list.size();i++){
			 sheet.setRowView(i+1,380);
        	 lab = new Label(0,i+1,list.get(i).getStuId());sheet.addCell(lab); //学 号
        	 lab = new Label(1,i+1,list.get(i).getRealName()); sheet.addCell(lab); //姓 名
        	 lab = new Label(2,i+1,list.get(i).getSchoolId()); sheet.addCell(lab); //学 校
        	 lab = new Label(3,i+1,list.get(i).getMechanismId()); sheet.addCell(lab); //学院(系别)
        	 lab = new Label(4,i+1,list.get(i).getMajor()); sheet.addCell(lab); //专 业
        	 lab = new Label(5,i+1,list.get(i).getGrade()); sheet.addCell(lab); //年 级
        	 lab = new Label(6,i+1,list.get(i).getClasses()); sheet.addCell(lab); //班 级
        	 lab = new Label(7,i+1,list.get(i).getPhoneNum()); sheet.addCell(lab); //电 话
        	 lab = new Label(8,i+1,list.get(i).getEmail()); sheet.addCell(lab); //邮 箱
        	 lab = new Label(9,i+1,list.get(i).getBirthday()); sheet.addCell(lab); //出生年月
        	 lab = new Label(10,i+1,list.get(i).getAddress()); sheet.addCell(lab); //居住地址
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
		mechanism.setMechanismPid(mechanismPid);
		mechanism.setMechanismName(mechanismName);
		return mechanism;
	}
	
	/**
	 * 通过用户名获取用户对象
	 */
	@Override
	public User getByUserEmail(String email){
		return baseDao.getByUserEmail(email);
	}

	@Override
	public User getByUserPhone(String phoneNum){
		return baseDao.getByUserPhone(phoneNum);
	}

	/**
	 * 通过评论表的userId获取学生
	 */
	@Override
	public Student getStudentById(String userId){
		return baseDao.getStudentById(userId);
	}

	/**
	 * 通过groupId查找所有userId（学生Id）
	 */
	public List<String> getUserIdbyGroupId(String groupId){
		return baseDao.getUserIdbyGroupId(groupId);
	}

	/**
	 *  假删除用户管理数据信息
	 * @param id
	 */
	@Override
	public boolean deleteUserData(Map<String,Object> map) {
		return baseDao.deleteUserData(map);
	}

	/**
	 * 根据条件获取用户平台管理员的数据，将数据显示在编辑的页面上
	 */
	@Override
	public User selectIdUserData(User user) {
		return baseDao.selectIdUserData(user);
	}

	/**
	 * 查询添加姓名是否已经存在
	 */
	@Override
	public int countUserData(User user) {
		return baseDao.countUserData(user);
	}

	/**
	 * 查询用户管理学生数据，显示在表格的列表上查询分页信息
	 */
	@Override
	public void getUserStuPageInfo(PageInfo pageInfo) {
		/*查询分页数据*/
		pageInfo.setData(baseDao.selectUserStuManageData(pageInfo));
		/*查询总数*/
		int total = baseDao.countUserStuManage(pageInfo);
		pageInfo.setRecordsTotal(total);
		pageInfo.setRecordsFiltered(total);//过滤记录数，dataTable必要参数

	}


	/**
	 * 查询添加学生学号是否已经存在
	 * @param user
	 */
	@Override
	public int countUserStuData(Student student) {
		return baseDao.countUserStuData(student);
	}

	/**
	 * 添加学生
	 */
	@Override
	public boolean addUserStu(Student student) {
		return baseDao.addUserStu(student);
	}

	/**
	 * 假删除激活用户管理学生的数据信息
	 */
	@Override
	public boolean deleteUserStuData(Map<String,Object> map) {
		return baseDao.deleteUserStuData(map);
	}

	/**
	 * 更新用户密码
	 * @param user
	 */
	public void updateUserPwd(User user){
		baseDao.updateUserPwd(user);
	}
	
	/**
	 * 根据条件获取学生的数据，将数据显示在编辑的页面上
	 */
	@Override
	public Student selectIdUserStuData(Student student) {
		return baseDao.selectIdUserStuData(student);
	}

	/**
	 * 编辑学生
	 * @param student
	 * @return
	 */
	@Override
	public boolean editUserStu(Student student) {
		return baseDao.editUserStu(student);
	}

	@Override
	public void getUserStuexperiment(PageInfo pageInfo) {
		/*查询分页数据*/
		pageInfo.setData(baseDao.selectUserStuexperiment(pageInfo));
		/*查询总数*/
		int total = baseDao.countUserManageStuexperiment(pageInfo);
		pageInfo.setRecordsTotal(total);
		pageInfo.setRecordsFiltered(total);//过滤记录数，dataTable必要参数
	}

	/**
	 * 查询出所有的院系 accountId 和院系名称
	 */
	@Override
	public List<User> getAllDepartments(Map<String, Object> map) {
		return baseDao.seleAllDepartments(map);
	}

	/**
	 * 获取用户中的数据是否存在院系和学校的机构管理是否被用了
	 * @param map
	 * @return
	 */
	@Override
	public int countUserAccount(Map<String,Object> map){
		return baseDao.countUserAccount(map);
	}
	
	@Override
	public int countStudentId(Map<String,Object> map){
		return baseDao.countStudentId(map);
	}

}
