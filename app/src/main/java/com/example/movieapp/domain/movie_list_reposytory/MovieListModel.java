package com.example.movieapp.domain.movie_list_reposytory;

import com.example.movieapp.data.model.base.ListResponse;
import com.example.movieapp.data.model.common.Movie;

import io.reactivex.Observable;

public interface MovieListModel {

    Observable<ListResponse<Movie>> getMovieList(int _page);

}
