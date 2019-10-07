package com.example.android.popularmovies.utils;

import com.example.android.popularmovies.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    public static Movie getMovie() {
        return new Movie("Title", "/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg");
    }


    public static List<Movie> getMovies(int count) {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            movies.add(getMovie());
        }
        return movies;
    }
}
