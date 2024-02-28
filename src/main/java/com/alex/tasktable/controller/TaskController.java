package com.alex.tasktable.controller;

import com.alex.tasktable.bean.Task;
import com.alex.tasktable.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public List<Task> getTasks() {
        return taskService.findAll();
    }

    @PostMapping("/")
    public Task createTask(@RequestBody Task task) {
        return taskService.save(task);
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        taskService.deleteById(id);
        return taskService.save(taskDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
    }
}
