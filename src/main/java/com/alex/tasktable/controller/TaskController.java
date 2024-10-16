package com.alex.tasktable.controller;

import com.alex.tasktable.dto.TaskDto;
import com.alex.tasktable.enums.Statuses;
import com.alex.tasktable.exceptions.ResourceNotFoundException;
import com.alex.tasktable.exceptions.TaskException;
import com.alex.tasktable.mapper.TaskMapper;
import com.alex.tasktable.model.Task;
import com.alex.tasktable.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDto>> getTasks() {
        List<TaskDto> taskDtos = taskService.findAll();
        if (taskDtos.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(taskDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        taskService.save(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody Task task) {
        taskService.update(task);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
