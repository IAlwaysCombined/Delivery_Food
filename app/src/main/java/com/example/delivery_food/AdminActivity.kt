package com.example.delivery_food

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.delivery_food.databinding.ActivityAdminBinding
import com.example.delivery_food.ui.objects.AppDrawerAdmin

class AdminActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityAdminBinding
    private lateinit var mAppDrawerAdmin: AppDrawerAdmin


    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityAdminBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initFields()
        initFunc()
    }

    private fun initFields() {
        mAppDrawerAdmin = AppDrawerAdmin(this, mBinding)
    }

    //Initial functions
    private fun initFunc() {
        mAppDrawerAdmin.create()
    }

}