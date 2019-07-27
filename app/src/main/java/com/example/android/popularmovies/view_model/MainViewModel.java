package com.example.android.popularmovies.view_model;

/*
View Model Features
1- The ViewModel class is designed to separate out view data ownership from UI controller logic.
2- The ViewModel class allows data to survive configuration changes such as screen rotations using LiveDate.

On This branch we will develop the first one
and develop the second feature on LiveData branch
*/

//ToDo 2 : Implement Main View Model that is responsible for preparing data for the Main Activity that display list of movies

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.popularmovies.model.Movie;
import com.example.android.popularmovies.model.MoviesResponse;
import com.example.android.popularmovies.rest.ApiClient;
import com.example.android.popularmovies.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//ToDo 3 : Make the class extended from View Model
/*
 * In case you not need to pass data from the activity to View Model extend ViewModel
 * else You need to have a factory class for your ViewModel
 *
 * In our case we need to pass API_Key and so we will create MainViewModelFactory in To-Do 4 @{link MainViewModelFactory}
 */
public class MainViewModel extends ViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();

    private List<Movie> movies;
    private String apiKey;

    //ToDo  5 : Define the constructor with the parameter needed
    MainViewModel(String apiKey) {
        this.apiKey = apiKey;
    }

    //ToDo 6: Create fetch data listener
    public interface OnDataLoadListener {

        void onSuccess(List<Movie> movies);

        void onFailure();
    }

    //ToDo 7: Do an asynchronous operation to fetch movies.
    public void loadMovies(final OnDataLoadListener onDataLoadListener) {
        // we can copy it from the MainActivity

        Log.d(TAG, "Load Movies Called");

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = service.getPopularMovies(apiKey);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {

                if (response.body() != null) {
                    movies = response.body().getResults();
                    onDataLoadListener.onSuccess(movies);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesResponse> call, @NonNull Throwable t) {
                onDataLoadListener.onFailure();
            }
        });
    }

}
