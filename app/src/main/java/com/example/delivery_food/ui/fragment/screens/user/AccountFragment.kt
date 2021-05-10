package com.example.delivery_food.ui.fragment.screens.user

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.delivery_food.R
import com.example.delivery_food.activity.AuthActivity
import com.example.delivery_food.databinding.FragmentAccountBinding
import com.example.delivery_food.utilites.AUTH
import com.example.delivery_food.utilites.USER
import com.example.delivery_food.utilites.replaceActivity
import com.example.delivery_food.utilites.replaceFragment


class AccountFragment : Fragment(R.layout.fragment_account) {

    private lateinit var binding: FragmentAccountBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAccountBinding.bind(view)
    }

    override fun onResume() {
        super.onResume()
        binding.nameTextView.text = USER.name
        binding.btnExit.setOnClickListener { signOut() }
        binding.dataUser.setOnClickListener { changeDataUser() }
        binding.addressUser.setOnClickListener { changeAddressUser() }
        binding.orderRestaurants.setOnClickListener { orderOnCrateRestaurant() }
        binding.ordersUser.setOnClickListener { replaceFragment(MyOrdersFragment()) }
    }

    //Create restaurant order replace fragment
    private fun orderOnCrateRestaurant() {
        replaceFragment(CreateOrdersRestaurantFragment())
    }

    //SignOut account
    private fun signOut() {
        replaceActivity(AuthActivity())
        AUTH.signOut()
    }

    //Change date user
    private fun changeDataUser() {
        replaceFragment(ChangeDataFragment())
    }

    //Change Address
    private fun changeAddressUser() {
        replaceFragment(ChangeAddressFragment())
    }
}