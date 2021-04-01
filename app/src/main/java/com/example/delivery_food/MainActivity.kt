package com.example.delivery_food

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.delivery_food.activity.AuthActivity
import com.example.delivery_food.databinding.ActivityMainBinding
import com.example.delivery_food.models.Users
import com.example.delivery_food.ui.objects.AppDrawer
import com.example.delivery_food.utilites.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAppDrawer: AppDrawer

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

    //Initial Fields
    private fun initFields() {
        mAppDrawer = AppDrawer(this, mBinding)
        initFirebase()
        initUser()
    }

    //Initial Functions
    private fun initFunc() {
        if (AUTH.currentUser != null) {
            mAppDrawer.create()
        } else {
            replaceActivity(AuthActivity())
        }
    }
    //Initial Users
    private fun initUser() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID)
            .addListenerForSingleValueEvent(AppValueEventListener {
                USER = it.getValue(Users::class.java) ?: Users()
            })
    }
}