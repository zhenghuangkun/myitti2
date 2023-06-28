package com.awslabplatform_admin.service.systemManage.impl;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.awslabplatform_admin.dao.systemManage.DictionaryDataDao;
import com.awslabplatform_admin.entity.DictionaryData;
import com.awslabplatform_admin.entity.PageInfo;
import com.awslabplatform_admin.service.base.impl.BaseServiceImpl;
import com.awslabplatform_admin.service.systemManage.DictionaryDataService;

/**
 * 数据字典service 接口实现类
 * @author czy
 *
 */
@Service("DictionaryDataService")
public class DictionaryDataServiceImpl extends BaseServiceImpl<DictionaryDataDao, DictionaryData, String> implements DictionaryDataService {

	/**
	 * 获取数据字典父项的数据
	 * @return
	 */
	@Override
	public List<DictionaryData> getDictionaryData(DictionaryData dictionaryData) {

		return baseDao.getDictionaryData(dictionaryData);
	}

	/**
	 * 查询数据字典父项的数量
	 * @return
	 */
	@Override
	public int countDictionaryData(DictionaryData dictionaryData) {

		return baseDao.countDictionaryData(dictionaryData);
	}

	/**
	 * 根据dicId查询数据字典父项的数据
	 * @param divId
	 */
	@Override
	public DictionaryData getDicIdData(String divId) {

		return baseDao.getDicIdData(divId);
	}

	/**
	 * 删除子项数据字典的数据
	 * @param dicId
	 */
	@Override
	public boolean deleteDicSubItemData(String dicId) {

		return baseDao.deleteDicSubItemData(dicId);
	}

	/**
     * 查询分页信息
     * @param pageInfo
     * @return
     **/
	@Override
	public void getDicSumItemPageInfo(PageInfo pageInfo) {
		/*查询分页数据*/
		pageInfo.setData(baseDao.getSubItemData(pageInfo));
		/*查询总数*/
		int total = baseDao.countDicSubItemData(pageInfo);
		pageInfo.setRecordsTotal(total);
		pageInfo.setRecordsFiltered(total);//过滤记录数，dataTable必要参数
	}

	/**
	 * 查询数据字典子项的数量
	 */
	@Override
	public int countSubItemData(DictionaryData dictionaryData) {

		return baseDao.countSubItemData(dictionaryData);
	}
	/**
	 * 插入子项数据字典数据
	 * @param dictionaryData
	 */
	@Override
	public void insertSubItem(DictionaryData dictionaryData) {

		baseDao.insertSubItem(dictionaryData);
	}
	/**
	 * 根据itemId查询数据字典子项的数据
	 * @param itemId
	 */
	@Override
	public DictionaryData getItemIdSubItemData(String itemId) {

		return baseDao.getItemIdSubItemData(itemId);
	}
	/**
	 * 修改子项的数据字典数据
	 * @param dictionaryData
	 */
	@Override
	public void updateSubItemData(DictionaryData dictionaryData) {

		baseDao.updateSubItemData(dictionaryData);
	}
	/**
	 * 根据itemId删除子项数据字典的数据-->
	 */
	@Override
	public boolean deleteSubItem(String itemId) {

		return baseDao.deleteSubItem(itemId);
	}

	/**
	 * 根据父项的dicCode获取子项的数据接口
	 * @param dicCode
	 */
	@Override
	public List<DictionaryData> getSubItemListData(String dicCode) {

		return baseDao.getSubItemListData(dicCode);
	}
	
	/**
	 * 根据当前登入的用户id显示对应的IAM类型
	 */
	@Override
	public List<DictionaryData> getIamTypeData(Map<String,Object> param){
		
		return baseDao.getIamTypeData(param);
	}


	/**
	 *  查询数据字典子项的数量
	 * @param pageInfo
	 */
	@Override
	public int countDicSubItemData(PageInfo pageInfo) {

		return baseDao.countDicSubItemData(pageInfo);
	}

	/**
	 * 获取数据字典里的课程类型
	 * @return
	 */
	@Override
	public List<DictionaryData> getCourseTypeData(String courseTypeDicId){
		return baseDao.getDicDataByDicId(courseTypeDicId);
	}

	/**
	 * 获取数据字典里的课程等级
	 */
	@Override
	public List<DictionaryData> getCourseLevelData(String courseTypeDicId){
		return baseDao.getDicDataByDicId(courseTypeDicId);
	}

	/**
	 * 获取数据字典里的子项名
	 * @param itemId
	 * @return
	 */
	public String getDicDataNameByItemId(String itemId){
		return baseDao.getDicDataNameByItemId(itemId);
	}
	

	/**
	 * 根据课程代码查找数据字典ID
	 */
	@Override
	public String getDicIdByDicCode(String dicCode){
		return baseDao.getDicIdByDicCode(dicCode);
	}
	
	
	/**
	 * 获取课程类型是否存在
	 * @param dicId
	 * @param courseTypeName
	 * @return
	 */
	public Boolean checkCourseTypeExistByDicIdAndName(String dicId, String courseTypeName){
		
		int ret = baseDao.selectCourseTypeByDicIdAndName(dicId, courseTypeName);
		
		if(ret == 0){
			return false;
		}
		
		return true;
	}
	
	/**
	 * 查询课程类型数量
	 * @param dicId
	 * @return
	 */
	public int countCourseTypeTotal(String dicId){
		return baseDao.countCourseTypeTotal(dicId);
	}
	
	/**
	 * 添加课程类型数据到子项表
	 * @param dictionaryData
	 * @return
	 */
	public int addCourseTypeDateToSubItem(DictionaryData dictionaryData){
		return baseDao.addCourseTypeDateToSubItem(dictionaryData);
	}
}
