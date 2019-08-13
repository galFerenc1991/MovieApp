package com.example.movieapp.data.service;

import com.example.movieapp.data.model.base.ListResponse;
import com.example.movieapp.data.model.common.Movie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieSearchService {

    @GET("/3/search/movie")
    Observable<ListResponse<Movie>> searchMovie(@Query("api_key") String _apiKey,
                                                @Query("query") String _searchText,
                                                @Query("page") int _page);
}
