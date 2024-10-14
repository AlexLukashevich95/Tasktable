package com.alex.tasktable.controller;

import com.alex.tasktable.exceptions.TaskException;
import com.alex.tasktable.model.Task;
import com.alex.tasktable.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskDataController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/save")
    public ResponseEntity<Task> createTask(@RequestBody Task task) throws TaskException {
        taskService.save(task);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        taskService.update(task);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
