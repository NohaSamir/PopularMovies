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

    //ToDo 5 : Update my repository to return LiveData<PagedList> instead of LiveData<List> using LivePagedListBuilder
    @Override
    public LiveData<PagedList<Movie>> getMovies() {
        MoviesDataSourceFactory dataSourceFactory = new MoviesDataSourceFactory(service, apiKey);
        return (LiveData<PagedList<Movie>>) new LivePagedListBuilder<>(dataSourceFactory, PAGE_SIZE).build();
    }

    /*
    public LiveData<List<Movie>> getMovies() {

        final MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
        Call<MoviesResponse> call = service.getPopularMovies(apiKey);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {

                if (response.body() != null) {
                    movies.setValue(response.body().getResults());

                } else {
                    movies.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesResponse> call, @NonNull Throwable t) {
                movies.setValue(null);
            }
        });

        return movies;
    }
*/


}
