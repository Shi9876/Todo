package com.springboot.todoapp.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller     //to handle the requests

public class SayHelloController {
    //"say-hello" => "Hello! What are you learning today?"

    //say-hello
    //http://localhost:8080/say-hello

    @RequestMapping("say-hello")     //to map a URL of say-hello
    @ResponseBody                   //to return the string as ease

    public String sayHello(){
        return "Hello! What are you learning today?";
    }

    @RequestMapping("say-hello-html")
    @ResponseBody
    public String sayHelloHtml(){
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title> My first HTML Page</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<h1>");
        sb.append("We will learn HTML here");
        sb.append("<h1>");
        sb.append("My first html page with body");
        sb.append("</body>");
        sb.append("</html>");

        return sb.toString();
    }


//say-hello-jsp => sayHello.jsp
@RequestMapping("say-hello-view")
public String sayHelloView() {
    return "sayHello";
}
}


//Notes:
//1. When we hit the URL localhost:8080/say-hello, we're returning the string back by creating a method sayHello().