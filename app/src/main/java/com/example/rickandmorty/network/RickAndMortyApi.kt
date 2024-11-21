package com.example.rickandmorty.network

import com.example.rickandmorty.models.RickMorty
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {
    @GET("character/{ids}")
    fun getCharactersByIds(@Path("ids") ids: String): Call<List<RickMorty>>

}
