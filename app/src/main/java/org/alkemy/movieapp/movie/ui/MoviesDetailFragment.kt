package org.alkemy.movieapp.movie.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import org.alkemy.movieapp.movie.domain.Movie
import org.alkemy.movieapp.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MoviesDetailFragment : Fragment(R.layout.fragment_movie_detail) {
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //con esto obtenemos la informacion del elemento seleccionado en el fragment main
        requireArguments().let {
            movie = it.getParcelable("movieSelect")!!
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /* aqui se cargan todos los datos del elemento seleccionado,
        en este caso se estan utilizando los sintenticos de kotlin los cuales no deberian
        usarse por que nunca han sido la mejor alternativa y en este momento se encuentran
        obsoletos, lo ideal es hacer la vinculacion de vistas con viewBinding, pero apesar
        de llevar varios dias en un proyecto utilizando todas las nuevas herramientas, no
        pude hacer funcionar el Adapter con binding, he leido algunos problemas en la version
        2.4.0 de navigations components, o simplemente yo no pude hacerlo correr
         */
        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500/${movie.image}")
            .centerCrop().into(ivImagenItem)
        tvTituloItem.text = movie.title
        tvDescripcionDetalle.text = movie.detail
        tvPopular.text=movie.popularity
        tvDate.text = movie.release_date

    }
}