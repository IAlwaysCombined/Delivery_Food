package com.example.delivery_food.ui.fragment.screens.user

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery_food.R
import com.example.delivery_food.adapters.RequestRestaurantListAdapter
import com.example.delivery_food.adapters.RestaurantListAdapter
import com.example.delivery_food.databinding.FragmentCatalogBinding
import com.example.delivery_food.models.CommonModel
import com.example.delivery_food.utilites.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.example.delivery_food.utilites.REF_DATABASE_ROOT as REF_DATABASE_ROOT

class CatalogFragment : Fragment(R.layout.fragment_catalog) {

    private lateinit var mAdapter: RestaurantListAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mRefRestaurant: DatabaseReference
    private lateinit var mRestaurantListener: AppValueEventListener
    private var mRestaurantList = mutableListOf<CommonModel>()

    private lateinit var binding: FragmentCatalogBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCatalogBinding.bind(view)
    }

    override fun onResume() {
        super.onResume()
        getAllRestaurant()
    }

    private fun getAllRestaurant() {
        mRecyclerView = binding.recyclerViewRestaurantList
        mAdapter = RestaurantListAdapter(mutableListOf())
        mRefRestaurant = REF_DATABASE_ROOT.child(NODE_RESTAURANT)
        mRecyclerView.adapter = mAdapter
        mRestaurantListener = AppValueEventListener { dataSnapshot ->
            mRestaurantList =
                dataSnapshot.children.map { it.getCommonModel() } as MutableList<CommonModel>
            mAdapter.setList(mRestaurantList)
            mRecyclerView.smoothScrollToPosition(mAdapter.itemCount)
        }
        mRefRestaurant.addValueEventListener(mRestaurantListener)

    }
}