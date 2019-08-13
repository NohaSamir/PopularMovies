package com.example.android.popularmovies.model;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.example.android.popularmovies.rest.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//ToDo 3: DataSource is the base class for loading snapshots of data into a PagedList.
/* If your app gets data directly from the network and displays it without caching, then the class that makes network requests would be your data source.
 * else if your app gets data from room database then The Room persistence library provides native support for data sources associated with the Paging library.
 * *
 * In our case we will create our data source using the network call from movie repository
 */
public class MoviesDataSource extends PageKeyedDataSource<Integer, Movie> {

    private ApiInterface service;
    private String apiKey;

    MoviesDataSource(ApiInterface service, String apiKey) {
        this.service = service;
        this.apiKey = apiKey;
    }

    /*Load initial page*/
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Movie> callback) {
        Call<MoviesResponse> call = service.getPopularMovies(apiKey, 1);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {

                if (response.body() != null) {
                    MoviesResponse moviesResponse = response.body();

                    int currentPageNum = moviesResponse.getPage();
                    int totalNumOfPages = moviesResponse.getTotalPages();

                    if (totalNumOfPages > currentPageNum)
                        callback.onResult(response.body().getResults(), null, currentPageNum + 1);
                    else
                        callback.onResult(response.body().getResults(), null, null);

                } else {
                    callback.onResult(new ArrayList<Movie>(), null, null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesResponse> call, @NonNull Throwable t) {
                callback.onResult(new ArrayList<Movie>(), null, null);
            }
        });

    }

    /*we scroll in one direction and so we will leave it blank*/
    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {

    }

    /*Load remaining pages*/
    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Movie> callback) {
        Call<MoviesResponse> call = service.getPopularMovies(apiKey, params.key);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {

                if (response.body() != null) {
                    MoviesResponse moviesResponse = response.body();

                    int currentPageNum = moviesResponse.getPage();
                    int totalNumOfPages = moviesResponse.getTotalPages();

                    if (totalNumOfPages > currentPageNum)
                        callback.onResult(response.body().getResults(), currentPageNum + 1);
                    else
                        callback.onResult(response.body().getResults(), null);

                } else {
                    callback.onResult(new ArrayList<Movie>(), null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesResponse> call, @NonNull Throwable t) {
                callback.onResult(new ArrayList<Movie>(), null);
            }
        });

    }
}


