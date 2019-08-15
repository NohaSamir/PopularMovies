package com.example.android.popularmovies.repository;


import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.example.android.popularmovies.local.MoviesDao;
import com.example.android.popularmovies.model.Movie;
import com.example.android.popularmovies.network.ApiInterface;
import com.example.android.popularmovies.network.MoviesBoundaryCallback;


public class MovieRepositoryImpl implements MovieRepository {

    private static final int PAGE_SIZE = 20;
    private ApiInterface service;
    private MoviesDao cache;
    private String apiKey;


    public MovieRepositoryImpl(ApiInterface service, MoviesDao cache, String apiKey) {
        this.service = service;
        this.apiKey = apiKey;
        this.cache = cache;
    }

    public LiveData<PagedList<Movie>> getMovies() {


        DataSource.Factory<Integer, Movie> movieFactory = cache.getMovies();

        MoviesBoundaryCallback moviesBoundaryCallback = new MoviesBoundaryCallback(service, cache, apiKey);

        LiveData<PagedList<Movie>> moviesList = new LivePagedListBuilder(movieFactory, PAGE_SIZE)
                .setBoundaryCallback(moviesBoundaryCallback)
                .build();


        return moviesList;

    }

}

