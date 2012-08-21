package fr.ippon.spring.dao;

import java.util.List;

import fr.ippon.spring.controller.DataInitializer;
import fr.ippon.spring.model.Todo;
import fr.ippon.spring.dao.TodoDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TodoDaoTest {

	@Autowired
	private TodoDao todoDao;

	@Autowired
	private DataInitializer dataInitializer;

	@Before
	public void prepareData() {
		dataInitializer.initData();
	}

	@Test
	public void shouldSaveATodo() {
        Todo t = new Todo();
		t.setTitle("Andy");
		t.setCompleted(true);
        todoDao.save(t);
		Long id = t.getId();
		Assert.assertNotNull(id);
	}

	@Test
	public void shouldLoadATodo() {
		Long template = dataInitializer.todo.get(0);
		Todo t = todoDao.find(template);

		Assert.assertNotNull("Todo not found!", t);
		Assert.assertEquals(template, t.getId());
	}

	public void shouldListPeople() {
		List<Todo> todo = todoDao.getTodo();
		Assert.assertEquals(DataInitializer.TODO_COUNT, todo.size());

	}

}
