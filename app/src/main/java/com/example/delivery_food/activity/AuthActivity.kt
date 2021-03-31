package com.example.delivery_food.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.delivery_food.databinding.ActivityAuthBinding
import com.example.delivery_food.ui.fragment.AuthFragment
import com.example.delivery_food.utilites.initFirebase
import com.example.delivery_food.utilites.replaceFragmentAuth

class AuthActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityAuthBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initFirebase()
    }

    override fun onStart() {
        super.onStart()
        replaceFragmentAuth(AuthFragment())
    }
}