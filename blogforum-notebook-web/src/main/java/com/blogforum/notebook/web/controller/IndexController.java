package com.blogforum.notebook.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blogforum.notebook.common.page.Page;
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
	public String index(ModelMap map,HttpServletRequest request, HttpServletResponse response){
		List<NoteBook> books = noteBookService.queryListByParentId("0");
		
		Page<Note> notes = noteService.queryList(new Page<Note>(request, response),new Note());
		map.put("noteBooks", books);
		map.put("notes", notes);
		return ViewConstant.INDEX;
	}

	@RequestMapping("/simplenote")
	public String simpleNote(ModelMap map,String noteBookName,String noteBookId,String noteId){
		map.put("noteBookId", noteBookId);
		map.put("noteBookName", noteBookName);
		map.put("noteId", noteId);
		return ViewConstant.SIMPLENOTE;
	}
	
	@RequestMapping("/nullnote")
	public String nullNote(ModelMap map){
		return ViewConstant.NULLNOTE;
	}
	
	@RequestMapping("/markdownnote")
	public String markDownNote(ModelMap map,String noteBookName,String noteBookId,String noteId){
		map.put("noteBookId", noteBookId);
		map.put("noteBookName", noteBookName);
		map.put("noteId", noteId);
		return ViewConstant.MARKDOWNNOTE;
	}
	
}
