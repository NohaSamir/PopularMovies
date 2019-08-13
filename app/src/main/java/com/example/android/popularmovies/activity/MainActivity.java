package com.example.android.popularmovies.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
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

//ToDo: Paging Introduction
/*  The Paging Library helps you load and display small chunks of data at a time.
    Loading partial data on demand reduces usage of network bandwidth and system resources.
    The Paging library and its main components:
    ********************************************
    1- PagedList - a collection that loads data in pages, asynchronously.
    - A PagedList can be used to load data from sources you define, and present it easily in your UI with a RecyclerView.
    *
    2- DataSource and DataSource.Factory -
    - A DataSource is the base class for loading snapshots of data into a PagedList.
    - A DataSource.Factory is responsible for creating a DataSource.
    *
    3- LivePagedListBuilder - builds a LiveData<PagedList>, based on DataSource.Factory and a PagedList.Config.
    *
    4- BoundaryCallback - signals when a PagedList has reached the end of available data.
    - we will use it in case we read from local database and it reached to the end , at this time we need to call the network to load more data.
    - we will use it later
    *
    5- PagedListAdapter - a RecyclerView.Adapter that presents paged data from PagedLists in a RecyclerView.
    - PagedListAdapter listens to PagedList loading callbacks as pages are loaded, and uses DiffUtil to compute fine-grained updates as new PagedLists are received.
 */

//ToDo 1 :Add paging dependency
//ToDo 2 :Replace occurrences of List<Movie> with PagedList<Movie> but we will not change the MovieResponse ( you will find an error in repository skip it now)

public class MainActivity extends AppCompatActivity {

    private Context mContext;

    //private List<Movie> mMovies = new ArrayList<>();
    MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        moviesAdapter = new MoviesAdapter();

        final ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setAdapter(moviesAdapter);

        final MainViewModel mainViewModel = ViewModelProviders.of(this, new MainViewModelFactory(Injection.provideMovieRepository(this)))
                .get(MainViewModel.class);

        mainViewModel.getMovies().observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(@Nullable PagedList<Movie> movies) {
                if (movies != null) {

                    //ToDo 12 : Submit the list
                    moviesAdapter.submitList(movies);
                    //moviesAdapter.addItem(movies);

                } else {
                    Toast.makeText(mContext, R.string.error, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //ToDo 13: Congratulation, Now your app load more movies.
    // Enjoy , and wish me a good things.

}
