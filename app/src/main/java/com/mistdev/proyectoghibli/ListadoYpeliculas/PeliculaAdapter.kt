package com.mistdev.proyectoghibli.ListadoYpeliculas

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mistdev.proyectoghibli.ConexionConApi.PeliculaApi
import com.mistdev.proyectoghibli.R
import kotlin.jvm.java

class PeliculaAdapter(val peliculas: List<PeliculaApi>, var context:Context):
    RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder>(){

        class PeliculaViewHolder(view: View): RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_pelicula,parent,false)
            return PeliculaViewHolder(view)
        }

        override fun getItemCount() = peliculas.size

        override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) { //Permite manipular el contenido de cada item de la lista
            val pelicula = peliculas[position]
            holder.itemView.findViewById<TextView>(R.id.tv_titulo).text =pelicula.title
            holder.itemView.findViewById<TextView>(R.id.tv_director).text = "Director: ${pelicula.director}"
            holder.itemView.findViewById<TextView>(R.id.tv_duracion).text = "Duración: ${pelicula.duracion} min"

            holder.itemView.setOnClickListener {

                val intent = Intent(context, PeliculaActivity::class.java).apply {
                    putExtra("peliculaId", pelicula.id)
                }
                context.startActivity(intent)

            }



        }


}


