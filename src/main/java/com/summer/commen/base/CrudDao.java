/**
 * Copyright &copy; 2015-2020 <a href="http://www.snieri.com/">陕西省信息化工程研究院</a> All rights reserved.
 */
package com.summer.commen.base;

import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CrudDao<T> extends BaseDao {

	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(String id);
	
	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public T get(T entity);
	
	/**
	 * 根据实体名称和字段名称和字段值获取唯一记录
	 * 
	 * @param
	 * @param
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public  T findUniqueByProperty(@Param(value = "propertyName") String propertyName, @Param(value = "value") Object value);

	
	/**
	 * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity);

	/**
	 * 分页查询
	 * @param page
	 * @param entity
	 * @return
	 */
	List<T> findList(Page<T> page, T entity);
	
	/**
	 * 查询所有数据列表
	 * @param entity
	 * @return
	 */
	public List<T> findAllList(T entity);
	
	/**
	 * 查询所有数据列表
	 * @see public List<T> findAllList(T entity)
	 * @return
	 */
	@Deprecated
	public List<T> findAllList();
	
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	public int insert(T entity);
	
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public int update(T entity);
	
	/**
	 * 删除数据（物理删除，从数据库中彻底删除）
	 * @param id
	 * @see public int delete(T entity)
	 * @return
	 */
	@Deprecated
	public int delete(String id);
	
	/**
	 * 删除数据（逻辑删除，更新del_flag字段为1,在表包含字段del_flag时，可以调用此方法，将数据隐藏）
	 * @param id
	 * @see public int delete(T entity)
	 * @return
	 */
	@Deprecated
	public int deleteByLogic(String id);
	
	/**
	 * 删除数据（物理删除，从数据库中彻底删除）
	 * @param entity
	 * @return
	 */
	public int delete(T entity);
	
	/**
	 * 删除数据（逻辑删除，更新del_flag字段为1,在表包含字段del_flag时，可以调用此方法，将数据隐藏）
	 * @param entity
	 * @return
	 */
	public int deleteByLogic(T entity);
	
}