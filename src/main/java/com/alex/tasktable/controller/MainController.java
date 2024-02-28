package com.alex.tasktable.controller;

import com.alex.tasktable.bean.Task;
import com.alex.tasktable.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/")
    public String showText() {
        taskService.save(new Task());
        return "index";
    }


}
