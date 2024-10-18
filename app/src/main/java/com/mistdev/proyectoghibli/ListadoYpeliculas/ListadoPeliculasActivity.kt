package com.mistdev.proyectoghibli.ListadoYpeliculas

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.mistdev.proyectoghibli.R
import com.mistdev.proyectoghibli.info_activity
import java.util.ArrayList
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

        rvPeliculas = findViewById(R.id.rvPeliculas)

        peliculasAdapter = PeliculaAdapter(getPeliculas(), this)
        rvPeliculas.adapter = peliculasAdapter
    }

    private fun getPeliculas(): MutableList<Pelicula> {
        val peliculas: MutableList<Pelicula> = ArrayList()

        peliculas.add(Pelicula(1, "Howl's Moving Castle", "1h 59m", "Hayao Miyazaki"))
        peliculas.add(Pelicula(2, "Spirited Away", "2h 5m", "Hayao Miyazaki"))
        peliculas.add(Pelicula(3, "My Neighbor Totoro", "1h 26m", "Hayao Miyazaki"))
        peliculas.add(Pelicula(4, "Princess Mononoke", "2h 14m", "Hayao Miyazaki"))
        peliculas.add(Pelicula(5, "Kiki's Delivery Service", "1h 43m", "Hayao Miyazaki"))
        peliculas.add(Pelicula(6, "Grave of the Fireflies", "1h 29m", "Isao Takahata"))
        peliculas.add(Pelicula(7, "The Tale of the Princess Kaguya", "2h 17m", "Isao Takahata"))
        peliculas.add(Pelicula(8, "Pom Poko", "1h 59m", "Isao Takahata"))
        peliculas.add(Pelicula(9, "Only Yesterday", "1h 58m", "Isao Takahata"))
        peliculas.add(Pelicula(10, "The Wind Rises", "2h 6m", "Hayao Miyazaki"))
        peliculas.add(Pelicula(11, "Porco Rosso", "1h 34m", "Hayao Miyazaki"))
        peliculas.add(Pelicula(12, "Ponyo", "1h 41m", "Hayao Miyazaki"))
        peliculas.add(Pelicula(13, "When Marnie Was There", "1h 43m", "Hiromasa Yonebayashi"))
        peliculas.add(
            Pelicula(
                14,
                "The Secret World of Arrietty",
                "1h 34m",
                "Hiromasa Yonebayashi"
            )
        )
        peliculas.add(Pelicula(15, "The Cat Returns", "1h 15m", "Hiroyuki Morita"))
        peliculas.add(Pelicula(16, "Whisper of the Heart", "1h 51m", "Yoshifumi Kondō"))
        peliculas.add(Pelicula(17, "Castle in the Sky", "2h 4m", "Hayao Miyazaki"))
        peliculas.add(Pelicula(18, "Tales from Earthsea", "1h 55m", "Gorō Miyazaki"))
        peliculas.add(Pelicula(19, "From Up on Poppy Hill", "1h 31m", "Gorō Miyazaki"))
        peliculas.add(Pelicula(20, "Ocean Waves", "1h 12m", "Tomomi Mochizuki"))

        return peliculas
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