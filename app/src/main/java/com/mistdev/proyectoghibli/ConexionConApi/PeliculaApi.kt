package com.mistdev.proyectoghibli.ConexionConApi

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass (generateAdapter = true)
data class PeliculaApi(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "director") val director: String,
    @Json(name = "running_time") val duracion: Int,
    @Json(name = "description") val descripcion: String,
    @Json(name = "image") val imageUrl: String
)
