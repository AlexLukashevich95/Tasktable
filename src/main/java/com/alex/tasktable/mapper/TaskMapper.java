package com.alex.tasktable.mapper;

import com.alex.tasktable.dto.TaskDto;
import com.alex.tasktable.model.Task;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
@Component
public class TaskMapper {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    public TaskDto toDto(Task task)   {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setDescription(task.getDescription());
        dto.setDeadline(task.getDeadline().format(dateTimeFormatter));
        dto.setStatus(task.getStatus());
        return dto;
    }
}
