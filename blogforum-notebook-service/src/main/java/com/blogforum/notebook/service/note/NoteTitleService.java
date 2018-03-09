package com.blogforum.notebook.service.note;

import com.blogforum.notebook.pojo.entity.NoteTitle;
import com.blogforum.notebook.pojo.vo.NoteDateIn;
import com.blogforum.notebook.pojo.vo.NoteTitleVO;
import com.blogforum.notebook.pojo.vo.NoteVO;
import com.blogforum.notebook.service.BaseService;
import com.blogforum.sso.facade.model.UserVO;

public interface NoteTitleService extends BaseService<NoteTitle> {

	/**
	 * 获取笔记本下有多少笔记
	 * 
	 * @param noteBookId
	 * @return
	 * @author: wwd
	 * @time: 2017年10月25日
	 */
	int countByNoteBookId(String noteBookId);

	/**
	 * 添加笔记内容
	 * 
	 * @param user
	 * @param noteTitle
	 * @return
	 * @author: wwd
	 * @time: 2017年12月2日
	 */
	NoteTitleVO addNote(UserVO user, NoteTitle noteTitle);

	
	/**
	 * 更新笔记
	 * @param user
	 * @param note
	 * @author: wwd
	 * @time: 2017年12月2日
	 */
	void updateNote(UserVO user, NoteVO note);
	
	
	/**
	 * 删除对应笔记
	 * @param user
	 * @param noteId
	 * @author: wwd
	 * @time: 2017年12月2日
	 */
	NoteTitle deleteNote(UserVO user,String noteTitleId);
	
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

}
