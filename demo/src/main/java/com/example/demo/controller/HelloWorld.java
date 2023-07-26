package src.main.java.com.example.demo.controller;

@RestController
public class HelloWorld {

    @RequestMapping("/")
    public String hello() {
        return "Hello World";
    }

}
