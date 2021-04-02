package com.example.delivery_food

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.delivery_food.activity.AuthActivity
import com.example.delivery_food.databinding.ActivityMainBinding
import com.example.delivery_food.models.Address
import com.example.delivery_food.models.Users
import com.example.delivery_food.ui.fragment.CatalogFragment
import com.example.delivery_food.ui.objects.AppDrawer
import com.example.delivery_food.ui.objects.AppDrawerAdmin
import com.example.delivery_food.utilites.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

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

    //Initial fields
    private fun initFields() {
        mAppDrawer = AppDrawer(this, mBinding)
        initFirebase()
        initUser()
        initAddress()
    }

    //Initial functions
    private fun initFunc() {
        if (AUTH.currentUser != null) {
            REF_DATABASE_ROOT.child(NODE_USERS).child(UID)
                .addValueEventListener(object: ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val roleUser = snapshot.child(CHILD_ROLE).value.toString()
                        if (roleUser == ADMIN_ROLE) {
                            replaceActivity(AdminActivity())
                        } else {
                            mAppDrawer.create()
                            replaceFragment(CatalogFragment())
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
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