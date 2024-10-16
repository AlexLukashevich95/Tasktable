package com.alex.tasktable.service.impl;

import com.alex.tasktable.dto.TaskDto;
import com.alex.tasktable.exceptions.ResourceNotFoundException;
import com.alex.tasktable.mapper.TaskMapper;
import com.alex.tasktable.model.Task;
import com.alex.tasktable.repository.TaskRepository;
import com.alex.tasktable.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<TaskDto> findAll() {
        return taskRepository.findAll().stream().map(task -> taskMapper.toDto(task))
                .collect(Collectors.toList());
    }

    @Override
    public Task findById(Long id) {
        return Optional.ofNullable(taskRepository.findById(id)).orElseThrow(() ->
                new ResourceNotFoundException("Not found Task with id = " + id));
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task) {
        return Optional.ofNullable(taskRepository.update(task)).orElseThrow(() ->
                new ResourceNotFoundException("Not found Task with id = " + task.getId()));
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
