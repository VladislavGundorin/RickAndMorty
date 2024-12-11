package com.example.rickandmorty.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.databinding.CharacterNameBinding
import com.example.rickandmorty.models.RickMorty

class CharacterAdapter(private val characters: List<RickMorty>) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = CharacterNameBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)
    }

    override fun getItemCount(): Int = characters.size

    class CharacterViewHolder(private val binding: CharacterNameBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(character: RickMorty) {
            with(binding) {
                characterId.text = "ID: ${character.id}"
                characterName.text = character.name
                characterStatus.text = character.status
                characterSpecies.text = character.species
                characterGender.text = character.gender

                Glide.with(root.context)
                    .load(character.image)
                    .into(characterImage)
            }
        }
    }
}
