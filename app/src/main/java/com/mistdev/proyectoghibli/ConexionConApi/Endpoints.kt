package com.mistdev.proyectoghibli.ConexionConApi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoints {

    @GET("films")
    fun getFilms(): Call<List<PeliculaApi>>

    @GET("/films/{id}")
    fun getFilm(@Path("id") id: String): Call<PeliculaApi>

}