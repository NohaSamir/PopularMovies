package com.example.android.popularmovies.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.android.popularmovies.Injection;
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

        // API_KEY = getString(R.string.api_key);
        mContext = this;
        moviesAdapter = new MoviesAdapter(mMovies);

        final ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setAdapter(moviesAdapter);

        //ToDo 6 : send the repository as a parameter using Injection class
        final MainViewModel mainViewModel = ViewModelProviders.of(this, new MainViewModelFactory(Injection.provideMovieRepository(this)))
                .get(MainViewModel.class);

        mainViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                if (movies != null) {
                    moviesAdapter.addItem(movies);
                } else {
                    Toast.makeText(mContext, R.string.error, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
