package com.blogforum.notebook.service;

import java.util.List;

import com.blogforum.notebook.common.page.Page;

/**
 * Service层基类
 * 
 * @author Edward
 * 
 * @param <T>
 * @param <ID>
 */
public interface BaseService<T> {
	public void save(T t);

	public T getById(T t);
	
	public T get(T t);
	
	public List<T> queryList(T t);
	
	public Page<T> queryList(Page<T> page, T t);
	
	public void update(T t);

	public void delete(T t);
	
}
