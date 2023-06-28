package com.awslabplatform_admin.dao.resourceReview;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.TmplReivewRecord;
import com.awslabplatform_admin.entity.TmplUseApply;

/**
 * 模板试用DAO接口
 * @author weixin
 * @version 2018-4-2
 */
@Repository("TmplReviewDao")
public interface TmplReviewDao {
	
	/**
	 * 插入模板试用申请
	 * @author weixin
	 * @version 2018年4月7日
	 * @param tmplUseApply
	 * @return
	 */
	int insertTmplUseApply(TmplUseApply tmplUseApply);
	
	/**
	 * 插入审核流程记录
	 * @author weixin
	 * @version 2018年4月7日
	 * @param reivewRecord
	 * @return
	 */
	int insertTmplReviewRecord(TmplReivewRecord reivewRecord);
	
	/**
	 * 更新用户申请记录
	 * @author weixin
	 * @version 2018年4月7日
	 * @param tmplUseApply
	 * @return
	 */
	int updateTmplUseApply(TmplUseApply tmplUseApply);
	
	/**
	 * 获取模板申请信息
	 * @author weixin
	 * @version 2018年4月7日
	 * @param condition
	 * @return
	 */
	TmplUseApply getTmplUseApplyInfo(Map<String, Object> condition);
	
	/**
	 * 获取模板申请审核记录
	 * @author weixin
	 * @version 2018年4月7日
	 * @param applyId
	 * @return
	 */
	List<TmplReivewRecord> getApplyRecordInfo(@Param(value="applyId" ) String applyId);
	
	/**
	 * 获取模板申请总数
	 * @author weixin
	 * @version 2018年4月4日
	 * @param condition
	 * @return
	 */
	int getUseApplyTotal(Map<String, Object> condition);
	
	/**
	 * 查询模板申请列表数据
	 * @author weixin
	 * @version 2018年3月29日
	 * @param info 
	 * @return
	 */
	List<TmplUseApply> listTmplUseApplyTbData(PageInfo info);
	
	/**
	 * 查询模板申请列表总数
	 * @author weixin
	 * @version 2018年3月29日
	 * @param info
	 * @return
	 */
	int getTmplUseApplyTbTotal(PageInfo info);
	
}
