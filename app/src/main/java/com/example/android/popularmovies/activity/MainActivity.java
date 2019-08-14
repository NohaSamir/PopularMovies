package com.example.android.popularmovies.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.android.popularmovies.Injection;
import com.example.android.popularmovies.R;
import com.example.android.popularmovies.adapter.MoviesAdapter;
import com.example.android.popularmovies.databinding.ActivityMainBinding;
import com.example.android.popularmovies.view_model.MainViewModel;
import com.example.android.popularmovies.view_model.MainViewModelFactory;


public class MainActivity extends AppCompatActivity {

    private Context mContext;

    //private List<Movie> mMovies = new ArrayList<>();
    MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        //ToDo 17: not pass list in the constructor
        moviesAdapter = new MoviesAdapter();

        final ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setAdapter(moviesAdapter);

        final MainViewModel mainViewModel = ViewModelProviders.of(this, new MainViewModelFactory(Injection.provideMovieRepository(this)))
                .get(MainViewModel.class);

        mainViewModel.getMovies().observe(this, movies -> {
            if (movies != null) {
                //ToDo 18 : submit your list
                moviesAdapter.submitList(movies);
            } else {
                Toast.makeText(mContext, R.string.error, Toast.LENGTH_LONG).show();
            }
        });
    }

    //ToDo 19 : Congratulation
    // Now your app will work in case offline or server error or timeout
    // Enjoy and wish me nice things :)
}