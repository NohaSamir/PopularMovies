package com.example.android.popularmovies.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android.popularmovies.model.Movie;

import java.util.List;


public interface MovieRepository {

    MutableLiveData<List<Movie>> getMovies();
}
