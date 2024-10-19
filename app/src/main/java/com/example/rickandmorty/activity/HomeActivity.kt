//package com.example.rickandmorty.activity
//
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import android.widget.Button
//import android.widget.ImageView
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import coil.load
//import com.example.rickandmorty.R
//import com.example.rickandmorty.models.RickMorty
//import com.example.rickandmorty.network.RetrofitInstance
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class HomeActivity : AppCompatActivity() {
//
//    private val tag = "HomeActivity"
//    private lateinit var imageView1: ImageView
//    private lateinit var textViewName1: TextView
//    private lateinit var buttonDetails1: Button
//    private lateinit var imageView2: ImageView
//    private lateinit var textViewName2: TextView
//    private lateinit var buttonDetails2: Button
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home)
//
//        imageView1 = findViewById(R.id.imageView1)
//        textViewName1 = findViewById(R.id.textViewName1)
//        buttonDetails1 = findViewById(R.id.buttonDetails1)
//
//        imageView2 = findViewById(R.id.imageView2)
//        textViewName2 = findViewById(R.id.textViewName2)
//        buttonDetails2 = findViewById(R.id.buttonDetails2)
//
//        loadCharacterData(16, imageView1, textViewName1, buttonDetails1)
//        loadCharacterData(147, imageView2, textViewName2, buttonDetails2)
//    }
//
//    private fun loadCharacterData(
//        id: Int,
//        imageView: ImageView,
//        textView: TextView,
//        button: Button
//    ) {
//        RetrofitInstance.api.getCharacter(id).enqueue(object : Callback<RickMorty> {
//            override fun onResponse(call: Call<RickMorty>, response: Response<RickMorty>) {
//                if (response.isSuccessful) {
//                    val character = response.body()
//                    character?.let { rickMorty ->
//                        textView.text = rickMorty.name
//                        imageView.load(rickMorty.image)
//
//                        button.setOnClickListener {
//                            openCharacterDetails(rickMorty)
//                        }
//                    }
//                } else {
//                    Log.e(tag, "Ошибка загрузки данных: ${response.code()}")
//                    Toast.makeText(this@HomeActivity, "Ошибка загрузки данных", Toast.LENGTH_LONG).show()
//                }
//            }
//
//            override fun onFailure(call: Call<RickMorty>, t: Throwable) {
//                Log.e(tag, "Ошибка загрузки данных", t)
//                Toast.makeText(this@HomeActivity, "Ошибка загрузки данных", Toast.LENGTH_LONG).show()
//            }
//        })
//    }
//
//    private fun openCharacterDetails(character: RickMorty) {
//        val intent = Intent(this, CharacterDetailActivity::class.java)
//        intent.putExtra("character", character)
//        startActivity(intent)
//    }
//}
