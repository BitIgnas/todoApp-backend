package com.todo.demo.repository;

import com.todo.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username=:username AND u.password=:password")
    User findUserByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

    @Query("SELECT u FROM User u WHERE u.id=:id")
    User findUserById(@Param("id") Long id);
}
