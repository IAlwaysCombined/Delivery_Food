 package com.example.delivery_food

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.delivery_food.activity.AuthActivity
import com.example.delivery_food.databinding.ActivityMainBinding
import com.example.delivery_food.models.Address
import com.example.delivery_food.models.CommonModel
import com.example.delivery_food.models.Restaurant
import com.example.delivery_food.models.Users
import com.example.delivery_food.ui.fragment.screens.restaurant.AccountRestaurantFragment
import com.example.delivery_food.ui.fragment.screens.user.AccountFragment
import com.example.delivery_food.ui.fragment.screens.user.CatalogFragment
import com.example.delivery_food.ui.objects.AppDrawer
import com.example.delivery_food.ui.objects.AppDrawerAdmin
import com.example.delivery_food.ui.objects.AppDrawerRestaurant
import com.example.delivery_food.utilites.*
import com.google.firebase.database.DatabaseReference


 class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAppDrawer: AppDrawer
    private lateinit var mAppDrawerAdmin: AppDrawerAdmin
    private lateinit var mAppDrawerRestaurant: AppDrawerRestaurant
    private var mUserList = mutableListOf<CommonModel>()
    private lateinit var mUserListener: AppValueEventListener
    private lateinit var mRefUser: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        APP_ACTIVITY = this
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    //Initial fields
    private fun initFields() {
        mAppDrawer = AppDrawer(this, mBinding)
        mAppDrawerAdmin = AppDrawerAdmin(this, mBinding)
        mAppDrawerRestaurant = AppDrawerRestaurant(this, mBinding)
        initFirebase()
        initUser()
        initAddress()
        initRestaurant()
    }

    //Initial functions
    private fun initFunc() {
        if (AUTH.currentUser != null) {
            changeUserRole()
        } else {
            replaceActivity(AuthActivity())
        }
    }

    //Change user role
    private fun changeUserRole() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID)
            .addValueEventListener(AppValueEventListener {
                when (USER.role) {
                    ADMIN_ROLE -> {
                        mBinding.bottomNavigationView.menu.clear()
                        mBinding.bottomNavigationView.inflateMenu(R.menu.menu_bottom_nav_admin)
                        mAppDrawerAdmin.create()
                        replaceFragment(AccountFragment())
                    }
                    USER_ROLE -> {
                        mBinding.bottomNavigationView.menu.clear()
                        mBinding.bottomNavigationView.inflateMenu(R.menu.menu_bottom_nav_user)
                        mAppDrawer.create()
                        replaceFragment(CatalogFragment())
                    }
                    RESTAURANTS_ROLE -> {
                        mBinding.bottomNavigationView.menu.clear()
                        mBinding.bottomNavigationView.inflateMenu(R.menu.menu_bottom_nav_restaurant)
                        mAppDrawerRestaurant.create()
                        replaceFragment(AccountRestaurantFragment())
                    }
                }
            })
    }

    //Initial users
    private fun initUser() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID)
            .addListenerForSingleValueEvent(AppValueEventListener {
                USER = it.getValue(Users::class.java) ?: Users()
            })
    }

    //Initial address
    private fun initAddress() {
        REF_DATABASE_ROOT.child(NODE_ADDRESS).child(UID)
            .addListenerForSingleValueEvent(AppValueEventListener {
                ADDRESS = it.getValue(Address::class.java) ?: Address()
            })
    }

    //Initial restaurant
    private fun initRestaurant() {
        REF_DATABASE_ROOT.child(NODE_RESTAURANT).child(UID)
            .addListenerForSingleValueEvent(AppValueEventListener {
                RESTAURANT = it.getValue(Restaurant::class.java) ?: Restaurant()
            })
    }
}