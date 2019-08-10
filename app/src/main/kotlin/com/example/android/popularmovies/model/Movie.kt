package com.example.android.popularmovies.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


class Movie() : Parcelable {

    private val imageBaseUrl = "https://image.tmdb.org/t/p/w500"

    @SerializedName("poster_path")
    var posterPath: String? = null
        get() {
            return when {
                field == null -> return ""
                !field?.contains(imageBaseUrl)!! -> imageBaseUrl + field
                else -> field
            }
        }

    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("release_date")
    var releaseDate: String? = null

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("vote_count")
    var voteCount: Int? = null

    @SerializedName("vote_average")
    var voteAverage: Float? = null

    @SerializedName("backdrop_path")
    var backdropPath: String? = null
        get() {
            return when {
                field == null -> return ""
                !field?.contains(imageBaseUrl)!! -> imageBaseUrl + field
                else -> field
            }
        }

    constructor(parcel: Parcel) : this() {
        posterPath = parcel.readString()
        overview = parcel.readString()
        releaseDate = parcel.readString()
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        title = parcel.readString()
        voteCount = parcel.readValue(Int::class.java.classLoader) as? Int
        voteAverage = parcel.readValue(Float::class.java.classLoader) as? Float
        backdropPath = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(posterPath)
        parcel.writeString(overview)
        parcel.writeString(releaseDate)
        parcel.writeValue(id)
        parcel.writeString(title)
        parcel.writeValue(voteCount)
        parcel.writeValue(voteAverage)
        parcel.writeString(backdropPath)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }


}