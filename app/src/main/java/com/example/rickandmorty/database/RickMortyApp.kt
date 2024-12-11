package com.example.rickandmorty.database

import android.app.Application
import com.example.rickandmorty.network.RetrofitInstance

class RickMortyApp : Application() {
    lateinit var repository: RickMortyRepository
        private set

    override fun onCreate() {
        super.onCreate()
        val database = AppDatabase.getDatabase(this)
        repository = RickMortyRepository(
            RetrofitInstance.api,
            database.characterDao())
    }
}
