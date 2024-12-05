package com.example.rickandmorty.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentSignInBinding
import com.example.rickandmorty.models.User

class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val sharedPrefs by lazy {
        requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val savedUser = getUserFromSharedPreferences()
        if (savedUser != null) {
            binding.emailInput.setText(savedUser.email)
            binding.passwordInput.setText(savedUser.password)
        }

        binding.signInButton.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()

            if (savedUser != null && email == savedUser.email && password == savedUser.password) {
                Toast.makeText(context, "Вход успешен", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_signInFragment_to_characterListFragment)
            } else {
                Toast.makeText(context, "Неверный email или пароль", Toast.LENGTH_SHORT).show()
            }
        }

        binding.registerLink.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    private fun getUserFromSharedPreferences(): User? {
        val email = sharedPrefs.getString("email", null)
        val password = sharedPrefs.getString("password", null)
        return if (email != null && password != null) User(email, password) else null
    }

    private fun clearUserFromSharedPreferences() {
        sharedPrefs.edit().clear().apply()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_exit -> {
                clearUserFromSharedPreferences()
                Toast.makeText(context, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_signInFragment_to_signInFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
