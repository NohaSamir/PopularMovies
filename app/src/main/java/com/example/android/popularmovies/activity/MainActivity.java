package com.example.android.popularmovies.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.adapter.MoviesAdapter;
import com.example.android.popularmovies.databinding.ActivityMainBinding;
import com.example.android.popularmovies.model.Movie;
import com.example.android.popularmovies.view_model.MainViewModel;
import com.example.android.popularmovies.view_model.MainViewModelFactory;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static String API_KEY;
    private Context mContext;

    private List<Movie> mMovies = new ArrayList<>();
    MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        API_KEY = getString(R.string.api_key);
        mContext = this;
        moviesAdapter = new MoviesAdapter(mContext, mMovies);

        final ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //ToDo 12 : there is an error in logcat window with E/RecyclerView: No adapter attached; skipping layout
        // we can resolve this error by set the adapter with empty list in the first
        activityMainBinding.setAdapter(moviesAdapter);

        //ToDo 8: Delete  UI controller logic from the view
        /*
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = service.getPopularMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {

                List<Movie> movies = null;
                if (response.body() != null) {
                    movies = response.body().getResults();
                    //ToDo: Take care that we not set the adapter in the ViewModel and we will need to implement it in the view
                    activityMainBinding.setAdapter(new MoviesAdapter(mContext, movies));
                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesResponse> call, @NonNull Throwable t) {

            }
        });*/


        //ToDo 9 : Create View Model instance using our MainViewModelFactory to pass API Key
        final MainViewModel mainViewModel = ViewModelProviders.of(this, new MainViewModelFactory(API_KEY)).get(MainViewModel.class);

        //ToDo 10 : Get movies list and add it to the list
        mainViewModel.loadMovies(new MainViewModel.OnDataLoadListener() {
            @Override
            public void onSuccess(List<Movie> movies) {
                moviesAdapter.addItem(movies);
            }

            @Override
            public void onFailure() {
                Toast.makeText(mContext, R.string.error, Toast.LENGTH_LONG).show();
            }
        });


        //ToDo 11: Run your app
        /* Congratulation every things okay
         * we will not create a View Model for movie details because there is no UI Logic in this view
         * you will see that every things okay, but if you rotate your phone
         * and search in the logcat with key " MainViewModel "
         * Oops !!!! you will see that every time we rotate the screen we load the data again and again
         * we will resolve this issue in LiveData branch
         *
         */
    }
}
