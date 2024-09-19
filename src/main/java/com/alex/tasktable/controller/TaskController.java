package com.alex.tasktable.controller;

import com.alex.tasktable.enums.Statuses;
import com.alex.tasktable.exceptions.TaskException;
import com.alex.tasktable.mapper.TaskMapper;
import com.alex.tasktable.model.Task;
import com.alex.tasktable.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(value = "")
    public String showMain(Model m) {
        return "tasks";
    }

    @RequestMapping(value = "/viewtask", method = RequestMethod.GET)
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

    @RequestMapping(value = "/taskform")
    public String showform(Model model) {
        model.addAttribute("task", new Task());
        Statuses[] statuses = Statuses.values();
        model.addAttribute("status", statuses);
        return "taskform";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String createTask(@ModelAttribute("task") Task task, Model model) {
        try {
            taskService.save(task);
        } catch (TaskException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "viewtask";
        }
        return "redirect:/tasks/viewtask";
    }

    @RequestMapping(value = "/edittask/{id}")
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

    @RequestMapping(value = "/edittask/editsave", method = RequestMethod.POST)
    public String updateTask(@ModelAttribute("task") Task task, Model model) {
        try {
            taskService.update(task);
        } catch (TaskException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "viewtask";
        }
        return "redirect:/tasks/viewtask";
    }

    @RequestMapping(value = "/deletetask/{id}")
    public String deleteTask(@PathVariable Long id, Model model) {
        try {
            taskService.deleteById(id);
        } catch (TaskException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "viewtask";
        }
        return "redirect:/tasks/viewtask";
    }
}
