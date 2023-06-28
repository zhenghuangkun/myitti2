package com.awslabplatform.controller.experiment;


import com.amazonaws.services.cloudformation.model.StackEvent;
import com.amazonaws.services.ec2.model.Instance;
import com.awslabplatform.entity.*;
import com.awslabplatform.service.courseManage.CourseService;
import com.awslabplatform.service.experimentManage.ExperimentService;
import com.awslabplatform.service.reportManage.ReportService;
import com.awslabplatform.service.userManage.UserService;
import com.awslabplatform.util.DownloadUtil;
import com.awslabplatform.util.JsonTool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.awslabplatform.common.Common.*;

/**
 * 
 * @author lijf
 * @date 2018年3月24日 上午8:50:19
 */
@Controller
public class ExperimentController{
	/**
	 * log
	 */
	private static Logger log = LoggerFactory.getLogger(ExperimentController.class);
	@Autowired
	private ExperimentService experimentService;
	@Autowired
	private UserService userService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private CourseService courseService;
	
	/**
	 * CH version 2.0 将联盟功能更换成控制台实验
	 * 实验页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/running",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String running(String experimentId,Model model,HttpServletRequest request) throws Exception {
		Student currentUser = (Student) request.getSession().getAttribute("currentUser");
		Student student = userService.getStudentById(currentUser.getId());

		ExperimentRecord runningExperiment = experimentService.getRunningExperiment(student.getId());
		if(runningExperiment!=null&&!"".equals(runningExperiment)){
			//if(!experimentId.equals(student.getRunningExperiment().split("_")[0])){
			if(!experimentId.equals(runningExperiment.getExperimentId())){
				experimentId=runningExperiment.getExperimentId();
				model.addAttribute("message", "要先结束这个实验，才能做其它实验哦！");
			}
			//}

			/*判断是否已经在控制台实验，有则返回数据*/
			StudentExperment studentExperment =experimentService.runControlExperiment(student,
					AWS_IAM_IAMKIND,AWS_ACCOUNTPOOL_ISACTIVE,AWS_ACCOUNTPOOL_ISDELETE,AWS_ACCOUNT_ACCOUNTSTAUSE,AWS_ACCOUNT_ISACTIVE);
			if(studentExperment!=null&&studentExperment.getPoolUsed().equals(AWS_ACCOUNTPOOL_THIS_USED)){
				ExperimentRecord experimentRecord=experimentService.getRunningExperiment(student.getId());
				studentExperment.setEndTime(experimentRecord.getStopTime());
				model.addAttribute("studentExperment",studentExperment);
				request.getSession().setAttribute("controlExperimentId", experimentId);
			}
		}
		Experiment experiment = experimentService.selectByPrimaryKey(experimentId);
		//change by Lijf 2019/4/12 未查到实验时
		if(experiment==null){
			throw new Exception("无效的实验Id!");
		}
		if(experiment.getStartTime()!=null&&experiment.getEndTime()!=null){
			if(new Date().getTime()<experiment.getStartTime().getTime()||new Date().getTime()>experiment.getEndTime().getTime()){
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String startTime = dateFormat.format(experiment.getStartTime());
				String endTime = dateFormat.format(experiment.getEndTime());
				if(new Date().getTime()<experiment.getStartTime().getTime()){
					model.addAttribute("message", "本实验开放时间为：<br>"+startTime+" ~ "+endTime+"。");
				}
				if(new Date().getTime()>experiment.getEndTime().getTime()){
					model.addAttribute("message", "您已错过！本实验开放时间为：<br>"+startTime+" ~ "+endTime+"。");
					model.addAttribute("courseId", experiment.getCourseId());
				}
				//课程信息
				Course course = courseService.selectByPrimaryKey(experiment.getCourseId());
				model.addAttribute(course);
				User teacher = courseService.getTeacherByCourseId(experiment.getCourseId());
				model.addAttribute("teacher", teacher);
				//本课程的实验
				List<Experiment> experimentList = experimentService.listByCourseId(experiment.getCourseId());
				model.addAttribute(experimentList);
				return "coursedetail";
			}
		}
		Report report = reportService.findByExperimentId(experimentId, student.getId());
		Policys policys =experimentService.attachPoricy(experimentId,AWS_POLICY_STATUS);
		//用来区分实验是否是控制台实验
		if(policys!=null){
			model.addAttribute("controlExp",AWS_CONTROL_EXPERMENT);
		}

