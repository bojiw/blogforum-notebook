package com.blogforum.notebook.service.note;

import java.util.List;

import com.blogforum.notebook.pojo.entity.NoteBook;
import com.blogforum.notebook.service.BaseService;

public interface NoteBookService extends BaseService<NoteBook> {
	
	
	public List<NoteBook> queryListByParentId(String parentId);
	

}
