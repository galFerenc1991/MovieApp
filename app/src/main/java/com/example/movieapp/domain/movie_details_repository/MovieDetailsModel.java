package com.example.movieapp.domain.movie_details_repository;


import com.example.movieapp.data.model.common.MovieDetails;

import io.reactivex.Observable;

public interface MovieDetailsModel {

    Observable<MovieDetails> getMovieDetails(int _movieID);

}
