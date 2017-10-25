package com.blogforum.notebook.service.note.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogforum.notebook.dao.mapper.NoteTitleMapper;
import com.blogforum.notebook.pojo.entity.NoteTitle;
import com.blogforum.notebook.service.CrudService;
import com.blogforum.notebook.service.note.NoteTitleService;

@Service
public class NoteTitleServiceImpl extends CrudService<NoteTitle> implements NoteTitleService {


	@Autowired
	private NoteTitleMapper noteTitleMapper;

	@Override
	public int countByNoteBookId(String noteBookId) {
		return noteTitleMapper.countByNoteBookId(noteBookId);
	}

	

}
