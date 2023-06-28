package com.awslabplatform_admin.entity;

import java.io.Serializable;

/**
 * 数据字典表entity
 * 
 * @author czy
 *
 */
public class DictionaryData implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 123456543212345L;

	private String dicId;// 数据字典父项ID

	private String dicCode;// 数据字典父项类型英文描述

	private String dicName;// 数据字典父项类型中文描述

	private Integer dicStause;// 删除状态，0表示可用数据，1表示删除状态

	private String itemId;// 数据字典子项ID

	private String itemName;// 数据字典子项名称

	private String itemValue;// 数据字典子项值

	private Integer itemSort;// 数据字典子项排序

	public String getDicId() {
		return dicId;
	}

	public void setDicId(String dicId) {
		this.dicId = dicId;
	}

	public String getDicCode() {
		return dicCode;
	}

	public void setDicCode(String dicCode) {
		this.dicCode = dicCode;
	}

	public String getDicName() {
		return dicName;
	}

	public void setDicName(String dicName) {
		this.dicName = dicName;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public Integer getItemSort() {
		return itemSort;
	}

	public void setItemSort(Integer itemSort) {
		this.itemSort = itemSort;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getDicStause() {
		return dicStause;
	}

	public void setDicStause(Integer dicStause) {
		this.dicStause = dicStause;
	}

}
