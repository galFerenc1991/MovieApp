package com.example.movieapp.data.service;

import com.example.movieapp.data.model.common.MovieDetails;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDetailsService {

    @GET("/3/movie/{movie_id}")
    Observable<MovieDetails> getMovieDetails(@Path("movie_id") int _id, @Query("api_key") String _apiKey);
}
