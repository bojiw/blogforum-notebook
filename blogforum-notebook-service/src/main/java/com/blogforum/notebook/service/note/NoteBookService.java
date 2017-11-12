package com.blogforum.notebook.service.note;

import java.util.List;

import com.blogforum.notebook.pojo.entity.NoteBook;
import com.blogforum.notebook.service.BaseService;

public interface NoteBookService extends BaseService<NoteBook> {
	
	/**
	 * 根据父笔记本id和用户id获取笔记本
	 * @param noteBook
	 * @return
	 * @author: wwd
	 * @time: 2017年11月11日
	 */
	public List<NoteBook> queryListByParentId(NoteBook noteBook);
	

}
