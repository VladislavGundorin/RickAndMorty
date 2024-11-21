package com.example.rickandmorty.fragments

//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.rickandmorty.R
//import com.example.rickandmorty.models.RickMorty
//
//class CharacterAdapter(private val characters: List<RickMorty>) :
//    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_name, parent, false)
//        return CharacterViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
//        val character = characters[position]
//        holder.name.text = character.name
//        holder.status.text = character.status
//        holder.species.text = character.species
//        holder.gender.text = character.gender
//        holder.origin.text = character.origin.name
//        holder.location.text = character.location.name
//    }
//
//    override fun getItemCount(): Int = characters.size
//
//    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val name: TextView = itemView.findViewById(R.id.character_name)
//        val status: TextView = itemView.findViewById(R.id.character_status)
//        val species: TextView = itemView.findViewById(R.id.character_species)
//        val gender: TextView = itemView.findViewById(R.id.character_gender)
//        val origin: TextView = itemView.findViewById(R.id.character_origin)
//        val location: TextView = itemView.findViewById(R.id.character_location)
//    }
//}
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.models.RickMorty

class CharacterAdapter(private val characters: List<RickMorty>) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_name, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]

        holder.id.text = "ID: ${character.id}"
        holder.name.text = character.name
        holder.status.text = character.status
        holder.species.text = character.species
        holder.gender.text = character.gender
        holder.origin.text = character.origin.name
        holder.location.text = character.location.name

        Glide.with(holder.itemView.context)
            .load(character.image)
            .into(holder.image)
    }

    override fun getItemCount(): Int = characters.size

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.character_image)
        val id: TextView = itemView.findViewById(R.id.character_id)
        val name: TextView = itemView.findViewById(R.id.character_name)
        val status: TextView = itemView.findViewById(R.id.character_status)
        val species: TextView = itemView.findViewById(R.id.character_species)
        val gender: TextView = itemView.findViewById(R.id.character_gender)
        val origin: TextView = itemView.findViewById(R.id.character_origin)
        val location: TextView = itemView.findViewById(R.id.character_location)
    }
}
