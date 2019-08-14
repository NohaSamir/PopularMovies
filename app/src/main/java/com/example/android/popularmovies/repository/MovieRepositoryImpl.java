package com.example.android.popularmovies.repository;


import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.android.popularmovies.local.MoviesDao;
import com.example.android.popularmovies.model.Movie;
import com.example.android.popularmovies.model.MoviesResponse;
import com.example.android.popularmovies.network.ApiInterface;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieRepositoryImpl implements MovieRepository {

    private ApiInterface service;
    private MoviesDao cache;
    private String apiKey;
    private Executor executor = Executors.newSingleThreadExecutor();


    public MovieRepositoryImpl(ApiInterface service, MoviesDao cache, String apiKey) {
        this.service = service;
        this.apiKey = apiKey;
        this.cache = cache;
    }


    public LiveData<List<Movie>> getMovies() {

        //Read from cache
        LiveData<List<Movie>> moviesList = cache.getMovies();

        Call<MoviesResponse> call = service.getPopularMovies(apiKey);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {

                if (response.body() != null) {

                    List<Movie> movies = response.body().getResults();

                    executor.execute(() ->
                    {
                        //clear cache and save new data in cache
                        cache.deleteAll();
                        cache.insert(movies);
                    });


                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesResponse> call, @NonNull Throwable t) {
            }
        });

        return moviesList;
    }

}

