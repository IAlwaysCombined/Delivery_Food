package com.example.delivery_food.ui.objects

import androidx.appcompat.app.AppCompatActivity
import com.example.delivery_food.R
import com.example.delivery_food.databinding.ActivityMainBinding
import com.example.delivery_food.ui.fragment.screens.restaurant.AccountRestaurantFragment
import com.example.delivery_food.ui.fragment.screens.restaurant.AddDishesFragment
import com.example.delivery_food.ui.fragment.screens.restaurant.DishesListFragment
import com.example.delivery_food.ui.fragment.screens.restaurant.OrdersFragment
import com.example.delivery_food.utilites.replaceFragment

class AppDrawerRestaurant(var mainActivity: AppCompatActivity, private val mBinding: ActivityMainBinding) {

    fun create() {
        createBottomNav()
    }

    private fun createBottomNav(){
        mBinding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.account_restaurant -> mainActivity.replaceFragment(AccountRestaurantFragment())
                R.id.orders -> mainActivity.replaceFragment(OrdersFragment())
                R.id.dishes_list -> mainActivity.replaceFragment(DishesListFragment())
                R.id.add_dishes -> mainActivity.replaceFragment(AddDishesFragment())
            }
            true
        }
    }

}