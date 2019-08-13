package com.example.movieapp.presentation.screens.movie_list;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.data.model.common.Movie;
import com.example.movieapp.domain.movie_list_reposytory.MovieListRepository;
import com.example.movieapp.presentation.base.list.EndlessScrollListener;
import com.example.movieapp.presentation.base.refreshable.RefreshableFragment;
import com.example.movieapp.presentation.base.refreshable.RefreshablePresenter;
import com.example.movieapp.presentation.screens.movie_list.movie_list_adapter.MovieListAdapter;
import com.example.movieapp.presentation.utils.ToolbarManager;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;


@EFragment
public class MovieListFragment extends RefreshableFragment implements MovieListContract.View {

    private MovieListContract.Presenter mPresenter;
    @ViewById(R.id.rvMovieList_FML)
    protected RecyclerView rvMovieList;
    private ToolbarManager mToolbarManager;
    @Bean
    protected MovieListAdapter mMovieListAdapter;
    @Bean
    protected MovieListRepository mMovieListRepository;
    protected EndlessScrollListener mScrollListener;

    @AfterInject
    @Override
    public void initPresenter() {
        new MovieListPresenter(this, mMovieListRepository);
    }

    @AfterViews
    protected void initUI() {
        mToolbarManager = mActivity.getToolbarManager();
        mToolbarManager.setTitle("Movies");
        mToolbarManager.showToolbar(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mScrollListener = new EndlessScrollListener(layoutManager, () -> {
            if (isRefreshing()) return false;
            mPresenter.loadNextPage();
            return true;
        });

        rvMovieList.setLayoutManager(layoutManager);
        rvMovieList.setAdapter(mMovieListAdapter);
        rvMovieList.addOnScrollListener(mScrollListener);

        mPresenter.subscribe();
    }

    @Override
    public void setPresenter(MovieListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected RefreshablePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_movie_list;
    }

    @Override
    public void setMovieList(List<Movie> _movieList) {
        mScrollListener.reset();
        mMovieListAdapter.setMovieList(_movieList);
    }

    @Override
    public void addMovieList(List<Movie> _movieList) {
        mMovieListAdapter.addMovieListDH(_movieList);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }
}
