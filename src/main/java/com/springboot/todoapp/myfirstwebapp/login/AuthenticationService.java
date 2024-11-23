package com.springboot.todoapp.myfirstwebapp.login;


import org.springframework.stereotype.Service;

@Service    //This contains business logic That's why using @Service here.
public class AuthenticationService {
    public static boolean authenticate(String username, String password){
        boolean isValidUserName = username.equalsIgnoreCase("Shivanshi");
        boolean isValidPassword = password.equalsIgnoreCase("123456");

        return isValidUserName && isValidPassword; //when both are valid (username and password): return true;
    }
}
