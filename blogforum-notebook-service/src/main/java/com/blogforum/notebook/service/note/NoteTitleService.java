package com.blogforum.notebook.service.note;

import com.blogforum.notebook.pojo.entity.NoteTitle;
import com.blogforum.notebook.service.BaseService;

public interface NoteTitleService extends BaseService<NoteTitle> {
	
	/**
	 * 获取笔记本下有多少笔记
	 * @param noteBookId
	 * @return
	 * @author: wwd
	 * @time: 2017年10月25日
	 */
	public int countByNoteBookId(String noteBookId);
	
}
