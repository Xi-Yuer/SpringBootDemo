package com.example.springbootdemo.response;

public class ApiResponse<T> {
    private T data;
    private String message;
    private int code;

    public ApiResponse(T data, String message, int code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}