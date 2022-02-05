package org.alkemy.movieapp.movie.data.network

import org.alkemy.movieapp.movie.domain.MovieList
import retrofit2.http.GET
import retrofit2.http.Query

/*webservice es una interfaz que con un anotador GET puedo obtener la informacion desde un servidor
En este ejemplo se pidió solamente se pidio obtener el listado de las peliculas populares, pero
se podria utilizar la interfaz para hacer mas metodos para disferentes consultas o requemientos.

suspend fun para trabajar con corrutinas. El anotador @Query es para darle la respuesta a la api,
en este caso la key que se obtuvo en TMbd

 */
interface WebService {
    @GET("popular")
    suspend fun getMovieByName (@Query(value = "api_key") apiKey : String): MovieList
}