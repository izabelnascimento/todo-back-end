package com.projects.todo;

import com.projects.todo.model.Todo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TodoApplication {

    @GetMapping("/")
    public String hello(){
        return "Hello World";
    }

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

}
