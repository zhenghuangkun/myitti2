package com.awslabplatform_admin.controller.resourceReview;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Result;
import com.awslabplatform_admin.entity.Template;
import com.awslabplatform_admin.entity.TmplReivewRecord;
import com.awslabplatform_admin.entity.TmplReleaseRecord;
import com.awslabplatform_admin.entity.TmplUseApply;
import com.awslabplatform_admin.service.resourceReview.TmplReleaseService;
import com.awslabplatform_admin.service.resourceReview.TmplReviewService;
import com.awslabplatform_admin.service.template.TemplateService;

@Controller
@RequestMapping("/tmplreview")
public class TmplReviewController {
    /**模板service*/
    @Autowired
    private  TemplateService  templateService;
	/**
	 * 测试申请service
	 */
	@Autowired
	private TmplReviewService tmplReviewService;
	
	/**
	 * 发布审核service
	 */
	@Autowired
	private TmplReleaseService tmplReleaseService;
	/**
	 * 教学资源审核->模板审核
	 * @return
	 */
	@RequestMapping(value = "/list/{url}",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String pageTmplReviewList(@PathVariable(value = "url") String url) {
		return "/resourceReview/" + url;
	}
	
	/**
	 * 查询申请列表信息
	 * @author weixin
	 * @version 2018年4月4日
	 * @return
	 */
	@RequestMapping(value = "/applyList",  method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PageInfo tmplApplyListInfo(String keyword, Integer reviewState, int start, int length, int draw) {
		return tmplReviewService.listTmplReviewInfo(reviewState, keyword, start, length, draw);
	}
	
	/**
	 * 试用审核/查看模板
	 * @return
	 */
	@RequestMapping(value = "/reviewinfo",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String pageTmplReviewInfo(Model model, String tmplId, String applyId) {
		/*查询模板信息*/
		Template tmplate = templateService.getTemplateInfo(tmplId);
		TmplUseApply tmplUseApply = tmplReviewService.getTmplUseApplyInfo(applyId);
		/*设置返回页面数据*/
		model.addAttribute("tmplate", tmplate);//模板信息
		model.addAttribute("tmplUseApply", tmplUseApply);//模板申请使用信息
		return "/resourceReview/tmplReviewInfo";
	}
	
	/**
	 * 模板审核
	 * @author weixin
	 * @version 2018年4月7日
	 * @param operation	通过或拒绝
	 * @param remark	审核意见
	 * @return
	 */
	@RequestMapping(value = "/review",  method = RequestMethod.POST  , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result tmplReview(@RequestBody TmplReivewRecord reivewRecord) {
		return tmplReviewService.addTmplReivew(reivewRecord);
	}
	
	
	/**
	 * 查询模板发布申请列表
	 * @author weix
	 * @date 2018年5月17日  
	 * @param keyword
	 * @param reviewState
	 * @param start
	 * @param length
	 * @param draw
	 * @return
	 */
	@RequestMapping(value = "/releaseList",  method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PageInfo tmplReleaseList(String keyword, Integer reviewState, int start, int length, int draw) {
		return tmplReleaseService.listTmplReleaseInfo(reviewState, keyword, start, length, draw);
	}
	
	/**
	 * 发布审核/查看模板
	 * @author weix
	 * @date 2018年5月20日  
	 * @param model
	 * @param tmplId
	 * @return
	 */
	@RequestMapping(value = "/tmplReleaseReviewInfo",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String tmplReleaseReviewInfo(Model model, String tmplId) {
		/*查询模板信息*/
		Template tmplate = tmplReleaseService.getTmplReleaseReviewInfo(tmplId);
		List<TmplReleaseRecord> releaseRecords =  tmplReleaseService.listReleaseRecordInfo(tmplId);
		/*设置返回页面数据*/
		model.addAttribute("tmplate", tmplate);//模板信息
		model.addAttribute("releaseRecords", releaseRecords);//模板发布审核记录信息
		return "/resourceReview/tmplReleaseReviewInfo";
	}
	
	/**
	 * 模板发布审核
	 * @author weixin
	 * @version 2018年4月7日
	 * @param operation	通过或拒绝
	 * @param remark	审核意见
	 * @return
	 */
	@RequestMapping(value = "/releaseReview",  method = RequestMethod.POST  , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result releaseReview(@RequestBody TmplReleaseRecord tmplReleaseRecord) {
		return tmplReleaseService.addTmplReleaseReivew(tmplReleaseRecord);
	}
}
