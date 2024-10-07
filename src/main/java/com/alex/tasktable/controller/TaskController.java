package com.alex.tasktable.controller;

import com.alex.tasktable.enums.Statuses;
import com.alex.tasktable.exceptions.TaskException;
import com.alex.tasktable.mapper.TaskMapper;
import com.alex.tasktable.model.Task;
import com.alex.tasktable.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskMapper taskMapper;

    @GetMapping("")
    public ResponseEntity<Void> showMain(Model m) {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/viewtask")
    public ResponseEntity<List<Task>> getTasks(Model model) {
        try {
            List<Task> tasks = taskService.findAll();
            model.addAttribute("list", tasks);
            return ResponseEntity.ok(tasks);
        } catch (TaskException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/taskform")
    public ResponseEntity<Void> showform(Model model) {
        model.addAttribute("task", new Task());
        Statuses[] statuses = Statuses.values();
        model.addAttribute("status", statuses);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/save")
    public ResponseEntity<Task> createTask(@ModelAttribute("task") Task task, Model model) {
        try {
            taskService.save(task);
            return ResponseEntity.ok(task);
        } catch (TaskException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/edittask/{id}")
    public ResponseEntity<Task> edit(@PathVariable Long id, Model model) {
        try {
            Task task = taskService.findById(id);
            model.addAttribute("task", task);
            Statuses[] statuses = Statuses.values();
            model.addAttribute("status", statuses);
            model.addAttribute("selectedStatus", task.getStatus());
            return ResponseEntity.ok(task);
        } catch (TaskException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/edittask/editsave")
    public ResponseEntity<Task> updateTask(@ModelAttribute("task") Task task, Model model) {
        try {
            taskService.update(task);
            return ResponseEntity.ok(task);
        } catch (TaskException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/deletetask/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id, Model model) {
        try {
            taskService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (TaskException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
