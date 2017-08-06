/***********************************************************************
 * Module:  NodeGroup.java
 * Author:  Administrator
 * Purpose: Defines the Class NodeGroup
 ***********************************************************************/
package com.blogforum.notebook.pojo.entity;

import java.util.List;

/** 笔记本 存放笔记 */
public class NoteBook extends DataEntity<NoteBook> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 笔记本编号
	 */
	public String id;
	/**
	 * 父类目ID=0时，代表的是一级的类目
	 */
	public String parentId;
	/**
	 * 笔记本名称
	 */
	public String name;
	/**
	 * 创建笔记本的用户id
	 */
	public String userId;
	
	/**
	 * 笔记
	 */
	public List<Note> notes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	


}