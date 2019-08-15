package com.example.android.popularmovies.network;

import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.example.android.popularmovies.local.MoviesDao;
import com.example.android.popularmovies.model.Movie;
import com.example.android.popularmovies.model.MoviesResponse;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MoviesBoundaryCallback extends PagedList.BoundaryCallback<Movie> {

    private int lastRequestedPage = 1;
    private boolean isRequestInProgress = false;

    private ApiInterface service;
    private MoviesDao cache;
    private String apiKey;
    private Executor executor = Executors.newSingleThreadExecutor();

    public MoviesBoundaryCallback(ApiInterface service, MoviesDao cache, String apiKey) {
        this.service = service;
        this.cache = cache;
        this.apiKey = apiKey;
    }


    private void requestAndSaveData() {

        if (isRequestInProgress) return;

        isRequestInProgress = true;
        service.getPopularMovies(apiKey, lastRequestedPage).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {

                isRequestInProgress = false;

                if (response.isSuccessful() && response.body() != null) {

                    lastRequestedPage++;

                    executor.execute(() -> {
                        if (lastRequestedPage == 1)
                            cache.deleteAll();

                        cache.insert(response.body().getResults());
                    });
                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesResponse> call, @NonNull Throwable t) {
                isRequestInProgress = false;
            }
        });
    }

    /**
     * Database returned 0 items. We should query the backend for more items.
     */
    @Override
    public void onZeroItemsLoaded() {
        requestAndSaveData();
    }

    /**
     * When all items in the database were loaded, we need to query the backend for more items.
     */
    @Override
    public void onItemAtEndLoaded(@NonNull Movie itemAtEnd) {
        requestAndSaveData();
    }


}
