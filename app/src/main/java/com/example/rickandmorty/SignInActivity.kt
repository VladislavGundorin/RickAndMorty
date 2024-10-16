package com.example.rickandmorty

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmorty.models.User

class SignInActivity : AppCompatActivity() {

    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private val tag = "SignInActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        Log.d(tag, "onCreate called")

        emailInput = findViewById(R.id.emailInput)
        passwordInput = findViewById(R.id.passwordInput)
        val signInButton: Button = findViewById(R.id.signInButton)
        val registerLink: TextView = findViewById(R.id.registerLink)

        signInButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (email == "test" && password == "test") {
                Toast.makeText(this, "Вход успешен", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Неверный email или пароль", Toast.LENGTH_SHORT).show()
            }
        }
        registerLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy called")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(tag, "onActivityResult called with requestCode $requestCode and resultCode $resultCode")

        if (requestCode == 1 && resultCode == RESULT_OK) {
            val email = data?.getStringExtra("email")
            val password = data?.getStringExtra("password")
            val user = data?.getSerializableExtra("user") as? User

            if (user != null) {
                emailInput.setText(user.email)
                passwordInput.setText(user.password)
                Toast.makeText(this, "Данные получены через объект User", Toast.LENGTH_LONG).show()
            } else if (email != null && password != null) {
                emailInput.setText(email)
                passwordInput.setText(password)
                Toast.makeText(this, "Данные получены через String", Toast.LENGTH_LONG).show()
            }
        }
    }
}
