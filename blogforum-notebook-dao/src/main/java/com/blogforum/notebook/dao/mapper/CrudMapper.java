package com.blogforum.notebook.dao.mapper;

import java.util.List;

import com.blogforum.notebook.pojo.entity.DataEntity;



public interface CrudMapper<T extends DataEntity<T>> extends BaseMapper {
	public void save(T t);

	public void delete(T t);

	public void update(T t);

	public T getById(T t);

	public T get(T t);

	public List<T> queryList(T t);
}
