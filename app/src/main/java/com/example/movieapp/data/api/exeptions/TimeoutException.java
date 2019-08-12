package com.example.movieapp.data.api.exeptions;

public class TimeoutException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Server doesn't respond";
    }
}
