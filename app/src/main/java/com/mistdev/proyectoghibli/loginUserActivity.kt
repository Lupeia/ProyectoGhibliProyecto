package com.mistdev.proyectoghibli

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NotificationCompat

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mistdev.proyectoghibli.UsuarioYregistro.AppDatabase
import com.mistdev.proyectoghibli.UsuarioYregistro.Usuario


class loginUserActivity : AppCompatActivity() {

    lateinit var btnRegistrar: Button
    lateinit var btnIniciarSesion: Button
    lateinit var nombreUsuario: EditText
    lateinit var contraseña: EditText
    lateinit var checkbox: CheckBox
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_user)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.toolbarTitulo)

        nombreUsuario = findViewById(R.id.etNombreUsuario)
        contraseña = findViewById(R.id.etContraseña)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)
        checkbox = findViewById(R.id.cbRecordarUsuario)


        btnRegistrar.setOnClickListener {

            val usuario = nombreUsuario.text.toString()
            val pass = contraseña.text.toString()

            if (usuario.isEmpty() || pass.isEmpty()) {

                Toast.makeText(this, "-Completar Datos", Toast.LENGTH_SHORT).show()

            } else {

                if(checkbox.isChecked){

                    mostrarNotificacion()

                }


                val bbd = AppDatabase.getDatabase(this) // Obtener la base de datos
                val nuevoUsuario = Usuario(usuario, pass)
                bbd.usuarioDao().insertUsuario(nuevoUsuario)


                Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, InicioActivity::class.java)
                startActivity(intent)


            }


        }

        btnIniciarSesion.setOnClickListener {

            val preferencias =
                getSharedPreferences(resources.getString(R.string.sp_credenciales), MODE_PRIVATE)
            val usuarioGuardado =
                preferencias.getString(resources.getString(R.string.nombre_usuario), "")
            val paswordGuardado =
                preferencias.getString(resources.getString(R.string.usuario_password), "")

            val usuario = nombreUsuario.text.toString()
            val pass = contraseña.text.toString()

            if (usuario.isEmpty() || pass.isEmpty()) {

                Toast.makeText(this, "-Completar los campos", Toast.LENGTH_SHORT).show()

            }else{
                val bbd = AppDatabase.getDatabase(this)
                val usuarioEncontrado= bbd.usuarioDao().getUsuario(usuario,pass)

                if(usuarioEncontrado != null){

                   if(checkbox.isChecked) {

                       val preferencias = getSharedPreferences(resources.getString(R.string.sp_credenciales), MODE_PRIVATE)
                       preferencias.edit().putString(resources.getString(R.string.nombre_usuario), usuario).apply()
                       preferencias.edit().putString(resources.getString(R.string.usuario_password), pass).apply()

                   }

                    Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
                    startMainActivity(usuario)

                }else{

                    Toast.makeText(this, "- Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()


                }


            }


        }

    }

    private fun startMainActivity(usuario: kotlin.String) {

        val intent = Intent(this, InicioActivity::class.java)
        intent.putExtra(resources.getString(R.string.nombre_usuario), usuario)
        startActivity(intent)

    }

    private fun mostrarNotificacion(){

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        val dismissIntent = Intent(this, CancelNotificationReceiver::class.java).apply {
            action = "CANCEL_NOTIFICATION"
        }
        val dismissIntentPending = PendingIntent.getBroadcast(this, 0, dismissIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(this, "canal_usuario")
            .setSmallIcon(R.drawable.baseline_notification_important_24)
            .setContentTitle("Usuario Recordado")
            .setContentText("El usuario se recordara correctamente")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(R.drawable.baseline_check_24, "LISTO", dismissIntentPending)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(1, notification)


    }





}