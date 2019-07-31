package com.example.android.popularmovies.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.popularmovies.model.Movie;
import com.example.android.popularmovies.model.MoviesResponse;
import com.example.android.popularmovies.rest.ApiClient;
import com.example.android.popularmovies.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainViewModel extends ViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();

    private MutableLiveData<List<Movie>> movies = new MutableLiveData<>();

    private String apiKey;


    MainViewModel(String apiKey) {
        this.apiKey = apiKey;
        loadMovies();
    }

    private void loadMovies() {
        // we can copy it from the MainActivity

        Log.d(TAG, "Load Movies Called");

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);

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
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

}
