package com.brn.sort.web;

public class ErrorMessage {

    private String message;

    public ErrorMessage() {
        //empty
    }

    public ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
