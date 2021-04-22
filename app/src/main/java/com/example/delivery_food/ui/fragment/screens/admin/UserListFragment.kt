package com.example.delivery_food.ui.fragment.screens.admin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery_food.R
import com.example.delivery_food.adapters.UserListAdapter
import com.example.delivery_food.databinding.FragmentUserListBinding
import com.example.delivery_food.models.CommonModel
import com.example.delivery_food.utilites.AppValueEventListener
import com.example.delivery_food.utilites.NODE_USERS
import com.example.delivery_food.utilites.REF_DATABASE_ROOT
import com.example.delivery_food.utilites.getCommonModel
import com.google.firebase.database.DatabaseReference

class UserListFragment: Fragment(R.layout.fragment_user_list) {

    private lateinit var binding: FragmentUserListBinding
    private lateinit var mAdapter: UserListAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mRefUser: DatabaseReference
    private lateinit var mUsersListener: AppValueEventListener
    private var mUserList = mutableListOf<CommonModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserListBinding.bind(view)
    }

    override fun onStart() {
        super.onStart()
        getAllUsers()
    }

    private fun getAllUsers() {
        mRecyclerView = binding.usersRecyclerView
        mAdapter = UserListAdapter(mutableListOf())
        mRefUser = REF_DATABASE_ROOT.child(NODE_USERS)
        mRecyclerView.adapter = mAdapter
        mUsersListener = AppValueEventListener { dataSnapshot ->
            mUserList = dataSnapshot.children.map { it.getCommonModel() } as MutableList<CommonModel>
            mAdapter.setList(mUserList)
            mRecyclerView.smoothScrollToPosition(mAdapter.itemCount)
        }
        mRefUser.addValueEventListener(mUsersListener)
    }
}
