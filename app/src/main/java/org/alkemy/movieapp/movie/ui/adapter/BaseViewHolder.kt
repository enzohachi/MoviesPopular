package org.alkemy.movieapp.movie.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/*
Esta clase nos va a dar el item y una posicion para poder sobreescribir bind en el clase MainAdapter
 */
abstract class BaseViewHolder <T> (itemView: View): RecyclerView.ViewHolder(itemView){
    abstract fun bind (item: T, position: Int)
}