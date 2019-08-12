package com.example.android.popularmovies.repository;

import android.arch.lifecycle.LiveData;

import com.example.android.popularmovies.model.Movie;

import java.util.List;

//ToDo 1 : Create The Repository
/* A Repository is a class that abstracts access to multiple data sources.
 * The Repository is not part of the Architecture Components libraries, but is a suggested best practice for code separation and architecture.
 * A Repository class handles data operations.
 * It provides a clean API to the rest of the app for app data.
 * -
 * In movie app, Movie repository will fetch movies list from a network
 */

public interface MovieRepository {

    LiveData<List<Movie>> getMovies();
}
