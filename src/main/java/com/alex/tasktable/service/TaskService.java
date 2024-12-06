package com.alex.tasktable.service;

import com.alex.tasktable.dto.TaskDto;
import com.alex.tasktable.exceptions.ApplicationException;

import java.util.List;

public interface TaskService {
    List<TaskDto> findAll();

    TaskDto findById(Long id);

    TaskDto save(TaskDto taskDto);

    TaskDto update(TaskDto taskdto);

    void deleteById(Long id);
}
