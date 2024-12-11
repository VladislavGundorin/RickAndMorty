package com.example.rickandmorty.models

import kotlinx.serialization.Serializable

@Serializable
data class RickMorty(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String
)