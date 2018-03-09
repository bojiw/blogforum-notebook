package com.blogforum.notebook.service.facade.note.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.blogforum.common.enums.BizErrorEnum;
import com.blogforum.common.model.ErrorContext;
import com.blogforum.common.model.Result;
import com.blogforum.notebook.common.enums.IsDelFlagEnum;
import com.blogforum.notebook.facade.note.NoteServerFacade;
import com.blogforum.notebook.pojo.vo.NoteDateIn;
import com.blogforum.notebook.service.note.NoteTitleService;

public class NoteServerFacadeImpl implements NoteServerFacade {

	@Autowired
	private NoteTitleService noteTitleService;

	@Override
	public Result<Integer> getAllNoteCount(IsDelFlagEnum delFlag) {
		Integer countNote = noteTitleService.countNote((delFlag == null) ? null : delFlag.getValue());
		return new Result<Integer>(true, countNote);
	}

	@Override
	public Result<Integer> getDateInNote(Date startDate, Date endDate, IsDelFlagEnum delFlag) {

		if (delFlag == null) {
			return new Result<Integer>(false, new ErrorContext(BizErrorEnum.ILLEGAL_PARAMETER, "delFlag不可为空"), 0);
		}
		NoteDateIn noteDateIn = new NoteDateIn(startDate, endDate, delFlag.getValue());
		Integer countDateInNote = noteTitleService.countDateInNote(noteDateIn);
		return new Result<Integer>(true, countDateInNote);
	}

}
