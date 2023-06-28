package com.awslabplatform.entity;

import java.io.Serializable;

public class CourseType implements Serializable{
	private int count;
	private String itemId;
	private String itemName;
	private String typePic;
	
	public String getTypePic() {
		return typePic;
	}
	public void setTypePic(String typePic) {
		this.typePic = typePic;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
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
	@Override
	public String toString() {
		return "CourseType [itemId=" + itemId + ", itemName=" + itemName + "]";
	}

}
