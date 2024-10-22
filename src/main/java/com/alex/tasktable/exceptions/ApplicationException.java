package com.alex.tasktable.exceptions;

import java.sql.SQLException;

public class ApplicationException extends SQLException {
    public ApplicationException() {
        super();
    }

    /*public TaskException(Long id) {
        super("Task not found by id - "+id.toString());
    }*/

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }
}