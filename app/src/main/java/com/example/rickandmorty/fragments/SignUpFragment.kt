package com.example.rickandmorty.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentSignUpBinding
import com.example.rickandmorty.models.User

class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val sharedPrefs by lazy {
        requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonRegister.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            val confirmPassword = binding.editTextConfirmPassword.text.toString()

            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(context, "Поля не могут быть пустыми", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password == confirmPassword) {
                saveUserToSharedPreferences(email, password)
                Toast.makeText(context, "Регистрация успешна", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
            } else {
                Toast.makeText(context, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun saveUserToSharedPreferences(email: String, password: String) {
        sharedPrefs.edit().apply {
            putString("email", email)
            putString("password", password)
            apply()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
