package com.blogforum.notebook.web.controller;

import java.util.Iterator;
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
import com.blogforum.notebook.pojo.entity.NoteBody;
import com.blogforum.notebook.pojo.entity.NoteTitle;
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
			return blogforumResult.build(BizError.ILLEGAL_PARAMETER, "没有该笔记!");
		}
		noteTitle.setNoteTitle(note.getNoteTitle());
		noteTitle.setNoteContext(note.getNoteContext());
		noteTitleService.update(noteTitle);
		List<NoteBody> oldNoteBody = noteBodyService.getByNoteTitleId(note.getNoteTitleId());
		if (CollectionUtils.isEmpty(oldNoteBody)) {
			return blogforumResult.build(BizError.SYS_EXCEPTION, "系统异常,没有对应的笔记内容!");
		}
		Iterator<NoteBody> iterator = oldNoteBody.iterator();
		NoteBody noteBody = iterator.next();
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

}
