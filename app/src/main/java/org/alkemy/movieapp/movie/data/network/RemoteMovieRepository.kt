package org.alkemy.movieapp.copete.data.network

import org.alkemy.movieapp.movie.data.network.RetrofitClient
import org.alkemy.movieapp.movie.domain.Movie
import org.alkemy.movieapp.movie.domain.MovieRepository
import org.alkemy.movieapp.movie.presentation.Resource


class RemoteMovieRepository : MovieRepository {

/* esta es la clase encargada de tomar toda la lista de la consulta al servidor y retornarla a la clase
Resource.Success, para que este la lleve al Reposotory
Tambien podemos ver que aqui se coloca la Apikey obtenida en la pagina de desarolladores TMdb
 */
    override suspend fun getMovieList(movieName: String): Resource<List<Movie>> {
        return Resource.Success(RetrofitClient.webService.getMovieByName("d348637ab3f8ed54922fea8c36ec45ae").movieList)
    }


}