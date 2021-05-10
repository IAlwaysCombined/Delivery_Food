 package com.example.delivery_food

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.delivery_food.activity.AuthActivity
import com.example.delivery_food.databinding.ActivityMainBinding
import com.example.delivery_food.ui.fragment.screens.admin.AdminAccountFragment
import com.example.delivery_food.ui.fragment.screens.user.CatalogFragment
import com.example.delivery_food.ui.objects.AppDrawer
import com.example.delivery_food.ui.objects.AppDrawerAdmin
import com.example.delivery_food.utilites.*


 class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAppDrawer: AppDrawer
    private lateinit var mAppDrawerAdmin: AppDrawerAdmin

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
                        replaceFragment(AdminAccountFragment())
                    }
                    USER_ROLE -> {
                        mBinding.bottomNavigationView.menu.clear()
                        mBinding.bottomNavigationView.inflateMenu(R.menu.menu_bottom_nav_user)
                        mAppDrawer.create()
                        replaceFragment(CatalogFragment())
                    }
                }
            })
    }
}