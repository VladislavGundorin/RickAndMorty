package com.example.rickandmorty

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.rickandmorty.models.RickMorty

class CharacterDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        val character = intent.getSerializableExtra("character") as? RickMorty

        character?.let {
            val textViewName = findViewById<TextView>(R.id.textViewName)
            val imageView = findViewById<ImageView>(R.id.imageViewCharacter)
            val textViewStatus = findViewById<TextView>(R.id.textViewStatus)
            val textViewSpecies = findViewById<TextView>(R.id.textViewSpecies)

            textViewName.text = it.name
            imageView.load(it.image)
            textViewStatus.text = "Статус: ${it.status}"
            textViewSpecies.text = "Вид: ${it.species}"
        }
    }
}
