package com.example.android.popularmovies;

import android.content.Context;

import com.example.android.popularmovies.repository.MovieRepository;
import com.example.android.popularmovies.repository.MovieRepositoryImpl;
import com.example.android.popularmovies.rest.ApiClient;
import com.example.android.popularmovies.rest.ApiInterface;


public class Injection {

    public static MovieRepository provideMovieRepository(Context context) {
        return new MovieRepositoryImpl(provideAPIService(), provideAPIKey(context));
    }

    private static ApiInterface provideAPIService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }

    private static String provideAPIKey(Context context) {
        return context.getString(R.string.api_key);
    }
}
