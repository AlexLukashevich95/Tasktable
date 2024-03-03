package com.alex.tasktable.controller;

import com.alex.tasktable.model.Task;
import com.alex.tasktable.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/")
    public String showText() {
        Task task=new Task();
        task.setId(1);
        taskService.save(task);
        return "index";
    }


}
