package com.alex.tasktable.service;

import com.alex.tasktable.dto.TaskDto;
import com.alex.tasktable.exceptions.ApplicationException;
import com.alex.tasktable.model.Task;

import java.util.List;

public interface TaskService {
    List<TaskDto> findAll() throws ApplicationException;

    Task findById(Long id) throws ApplicationException;

    Task save(Task task) throws ApplicationException;

    Task update(Task task) throws ApplicationException;

    void deleteById(Long id) throws ApplicationException;
}
