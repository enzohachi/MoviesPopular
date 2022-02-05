package org.alkemy.movieapp.movie.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/*
Las anotaciones en amarillo son para hacer un mapeo interno de los datos y asi poder
usar los datos de una mejor manera.
Parcelice nos sirve para pasar objetos entre los diversos componentes de la app.
SerializerName nos sirve para poder usar un nombre diferente de los objetos que se obtendran
de la lista de informacion que tendremos cuando haremos la consulta a la api

 */

@Parcelize
data class Movie(
    @SerializedName("poster_path")
    val image: String = "",
    @SerializedName("original_title")
    val title: String = "",
    @SerializedName("overview")
    val detail: String = "",
    @SerializedName("release_date")
    val release_date: String ="",
    @SerializedName("popularity")
    val popularity: String =""
) : Parcelable

//dentro de la consulta a la api, se puede ver que el objeto results es quien tiene la lista de
// datos.
data class MovieList (
    @SerializedName ("results")
    val movieList: List<Movie>
)


