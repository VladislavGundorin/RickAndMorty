package com.example.rickandmorty.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.rickandmorty.R
import com.example.rickandmorty.models.User

class SignInFragment: Fragment() {
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emailInput = view.findViewById(R.id.emailInput)
        passwordInput = view.findViewById(R.id.passwordInput)
        val signInButton: Button = view.findViewById(R.id.signInButton)
        val registerLink: TextView = view.findViewById(R.id.registerLink)

        val user = arguments?.getSerializable("user") as? User
        user?.let {
            emailInput.setText(it.email)
            passwordInput.setText(it.password)
        }

        signInButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (email == "test" && password == "test") {
                Toast.makeText(context, "Вход успешен", Toast.LENGTH_SHORT).show()
                (activity as MainActivity).navigateToHome()
            }else {
                Toast.makeText(context, "Неверный email или пароль", Toast.LENGTH_SHORT).show()
            }
        }
        registerLink.setOnClickListener {
            (activity as MainActivity).navigateToSignUp()
        }
    }
}