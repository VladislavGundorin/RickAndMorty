package com.example.rickandmorty.repository

import com.example.rickandmorty.models.RickMorty
import com.example.rickandmorty.network.RetrofitInstance

class RickMortyRepository {
    private val api = RetrofitInstance.api

    suspend fun getCharactersByIds(ids: String): List<RickMorty> {
        return api.getCharactersByIds(ids)
    }
}
