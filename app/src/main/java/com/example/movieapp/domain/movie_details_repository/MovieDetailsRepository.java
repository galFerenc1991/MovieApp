package com.example.movieapp.domain.movie_details_repository;

import com.example.movieapp.data.api.Rest;
import com.example.movieapp.data.api.RestConstants;
import com.example.movieapp.data.model.common.MovieDetails;
import com.example.movieapp.data.service.MovieDetailsService;
import com.example.movieapp.domain.NetworkRepository;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import io.reactivex.Observable;

@EBean(scope = EBean.Scope.Singleton)
public class MovieDetailsRepository extends NetworkRepository implements MovieDetailsModel {


    @Bean
    protected Rest rest;

    private MovieDetailsService mMovieDetailService;

    @AfterInject
    protected void initServices() {
        mMovieDetailService = rest.getMovieDetailsService();
    }

    @Override
    public Observable<MovieDetails> getMovieDetails(int _movieID) {
        return getNetworkObservable(mMovieDetailService.getMovieDetails(_movieID, RestConstants.API_KEY));
    }
}
