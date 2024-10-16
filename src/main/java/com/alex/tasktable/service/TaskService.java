package com.alex.tasktable.service;

import com.alex.tasktable.dto.TaskDto;
import com.alex.tasktable.model.Task;

import java.util.List;

public interface TaskService {
    List<TaskDto> findAll();

    Task findById(Long id);

    Task save(Task task);

    Task update(Task task);

    void deleteById(Long id);
}
