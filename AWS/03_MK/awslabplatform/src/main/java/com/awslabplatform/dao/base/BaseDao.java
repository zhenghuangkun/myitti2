package com.awslabplatform.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


/**
 * 通用DAO
 *
 * @param <T>  实体
 * @param <PK> 主键
 * @author weix
 */
public interface BaseDao<T, PK extends Serializable> {

	/**
	 * 查询（根据主键ID查询）
	 **/
	T selectByPrimaryKey(@Param("id") PK id);

	/**
	 * 查询（根据主键ID查询集合）
	 *
	 * @param id
	 * @return
	 */
	List<T> listByPrimaryKey(@Param("id") PK id);


	/**
	 * 删除（根据主键ID删除）
	 **/
	int deleteByPrimaryKey(@Param("id") PK id);

	/**
	 * 添加
	 **/
	int insert(T entity);

	/**
	 * 添加 （匹配有值的字段）
	 **/
	int insertSelective(T entity);

	/**
	 * 修改
	 **/
	int updateByPrimaryKey(T entity);

	/**
	 * 修改 （匹配有值的字段）
	 **/
	int updateByPrimaryKeySelective(T entity);

	/**
	 * 查询（根据任意字段查询）
	 *
	 * @param {field，value}
	 * @return
	 */
	T selectByCondition(Map<String, Object> condition);

	/**
	 * 查询（任意字段返回多个结果）
	 *
	 * @param {field，value}
	 * @return
	 **/
	List<T> listByCondition(Map<String, Object> condition);
}