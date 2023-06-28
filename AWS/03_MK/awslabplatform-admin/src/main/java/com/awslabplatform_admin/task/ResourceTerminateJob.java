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
import com.awslabplatform_admin.dao.resourceReview.TmplReviewDao;
import com.awslabplatform_admin.dao.template.TmplInstanceDao;
import com.awslabplatform_admin.entity.AwsInstance;
import com.awslabplatform_admin.entity.QuartzJob;
import com.awslabplatform_admin.entity.TmplUseApply;
import com.awslabplatform_admin.service.amazonaws.KeyPairService;
import com.awslabplatform_admin.service.amazonaws.StackService;
import com.awslabplatform_admin.service.amazonaws.impl.StackServiceImpl;
import com.awslabplatform_admin.util.SpringBeanFactoryUtils;
import com.awslabplatform_admin.util.TimeUtils;

/**
 * 资源到期释放任务
 * @author weixin
 * @version 2018年4月12日
 */
@Component
public class ResourceTerminateJob implements Job {
	
    private static Logger log = LoggerFactory.getLogger(StackServiceImpl.class);
    
    /**堆栈service*/
    private StackService stackService;
    
    /**密钥service*/
    private KeyPairService keyPairService;
    
    /**模板审核dao*/
    private  TmplReviewDao  tmplReviewDao;
    
    /**模板实例dao*/
    private  TmplInstanceDao  tmplInstanceDao;
    
    /**任务dao*/
    private  QuartzJobDao quartzJobDao;
    
    @Override  
    public void execute(JobExecutionContext context){
    	stackService =(StackService)SpringBeanFactoryUtils.getBean("StackService");
    	keyPairService =(KeyPairService)SpringBeanFactoryUtils.getBean("KeyPairService");
    	tmplReviewDao =(TmplReviewDao)SpringBeanFactoryUtils.getBean("TmplReviewDao");
    	tmplInstanceDao =(TmplInstanceDao)SpringBeanFactoryUtils.getBean("TmplInstanceDao");
    	quartzJobDao =(QuartzJobDao)SpringBeanFactoryUtils.getBean("QuartzJobDao");
    	
    	//获取添加任务时的参数
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();  
        TmplUseApply tmplUseApply =  (TmplUseApply)dataMap.get("data");
        String stackName = tmplUseApply.getStackName();
        
        //判断堆栈是否是创建成功状态
        if (!Common.TEMPLATE_STACK_STATE_CREATE_COMPLETE.equals(tmplUseApply.getStackState())){
        	log.error( "task" + stackName + " " + tmplUseApply.getStackState());

        	return;
        }
        
		/**更新实例状态*/
		List<AwsInstance> awsInstances = tmplUseApply.getAwsInstances();
		for (AwsInstance awsInstance : awsInstances) {
			awsInstance.setInstanceState(Common.TEMPLATE_RESOURCE_INSTANCE_STATE_TERMINATED);
		}
		tmplInstanceDao.updateTmplInstances(awsInstances);
		
		String currentTime = TimeUtils.currentTime();//获取当前时间
		
		/**更新任务状态*/
		QuartzJob quartzJob = new QuartzJob();
		quartzJob.setJobName(tmplUseApply.getStackName());
		quartzJob.setJobEndTime(currentTime);
		quartzJob.setJobState(Common.STATE_DELETE);
		quartzJobDao.updateQuartzJob(quartzJob);
		
		/**更新堆栈信息*/
		TmplUseApply updateUseApply = new TmplUseApply();
		updateUseApply.setApplyId(tmplUseApply.getApplyId());
		updateUseApply.setStackState(Common.TEMPLATE_STACK_STATE_DELETE_COMPLETE);//设置删除状态
		updateUseApply.setActualUseLength(tmplUseApply.getUseTimeLength());//实际使用时长
		updateUseApply.setStackDeleteTime(currentTime);//删除堆栈时间
		tmplReviewDao.updateTmplUseApply(updateUseApply);
		
		/**删除密钥*/
		if ( !keyPairService.deleteKeyPair(tmplUseApply.getAwsInstances().get(0).getKeyName()) ) {
			log.error("task" + stackName+ " terminate error");
		}
		
        //释放资源
        log.warn( "task" + TimeUtils.currentTime() + ":" + stackName + " resource begin terminate");
		if (!stackService.deleteStack(tmplUseApply.getAwsStack())) {
			log.error("task" + stackName+ " terminate error");
			return;
		}
    }
}
