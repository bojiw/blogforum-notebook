package com.blogforum.notebook.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blogforum.common.enums.BizErrorEnum;
import com.blogforum.common.tools.BaseConverter;
import com.blogforum.common.tools.UUIDCreateUtils;
import com.blogforum.common.tools.blogforumResult;
import com.blogforum.notebook.common.page.Page;
import com.blogforum.notebook.pojo.entity.NoteBody;
import com.blogforum.notebook.pojo.entity.NoteTitle;
import com.blogforum.notebook.pojo.vo.NoteBodyVO;
import com.blogforum.notebook.pojo.vo.NoteTitleVO;
import com.blogforum.notebook.pojo.vo.NoteVO;
import com.blogforum.notebook.service.note.NoteBodyService;
import com.blogforum.notebook.service.note.NoteTitleService;
import com.blogforum.notebook.web.enums.IsDelFlag;

@Controller
@RequestMapping("/note")
public class NoteController {
	@Autowired
	private NoteTitleService	noteTitleService;
	@Autowired
	private NoteBodyService		noteBodyService;

	@RequestMapping(value = "/addNote", method = RequestMethod.POST)
	@ResponseBody
	public blogforumResult addNote(NoteTitle noteTitle) {
		noteTitle.setDelFlag(IsDelFlag.N.getValue());
		//伪代码
		noteTitle.setUserId("20170905C132BB4668E64566834B18B5BC0307DB57810067");
		noteTitle.setId(UUIDCreateUtils.getUUID());
		noteTitleService.save(noteTitle);
		NoteBody noteBody = new NoteBody();
		noteBody.setId(UUIDCreateUtils.getUUID());
		noteBody.setDelFlag(IsDelFlag.N.getValue());
		noteBody.setNoteTitleId(noteTitle.getId());
		noteBodyService.save(noteBody);
		return blogforumResult.ok(noteTitle);
	}

	@RequestMapping(value = "/updateNote", method = RequestMethod.POST)
	@ResponseBody
	public blogforumResult updateNote(NoteVO note) {
		NoteTitle noteTitle = noteTitleService.getById(note.getNoteTitleId());

		if (noteTitle == null) {
			return blogforumResult.build(BizErrorEnum.ILLEGAL_PARAMETER, "没有该笔记!");
		}
		noteTitle.setNoteTitle(note.getNoteTitle());
		noteTitle.setNoteContext(note.getNoteContext());
		noteTitleService.update(noteTitle);
		NoteBody noteBody = noteBodyService.getByNoteTitleId(note.getNoteTitleId());
		if (noteBody == null) {
			return blogforumResult.build(BizErrorEnum.SYS_EXCEPTION, "系统异常,没有对应的笔记内容!");
		}
		noteBody.setNoteBody(note.getNoteBody());
		noteBody.setMdNoteBody(note.getMdNoteBody());
		noteBody.setTextType(note.getTextType());
		noteBody.setLabel(note.getLabel());
		noteBodyService.update(noteBody);
		return blogforumResult.ok();
	}

	@RequestMapping(value = "/getNoteTitleList", method = RequestMethod.GET)
	@ResponseBody
	public blogforumResult getNoteTitleList(NoteTitle noteTitle, HttpServletRequest request,
						HttpServletResponse response) {
		Page<NoteTitle> page = noteTitleService.queryList(new Page<NoteTitle>(request, response), noteTitle);

		Page<NoteTitleVO> pageNoteTitleVO = new Page<>();
		BeanUtils.copyProperties(page, pageNoteTitleVO, "list");
		BaseConverter<NoteTitle, NoteTitleVO> noteConverter = new BaseConverter<>();
		List<NoteTitleVO> noteVOs = noteConverter.convertList(page.getList(), NoteTitleVO.class);
		pageNoteTitleVO.setList(noteVOs);
		return blogforumResult.ok(pageNoteTitleVO);
	}
	
	@RequestMapping(value = "/getNoteBody", method = RequestMethod.GET)
	@ResponseBody
	public blogforumResult getNoteBody(NoteTitle noteTitle, HttpServletRequest request,
						HttpServletResponse response){
		NoteBody noteBody = noteBodyService.getByNoteTitleId(noteTitle.getId());
		if (noteBody == null) {
			return blogforumResult.build(BizErrorEnum.SYS_EXCEPTION, "系统异常,没有对应的笔记内容!");
		}
		BaseConverter<NoteBody, NoteBodyVO> noteConverter = new BaseConverter<>();
		NoteBodyVO noteBodyVO = noteConverter.convert(noteBody, NoteBodyVO.class);
		NoteTitle noteTitleDO = noteTitleService.getById(noteTitle.getId());
		noteBodyVO.setNoteTitle(noteTitleDO.getNoteTitle());
		return blogforumResult.ok(noteBodyVO);
	}
	
	@RequestMapping(value = "/deleteNote", method = RequestMethod.POST)
	@ResponseBody
	public blogforumResult deleteNote(String noteId){
		NoteBody noteBody = noteBodyService.getByNoteTitleId(noteId);
		if (noteBody == null) {
			blogforumResult.build(BizErrorEnum.SYS_EXCEPTION, "找不到对应的内容笔记");
		}
		noteBody.setDelFlag("Y");
		noteBodyService.update(noteBody);
		NoteTitle noteTitle = noteTitleService.getById(noteId);
		if (noteTitle == null) {
			blogforumResult.build(BizErrorEnum.SYS_EXCEPTION, "找不到对应的笔记");
		}
		noteTitle.setDelFlag("Y");
		noteTitleService.update(noteTitle);
		
		return blogforumResult.ok();
		
	}
	
	

}
