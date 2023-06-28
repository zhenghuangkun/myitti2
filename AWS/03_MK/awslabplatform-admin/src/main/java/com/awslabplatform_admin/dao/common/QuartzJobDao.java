package com.awslabplatform_admin.dao.common;


import java.util.Map;

import org.springframework.stereotype.Repository;

import com.awslabplatform_admin.dao.base.BaseDao;
import com.awslabplatform_admin.entity.QuartzJob;

/**
 * 文件信息管理DAO接口
 * @author weixin
 * @version 2018-3-23
 */
@Repository("QuartzJobDao")
public interface QuartzJobDao extends BaseDao<QuartzJob, String> {
	
	/**
	 * 插入QuartzJob
	 * @author weixin
	 * @version 2018年4月12日
	 * @param quartzJob
	 * @return
	 */
	public int insertQuartzJob(QuartzJob quartzJob);
	
	/**
	 * 更新QuartzJob
	 * @author weixin
	 * @version 2018年4月12日
	 * @param quartzJob
	 * @return
	 */
	public int updateQuartzJob(QuartzJob quartzJob);
	
	/**
	 * 查询QuartzJob
	 * @author weixin
	 * @version 2018年4月12日
	 * @param condition
	 * @return
	 */
	public QuartzJob getQuartzJob(Map<String,Object> condition);
}
