package ru.OSHC.exception;

import java.sql.SQLException;

public class CustomSQLException extends SQLException {
    public CustomSQLException(String reason) {
        super(reason);
    }

    public CustomSQLException() {
    }
}
