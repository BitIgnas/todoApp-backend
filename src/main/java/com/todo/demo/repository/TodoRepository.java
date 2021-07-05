package com.todo.demo.repository;

import com.todo.demo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findAllByUserId(Long id);

    Optional<Todo> findById(Long id);

    @Transactional
    void deleteTodoByNameAndIdAndUserId(String name, Long id, Long userId);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Todo u" +
            " SET u.name=:name, u.description=:description, u.priority=:priority" +
            " WHERE u.id=:id ")
    void updateTodo(@Param("id") Long id,
                    @Param("name") String name,
                    @Param("description") String description,
                    @Param("priority") String priority);
}
