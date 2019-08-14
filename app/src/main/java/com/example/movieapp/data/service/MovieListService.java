package com.example.movieapp.data.service;

import com.example.movieapp.data.model.base.ListResponse;
import com.example.movieapp.data.model.common.Movie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieListService {

    @GET("/3/discover/movie")
    Observable<ListResponse<Movie>> getAllMovies(@Query("page") int _page);
}
