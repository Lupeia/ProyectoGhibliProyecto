package com.mistdev.proyectoghibli.UsuarioYregistro

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {

    @Query("select * from usuarios WHERE nombreusuario = :nombreUsuario AND contraseña = :contrasenia LIMIT 1")

    fun getUsuario(nombreUsuario:String, contrasenia:String): Usuario?

    @Insert
    fun insertUsuario(usuario: Usuario): Long

}