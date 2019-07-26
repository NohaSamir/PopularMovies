package com.example.android.popularmovies.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.popularmovies.R;

public class MovieDetails extends AppCompatActivity {
    private Context context;
    private static String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private ImageView moviePoster;
    private ImageView movieImage;
    private TextView movieTitle;
    private TextView movieOverview;
    private TextView movieReleaseDate;
    private RatingBar movieRating;
    private int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        String image_URL = IMAGE_BASE_URL + getIntent().getStringExtra("PosterPath");
        moviePoster = findViewById(R.id.moviePoster);
        movieImage = findViewById(R.id.movieImage);
        movieTitle = findViewById(R.id.moviewTitle);
        movieRating = findViewById(R.id.Rate);
        movieOverview = findViewById(R.id.overView);
        movieReleaseDate = findViewById(R.id.ReleaseDate);

        Log.d("Image_URL", image_URL);

        Glide.with(this).load(image_URL).into(moviePoster);
        Glide.with(this).load(image_URL).into(movieImage);


        String filmTitle = getIntent().getStringExtra("Title");
        movieTitle.setText(filmTitle);
        getSupportActionBar().setTitle(filmTitle);
        String filmOverView = getIntent().getStringExtra("OverView");
        movieOverview.setText(filmOverView);
        Double d = getIntent().getDoubleExtra("VoteAverage", 0.0);
        float filmRating = d.floatValue();
        movieRating.setRating(filmRating / 2);
        String filmRelease = getIntent().getStringExtra("ReleaseDate");
        movieReleaseDate.setText(filmRelease);
    }
}
