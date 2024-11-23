package com.springboot.todoapp.myfirstwebapp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();
   private static int id=1;

    static {
        todos.add(new Todo(id++, "shivanshi","Learn Spring Boot", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(id++, "shivanshi","Learn AWS", LocalDate.now().plusYears(2), false));
        todos.add(new Todo(id++, "shivanshi","Learn DevOps", LocalDate.now().plusYears(3), false));
    }

    public List<Todo> findByUsername(String username){
       return todos;
    }
}
