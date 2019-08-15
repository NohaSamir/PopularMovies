package com.example.android.popularmovies.repository;


import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.example.android.popularmovies.local.MoviesDao;
import com.example.android.popularmovies.model.Movie;
import com.example.android.popularmovies.network.ApiInterface;
import com.example.android.popularmovies.network.MoviesBoundaryCallback;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class MovieRepositoryImpl implements MovieRepository {

    private static final int PAGE_SIZE = 20;
    private ApiInterface service;
    private MoviesDao cache;
    private String apiKey;
    private Executor executor = Executors.newSingleThreadExecutor();


    public MovieRepositoryImpl(ApiInterface service, MoviesDao cache, String apiKey) {
        this.service = service;
        this.apiKey = apiKey;
        this.cache = cache;
    }

    //ToDo 3.3 :Replace occurrences of List<Movie> with PagedList<Movie>
    public LiveData<PagedList<Movie>> getMovies() {

        //ToDo 5: Get data source factory from the local cache
        DataSource.Factory<Integer, Movie> movieFactory = cache.getMovies();


        //ToDo 6: when the user reaches to the end of list we need to load more data from the network
        // The BoundaryCallback will observe when the user reaches to the edges of the list and update the database with extra data
        // lets create MoviesBoundaryCallback inside network package

        //ToDo 7 : define instance of MoviesBoundaryCallback
        MoviesBoundaryCallback moviesBoundaryCallback = new MoviesBoundaryCallback(service, cache, apiKey);

        //ToDo 8: Build and configure a paged list using LivePagedListBuilder
        LiveData<PagedList<Movie>> moviesList = new LivePagedListBuilder(movieFactory, PAGE_SIZE)
                .setBoundaryCallback(moviesBoundaryCallback)
                .build();


        return moviesList;


        //Read from cache
        //LiveData<List<Movie>> moviesList = cache.getMovies();

        /*Call<MoviesResponse> call = service.getPopularMovies(apiKey);
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
        */

    }

}

