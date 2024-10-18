package com.mistdev.proyectoghibli.UsuarioYregistro

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuario(
     @ColumnInfo (name="nombreusuario") val nombreUsuario: String,
     @ColumnInfo (name="contraseña")  val contrasenia: String
)
{
     @PrimaryKey(autoGenerate = true) var id: Int = 0
}
