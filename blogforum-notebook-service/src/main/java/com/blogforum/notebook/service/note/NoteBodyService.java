package com.blogforum.notebook.service.note;

import java.util.List;

import com.blogforum.notebook.pojo.entity.NoteBody;
import com.blogforum.notebook.service.BaseService;

public interface NoteBodyService extends BaseService<NoteBody> {

	public List<NoteBody> getByNoteTitleId(String noteTitleId);
}
