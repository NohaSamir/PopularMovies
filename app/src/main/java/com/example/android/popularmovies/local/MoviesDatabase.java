package com.example.android.popularmovies.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.android.popularmovies.model.Movie;

//ToDo 9.2: migrate db version
@Database(entities = {Movie.class}, version = 2)
public abstract class MoviesDatabase extends RoomDatabase {


    private static volatile MoviesDatabase INSTANCE;

    public static MoviesDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (MoviesDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MoviesDatabase.class, "movies_database")
                            .fallbackToDestructiveMigration() //ToDo 9.2 : Clear old database on migration
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    public abstract MoviesDao moviesDao();

}
