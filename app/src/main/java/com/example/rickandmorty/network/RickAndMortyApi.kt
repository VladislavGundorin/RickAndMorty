package com.example.rickandmorty.network

import com.example.rickandmorty.models.RickMorty
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface RickAndMortyApi {
    @GET("character/{id}")
    fun getCharacter(@Path("id") characterId: Int): Call<RickMorty>
}
