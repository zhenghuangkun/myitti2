package com.awslabplatform_admin.task;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.awslabplatform_admin.common.Common;
import com.awslabplatform_admin.dao.common.QuartzJobDao;
import com.awslabplatform_admin.dao.template.TmplInstanceDao;
import com.awslabplatform_admin.entity.AwsInstance;
import com.awslabplatform_admin.entity.Experiment;
import com.awslabplatform_admin.entity.QuartzJob;
import com.awslabplatform_admin.service.amazonaws.KeyPairService;
import com.awslabplatform_admin.service.amazonaws.StackService;
import com.awslabplatform_admin.service.amazonaws.impl.StackServiceImpl;
import com.awslabplatform_admin.service.courseManage.ExperimentService;
import com.awslabplatform_admin.util.SpringBeanFactoryUtils;
import com.awslabplatform_admin.util.TimeUtils;

/**
 * 资源到期释放任务
 * @author weixin
 * @version 2018年4月12日
 */
@Component
public class ExperimentResourceTerminateJob implements Job {
	
    private static Logger log = LoggerFactory.getLogger(StackServiceImpl.class);
    
    /**堆栈service*/
    private StackService stackService;
    
    /**模板实例dao*/
    private  TmplInstanceDao  tmplInstanceDao;
    
    /**任务dao*/
    private  QuartzJobDao quartzJobDao;
    
    /**
	 * 实验service
	 */
	private ExperimentService expService;
    
	/**密钥service*/
    private KeyPairService keyPairService;
	
    @Override  
    public void execute(JobExecutionContext context){
    	stackService =(StackService)SpringBeanFactoryUtils.getBean("StackService");
    	tmplInstanceDao =(TmplInstanceDao)SpringBeanFactoryUtils.getBean("TmplInstanceDao");
    	quartzJobDao =(QuartzJobDao)SpringBeanFactoryUtils.getBean("QuartzJobDao");
    	expService =(ExperimentService)SpringBeanFactoryUtils.getBean("ExperimentService");
    	keyPairService =(KeyPairService)SpringBeanFactoryUtils.getBean("KeyPairService");
    	
    	//获取添加任务时的参数
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();  
        Experiment experimentInfo =  (Experiment)dataMap.get("data");
        String stackName = experimentInfo.getStackName();
        
        //判断堆栈是否是创建成功状态
        if (!Common.TEMPLATE_STACK_STATE_CREATE_COMPLETE.equals(experimentInfo.getStackState())){
        	log.error( "task" + stackName + " " + experimentInfo.getStackState());

        	return;
        }
        
        //释放资源
        log.warn( "task" + TimeUtils.currentTime() + ":" + stackName + " resource begin terminate");
		if (!stackService.deleteStack(experimentInfo.getAwsStack())) {
			log.error("task" + stackName+ " terminate error");
			return;
		}
		
		
		experimentInfo.setStackState(Common.TEMPLATE_STACK_STATE_DELETE_COMPLETE);//设置删除状态
		String currentTime = TimeUtils.currentTime();//获取当前时间
		experimentInfo.setActualUseLength(experimentInfo.getRuntime());//实际使用时长
		experimentInfo.setStackDeleteTime(currentTime);//删除堆栈时间
		expService.updateExperimentResourceInfo(experimentInfo);
		
		/**更新实例状态*/
		List<AwsInstance> awsInstances = experimentInfo.getAwsInstances();
		for (AwsInstance awsInstance : awsInstances) {
			awsInstance.setInstanceState(Common.TEMPLATE_RESOURCE_INSTANCE_STATE_TERMINATED);
		}
		tmplInstanceDao.updateTmplInstances(awsInstances);
		
		
		/**删除密钥*/
		if ( !keyPairService.deleteKeyPair(experimentInfo.getAwsInstances().get(0).getKeyName()) ) {
			log.error("task" + stackName+ " terminate error");
		}
		
		
		/**更新任务状态*/
		QuartzJob quartzJob = new QuartzJob();
		quartzJob.setJobName(experimentInfo.getStackName());
		quartzJob.setJobEndTime(currentTime);
		quartzJob.setJobState(Common.STATE_DELETE);
		quartzJobDao.updateQuartzJob(quartzJob);
    }
}
