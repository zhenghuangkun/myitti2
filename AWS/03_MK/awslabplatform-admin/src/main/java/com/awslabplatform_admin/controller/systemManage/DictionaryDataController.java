package com.awslabplatform_admin.controller.systemManage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.awslabplatform_admin.common.Common;
import com.awslabplatform_admin.entity.DictionaryData;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.service.systemManage.DictionaryDataService;
import com.awslabplatform_admin.util.JsonTool;
import com.awslabplatform_admin.util.WriteJsonUtil;


/**
* 数据字典Controller层
*
* @author   czy
* @version  2018-3-16
*/
@Controller
@RequestMapping("/dictionary")
public class DictionaryDataController {

	/**数据字典的service*/
    @Autowired
    private  DictionaryDataService  dictionaryDataService;
		
    /**
     * 查询数据字典父项的数据，将数据传回到前台进行显示
     * @param response
     * @param request
     */
	@RequestMapping("/selectDictionary")
	public void selectDictionaryData(HttpServletResponse response,HttpServletRequest request){
		DictionaryData dictionaryData=new DictionaryData();
		dictionaryData.setDicStause(Common.DIC_STATE_ACTIVE);
		List<DictionaryData> dictionaryDataList=dictionaryDataService.getDictionaryData(dictionaryData);

		/*数据返回*/
		WriteJsonUtil.writeJsonObject(dictionaryDataList, response);
	}
    	
	/**
     * 添加父项的数据字典数据，将数据插入到数据库中存储起来
     * @param response
     * @param request
     */
	@RequestMapping("/addParentDic")
	public void addParentDicData(DictionaryData dictionaryData,HttpServletResponse response,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		
		if(dictionaryDataService.countDictionaryData(dictionaryData)>0){
			map.put("success", false);
		}else{
			dictionaryDataService.insert(dictionaryData);
			map.put("success", true);
		}
		/*数据返回*/
		WriteJsonUtil.writeJsonObject(map, response);
	}
	
	/**
     * 根据dicId查询数据字典父项的数据 
     * @param divId
     * @param response
     */
	@RequestMapping("/getParentDic")
	public void getParentDicData(String dicId,HttpServletResponse response,HttpServletRequest request){
		DictionaryData getDictionaryData=dictionaryDataService.getDicIdData(dicId);
		
		/*数据返回*/
		WriteJsonUtil.writeJsonObject(getDictionaryData, response);
	}
	
	/**
     * 编辑父项数据，将数据提交到数据库进行修改数据
     * @param divId
     * @param response
     */
	@RequestMapping("/editParentDic")
	public void editParentDicData(DictionaryData dictionaryData,String copyDicName,HttpServletResponse response,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		if(dictionaryData.getDicName().equals(copyDicName)){
			dictionaryDataService.updateByPrimaryKeySelective(dictionaryData);
			map.put("success", true);
		}else{
			if(dictionaryDataService.countDictionaryData(dictionaryData)>0){
				map.put("success", false);
			}else{
				dictionaryDataService.updateByPrimaryKeySelective(dictionaryData);
				map.put("success", true);
			}
		}
		/*数据返回*/
		WriteJsonUtil.writeJsonObject(map, response);
	}
	
	/**
     * 删除父项的数据字典
     * @param divId
     * @param response
     */
	@RequestMapping("/deleteDicParent")
	public void deleteDicParentData(String dicId,HttpServletResponse response,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		DictionaryData dictionaryData=new DictionaryData();
		dictionaryData.setDicStause(Common.DIC_STATE_DELETE);
		dictionaryData.setDicId(dicId);
		if(dictionaryDataService.updateByPrimaryKeySelective(dictionaryData)){
			dictionaryDataService.deleteDicSubItemData(dicId);
			map.put("success", true);
		}else{
			map.put("success", false);
		}
		/*数据返回*/
		WriteJsonUtil.writeJsonObject(map, response);
	}
	
