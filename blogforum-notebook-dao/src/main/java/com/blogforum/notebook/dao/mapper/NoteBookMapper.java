package com.blogforum.notebook.dao.mapper;

import java.util.List;

import com.blogforum.notebook.pojo.entity.NoteBook;

public interface NoteBookMapper extends CrudMapper<NoteBook> {
	public List<NoteBook> queryListByParentId(String parentId);

}
