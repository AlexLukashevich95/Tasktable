package com.alex.tasktable.repository;

import com.alex.tasktable.exceptions.TaskException;
import com.alex.tasktable.model.Task;

import java.util.List;

public interface TaskRepository {
    List<Task> findAll() ;

    Task findById(Long id) ;

    Task save(Task task) ;

    Task update(Task task) ;

    void deleteById(Long id) ;
}
