package com.example.movieapp.presentation.screens.movie_list.search;

import androidx.appcompat.widget.SearchView;

import io.reactivex.Observable;
import com.jakewharton.rxrelay2.PublishRelay;


public class RxSearchObservable {

    public static Observable<String> fromView(SearchView searchView) {

        final PublishRelay<String> relay = PublishRelay.create();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchView.clearFocus();
                relay.accept(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                relay.accept(text);
                return true;
            }
        });

        return relay;
    }
}
