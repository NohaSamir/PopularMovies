package com.example.android.popularmovies.activity

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.android.popularmovies.R
import com.example.android.popularmovies.adapter.MoviesAdapter
import com.example.android.popularmovies.databinding.ActivityMainBinding
import com.example.android.popularmovies.model.Movie
import com.example.android.popularmovies.view_model.MainViewModel
import com.example.android.popularmovies.view_model.MainViewModelFactory
import java.util.*


class MainActivity : AppCompatActivity() {
    private var mContext: Context? = null

    private var mMovies = ArrayList<Movie>()
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        API_KEY = getString(R.string.api_key)
        mContext = this
        moviesAdapter = MoviesAdapter(mMovies)

        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.adapter = moviesAdapter

        val mainViewModel = ViewModelProviders.of(this, MainViewModelFactory(API_KEY!!)).get(MainViewModel::class.java)

        mainViewModel.getMovies().observe(this, Observer { movies ->
            if (movies != null) {
                moviesAdapter.addItem(movies)
            } else {
                Toast.makeText(mContext, R.string.error, Toast.LENGTH_LONG).show()
            }
        })
    }

    companion object {

        private var API_KEY: String? = null
    }
}
