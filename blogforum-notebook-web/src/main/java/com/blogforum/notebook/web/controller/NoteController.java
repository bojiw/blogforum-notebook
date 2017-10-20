package com.blogforum.notebook.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blogforum.common.enums.BizError;
import com.blogforum.common.tools.BaseConverter;
import com.blogforum.common.tools.UUIDCreateUtils;
import com.blogforum.common.tools.blogforumResult;
import com.blogforum.notebook.common.page.Page;
import com.blogforum.notebook.pojo.entity.Note;
import com.blogforum.notebook.pojo.entity.NoteBook;
import com.blogforum.notebook.pojo.vo.NoteVO;
import com.blogforum.notebook.service.note.NoteBookService;
import com.blogforum.notebook.service.note.NoteService;
import com.blogforum.notebook.web.enums.YesOrNo;

@Controller
@RequestMapping("/note")
public class NoteController {
	@Autowired
	private NoteService noteService;
	@Autowired
	private NoteBookService noteBookService;
	
	@RequestMapping(value = "/addNote" , method = RequestMethod.POST)
	@ResponseBody
	public blogforumResult addNote(Note note){
		note.setDelFlag(YesOrNo.N.name());
		note.setId(UUIDCreateUtils.getUUID());
		//伪代码
		note.setUserId("20170905C132BB4668E64566834B18B5BC0307DB57810067");
		noteService.save(note);
		return blogforumResult.ok(note);
	}
	
	@RequestMapping(value = "/updateNote", method = RequestMethod.POST)
	@ResponseBody
	public blogforumResult updateNote(Note note){
		Note oldNote = noteService.getById(note.getId());
		if (oldNote == null) {
			return blogforumResult.build(BizError.ILLEGAL_PARAMETER,"没有该笔记!");
		}
		note.setCreateDate(oldNote.getCreateDate());
		note.setUpdateDate(new Date());
		note.setUserId(oldNote.getUserId());
		noteService.update(note);
		return blogforumResult.ok();
	}
	
	
	@RequestMapping(value = "/getBookList", method = RequestMethod.GET)
	@ResponseBody
	public blogforumResult getBookList(Note note,HttpServletRequest request, HttpServletResponse response){
		Page<Note> page = noteService.queryList(new Page<Note>(request, response), note);
		
		Page<NoteVO>pageNoteVO = new Page<>();
		BeanUtils.copyProperties(page, pageNoteVO,"list");
		BaseConverter<Note, NoteVO> noteConverter = new BaseConverter<>();
		List<NoteVO> noteVOs = noteConverter.convertList(page.getList(), NoteVO.class);
		if (CollectionUtils.isNotEmpty(noteVOs)) {
			for (NoteVO noteVO : noteVOs) {
				NoteBook noteBook = noteBookService.getById(noteVO.getNoteBookId());
				noteVO.setNoteBookName(noteBook.getName());
			}
		}
		pageNoteVO.setList(noteVOs);
		return blogforumResult.ok(pageNoteVO);
	}

}
