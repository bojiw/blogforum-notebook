/***********************************************************************
 * Module:  Note.java
 * Author:  Administrator
 * Purpose: Defines the Class Note
 ***********************************************************************/
package com.blogforum.notebook.pojo.entity;

/** 笔记表 */
public class Note extends DataEntity<Note> {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	/**
	 * 编号
	 */
	public String				id;
	/**
	 * 笔记标题
	 */
	public String				noteTitle;
	/**
	 * 笔记内容
	 */
	public String				noteBody;
	/**
	 * 用户
	 */
	public String				userId;
	/**
	 * 笔记本
	 */
	public String				notebookId;

	/**
	 * 附件路径
	 */
	public String				enclosure;

	/**
	 * 笔记类型 普通笔记或者markdown笔记
	 */
	public String				type;
	/**
	 * 删除标记 N为不删除 Y为删除
	 */
	public String				delFlag;

	/**
	 * 扩展参数
	 */
	public String				extjson;

	/**
	 * 标签,用;隔开
	 */
	public String				label;

	/**
	 * 博客笔记
	 */
	public String				blogId;

	/**
	 * 加密的访问笔记
	 */
	public String			pwdShareId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getNoteBody() {
		return noteBody;
	}

	public void setNoteBody(String noteBody) {
		this.noteBody = noteBody;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getExtjson() {
		return extjson;
	}

	public void setExtjson(String extjson) {
		this.extjson = extjson;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	public String getNotebookId() {
		return notebookId;
	}

	public void setNotebookId(String notebookId) {
		this.notebookId = notebookId;
	}

	public String getPwdShareId() {
		return pwdShareId;
	}

	public void setPwdShareId(String pwdShareId) {
		this.pwdShareId = pwdShareId;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", noteTitle=" + noteTitle + ", noteBody=" + noteBody + ", userId=" + userId
							+ ", notebookId=" + notebookId + ", enclosure=" + enclosure + ", type=" + type
							+ ", delFlag=" + delFlag + ", extjson=" + extjson + ", label=" + label + ", blogId="
							+ blogId + ", pwdShareId=" + pwdShareId + "]";
	}

}