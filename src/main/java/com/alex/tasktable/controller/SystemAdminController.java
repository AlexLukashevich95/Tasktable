package com.alex.tasktable.controller;

import com.alex.tasktable.dto.TaskDto;
import com.alex.tasktable.exceptions.ApplicationException;
import com.alex.tasktable.mapper.TaskMapper;
import com.alex.tasktable.model.Task;
import com.alex.tasktable.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        return Arrays.stream(taskClass.getDeclaredFields()).map(p -> Map.of("name", p.getName(), "field", p.getType().getSimpleName())).collect(Collectors.toList());
    }

    @GetMapping("/task/methods")
    public List<String> getTaskMethods() {
        return Arrays.stream(taskClass.getDeclaredMethods()).map(Method::getName).collect(Collectors.toList());
    }

    @GetMapping("/task")
    public ResponseEntity<TaskDto> update(@RequestParam Long id,
                                          @RequestParam String fieldName,
                                          @RequestParam String value) throws ApplicationException {
        TaskDto taskDto = taskService.findById(id);
        try {
            Class<?> taskDtoClass = TaskDto.class;
            Field field = taskDtoClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(taskDto, value);
            taskService.update(taskDto);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
