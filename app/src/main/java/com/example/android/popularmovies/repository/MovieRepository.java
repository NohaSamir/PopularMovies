package com.example.android.popularmovies.repository;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import com.example.android.popularmovies.model.Movie;

public interface MovieRepository {

    //ToDo 3.2 :Replace occurrences of List<Movie> with PagedList<Movie>
    LiveData<PagedList<Movie>> getMovies();
}