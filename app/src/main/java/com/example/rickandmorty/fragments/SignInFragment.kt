package com.example.rickandmorty.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val args: SignInFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = args.user

        if (user != null) {
            binding.emailInput.setText(user.email)
            binding.passwordInput.setText(user.password)
        }

        binding.signInButton.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()

            if (email == user?.email && password == user?.password) {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

