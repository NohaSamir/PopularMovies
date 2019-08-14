package com.example.android.popularmovies.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.activity.MovieDetails;
import com.example.android.popularmovies.databinding.ListItemsBinding;
import com.example.android.popularmovies.model.Movie;

//ToDo 10 : Now if you run the app , you will see that the list repeat itself twice.
// because we read from cache first then update the list from the network, this notify the list twice
// and if we edit addItem to setItems this.movies = movies you will see that the list flashing because we use notifyDataSetChanged
// public void setItems(List<Movie> movies) {
//        this.movies = movies;
//        notifyDataSetChanged();
//    }
// try it now and run

//ToDo 11: To resolve this issue we need to extend ListAdapter
// -
// ListAdapter is a RecyclerView.Adapter base class for presenting List data in a RecyclerView,
// including computing diffs between Lists on a background thread
// It's a base Adapter that automatically updates itself (with free animations!) when the list changes.
// Calling notifyDataSetChanged() and its friends is now a thing from the past —
// all you need now is to call submitList() passing the updated list.


public class MoviesAdapter extends ListAdapter<Movie, MoviesAdapter.MovieViewHolder> {

    //private List<Movie> movies;
    private Context context;

    class MovieViewHolder extends RecyclerView.ViewHolder {

        private ListItemsBinding itemsBinding;

        MovieViewHolder(ListItemsBinding listItemsBinding) {
            super(listItemsBinding.getRoot());
            this.itemsBinding = listItemsBinding;
        }
    }

    //ToDo 12 : define DiffUtil.ItemCallback<Movie>
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

    //ToDo 13: pass DIFF_CALLBACK to the constructor
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

        //ToDo 14: Use get item instead of using list
        Movie movie = getItem(position);

        if (movie != null) {
            holder.itemsBinding.setMovie(movie);
            holder.itemsBinding.setClickHandler(new ClickHandlers());
        }
    }

    //ToDo 15: Remove get item count
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

    //ToDo 16 : remove add item because we will use submitList
   /* public void addItem(List<Movie> movies) {
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }*/
}



