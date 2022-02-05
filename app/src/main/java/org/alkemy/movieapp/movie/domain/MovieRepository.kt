package org.alkemy.movieapp.movie.domain

import org.alkemy.movieapp.movie.presentation.Resource

/* tiene el metodo para obtener una lista de peliculas, donde estaremos ocupando la clase sellada
Resource que tiene los 3 estados (cargando, ok, error) y que debe tener contenido la informacion
que se pidió a la api
 */
interface MovieRepository {
    suspend fun getMovieList(movieName: String) : Resource<List<Movie>>
}

