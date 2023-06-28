package com.awslabplatform.dao.reportManage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.awslabplatform.dao.base.BaseDao;
import com.awslabplatform.entity.CredentialsAndRegion;
import com.awslabplatform.entity.Experiment;
import com.awslabplatform.entity.ExperimentInfo;
import com.awslabplatform.entity.Report;

/**
 * 实验Dao
 * @author lijf
 * date 2018年3月20日 下午2:14:01
 */
public interface ReportDao {
	/**
	 * 列出学生的实验报告
	 * @param studentId
	 * @return
	 */
	List<Report> listStudentReport(String studentId);
	/**
	 * 找出某个实验的报告
	 * @param experimentId
	 * @return
	 */
	Report findByExperimentId(@Param("experimentId")String experimentId,@Param("studentId")String studentId);
	/**
	 * 保存实验报告
	 * @param report
	 */
	void addReport(Report report);
	/**
	 * 修改实验报告
	 * @param report
	 */
	void updateReport(Report report);
}
