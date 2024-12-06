package com.springboot.todoapp.myfirstwebapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {
    //LDP or database
    //In Memory
    // In-Memory UserDetailsManager Configuration
    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {

        // Creating an in-memory user with a username, password, and roles


        UserDetails userDetails1 = createNewUser("shivanshi", "123456");
        UserDetails userDetails2 = createNewUser("Vaibhav", "234567");

        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    private UserDetails createNewUser(String username, String password) {
        // Lambda function that takes a plain text password and encodes it


        return User.builder()
                .passwordEncoder(passwordEncoder()::encode)
                .username(username)
                .password(password)  // Encoding the password
                .roles("USER", "ADMIN")  // Assigning roles
                .build();

    }

    // Password encoder using BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
