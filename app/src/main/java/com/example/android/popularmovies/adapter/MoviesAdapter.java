package com.example.android.popularmovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.popularmovies.R;
import com.example.android.popularmovies.activity.MovieDetails;
import com.example.android.popularmovies.model.Movie;

import java.util.List;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> movies;
    private int rowLayout;
    private Context context;
    public static final String IMAGE_URL_BASE = "https://image.tmdb.org/t/p/w185";

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout moviesLayout;
        TextView movieTitle;
        ImageView movieImage;

        public MovieViewHolder(View v) {
            super(v);
            moviesLayout = (RelativeLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            movieImage = (ImageView) v.findViewById(R.id.item_movie_poster);
        }
    }

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {

        String image_url = IMAGE_URL_BASE + movies.get(position).getPosterPath();

        Glide.with(context).load(image_url).into(holder.movieImage);

        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.moviesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetails.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("ID", movies.get(position).getId());
                intent.putExtra("OverView", movies.get(position).getOverview());
                intent.putExtra("ReleaseDate", movies.get(position).getReleaseDate());
                intent.putExtra("Title", movies.get(position).getTitle());
                intent.putExtra("PosterPath", movies.get(position).getPosterPath());
                intent.putExtra("VoteAverage", movies.get(position).getVoteAverage());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}