	/**
	 * 获取子项的数据，将数据显示在表格上
	 * @param dicId
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/getSubItemData")
	public void getSubItemData(String dicId,int start, int length, int draw,HttpServletResponse response) throws IOException{
		Map<String,Object> condition  = new HashMap<String, Object>();
		condition.put("dicId", dicId.trim());//角色名称
		
		PageInfo pageInfo = new PageInfo(start, length, draw);
		pageInfo.setCondition(condition);
		dictionaryDataService.getDicSumItemPageInfo(pageInfo);
		
		/*数据返回*/
		JsonTool.toJson(pageInfo,response);
	}
	
	/**
     * 添加子项的数据字典数据，将数据插入到数据库中存储起来
     * @param response
     * @param request
     */
	@RequestMapping("/addSubItemDic")
	public void addSubItemDicData(DictionaryData dictionaryData,HttpServletResponse response,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();

		if(dictionaryDataService.countSubItemData(dictionaryData)>0){
			map.put("success", false);
		}else{
			dictionaryDataService.insertSubItem(dictionaryData);
			map.put("success", true);
		}
		/*数据返回*/
		WriteJsonUtil.writeJsonObject(map, response);
	}
	
	/**
     * 根据itemId查询数据字典子项的数据 
     * @param itemId
     * @param response
     */
	@RequestMapping("/getSubItemDic")
	public void getSubItemDicData(String itemId,HttpServletResponse response,HttpServletRequest request){
		DictionaryData getSubItemDicData=dictionaryDataService.getItemIdSubItemData(itemId);
		
		/*数据返回*/
		WriteJsonUtil.writeJsonObject(getSubItemDicData, response);
	}
	
	/**
     * 编辑子项数据，将数据提交到数据库进行修改数据
     * @param divId
     * @param response
     */
	@RequestMapping("/editSubItemDic")
	public void editSubItemDicData(DictionaryData dictionaryData,String copyItemName,String copyitemValue,HttpServletResponse response,HttpServletRequest request){
		Map<String,Object> condition  = new HashMap<String, Object>();
		condition.put("dicId", dictionaryData.getDicId());
		PageInfo pageInfo = new PageInfo();
		Map<String,Object> map = new HashMap<String, Object>();
		if(dictionaryData.getItemName().equals(copyItemName) && dictionaryData.getItemValue().equals(copyitemValue)){//数据都相同的时候
			dictionaryDataService.updateSubItemData(dictionaryData);
			map.put("success", true);
		}else if(dictionaryData.getItemValue().equals(copyitemValue) && !(dictionaryData.getItemName().equals(copyItemName))){//ItemValue没改，ItemName改
			condition.put("itemName", dictionaryData.getItemName());
			condition.put("itemValue", "");
			pageInfo.setCondition(condition);
			if(dictionaryDataService.countDicSubItemData(pageInfo)>0){
				map.put("success", false);
			}else{
				dictionaryDataService.updateSubItemData(dictionaryData);;
				map.put("success", true);	
			}
		}else if(!(dictionaryData.getItemValue().equals(copyitemValue)) && dictionaryData.getItemName().equals(copyItemName)){//ItemValue改，ItemName没改
			condition.put("itemName", "");
			condition.put("itemValue", dictionaryData.getItemValue());
			pageInfo.setCondition(condition);
			if(dictionaryDataService.countDicSubItemData(pageInfo)>0){
				map.put("success", false);
			}else{
				dictionaryDataService.updateSubItemData(dictionaryData);;
				map.put("success", true);
			}
		}else{
			dictionaryDataService.updateSubItemData(dictionaryData);;
			map.put("success", true);
		}
		/*数据返回*/
		WriteJsonUtil.writeJsonObject(map, response);
	}
	
	/**
     * 删除子项的数据字典
     * @param divId
     * @param response
     */
	@RequestMapping("/deleteSubItem")
	public void deleteSubItem(String itemId,HttpServletResponse response,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		
		if(dictionaryDataService.deleteSubItem(itemId)){
			
			map.put("success", true);
		}
		/*数据返回*/
		WriteJsonUtil.writeJsonObject(map, response);
	}
}
