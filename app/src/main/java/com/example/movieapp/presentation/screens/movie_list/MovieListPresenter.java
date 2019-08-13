package com.example.movieapp.presentation.screens.movie_list;

import com.example.movieapp.data.api.exeptions.ConnectionLostException;
import com.example.movieapp.data.model.common.Movie;
import com.example.movieapp.domain.movie_list_reposytory.MovieListModel;
import com.example.movieapp.presentation.utils.Constants;
import com.example.movieapp.presentation.utils.ToastManager;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MovieListPresenter implements MovieListContract.Presenter {

    private MovieListContract.View mView;
    private MovieListModel mMovieListModel;
    private CompositeDisposable mCompositeDisposable;

    private int mPage;
    private int mTotalPages = Integer.MAX_VALUE;
    private boolean mNeedRefresh;
    private String mSearchText;

    private Observable<String> mSearchObservable;


    public MovieListPresenter(MovieListContract.View _view
            , MovieListModel _movieListModel) {
        this.mView = _view;
        this.mMovieListModel = _movieListModel;
        this.mCompositeDisposable = new CompositeDisposable();

        this.mPage = 1;
        this.mNeedRefresh = true;
        this.mSearchText = null;


        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        if (mNeedRefresh) {
            mView.showProgressMain();
            loadMovieList(1, mSearchText);
        }

        mCompositeDisposable.add(mSearchObservable.debounce(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String result) throws Exception {
                        mPage = 1;
                        mSearchText = result;
                        loadMovieList(1, result);
                    }
                }));
    }

    private void loadMovieList(int _page, String _searchText) {
        mCompositeDisposable.add(mMovieListModel.getMovieList(_page, _searchText)
                .subscribe(movieListResponse -> {
                    mView.hideProgress();
                    this.mPage = _page;
                    mTotalPages = movieListResponse.getTotalPages();

                    if (mPage == 1) {
                        mView.setMovieList(movieListResponse.getResults());
                        mNeedRefresh = movieListResponse.getResults().isEmpty();
                        if (movieListResponse.getResults().isEmpty()) {
//                            mView.showPlaceHolderText();
                        }
                    } else {
                        mView.addMovieList(movieListResponse.getResults());
                    }
                }, throwableConsumer));
    }

    @Override
    public void loadNextPage() {
        if (mPage < mTotalPages) {
            mView.showProgressPagination();
            loadMovieList(mPage + 1, mSearchText);
        }
    }

    @Override
    public void onRefresh() {
        loadMovieList(1, mSearchText);
    }

    @Override
    public void clickedMovieItem(Movie _movie) {
        mView.openMovieDetailScreen(_movie.getId());

    }

    @Override
    public void setSearchObservable(Observable<String> _searchObservable) {
        mSearchObservable = _searchObservable;
    }

    private Consumer<Throwable> throwableConsumer = throwable -> {
        throwable.printStackTrace();
        mView.hideProgress();
        if (throwable instanceof ConnectionLostException) {
            ToastManager.showToast("Connection Lost");
        } else {
            ToastManager.showToast("Something went wrong");
        }
    };

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }
}
