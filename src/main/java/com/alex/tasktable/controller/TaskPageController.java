package com.alex.tasktable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class TaskPageController {
    @GetMapping
    public String showMain() {
        return "tasks";
    }

    @GetMapping("/taskform")
    public String showForm() {
        return "taskform";
    }

    @GetMapping("/edittask")
    public String showEditForm() {
        return "taskeditform";
    }

    @GetMapping("/viewtask")
    public String showViewTasks() {
        return "viewtask";
    }


}
