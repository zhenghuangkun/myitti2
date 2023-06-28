package com.awslabplatform_admin.service.courseManage;

import java.util.List;
import java.util.Map;

import com.awslabplatform_admin.entity.*;
import com.awslabplatform_admin.service.base.BaseService;

/**
* 实验组管理Service层
*
* @author  zhenghk
* @version 2018/3/15
*/
public interface ExperimentService extends BaseService<Experiment, String> {

	/**
	 * 查找所有课程实验信息
	 *
	 */
	public List<Experiment> listCourseExperiment();


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
	 * 解析json字符串并转成实验对象
	 * @param jsonStr
	 * @return
	 */
	public List<Experiment> parseJsonToExperiment(String jsonStr);

	/**
	 * 通过courseId查找tb_experiment
	 */

	public List<Experiment> getExperimentByCourseId(String courseId);

	/**
	 * 通过一个学生Id查找出所有的实验报告
	 */
	public List<Report> getCourseReportByStudentId(String courseId, String studentId);

	/**
	 * 通过学生ID和实验ID查找出一个实验报告
	 */
	public Report getCourseReportByStudentIdAndExperimentId(String experimentId, String studentId);

	/**
	 * 更新实验报告信息
	 */
	public void uodateCourseReport(Report report);
	
	/**
	 * 取得实验信息
	 * @param experiment
	 * @return
	 */
	public Experiment getExperimentInfo(String experimentId);
	
	/**
	 * 启动模板资源
	 */
	public Result startTmplResource(String templateId, String experimentId);
	
	/**
	 * 获取资源创建状态
	 */
	public Result getResourceCreateState(String templateId, String experimentId);
	
	/**
	 * 更新实验资源信息
	 * @param experiment
	 * @return
	 */
	public int updateExperimentResourceInfo(Experiment experiment);
	
	/**
	 * 释放模板资源
	 * @param templateId
	 * @param experimentId
	 * @return
	 */
	public Result terminationTmplResource(String templateId, String experimentId);
	
	/**
	 * 制作镜像
	 * @param tmplId
	 * @param experimentId
	 * @param instanceId
	 * @return
	 */
	public Result AMIMake(String tmplId, String experimentId, String instanceId);
	
	/**
	 * 取得实验的实例信息
	 * @param experimentId
	 * @return
	 */
	public List<AwsInstance> listExperimentInstance(String experimentId);

	/**
	 * CH version 2.0
	 * 取得实验区域
	 * @param experimentId 实验ID
	 * @return
	 */
	public String getExperimentRegion(String experimentId);
}
