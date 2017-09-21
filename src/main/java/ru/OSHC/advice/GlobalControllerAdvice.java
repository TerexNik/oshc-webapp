package ru.OSHC.advice;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.OSHC.annotation.Loggable;
import ru.OSHC.exception.ApiError;
import ru.OSHC.exception.FileNotFoundException;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalControllerAdvice {
    private static final Logger log = Logger.getLogger(GlobalControllerAdvice.class);

    @ExceptionHandler(NoResultException.class)
    public @ResponseBody ResponseEntity<ApiError> returnErrorInfoNoResultException(Exception e) {
        return returnErrorInfoFileNotFound(e);
    }

    @Loggable
    @ExceptionHandler(FileNotFoundException.class)
    public @ResponseBody ResponseEntity<ApiError> returnErrorInfoFileNotFound(Exception e) {
        return new ResponseEntity<ApiError>(new ApiError("Файл не найден", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @Loggable
    @ExceptionHandler(SQLException.class)
    public @ResponseBody ResponseEntity<ApiError> returnErrorInfoSQLEx(Exception e) {
        return new ResponseEntity<ApiError>(new ApiError("Проблемы с SQL запросами", e.getMessage()), HttpStatus.BAD_GATEWAY);
    }

    @Loggable
    @ExceptionHandler(PersistenceException.class)
    public @ResponseBody ResponseEntity<ApiError> returnErrorInfoPersistance(Exception e) {
        return new ResponseEntity<ApiError>(new ApiError("Ошибка в запросе", "Неправильный формат JSON"), HttpStatus.BAD_REQUEST);
    }
}
