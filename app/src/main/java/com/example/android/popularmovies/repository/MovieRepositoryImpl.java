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


//ToDo 2: Implement Movie Repository to fetch data from network
public class MovieRepositoryImpl implements MovieRepository {

    private ApiInterface service;
    private String apiKey;

    public MovieRepositoryImpl(ApiInterface service, String apiKey) {
        this.service = service;
        this.apiKey = apiKey;
    }

    //ToDo 3: implement get movies (Copy it from MainViewModel)
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

}
