package com.alex.tasktable.controller;

import com.alex.tasktable.model.Task;
import com.alex.tasktable.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "")
    public String showMain(Model m) {
        return "tasks";
    }

    @RequestMapping(value = "/viewtask", method = RequestMethod.GET)
    public String getTasks(Model model) {
        model.addAttribute("list", taskService.findAll());
        return "viewtask";
    }

    @RequestMapping(value = "/taskform")
    public String showform(Model model) {
        model.addAttribute("task", new Task());
        return "taskform";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String createTask(@ModelAttribute("task") Task task) {
        taskService.save(task);
        return "redirect:/tasks/viewtask";
    }

    @RequestMapping(value = "/edittask/{id}")
    public String edit(@PathVariable Long id, Model m) {
        Task task = taskService.findById(id);
        m.addAttribute("task", task);
        return "taskeditform";
    }

    @RequestMapping(value = "/edittask/editsave", method = RequestMethod.POST)
    public String updateTask(@ModelAttribute("task") Task task) {
        taskService.update(task);
        return "redirect:/tasks/viewtask";
    }

    @RequestMapping(value = "/deletetask/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return "redirect:/tasks/viewtask";
    }
}
