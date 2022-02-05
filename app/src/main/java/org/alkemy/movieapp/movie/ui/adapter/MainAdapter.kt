package org.alkemy.movieapp.movie.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.alkemy.movieapp.movie.domain.Movie
import org.alkemy.movieapp.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movies.view.ivImagenItem
import kotlinx.android.synthetic.main.item_movies.view.tvTituloItem

/*
Aca extendemos de Recycleview con un adaptador y este utlimo pide un ViewHolder, como el
BaseViewHolder no sabemos que informacion se le va a pasar, se coloca "*"
 */
class MainAdapter(private val context: Context,
                  private val moviesList:List<Movie>,
                  private val itemClickListener: OnMoviesClickListener
) :RecyclerView.Adapter<BaseViewHolder<*>>(){

    interface OnMoviesClickListener{
        fun onMovieClick(drink: Movie)
    }
    //implementar metodos

    /*el recyclerview necesita ver que vista se debe inflar o armar para colocar los datos
    como no tenemos una clase especifica de ViewHolder, abajo se hace una inner class con el nombre
    MainViewHolder y utilizamos la BaseViewHolder anteriormente hecha para inflar ese recyclerView
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.item_movies,parent,false))
    }

    /* obtiene el tamaño de la lista de peliculas, para que el reciclerView pueda saber cuantos
    elementos debe colocar en la vista
     */
    override fun getItemCount(): Int {
        return moviesList.size
    }

    /*
    Aqui decimos al mainViewHolder cual es el holder que se necesita bindear
     */
    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder){
            is MainViewHolder -> holder.bind(moviesList[position],position)
        }
    }

    /*
    esta es la clase ViewHolder hecha para el MainFragment
    tambien esto esta realizado como inner class cuando la clase MainAdapter se termina,
    asi no se mal ocupa mal espacio en memoria
     */
    inner class MainViewHolder (itemView: View): BaseViewHolder<Movie>(itemView){
        override fun bind(item: Movie, position: Int) {
            //con esto se carga el link de la foto obtenida del json
            Glide.with(context).load("https://image.tmdb.org/t/p/w500/${item.image}").centerCrop().into(itemView.ivImagenItem)
            //con esto se carga la info del titulo obtenida en el json
            itemView.tvTituloItem.text = item.title
            //con esto obtenemos el dato del click que dimos al probar la app
            itemView.setOnClickListener{ itemClickListener.onMovieClick(item)}
        }
    }
}