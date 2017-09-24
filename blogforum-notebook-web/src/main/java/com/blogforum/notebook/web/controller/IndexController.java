package com.blogforum.notebook.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blogforum.notebook.pojo.entity.Note;
import com.blogforum.notebook.pojo.entity.NoteBook;
import com.blogforum.notebook.service.note.NoteBookService;
import com.blogforum.notebook.service.note.NoteService;
import com.blogforum.notebook.web.constant.ViewConstant;

/**
 * 首页
 * @author wwd
 *
 */
@Controller
public class IndexController {
	@Autowired
	private NoteBookService noteBookService;
	@Autowired
	private NoteService noteService;
	
	@RequestMapping("/")
	public String index(ModelMap map){
		List<NoteBook> books = noteBookService.queryListByParentId("0");
		List<Note> notes = noteService.queryList();
		map.put("noteBooks", books);
		map.put("notes", notes);
		return ViewConstant.INDEX;
	}

	@RequestMapping("/simplenote")
	public String simpleNote(){
		return ViewConstant.SIMPLENOTE;
	}
	
	@RequestMapping("/nullnote")
	public String nullNote(){
		return ViewConstant.NULLNOTE;
	}
	
	@RequestMapping("/markdownnote")
	public String markDownNote(){
		return ViewConstant.MARKDOWNNOTE;
	}
	
}
