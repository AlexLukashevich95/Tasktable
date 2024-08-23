package com.alex.tasktable.service.impl;

import com.alex.tasktable.model.Task;
import com.alex.tasktable.repository.TaskRepository;
import com.alex.tasktable.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task) {
        return taskRepository.update(task);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
