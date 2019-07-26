package com.example.android.popularmovies.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

        //ToDo 10: edit view holder to take ItemBinding instead of View

        /*RelativeLayout moviesLayout;
        TextView movieTitle;
        ImageView movieImage;

        MovieViewHolder(View v) {
            super(v);
            moviesLayout = (RelativeLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            movieImage = (ImageView) v.findViewById(R.id.item_movie_poster);
        }*/


        private ListItemsBinding itemsBinding;

        MovieViewHolder(ListItemsBinding listItemsBinding) {
            super(listItemsBinding.getRoot());
            this.itemsBinding = listItemsBinding;
        }
    }

    public MoviesAdapter(Context context, List<Movie> movies) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //ToDo 11 : use DataBindingUtil.inflate

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemsBinding listItemsBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_items, parent, false);

        //Delete this line
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new MovieViewHolder(listItemsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, final int position) {


        //ToDo 12: bind item data
        holder.itemsBinding.setMovie(movies.get(position));
        /*
         String image_url = IMAGE_URL_BASE + movies.get(position).getPosterPath();
        Glide.with(context).load(image_url).into(holder.movieImage);
        holder.movieTitle.setText(movies.get(position).getTitle());
        */

        //ToDo 14 : set my handler
        holder.itemsBinding.setClickHandler(new ClickHandlers());

      /*  holder.moviesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetails.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Movie", movies.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    //ToDo 13:  Define my click handler
    public class ClickHandlers {
        public void onClickMovie(Movie movie) {
            MovieDetails.start(context, movie);
        }
    }
}

