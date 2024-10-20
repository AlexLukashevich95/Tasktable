package com.alex.tasktable.controller;

import com.alex.tasktable.dto.TaskDto;
import com.alex.tasktable.exceptions.ApplicationException;
import com.alex.tasktable.model.Task;
import com.alex.tasktable.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/main")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDto>> getTasks() {
        try {
            List<TaskDto> taskDtos = taskService.findAll();
            if (taskDtos.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<>(taskDtos, HttpStatus.OK);
        } catch (ApplicationException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        try {
            taskService.save(task);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ApplicationException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody Task task) {
        try {
            taskService.update(task);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ApplicationException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ApplicationException e) {
            throw new RuntimeException(e);
        }
    }
}
