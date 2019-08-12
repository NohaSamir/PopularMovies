package com.example.android.popularmovies;

import android.content.Context;

import com.example.android.popularmovies.repository.MovieRepository;
import com.example.android.popularmovies.rest.ApiClient;
import com.example.android.popularmovies.rest.ApiInterface;

//ToDo 3: Create injection class
/* Class that handles object creation.
 * Like this, objects can be passed as parameters in the constructors and then replaced for
 * testing, where needed.
 */
public class Injection {

    public static MovieRepository provideMovieRepository(Context context) {
        return new MovieRepository(provideAPIService(), provideAPIKey(context));
    }

    private static ApiInterface provideAPIService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }

    private static String provideAPIKey(Context context) {
        return context.getString(R.string.api_key);
    }
}
