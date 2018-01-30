package com.blogforum.notebook.dao.mapper;

import com.blogforum.notebook.pojo.entity.HistoryNote;

public interface HistoryNoteMapper extends CrudMapper<HistoryNote> {

	/**
	 * 查询笔记下的历史笔记
	 * @param historyNote
	 * @return
	 * @author: wwd
	 * @time: 2018年1月29日
	 */
	HistoryNote getByNoteBodyId(HistoryNote historyNote);
	
	
}
