package com.example.movieapp.presentation.base.refreshable;

import com.example.movieapp.presentation.base.BasePresenter;

public interface RefreshablePresenter extends BasePresenter {
    void onRefresh();
}
