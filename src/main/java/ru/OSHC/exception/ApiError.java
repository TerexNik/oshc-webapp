package ru.OSHC.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class ApiError {
    @JsonIgnore
    private Exception exception;
    private String name;
    private String describe;

    public ApiError(Exception exception) {
        this.exception = exception;
    }

    public ApiError(Exception exception, String name) {
        this.exception = exception;
        this.name = name;
    }

    public ApiError(Exception exception, String name, String describe) {
        this.exception = exception;
        this.name = name;
        this.describe = describe;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "exception=" + exception +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }
}
