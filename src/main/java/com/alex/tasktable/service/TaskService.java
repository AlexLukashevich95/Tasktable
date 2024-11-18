package com.alex.tasktable.service;

import com.alex.tasktable.dto.TaskDto;
import com.alex.tasktable.exceptions.ApplicationException;
import com.alex.tasktable.model.Task;

import java.util.List;

public interface TaskService {
    List<TaskDto> findAll() throws ApplicationException;

    TaskDto findById(Long id) throws ApplicationException;

    TaskDto save(TaskDto taskDto) throws ApplicationException;

    TaskDto update(TaskDto taskdto) throws ApplicationException;

    void deleteById(Long id) throws ApplicationException;
}
