package com.example.android.popularmovies.model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.example.android.popularmovies.rest.ApiInterface;


//ToDo 4: create data source factory that is responsible for creating a DataSource.

public class MoviesDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<MoviesDataSource> sourceLiveData =
            new MutableLiveData<>();

    private ApiInterface service;
    private String apiKey;

    public MoviesDataSourceFactory(ApiInterface service, String apiKey) {
        this.service = service;
        this.apiKey = apiKey;
    }


    @Override
    public DataSource create() {
        MoviesDataSource moviesSource = new MoviesDataSource(service, apiKey);
        sourceLiveData.postValue(moviesSource);
        return moviesSource;
    }
}
