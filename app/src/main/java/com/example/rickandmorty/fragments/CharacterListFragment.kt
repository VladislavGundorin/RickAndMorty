package com.example.rickandmorty.fragments

import android.os.Bundle
import retrofit2.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.models.RickMorty
import com.example.rickandmorty.repository.RickMortyRepository
import retrofit2.Response

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
        val ids = (201..250).toList()

        for (id in ids) {
            repository.getCharactersByIds((201..250).joinToString(",")).enqueue(object : retrofit2.Callback<List<RickMorty>> {
                override fun onResponse(call: Call<List<RickMorty>>, response: Response<List<RickMorty>>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            characters.addAll(it)
                            adapter.notifyDataSetChanged()
                        }
                    } else {
                        Toast.makeText(context, "Ошибка: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<RickMorty>>, t: Throwable) {
                    Toast.makeText(context, "Ошибка загрузки данных: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
    }
