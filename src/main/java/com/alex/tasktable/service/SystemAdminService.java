package com.alex.tasktable.service;

import com.alex.tasktable.dto.TaskDto;
import com.alex.tasktable.exceptions.ApplicationException;
import com.alex.tasktable.model.FieldChange;
import org.springframework.stereotype.Service;


public interface SystemAdminService {
    TaskDto updateField(Long id, FieldChange fieldChange) throws ApplicationException;
}
