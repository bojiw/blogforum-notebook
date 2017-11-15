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
import com.blogforum.notebook.pojo.entity.NoteBook;
import com.blogforum.notebook.pojo.entity.NoteTitle;
import com.blogforum.notebook.pojo.vo.NoteBookVO;
import com.blogforum.notebook.pojo.vo.NoteTitleVO;
import com.blogforum.notebook.service.note.NoteBookService;
import com.blogforum.notebook.service.note.NoteTitleService;
import com.blogforum.notebook.web.constant.ViewConstant;
import com.blogforum.sso.facade.model.UserVO;
import com.google.common.collect.Lists;


/**
 * 首页
 * 
 * @author wwd
 *
 */
@Controller
public class IndexController {
	@Autowired
	private NoteBookService		noteBookService;
	@Autowired
	private NoteTitleService	noteTitleService;

	@RequestMapping("/")
	public String index(ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		UserVO user = (UserVO) request.getAttribute("user");
		NoteBook noteBook = new NoteBook(user.getId(), "0");
		List<NoteBook> books = noteBookService.queryListByParentId(noteBook);
		BaseConverter<NoteBook, NoteBookVO> converter = new BaseConverter<>();
		List<NoteBookVO> notebooks = converter.convertList(books, NoteBookVO.class);
		for (NoteBookVO noteBookVO : notebooks) {
			noteBookVO.setNoteCount(noteTitleService.countByNoteBookId(noteBookVO.getId()));
		}
		Page<NoteTitleVO> pageNoteVO = new Page<>();
		List<NoteTitleVO> noteTitleVOs = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(notebooks)) {
			NoteTitle noteTitle = new NoteTitle(user.getId(), notebooks.get(0).getId());
			Page<NoteTitle> page = noteTitleService.queryList(new Page<NoteTitle>(request, response), noteTitle);
			BeanUtils.copyProperties(page, pageNoteVO, "list");
			BaseConverter<NoteTitle, NoteTitleVO> noteConverter = new BaseConverter<>();
			noteTitleVOs = noteConverter.convertList(page.getList(), NoteTitleVO.class);
		}
		pageNoteVO.setList(noteTitleVOs);
		map.put("noteBooks", notebooks);
		map.put("notes", pageNoteVO);
		return ViewConstant.INDEX;
	}

	@RequestMapping("/simplenote")
	public String simpleNote(ModelMap map, String noteBookName, String noteBookId, String noteId) {
		map.put("noteBookId", noteBookId);
		map.put("noteBookName", noteBookName);
		map.put("noteId", noteId);
		return ViewConstant.SIMPLENOTE;
	}

	@RequestMapping("/nullnote")
	public String nullNote(ModelMap map) {
		return ViewConstant.NULLNOTE;
	}

	@RequestMapping("/markdownnote")
	public String markDownNote(ModelMap map, String noteBookName, String noteBookId, String noteId) {
		map.put("noteBookId", noteBookId);
		map.put("noteBookName", noteBookName);
		map.put("noteId", noteId);
		return ViewConstant.MARKDOWNNOTE;
	}

}
