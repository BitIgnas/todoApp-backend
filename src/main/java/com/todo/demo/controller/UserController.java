package com.todo.demo.controller;

import com.todo.demo.exception.NoUserFoundException;
import com.todo.demo.exception.UserTakenException;
import com.todo.demo.model.User;
import com.todo.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/")
    public ResponseEntity<Void> registerUser(@RequestBody User user) throws UserTakenException {
        userService.registerUser(user);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/login")
    public ResponseEntity<User> userLogin
            (@RequestParam("username") String username, @RequestParam("password") String password) throws NoUserFoundException {
        return new ResponseEntity<>(userService.userLogin(username, password), HttpStatus.OK);
    }
}
