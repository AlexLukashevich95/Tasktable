package com.alex.tasktable.service.impl;

import com.alex.tasktable.dto.TaskDto;
import com.alex.tasktable.exceptions.ApplicationException;
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
    public List<TaskDto> findAll() throws ApplicationException {
        return taskRepository.findAll().stream().map(task -> taskMapper.toDto(task))
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto findById(Long id) throws ApplicationException {
        return Optional.ofNullable(taskMapper.toDto(taskRepository.findById(id))).orElseThrow(() ->
                new ResourceNotFoundException("Not found Task with id = " + id));
    }

    @Override
    public TaskDto save(TaskDto taskDto) throws ApplicationException {
        Task task = taskMapper.toModel(taskDto);
        task = taskRepository.save(task);
        taskDto =taskMapper.toDto(task);
        return taskDto;
    }

    @Override
    public TaskDto update(TaskDto taskdto) throws ApplicationException {
        Task task = taskMapper.toModel(taskdto);
        task=taskRepository.update(task);
        taskdto=taskMapper.toDto(task);
        return taskdto;
    }

    @Override
    public void deleteById(Long id) throws ApplicationException {
        taskRepository.deleteById(id);
    }
}
