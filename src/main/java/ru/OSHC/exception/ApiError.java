package ru.OSHC.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class ApiError {
    private String name;
    private String message;
    private String describe;

    public ApiError(String name) {
        this.name = name;
    }

    public ApiError(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public ApiError(String name, String message, String describe) {
        this.name = name;
        this.message = message;
        this.describe = describe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }
}
