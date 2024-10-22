package com.mistdev.proyectoghibli.ListadoYpeliculas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mistdev.proyectoghibli.ConexionConApi.PeliculaApi
import com.mistdev.proyectoghibli.ConexionConApi.RetrofitFilms
import com.mistdev.proyectoghibli.R
import com.mistdev.proyectoghibli.info_activity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.jvm.java

class ListadoPeliculasActivity : AppCompatActivity() {
    lateinit var rvPeliculas: RecyclerView
    lateinit var peliculasAdapter: PeliculaAdapter
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listado_peliculas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.toolbarTitulo)

        rvPeliculas =findViewById(R.id.rvPeliculas)
        rvPeliculas.layoutManager = LinearLayoutManager(this)


        val api = RetrofitFilms.api
       api.getFilms().enqueue(object : Callback<List<PeliculaApi>> {

           override fun onResponse(call: Call<List<PeliculaApi>>, response: Response<List<PeliculaApi>>) {

               val peliculas = response.body()?: emptyList()
               if (peliculas.isNotEmpty()) {
                   peliculasAdapter = PeliculaAdapter(peliculas, this@ListadoPeliculasActivity)
                   rvPeliculas.adapter = peliculasAdapter
               } else {
                   Log.d("Peliculas", "No hay películas para mostrar.")
               }
           }

           override fun onFailure(call: Call<List<PeliculaApi>>, t: Throwable) {
               Log.e("ERROR", "Error en la llamada a la API: ${t.message}")
           }

       })

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.impro_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId== R.id.item_info){
            val intent = Intent(this, info_activity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }


}