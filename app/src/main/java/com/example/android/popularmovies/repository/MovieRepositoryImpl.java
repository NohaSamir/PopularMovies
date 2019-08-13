package com.example.android.popularmovies.repository;


import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.example.android.popularmovies.model.Movie;
import com.example.android.popularmovies.model.MoviesDataSourceFactory;
import com.example.android.popularmovies.rest.ApiInterface;


public class MovieRepositoryImpl implements MovieRepository {

    private static final int PAGE_SIZE = 20;

    private ApiInterface service;
    private String apiKey;

    public MovieRepositoryImpl(ApiInterface service, String apiKey) {
        this.service = service;
        this.apiKey = apiKey;
    }


    @Override
    public LiveData<PagedList<Movie>> getMovies() {
        MoviesDataSourceFactory dataSourceFactory = new MoviesDataSourceFactory(service, apiKey);
        return (LiveData<PagedList<Movie>>) new LivePagedListBuilder<>(dataSourceFactory, PAGE_SIZE).build();
    }
}
