package com.awslabplatform.service.reportManage.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awslabplatform.dao.experimentManage.ExperimentDao;
import com.awslabplatform.dao.reportManage.ReportDao;
import com.awslabplatform.entity.Experiment;
import com.awslabplatform.entity.Report;
import com.awslabplatform.service.reportManage.ReportService;
import com.awslabplatform.util.UUIDUtils;
@Service("reportService")
public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportDao reportDao;
	@Autowired
	private ExperimentDao experimentDao;
	@Override
	public List<Report> listStudentReport(String studentId) {
		return reportDao.listStudentReport(studentId);
	}

	@Override
	public void addReport(Report report) {
		Report oldReport = reportDao.findByExperimentId(report.getExperimentId(),report.getStudentId());
		if(oldReport==null){
			Experiment experiment = experimentDao.selectByPrimaryKey(report.getExperimentId());
			report.setId(UUIDUtils.getUUID());
			report.setCreateTime(new Date());
			report.setExperimentName(experiment.getExperimentName());
			reportDao.addReport(report);
		}else{//一个实验只能有一份报告，已有报告，取出修改
			oldReport.setCreateTime(new Date());
			oldReport.setContent(report.getContent());
			reportDao.updateReport(oldReport);
		}
	}

	@Override
	public Report findByExperimentId(String experimentId,String studentId) {
		return reportDao.findByExperimentId(experimentId,studentId);
	}

}
