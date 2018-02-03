package com.blogforum.notebook.service.note;

import com.blogforum.notebook.common.page.Page;
import com.blogforum.notebook.pojo.entity.HistoryNote;
import com.blogforum.notebook.pojo.entity.NoteBody;
import com.blogforum.notebook.pojo.entity.NoteTitle;
import com.blogforum.notebook.service.BaseService;

public interface HistoryNoteService extends BaseService<HistoryNote> {


	/**
	 * 获取分页历史笔记
	 * @param page
	 * @param noteBodyId
	 * @return
	 * @author: wwd
	 * @time: 2018年2月1日
	 */
	Page<HistoryNote> getHistoryNotePage(Page<HistoryNote> page, String noteBodyId,String userId);
	
	
	/**
	 * 通过笔记内容拼装出历史笔记表
	 * @param noteBody
	 * @return
	 * @author: wwd
	 * @time: 2018年2月3日
	 */
	HistoryNote buildHistoryNote(NoteBody noteBody,NoteTitle noteTitle);
}
