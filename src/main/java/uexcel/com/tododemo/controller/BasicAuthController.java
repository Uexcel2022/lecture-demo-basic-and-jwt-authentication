package uexcel.com.tododemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicAuthController {

    @GetMapping("basic-auth")
    public String login(){
        return "success";
    }

    @GetMapping("hello-world")
    public String helloWorld(){
        return "Hello World";
    }
}
