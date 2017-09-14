package ru.OSHC.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.OSHC.exception.ApiError;
import ru.OSHC.exception.FileNotFoundException;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(FileNotFoundException.class)
    public @ResponseBody ResponseEntity<ApiError> returnErrorInfo(Exception e) {
        return new ResponseEntity<ApiError>(new ApiError("Файл не найден", e.getMessage()), HttpStatus.I_AM_A_TEAPOT);
    }
}
