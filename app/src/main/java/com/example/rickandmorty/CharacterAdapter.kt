package com.example.rickandmorty

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Chat(
    val senderName: String,
    val lastMessage: String,
    val messageTime: String,
    val profileImageRes: Int
)

class ChatAdapter(private val chatList: List<Chat>) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_item, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = chatList[position]
        holder.senderName.text = chat.senderName
        holder.lastMessage.text = chat.lastMessage
        holder.messageTime.text = chat.messageTime
        holder.profileImage.setImageResource(chat.profileImageRes)
    }

    override fun getItemCount(): Int = chatList.size

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val senderName: TextView = itemView.findViewById(R.id.sender_name)
        val lastMessage: TextView = itemView.findViewById(R.id.last_message)
        val messageTime: TextView = itemView.findViewById(R.id.message_time)
        val profileImage: ImageView = itemView.findViewById(R.id.profile_image)
    }
}
