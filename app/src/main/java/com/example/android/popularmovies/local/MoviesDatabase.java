package com.example.android.popularmovies.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.android.popularmovies.model.Movie;

//ToDo 4: Create the Room database
// Create a public abstract class that extends RoomDatabase
// Annotate the class to be a Room database
@Database(entities = {Movie.class}, version = 1)
public abstract class MoviesDatabase extends RoomDatabase {

    //ToDo 5: Create method get instance
    // Make the MoviesDatabase a singleton to prevent having multiple instances of the database opened at the same time.

    private static volatile MoviesDatabase INSTANCE;

    public static MoviesDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (MoviesDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MoviesDatabase.class, "movies_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    //ToDo 6: Define the DAOs that work with the database. Provide an abstract "getter" method for each @Dao
    public abstract MoviesDao moviesDao();

}
