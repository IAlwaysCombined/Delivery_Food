package com.example.delivery_food.ui.fragment.screens.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.delivery_food.R
import com.example.delivery_food.databinding.FragmentStartBinding
import com.example.delivery_food.utilites.replaceFragment
import com.example.delivery_food.utilites.replaceFragmentAuth


class StartFragment : Fragment(R.layout.fragment_start) {

    private lateinit var binding: FragmentStartBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStartBinding.bind(view)
    }

    override fun onStart() {
        super.onStart()
        binding.authBtnStartFragment.setOnClickListener { replaceFragmentAuth(AuthFragment()) }
        binding.registrationBtnStartFragment.setOnClickListener { replaceFragmentAuth(RegistrationFragment()) }
    }
}