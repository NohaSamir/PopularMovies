package com.example.android.popularmovies.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;

import com.example.android.popularmovies.model.Movie;
import com.example.android.popularmovies.model.MoviesResponse;
import com.example.android.popularmovies.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieRepositoryImpl implements MovieRepository {

    private ApiInterface service;
    private String apiKey;

    public MovieRepositoryImpl(ApiInterface service, String apiKey) {
        this.service = service;
        this.apiKey = apiKey;
    }


    public MutableLiveData<List<Movie>> getMovies() {

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

}
