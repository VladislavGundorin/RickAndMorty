package com.example.rickandmorty.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.database.RickMortyApp
import com.example.rickandmorty.database.toRickMorty
import com.example.rickandmorty.databinding.FragmentChatBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharacterListFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CharacterAdapter
    private val characters = mutableListOf<com.example.rickandmorty.models.RickMorty>()
    private val repository by lazy { (requireActivity().application as RickMortyApp).repository }

    private val ranges = mapOf(
        1 to (1..50),
        2 to (51..100),
        3 to (101..150),
        4 to (151..200),
        5 to (201..250),
        6 to (251..300),
        7 to (301..350),
        8 to (351..400),
        9 to (401..450),
        10 to (451..500),
        11 to (501..550),
        12 to (551..600),
        13 to (601..650),
        14 to (651..700),
        15 to (701..750)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupButtons()
        observeCharacters()

//        lifecycleScope.launch {
//            repository.getCharactersFromDb().collectLatest { dbCharacters ->
//                if (dbCharacters.isEmpty()) {
//                    fetchCharactersFromApi()
//                } else {
//                    characters.clear()
//                    characters.addAll(dbCharacters.map { it.toRickMorty() })
//                    adapter.notifyDataSetChanged()
//                }
//            }
//        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = CharacterAdapter(characters)
        binding.recyclerView.adapter = adapter
    }

    private fun setupButtons() {
        binding.buttonRefresh.setOnClickListener { fetchCharactersFromApi() }

        binding.buttonClear.setOnClickListener {
            lifecycleScope.launch {
                repository.clearAllCharacters()
                Toast.makeText(context, "База данных очищена", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonConfirm.setOnClickListener {
            val inputText = binding.inputListNumber.text.toString()
            val listNumber = inputText.toIntOrNull()

            if (listNumber != null && listNumber in 1..15) {
                val range = ranges[listNumber] ?: return@setOnClickListener
                fetchCharactersByRange(range)
            } else {
                Toast.makeText(context, "Введите номер от 1 до 15", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeCharacters() {
        lifecycleScope.launch {
            repository.getCharactersFromDb().collectLatest { dbCharacters ->
                characters.clear()
                characters.addAll(dbCharacters.map { it.toRickMorty() })
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun fetchCharactersFromApi() {
        lifecycleScope.launch {
            try {
                val ids = (1..20).joinToString(",")
                repository.refreshCharactersFromApi(ids)
                Toast.makeText(context, "Данные обновлены!", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(context, "Ошибка загрузки: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchCharactersByRange(range: IntRange) {
        lifecycleScope.launch {
            try {
                val ids = range.joinToString(",")
                repository.refreshCharactersFromApi(ids)
                Toast.makeText(context, "Загружены данные для диапазона: $range", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(context, "Ошибка: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
