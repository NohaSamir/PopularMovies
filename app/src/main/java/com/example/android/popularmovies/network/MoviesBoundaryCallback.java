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

    //ToDo 5: Start download from page 4 if th user scroll
    private int lastRequestedPage = 4;
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

                    executor.execute(() -> {

                        //ToDo 6: Stop clear our database worker will clear it
                        /*if (lastRequestedPage == 1)
                            cache.deleteAll();*/

                        cache.insert(response.body().getResults());
                        lastRequestedPage++;
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
