package com.awslabplatform_admin.dao.systemManage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.awslabplatform_admin.dao.base.BaseDao;
import com.awslabplatform_admin.entity.DictionaryData;
import com.awslabplatform_admin.entity.PageInfo;

/**
 * 数据字典DAO
 * @author czy
 *
 */
public interface DictionaryDataDao extends BaseDao<DictionaryData, String>{

	/**
	 * 获取数据字典父项的数据
	 * @return
	 */
	List<DictionaryData> getDictionaryData(DictionaryData dictionaryData);

	/**
	 * 查询数据字典父项的数量
	 * @return
	 */
	int countDictionaryData(DictionaryData dictionaryData);

	/**
	 * 根据dicId查询数据字典父项的数据
	 * @param divId
	 */
	DictionaryData getDicIdData(String dicId);

	/**
	 * 删除子项数据字典的数据
	 * @param dicId
	 */
	boolean deleteDicSubItemData(String dicId);


	/**
	 * 获取子项的数据，将数据显示在表格上
	 * @param dictionaryData
	 */
	List<DictionaryData> getSubItemData(PageInfo pageInfo);

	/**
	 *  查询数据字典子项的数量
	 * @param dicId
	 */
	int countDicSubItemData(PageInfo pageInfo);

	/**
	 * 查询数据字典子项的数量
	 */
	int countSubItemData(DictionaryData dictionaryData);

	/**
	 * 插入子项数据字典数据
	 * @param dictionaryData
	 */
	void insertSubItem(DictionaryData dictionaryData);

	/**
	 * 根据itemId查询数据字典子项的数据
	 * @param itemId
	 */
	DictionaryData getItemIdSubItemData(String itemId);
	
	/**
	 * 修改子项的数据字典数据
	 * @param dictionaryData
	 */
	void updateSubItemData(DictionaryData dictionaryData);

	/**
	 * 根据itemId删除子项数据字典的数据-->
	 */
	boolean deleteSubItem(String itemId);

	/**
	 * 根据父项的dicCode获取子项的数据接口
	 * @param dicCode
	 */
	List<DictionaryData> getSubItemListData(@Param("dicCode")String dicCode);
	
	/**
	 * 根据当前登入的用户id显示对应的IAM类型
	 */
	List<DictionaryData> getIamTypeData(Map<String,Object> param);

	/**
	 * 获取数据字典数据
	 */
	List<DictionaryData> getDicDataByDicId(String DicId);

	/**
	 * 获取数据字典ID
	 */
	String getDicIdByDicCode(String dicCode);
	
	/**
	 * 获取数据字典里的子项名
	 * @param itemId
	 * @return
	 */
	public String getDicDataNameByItemId(String itemId);
	
	/**
	 * 获取数据课程类型
	 * @param dicId
	 * @param courseTypeName
	 * @return
	 */
	public int selectCourseTypeByDicIdAndName(@Param("dicId")String dicId, @Param("courseTypeName")String courseTypeName);

	/**
	 * 查询课程类型数量
	 * @param dicId
	 * @return
	 */
	public int countCourseTypeTotal(@Param("dicId")String dicId);
	
	/**
	 * 添加课程类型数据到子项表
	 * @param dictionaryData
	 * @return
	 */
	public int addCourseTypeDateToSubItem(DictionaryData dictionaryData);
}
