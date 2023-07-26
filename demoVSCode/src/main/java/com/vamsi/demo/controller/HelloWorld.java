package com.vamsi.demo.controller;

// import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @RequestMapping("/")
    public String hello(){
        return "<a href = \"http://localhost:30405/index.html\"> Welcome </a>";
    }

  

    
}
