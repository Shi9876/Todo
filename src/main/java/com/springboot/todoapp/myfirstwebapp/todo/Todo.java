package com.springboot.todoapp.myfirstwebapp.todo;

import java.time.LocalDate;

//Database (MySQL)
//Static List of todos => Database (H2, MySQL)

public class Todo {
    //id
    //username
    //description
    //targetDate
    //done
    private int id;
    private String username;
    private String description;
    private LocalDate targetDate;
    private boolean done;

    public Todo(int id, String username, String description, LocalDate targetDate, boolean done) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    //calling the toString() on a Todo object.
    public String toString() {     //it converts the object into a string that represents the contents in a human-readable format.
        return "Todo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", description='" + description + '\'' +
                ", targetDate=" + targetDate +
                ", done=" + done +
                '}';
    }
}


//Steps:
// 1. Create a class under a package
// 2. Declare fields to store the details which are required.
// 3. Create a constructor for storing values to the fields.
// 4. Generate getters and setter for those.
// 5. Generate toString()