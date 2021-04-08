package com.example.delivery_food.ui.fragment.screens.admin

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery_food.R
import com.example.delivery_food.adapters.RequestRestaurantListAdapter
import com.example.delivery_food.adapters.UserListAdapter
import com.example.delivery_food.models.CommonModel
import com.example.delivery_food.utilites.*
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.fragment_request.*
import kotlinx.android.synthetic.main.fragment_user_list.*

class RequestFragment : Fragment(R.layout.fragment_request) {

    private lateinit var mAdapter: RequestRestaurantListAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mRefRestaurant: DatabaseReference
    private lateinit var mRestaurantRequestListener: AppValueEventListener
    private var mRestaurantRequestList = mutableListOf<CommonModel>()

    override fun onStart() {
        super.onStart()
        getAllUsers()
    }

    private fun getAllUsers() {
        mRecyclerView = request_restaurant_recycler_view
        mAdapter = RequestRestaurantListAdapter(mutableListOf())
        mRefRestaurant = REF_DATABASE_ROOT.child(NODE_ORDERS_CREATE_RESTAURANT)
        mRecyclerView.adapter = mAdapter
        mRestaurantRequestListener = AppValueEventListener { dataSnapshot ->
            mRestaurantRequestList = dataSnapshot.children.map { it.getCommonModel() } as MutableList<CommonModel>
            mAdapter.setList(mRestaurantRequestList)
            mRecyclerView.smoothScrollToPosition(mAdapter.itemCount)
        }
        mRefRestaurant.addValueEventListener(mRestaurantRequestListener)
    }
}