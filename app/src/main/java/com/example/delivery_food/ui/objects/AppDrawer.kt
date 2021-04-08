package com.example.delivery_food.ui.objects

import androidx.appcompat.app.AppCompatActivity
import com.example.delivery_food.R
import com.example.delivery_food.databinding.ActivityMainBinding
import com.example.delivery_food.ui.fragment.screens.user.AccountFragment
import com.example.delivery_food.ui.fragment.screens.user.BasketFragment
import com.example.delivery_food.ui.fragment.screens.user.CatalogFragment
import com.example.delivery_food.utilites.replaceFragment

class AppDrawer(var mainActivity: AppCompatActivity, private val mBinding: ActivityMainBinding) {

    fun create() {
        createBottomNav()
    }

    private fun createBottomNav(){
        mBinding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.restaurants -> mainActivity.replaceFragment(CatalogFragment())
                R.id.basket -> mainActivity.replaceFragment(BasketFragment())
                R.id.personal -> mainActivity.replaceFragment(AccountFragment())
            }
            true
        }
    }
}