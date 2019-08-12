package com.example.movieapp.domain.movie_list_reposytory;

import com.example.movieapp.data.api.Rest;
import com.example.movieapp.data.api.RestConstants;
import com.example.movieapp.data.model.base.ListResponse;
import com.example.movieapp.data.model.common.Movie;
import com.example.movieapp.data.service.MovieListService;
import com.example.movieapp.domain.NetworkRepository;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import io.reactivex.Observable;


@EBean(scope = EBean.Scope.Singleton)
public class MovieListRepository extends NetworkRepository implements MovieListModel {

    @Bean
    protected Rest rest;

    private MovieListService mMovieListService;

    @AfterInject
    protected void initServices() {
        mMovieListService = rest.getMovieListService();
    }

    @Override
    public Observable<ListResponse<Movie>> getMovieList(int _page) {
        return getNetworkObservable(mMovieListService.getAllMovies(RestConstants.API_KEY, _page));
    }
}
