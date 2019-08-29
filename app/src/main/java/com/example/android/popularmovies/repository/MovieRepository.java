package com.example.android.popularmovies.repository;

import androidx.lifecycle.LiveData;

import com.example.android.popularmovies.model.Movie;

import java.util.List;


public interface MovieRepository {

    LiveData<List<Movie>> getMovies();
}
