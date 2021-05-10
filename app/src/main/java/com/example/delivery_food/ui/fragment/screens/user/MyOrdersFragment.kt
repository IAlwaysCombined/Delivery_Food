package com.example.delivery_food.ui.fragment.screens.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery_food.R
import com.example.delivery_food.adapters.DishesListAdapter
import com.example.delivery_food.adapters.MyOrdersAdapter
import com.example.delivery_food.databinding.FragmentMyOrdersBinding
import com.example.delivery_food.models.CommonModel
import com.example.delivery_food.utilites.*
import com.google.firebase.database.DatabaseReference

class MyOrdersFragment : Fragment(R.layout.fragment_my_orders) {

    private lateinit var binding: FragmentMyOrdersBinding
    private lateinit var adapter: MyOrdersAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var refDishes: DatabaseReference
    private lateinit var orderListener: AppValueEventListener
    private var orderList = mutableListOf<CommonModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMyOrdersBinding.bind(view)
    }

    override fun onResume() {
        super.onResume()
        binding.orderBtnBack.setOnClickListener { backAccount() }
        getAllOrders()
    }

    private fun backAccount() {
        replaceFragment(AccountFragment())

    }

    private fun getAllOrders() {
        recyclerView = binding.orderList
        adapter = MyOrdersAdapter(mutableListOf())
        refDishes = REF_DATABASE_ROOT.child(NODE_ORDER).child(UID)
        recyclerView.adapter = adapter
        orderListener = AppValueEventListener { dataSnapshot ->
            orderList = dataSnapshot.children.map { it.getCommonModel() } as MutableList<CommonModel>
            adapter.setList(orderList)
            recyclerView.smoothScrollToPosition(adapter.itemCount)
        }
        refDishes.addValueEventListener(orderListener)

    }

}