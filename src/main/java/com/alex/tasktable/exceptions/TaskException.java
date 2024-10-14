package com.alex.tasktable.exceptions;

import java.sql.SQLException;

public class TaskException extends SQLException {
    public TaskException() {
        super();
    }

    /*public TaskException(Long id) {
        super("Task not found by id - "+id.toString());
    }*/

    public TaskException(String message) {
        super(message);
    }

    public TaskException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskException(Throwable cause) {
        super(cause);
    }
}