package org.alkemy.movieapp.movie.presentation

import java.lang.Exception

/*Esta clase sellada Resource nos sirve como un contenerdor de informacion,
ademas que nos retorna 3 estados, dependiendo
de como descargue la informacion con la llamada.
En el generico T podria ser cualquier tipo de dato ya sea lista, string, o lo
que sea que queramos obtener.
Loading solo sirve para avisarnos que esta cargando
Success es un data class que en este caso le dandaremos la informacion de la lista
de peliculas
Failure, nos dice que hay un error y en ese caso le pasamos una excepcion

 */
sealed class Resource <out T> {
    class Loading <out T> : Resource<T>()
    data class Success <out T> (val data: T) : Resource<T>()
    data class Failure <out T> (val exception: Exception) : Resource<T>()
}