package com.blogforum.notebook.web.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blogforum.common.enums.BizError;
import com.blogforum.common.tools.BaseConverter;
import com.blogforum.common.tools.UUIDCreateUtils;
import com.blogforum.common.tools.blogforumResult;
import com.blogforum.notebook.pojo.entity.NoteBook;
import com.blogforum.notebook.pojo.vo.NoteBookVO;
import com.blogforum.notebook.service.note.NoteBookService;

@Controller
@RequestMapping("/noteBook")
public class NoteBookController {
	@Autowired
	private NoteBookService noteBookService;
	
	@RequestMapping("/getNoteBook/{parentId}")
	@ResponseBody
	public blogforumResult getNoteBooks( @PathVariable String parentId){
		List<NoteBook> books = noteBookService.queryListByParentId(parentId);
		BaseConverter<NoteBook, NoteBookVO> noteBookConver = new BaseConverter<>();
		List<NoteBookVO> noteBooks = noteBookConver.convertList(books, NoteBookVO.class);
		return blogforumResult.ok(noteBooks);
	}
	
	@RequestMapping( value = "/addNoteBook" ,method = RequestMethod.POST) 
	@ResponseBody
	public blogforumResult addNoteBooks(String name,@RequestParam(defaultValue="0") String parentId){
		NoteBook noteBook = new NoteBook();
		String id = UUIDCreateUtils.getUUID();
		noteBook.setId(id);
		noteBook.setParentId(parentId);
		noteBook.setName(name);
		noteBook.setUserId(UUIDCreateUtils.getUUID());
		noteBook.setCreateDate(new Date());
		noteBook.setUpdateDate(new Date());
		noteBook.setParentId(parentId);
		noteBookService.save(noteBook);
		if (!StringUtils.equals(parentId, "0")) {
			NoteBook parentNoteBook = noteBookService.getById(parentId);
			if (!parentNoteBook.getHaveNode()) {
				parentNoteBook.setHaveNode(true);
				noteBookService.update(parentNoteBook);
			}
		}
		return blogforumResult.ok(noteBook);
	}
	
	
	@RequestMapping( value = "/updateNoteBook",method = RequestMethod.POST)
	@ResponseBody
	public blogforumResult updateNoteBooks(String id,String name){
		NoteBook noteBook = noteBookService.getById(id);
		if (null == noteBook) {
			return blogforumResult.build(BizError.SYS_EXCEPTION, "系统异常请联系管理员!");
		}
		noteBook.setName(name);
		noteBookService.update(noteBook);
		
		return blogforumResult.ok(noteBook);
	}
	
	@RequestMapping("/deleteNoteBook")
	@ResponseBody
	public blogforumResult deleteNoteBook(String id,String parentId){
		//伪代码 后续对接sso系统 删除和设置再加一个userId的条件
		Boolean isNode = true;
		//删除笔记这里需要考虑子笔记本和笔记的删除
		List<NoteBook> delnoteBooks = noteBookService.queryListByParentId(id);
		if(CollectionUtils.isNotEmpty(delnoteBooks)){
			blogforumResult.build(BizError.ILLEGAL_PARAMETER, "该笔记本下有笔记不可删除!!!");
		}
		NoteBook noteBook = noteBookService.getById(id);
		if (noteBook.getHaveNode()) {
			blogforumResult.build(BizError.ILLEGAL_PARAMETER, "该笔记本下有子笔记不可删除!!!");
		}
		noteBookService.delete(id);
		if (!StringUtils.equals(parentId, "0")) {
			List<NoteBook> noteBooks = noteBookService.queryListByParentId(parentId);
			if (CollectionUtils.isEmpty(noteBooks)) {
				NoteBook parentNoteBook = noteBookService.getById(parentId);
				parentNoteBook.setHaveNode(false);
				noteBookService.update(parentNoteBook);
				isNode = false;
			}
		}
		return blogforumResult.ok(isNode);
	}
	
	
	
}
