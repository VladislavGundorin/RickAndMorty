package com.example.rickandmorty.models

import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val results: List<RickMorty>
)
