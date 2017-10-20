package com.blogforum.notebook.service.note.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogforum.notebook.dao.mapper.NoteMapper;
import com.blogforum.notebook.pojo.entity.Note;
import com.blogforum.notebook.service.CrudService;
import com.blogforum.notebook.service.note.NoteService;

@Service
public class NoteServiceImpl extends CrudService<Note> implements NoteService{

	@Autowired
	private NoteMapper noteMapper;
	
	@Override
	public int countByNoteBookId(String noteBookId) {
		return noteMapper.countByNoteBookId(noteBookId);
	}


}
