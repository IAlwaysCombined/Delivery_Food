package com.example.delivery_food.ui.fragment.screens.admin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery_food.R
import com.example.delivery_food.adapters.RequestRestaurantListAdapter
import com.example.delivery_food.databinding.FragmentRequestBinding
import com.example.delivery_food.models.CommonModel
import com.example.delivery_food.utilites.AppValueEventListener
import com.example.delivery_food.utilites.NODE_ORDERS_CREATE_RESTAURANT
import com.example.delivery_food.utilites.REF_DATABASE_ROOT
import com.example.delivery_food.utilites.getCommonModel
import com.google.firebase.database.DatabaseReference

class RequestFragment : Fragment(R.layout.fragment_request) {

    private lateinit var binding: FragmentRequestBinding
    private lateinit var mAdapter: RequestRestaurantListAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mRefRestaurant: DatabaseReference
    private lateinit var mRestaurantRequestListener: AppValueEventListener
    private var mRestaurantRequestList = mutableListOf<CommonModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRequestBinding.bind(view)
    }

    override fun onResume() {
        super.onResume()
        getAllUsers()
    }

    private fun getAllUsers() {
        mRecyclerView = binding.requestRestaurantRecyclerView
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