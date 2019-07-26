package com.example.android.popularmovies.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.adapter.MoviesAdapter;
import com.example.android.popularmovies.databinding.ActivityMainBinding;
import com.example.android.popularmovies.model.Movie;
import com.example.android.popularmovies.model.MoviesResponse;
import com.example.android.popularmovies.rest.ApiClient;
import com.example.android.popularmovies.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static String API_KEY;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ToDo 16: Create instance of data binding
        //setContentView(R.layout.activity_main);
        //final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);

        final ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this , R.layout.activity_main);

        API_KEY = getString(R.string.api_key);
        mContext = this;

        /*
        //move this lines to xml
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        */

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = service.getPopularMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {

                List<Movie> movies = null;
                if (response.body() != null) {
                    movies = response.body().getResults();

                    //ToDo 17: set recycler adapter
                    activityMainBinding.setAdapter(new MoviesAdapter(mContext , movies));
                    //recyclerView.setAdapter(new MoviesAdapter(mContext, movies));
                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesResponse> call, @NonNull Throwable t) {

            }
        });
    }



}
