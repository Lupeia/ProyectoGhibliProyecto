package com.mistdev.proyectoghibli.ConexionConApi

import com.squareup.moshi.JsonClass

@JsonClass (generateAdapter = true)
data class PeliculaApi(
    val id: String,
    val title: String,
    val duracion: Int,
    val director: String
)
