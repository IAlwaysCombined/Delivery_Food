package com.example.delivery_food.ui.objects

import androidx.appcompat.app.AppCompatActivity
import com.example.delivery_food.R
import com.example.delivery_food.databinding.ActivityAdminBinding
import com.example.delivery_food.ui.fragment.*
import com.example.delivery_food.utilites.replaceFragment

class AppDrawerAdmin(var adminActivity: AppCompatActivity, private val mBinding: ActivityAdminBinding) {

    fun create() {
        createBottomNav()
    }

    private fun createBottomNav(){
        mBinding.bottomNavigationViewAdmin.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.restaurants -> adminActivity.replaceFragment(AccountFragment())
                R.id.request -> adminActivity.replaceFragment(RequestFragment())
                R.id.user_list -> adminActivity.replaceFragment(UserListFragment())
            }
            true
        }
    }

}