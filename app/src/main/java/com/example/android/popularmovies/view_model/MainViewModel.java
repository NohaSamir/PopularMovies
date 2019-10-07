package com.example.android.popularmovies.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.popularmovies.model.Movie;
import com.example.android.popularmovies.repository.MovieRepository;

import java.util.List;


public class MainViewModel extends ViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();

    private MutableLiveData<List<Movie>> movies;

    MainViewModel(MovieRepository repository) {
        movies = repository.getMovies();
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

}
