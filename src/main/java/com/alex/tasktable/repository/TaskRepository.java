package com.alex.tasktable.repository;

import com.alex.tasktable.bean.Task;

import java.util.List;

public interface TaskRepository {
    List<Task> findAll();
    Task findById(Long id);
    Task save(Task task);
    void deleteById(Long id);
}
