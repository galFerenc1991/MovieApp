package com.example.movieapp.data.api;

public abstract class RestConstants {
    static final String BASE_URL = "https://api.themoviedb.org/";
    public static final String API_KEY = "4423e8392de79824a85f90ecf9fc6c55";

    static final String HEADER_CONTENT_TYPE = "Content-Type";
    static final String HEADER_VALUE_HTML = "text/html";

    static final long TIMEOUT = 30; //seconds
    static final long TIMEOUT_READ = 30; //seconds
    static final long TIMEOUT_WRITE = 60; //seconds
}
