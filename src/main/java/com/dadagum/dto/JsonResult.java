package com.dadagum.dto;

public class JsonResult<T> {

    private T data;
    private String message;
    private boolean status;

    public JsonResult() {
    }

    public JsonResult(T data, String message, boolean status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
}
