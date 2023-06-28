package com.awslabplatform_admin.controller.templateManage;

import java.util.List;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.awslabplatform_admin.common.Dictionary;
import com.awslabplatform_admin.entity.DictionaryData;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Result;
import com.awslabplatform_admin.entity.Template;
import com.awslabplatform_admin.entity.TmplComment;
import com.awslabplatform_admin.entity.TmplUseApply;
import com.awslabplatform_admin.service.resourceReview.TmplReviewService;
import com.awslabplatform_admin.service.systemManage.DictionaryDataService;
import com.awslabplatform_admin.service.template.TemplateService;
import com.awslabplatform_admin.service.template.TmplCommentService;
/**
 * 我的模板Controller层
 * @author weixin
 * @version 2018年3月31日
 */
@Controller
@RequestMapping("/template")
public class TemplateController {
	
    /**模板service*/
    @Autowired
    private  TemplateService  templateService;
    
    /**模板评论service*/
    @Autowired
    private  TmplCommentService  tmplCommentService;
    
	/**
	 * 测试申请service
	 */
	@Autowired
	private TmplReviewService tmplReviewService;
    
	/**
	 * 数据字典service
	 */
	@Autowired
	private DictionaryDataService dictionaryDataService;
    
	
	/**
	 * 我的模板——推荐模板
	 * @author weixin
	 * @version 2018年4月3日
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String pageTemplateIndex( Model model ,@RequestParam(value="keyword", defaultValue="")String keyword) {
		/*获取模板类型*/
		List<DictionaryData>  tmplType =  dictionaryDataService.getSubItemListData(Dictionary.DICTIONARY_TMPLTYPE.getDicCode());//获取数据字典数据
		List<Template> tmplList = templateService.listIndexTmplThumbnail(keyword);
		model.addAttribute("tmplList", tmplList);
		
		model.addAttribute("tmplType",tmplType);
		
		model.addAttribute("keyword",keyword);
		
