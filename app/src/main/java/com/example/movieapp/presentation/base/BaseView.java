package com.example.movieapp.presentation.base;

public interface BaseView<T extends BasePresenter> {
    void initPresenter();
    void setPresenter(T presenter);
}