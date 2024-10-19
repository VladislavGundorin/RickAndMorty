//package com.example.rickandmorty.activity
//
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import androidx.appcompat.app.AppCompatActivity
//import android.widget.Button
//import com.example.rickandmorty.R
//
//class OnboardActivity : AppCompatActivity() {
//
//    private val tag = "OnboardActivity"
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_onboard)
//        Log.d(tag, "onCreate called")
//
//        val startButton: Button = findViewById(R.id.startButton)
//        startButton.setOnClickListener {
//            navigateToSignIn()
//        }
//    }
//
//    private fun navigateToSignIn() {
//        val intent = Intent(this, SignInActivity::class.java)
//        startActivity(intent)
//    }
//
//    override fun onStart() {
//        super.onStart()
//        Log.d(tag, "onStart called")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.d(tag, "onResume called")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d(tag, "onPause called")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d(tag, "onStop called")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d(tag, "onDestroy called")
//    }
//}
