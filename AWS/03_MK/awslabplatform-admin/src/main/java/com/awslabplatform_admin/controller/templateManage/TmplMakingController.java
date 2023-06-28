package com.awslabplatform_admin.controller.templateManage;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awslabplatform_admin.entity.InstanceImage;
import com.awslabplatform_admin.entity.Mechanism;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.entity.Result;
import com.awslabplatform_admin.entity.Template;
import com.awslabplatform_admin.entity.TmplReleaseRecord;
import com.awslabplatform_admin.service.mechanismManage.MechanismService;
import com.awslabplatform_admin.service.resourceReview.TmplReleaseService;
import com.awslabplatform_admin.service.template.TemplateService;
import com.awslabplatform_admin.service.template.TmplCommentService;
import com.awslabplatform_admin.service.template.TmplMakingService;
import com.awslabplatform_admin.util.ResultUtil;
import com.awslabplatform_admin.util.ShiroUtil;
import com.awslabplatform_admin.util.UUIDUtils;
/**
 * 模板制作Controller层
 * @author weixin
 * @version 2018年3月31日
 */
@Controller
@RequestMapping("/tmplmaking")
public class TmplMakingController {
	
    /**模板制作service*/
    @Autowired
    private TmplMakingService  tmplMakingService;
    
    /**模板service*/
    @Autowired
    private TemplateService templateService;
    
    /**模板发布审核记录service*/
    @Autowired
    private TmplReleaseService tmplReleaseService;
    
    /**机构管理service*/
    @Autowired
    private MechanismService mechanismService;
    
    /**模板评论service*/
    @Autowired
    private  TmplCommentService  tmplCommentService;
    
