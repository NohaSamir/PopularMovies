package com.example.android.popularmovies.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;

import com.example.android.popularmovies.model.Movie;
import com.example.android.popularmovies.repository.MovieRepository;


public class MainViewModel extends ViewModel {

    private LiveData<PagedList<Movie>> movies;

    MainViewModel(MovieRepository repository) {
        movies = repository.getMovies();
    }

    public LiveData<PagedList<Movie>> getMovies() {
        return movies;
    }

}
