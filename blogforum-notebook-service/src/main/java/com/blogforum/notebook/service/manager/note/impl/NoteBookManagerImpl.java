package com.blogforum.notebook.service.manager.note.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogforum.common.tools.blogforumResult;
import com.blogforum.notebook.pojo.vo.NoteBookVO;
import com.blogforum.notebook.service.manager.note.NoteBookManager;
import com.blogforum.notebook.service.note.NoteBookService;
import com.blogforum.notebook.service.note.NoteTitleService;
import com.blogforum.sso.facade.model.UserVO;

@Service
public class NoteBookManagerImpl implements NoteBookManager {

	@Autowired
	private NoteBookService noteBookService;
	
	@Autowired
	private NoteTitleService noteTitleService;
	
	
	@Override
	public blogforumResult updateNoteBook(String id, UserVO user, String name) {
		
		NoteBookVO updateBook = noteBookService.updateBook(id, user, name);
		//更新笔记头部表里存放的笔记本名
		noteTitleService.updateBookName(id, user.getId(), name);
		return blogforumResult.ok(updateBook);
	}

}
