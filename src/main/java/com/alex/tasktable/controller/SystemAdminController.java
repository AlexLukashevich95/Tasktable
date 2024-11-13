package com.alex.tasktable.controller;

import com.alex.tasktable.dto.TaskDto;
import com.alex.tasktable.exceptions.ApplicationException;
import com.alex.tasktable.mapper.TaskMapper;
import com.alex.tasktable.model.Task;
import com.alex.tasktable.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class SystemAdminController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskMapper taskMapper;

    private final Class<?> taskClass = Task.class;

    @GetMapping("/task/fields")
    public List<Map<String, String>> getTaskFields() {
        List<Map<String, String>> taskFields = new ArrayList<>();
        for (Field field : taskClass.getDeclaredFields()) {
            Map<String, String> fieldInfo = new HashMap<>();
            fieldInfo.put("name", field.getName());
            fieldInfo.put("type", field.getType().getSimpleName());
            taskFields.add(fieldInfo);
        }
        return taskFields;
    }

    @GetMapping("/task/methods")
    public List<String> getTaskMethods() {
        List<String> taskMethods = new ArrayList<>();
        for (Method method : taskClass.getDeclaredMethods()) {
            taskMethods.add(method.getName());
        }
        return taskMethods;
    }

    @GetMapping("/task")
    public ResponseEntity<TaskDto> update(@RequestParam Long id,
                                          @RequestParam String fieldName,
                                          @RequestParam String value) {
        try {
            TaskDto taskDto = taskMapper.toDto(taskService.findById(id));
            try {
                Class<?> taskDtoClass = TaskDto.class;
                Field field = taskDtoClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(taskDto, value);
                taskService.update(taskDto);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        } catch (ApplicationException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
