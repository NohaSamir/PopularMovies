package com.example.android.popularmovies.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.databinding.ActivityMovieDetailsBinding;
import com.example.android.popularmovies.model.Movie;

public class MovieDetails extends AppCompatActivity {
    private Context context;
    private static String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private ImageView moviePoster;
    private ImageView movieImage;
    private TextView movieTitle;
    private TextView movieOverview;
    private TextView movieReleaseDate;
    private RatingBar movieRating;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ToDo 6: remove all old code
       /* setContentView(R.layout.activity_movie_details);
        moviePoster = findViewById(R.id.moviePoster);
        movieImage = findViewById(R.id.movieImage);
        movieTitle = findViewById(R.id.moviewTitle);
        movieRating = findViewById(R.id.Rate);
        movieOverview = findViewById(R.id.overView);
        movieReleaseDate = findViewById(R.id.ReleaseDate);

        Intent intent = getIntent();
        if (intent != null) {
            movie = intent.getParcelableExtra("Movie");

            movieTitle.setText(movie.getTitle());
            getSupportActionBar().setTitle(movie.getTitle());

            movieOverview.setText(movie.getOverview());
            movieRating.setRating((float) (movie.getVoteAverage() / 2));
            movieReleaseDate.setText(movie.getReleaseDate());

            String image_URL = IMAGE_BASE_URL + movie.getPosterPath();
            Log.d("Image", image_URL);
            Glide.with(this).load(image_URL).into(moviePoster);
            Glide.with(this).load(image_URL).into(movieImage);
        }*/

        //ToDo 7: create instance from data binding using the name of xml
        //activity_movie_details => Activity Movie Details Binding
        ActivityMovieDetailsBinding detailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);

        Intent intent = getIntent();
        if (intent != null) {
            movie = intent.getParcelableExtra("Movie");

            //ToDo 8 : pass the model to the defined variable inside  xml
            detailsBinding.setMovie(movie);
        }
    }


    public static void start(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetails.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("Movie", movie);
        context.startActivity(intent);
    }
}
