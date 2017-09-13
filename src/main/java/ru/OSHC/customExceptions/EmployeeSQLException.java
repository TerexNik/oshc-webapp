package ru.OSHC.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Somthing happen in data-base")
public class EmployeeSQLException extends SQLException {
    public EmployeeSQLException(String reason) {
        super(reason);
    }

    public EmployeeSQLException() {
        super();
    }
}
