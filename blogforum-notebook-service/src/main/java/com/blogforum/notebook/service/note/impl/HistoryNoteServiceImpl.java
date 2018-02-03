package com.blogforum.notebook.service.note.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogforum.common.tools.UUIDCreateUtils;
import com.blogforum.notebook.common.enums.IsDelFlagEnum;
import com.blogforum.notebook.common.page.Page;
import com.blogforum.notebook.dao.mapper.HistoryNoteMapper;
import com.blogforum.notebook.pojo.entity.HistoryNote;
import com.blogforum.notebook.pojo.entity.NoteBody;
import com.blogforum.notebook.pojo.entity.NoteTitle;
import com.blogforum.notebook.service.CrudService;
import com.blogforum.notebook.service.note.HistoryNoteService;

@Service
public class HistoryNoteServiceImpl extends CrudService<HistoryNote> implements HistoryNoteService {

	@Autowired
	private HistoryNoteMapper historyNoteMapper;
	
	@Override
	public Page<HistoryNote> getHistoryNotePage(Page<HistoryNote> page, String noteBodyId,String userId) {
		HistoryNote historyNoteCondition = new HistoryNote(userId, noteBodyId);
		historyNoteCondition.setPage(page);
		page.setList(historyNoteMapper.queryByNoteBodyId(historyNoteCondition));
		return page;
	}

	
	/**
	 * 拼装历史笔记
	 * @param noteBody
	 * @return
	 * @author: wwd
	 * @time: 2018年2月3日
	 */
	public HistoryNote buildHistoryNote(NoteBody noteBody,NoteTitle noteTitle){
		HistoryNote historyNote = new HistoryNote();
		historyNote.setId(UUIDCreateUtils.getUUID());
		historyNote.setDelFlag(IsDelFlagEnum.N.getValue());
		historyNote.setEnclosure(noteBody.getEnclosure());
		historyNote.setExtjson(noteBody.getExtjson());
		historyNote.setImageContext(noteBody.getImageContext());
		historyNote.setLabel(noteBody.getLabel());
		historyNote.setMdNoteBody(noteBody.getMdNoteBody());
		historyNote.setNoteBody(noteBody.getNoteBody());
		historyNote.setNoteBodyId(noteBody.getId());
		historyNote.setNoteTitleId(noteBody.getNoteTitleId());
		historyNote.setNoteTitleName(noteBody.getNoteTitleName());
		historyNote.setTextType(noteBody.getTextType());
		historyNote.setLastDate(noteBody.getUpdateDate());
		historyNote.setUserId(noteBody.getUserId());
		historyNote.setNoteContext(noteTitle.getNoteContext());
		historyNote.setType(noteTitle.getType());
		historyNote.setNoteBookId(noteTitle.getNoteBookId());
		historyNote.setNoteBookName(noteTitle.getNoteTitle());
		return historyNote;
	}
	
}
