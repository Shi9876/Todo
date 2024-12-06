package com.springboot.todoapp.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();
   private static int todosCount=0; //to have todo's count
    static {            //static list
        todos.add(new Todo(++todosCount, "shivanshi","Learn Google Cloud", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosCount, "shivanshi","Learn JAVA", LocalDate.now().plusYears(2), false));
        todos.add(new Todo(++todosCount, "shivanshi","Learn DevOps", LocalDate.now().plusYears(3), false));
    }

    public List<Todo> findByUsername(String username){
        Predicate<? super Todo> predicate =
                todo -> todo.getUsername().equalsIgnoreCase(username);
        System.out.println("Todos fetched for user: "+username+ " -> " + todos.stream().filter(predicate).toList());
        return todos.stream().filter(predicate).toList();
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean done){
        Todo todo = new Todo(++todosCount, username, description, targetDate, done);
        todos.add(todo);

    }

    public void deleteById(int id){  //to delete a Todo
       //todo.getId() == id [Predicate]
       //todo -> todo.getId() == id  [Lambda Function]
        Predicate<? super Todo> predicate = todo -> todo.getId()==id;     //does this todo have matching id?
        todos.removeIf(predicate);             //A predicate is basically a condition, if the condition matches it would remove the specific todo.
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId()==id;     //does this todo have matching id?
        return todos.stream().filter(predicate).findFirst().orElse(null);  //from the streams of todos we are filtering based on that condition.
                                                      //At the end, we should just find one element. We are getting that element and returning it back.

    }

    public void updateTodo(@Valid Todo todo){
    deleteById(todo.getId());
    todos.add(todo);
    }
}

//Notes:
// 1. A static list: Whenever the server restart the static list is refreshed.
// For ex: if I add 10 todos and restart the server, all the todos that I have added will be lost.