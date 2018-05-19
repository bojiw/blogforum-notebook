package com.blogforum.notebook.dao.mapper;

import com.blogforum.notebook.pojo.entity.NoteTitle;
import com.blogforum.notebook.pojo.vo.NoteDateIn;

public interface NoteTitleMapper extends CrudMapper<NoteTitle> {

	/**
	 * 获取笔记本下有多少笔记
	 * @param noteBookId
	 * @return
	 * @author: wwd
	 * @time: 2017年10月25日
	 */
	Integer countByNoteBookId(String noteBookId);
	
	
	/**
	 * 获取对应状态的笔记 为空获取所有
	 * @param delFlag
	 * @return
	 * @author: wwd
	 * @time: 2018年3月3日
	 */
	Integer countNote(String delFlag);
	
	/**
	 * 获取时间段 指定状态的用户
	 * @param noteDateIn
	 * @return
	 * @author: wwd
	 * @time: 2018年3月3日
	 */
	Integer countDateInNote(NoteDateIn noteDateIn);
	
	/**
	 * 更新笔记本名
	 * @param noteTitle
	 * @return
	 * @author: wwd
	 * @time: 2018年5月19日
	 */
	Integer updateBookName(NoteTitle noteTitle);

}
