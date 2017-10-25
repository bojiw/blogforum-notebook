package com.blogforum.notebook.service.note;

import java.util.List;

import com.blogforum.notebook.pojo.entity.Note;
import com.blogforum.notebook.service.BaseService;

public interface NoteService extends BaseService<Note> {

	public int countByNoteBookId(String noteBookId);
	
	public List<Note> queryNoteTitleList(String noteBookId);
}
