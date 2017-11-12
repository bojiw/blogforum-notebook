package com.blogforum.notebook.service.note.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogforum.notebook.dao.mapper.NoteBookMapper;
import com.blogforum.notebook.pojo.entity.NoteBook;
import com.blogforum.notebook.service.CrudService;
import com.blogforum.notebook.service.note.NoteBookService;
@Service
public class NoteBookServiceImpl extends CrudService<NoteBook> implements NoteBookService {
	@Autowired
	protected NoteBookMapper noteBookMapper;
	@Override
	public List<NoteBook> queryListByParentId(NoteBook noteBook) {
		List<NoteBook> noteBooks = noteBookMapper.queryListByParentId(noteBook);
		return noteBooks;
	}

	
}
