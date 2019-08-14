package com.example.android.popularmovies.repository;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import com.example.android.popularmovies.model.Movie;

import java.util.List;

public interface MovieRepository {

    LiveData<List<Movie>> getMovies();
}