package com.example.delivery_food.ui.fragment.screens.user

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery_food.R
import com.example.delivery_food.adapters.DishesListAdapter
import com.example.delivery_food.databinding.FragmentDishesListBinding
import com.example.delivery_food.models.CommonModel
import com.example.delivery_food.utilites.*
import com.google.firebase.database.DatabaseReference


class DishesListFragment(bundle: Bundle) : Fragment(R.layout.fragment_dishes_list) {

    private lateinit var binding: FragmentDishesListBinding
    private lateinit var adapter: DishesListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var refDishes: DatabaseReference
    private lateinit var dishesListener: AppValueEventListener
    private var dishesList = mutableListOf<CommonModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDishesListBinding.bind(view)
    }

    override fun onResume() {
        super.onResume()
        getAllDishes()
    }

    private fun getAllDishes() {
        val name: String? = requireArguments().getString("nameRestaurant")
        val uid: String? = requireArguments().getString("uidRestaurant")
        binding.restaurantName.text = name
        recyclerView = binding.dishesListRecyclerView
        adapter = DishesListAdapter(mutableListOf())
        refDishes = REF_DATABASE_ROOT.child(NODE_DISHES).child(uid.toString())
        recyclerView.adapter = adapter
        dishesListener = AppValueEventListener { dataSnapshot ->
            dishesList = dataSnapshot.children.map { it.getCommonModel() } as MutableList<CommonModel>
            adapter.setList(dishesList)
            recyclerView.smoothScrollToPosition(adapter.itemCount)
        }
        refDishes.addValueEventListener(dishesListener)

    }
}