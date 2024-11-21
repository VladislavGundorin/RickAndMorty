package com.example.rickandmorty.repository

import retrofit2.Call
import com.example.rickandmorty.models.RickMorty
import com.example.rickandmorty.network.RetrofitInstance

class RickMortyRepository {
    private val api = RetrofitInstance.api

    fun getCharactersByIds(ids: String): Call<List<RickMorty>> {
        return api.getCharactersByIds(ids)
    }

}
