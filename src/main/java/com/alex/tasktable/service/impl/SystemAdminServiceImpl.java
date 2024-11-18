package com.alex.tasktable.service.impl;

import com.alex.tasktable.dto.TaskDto;
import com.alex.tasktable.exceptions.ApplicationException;
import com.alex.tasktable.model.FieldChange;
import com.alex.tasktable.service.SystemAdminService;
import com.alex.tasktable.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
@Service
public class SystemAdminServiceImpl implements SystemAdminService {
    @Autowired
    TaskService taskService;

    @Override
    public TaskDto updateField(Long id, FieldChange fieldChange) throws ApplicationException {
        TaskDto taskDto = taskService.findById(id);
        try {
            Class<?> taskDtoClass = TaskDto.class;
            Field field = taskDtoClass.getDeclaredField(fieldChange.getName());
            field.setAccessible(true);
            field.set(taskDto, fieldChange.getValue());
            return taskService.update(taskDto);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
