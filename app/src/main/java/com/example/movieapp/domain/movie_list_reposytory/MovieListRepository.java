package com.example.movieapp.domain.movie_list_reposytory;

import android.text.TextUtils;

import com.example.movieapp.data.api.Rest;
import com.example.movieapp.data.model.base.ListResponse;
import com.example.movieapp.data.model.common.Movie;
import com.example.movieapp.data.service.MovieListService;
import com.example.movieapp.data.service.MovieSearchService;
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
    private MovieSearchService mMovieSearchService;

    @AfterInject
    protected void initServices() {
        mMovieListService = rest.getMovieListService();
        mMovieSearchService = rest.getMovieSearchService();
    }


    @Override
    public Observable<ListResponse<Movie>> getMovieList(int _page, String _searchText) {
        if (!TextUtils.isEmpty(_searchText))
            return getNetworkObservable(mMovieSearchService.searchMovie(_searchText, _page));
        else
            return getNetworkObservable(mMovieListService.getAllMovies(_page));
    }
}
