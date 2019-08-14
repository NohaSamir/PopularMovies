package com.example.android.popularmovies.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.popularmovies.model.Movie;
import com.example.android.popularmovies.repository.MovieRepository;

import java.util.List;


public class MainViewModel extends ViewModel {

    private LiveData<List<Movie>> movies;

    MainViewModel(MovieRepository repository) {
        movies = repository.getMovies();
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

}
