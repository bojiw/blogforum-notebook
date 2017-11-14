package com.blogforum.notebook.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blogforum.common.enums.BizErrorEnum;
import com.blogforum.common.tools.BaseConverter;
import com.blogforum.common.tools.UUIDCreateUtils;
import com.blogforum.common.tools.blogforumResult;
import com.blogforum.notebook.pojo.entity.NoteBook;
import com.blogforum.notebook.pojo.entity.NoteTitle;
import com.blogforum.notebook.pojo.vo.NoteBookVO;
import com.blogforum.notebook.service.note.NoteBookService;
import com.blogforum.notebook.service.note.NoteTitleService;
import com.blogforum.sso.facade.model.UserVO;


@Controller
@RequestMapping("/noteBook")
public class NoteBookController {
	@Autowired
	private NoteBookService noteBookService;
	
	@Autowired
	private NoteTitleService	noteTitleService;
	
	@RequestMapping("/getNoteBook/{parentId}")
	@ResponseBody
	public blogforumResult getNoteBooks( @PathVariable String parentId,HttpServletRequest request){
		UserVO user = (UserVO) request.getAttribute("user");
		NoteBook noteBook = new NoteBook(user.getId(), "0");
		List<NoteBook> books = noteBookService.queryListByParentId(noteBook);
		BaseConverter<NoteBook, NoteBookVO> noteBookConver = new BaseConverter<>();
		List<NoteBookVO> noteBooks = noteBookConver.convertList(books, NoteBookVO.class);
		for (NoteBookVO noteBookVO : noteBooks) {
			noteBookVO.setNoteCount(noteTitleService.countByNoteBookId(noteBookVO.getId()));
		}
		return blogforumResult.ok(noteBooks);
	}
	
	@RequestMapping( value = "/addNoteBook" ,method = RequestMethod.POST) 
	@ResponseBody
	public blogforumResult addNoteBooks(String name,@RequestParam(defaultValue="0") String parentId,HttpServletRequest request){
		UserVO user = (UserVO) request.getAttribute("user");
		NoteBook noteBook = new NoteBook();
		String id = UUIDCreateUtils.getUUID();
		noteBook.setId(id);
		noteBook.setParentId(parentId);
		noteBook.setName(name);
		noteBook.setUserId(user.getId());
		noteBook.setCreateDate(new Date());
		noteBook.setUpdateDate(new Date());
		noteBook.setParentId(parentId);
		noteBookService.save(noteBook);
		//如果父节点不是0代表是有父节点 更新父节点有子节点状态
		if (!StringUtils.equals(parentId, "0")) {
			NoteBook parentNoteBook = noteBookService.getById(noteBook);
			if (!parentNoteBook.getHaveNode()) {
				parentNoteBook.setHaveNode(true);
				noteBookService.update(parentNoteBook);
			}
		}
		BaseConverter<NoteBook, NoteBookVO> noteBookConverter = new BaseConverter<>();
		NoteBookVO noteBookVO = noteBookConverter.convert(noteBook, NoteBookVO.class);
		noteBookVO.setNoteCount(0);
		
		return blogforumResult.ok(noteBookVO);
	}
	
	
	@RequestMapping( value = "/updateNoteBook",method = RequestMethod.POST)
	@ResponseBody
	public blogforumResult updateNoteBooks(String id,String name,HttpServletRequest request){
		UserVO user = (UserVO) request.getAttribute("user");
		NoteBook book = new NoteBook(user.getId(), id, null);
		NoteBook noteBook = noteBookService.getById(book);
		if (null == noteBook) {
			return blogforumResult.build(BizErrorEnum.SYS_EXCEPTION, "系统异常请联系管理员!");
		}
		noteBook.setName(name);
		noteBookService.update(noteBook);
		
		BaseConverter<NoteBook, NoteBookVO> noteBookConverter = new BaseConverter<>();
		NoteBookVO noteBookVO = noteBookConverter.convert(noteBook, NoteBookVO.class);
		noteBookVO.setNoteCount(0);
		return blogforumResult.ok(noteBookVO);
	}
	
	@RequestMapping("/deleteNoteBook")
	@ResponseBody
	public blogforumResult deleteNoteBook(String id,String parentId,HttpServletRequest request){

		Boolean isNode = true;
		//删除笔记这里需要考虑子笔记本和笔记的删除
		UserVO user = (UserVO) request.getAttribute("user");
		NoteBook book = new NoteBook(user.getId(), id,null);
		NoteBook noteBook = noteBookService.getById(book);
		if (noteBook == null) {
			blogforumResult.build(BizErrorEnum.ILLEGAL_PARAMETER, "找不到该笔记本!!!");
		}
		if (noteBook.getHaveNode()) {
			blogforumResult.build(BizErrorEnum.ILLEGAL_PARAMETER, "该笔记本下有子笔记不可删除!!!");
		}
		NoteTitle title = new NoteTitle(user.getId(), id);
		List<NoteTitle> notes = noteTitleService.queryList(title);
		if(CollectionUtils.isNotEmpty(notes)){
			blogforumResult.build(BizErrorEnum.ILLEGAL_PARAMETER, "该笔记本下有笔记不可删除!!!");
		}

		noteBookService.delete(book);
		//如果笔记本的父id不是0代表有父笔记本 判断父笔记本下是否有子笔记本 如果没有则把父笔记本的有子节点设置为false
		if (!StringUtils.equals(noteBook.getParentId(), "0")) {
			//通过父笔记本id看看是否能获取笔记本 如果没有代表父笔记本已经没有子节点了 设置为false
			NoteBook prevNoteBook = new NoteBook(user.getId(), book.getParentId());
			List<NoteBook> noteBooks = noteBookService.queryListByParentId(prevNoteBook);
			if (CollectionUtils.isEmpty(noteBooks)) {
				NoteBook parent = new NoteBook(user.getId(), book.getParentId(), null);
				NoteBook parentNoteBook = noteBookService.getById(parent);
				parentNoteBook.setHaveNode(false);
				noteBookService.update(parentNoteBook);
				isNode = false;
			}
		}
		return blogforumResult.ok(isNode);
	}
	
	
	
}
