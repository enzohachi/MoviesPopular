package org.alkemy.movieapp.movie.ui.viewmodel

import androidx.lifecycle.*
import org.alkemy.movieapp.movie.domain.MovieRepository
import kotlinx.coroutines.Dispatchers
import org.alkemy.movieapp.movie.presentation.Resource

class MainViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private val movieData = MutableLiveData<String>()

    /*con esta funcion podemos obtener
    el listado
     */

    private fun setMovies(movieName: String) {
        movieData.value = movieName
    }
    init {
        setMovies("")
    }
    /*
    Esta variable retorna un Livedata
    Los despatchers sirve para hacer ejecuciones en segundo plano
     */
    val fetchMoviesList = movieData.switchMap { nameMovie ->
        liveData(Dispatchers.IO) {
            /* en este caso se utiliza el emit en Loading, principalmente
            para que durante el tiempo de carga poder mostrar al usuario una
            progressbar mientras se cargan los datos, de esta forma el usuario no piense
            que la app no funciona
            dentro de emit se emiten los datos que se van a mostrar en pantalla
             */
            emit(Resource.Loading())
            try {
                emit(movieRepository.getMovieList(nameMovie))
            /*
            dentro de catch mostramos una exception de error si es que no se pueden
            acceder a los datos del servidor
             */
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }
}