		return "/template/tmplIndex";
	}
	
	/**
	 * 我的模板——收藏列表
	 * @author weixin
	 * @version 2018年4月3日
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/collection",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public ModelAndView pageTemplateCollect( @RequestParam(value="page", defaultValue="1") Integer page,  @RequestParam(value="keyword", defaultValue="")String keyword ) {
		ModelAndView mav = new ModelAndView("/template/tmplCollection");
		mav.addObject(templateService.listTmplcollection(page, keyword));
		return mav;
	}
	
	/**
	 * 我的模板——模板试用
	 * @author weixin
	 * @version 2018年4月3日
	 * @return
	 */
	@RequestMapping(value = "/using",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String pageTemplateUsing( ) {
		return "/template/tmplUseApplyList";
	}
	
	/**
	 * 查看所有模板列表
	 * @author weixin
	 * @version 2018年3月31日
	 * @param page	当前页码
	 * @param sort		排序字段
	 * @param keyword	搜索关键词
	 * @return
	 */
	@RequestMapping(value = "/all",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public ModelAndView pageTemplateAllList(@RequestParam(value="page", defaultValue="1") Integer page, 
			@RequestParam(value="sort", defaultValue="") String sort, @RequestParam(value="keyword", defaultValue="")String keyword) {
		ModelAndView mav = new ModelAndView("/template/tmplAllList");
		mav.addObject(templateService.listAllTmplThumbnail(page, sort, keyword));
		return mav;
	}
	
	/**
	 * 查看模板具体信息
	 * @author weixin
	 * @version 2018年3月31日
	 * @return
	 */
	@RequestMapping(value = "/info",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String pageTemplateInfo(Model model, String tmplId) {
		/*查询模板信息*/
		Template tmplate = templateService.getTemplateInfo(tmplId);
		/*评论*/
		PageInfo commentInfo = tmplCommentService.listTmplComment(1, tmplId);
		
		/*设置返回页面数据*/
		model.addAttribute("tmplate", tmplate);//模板信息
		model.addAttribute("collection", templateService.getTmplCollection(tmplId));//收藏
		model.addAttribute("commentInfo", commentInfo);//评论信息
		
		return "/template/tmplInfo";
	}
	
	/**
	 * 模板收藏
	 * @author weixin
	 * @version 2018年4月2日
	 * @param tmplId
	 * @return
	 */
	@RequestMapping(value = "/collection",  method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result tmplCollection(@RequestBody String tmplId) {
		return templateService.tmplCollection(tmplId);
	}
	
	/**
	 * 模板取消收藏
	 * @author weixin
	 * @version 2018年4月2日
	 * @param tmplId
	 * @return
	 */
	@RequestMapping(value = "/collectioncanacel",  method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result tmplCollectionCancel(@RequestBody String tmplId) {
		return templateService.tmplCollectionCancel(tmplId);
	}
	
	/**
	 * 模板评论发表
	 * @author weixin
	 * @version 2018年4月2日
	 * @param tmplComment
	 * @return
	 */
	@RequestMapping(value = "/addcomment",  method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result addTmplComment(@RequestBody TmplComment tmplComment) {
		return tmplCommentService.addTmplComment(tmplComment);
	}
	
	/**
	 * 模板评论删除
	 * @author weixin
	 * @version 2018年4月2日
	 * @param tmplCommentId
	 * @return
	 */
	@RequestMapping(value = "/deletecomment",  method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result deleteTmplComment(@RequestBody String tmplCommentId) {
		return tmplCommentService.deleteTmplComment(tmplCommentId);
	}
	
	/**
	 * 模板评论查询
	 * @author weixin
	 * @version 2018年4月2日
	 * @param tmplCommentId
	 * @return
	 */
	@RequestMapping(value = "/listcomment",  method = RequestMethod.POST  , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PageInfo listComment( @RequestParam("page") Integer page,  @RequestParam("tmplId") String tmplId) {
		return tmplCommentService.listTmplComment(page, tmplId);
	}
	
	/**
	 * 测试申请
	 * @author weixin
	 * @version 2018年4月4日
	 * @param page
	 * @param tmplId
	 * @return
	 */
	@RequestMapping(value = "/usingapply",  method = RequestMethod.POST  , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result usingApply(@RequestBody TmplUseApply tmplUseApply) {
		return tmplReviewService.addTmplUseApply(tmplUseApply);
	}
	
   /**
    * 查询模板申请列表数据
    * @author weixin
    * @version 2018年3月29日
    * @param keyword
    * @param start
    * @param length
    * @param draw
    * @return
    */
	@RequiresRoles(value={"teacher","admin"}, logical=Logical.OR)
	@RequestMapping(value = "/listtmplapply",  method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PageInfo listTmplApplyTb(String keyword,  Integer reviewState, int start, int length, int draw) {
		return tmplReviewService.listTmplUseApplyInfo(reviewState, keyword, start, length, draw);
	}
	
	
	/**
	 * 模板申请使用信息
	 * @author weixin
	 * @version 2018年4月8日
	 * @param tmplId
	 * @param applyId
	 * @return
	 */
	@RequestMapping(value = "/tmplapplyinfo",   method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String tmplApplyInfo(Model model, String tmplId, String applyId) {
		/*查询模板信息*/
		Template tmplate = templateService.getTemplateInfo(tmplId);
		TmplUseApply tmplUseApply = tmplReviewService.getTmplUseApplyInfo(applyId);
		/*设置返回页面数据*/
		model.addAttribute("tmplate", tmplate);//模板信息
		model.addAttribute("tmplUseApply", tmplUseApply);//模板申请使用信息
		
		return "/template/tmplUseApplyInfo";
	}
	
	/**
	 * 测试申请
	 * @author weixin
	 * @version 2018年4月4日
	 * @param page
	 * @param tmplId
	 * @return
	 */
	@RequestMapping(value = "/usereapply",  method = RequestMethod.POST  , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result useReapply(@RequestBody TmplUseApply tmplUseApply) {
		return tmplReviewService.updateTmplUseApply(tmplUseApply);
	}
	
	/**
	 * 资源启动
	 * @author weixin
	 * @version 2018年4月9日
	 * @param tmplUseApply
	 * @return
	 */
	@RequestMapping(value = "/startRes",  method = RequestMethod.POST  , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result startResource(@RequestBody TmplUseApply tmplUseApply) {
		return templateService.startTmplResource(tmplUseApply);
	}
	/**
	 * 资源创建查询
	 * @author weixin
	 * @version 2018年4月10日
	 * @param tmplUseApply
	 * @return
	 */
	@RequestMapping(value = "/getResState",  method = RequestMethod.POST  , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result getResourceState(@RequestBody TmplUseApply tmplUseApply) {
		return templateService.getResourceCreateState(tmplUseApply);
	}
	
	/**
	 * 资源释放
	 * @author weixin
	 * @version 2018年4月9日
	 * @param tmplUseApply
	 * @return
	 */
	@RequestMapping(value = "/terminationRes",  method = RequestMethod.POST  , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result terminationResource(@RequestBody TmplUseApply tmplUseApply) {
		return templateService.terminationTmplResource(tmplUseApply);
	}
	
	/**
	 * 镜像制作
	 * @author weixin
	 * @version 2018年4月14日
	 * @param tmplUseApply
	 * @return
	 */
	@RequestMapping(value = "/AMIMake",  method = RequestMethod.POST  , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result AMIMake(@RequestParam(value="tmplId",defaultValue="") String tmplId,@RequestParam(value="applyId",defaultValue="") String applyId,
			@RequestParam(value="instanceId",defaultValue="") String instanceId) {
		return templateService.instanceImageMake(tmplId,applyId,instanceId,"");
	}
	
	/**
	 * 课程审核模板信息查看
	 * @author weix
	 * @date 2018年5月27日  
	 * @param model
	 * @param tmplId
	 * @return
	 */
	@RequestMapping(value = "/coursetmplinfo",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String courseTmplInfo(Model model, String tmplId) {
		/*查询模板信息*/
		Template tmplate = templateService.getTemplateInfo(tmplId);
		
		/*评论*/
		PageInfo commentInfo = tmplCommentService.listTmplComment(1, tmplId);
		
		/*设置返回页面数据*/
		model.addAttribute("tmplate", tmplate);//模板信息
		model.addAttribute("collection", templateService.getTmplCollection(tmplId));//收藏
		model.addAttribute("commentInfo", commentInfo);//评论信息
		
		return "/resourceReview/courseReviewTmplInfo";
	}
		
}
