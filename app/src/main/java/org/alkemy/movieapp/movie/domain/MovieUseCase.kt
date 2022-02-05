package org.alkemy.movieapp.movie.domain


import org.alkemy.movieapp.movie.presentation.Resource
import org.alkemy.movieapp.copete.data.network.RemoteMovieRepository

/*esta es la clase de caso de uso o implements , donde estamos implementando el metodo de
MovieRepository para obtener la lista de peliculas al servidor
El simbolo = se puede cmabiar por return {} si se desea
La clase Repository es la encargada de entregar la lista al ViewModel
 */
class MovieUseCase(
    private val remoteMovieRepository: RemoteMovieRepository) : MovieRepository {
    override suspend fun getMovieList(movieName: String): Resource<List<Movie>> = remoteMovieRepository.getMovieList(movieName)
}