package com.alex.tasktable.mapper;

import com.alex.tasktable.dto.TaskDto;
import com.alex.tasktable.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
@Mapper(componentModel = "spring")
@Component
public interface TaskMapper {
    TaskDto toDto(Task task);
    Task toModel(TaskDto taskDto);
}
