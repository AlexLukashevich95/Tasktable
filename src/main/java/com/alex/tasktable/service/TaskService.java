package com.alex.tasktable.service;

import com.alex.tasktable.exceptions.TaskException;
import com.alex.tasktable.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAll() throws TaskException;

    Task findById(Long id) throws TaskException;

    Task save(Task task) throws TaskException;

    Task update(Task task) throws TaskException;

    void deleteById(Long id) throws TaskException;
}
