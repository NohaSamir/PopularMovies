package com.example.android.popularmovies.local;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.android.popularmovies.model.Movie;

import java.util.List;


//ToDo 3: Create DAO interface
// A DAO (data access object) validates your SQL at compile-time and associate it with a method,
// so you don't have to worry about the SQL again... all with simple annotations like @Insert!

@Dao
public interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Movie> Movies);

    @Query("select * from MOVIE_TABLE")
    LiveData<List<Movie>> getMovies();

    @Query("DELETE FROM MOVIE_TABLE")
    void deleteAll();
}
