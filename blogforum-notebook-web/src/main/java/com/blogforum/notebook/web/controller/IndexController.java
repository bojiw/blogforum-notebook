package com.blogforum.notebook.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blogforum.common.tools.BaseConverter;
import com.blogforum.notebook.common.page.Page;
import com.blogforum.notebook.pojo.entity.Note;
import com.blogforum.notebook.pojo.entity.NoteBook;
import com.blogforum.notebook.pojo.vo.NoteBookVO;
import com.blogforum.notebook.pojo.vo.NoteVO;
import com.blogforum.notebook.service.note.NoteBookService;
import com.blogforum.notebook.service.note.NoteService;
import com.blogforum.notebook.web.constant.ViewConstant;
import com.google.common.collect.Lists;

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
		BaseConverter<NoteBook, NoteBookVO> converter = new BaseConverter<>();
		List<NoteBookVO> notebooks = converter.convertList(books, NoteBookVO.class);
		for (NoteBookVO noteBookVO : notebooks) {
			noteBookVO.setNoteCount(noteService.countByNoteBookId(noteBookVO.getId()));
		}
		Page<NoteVO>pageNoteVO = new Page<>();
		List<NoteVO> noteVOs = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(notebooks)) {
			Note note = new Note();
			note.setNoteBookId(notebooks.get(0).getId());
			Page<Note> page = noteService.queryList(new Page<Note>(request, response),note);
			BeanUtils.copyProperties(page, pageNoteVO,"list");
			BaseConverter<Note, NoteVO> noteConverter = new BaseConverter<>();
			noteVOs = noteConverter.convertList(page.getList(), NoteVO.class);
			if (CollectionUtils.isNotEmpty(noteVOs)) {
				for (NoteVO noteVO : noteVOs) {
					NoteBook noteBook = noteBookService.getById(noteVO.getNoteBookId());
					noteVO.setNoteBookName(noteBook.getName());
				}
			}
		}
		pageNoteVO.setList(noteVOs);
		map.put("noteBooks", notebooks);
		map.put("notes", pageNoteVO);
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
