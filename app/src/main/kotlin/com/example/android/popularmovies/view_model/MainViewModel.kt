package com.example.android.popularmovies.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import com.example.android.popularmovies.model.Movie
import com.example.android.popularmovies.model.MoviesResponse
import com.example.android.popularmovies.rest.ApiClient
import com.example.android.popularmovies.rest.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel(private val apiKey: String) : ViewModel() {

    private val movies = MutableLiveData<List<Movie>>()


    init {
        loadMovies()
    }

    private fun loadMovies() {
        // we can copy it from the MainActivity

        Log.d(TAG, "Load Movies Called")

        val service = ApiClient.client.create(ApiInterface::class.java)

        val call = service.getPopularMovies(apiKey)
        call.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {

                movies.value = response.body()?.results

            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                movies.value = null
            }
        })
    }

    fun getMovies(): LiveData<List<Movie>> {
        return movies
    }

    companion object {

        private val TAG = MainViewModel::class.java.simpleName
    }

}
