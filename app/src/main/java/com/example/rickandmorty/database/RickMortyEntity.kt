package com.example.rickandmorty.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmorty.models.RickMorty

@Entity(tableName = "rick_morty_characters")
data class RickMortyEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String
)

fun RickMortyEntity.toRickMorty(): RickMorty {
    return RickMorty(id, name, status, species, gender, image)
}
