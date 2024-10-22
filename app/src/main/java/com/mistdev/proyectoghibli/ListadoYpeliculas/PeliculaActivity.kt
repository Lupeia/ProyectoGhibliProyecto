package com.mistdev.proyectoghibli.ListadoYpeliculas

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mistdev.proyectoghibli.R

class PeliculaActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var titulo: TextView
    lateinit var director: TextView
    lateinit var duracion: TextView

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

        titulo = findViewById(R.id.tvTituloPelicula)
        director = findViewById(R.id.tvDirectorPelicula)
        duracion = findViewById(R.id.tvDuracionPelicula)

        titulo.text = intent.getStringExtra("TITULO")
        director.text = intent.getStringExtra("DIRECTOR")
        duracion.text = intent.getStringExtra("DURACION")

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

}