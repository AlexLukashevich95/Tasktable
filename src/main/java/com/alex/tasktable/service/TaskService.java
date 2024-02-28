package com.alex.tasktable.service;

import com.alex.tasktable.bean.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAll();
    Task findById(Long id);
    Task save(Task task);
    void deleteById(Long id);
}
