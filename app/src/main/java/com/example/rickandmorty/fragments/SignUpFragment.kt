package com.example.rickandmorty.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.rickandmorty.R
import com.example.rickandmorty.models.User

class SignUpFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editTextEmail = view.findViewById<EditText>(R.id.editTextEmail)
        val editTextPassword = view.findViewById<EditText>(R.id.editTextPassword)
        val editTextConfirmPassword = view.findViewById<EditText>(R.id.editTextConfirmPassword)
        val buttonRegister = view.findViewById<Button>(R.id.buttonRegister)


        buttonRegister.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val confirmPassword = editTextConfirmPassword.text.toString()

            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(context, "Поля не могут быть пустыми", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password == confirmPassword) {
                val user = User(email, password)
                val bundle = Bundle().apply {
                    putSerializable("user", user)
                }

                val signInFragment = SignInFragment().apply {
                    arguments = bundle
                }

                Toast.makeText(context, "Регистрация успешна", Toast.LENGTH_SHORT).show()
                (activity as MainActivity).navigateToFragment(signInFragment)
            } else {
                Toast.makeText(context, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
            }
        }
    }
}