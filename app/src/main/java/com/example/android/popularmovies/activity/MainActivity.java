package com.example.android.popularmovies.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.adapter.MoviesAdapter;
import com.example.android.popularmovies.databinding.ActivityMainBinding;
import com.example.android.popularmovies.model.Movie;
import com.example.android.popularmovies.view_model.MainViewModel;
import com.example.android.popularmovies.view_model.MainViewModelFactory;

import java.util.ArrayList;
import java.util.List;

//ToDo Introduction: Work with LiveData
/* LiveData is an observable data holder class
 * But what the mean of observable ? It mean that we need to listen if the data changed, it must call onChanged( ) method.
 * *
 * But what is the difference between Listener , Observable , LiveData ?
 * Listener: when one object changes state, only the class that implement this listener will be notified.
 * Observable: when one object changes state, all of its dependents are notified and updated automatically.
 * LiveData: is an observable data and also LiveData is lifecycle-aware ,
 * meaning it only updates app component observers that are in an active lifecycle state ( STARTED or RESUMED ) .
 * *
 * what is the difference between LiveData and MutableLiveData?
 * MutableLiveData can be updated using setValue(T) and postValue(T) methods
 * *
 * Now we will replace listener with LiveData object
 * 1- Create an instance of LiveData in ViewModel
 * 2- Create an Observer object in UI controller
 * 3- Attach the Observer object to the LiveData object using the observe() method.
 */

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
        moviesAdapter = new MoviesAdapter(mMovies);

        final ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setAdapter(moviesAdapter);

        final MainViewModel mainViewModel = ViewModelProviders.of(this, new MainViewModelFactory(API_KEY)).get(MainViewModel.class);

        //ToDo 6: Delete unused data load listener
        /*mainViewModel.loadMovies(new MainViewModel.OnDataLoadListener() {
            @Override
            public void onSuccess(List<Movie> movies) {
                moviesAdapter.addItem(movies);
            }

            @Override
            public void onFailure() {
                Toast.makeText(mContext, R.string.error, Toast.LENGTH_LONG).show();
            }
        });*/


        //ToDo 7: Create an Observer object and attach it to LiveData
        mainViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                moviesAdapter.addItem(movies);
            }
        });


        //ToDo 8: Run your app
        /* Congratulation every things okay
         * if you rotate your phone now, and search in the logcat with key " MainViewModel " ( MainViewModel: Load Movies Called )
         * you will see that every time we rotate the screen we load the data only one time
         * Now The ViewModel class allows data to survive configuration changes such as screen rotations using LiveDate.
         */

    }
}
