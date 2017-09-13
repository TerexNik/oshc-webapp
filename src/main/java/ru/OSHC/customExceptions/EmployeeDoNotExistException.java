package ru.OSHC.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such employee")
public class EmployeeDoNotExistException extends NullPointerException {
    public EmployeeDoNotExistException() {
        super();
    }

    public EmployeeDoNotExistException(String message) {
        super(message);
    }
}
