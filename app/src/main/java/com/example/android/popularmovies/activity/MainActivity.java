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
        activityMainBinding.setAdapter(moviesAdapter);

        final MainViewModel mainViewModel = ViewModelProviders.of(this, new MainViewModelFactory(API_KEY)).get(MainViewModel.class);

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

    }
}
