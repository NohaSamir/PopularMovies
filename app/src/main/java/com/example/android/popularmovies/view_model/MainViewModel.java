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

    //ToDo 1 : Create an instance of LiveData to hold movie List
    //private List<Movie> movies;
    private MutableLiveData<List<Movie>> movies = new MutableLiveData<>();

    private String apiKey;


    MainViewModel(String apiKey) {
        this.apiKey = apiKey;
        //ToDo 4: call method load movies in the constructor
        loadMovies();
    }


    //ToDo 2 : Delete On data load listener
    /*public interface OnDataLoadListener {

        void onSuccess(List<Movie> movies);

        void onFailure();
    }


    public void loadMovies(final OnDataLoadListener onDataLoadListener) {
        // we can copy it from the MainActivity

        Log.d(TAG, "Load Movies Called");

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = service.getPopularMovies(apiKey);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {

                if (response.body() != null) {
                    movies = response.body().getResults();
                    onDataLoadListener.onSuccess(movies);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesResponse> call, @NonNull Throwable t) {
                onDataLoadListener.onFailure();
            }
        });
    }*/

    //ToDo 3: Create method to Load movies list and set LiveData object
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

    //ToDo 5: create method return movies list
    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

}