/*CH version 2.0 注销功能为联盟*/
//		if(experiment.getPolicyFileUrl()!=null&&!"".equals(experiment.getPolicyFileUrl().trim())){
//			String awsLoginUrl = experimentService.getAwsLoginUrl(student, experiment,AWS_ACCOUNTPOOL_USETYPE,AWS_IAM_IAMKIND,AWS_IAM_IAMSTATUS,USER_STATE,AWS_ACCOUNTPOOL_ISACTIVE,AWS_ACCOUNTPOOL_ISDELETE,AWS_ACCOUNT_ACCOUNTSTAUSE,AWS_ACCOUNT_ISACTIVE);
//				model.addAttribute("awsLoginUrl",awsLoginUrl);
//
//		}
		model.addAttribute("experiment", experiment);
		model.addAttribute("experimentId", experimentId);
		model.addAttribute("report", report);
		return "running";
	}
	
	/**
	 * 启动实验
	 * @return
	 */
	@RequestMapping(value = "/startUp",  method = RequestMethod.POST , produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String startUp(String experimentId,HttpServletRequest request) {
		Student  student = (Student) request.getSession().getAttribute("currentUser");
		student = userService.getStudentById(student.getId());
		ExperimentRecord runningExperiment = experimentService.getRunningExperiment(student.getId());
		String studentId=student.getId();
		//判断是否有正在进行的实验
		if(runningExperiment==null){//没有，创建资源
			runningExperiment = experimentService.startExp(student, experimentId,AWS_ACCOUNTPOOL_USETYPE,AWS_IAM_IAMKIND,AWS_ACCOUNTPOOL_ISACTIVE,AWS_ACCOUNTPOOL_ISDELETE,AWS_ACCOUNT_ACCOUNTSTAUSE,AWS_ACCOUNT_ISACTIVE);
			if(runningExperiment!=null&&runningExperiment.getReason()!=null){//如果出错返回原因
				return  runningExperiment.getReason();
			}
			student.setRunningExperiment(runningExperiment.getExperimentId());
			request.getSession().setAttribute("experimentId", experimentId);
			Experiment experiment = experimentService.selectByPrimaryKey(experimentId);
			TimerTask task=new TimerTask() {
				@Override
				public void run() {
					/* CH version 2.0*/
					experimentService.deleteStack(studentId,experimentId,AWS_ACCOUNTPOOL_USETYPE,AWS_IAM_IAMKIND,AWS_ACCOUNTPOOL_ISACTIVE,AWS_ACCOUNTPOOL_ISDELETE,AWS_ACCOUNT_ACCOUNTSTAUSE,AWS_ACCOUNT_ISACTIVE);
				}
			};
			Timer timer = new Timer();
			timer.schedule(task, 1000*60*experiment.getRuntime());
		}
		//取出数据
		return this.getResource(experimentId, request);
	}
	@RequestMapping(value = "/getResource",  method = RequestMethod.POST )
	@ResponseBody
	public String getResource(String experimentId,HttpServletRequest request) {
		Student  student = (Student) request.getSession().getAttribute("currentUser");
		ExperimentRecord runningExperiment = experimentService.getRunningExperiment(student.getId());
		//判断是否有正在进行的实验
		if(runningExperiment!=null){
			//取出数据
			String endtime = runningExperiment.getStopTime();
			List<Instance> instanceList = experimentService.updataInstanceBystudent(runningExperiment,AWS_ACCOUNTPOOL_USETYPE,AWS_IAM_IAMKIND,AWS_ACCOUNTPOOL_ISACTIVE,AWS_ACCOUNTPOOL_ISDELETE,AWS_ACCOUNT_ACCOUNTSTAUSE,AWS_ACCOUNT_ISACTIVE);
			HashMap<String, Object> data = new HashMap<String, Object>();
			data.put("instanceList", instanceList);
			data.put("endtime", endtime);
			String keyUrl = runningExperiment.getKeyUrl();
			data.put("keyUrl", keyUrl);
			if(runningExperiment.getAwsUrl()!=null&&!"".equals(runningExperiment.getAwsUrl().trim())){
				String awsUrl= runningExperiment.getAwsUrl();
				data.put("awsUrl", awsUrl);
			}
			data.put("windowsPassword", runningExperiment.getWindowsPassword());
			return JsonTool.toFastjson(data);
		}else{
			return "failed";
		}
	}
	@RequestMapping(value = "/deleteResource",  method = RequestMethod.POST , produces = "text/html; charset=UTF-8")
	@ResponseBody
	public void deleteResource(String experimentId,HttpServletRequest request,String studentId) {
		Student  student = (Student) request.getSession().getAttribute("currentUser");
		if(studentId==null||"".equals(studentId)){
			studentId=student.getId();
		}
		experimentService.deleteStack(studentId,experimentId,AWS_ACCOUNTPOOL_USETYPE,AWS_IAM_IAMKIND,AWS_ACCOUNTPOOL_ISACTIVE,AWS_ACCOUNTPOOL_ISDELETE,AWS_ACCOUNT_ACCOUNTSTAUSE,AWS_ACCOUNT_ISACTIVE);
		student.setRunningExperiment(null);
		request.getSession().removeAttribute("experimentId");
	}
	@RequestMapping(value = "/getStackEvents",  method = RequestMethod.POST )
	@ResponseBody
	public String getStackEvents(HttpServletRequest request) {
		Student  student = (Student) request.getSession().getAttribute("currentUser");
		ExperimentRecord runningExperiment = experimentService.getRunningExperiment(student.getId());
		//判断是否有正在进行的实验
		if(runningExperiment!=null){
			//取出数据
			List<StackEvent> stackEvents = experimentService.getStackEvents(runningExperiment,AWS_ACCOUNTPOOL_USETYPE,AWS_IAM_IAMKIND,AWS_ACCOUNTPOOL_ISACTIVE,AWS_ACCOUNTPOOL_ISDELETE,AWS_ACCOUNT_ACCOUNTSTAUSE,AWS_ACCOUNT_ISACTIVE);
			if(stackEvents==null||stackEvents.size()==0){
				//资源不存在
				deleteResource(runningExperiment.getExperimentId(), request, student.getId());
				return "failed";
			}
			return JsonTool.toFastjson(stackEvents);
		}else{
			request.getSession().removeAttribute("experimentId");
			return "failed";
		}
	}
	@RequestMapping(value = "/saveReport", method = RequestMethod.POST )
	@ResponseBody
	public String saveReport(String content,String experimentId,HttpServletRequest request) {
		Student  student = (Student) request.getSession().getAttribute("currentUser");
		if(content!=null&&!"".equals(content.trim())){
			content = content.replaceAll("&lt;", "<");
			content = content.replaceAll("&gt;", ">");
		}
		Report report = new Report();
		report.setContent(content);
		report.setExperimentId(experimentId);
		report.setStudentId(student.getId());
		reportService.addReport(report);
		return "保存成功";
	}
	@RequestMapping(value = "/downloadKey")
	public void downloadKey(String keyUrl,HttpServletRequest request,HttpServletResponse response){
		try {
			DownloadUtil.parseHeader("key.pem", request, response);
			DownloadUtil.download(keyUrl, response);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	@RequestMapping(value = "/windowsConn")
	public void windowsConn(String publicDnsName,HttpServletRequest request,HttpServletResponse response){
		try {
			DownloadUtil.parseHeader("windowsConnect.rdp", request, response);
			BufferedOutputStream bos = null;
			String fileContent="auto connect:i:1full address:s:"+publicDnsName+"username:s:Administrator";
			fileContent=new String(fileContent.getBytes(),"iso-8859-1");
			try {
				bos = new BufferedOutputStream(response.getOutputStream());
		        bos.write(fileContent.getBytes());    
			} catch (Exception e) {
				throw new RuntimeException(e);
			}finally{
	    		try {
					if(bos!=null) bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}  
		    }  
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * CH version 2.0
	 * 开启控制台实验
	 * @param experimentId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/startUpControlExp")
	@ResponseBody
	public StudentExperment startUpControlExp(String experimentId,HttpServletRequest request){
		Student student = (Student) request.getSession().getAttribute("currentUser");
		student = userService.getStudentById(student.getId());
		String studentId =student.getId();
		StudentExperment studentExperment=null;
		/*判断是否有正在进行的实验*/
		ExperimentRecord runningExperiment=experimentService.getRunningExperiment(student.getId());
		if(runningExperiment==null){
			//启动实验
			studentExperment =experimentService.startControlExp(student,experimentId,AWS_ACCOUNTPOOL_CONTROL_USETYPE,
					AWS_IAM_IAMKIND,AWS_IAM_CONTROL_IAMKIND,AWS_ACCOUNTPOOL_ISACTIVE,AWS_ACCOUNTPOOL_ISDELETE,AWS_ACCOUNT_ACCOUNTSTAUSE,AWS_ACCOUNT_ISACTIVE,AWS_ACCOUNTPOOL_USED,AWS_POLICY_STATUS,AWS_ACCOUNTPOOL_NOT_USED);
			if(studentExperment!=null&&studentExperment.getPoolUsed().equals(AWS_ACCOUNTPOOL_SUCCESS)){
				student.setRunningExperiment(studentExperment.getExperimentId());
				request.getSession().setAttribute("controlExperimentId", experimentId);
				TimerTask task=new TimerTask() {
					@Override
					public void run() {

						experimentService.stopControlExp(studentId,experimentId,
								AWS_IAM_IAMKIND,AWS_IAM_CONTROL_IAMKIND,AWS_ACCOUNTPOOL_NOT_USED,AWS_POLICY_STATUS);
					}
				};
				Timer timer = new Timer();
				timer.schedule(task, 1000*60*studentExperment.getRunningTime());
			}
		}else{
			studentExperment=new StudentExperment();
			studentExperment.setPoolUsed(AWS_CONTROL_ERROR);
		}

		//System.out.println("添加成功！");
		/* CH version 2.0 注销*/
		//Experiment experiment =experimentService.selectByPrimaryKey(experimentId);
		//courseService.addMycourse(student,experiment);
		//return ResultUtil.getResult(true, "success", "添加成功");
		return studentExperment;
	}

	/**
	 * CH version 2.0 停止实验
	 * @param experimentId
	 * @param request
	 */
	@RequestMapping(value = "/stopControlExp")
	@ResponseBody
	public void stopControlExperment(String experimentId,HttpServletRequest request){
		System.out.println("访问结束实验Controller "+new Date());
		Student student = (Student) request.getSession().getAttribute("currentUser");
		//student = userService.getStudentById(student.getId());

			//student = userService.getStudentById(student.getId());
		/*CredentialsAndRegion credentialsAndRegion =experimentService.selectProof(student,AWS_ACCOUNTPOOL_USETYPE,
				AWS_IAM_IAMKIND,AWS_IAM_IAMSTATUS,AWS_ACCOUNTPOOL_ISACTIVE,AWS_ACCOUNTPOOL_ISDELETE,AWS_ACCOUNT_ACCOUNTSTAUSE,AWS_ACCOUNT_ISACTIVE);*/
			/*停止实验*/
			experimentService.stopControlExp(student.getId(),experimentId,
					AWS_IAM_IAMKIND,AWS_IAM_CONTROL_IAMKIND,AWS_ACCOUNTPOOL_NOT_USED,AWS_POLICY_STATUS);


		request.getSession().removeAttribute("controlExperimentId");
		//request.getSession().removeAttribute("studentExperiment");
	}
}
