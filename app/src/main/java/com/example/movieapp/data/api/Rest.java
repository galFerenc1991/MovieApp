package com.example.movieapp.data.api;

import com.example.movieapp.MovieApp;
import com.example.movieapp.data.api.exeptions.ConnectionLostException;
import com.example.movieapp.data.api.exeptions.TimeoutException;
import com.example.movieapp.data.service.MovieDetailsService;
import com.example.movieapp.data.service.MovieListService;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EBean;

import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@EBean(scope = EBean.Scope.Singleton)
public class Rest {

    private Retrofit retrofit;

    @App
    protected MovieApp application;

    public Rest() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .connectTimeout(RestConstants.TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(RestConstants.TIMEOUT_READ, TimeUnit.SECONDS)
                .writeTimeout(RestConstants.TIMEOUT_WRITE, TimeUnit.SECONDS)
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(chain -> {
                    try {
                        if (!application.hasInternetConnection()) {
                            throw new ConnectionLostException();
                        } else {
                            Request.Builder requestBuilder = chain.request().newBuilder()
                                    .header(RestConstants.HEADER_CONTENT_TYPE, RestConstants.HEADER_VALUE_HTML);
                            return chain.proceed(requestBuilder.build());
                        }
                    } catch (SocketTimeoutException e) {
                        throw new TimeoutException();
                    }
                });


        retrofit = new Retrofit.Builder()
                .baseUrl(RestConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(clientBuilder.build())
                .build();

    }


    public MovieListService getMovieListService() {
        return retrofit.create(MovieListService.class);
    }

    public MovieDetailsService getMovieDetailsService() {
        return retrofit.create(MovieDetailsService.class);

    }

}
