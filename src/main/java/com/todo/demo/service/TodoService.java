package com.todo.demo.service;

import com.todo.demo.model.Todo;
import com.todo.demo.repository.TodoRepository;
import com.todo.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public List<Todo> getAllTodosByUserId(Long id) {
        return todoRepository.findAllByUserId(id);
    }

    public void insertTodo(Long id, Todo todo, String priority) {
        todo.setUser(userRepository.findUserById(id));
        todo.setPriority(priority);
        todoRepository.save(todo);
    }

    public void deleteTodo(Long userId, Long todoId, String name) {
        todoRepository.deleteTodoByNameAndIdAndUserId(name, todoId, userId);
    }

    public void updateTodo(Todo todo) {
        todoRepository.updateTodo(
                todo.getId(),
                todo.getName(),
                todo.getDescription(),
                todo.getPriority()
        );
    }

    public Optional<Todo> findById(Long id) {
        return todoRepository.findById(id);
    }
}
