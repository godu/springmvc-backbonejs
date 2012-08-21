package fr.ippon.spring.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TodoControllerTest {
	
	@Autowired
	private DataInitializer dataInitializer;
	
	@Autowired
	private TodoController todoController;
		
	@Before
	public void before() {
		dataInitializer.initData();
	}

    @Test
    public void shouldReturnTodoListView() {
    }

	/*
    @Test
	public void shouldReturnTodoListView() {
		ModelAndView mav = todoController.listTodo();
		assertEquals("list",mav.getViewName());
		
		@SuppressWarnings("unchecked")
		List<Todo> todo = (List<Todo>) mav.getModelMap().get("todo");
		assertNotNull(todo);
		assertEquals(DataInitializer.TODO_COUNT,todo.size());
	}
	
	

	public void shouldReturnNewTodoWithEditMav() {
		ModelAndView mav = todoController.editTodo(null);
		assertNotNull(mav);
		assertEquals("edit", mav.getViewName());
		Object object = mav.getModel().get("todo");
		assertTrue(Todo.class.isAssignableFrom(object.getClass()));
        Todo todo = (Todo) object;
		assertNull(todo.getId());
		assertNull(todo.getTitle());
		assertNull(todo.getCompleted());
	}
	
	@Test
	public void shouldReturnSecondTodoWithEditMav() {
		Long template = dataInitializer.todo.get(1);
		ModelAndView mav = todoController.editTodo(template);
		assertNotNull(mav);
		assertEquals("edit", mav.getViewName());
		Object object = mav.getModel().get("person");
		assertTrue(Todo.class.isAssignableFrom(object.getClass()));
        Todo todo = (Todo) object;
		assertEquals(template,todo.getId());
	}
    */
	
}
