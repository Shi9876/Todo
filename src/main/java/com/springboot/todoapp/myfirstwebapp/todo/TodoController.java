package com.springboot.todoapp.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")   //all session attribute with the name as name will be available in the model as well.
public class TodoController {

    public TodoController(TodoService todoService) {
        super();
        this.todoService = todoService;
    }

    private TodoService todoService;

    //list-todos
    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model) {
        String username = getLoggedInUsername(model);
        System.out.println(("Fetching Todos for user: " + username));
        List<Todo> todos = todoService.findByUsername(username);
        model.addAttribute("todos", todos);
        System.out.println(("Todos fetched for user: " + username + " -> " + todos));
        return "listTodos";
    }


    //GET, POST
    @RequestMapping(value = "add-todo", method = RequestMethod.GET)   //1st side of binding - from our form to the bean
    public String showNewTodoPage(ModelMap model) {
        String username = getLoggedInUsername(model);
        Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
        model.addAttribute("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)   //2nd side of binding - from our bean to the form
    public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {  //todo added   //to capture the description

        if (result.hasErrors()) {
            return "todo";
        }
        String username = getLoggedInUsername(model);
        todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());  //calling addTodo Service
        return "redirect:list-todos";         //After processing the /add-todo request, the user is redirected back to list-todos URL.
    }




    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = todoService.findById(id);
        model.addAttribute("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    //2nd side of binding - from our bean to the form
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {  //todo added   //to capture the description

        if (result.hasErrors()) {
            return "todo";
        }

        String username = getLoggedInUsername(model);
        todo.setUsername(username);
        todoService.updateTodo(todo);     //calling updateTodo Service
        return "redirect:list-todos";         //After processing the /add-todo request, the user is redirected back to list-todos URL.
    }



    @RequestMapping(value = "delete-todo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam int id) {
        //Delete todo

        todoService.deleteById(id);
        return "redirect:list-todos";
    }

    private static String getLoggedInUsername(ModelMap model) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}




//Note:
//1. if mapped add-todo url to the method
//   then this will handle all the kinds of requests(GET, POST, any request type, any request method.)
//2. When a description entered on the todo form, we are binding it to the addNewTodo method( binding the method by using @RequestParam)
//   Instead of, putting all of them  and using them to bind to the Todo Bean.
// 3. Two way binding: