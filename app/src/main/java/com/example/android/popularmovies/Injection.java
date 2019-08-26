package com.example.android.popularmovies;

import android.content.Context;

import com.example.android.popularmovies.local.MoviesDao;
import com.example.android.popularmovies.local.MoviesDatabase;
import com.example.android.popularmovies.network.ApiClient;
import com.example.android.popularmovies.network.ApiInterface;
import com.example.android.popularmovies.repository.MovieRepository;
import com.example.android.popularmovies.repository.MovieRepositoryImpl;


public class Injection {

    public static MovieRepository provideMovieRepository(Context context) {
        return new MovieRepositoryImpl(provideAPIService(), provideMovieDao(context), provideAPIKey(context));
    }

    public static ApiInterface provideAPIService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }

    public static String provideAPIKey(Context context) {
        return context.getString(R.string.api_key);
    }

    public static MoviesDao provideMovieDao(Context context) {
        return MoviesDatabase.getInstance(context).moviesDao();
    }
}
