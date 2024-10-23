package com.mistdev.proyectoghibli.ListadoYpeliculas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.mistdev.proyectoghibli.ConexionConApi.Endpoints
import com.mistdev.proyectoghibli.ConexionConApi.PeliculaApi
import com.mistdev.proyectoghibli.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class PeliculaActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    private lateinit var apiService: Endpoints

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pelicula)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.toolbarTitulo)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://ghibliapi.vercel.app/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        apiService = retrofit.create(Endpoints::class.java)

        val peliculaId = intent.getStringExtra("peliculaId") ?: return

        obtenerDetallesPelicula(peliculaId)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.volver_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId== R.id.item_volver){
            val intent = Intent(this, ListadoPeliculasActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun obtenerDetallesPelicula(id: String) {
        apiService.getFilm(id).enqueue(object : Callback<PeliculaApi> {
            override fun onResponse(call: Call<PeliculaApi>, response: Response<PeliculaApi>) {
                if (response.isSuccessful) {
                    val pelicula = response.body()
                    if (pelicula != null) {
                        findViewById<TextView>(R.id.tvTituloPelicula).text = pelicula.title
                        findViewById<TextView>(R.id.tvDescripcionPelicula).text = pelicula.descripcion
                        findViewById<TextView>(R.id.tvDirectorPelicula).text = pelicula.director
                        findViewById<TextView>(R.id.tvDuracionPelicula).text = "${pelicula.duracion} min"

                        Glide.with(this@PeliculaActivity)
                            .load(pelicula.imageUrl)
                            .into(findViewById<ImageView>(R.id.imageViewPelicula))

                    }
                } else {
                    Log.e("PeliculaActivity", "Error en la respuesta: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<PeliculaApi>, t: Throwable) {
                Log.e("PeliculaActivity", "Error en la llamada a la API", t)
            }
        })
    }

}

