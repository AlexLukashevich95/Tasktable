package com.alex.tasktable.controller;

import com.alex.tasktable.dto.TaskDto;
import com.alex.tasktable.exceptions.ApplicationException;
import com.alex.tasktable.model.FieldChange;
import com.alex.tasktable.model.Task;
import com.alex.tasktable.service.SystemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class SystemAdminController {
    @Autowired
    SystemAdminService systemAdminService;

    private final Class<?> taskClass = Task.class;

    @GetMapping("/task/fields")
    public List<Map<String, String>> getTaskFields() {
        return Arrays.stream(taskClass.getDeclaredFields()).map(p -> Map.of("name", p.getName(), "field", p.getType().getSimpleName())).collect(Collectors.toList());
    }

    @GetMapping("/task/methods")
    public List<String> getTaskMethods() {
        return Arrays.stream(taskClass.getDeclaredMethods()).map(Method::getName).collect(Collectors.toList());
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<TaskDto> update(@PathVariable Long id, @RequestBody FieldChange fieldChange) throws ApplicationException {
        systemAdminService.updateField(id, fieldChange);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
