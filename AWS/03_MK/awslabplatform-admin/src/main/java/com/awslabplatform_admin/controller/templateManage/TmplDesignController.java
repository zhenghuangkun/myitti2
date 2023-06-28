package com.awslabplatform_admin.controller.templateManage;


import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.awslabplatform_admin.common.Common;
import com.awslabplatform_admin.common.Dictionary;
import com.awslabplatform_admin.common.message.TmplMessage;
import com.awslabplatform_admin.entity.DictionaryData;
import com.awslabplatform_admin.entity.FileInfo;
import com.awslabplatform_admin.entity.Result;
import com.awslabplatform_admin.entity.Template;
import com.awslabplatform_admin.service.systemManage.DictionaryDataService;
import com.awslabplatform_admin.service.template.TemplateService;
import com.awslabplatform_admin.service.uploadService.uploadService;
import com.awslabplatform_admin.util.ResultUtil;
import com.awslabplatform_admin.util.UUIDUtils;

@Controller
@RequestMapping("/tmpldesign")
public class TmplDesignController {
	
	//private static Logger log = LoggerFactory.getLogger(TmplDesignController.class);
	
    /**模板service*/
    @Autowired
    private  TemplateService  templateService;
    
	/**
	 * 文件上传service
	 */
	@Autowired
	private uploadService uploadsv;
	/**
	 * 数据字典service
	 */
	@Autowired
	private DictionaryDataService dictionaryDataService;
	
