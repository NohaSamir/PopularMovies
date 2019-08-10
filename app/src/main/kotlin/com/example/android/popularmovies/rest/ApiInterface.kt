package com.example.android.popularmovies.rest

import com.example.android.popularmovies.model.MoviesResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Call<MoviesResponse>
}
