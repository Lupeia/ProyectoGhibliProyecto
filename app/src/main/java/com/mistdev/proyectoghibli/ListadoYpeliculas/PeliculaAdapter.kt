package com.mistdev.proyectoghibli.ListadoYpeliculas

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mistdev.proyectoghibli.R
import kotlin.jvm.java

class PeliculaAdapter(var peliculas: MutableList<Pelicula>, var context:Context):
    RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder>(){

        class PeliculaViewHolder(view: View): RecyclerView.ViewHolder(view) {
            lateinit var txtTitulo: TextView
            lateinit var txtDirector: TextView
            lateinit var txtDuracion: TextView

            init {
                txtTitulo = view.findViewById((R.id.tv_titulo))
                txtDirector = view.findViewById((R.id.tv_director))
                txtDuracion = view.findViewById((R.id.tv_duracion))
            }


        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_pelicula,parent,false)
            return PeliculaViewHolder(view)
        }

        override fun getItemCount(): Int = peliculas.size

        override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) { //Permite manipular el contenido de cada item de la lista
            val item = peliculas.get(position)
            holder.txtTitulo.text = item.titulo
            holder.txtDirector.text = item.director
            holder.txtDuracion.text = item.duracion

            holder.itemView.setOnClickListener(){
                val intent = Intent(context, PeliculaActivity::class.java)
                intent.putExtra("TITULO",item.titulo)
                intent.putExtra("DIRECTOR",item.director)
                intent.putExtra("DURACION",item.duracion)

                context.startActivity(intent)
            }
        }


}