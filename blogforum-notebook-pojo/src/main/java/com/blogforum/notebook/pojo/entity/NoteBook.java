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
	private String id;
	/**
	 * 父类目ID=0时，代表的是一级的类目
	 */
	private String parentId;
	/**
	 * 笔记本名称
	 */
	private String name;
	/**
	 * 创建笔记本的用户id
	 */
	private String userId;
	
	/**
	 * 判断是否有子节点
	 */
	private boolean isNode;
	
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

	public boolean isNode() {
		return isNode;
	}

	public void setNode(boolean isNode) {
		this.isNode = isNode;
	}


	
	


}