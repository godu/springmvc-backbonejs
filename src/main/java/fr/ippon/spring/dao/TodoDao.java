package fr.ippon.spring.dao;

import fr.ippon.spring.model.Todo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TodoDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Todo find(Long id) {
		return entityManager.find(Todo.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Todo> getTodo() {
		return entityManager.createQuery("select p from Todo p").getResultList();
	}

    @Transactional
    public Todo save(Todo todo) {
        if (todo.getId() == null) {
            entityManager.persist(todo);
            return todo;
        } else {
            return entityManager.merge(todo);
        }
    }

    @Transactional
    public boolean remove(Todo todo) {
        if (todo.getId() != null) {
            entityManager.remove(entityManager.find(Todo.class, todo.getId()));
            return true;
        } else {
            return false;
        }
    }
	
}
