package com.example.rickandmorty.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RickMortyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<RickMortyEntity>)

    @Query("SELECT * FROM rick_morty_characters")
    fun getAllCharacters(): Flow<List<RickMortyEntity>>

    @Query("DELETE FROM rick_morty_characters")
    suspend fun clearAllCharacters()
}
