package com.example.rickandmorty.network

import com.example.rickandmorty.models.RickMorty
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {
    @GET("character/{ids}")
    suspend fun getCharactersByIds(@Path("ids") ids: String): List<RickMorty>
}
