package com.example.rickandmorty.database

import com.example.rickandmorty.network.RickAndMortyApi
import kotlinx.coroutines.flow.Flow

class RickMortyRepository(
    private val api: RickAndMortyApi,
    private val dao: RickMortyDao
) {
    fun getCharactersFromDb(): Flow<List<RickMortyEntity>> = dao.getAllCharacters()

    suspend fun refreshCharactersFromApi(ids: String) {
        val characters = api.getCharactersByIds(ids)
        val entities = characters.map { RickMortyEntity(it.id, it.name, it.status, it.species, it.gender, it.image) }
        dao.clearAllCharacters()
        dao.insertCharacters(entities)
    }
    suspend fun clearAllCharacters() {
        dao.clearAllCharacters()
    }
}
