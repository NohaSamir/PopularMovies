package com.example.android.popularmovies.adapter

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.example.android.popularmovies.R
import com.example.android.popularmovies.activity.MovieDetailsActivity
import com.example.android.popularmovies.databinding.ListItemsBinding
import com.example.android.popularmovies.model.Movie


class MoviesAdapter(private val movies: MutableList<Movie>) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    private var context: Context? = null

    inner class MovieViewHolder(val itemsBinding: ListItemsBinding) : RecyclerView.ViewHolder((itemsBinding as ViewDataBinding).root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val listItemsBinding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, R.layout.list_items, parent, false) as ListItemsBinding

        return MovieViewHolder(listItemsBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.itemsBinding.movie = movies[position]
        holder.itemsBinding.clickHandler = ClickHandlers()
    }

    override fun getItemCount(): Int {
        return movies.size
    }


    inner class ClickHandlers {
        fun onClickMovie(movie: Movie) {
            context?.let { MovieDetailsActivity.start(it, movie) }
        }
    }

    fun addItem(movies: List<Movie>) {
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }
}

