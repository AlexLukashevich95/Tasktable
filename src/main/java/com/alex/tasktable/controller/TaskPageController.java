package com.alex.tasktable.controller;

import com.alex.tasktable.enums.Statuses;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasks")
public class TaskPageController {
    @GetMapping
    public String showMain(Model model) {
        model.addAttribute("taskStatuses", Statuses.values());
        return "main";
    }
}
