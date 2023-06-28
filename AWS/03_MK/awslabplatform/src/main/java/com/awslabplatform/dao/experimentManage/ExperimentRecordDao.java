package com.awslabplatform.dao.experimentManage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.awslabplatform.dao.base.BaseDao;
import com.awslabplatform.entity.CredentialsAndRegion;
import com.awslabplatform.entity.Experiment;
import com.awslabplatform.entity.ExperimentInfo;
import com.awslabplatform.entity.ExperimentRecord;

/**
 * 实验记录Dao
 * @author lijf
 * date 2018年5月28日 下午17:55:01
 */
public interface ExperimentRecordDao{
	/**
	 * 添加一条实验记录
	 * @param runningExperiment
	 */
	void addOne(ExperimentRecord experimentRecord);
	/**
	 * 修改（只修改结束时间和状态0正在实验;1已结束）
	 * @param runningExperiment
	 */
	void update(ExperimentRecord runningExperiment);
	/**
	 * 查找学生正在进行的实验
	 * @param studentId
	 * @return
	 */
	ExperimentRecord getRunning(@Param("studentId")String studentId);
}
