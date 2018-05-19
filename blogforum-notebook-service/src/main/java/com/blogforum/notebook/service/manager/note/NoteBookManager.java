package com.blogforum.notebook.service.manager.note;

import com.blogforum.common.tools.blogforumResult;
import com.blogforum.sso.facade.model.UserVO;

public interface NoteBookManager {
	
	blogforumResult updateNoteBook(String id, UserVO user, String name);

}
