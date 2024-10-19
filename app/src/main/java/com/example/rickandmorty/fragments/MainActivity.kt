package com.example.rickandmorty.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rickandmorty.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigateToFragment(OnboardFragment())
        }
    }
    fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }
    fun navigateToOnboard() {
        navigateToFragment(OnboardFragment())
    }
    fun navigateToSignIn() {
        navigateToFragment(SignInFragment())
    }
    fun navigateToHome() {
        navigateToFragment(HomeFragment())
    }
    fun navigateToSignUp() {
        navigateToFragment(SignUpFragment())
    }
}