package com.springboot.todoapp.myfirstwebapp.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("name")  //putting name into session
public class WelcomeController {

    //GET, POST
    //Here, we are capturing details from the login form and show it on the welcome page.

    @RequestMapping(value="/", method= RequestMethod.GET)     //Now it only supports GET method, POST is not supported.
    public String gotoWelcomePage(ModelMap model){   //welcome page logged in username, put it into the model and it's written back.
        model.put("name",getLoggedinUsername());
        return "welcome";
    }


    private String getLoggedinUsername(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}

//Note:
// Whenever we use session attributes, we need to use it in all the controllers where we want to make use of that value.
// Expression Language is used to display simple things so if we put any value into the model in the controller to display it we were using expression language.
// Just expression language will not be sufficient to list dynamic content into a table, for that we use JSTL tags.