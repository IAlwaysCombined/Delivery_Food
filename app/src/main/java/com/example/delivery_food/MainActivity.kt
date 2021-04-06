package com.example.delivery_food

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.delivery_food.activity.AuthActivity
import com.example.delivery_food.databinding.ActivityMainBinding
import com.example.delivery_food.models.Address
import com.example.delivery_food.models.Users
import com.example.delivery_food.ui.fragment.AccountFragment
import com.example.delivery_food.ui.fragment.CatalogFragment
import com.example.delivery_food.ui.objects.AppDrawer
import com.example.delivery_food.ui.objects.AppDrawerAdmin
import com.example.delivery_food.utilites.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAppDrawer: AppDrawer
    private lateinit var mAppDrawerAdmin: AppDrawerAdmin

    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
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
    }

    //Initial functions
    private fun initFunc() {
        if (AUTH.currentUser != null) {
            REF_DATABASE_ROOT.child(NODE_USERS).child(UID)
                .addValueEventListener(AppValueEventListener {
                    Toast.makeText(this@MainActivity, USER.role, Toast.LENGTH_SHORT).show()
                    when (USER.role) {
                        ADMIN_ROLE -> {
                            bottom_navigation_view.menu.clear()
                            bottom_navigation_view.inflateMenu(R.menu.menu_bottom_nav_admin)
                            mAppDrawerAdmin.create()
                            replaceFragment(AccountFragment())
                        }
                        USER_ROLE -> {
                            bottom_navigation_view.menu.clear()
                            bottom_navigation_view.inflateMenu(R.menu.menu_bottom_nav_user)
                            mAppDrawer.create()
                            replaceFragment(CatalogFragment())
                        }
                        RESTAURANTS_ROLE -> {
                            bottom_navigation_view.menu.clear()
                        }
                    }
                })
        } else {
            replaceActivity(AuthActivity())
        }
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
}
