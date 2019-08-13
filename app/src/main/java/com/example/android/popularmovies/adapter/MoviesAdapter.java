package com.example.android.popularmovies.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.activity.MovieDetails;
import com.example.android.popularmovies.databinding.ListItemsBinding;
import com.example.android.popularmovies.model.Movie;

import java.util.List;

//ToDo 6 : Edit our adapter to work with paged list by extends PagedListAdapter
public class MoviesAdapter extends PagedListAdapter<Movie, MoviesAdapter.MovieViewHolder> {


    //private List<Movie> movies;
    private Context context;

    class MovieViewHolder extends RecyclerView.ViewHolder {

        private ListItemsBinding itemsBinding;

        MovieViewHolder(ListItemsBinding listItemsBinding) {
            super(listItemsBinding.getRoot());
            this.itemsBinding = listItemsBinding;
        }
    }

    //ToDo 7 : define DiffUtil.ItemCallback<Movie>
    private static DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie movie, @NonNull Movie t1) {
            return movie.getId().equals(t1.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie movie, @NonNull Movie t1) {
            return movie.getId().equals(t1.getId());
        }
    };

    //ToDo 8: pass DIFF_CALLBACK to the constructor
    public MoviesAdapter() {
        super(DIFF_CALLBACK);
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

        //ToDo 9: Use get item instead of using list
        Movie movie = getItem(position);

        if (movie != null) {
            holder.itemsBinding.setMovie(movie);
            holder.itemsBinding.setClickHandler(new ClickHandlers());
        }
    }

    //ToDo 10: Remove get item count
    /*
    @Override
    public int getItemCount() {
        return movies.size();
    }*/


    public class ClickHandlers {
        public void onClickMovie(Movie movie) {
            MovieDetails.start(context, movie);
        }
    }

    //ToDo 11 : remove add item because we will use submitList
   /* public void addItem(List<Movie> movies) {
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }*/
}

