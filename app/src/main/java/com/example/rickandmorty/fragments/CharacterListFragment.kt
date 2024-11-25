package com.example.rickandmorty.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.models.RickMorty
import com.example.rickandmorty.repository.RickMortyRepository
import kotlinx.coroutines.launch

class CharacterListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CharacterAdapter
    private val characters = mutableListOf<RickMorty>()
    private val repository = RickMortyRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = CharacterAdapter(characters)
        recyclerView.adapter = adapter

        loadCharacters()
    }

    private fun loadCharacters() {
        val ids = (201..250).joinToString(",")
        lifecycleScope.launch {
            try {
                val loadedCharacters = repository.getCharactersByIds(ids)
                characters.clear()
                characters.addAll(loadedCharacters)
                adapter.notifyDataSetChanged()
                Toast.makeText(context, "Данные загружены: ${loadedCharacters.size}", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(context, "Ошибка загрузки данных: ${e.message}", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }
}
