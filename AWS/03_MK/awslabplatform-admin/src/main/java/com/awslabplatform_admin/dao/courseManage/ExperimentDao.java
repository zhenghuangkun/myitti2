package com.awslabplatform_admin.dao.courseManage;

import java.util.List;
import java.util.Map;

import com.awslabplatform_admin.dao.base.BaseDao;
import com.awslabplatform_admin.entity.*;
import org.apache.ibatis.annotations.Param;

/**
 * 实验管理DAO
 * @author zhenghk
 *
 */
public interface ExperimentDao extends BaseDao<Experiment, String>{
	/**
	 * 查找所有课程实验信息
	 *
	 */
	public List<Experiment> listCourseExperiment();

	/**
	 * 分页查询课程实验信息
	 * @param pageInfo
	 * @return
	 */
	public List<Experiment> listCourseExperimentInfo(PageInfo pageInfo);

	/**
	 * 统计课程实验数量(分页)
	 * @param pageInfo
	 * @return
	 */
	public int getCourseExperimentInfoTotal(PageInfo pageInfo);

    /**
     *
     * 查询实验信息
     * @param PageInfo
     * @return
     **/
	public void getExperimentPageInfo(PageInfo pageInfo);

	/**
	 * 添加多条实验信息
	 * @param experimentList
	 * @return
	 */
	public int addMultiExperiment(List<Experiment> experimentList);

	/**
	 * 更新实验信息
	 * @param experimentList
	 * @return
	 */
	public int updateExperiment (Experiment experiment);

	/**
	 * 更新多条实验信息
	 * @param experimentList
	 * @return
	 */
	public int updateMultiExperiment(List<Experiment> experimentList);
    /**
     *
     * 删除实验
     * @param param
     * @return
     **/
	public void deleteExperimentByExperimentId(String experimentId);

	/**
    *
    * 删除多个实验信息
    * @param param
    * @return
    **/
	public int deleteMultiExperimentByExperimentId(String[] experimentArray);

	/**
	 * 通过courseId查找tb_experiment
	 */

	public List<Experiment> getExperimentByCourseId(String courseId);

	/**
	 * 通过一个学生Id、课程Id查找出所有的实验报告
	 */
	public List<Report> getCourseReportByStudentId(@Param("courseId") String courseId, @Param("studentId") String studentId);

	/**
	 * 通过学生ID和实验ID查找出一个实验报告
	 */
	public Report getCourseReportByStudentIdAndExperimentId(@Param("experimentId") String experimentId,@Param("studentId") String studentId);

	/**
	 * 更新实验报告信息
	 */
	public void uodateCourseReport(Report report);
	
	/**
	 * 取得实验信息
	 * @param experimentId
	 * @return
	 */
	public Experiment getExperimentInfo(String experimentId);
	
	/**
	 * 更新实验资源信息
	 * @param experiment
	 * @return
	 */
	public int updateExperimentResourceInfo(Experiment experiment);
	
	/**
	 * 取得实验的实例信息
	 * @param experimentId
	 * @return
	 */
	public List<AwsInstance> listExperimentInstance(String experimentId);

	/**CH version 2.0
	 * 根据实验ID获取实验区域
	 * @param experimentId
	 * @return
	 */
	public String getExperimentRegion(@Param("experimentId") String experimentId);
	
}
