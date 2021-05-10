package com.example.delivery_food.ui.fragment.screens.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.delivery_food.R
import com.example.delivery_food.activity.AuthActivity
import com.example.delivery_food.databinding.FragmentAdminAccountBinding
import com.example.delivery_food.utilites.AUTH
import com.example.delivery_food.utilites.USER
import com.example.delivery_food.utilites.replaceActivity


class AdminAccountFragment : Fragment(R.layout.fragment_admin_account) {

    private lateinit var binding: FragmentAdminAccountBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAdminAccountBinding.bind(view)
    }

    override fun onResume() {
        super.onResume()
        binding.accountAdminName.text = USER.name
        binding.accountAdminRole.text = USER.role
        binding.accountAdminBtnExit.setOnClickListener { exitBtn() }
    }

    private fun exitBtn() {
        replaceActivity(AuthActivity())
        AUTH.signOut()
    }
}