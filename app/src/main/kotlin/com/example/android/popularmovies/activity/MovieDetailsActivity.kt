package com.example.android.popularmovies.activity

import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.android.popularmovies.R
import com.example.android.popularmovies.databinding.ActivityMovieDetailsBinding
import com.example.android.popularmovies.model.Movie

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val detailsBinding: ActivityMovieDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)

        val intent = intent
        if (intent != null) {
            val movie = intent.getParcelableExtra<Movie>("Movie")
            detailsBinding.movie = movie
        }
    }

    companion object {

        fun start(context: Context, movie: Movie) {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("Movie", movie)
            context.startActivity(intent)
        }
    }
}
