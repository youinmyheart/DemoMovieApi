package com.demo.movieapi.model;

public class DataWrapper<T> {
    public enum Status {
        SUCCESS,
        ERROR
    }

    private T data;
    private Status status;
    private String message;

    public DataWrapper(T data, Status status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
