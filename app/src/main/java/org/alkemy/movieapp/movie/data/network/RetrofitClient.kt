package org.alkemy.movieapp.movie.data.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/* Sirve para crear una instancia retrofit para trabajar con la base url (lo comun entre
las diferentes listas de apis).
inicializado "lazy" para ejecutarla solo cuando la necesitemos

 */

object RetrofitClient {
    val webService by lazy{
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}