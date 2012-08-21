package fr.ippon.spring.controller;

import fr.ippon.spring.dao.TodoDao;
import fr.ippon.spring.model.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/todos")
public class TodoController {
	
	private static final Logger logger = LoggerFactory.getLogger(TodoController.class);

	@Autowired
	private TodoDao todoDao;
	
	@RequestMapping(method=RequestMethod.GET,value="edit")
	public ModelAndView editTodo(@RequestParam(value="id",required=false) Long id) {
		logger.debug("Received request to edit todo id : "+id);
		ModelAndView mav = new ModelAndView();		
 		mav.setViewName("edit");
        Todo todo = null;
 		if (id == null) {
             todo = new Todo();
 		} else {
             todo = todoDao.find(id);
 		}
 		
 		mav.addObject("todo", todo);
		return mav;
		
	}
	
	@RequestMapping(method=RequestMethod.POST,value="edit") 
	public String saveTodo(@ModelAttribute Todo todo) {
		logger.debug("Received postback on todo "+todo);
        todoDao.save(todo);
		return "redirect:list";
		
	}

    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody List<Todo> listTodos() {
        logger.debug("Received request to list todos");
        List<Todo> todo = todoDao.getTodo();
        return todo;

    }

    @RequestMapping(method=RequestMethod.POST)
    public @ResponseBody Todo addTodo(@RequestBody Todo todo) {
        logger.debug("Received request to add todo");
        todoDao.save(todo);
        return todo;
    }

    @RequestMapping(method=RequestMethod.PUT, value="/{id}")
    public @ResponseBody Todo updateTodo(@RequestBody Todo todo, @PathVariable Long id) {
        logger.debug("Received request to add todo");
        todoDao.save(todo);
        return todo;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/{id}")
    public @ResponseBody boolean removeTodo(@PathVariable Long id) {
        logger.debug("Received request to add todo");

        return todoDao.remove(todoDao.find(id));
    }

}
