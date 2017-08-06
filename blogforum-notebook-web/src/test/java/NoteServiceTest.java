

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blogforum.common.tools.UUIDCreateUtils;
import com.blogforum.notebook.pojo.entity.Note;
import com.blogforum.notebook.service.note.impl.NoteServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/application-*.xml" })

public class NoteServiceTest {
	@Autowired
	private NoteServiceImpl noteService;
	@Test
	public void insert(){
		ClassPathXmlApplicationContext application = new ClassPathXmlApplicationContext("classpath:spring/application-*.xml");
		NoteServiceImpl bean = (NoteServiceImpl) application.getBean("noteService");
		Note note = new Note();
		note.setId(UUIDCreateUtils.getUUID());
		note.setDelFlag("N");
		note.setNoteBody("test测试");
		note.setNoteTitle("这是测试");
		note.setNotebookId("123456789");
		note.setUserId("20170720C416F96A979F44D4A9458A6C76B1024E83029325");
		noteService.save(note);
	}

}
