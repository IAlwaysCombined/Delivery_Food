package com.example.delivery_food.ui.objects

import androidx.appcompat.app.AppCompatActivity
import com.example.delivery_food.MainActivity
import com.example.delivery_food.R
import com.example.delivery_food.databinding.ActivityMainBinding
import com.example.delivery_food.models.CommonModel
import com.example.delivery_food.ui.fragment.*
import com.example.delivery_food.utilites.replaceFragment

class AppDrawerAdmin(var mainActivity: AppCompatActivity, private val mBinding: ActivityMainBinding) {

    fun create() {
        createBottomNav()
    }

    private fun createBottomNav(){
        mBinding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.account -> mainActivity.replaceFragment(AccountFragment())
                R.id.request -> mainActivity.replaceFragment(RequestFragment())
                R.id.user_list -> mainActivity.replaceFragment(UserListFragment())
            }
            true
        }
    }
}