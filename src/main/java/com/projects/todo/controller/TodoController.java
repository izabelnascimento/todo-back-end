package com.projects.todo.controller;

import com.projects.todo.model.Todo;
import com.projects.todo.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
public class TodoController {

    private final TodoRepository todoRepository;

    @PostMapping
    public Todo save(@RequestBody Todo todo) {
        return this.todoRepository.save(todo);
    }

    @GetMapping("{id}")
    public Todo getById(@PathVariable Long id) {
        return this.todoRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Todo> getAll() {
        return this.todoRepository.findAll();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        this.todoRepository.deleteById(id);
    }

    @PatchMapping("/done/{id}")
    public Todo done(@PathVariable Long id) {
        return this.todoRepository.findById(id).map(todo -> {
            todo.setDone(true);
            todo.setDoneDate(LocalDateTime.now());
            this.todoRepository.save(todo);
            return todo;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
