package com.example.android.popularmovies.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.example.android.popularmovies.Injection;
import com.example.android.popularmovies.R;
import com.example.android.popularmovies.adapter.MoviesAdapter;
import com.example.android.popularmovies.databinding.ActivityMainBinding;
import com.example.android.popularmovies.model.Movie;
import com.example.android.popularmovies.view_model.MainViewModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Context mContext;

    private List<Movie> mMovies = new ArrayList<>();
    private MoviesAdapter moviesAdapter;

    @VisibleForTesting
    public MainViewModel mainViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        moviesAdapter = new MoviesAdapter(mMovies);

        final ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setAdapter(moviesAdapter);


        mainViewModel = new Injection().provideMainViewModel(this);

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
