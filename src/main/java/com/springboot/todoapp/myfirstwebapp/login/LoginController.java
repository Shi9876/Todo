package com.springboot.todoapp.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("name")  //putting name into session
public class LoginController {

    private AuthenticationService authenticationService;

    //doing constructor injection for wiring...
      //Make AuthenticationService a Spring bean. We want AuthenticationService to be managed by Spring.
     // We would want Spring to be able to create an instance of AuthenticationService.
    //And auto wiring it into the login controller by @Service (using it as containing business logic).
    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    //http://localhost:8080/login?name=Shivanshi
    //Model

    @RequestMapping("/login")
    public String gotoLoginPage(@RequestParam String name, ModelMap model){ //Extracts the name parameter from the URL query string (e.g,?name=Shivanshi) and assigns its value to the view (login.html)
        model.put("name", name);                //adds the name value to the model.This data is accessed by view using ${name} in login.html.
        System.out.println("Request param is"+name);  //Not recommended for production
        return "login";
    }

    //login
    //GET, POST
    //Here, we are capturing details from the login form and show it on the welcome page.

    @RequestMapping(value="login-view", method= RequestMethod.GET)     //Now it only supports GET method, POST is not supported.
    public String showLoginPage(){
        return "loginview";
    }

    @RequestMapping(value="login-view", method= RequestMethod.POST)     //Now it will support POST
    //login?name=John RequestParam
    public String showWelcomePage(@RequestParam String name, @RequestParam String password,ModelMap model) {

        if (AuthenticationService.authenticate(name, password)) { //if valid
            model.put("name", name);

            //Authentication logic: if a specific username and password as mentioned below is entered then we will direct them to welcome page otherwise send them back to the login page.
            //name - Shivanshi
            //password - 123456

            return "welcome";
        }

        model.put("errorMessage", "Invalid Credentials! Please try again.");
        return "loginview";
    }
}

//Note:
// Whenever we use session attributes, we need to use it in all the controllers where we want to make use of that value.
// Expression Language is used to display simple things so if we put any value into the model in the controller to display it we were using expression language.
// Just expression language will not be sufficient to list dynamic content into a table, for that we use JSTL tags.