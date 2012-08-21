package fr.ippon.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.ippon.spring.model.Todo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DataInitializer {

	public static final int TODO_COUNT = 3;

	@PersistenceContext
	private EntityManager entityManager;

	public List<Long> todo = new ArrayList<Long>();

	public void initData() {
		todo.clear();// clear out the previous list of people
		addTodo("Article", true);
		addTodo("foo", false);
		addTodo("bar", false);
		entityManager.flush();
		entityManager.clear();
	}

	public void addTodo(String title, boolean completed) {
		Todo t = new Todo();
		t.setTitle(title);
		t.setCompleted(completed);
		entityManager.persist(t);
		todo.add(t.getId());
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
