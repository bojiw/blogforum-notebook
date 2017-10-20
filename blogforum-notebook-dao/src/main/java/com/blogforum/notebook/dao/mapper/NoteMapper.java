package com.blogforum.notebook.dao.mapper;

import com.blogforum.notebook.pojo.entity.Note;

public interface NoteMapper extends CrudMapper<Note> {
	
	public int countByNoteBookId(String noteBookId);

}
