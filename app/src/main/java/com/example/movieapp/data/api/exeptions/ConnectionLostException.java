package com.example.movieapp.data.api.exeptions;

public class ConnectionLostException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Internet connection lost";
    }
}

