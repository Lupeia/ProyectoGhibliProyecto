package com.mistdev.proyectoghibli.UsuarioYregistro

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.jvm.java

@Database(entities = [Usuario::class], version = 1)
    abstract class AppDatabase: RoomDatabase() {

        abstract fun usuarioDao(): UsuarioDao

        companion object {
            @Volatile
            private var INSTANCIA: AppDatabase? = null

            fun getDatabase(contexto: Context): AppDatabase {

                synchronized(this) {

                    val instance = Room.databaseBuilder(
                        contexto.applicationContext,
                        AppDatabase::class.java,"usuario_database"
                    ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                    INSTANCIA = instance
                    return instance

                }

            }

        }

    }




