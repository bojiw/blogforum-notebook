package com.blogforum.notebook.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blogforum.common.tools.UUIDCreateUtils;
import com.blogforum.common.tools.blogforumResult;
import com.blogforum.notebook.pojo.entity.Note;
import com.blogforum.notebook.service.note.NoteService;

@Controller
@RequestMapping("/note")
public class NoteController {
	@Autowired
	private NoteService noteService;
	
	
	@RequestMapping(value = "/addNote" , method = RequestMethod.POST)
	@ResponseBody
	public blogforumResult addNote(Note note){
		note.setId(UUIDCreateUtils.getUUID());
		//伪代码
		note.setUserId("20170905C132BB4668E64566834B18B5BC0307DB57810067");
		noteService.save(note);
		return blogforumResult.ok(note);
	}
	
	@RequestMapping(value = "/updateNote", method = RequestMethod.POST)
	@ResponseBody
	public blogforumResult updateNote(Note note){
		noteService.update(note);
		return blogforumResult.ok();
	}
	

}
