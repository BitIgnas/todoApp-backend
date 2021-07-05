package com.todo.demo.controller;

import com.todo.demo.model.Todo;
import com.todo.demo.model.User;
import com.todo.demo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Todo>> getAllUserTodos(@PathVariable("userId") Long id) {
        return new ResponseEntity<>(todoService.getAllTodosByUserId(id), HttpStatus.ACCEPTED);
    }

    @PostMapping("/{userId}/{priority}")
    public ResponseEntity<Void> insertTodo
            (@RequestBody Todo todo, @PathVariable("userId") Long id, @PathVariable("priority") String priority) {
        todoService.insertTodo(id, todo, priority);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteTodo(@RequestParam("name") String name, @RequestParam("todoId") Long todoId, @RequestParam("userId") Long id) {
        todoService.deleteTodo(id, todoId, name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Void> updateTodo(@RequestBody Todo todo) {
        todoService.updateTodo(todo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Todo>> findTodoById(@PathVariable("id") Long id) {
        return new ResponseEntity<Optional<Todo>>(todoService.findById(id), HttpStatus.OK);
    }


}
