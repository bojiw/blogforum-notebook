package com.blogforum.notebook.dao.mapper;

import java.util.List;

import com.blogforum.notebook.pojo.entity.NoteBody;

public interface NoteBodyMapper extends CrudMapper<NoteBody> {

	
	public List<NoteBody> getByNoteTitleId(String noteTitleId);
}
