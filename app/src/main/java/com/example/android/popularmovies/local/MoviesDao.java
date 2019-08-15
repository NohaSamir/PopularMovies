package com.example.android.popularmovies.local;


import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.android.popularmovies.model.Movie;

import java.util.List;

@Dao
public interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Movie> Movies);

    @Query("select * from MOVIE_TABLE order by popularity desc")
    DataSource.Factory<Integer, Movie> getMovies();

    @Query("DELETE FROM MOVIE_TABLE")
    void deleteAll();
}
