package com.example.android.popularmovies;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.android.popularmovies.repository.MovieRepository;
import com.example.android.popularmovies.repository.MovieRepositoryImpl;
import com.example.android.popularmovies.rest.ApiClient;
import com.example.android.popularmovies.rest.ApiInterface;
import com.example.android.popularmovies.view_model.MainViewModel;
import com.example.android.popularmovies.view_model.MainViewModelFactory;

public class Injection {

    public MainViewModel provideMainViewModel(Context context) {
        MainViewModelFactory factory = provideMainViewModelFactory(context);
        return ViewModelProviders.of((FragmentActivity) context, factory).get(MainViewModel.class);
    }
    public MainViewModelFactory provideMainViewModelFactory(Context context) {
        return new MainViewModelFactory(provideMovieRepository(context));
    }


    public MovieRepository provideMovieRepository(Context context) {
        return new MovieRepositoryImpl(provideAPIService(), provideAPIKey(context));
    }

    private ApiInterface provideAPIService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }

    private String provideAPIKey(Context context) {
        return context.getString(R.string.api_key);
    }
}
