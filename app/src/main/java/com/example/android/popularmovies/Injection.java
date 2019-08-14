package com.example.android.popularmovies;

import android.content.Context;

import com.example.android.popularmovies.local.MoviesDao;
import com.example.android.popularmovies.local.MoviesDatabase;
import com.example.android.popularmovies.network.ApiClient;
import com.example.android.popularmovies.network.ApiInterface;
import com.example.android.popularmovies.repository.MovieRepository;
import com.example.android.popularmovies.repository.MovieRepositoryImpl;


public class Injection {

    //ToDo 8: Modify the repository to take MovieDao as an argument
    public static MovieRepository provideMovieRepository(Context context) {
        return new MovieRepositoryImpl(provideAPIService(),provideMovieDao(context), provideAPIKey(context));
    }

    private static ApiInterface provideAPIService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }

    private static String provideAPIKey(Context context) {
        return context.getString(R.string.api_key);
    }

    //ToDo 7: Inject Movie Dao Creation
    private static MoviesDao provideMovieDao(Context context) {
        return MoviesDatabase.getInstance(context).moviesDao();
    }
}