	/**
	 * 模板制作——实例列表
	 * @author weixin
	 * @version 2018年4月15日
	 * @return
	 */
	@RequestMapping(value = "/index",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String pageTmplInstanceList() {
		return "/template/tmplMaking/tmplInstanceList";
	}
	
	/**
	 * 模板列表-镜像管理
	 * @author weix
	 * @date 2018年4月15日  
	 * @return
	 */
	@RequestMapping(value = "/imagelist",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String pageTmplImageList() {
		return "/template/tmplMaking/tmplImageList";
	}
	
	/**
	 * 模板制作列表
	 * @author weix
	 * @date 2018年4月15日  
	 * @return
	 */
	@RequestMapping(value = "/makelist",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String pageTmplMakingList() {
		return "/template/tmplMaking/tmplMakingList";
	}

	/**
	 * 模板制作
	 * @author weixin
	 * @version 2018年4月16日
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/new",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String pageTmplNew( Model model ) {
		/*获取模板类型*/
		List<Template> templates = templateService.getTemplateList();//源模板
		Template newTmplate = new Template();
		newTmplate.setTmplId(UUIDUtils.getUUID());
		model.addAttribute("templates", templates);
		model.addAttribute("tmplate", newTmplate);
		model.addAttribute("operation", "new");
		return "/template/tmplMaking/tmplMakeInfo";
	}
	
	/**
	 * 模板编辑
	 * @author weixin
	 * @version 2018年4月16日
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String pageTmplEdit( Model model , String tmplId) {
		/*获取模板类型*/
		List<Template> templates = templateService.getTemplateList();//源模板列表
		Template tmplate = tmplMakingService.getTemplateInfo(tmplId);//当前模板信息
		List<TmplReleaseRecord> releaseRecord = tmplReleaseService.listReleaseRecordInfo(tmplId);//模板发布审核记录
		String schoolId = ShiroUtil.getCurrentUser().getSchoolId();//获取当前用户对应学校ID
		List<Mechanism> mechanisms = mechanismService.getParentMechanismList(schoolId);//获取院系
		model.addAttribute("templates", templates);
		model.addAttribute("tmplate", tmplate);
		model.addAttribute("releaseRecords", releaseRecord);
		model.addAttribute("operation", "edit");
		model.addAttribute("mechanisms", mechanisms);
		return "/template/tmplMaking/tmplMakeInfo";
	}

	
   /**
    * 查询模板实例列表数据
    * @author weixin
    * @version 2018年3月29日
    * @param keyword
    * @param start
    * @param length
    * @param draw
    * @return
    */
	@RequestMapping(value = "/listInstances",  method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PageInfo listInstances(String keyword, Integer start, Integer length, Integer draw) {
		return tmplMakingService.listTmplInstanceTb(start,length,draw,keyword);
	}
	
	/**
	 * 镜像列表
	 * @author weix
	 * @date 2018年4月15日  
	 * @param keyword
	 * @param start
	 * @param length
	 * @param draw
	 * @return
	 */
	@RequestMapping(value = "/listInstanceImages",  method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PageInfo listInstanceImages(String keyword, Integer start, Integer length, Integer draw) {
		return tmplMakingService.listTmplInstanceImageTb(start,length,draw,keyword);
	}	
	
	/**
	 * 模板列表
	 * @author weixin
	 * @version 2018年4月16日
	 * @param keyword
	 * @param start
	 * @param length
	 * @param draw
	 * @return
	 */
	@RequestMapping(value = "/listTemplates",  method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PageInfo listTemplates(String keyword, Integer start, Integer length, Integer draw) {
		return tmplMakingService.listTmplmakingTb(start, length, draw, keyword);
	}
	
	/**
	 * 镜像制作
	 * @author weix
	 * @date 2018年4月15日  
	 * @param keyword
	 * @param start
	 * @param length
	 * @param draw
	 * @return
	 */
	@RequestMapping(value = "/imageMake",  method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result instanceImageMake(@RequestParam(value="instanceId",defaultValue="") String instanceId, @RequestParam(value="tmplId",defaultValue="") String tmplId,
			@RequestParam(value="applyId",defaultValue="") String applyId, @RequestParam(value="imageDescribe",defaultValue="") String imageDescribe) {
		return templateService.instanceImageMake( tmplId, applyId, instanceId, imageDescribe);
	}
	
	/**
	 * 修改镜像
	 * @author weix
	 * @date 2018年4月15日  
	 * @param instanceId
	 * @param tmplId
	 * @param applyId
	 * @param imageDescribe
	 * @return
	 */
	@RequestMapping(value = "/imageedit",  method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result instanceImageDelete(InstanceImage instanceImage) {
		return tmplMakingService.updateInstanceImage(instanceImage);
	}
	
	
	/**
	 * 删除镜像
	 * @author weix
	 * @date 2018年4月15日  
	 * @param instanceId
	 * @param tmplId
	 * @param applyId
	 * @param imageDescribe
	 * @return
	 */
	@RequestMapping(value = "/imagedelete",  method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result instanceImageDelete(@RequestParam(value="imageId",defaultValue="") String imageId) {
		return tmplMakingService.deleteInstanceImage(imageId);
	}
	
	/**
	 * 添加模板
	 * @author weixin
	 * @version 2018年4月16日
	 * @param tmpl
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/addtmpl",  method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result  addTemplate(@Validated @RequestBody Template tmpl, BindingResult result){
		/*参数校验错误返回结果*/
		List<FieldError> listResult = result.getFieldErrors();
		for (FieldError error : listResult) {
		     // log.error( "errorField:" + error.getField() + "   errorMessage:" + error.getDefaultMessage() );
		      return ResultUtil.getResult(false, error.getDefaultMessage());//返回错误消息
		}
		
		return tmplMakingService.addTemplate(tmpl);
	}
	
	/**
	 * 删除模板
	 * @author weixin
	 * @version 2018年4月16日
	 * @param tmplId
	 * @return
	 */
	@RequestMapping(value = "/tmpldelete",  method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result templateDelete(@RequestParam(value="tmplId",defaultValue="") String tmplId) {
		return tmplMakingService.deleteTemplate(tmplId);
	}
	
	/**
	 * 编辑模板
	 * @author weixin
	 * @version 2018年4月16日
	 * @param tmpl
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/edittmpl",  method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result  editTemplate(@Validated @RequestBody Template tmpl, BindingResult result){
		/*参数校验错误返回结果*/
		List<FieldError> listResult = result.getFieldErrors();
		for (FieldError error : listResult) {
		     // log.error( "errorField:" + error.getField() + "   errorMessage:" + error.getDefaultMessage() );
		      return ResultUtil.getResult(false, error.getDefaultMessage());//返回错误消息
		}
		/*更新模板*/
		return tmplMakingService.updateTemplate(tmpl);
	}
	
	/**
	 * 模板发布申请
	 * @author weix
	 * @date 2018年5月21日  
	 * @param tmplRecord
	 * @return
	 */
	@RequestMapping(value = "/tmplReleaseApply",  method = RequestMethod.POST)
	@ResponseBody
	public Result tmplReleaseApply(@RequestBody TmplReleaseRecord tmplRecord){
		/*更新模板*/
		return tmplMakingService.tmplReleaseApply(tmplRecord);
	}
	
	/**
	 * 发布、取消模板
	 * @author weix
	 * @date 2018年5月21日  
	 * @param tmplId
	 * @param state
	 * @return
	 */
	@RequestMapping(value = "/tmplrelease",  method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result tmplrelease(@RequestParam(value="tmplId",defaultValue="") String tmplId, 
			@RequestParam(value="state",defaultValue="0") Integer state) {
		return tmplMakingService.tmplRelease(tmplId,state);
	}
	
	/**
	 * 查看个人模板信息
	 * @author weix
	 * @date 2018年5月27日  
	 * @param model
	 * @param tmplId
	 * @return
	 */
	@RequestMapping(value = "/tmplmakeInforead",  method = RequestMethod.GET , produces = "application/json;charset=UTF-8")
	public String tmplMakeInfoRead(Model model, String tmplId) {
		/*查询模板信息*/
		Template tmplate = tmplMakingService.getTemplateDetailInfo(tmplId);//当前模板信息
		/*评论*/
		PageInfo commentInfo = tmplCommentService.listTmplComment(1, tmplId);
		
		/*设置返回页面数据*/
		model.addAttribute("tmplate", tmplate);//模板信息
		model.addAttribute("collection", templateService.getTmplCollection(tmplId));//收藏
		model.addAttribute("commentInfo", commentInfo);//评论信息
		
		return "/template/tmplMaking/tmplMakeInfoRead";
	}
	
}
