package com.awslabplatform.service.reportManage;

import java.util.List;

import com.awslabplatform.entity.Report;
/**
 * 实验报告接口
 * @author lijf
 * @date 2018年4月3日 下午4:51:04
 */
public interface ReportService {
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
	Report findByExperimentId(String experimentId,String studentId);
	/**
	 * 保存实验报告
	 * @param report
	 */
	void addReport(Report report);
}
