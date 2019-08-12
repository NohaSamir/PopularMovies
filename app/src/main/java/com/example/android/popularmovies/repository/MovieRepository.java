package com.example.android.popularmovies.repository;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.android.popularmovies.model.Movie;
import com.example.android.popularmovies.model.MoviesResponse;
import com.example.android.popularmovies.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private static final int DATABASE_PAGE_SIZE = 20;
    private int lastRequestedPage;
    private boolean isRequestInProgress;
    private MutableLiveData<List<Movie>> movies;

    private ApiInterface service;
    private String apiKey;

    public MovieRepository(ApiInterface service, String apiKey) {
        this.service = service;
        this.apiKey = apiKey;

        isRequestInProgress = false;
        lastRequestedPage = 1;
        movies = new MutableLiveData<>();
    }

    public LiveData<List<Movie>> getMovies() {

        if (isRequestInProgress) return movies;

        isRequestInProgress = true;

        Call<MoviesResponse> call = service.getPopularMovies(apiKey, lastRequestedPage);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {

                isRequestInProgress = false;

                if (response.body() != null) {
                    movies.setValue(response.body().getResults());
                    lastRequestedPage++;
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

}
