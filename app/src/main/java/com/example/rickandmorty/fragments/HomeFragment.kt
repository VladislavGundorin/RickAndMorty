package com.example.rickandmorty.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R

class HomeFragment: Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_chat, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        chatAdapter = ChatAdapter(chatList)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = chatAdapter

        return view
    }
}
