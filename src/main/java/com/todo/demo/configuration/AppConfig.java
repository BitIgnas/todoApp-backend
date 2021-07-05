package com.todo.demo.configuration;

import com.todo.demo.model.Todo;
import com.todo.demo.model.User;
import com.todo.demo.repository.TodoRepository;
import com.todo.demo.repository.UserRepository;
import com.todo.demo.todoEnum.Priority;
import jdk.jfr.Category;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AppConfig implements CommandLineRunner {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public AppConfig(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User admin = new User("admin", "admin");

        List<Todo> adminTodo = Arrays.asList(
                new Todo("go skiing", "go skiing at mountain resort", Priority.HIGH.getPriority(), admin),
                new Todo("eat dinner", "remember to eat dinner at 23:30", Priority.HIGH.getPriority(), admin),
                new Todo("make todo list", "xd xd xd xd xd xd xd xd", Priority.LOW.getPriority(), admin),
                new Todo("test", "lorem ipsum", Priority.MEDIUM.getPriority(), admin)
        );



        userRepository.save(admin);
        todoRepository.saveAll(adminTodo);
    }
}
