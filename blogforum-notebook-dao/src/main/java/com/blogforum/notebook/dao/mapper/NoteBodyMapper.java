package com.blogforum.notebook.dao.mapper;

import com.blogforum.notebook.pojo.entity.NoteBody;

public interface NoteBodyMapper extends CrudMapper<NoteBody> {

	
	public NoteBody getByNoteTitleId(String noteTitleId);
}
