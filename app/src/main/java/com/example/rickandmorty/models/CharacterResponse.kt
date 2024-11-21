package com.example.rickandmorty.models

data class CharacterResponse(
    val results: List<RickMorty>,
    val info: Info
)

data class Info(
    val next: String?
)