package com.mistdev.proyectoghibli.InfoFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.mistdev.proyectoghibli.R

class PrimerFragmento : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_primer_fragmento,container,false)

        val buttonFrag2 = view.findViewById<Button>(R.id.btnFundador1)
        val buttonFrag3 = view.findViewById<Button>(R.id.btnFundador2)

        buttonFrag2.setOnClickListener{

            cargarFragmento(SegundoFragmento())

        }

        buttonFrag3.setOnClickListener{

            cargarFragmento(TercerFragmento())

        }

        return view

    }


    private fun cargarFragmento(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            ?.replace(R.id.ContenedorFragDosyTres,fragment)
            ?.addToBackStack(null)?.commit()

    }


}