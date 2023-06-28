package com.awslabplatform_admin.dao.resourceReview;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Template;
import com.awslabplatform_admin.entity.TmplReleaseRecord;

/**
 * 模板发布记录DAO接口
 * @author weixin
 * @version 2018-5-14
 */
@Repository("TmplReleaseDao")
public interface TmplReleaseDao {
	
	/**
	 * 插入发布审核流程记录
	 * @author weixin
	 * @version 2018年5月14日
	 * @param releaseRecord
	 * @return
	 */
	int addTmplReleaseRecord(TmplReleaseRecord releaseRecord);
	
	/**
	 * 获取模板发布审核记录
	 * @author weixin
	 * @version 2018年5月14日
	 * @param tmplId
	 * @return
	 */
	List<TmplReleaseRecord> listReleaseRecordInfo(String tmplId);
	
	
	/**
	 * 查询模板发布列表
	 * @author weix
	 * @date 2018年5月17日  
	 * @param info
	 * @return
	 */
	List<Template> listTmplReleaseTbData(PageInfo info);
	
	/**
	 * 查询模板发布列表总数
	 * @author weix
	 * @date 2018年5月17日  
	 * @param info
	 * @return
	 */
	int getTmplReleaseTbTotal(PageInfo info);
	
	/**
	 * 获取模板发布审核信息
	 * @author weix
	 * @date 2018年5月20日  
	 * @param condition
	 * @return
	 */
	Template getTmplReleaseReviewInfo(Map<String,Object> condition);
	
}
