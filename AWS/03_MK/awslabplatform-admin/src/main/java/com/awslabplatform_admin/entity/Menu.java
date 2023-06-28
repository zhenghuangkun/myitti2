package com.awslabplatform_admin.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单类
 * @author weix
 */
public class Menu implements Serializable {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 菜单ID
	 */
	private Integer menuId;
	/**
	 * 父级ID
	 */
	private Integer menuPID;

	/**
	 * 菜单名
	 */
	private String menuName;
	/**
	 * 菜单图标
	 */
	private String menuIcon;
	/**
	 * 菜单URL
	 */
	private String menuUrl;
	/**
	 * 菜单描述
	 */
	private String menuDESC;
	/**
	 * 排序标识
	 */
	private String menuSort;
	/**
	 * 菜单等级
	 */
	private Integer menuLevel;
	/**
	 * 操作人
	 */
	private String operator;
	/**
	 * 新建时间
	 */
	private String createTime;
	/**
	 * 更新时间
	 */
	private String updateTime;
	
	/**
	 * 状态
	 */
	private int state;

	/**
	 * 权限标识符号
	 * 取值参考 ShiroPermissionConstant
	 */
	private String permissionIdentifier;
	
	/**
	 * 子菜单
	 */
	private List<Menu> childMenus;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getMenuPID() {
		return menuPID;
	}

	public void setMenuPID(Integer menuPID) {
		this.menuPID = menuPID;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuDESC() {
		return menuDESC;
	}

	public void setMenuDESC(String menuDESC) {
		this.menuDESC = menuDESC;
	}

	public String getMenuSort() {
		return menuSort;
	}

	public void setMenuSort(String menuSort) {
		this.menuSort = menuSort;
	}

	public Integer getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public List<Menu> getChildMenus() {
		return childMenus;
	}

	public void setChildMenus(List<Menu> childMenus) {
		this.childMenus = childMenus;
	}

	public String getPermissionIdentifier() {
		return permissionIdentifier;
	}

	public void setPermissionIdentifier(String permissionIdentifier) {
		this.permissionIdentifier = permissionIdentifier;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
