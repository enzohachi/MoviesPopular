package org.alkemy.movieapp.movie.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.alkemy.movieapp.R
import org.alkemy.movieapp.movie.domain.Movie
import org.alkemy.movieapp.movie.domain.MovieUseCase
import org.alkemy.movieapp.movie.ui.viewmodel.MainViewModel
import org.alkemy.movieapp.movie.ui.viewmodel.VMFactory
import org.alkemy.movieapp.movie.presentation.Resource
import org.alkemy.movieapp.movie.ui.adapter.MainAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import org.alkemy.movieapp.copete.data.network.RemoteMovieRepository


class MainFragment : Fragment(R.layout.fragment_main), MainAdapter.OnMoviesClickListener {

    /*
    es para instanciar el viewModel
    esta variable es una inyeccion de dependencias manual, aunque lo ideal es
    la utilización de un framework para este uso.
     */
    private val viewModel by activityViewModels<MainViewModel> {
        VMFactory(
            MovieUseCase(
                RemoteMovieRepository()
            )
        )
    }

    /*se crea la vista en el fragmento, arriba se elimino onCreate ya que
    clase fragment implementa un oncreate cuando se le pasan los datos de R.layout
    */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    /*
    aqui es donde se buscan los datos del ViewModel y se colocan en el Adapter
    El Log es para pbservar en el logcat para ver el en que estado se encuentra
    durante el proceso.
     */
    private fun setupObservers() {
        viewModel.fetchMoviesList.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d("Loading MOVIES....", "LOADING TMdb data")
                    progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    Log.d("OK MOVIE DOWNLOAD", "datamovies... {$result.data}")
                    progressBar.visibility = View.GONE
                    rvCopete.adapter = MainAdapter(requireContext(), result.data, this)
                }
                is Resource.Failure -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "No se descargaron los datos ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    /*con bundleobtiene el elemento de la lista cuando se hace click sobre el
        elemento seleccionado y con findNavController se navega a la opcion seleccionada
         */

    override fun onMovieClick(movie: Movie) {
        val bundle = Bundle()
        bundle.putParcelable("movieSelect", movie)
        findNavController().navigate(R.id.action_mainFragment_to_moviesDetailFragment, bundle)
    }

    /*esto es para inflar el recyclerView, donde tenemos un layoutmanager para poder mostrar los items
    en este caso de orientacion vertical
     */

    private fun setupRecyclerView() {
        rvCopete.layoutManager = LinearLayoutManager(requireContext())
        rvCopete.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }
}