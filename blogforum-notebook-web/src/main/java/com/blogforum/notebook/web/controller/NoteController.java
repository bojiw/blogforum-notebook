package com.blogforum.notebook.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blogforum.common.tools.blogforumResult;
import com.blogforum.notebook.pojo.entity.NoteBook;
import com.blogforum.notebook.service.note.NoteBookService;

@Controller
@RequestMapping("/note")
public class NoteController {
	@Autowired
	private NoteBookService noteBookService;
	
	@RequestMapping("/getNoteBooks")
	@ResponseBody
	public blogforumResult getNoteBooks(){
		List<NoteBook> books = noteBookService.queryList();
		return blogforumResult.ok(books);
	}
	

}
