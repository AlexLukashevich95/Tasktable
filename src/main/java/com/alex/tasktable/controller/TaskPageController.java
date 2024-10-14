package com.alex.tasktable.controller;

import com.alex.tasktable.enums.Statuses;
import com.alex.tasktable.exceptions.TaskException;
import com.alex.tasktable.mapper.TaskMapper;
import com.alex.tasktable.model.Task;
import com.alex.tasktable.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/tasks")
public class TaskPageController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskMapper taskMapper;

    @GetMapping("")
    public String showMain(Model m) {
        return "tasks";
    }

    //@RequestMapping(value = "/taskform")
    @GetMapping("/taskform")
    public String showForm(Model model) {
        model.addAttribute("task", new Task());
        Statuses[] statuses = Statuses.values();
        model.addAttribute("status", statuses);
        return "taskform";
    }

    //@RequestMapping(value = "/viewtask", method = RequestMethod.GET)
    @GetMapping("/viewtask")
    public String getTasks(Model model) {
        try {
            model.addAttribute("list", taskService.findAll().stream().map(task -> taskMapper.toDto(task))
                    .collect(Collectors.toList()));
        } catch (TaskException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "redirect:/tasks/viewtask";
        }
        return "viewtask";
    }

    @GetMapping("/edittask/{id}")
    public String edit(@PathVariable Long id, Model model) {
        try {
            Task task = taskService.findById(id);
            model.addAttribute("task", task);
            Statuses[] statuses = Statuses.values();
            model.addAttribute("status", statuses);
            model.addAttribute("selectedStatus", task.getStatus());
        } catch (TaskException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "redirect:/tasks/viewtask";
        }
        return "taskeditform";
    }
}