	/**
	 * 模板管理->模板设计
	 * @return
	 */
	@RequestMapping(value = "/list",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public ModelAndView  pageTmplDesignList (@RequestParam(value="page", defaultValue="1") Integer page, 
			@RequestParam(value="sort", defaultValue="") String sort, @RequestParam(value="keyword", defaultValue="")String keyword) {
		ModelAndView mav = new ModelAndView("/template/tmplDesignList");
		mav.addObject(templateService.listTmplDesignThumbnail(page, sort, keyword));
		return mav;
	}
	
	/**
	 * 设计模板
	 * @return
	 */
	@RequestMapping(value = "/design",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String pageTmplDesginInfo(Model model) {
		
		/*获取模板类型*/
		List<DictionaryData>  tmplType=  dictionaryDataService.getSubItemListData(Dictionary.DICTIONARY_TMPLTYPE.getDicCode());//获取数据字典数据
		
		/*设置返回数据*/
		Template tmplate = new Template();
		tmplate.setTmplId(UUIDUtils.getUUID());
		model.addAttribute("tmplType", tmplType);
		model.addAttribute("tmplate", tmplate);
		model.addAttribute("operation", "new");
		return "/template/tmplDesignInfo";
	}
	
	/**
	 * 编辑模板
	 * @return
	 */
	@RequestMapping(value = "/edit",  method = RequestMethod.GET , produces = "text/html; charset=UTF-8")
	public String pageEditTemplate(Model model, String tmplId) {
		
		/*获取模板类型*/
		List<DictionaryData>  tmplType=  dictionaryDataService.getSubItemListData(Dictionary.DICTIONARY_TMPLTYPE.getDicCode());//获取数据字典数据
		model.addAttribute("tmplType", tmplType);
		/*查询模板信息*/
		Template tmplate = templateService.getTemplateInfo(tmplId);
		/*设置返回页面数据*/
		model.addAttribute("tmplate", tmplate);
		model.addAttribute("operation", "edit");
		return "/template/tmplDesignInfo";
	}
	
	
	/**
	 * 添加模板
	 * @author weixin
	 * @version 2018年3月28日
	 * @param tmpl 模板对象
	 * @param result 参数叫校验结果
	 * @return
	 */
	@RequestMapping(value = "/addtemplate",  method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result  addTemplate(@Validated @RequestBody Template tmpl, BindingResult result){
		/*参数校验错误返回结果*/
		List<FieldError> listResult = result.getFieldErrors();
		for (FieldError error : listResult) {
		     // log.error( "errorField:" + error.getField() + "   errorMessage:" + error.getDefaultMessage() );
		      return ResultUtil.getResult(false, error.getDefaultMessage());//返回错误消息
		}
		/*插入模板*/
		if (!templateService.insertTemplate(tmpl)) {
			return  ResultUtil.getResult(false, TmplMessage.TMPLATE_EXISTS);
		}
		/*返回数据*/
		return  ResultUtil.getResult(true, TmplMessage.TMPLATE_ADD_SUCCESS);
	}
	
	/**
	 * 编辑模板
	 * @author weixin
	 * @version 2018年3月28日
	 * @param tmpl 模板对象
	 * @param result	参数叫校验结果
	 * @return
	 */
	@RequestMapping(value = "/edittemplate",  method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result  editTemplate(@Validated @RequestBody Template tmpl, BindingResult result){
		/*参数校验错误返回结果*/
		List<FieldError> listResult = result.getFieldErrors();
		for (FieldError error : listResult) {
		     // log.error( "errorField:" + error.getField() + "   errorMessage:" + error.getDefaultMessage() );
		      return ResultUtil.getResult(false, error.getDefaultMessage());//返回错误消息
		}
		/*插入模板*/
		if (!templateService.updateTemplate(tmpl)) {
			return  ResultUtil.getResult(false, TmplMessage.TMPLATE_EXISTS);
		}
		/*返回数据*/
		return  ResultUtil.getResult(true, TmplMessage.TMPLATE_EDIT_SUCCESS);
	}
	
	/**
	 * 发布模板
	 * @author weixin
	 * @version 2018年3月28日
	 * @param tmplId
	 * @return
	 */
	@RequestMapping(value = "/releasetemplate",  method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result  releaseTemplate(@RequestBody String tmplId){
		/*发布模板*/
		if (!templateService.releaseTemplate(tmplId) ) {
			return  ResultUtil.getResult(true, TmplMessage.TMPLATE_NO_EXISTS);
		};
		/*返回数据*/
		return  ResultUtil.getResult(true, TmplMessage.TMPLATE_RELEASE_SUCCESS);
	}
	
	/**
	 * 删除模板
	 * @author weixin
	 * @version 2018年3月28日
	 * @param tmplId
	 * @return
	 */
	@RequestMapping(value = "/deltetemplate",  method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result  deleteTemplate(@RequestBody String tmplId){
		/*插入模板*/
		templateService.deleteTemplate(tmplId);
		/*返回数据*/
		return  ResultUtil.getResult(true, TmplMessage.TMPLATE_DELETE_SUCCESS);
	}
	
	/**
	 * 模板资源脚本上传
	 */
	@RequestMapping(value = "/scriptUpload",  method = RequestMethod.POST )
	@ResponseBody
	public Result  scriptUpload(MultipartFile file, @RequestParam(name = "correlationId", required = true) String correlationId){
 		try {
			FileInfo fileInfo = uploadsv.uploadPic(file.getBytes(), file.getOriginalFilename(), file.getSize());
			/*文件服务器返回错误*/
			if ( fileInfo == null ) {
				//log.error(TmplMessage.TMPLATE_FILESERVER_ERROR.getCode() + " ： " + TmplMessage.TMPLATE_FILESERVER_ERROR.getContent());
				return  ResultUtil.getResult(false, TmplMessage.TMPLATE_SCRIPT_UPLOAD_ERROR); 
			}
			
			fileInfo.setCorrelationId(correlationId);//设置关联ID
			fileInfo.setDistinguishFlag(Common.FILE_DISTINGUIDH_TMPL_SCRIPT);//设置资源脚本标识
			
			return  ResultUtil.getResult(true, TmplMessage.TMPLATE_SCRIPT_UPLOAD_SUCCESS, fileInfo);

		} catch (IOException  e) {
			//log.error(TmplMessage.TMPLATE_SCRIPT_UPLOAD_ERROR.getCode() + " ： " + TmplMessage.TMPLATE_SCRIPT_UPLOAD_ERROR.getContent() );
			e.printStackTrace();
		}
 		/*返回数据*/
 		return  ResultUtil.getResult(false, TmplMessage.TMPLATE_SCRIPT_UPLOAD_ERROR);
	}
	
	/**
	 * 模板缩略图上传
	 */
	@RequestMapping(value = "/imageUpload",  method = RequestMethod.POST )
	@ResponseBody
	public Result  imageUpload(MultipartFile tmplImg, @RequestParam(name = "correlationId", required = true) String correlationId){
 		try {
 			FileInfo fileInfo = uploadsv.uploadPic(tmplImg.getBytes(), tmplImg.getOriginalFilename(), tmplImg.getSize());
			/*文件服务器返回错误*/
			if ( fileInfo == null ) {
				//log.error(TmplMessage.TMPLATE_FILESERVER_ERROR.getCode() + " ： " + TmplMessage.TMPLATE_FILESERVER_ERROR.getContent());
				return  ResultUtil.getResult(false, TmplMessage.TMPLATE_IMG_UPLOAD_ERROR); 
			}

			fileInfo.setCorrelationId(correlationId);//设置关联ID
			fileInfo.setDistinguishFlag(Common.FILE_DISTINGUIDH_TMPL_IMG);//设置图片标识
			return  ResultUtil.getResult(true, TmplMessage.TMPLATE_IMG_UPLOAD_SUCCESS, fileInfo); 
		} catch (IOException  e) {
			//log.error(TmplMessage.TMPLATE_IMG_UPLOAD_ERROR.getCode() + " ： " + TmplMessage.TMPLATE_IMG_UPLOAD_ERROR.getContent() );
			e.printStackTrace();
		}
 		/*返回数据*/
 		return  ResultUtil.getResult(false, TmplMessage.TMPLATE_IMG_UPLOAD_ERROR);
	}
	
}
