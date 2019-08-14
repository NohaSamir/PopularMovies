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

    //ToDo 8: Modify the repository to take MovieDao as an argument
    public MovieRepositoryImpl(ApiInterface service, MoviesDao cache, String apiKey) {
        this.service = service;
        this.apiKey = apiKey;
        this.cache = cache;
    }


    //ToDo 9: Edit get movies to read from cache and save data to cache
    public LiveData<List<Movie>> getMovies() {

        //final MutableLiveData<List<Movie>> movies = new MutableLiveData<>();

        //Read from cache
        LiveData<List<Movie>> moviesList = cache.getMovies();

        Call<MoviesResponse> call = service.getPopularMovies(apiKey);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {

                if (response.body() != null) {

                    List<Movie> movies = response.body().getResults();

                    //save data in cache
                    executor.execute(() -> cache.insert(movies));
                    //movies.setValue(response.body().getResults());

                }
                /*else {
                    movies.setValue(null);
                }*/
            }

            @Override
            public void onFailure(@NonNull Call<MoviesResponse> call, @NonNull Throwable t) {
                //movies.setValue(null);
            }
        });

        return moviesList;
    }

}

