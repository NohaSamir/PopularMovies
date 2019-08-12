package com.example.android.popularmovies.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.popularmovies.model.Movie;
import com.example.android.popularmovies.repository.MovieRepository;

import java.util.List;

//ToDo 4: Use repository to get Movies
/* We will edit the ViewModel to take repository as a parameter and get movies from it
 */

public class MainViewModel extends ViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<List<Movie>> movies;

    MainViewModel(MovieRepository repository) {
        movies = repository.getMovies();
    }

    /*MainViewModel(String apiKey) {
        this.apiKey = apiKey;
        loadMovies();
    }*/

    /* private void loadMovies() {
         // we can copy it from the MainActivity

         Log.d(TAG, "Load Movies Called");

         ApiInterface service = ApiClient.getClient().create(ApiInterface.class);

         Call<MoviesResponse> call = service.getPopularMovies(apiKey, 2);
         call.enqueue(new Callback<MoviesResponse>() {
             @Override
             public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {

                 if (response.body() != null) {
                     movies.setValue(response.body().getResults());
                 } else {
                     movies.setValue(null);
                 }
             }

             @Override
             public void onFailure(@NonNull Call<MoviesResponse> call, @NonNull Throwable t) {
                 movies.setValue(null);
             }
         });
     }
 */
    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

}
