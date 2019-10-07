package com.example.android.popularmovies.adapter;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.activity.MovieDetails;
import com.example.android.popularmovies.databinding.ListItemsBinding;
import com.example.android.popularmovies.model.Movie;

import java.util.List;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {


    private List<Movie> movies;
    private Context context;

    class MovieViewHolder extends RecyclerView.ViewHolder {

        private ListItemsBinding itemsBinding;

        MovieViewHolder(ListItemsBinding listItemsBinding) {
            super(listItemsBinding.getRoot());
            this.itemsBinding = listItemsBinding;
        }
    }

    public MoviesAdapter(List<Movie> movies) {
        this.movies = movies;

    }

    @NonNull
    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ListItemsBinding listItemsBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_items, parent, false);

        return new MovieViewHolder(listItemsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, final int position) {

        holder.itemsBinding.setMovie(movies.get(position));
        holder.itemsBinding.setClickHandler(new ClickHandlers());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class ClickHandlers {
        public void onClickMovie(Movie movie) {
            MovieDetails.start(context, movie);
        }
    }

    public void addItem(List<Movie> movies) {
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }
}

