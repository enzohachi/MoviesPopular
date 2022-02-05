package org.alkemy.movieapp.movie.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.alkemy.movieapp.movie.domain.MovieRepository

/*
a la implementacion Viewmodel de MainViewModel le hace falta la implementacion
con esta clase podemos pasar el viewmodel, donde aca se crea el Factory y podemos pasar ese modelo
al MainFragment. Es para poder permitir la "inyeccion de dependecias manual"

Tambien cabe destacar que al igual que los sinteticos de Kotlin este tipo de inyeccion de dependencias
se encuentra sin soporte en las librerias actuales, por lo que reutilicé tanto sinteticos como
esta inyeccion de dependencias solamente porque tuve muchos problemas con el nuevo android studio
y navigation, lo cual de manera desesperada reutilicé esta forma
 */
class VMFactory(
    private val movieRepository: MovieRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(movieRepository)
    }
}