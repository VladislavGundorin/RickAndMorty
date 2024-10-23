package com.example.rickandmorty.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentChatBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var chatAdapter: ChatAdapter
    private val chatList = listOf(
        Chat("Вика", "Привет! Гулть пойдешь?", "10:30", R.drawable.ic_profile_placeholder),
        Chat("Марк", "чек мем", "09:15", R.drawable.ic_profile_placeholder),
        Chat("Денис", "Увидимся завтра.", "Вчера", R.drawable.ic_profile_placeholder)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        chatAdapter = ChatAdapter(chatList)
        binding.recyclerView.adapter = chatAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
