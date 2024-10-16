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

    @Autowired
    private TaskMapper taskMapper;

    @GetMapping("")
    public String showMain(Model model) {
        return "tasks";
    }

    @GetMapping("/taskform")
    public String showForm(Model model) {
        model.addAttribute("task", new Task());
        Statuses[] statuses = Statuses.values();
        model.addAttribute("status", statuses);
        return "taskform";
    }

    @GetMapping("/viewtask")
    public ResponseEntity<List<TaskDto>> getTasks() {

        List<TaskDto> taskDtos = taskService.findAll().stream().map(task -> taskMapper.toDto(task))
                .collect(Collectors.toList());
        if (taskDtos.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(taskDtos, HttpStatus.OK);
    }

    @GetMapping("/edittask/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Task task = taskService.findById(id);
        Optional.of(task).orElseThrow(() -> new ResourceNotFoundException("j"));
        model.addAttribute("task", task);
        Statuses[] statuses = Statuses.values();
        model.addAttribute("status", statuses);
        model.addAttribute("selectedStatus", task.getStatus());
        return "taskeditform";
    }

    @PostMapping("/save")
    public ResponseEntity<TaskDto> createTask(@RequestBody Task task) {
        taskService.save(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Optional.of(taskService.update(task)).orElseThrow(() -> new ResourceNotFoundException("Not found Task with id = " + id));
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
