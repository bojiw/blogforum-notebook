package com.blogforum.notebook.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blogforum.common.tools.UUIDCreateUtils;
import com.blogforum.notebook.pojo.entity.NoteBook;
import com.blogforum.notebook.service.note.NoteBookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/application-*.xml" })

public class NoteBookServiceTest {
	@Autowired
	private NoteBookService noteBookService;
	
	@Test
	public void save(){
		NoteBook noteBook = new NoteBook();
		noteBook.setId(UUIDCreateUtils.getUUID());
		noteBook.setParentId("0");
		noteBook.setName("默认笔记本3");
		noteBook.setUserId("20170720C416F96A979F44D4A9458A6C76B1024E83029325");
		noteBook.setHaveNode(true);
		noteBookService.save(noteBook);
	}

}
