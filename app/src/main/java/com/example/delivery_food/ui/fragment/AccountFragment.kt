package com.example.delivery_food.ui.fragment

import androidx.fragment.app.Fragment
import com.example.delivery_food.R
import com.example.delivery_food.activity.AuthActivity
import com.example.delivery_food.utilites.AUTH
import com.example.delivery_food.utilites.USER
import com.example.delivery_food.utilites.replaceActivity
import com.example.delivery_food.utilites.replaceFragment
import kotlinx.android.synthetic.main.fragment_account.*


class AccountFragment : Fragment(R.layout.fragment_account) {

    override fun onResume() {
        super.onResume()
        name_text_view.text = USER.name
        btn_exit.setOnClickListener { signOut() }
        data_user.setOnClickListener { changeDataUser() }
        address_user.setOnClickListener { changeAddressUser() }
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