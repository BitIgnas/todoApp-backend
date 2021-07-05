package com.todo.demo.service;

import com.todo.demo.exception.NoUserFoundException;
import com.todo.demo.exception.UserTakenException;
import com.todo.demo.model.User;
import com.todo.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void registerUser(User user) {
        List<User> users = userRepository.findAll();

        users.stream().filter(userList -> userList.getPassword().equals(user.getPassword()) && userList.getUsername().equals(user.getUsername()))
                .findAny().orElse(userRepository.save(user));

        users.stream().filter(userList -> userList.getPassword().equals(user.getPassword()) && userList.getUsername().equals(user.getUsername()))
                .findFirst().ifPresent(s -> {
            try {
                throw new UserTakenException("User is already taken");
            } catch (UserTakenException e) {
                e.printStackTrace();
            }
        });

    }


    public User userLogin(String username, String password) throws NoUserFoundException {
        User user = userRepository.findUserByUsernameAndPassword(username, password);

        if (user != null || user.getUsername() != null || user.getPassword() != null) {
            return user;
        } else {
            throw new NoUserFoundException("User was not found");
        }
    }

}
